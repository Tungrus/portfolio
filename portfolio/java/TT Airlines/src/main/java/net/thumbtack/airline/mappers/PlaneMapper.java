package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.PlaneInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlaneMapper {
	@Select("SELECT * FROM planes")
	public List<PlaneInfo> getInfoPlanes();

	@Select("SELECT planeId FROM planes WHERE ( planeName=#{planeName} )")
	public Integer getPlaneIdByName(@Param("planeName") String planeName);

	@Select("SELECT planeId FROM planes WHERE ( planeName=#{flight.planeInfo.planeNameType} )")
	public Integer getPlaneIdByNameFromFlight(@Param("flight") Flight flight);

	@Select("SELECT * FROM planes WHERE ( planeName=#{flight.planeInfo.planeNameType} )")
	public PlaneInfo getPlaneInfoByFlight(@Param("flight") Flight flight);

	@Select("SELECT * FROM planes WHERE ( planeName=#{planeName} )")
	public PlaneInfo getPlaneInfo(@Param("planeName") String planeName);

	@Select("SELECT * FROM planes WHERE ( planeId=#{planeId} )")
	public PlaneInfo getPlaneInfoByPlaneId(@Param("planeId") int planeId);

	@Delete("DELETE FROM planes")
	public void dropPlanesTable();
}

