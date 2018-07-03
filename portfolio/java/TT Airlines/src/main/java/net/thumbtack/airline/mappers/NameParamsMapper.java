package net.thumbtack.airline.mappers;

import net.thumbtack.airline.model.NameParams;
import org.apache.ibatis.annotations.*;


@Mapper
public interface NameParamsMapper {

	@Insert("INSERT INTO name_params (userId, firstName, lastName, patronymic) " +
			"VALUES ( #{userId}, #{nameParams.firstName}, #{nameParams.lastName}, #{nameParams.patronymic} )")
	public void insert(@Param("nameParams") NameParams nameParams, @Param("userId") int userId);

	@Select("SELECT firstName, lastName, patronymic FROM name_params WHERE ( userId=#{userId} )")
	@Results({
			//@Result(id = false, column = "id", property = "id"),
			@Result(column = "firstName", property = "firstName"),
			@Result(column = "lastName", property = "lastName"),
			@Result(column = "patronymic", property = "patronymic")
	})
	public NameParams getNameParamsById(@Param("userId") int userId);
}
