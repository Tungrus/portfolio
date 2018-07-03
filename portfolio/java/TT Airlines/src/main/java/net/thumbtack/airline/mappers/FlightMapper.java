package net.thumbtack.airline.mappers;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface FlightMapper {
	@Update("UPDATE flights SET approved=true WHERE ( flightId=#{flightId} )")
	public void approveFlight(@Param("flightId") int flightId);

	@Delete("DELETE FROM flights")
	public void dropFlightsTable();

	@Select("SELECT flights.flightId FROM ( flights, planes, flightDates ) " +
			"WHERE (((#{toTown} IS null ) OR (flights.toTown=#{toTown})) AND " +
			"((#{fromTown} IS null ) OR (flights.fromTown=#{fromTown})) AND " +
			"((#{flightName} IS null ) OR (flights.flightName=#{flightName})) AND " +
			"((#{planeName} IS null ) OR (planes.planeName=#{planeName} AND flights.planeId=planes.planeId)) AND " +
			"(SELECT flightId FROM flightDates " +
			"WHERE flights.flightId = flightDates.flightId " +
			"AND ((#{fromDate} IS null) OR (datediff(#{fromDate}, flightDate) <= 0)) " +
			"AND ((#{toDate} IS null) OR (datediff(#{toDate}, flightDate) >= 0)) LIMIT 1)=flights.flightId )")
	public List<Integer> getFlightsIdByQuery(@Param("toTown") String toTown, @Param("fromTown") String fromTown,
											 @Param("flightName") String flightName, @Param("fromDate") Date fromDate,
											 @Param("toDate") Date toDate, @Param("planeName") String planeName);

	@Delete("DELETE FROM flights WHERE flightId=#{flightId}")
	public void deleteFlight(@Param("flightId") int flightId);
}

