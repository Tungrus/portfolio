package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.OrderParams;
import net.thumbtack.airline.model.PlaneInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.Set;

@Mapper
public interface OrdersMapper {
	@Insert("INSERT INTO orders(orderId, flightDateId, flightId, userId, totalPrice) VALUES (#{order.orderId}," +
			" #{order.flightDateId}, #{order.flightInfo.id}, #{order.userId}, #{order.orderParams.totalPrice})")
	@Options(useGeneratedKeys = true, keyProperty = "order.orderId")
	public void insert(@Param("order") Order order);

	@Select("SELECT orderId FROM (orders , flights, flightDates, planes) " +
			"WHERE ((#{fromTown} IS NULL) OR (#{fromTown})) AND " +
			"((#{toTown} IS NULL) OR (#{toTown})) AND " +
			"((#{flightName} IS NULL) OR (#{flightName})) AND " +
			"((#{planeName} IS NULL) OR " +
			"(#{planeName} = planes.planeName AND orders.flightId = flights.flightId AND flights.planeId)) AND " +
			"((#{clientId} IS NULL) OR (#{clientId} = orders.userId) ) AND " +
			"((#{fromDate} IS NULL) OR " +
			"(orders.flightDateId=flightDates.flightDateId AND datediff( #{fromDate}, flightDates.flightDate ) <= 0)) AND " +
			"((#{toDate} IS NULL) OR " +
			"(orders.flightDateId=flightDates.flightDateId AND datediff( #{toDate}, flightDates.flightDate ) >= 0))")
	/*@Results({
			@Result(column = "orderId",
					many = @Many(select = "net.thumbtack.airline.mappers.OrdersMapper.getOrder"))
	})*/
	public Set<Integer> getOrdersIdByQuery(@Param("fromTown") String fromTown, @Param("toTown") String toTown,
										 @Param("flightName") String flightName, @Param("planeName")String planeName,
										 @Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
										 @Param("clientId") int clientId);

	@Select("SELECT orders.orderId, orders.flightDateId, orders.flightId, flightDates.flightDate," +
			" orders.userId " +
			"FROM orders, flightDates " +
			"WHERE orders.orderId=#{orderId} AND orders.flightDateId=flightDates.flightDateId ")
	@Results({
			@Result(id = true, column = "orderId", property = "orderId"),
			@Result(column = "userId", property = "userId"),
			@Result(column = "flightDateId", property = "flightDateId"),
			@Result(column = "flightDate", property = "flightDate"),
			@Result(column = "orderId", property = "orderParams",
					one = @One(select = "net.thumbtack.airline.mappers.OrdersMapper.getOrderParamByOrderId")),
			@Result(column = "orderId", property = "flightInfo",
					one = @One(select = "net.thumbtack.airline.mappers.OrdersMapper.getFlightInfoByOrderId")),
			@Result(column = "orderId", property = "passengers",
					many = @Many(select = "net.thumbtack.airline.mappers.PassengersMapper.getPassengersByOrderId"))
	})
	public Order getOrder(@Param("orderId") int orderId);

	@Select("SELECT planes.planeId, planes.planeName, planes.economyRows, planes.placesInEconomyRow, planes.bisnessRows, " +
			"planes.placesInBisnessRow " +
			"FROM planes, orders, flights WHERE orders.orderId=#{orderId} AND " +
			"orders.flightId=flights.flightId AND flights.planeId=planes.planeId")
	public PlaneInfo getPlaneInfoByOrderId(@Param("orderId") int orderId);

	@Select("SELECT flights.flightId, approved, flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy " +
			"FROM flights, orders " +
			"WHERE orders.orderId=#{orderId} AND orders.flightId=flights.flightId ")
	public FlightInfo getFlightInfoByOrderId(@Param("orderId") int orderId);

	@Select("SELECT totalPrice FROM orders WHERE orderId=#{orderId}")
	public OrderParams getOrderParamByOrderId(@Param("orderId") int orderId);

	@Select("SELECT flightDateId FROM orders WHERE orders.orderId=#{orderId}")
	public int getFlightDateId(@Param("orderId") int orderId);

	@Select("SELECT orderId FROM orders WHERE flightDateId=#{flightDateId}")
	public Set<Integer> getOrderIds(@Param("flightDateId") int flightDateId);
}
