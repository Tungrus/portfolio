package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClientMapper {

	@Update("UPDATE ( users, name_params, clients ) SET password=#{client.password}," +
			"lastName=#{client.clientParams.lastName}, firstName=#{client.clientParams.firstName}," +
			"patronymic=#{client.clientParams.patronymic}, email=#{client.clientParams.email}," +
			"phoneNumber=#{client.clientParams.phoneNumber} WHERE users.id=#{client.id}")
	public int updateClient(@Param("client") Client client);

	@Select("SELECT (clients.id, clients.emails, clients.phoneNumber)  FROM (users, clients, name_params")
	public List<Client> getClients();

	@Select("SELECT id, email, phoneNumber, firstName, lastName, patronymic, login, password, userType " +
			"FROM users, clients, name_params " +
			"WHERE ( users.id=#{userId} AND clients.userId=#{userId} AND name_params.userId=#{userId} )")
	public Client getClientById(@Param("userId") int userId);
}
