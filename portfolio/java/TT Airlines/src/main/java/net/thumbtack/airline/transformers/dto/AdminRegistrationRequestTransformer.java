package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.requests.AdminRegistrationRequest;
import net.thumbtack.airline.model.Admin;


public class AdminRegistrationRequestTransformer {
	public static AdminRegistrationRequest create(Admin admin) {
		return new AdminRegistrationRequest(AdminDtoTransformer.create(admin));
	}

	/*public static AdminRegistrationRequest create(Admin admin) {
		AdminDto adminDto = ClientDtoTransformer.create(admin);
		return new AdminRegistrationRequest(adminDto.getAdminParamsDto().getFirstName(),
				adminDto.getAdminParamsDto().getLastName(),
				adminDto.getAdminParamsDto().getPatronymic(),
				adminDto.getLogin(),
				adminDto.getPassword(),
				adminDto.getAdminParamsDto().getPositiont());
	}*/
}
