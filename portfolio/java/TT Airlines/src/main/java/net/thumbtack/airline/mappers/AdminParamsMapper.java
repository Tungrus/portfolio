package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.AdminParams;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AdminParamsMapper {
	@Insert("INSERT INTO admins(userId, position) VALUES ( #{userId}, #{admin.position} )")
	public void insert(@Param("admin") AdminParams admin, @Param("userId") int userId);

	@Select("SELECT position FROM admins WHERE (userId=#{userId})")
	public String getPositionById(@Param("userId") int id);

	@Select("SELECT firstName, lastName, patronymic, position FROM name_params, admins " +
			"WHERE (name_params.userId=#{userId} AND admins.userId=name_params.userId )")
	public AdminParams getAdminParamsById(@Param("userId") int userId);
}
