package net.thumbtack.airline.mappers;

import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface DatesMapper {
	@Insert("INSERT INTO flightDates (flightDate, flightId, flightDateId) " +
			"VALUES (#{flightDate}, #{flightId}, #{flightDateId} )")
	//@Options(useGeneratedKeys = true, keyProperty = "flightDateId.value")
	public void insertDates(@Param("flightDate") Date date, @Param("flightId") int flightId,
							@Param("flightDateId") Integer flightDateId);

	@Select("SELECT flightDate FROM flightDates WHERE flightId=#{flightId}")
	public List<Date> getFlightDates(@Param("flightId") int flightId);

	@Select("SELECT flightDateId FROM flightDates WHERE flightId=#{flightId} AND flightDate=#{flightDate}")
	public Integer getIdByDateAndFlightId(@Param("flightId") int flightId, @Param("flightDate") Date date);

	@Delete("DELETE FROM flightDates WHERE ( flightId = #{flightId} )")
	public void deleteDatesList(@Param("flightId") int flightId);

	@Delete("DELETE FROM flightDates")
	public void dropTable();


}
