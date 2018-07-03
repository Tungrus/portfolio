package net.thumbtack.airline.model;

import java.sql.Date;
import java.util.List;

public class Order {
	private int userId;
	private int orderId;
	private int flightDateId;
	private OrderParams orderParams;
	private FlightInfo flightInfo;
	private List<Passenger> passengers;
	private Date flightDate;


	public Order() {
	}

	public Order(int orderId, int flightDateId, OrderParams orderParams, FlightInfo flightInfo, List<Passenger> passengers,
				 Date flightDate) {
		this.orderId = orderId;
		this.orderParams = orderParams;
		this.flightInfo = flightInfo;
		this.passengers = passengers;
		this.flightDateId = flightDateId;
		this.flightDate = flightDate;
		orderParams.setTotalPrice(countTotalPrice());
	}

	public Order(OrderParams orderParams, FlightInfo flightInfo, List<Passenger> passengers, Date flightDate, int userId) {
		this.orderParams = orderParams;
		this.flightInfo = flightInfo;
		this.passengers = passengers;
		this.flightDate = flightDate;
		this.orderId = 0;
		this.flightDateId = 0;
		this.userId = userId;
		orderParams.setTotalPrice(countTotalPrice());
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderParams getOrderParams() {
		return orderParams;
	}

	public void setOrderParams(OrderParams orderParams) {
		this.orderParams = orderParams;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public int getFlightDateId() {
		return flightDateId;
	}

	public void setFlightDateId(int flightDateId) {
		this.flightDateId = flightDateId;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBusinessPlaceCount() {
		return getCountTypeOfPlace("BUSINESS");//TODO add ENUM
	}

	public int getEconomyPlacesCount() {
		return getCountTypeOfPlace("ECONOMY");//TODO add ENUM
	}

	private int getCountTypeOfPlace(String placeType) {
		int i = 0;
		for(Passenger passenger : passengers) {
			if(passenger.getTypeOfPlace().equals(placeType)) {
				i++;
			}
		}
		return i;
	}

	private int countTotalPrice() {
		int totalPrice = 0;
		for(Passenger passenger : getPassengers()) {
			if(passenger.getTypeOfPlace().equals("BUSINESS")) {
				totalPrice += getFlightInfo().getPriceBusiness();
			} else {
				totalPrice += getFlightInfo().getPriceEconomy();
			}
		}
		return totalPrice;
	}

}
