package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserParamsMapper {
	@Insert("INSERT INTO users (id, userType, login, password) VALUES ( #{user.id}, #{user.userType}, #{user.login}," +
			"#{user.password})")
	@Options(useGeneratedKeys = true, keyProperty = "user.id")
	public int insert(@Param("user") User user);

	@Select("SELECT * FROM users WHERE (userType=#{user.userType} AND " +
			"login=#{user.login} AND password=#{user.password} )")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "login", property = "login"),
			@Result(column = "password", property = "password"),
			@Result(column = "userType", property = "userType")
	})
	public User getUserByIdFromUser(@Param("user") User user);

	@Select("SELECT * FROM users WHERE ( login=#{login} AND password=#{password} )")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "login", property = "login"),
			@Result(column = "password", property = "password"),
			@Result(column = "userType", property = "userType")
	})
	public User initUser(@Param("login") String login, @Param("password") String password);

	@Select("SELECT * FROM users WHERE ( id=#{userId} )")
	public User getUserById(@Param("userId") int userID);

	@Select("SELECT id FROM users WHERE ( userType=#{userType} )")
	public List<Integer> getIdByUserType(@Param("userType") String userType);

	@Delete("DELETE FROM users")
	public void dropTable();

	@Delete("DELETE FROM users WHERE userType=#{userType}")
	public void deleteAll(@Param("userType") String userType);

}
