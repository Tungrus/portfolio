package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.AdminUpdateResponse;
import net.thumbtack.airline.model.AdminParams;
import net.thumbtack.airline.model.UserType;

public class AdminUpdateResponseTransformer {
	public static AdminUpdateResponse create(AdminParams adminParams) {
		return new AdminUpdateResponse(AdminDtoTransformer.create(adminParams), UserType.ADMIN.toString());
	}
}
