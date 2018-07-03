package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FlightInfoMapper {
	@Insert("INSERT INTO flights( flightId, approved, flightName, fromTown, toTown, start, duration, " +
			"planeId, priceBusiness, priceEconomy) " +
			"VALUES (#{flight.flightInfo.id}, #{flight.flightInfo.approved}," +
			"#{flight.flightInfo.flightName}, #{flight.flightInfo.fromTown}, #{flight.flightInfo.toTown}," +
			"#{flight.flightInfo.start}, #{flight.flightInfo.duration}, " +
			"#{flight.planeInfo.id}, #{flight.flightInfo.priceBusiness}, #{flight.flightInfo.priceEconomy})")
	@Options(useGeneratedKeys = true, keyProperty = "flight.flightInfo.id")
	public void addFlight(@Param("flight") Flight flight);

	@Update("UPDATE flights SET ( flightId=#{flight.flightInfo.id}, approved=#{flight.flightInfo.approved}, " +
			"fromTown=#{flight.flightInfo.fromTown}, toTown=#{flight.flightInfo.toTown}, " +
			"start=#{flight.flightInfo.start}, duration=#{flight.flightInfo.duration}, " +
			"planeId=#{flight.planeInfo.id}, priceBusiness=#{flight.flightInfo.priceBusiness}, " +
			"priceEconomy=#{flight.flightInfo.priceEconomy}, scheduleId=#{flight.dates.scheduleId} ) " +
			"WHERE ( flightName=#{flight.flightInfo.flightName} )")
	public void updateFlight(@Param("flight") Flight flight);

	@Select("SELECT flightId, approved, flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy " +
			"FROM (flights) " +
			"WHERE ( flights.flightId=#{flightId} )")
	public FlightInfo getFlightById(@Param("flightId") int flightId);

	@Select("SELECT planeId FROM flights WHERE flightId=#{flightId}")
	public int getPlaneId(@Param("flightId") int flightId);

}
