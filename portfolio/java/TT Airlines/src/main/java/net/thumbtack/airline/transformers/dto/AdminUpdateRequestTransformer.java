package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.AdminUpdateRequest;
import net.thumbtack.airline.model.AdminParams;

public class AdminUpdateRequestTransformer {
	public static AdminUpdateRequest create(AdminParams adminParams, String newPassword) {
		return new AdminUpdateRequest(AdminUpdateDtoTransformer.create(adminParams, newPassword));
	}
}
