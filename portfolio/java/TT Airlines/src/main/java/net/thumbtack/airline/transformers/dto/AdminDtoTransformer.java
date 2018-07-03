package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.AdminDto;
import net.thumbtack.airline.dto.AdminParamsDto;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.AdminParams;

public class AdminDtoTransformer {
	public static AdminDto create(Admin admin) {
		return new AdminDto(admin.getLogin(), admin.getPassword(), create(admin.getAdminParams()));
	}

	public static AdminParamsDto create(AdminParams params) {
		return new AdminParamsDto(params.getFirstName(),
				params.getLastName(), params.getPatronymic(), params.getPositiont());
	}
}
