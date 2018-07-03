package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.AdminDto;
import net.thumbtack.airline.dto.AdminParamsDto;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.AdminParams;
import net.thumbtack.airline.model.User;

public class ModelClientTransformer {
	public static Admin create(AdminDto adminDto) {
		return new Admin(0, adminDto.getLogin(), adminDto.getPassword(), create(adminDto.getAdminParamsDto()));
	}

	public static AdminParams create(AdminParamsDto adminParamsDto) {
		return new AdminParams(adminParamsDto.getFirstName(), adminParamsDto.getLastName(),
				adminParamsDto.getPatronymic(), adminParamsDto.getPositiont());
	}

	public static Admin create(User user, AdminParams adminParams) {
		return new Admin(user.getId(), user.getLogin(), user.getPassword(), adminParams);
	}
}
