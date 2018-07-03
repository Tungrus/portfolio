package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Order;

public interface PassengerDao {
	public void isContainEnoughPlaces(int orderId, Order order) throws Exception;
}
