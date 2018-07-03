package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.DepartureDao;
import net.thumbtack.airline.errors.types.runtime.BadOrderIdError;
import net.thumbtack.airline.errors.types.runtime.BadPlaceError;
import net.thumbtack.airline.errors.types.runtime.FirstLastNameMismatchError;
import net.thumbtack.airline.errors.types.runtime.PlaceNotExistError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.BadPlaceException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.mappers.DepartureMapper;
import net.thumbtack.airline.mappers.OrdersMapper;
import net.thumbtack.airline.mappers.PassengersMapper;
import net.thumbtack.airline.mappers.PlacesInPlaneMapper;
import net.thumbtack.airline.model.Departure;
import net.thumbtack.airline.model.PlaceInPlane;
import net.thumbtack.airline.model.PlaneInfo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DepartureDaoImpl extends BaseDaoImpl implements DepartureDao {

	private final Logger logger = LoggerFactory.getLogger(DepartureDaoImpl.class);

	protected DepartureMapper getDepartureMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(DepartureMapper.class);
	}

	protected PlacesInPlaneMapper getPlacesInPlaneMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PlacesInPlaneMapper.class);
	}

	protected OrdersMapper getOrdersMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(OrdersMapper.class);
	}

	protected PassengersMapper getPassengersMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PassengersMapper.class);
	}

	@Override
	public Departure register(Departure departure) throws DataNotFoundException, BadPlaceException, ApplicationSecurityException {
		try(SqlSession sqlSession = getSession()) {
			try{
				PlaneInfo planeInfo = getOrdersMapper(sqlSession).getPlaneInfoByOrderId(departure.getOrderId());
				if(planeInfo == null) {
					BadOrderIdError badOrderIdError = new BadOrderIdError();
					logger.info(badOrderIdError.toString());
					throw new DataNotFoundException(badOrderIdError);
				}
				String typeOfPlace = getPassengersMapper(sqlSession).getTypeOfPlace(departure.getPlace().getId());
				if(!planeInfo.checkPlace(departure.getPlace().getPlaceNumber(), departure.getPlace().getRowNumber(),
						typeOfPlace)) {
					PlaceNotExistError placeNotExistError = new PlaceNotExistError();
					logger.info(placeNotExistError.toString());
					throw new BadPlaceException(placeNotExistError);
				}
				if(getPlacesInPlaneMapper(sqlSession).checkFirstLastName(departure) == null) {
					FirstLastNameMismatchError firstLastNameMismatchError = new FirstLastNameMismatchError();
					logger.info(firstLastNameMismatchError.toString());
					throw new ApplicationSecurityException(firstLastNameMismatchError);
				}

				getPlacesInPlaneMapper(sqlSession).insert(departure);
			} catch (PersistenceException e) {
				sqlSession.rollback();
				BadPlaceError badPlaceError = new BadPlaceError();
				logger.info(badPlaceError.toString());
				throw new DataNotFoundException(badPlaceError);
			} catch(RuntimeException e) {
				logger.error(e.toString());
				sqlSession.rollback();
			}
			sqlSession.commit();
		}
		return departure;
	}

	@Override
	public Set<PlaceInPlane> getPlacesByOrder(int orderId) throws DataNotFoundException {
		try(SqlSession sqlSession = getSession()) {
			Set<PlaceInPlane> placesInPlane = getPlacesInPlaneMapper(sqlSession).getPlacesByOrderId(orderId);
			if(placesInPlane == null) {
				BadOrderIdError badOrderIdError = new BadOrderIdError();
				logger.info(badOrderIdError.toString());
				throw new DataNotFoundException(badOrderIdError);
			}
			return placesInPlane;
		}
	}
}
