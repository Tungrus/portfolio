package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.errors.types.runtime.BadPlaceInPlaneError;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.mappers.PlacesInPlaneMapper;
import net.thumbtack.airline.model.Departure;
import net.thumbtack.airline.model.PlaceInPlane;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PlacesInPlaneDaoImpl extends BaseDaoImpl {

	private final Logger logger = LoggerFactory.getLogger(PlacesInPlaneDaoImpl.class);

	protected PlacesInPlaneMapper getPlacesInPlaneMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PlacesInPlaneMapper.class);
	}

	public Departure insert(Departure departure) throws BaseApplicationException {
		try(SqlSession sqlSession = getSession()) {
			try {
				getPlacesInPlaneMapper(sqlSession).insert(departure);
			} catch (PersistenceException ex) {
				sqlSession.rollback();
				BadPlaceInPlaneError badPlaceInPlaneError = new BadPlaceInPlaneError("place");
				logger.info(badPlaceInPlaneError.toString());
				throw new BaseApplicationException(badPlaceInPlaneError);
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
			return departure;
		}
	}

	public Set<PlaceInPlane> getPlacesInPlace(int orderId) {
		try(SqlSession sqlSession = getSession()) {
			return getPlacesInPlaneMapper(sqlSession).getPlacesByOrderId(orderId);
		}
	}
}
