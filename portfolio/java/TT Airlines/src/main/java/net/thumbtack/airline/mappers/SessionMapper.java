package net.thumbtack.airline.mappers;

import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;


@Mapper
public interface SessionMapper {
	@Insert("INSERT INTO users_sessions (userId, sessionString, openSessionTime) VALUES ( #{userId}," +
			"#{sessionString}, #{openSessionTime} )")
	public void loginUser(@Param("userId") int userId, @Param("sessionString") String sessionString,
						  @Param("openSessionTime") Timestamp openSessionTime);

	@Select("SELECT (sessionString) FROM users_sessions WHERE ( userId=#{userId} )")
	public String getSessionToken(@Param("userId") int userId);

	@Select("SELECT userId FROM users_sessions WHERE ( sessionString=#{sessionString} ) ")
	public Integer getSessionID(@Param("sessionString") String sessionString);

	@Delete("DELETE FROM users_sessions WHERE ( sessionString=#{sessionString} ) ")
	public void logout(@Param("sessionString") String sessionString);


}
