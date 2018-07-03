package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.OrdersDao;
import net.thumbtack.airline.errors.types.runtime.BadDateError;
import net.thumbtack.airline.errors.types.runtime.BadNationalityError;
import net.thumbtack.airline.errors.types.runtime.BadOrderIdError;
import net.thumbtack.airline.errors.types.runtime.TooManyPlacesError;
import net.thumbtack.airline.exception.AddOrderException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.mappers.*;
import net.thumbtack.airline.model.Nationality;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.Passenger;
import net.thumbtack.airline.model.PlaneInfo;
import net.thumbtack.airline.querties.BaseAirlineQueryParams;
import net.thumbtack.airline.querties.OrderQuery;
import net.thumbtack.airline.querties.OrderQueryParams;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrdersDaoImpl extends BaseDaoImpl implements OrdersDao {

	private final Logger logger = LoggerFactory.getLogger(OrdersDaoImpl.class);

	protected OrdersMapper getOrdersMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(OrdersMapper.class);
	}

	protected CountriesMapper getCountriesMapper(SqlSession sqlSession)  {
		return sqlSession.getMapper(CountriesMapper.class);
	}

	protected PassengersMapper getPassengersMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PassengersMapper.class);
	}

	protected DatesMapper getDatesMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(DatesMapper.class);
	}

	protected PlacesMapper getPlacesMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(PlacesMapper.class);
	}

	private void getPassengers(Order order, SqlSession sqlSession) throws DataNotFoundException {
		int totalPrice = 0;
		List<Passenger> passengers = new ArrayList<>();
		for(Passenger passenger : order.getPassengers()) {
			Nationality nationality = getCountriesMapper(sqlSession).
					getNationalityInfoByIso(passenger.getNationality().getIso3166());
			if(nationality == null) {
				BadNationalityError badNationalityError = new BadNationalityError();
				logger.info(badNationalityError.toString());
				throw new DataNotFoundException(badNationalityError);
			}
			passenger.setNationality(nationality);
			int ticketPrice = 0;
			if(passenger.getTypeOfPlace().equals("BUSINESS")) {
				ticketPrice = order.getFlightInfo().getPriceBusiness();
			} else {
				ticketPrice = order.getFlightInfo().getPriceEconomy();
			}
			totalPrice+= ticketPrice;
			passenger.setPrice(ticketPrice);
			passengers.add(passenger);
			getPassengersMapper(sqlSession).insert(passenger, order.getOrderId());
		}
		order.setPassengers(passengers);
		order.getOrderParams().setTotalPrice(totalPrice);
	}

	@Override
	public Order addOrder(Order order) throws DataNotFoundException, AddOrderException {
		try(SqlSession sqlSession = getSession()) {
			try {
				Integer dateId = getDatesMapper(sqlSession).getIdByDateAndFlightId(order.getFlightInfo().getId(),
						order.getFlightDate());
				if(dateId == null) {
					BadDateError badDateError = new BadDateError();
					logger.info(badDateError.toString());
					throw new DataNotFoundException(badDateError);
				}
				int countOfChangedStrings = getPlacesMapper(sqlSession).updatePlaces(order.getBusinessPlaceCount(), order.getEconomyPlacesCount(),
						dateId);
				if(countOfChangedStrings < 1) {
					TooManyPlacesError tooManyPlacesError = new TooManyPlacesError();
					logger.info(tooManyPlacesError.toString());
					throw new AddOrderException(tooManyPlacesError);
				}
				order.setFlightDateId(dateId);
				getOrdersMapper(sqlSession).insert(order);
				getPassengers(order, sqlSession);

			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
		}
		return order;
	}

	@Override
	public Set<Order> getOrdersQuery(OrderQuery orderQuery) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			Set<Integer> orderIds = getOrdersMapper(sqlSession).getOrdersIdByQuery(
					(String)orderQuery.getProperty(BaseAirlineQueryParams.getFromTown()),
					(String)orderQuery.getProperty(BaseAirlineQueryParams.getToTown()),
					(String)orderQuery.getProperty(BaseAirlineQueryParams.getFlightName()),
					(String)orderQuery.getProperty(BaseAirlineQueryParams.getPlaneName()),
					(Date)orderQuery.getProperty(BaseAirlineQueryParams.getFromTown()),
					(Date)orderQuery.getProperty(BaseAirlineQueryParams.getToDate()),
					(int)orderQuery.getProperty(OrderQueryParams.getClientId()));
			Set<Order> orders = new HashSet<>();
			for(Integer id : orderIds) {
				orders.add(getOrderByOrderId(id));
			}
			return orders;
		}
	}

	@Override
	public PlaneInfo getPlaneInfoBy(int orderId) throws DataNotFoundException {
		try(SqlSession sqlSession = getSession()) {
			PlaneInfo result = getOrdersMapper(sqlSession).getPlaneInfoByOrderId(orderId);
			if(result == null) {
				BadOrderIdError badOrderIdError = new BadOrderIdError();
				logger.info(badOrderIdError.toString());
				throw new DataNotFoundException(badOrderIdError);
			}
			return result;
		}
	}

	@Override
	public int getFlightDateId(int orderId) {
		try(SqlSession sqlSession = getSession()) {
			return getOrdersMapper(sqlSession).getFlightDateId(orderId);
		}
	}

	@Override
	public Set<Integer> getOrderIdsByFlightDateId(int flightDateId) {
		try(SqlSession sqlSession = getSession()) {
			return getOrdersMapper(sqlSession).getOrderIds(flightDateId);
		}
	}

	@Override
	public Set<Integer> getAllOrderIdOnDeparture(int orderId) {
		try(SqlSession sqlSession = getSession()) {
			return getOrderIdsByFlightDateId(getFlightDateId(orderId));
		}
	}

	public Order getOrderByOrderId(int orderId) throws DataNotFoundException {
		try(SqlSession sqlSession = getSession()) {
			Order result = getOrdersMapper(sqlSession).getOrder(orderId);
			if(result == null) {
				BadOrderIdError badOrderIdError = new BadOrderIdError();
				logger.info(badOrderIdError.toString());
				throw new DataNotFoundException(badOrderIdError);
			}
			return result;
		}
	}

}
