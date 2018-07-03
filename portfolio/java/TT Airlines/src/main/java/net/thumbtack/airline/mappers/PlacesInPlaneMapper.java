package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Departure;
import net.thumbtack.airline.model.PlaceInPlane;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface PlacesInPlaneMapper {

	@Insert("INSERT INTO places(ticketId, placeNumber, rowNumber, stringRepresentation) " +
			"VALUES (#{departure.place.id}, #{departure.place.placeNumber} , #{departure.place.rowNumber}, " +
			"#{departure.place.stringRepresentation}) ")
	public void insert(@Param("departure")Departure departure);

	@Select("SELECT places.ticketId, places.rowNumber, places.placeNumber, places.stringRepresentation " +
			"FROM places, passengers " +
			"WHERE passengers.orderId=#{orderId} AND passengers.ticketId=places.ticketId")
	public Set<PlaceInPlane> getPlacesByOrderId(@Param("orderId") int orderId);

	@Select("SELECT ticketId " +
			"FROM passengers " +
			"WHERE #{departure.firstName}=passengers.firstName AND #{departure.lastName}=passengers.lastName " +
			"AND #{departure.place.id}=passengers.ticketId")
	public Integer checkFirstLastName(@Param("departure") Departure departure);
}
