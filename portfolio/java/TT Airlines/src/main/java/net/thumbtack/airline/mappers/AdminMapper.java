package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.AdminParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
	@Update("UPDATE users, name_params, admins SET users.password=#{newPassword}, " +
			"name_params.firstName=#{adminParams.firstName}, name_params.lastName=#{adminParams.lastName}, " +
			"name_params.patronymic=#{adminParams.patronymic}, admins.position=#{adminParams.position} " +
			"WHERE (users.id=#{userId} AND name_params.userId=#{userId} AND admins.userId=#{userId} )")
	public void updateAdmin(@Param("adminParams") AdminParams adminParams, @Param("userId") int userId,
							@Param("newPassword") String newPassword);
}
