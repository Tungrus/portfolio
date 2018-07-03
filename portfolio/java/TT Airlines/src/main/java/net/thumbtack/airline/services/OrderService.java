package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.OrdersDao;
import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.dto.OrderQueryDto;
import net.thumbtack.airline.dto.response.AddOrderResponse;
import net.thumbtack.airline.dto.response.OrderQueryResponse;
import net.thumbtack.airline.errors.types.runtime.FlightIsNotApprovedError;
import net.thumbtack.airline.exception.AddOrderException;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.transformers.dto.AddOrderResponseTransformer;
import net.thumbtack.airline.transformers.dto.OrderQueryResponseTransformer;
import net.thumbtack.airline.transformers.model.ModelOrderQueryTransformer;
import net.thumbtack.airline.transformers.model.ModelOrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService {

	@Autowired
	private FlightDao flightDao;
	@Autowired
	private OrdersDao ordersDao;

	public AddOrderResponse addOrder(int userId, OrderDto orderDto) throws ApplicationSecurityException, DataNotFoundException, AddOrderException {
		FlightInfo flightInfo = flightDao.getFlightInfo(orderDto.getOrderParamsDto().getFlightId());
		if(!flightInfo.isApproved()) throw new ApplicationSecurityException(new FlightIsNotApprovedError());
		return AddOrderResponseTransformer.create(ordersDao.addOrder(
				ModelOrderTransformer.create(orderDto, flightInfo, userId)));
	}

	public OrderQueryResponse getOrderQueryResponse(OrderQueryDto orderQueryDto, UserType userType) throws Exception {
		Set<Order> orders = ordersDao.getOrdersQuery(ModelOrderQueryTransformer.create(orderQueryDto, userType));
		return OrderQueryResponseTransformer.create(orders);
	}
}
