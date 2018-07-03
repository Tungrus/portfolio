package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.ClientParams;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ClientParamsMapper {
	@Insert("INSERT INTO clients(userId, email, phoneNumber) VALUES( #{userId}, #{clientParam.email}," +
			" #{clientParam.phoneNumber} )")
	public void insert(@Param("clientParam") ClientParams clientParams, @Param("userId") int userId);

	@Select("SELECT phoneNumber FROM clients WHERE (userId=#{userId})")
	public String getPhoneNumberById(@Param("userId") int id);

	@Select("SELECT email FROM clients WHERE (userId=#{userId})")
	public String getEmailById(@Param("userId") int id);

	@Select("SELECT id, email, phoneNumber, firstName, lastName, patronymic, login, password, userType " +
			"FROM ( clients, name_params, users ) " +
			"WHERE (users.id=name_params.userId AND clients.userId=users.id AND name_params.userId=clients.userId)")
	public List<Client> getClients();

}
/*@Results({
			@Result(id = true, column = "id", property = "userId"),
			@Result(column = "firstName", property = "firstName"),
			@Result(column = "lastName", property = "lastName"),
			@Result(column = "patronymic", property = "patronymic"),
			@Result(column = "email", property = "email"),
			@Result(column = "register", property = "register"),
			@Result(column = "password", property = "password"),
			@Result(column = "userType", property = "userType"),
			@Result(column = "phoneNumber", property = "phoneNumber")
	})*/