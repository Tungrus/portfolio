package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.AdminDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminDtoValidator {
	@Autowired
	private AdminParamsDtoValidator adminParamsDtoValidator;

	@Autowired
	private UserDtoValidator userDtoValidator;


	public void validate(AdminDto adminDto, ErrorCollection errorCollection) {
		userDtoValidator.validate(adminDto, errorCollection);
		adminParamsDtoValidator.validate(adminDto.getAdminParamsDto(), errorCollection);
	}
}
