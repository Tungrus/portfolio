package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.AdminUpdateDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AdminUpdateDtoValidator extends BaseValidator {
	@Autowired
	private AdminParamsDtoValidator adminParamsDtoValidator;

	@Value("${validator.password.regexp}")
	private String passwordRegexp;
	private String passwordFieldName = "newPassword";
	private Pattern passwordPattern;

	public void validate(AdminUpdateDto adminUpdateDto, ErrorCollection errorCollection) {
		passwordPattern = Pattern.compile(passwordRegexp);
		fieldCheck(adminUpdateDto.getNewPassword(), passwordFieldName, passwordPattern, errorCollection);
		adminParamsDtoValidator.validate(adminUpdateDto.getAdminParamsDto(), errorCollection);
	}
}
