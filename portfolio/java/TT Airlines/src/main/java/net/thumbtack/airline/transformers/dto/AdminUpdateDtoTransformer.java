package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.AdminUpdateDto;
import net.thumbtack.airline.model.AdminParams;

public class AdminUpdateDtoTransformer {
	public static AdminUpdateDto create(AdminParams adminParams, String newPassword) {
		return new AdminUpdateDto(AdminDtoTransformer.create(adminParams), newPassword);
	}
}
