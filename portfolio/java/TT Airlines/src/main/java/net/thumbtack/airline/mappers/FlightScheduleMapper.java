package net.thumbtack.airline.mappers;

import net.thumbtack.airline.utils.dates.Dates;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FlightScheduleMapper {
	@Insert("INSERT INTO flightSchedule(period, toDate, fromDate, flightId) VALUES ( #{dates.period}, " +
			"#{dates.toDate}, #{dates.fromDate}, #{flightId})")
	public void insert(@Param("dates") Dates dates, @Param("flightId") int flightId);

	@Select("SELECT period, toDate, fromDate FROM flightSchedule WHERE flightId=#{flightId}")
	public Dates getDatesById(@Param("flightId") int flightId);

	@Delete("DELETE FROM flightSchedule WHERE flightId=#{flightId}")
	public void deleteScheduleById(@Param("flightId") int scheduleId);
}
