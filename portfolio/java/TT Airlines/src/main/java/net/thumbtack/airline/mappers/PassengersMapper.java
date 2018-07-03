package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Passenger;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PassengersMapper {
	@Insert("INSERT INTO passengers(ticketId, orderId, nationalityId, firstName, lastName, passportNumber, typeOfPlace," +
			" price) " +
			"VALUES (#{passenger.ticketId}, #{orderId}, #{passenger.nationality.id}, #{passenger.firstName}, " +
			"#{passenger.lastName}, #{passenger.passportNumber}, #{passenger.typeOfPlace}, #{passenger.price})")
	@Options(useGeneratedKeys = true, keyProperty = "passenger.ticketId")
	public void insert(@Param("passenger") Passenger passenger, @Param("orderId") int orderId);

	@Select("SELECT ticketId, nationalityId, firstName, lastName, passportNumber, typeOfPlace, price " +
			"FROM passengers " +
			"WHERE passengers.orderId=#{orderId}")
	@Results({
		@Result(column = "ticketId", property = "ticketId"),
		@Result(column = "firstName", property = "firstName"),
		@Result(column = "lastName", property = "lastName"),
		@Result(column = "passportNumber", property = "passportNumber"),
		@Result(column = "typeOfPlace", property = "typeOfPlace"),
		@Result(column = "price", property = "price"),
		@Result(column = "nationalityId", property = "nationality",
				one = @One(select = "net.thumbtack.airline.mappers.CountriesMapper.getNationality"))
	})
	public List<Passenger> getPassengersByOrderId(@Param("orderId") int orderId);

	@Select("SELECT typeOfPlace FROM passengers WHERE #{ticketId}=passengers.ticketId")
	public String getTypeOfPlace(@Param("ticketId") int ticketId);

}
