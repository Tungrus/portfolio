package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.AddOrderException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.PlaneInfo;
import net.thumbtack.airline.querties.OrderQuery;

import java.util.Set;

public interface OrdersDao {
	public Order addOrder(Order order) throws DataNotFoundException, AddOrderException;

	public Set<Order> getOrdersQuery(OrderQuery orderQuery) throws DataNotFoundException;

	public PlaneInfo getPlaneInfoBy(int orderId) throws DataNotFoundException;

	public int getFlightDateId(int orderId);

	public Set<Integer> getOrderIdsByFlightDateId(int flightDateId);

	public Set<Integer> getAllOrderIdOnDeparture(int orderId);


}
