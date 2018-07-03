package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.ClientUpdateDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClientUpdateDtoValidator extends BaseValidator {
	@Autowired
	private ClientParamsDtoValidator clientParamsDtoValidator;
	@Value("${validator.password.regexp}")
	private String passwordRegexp;
	private String passwordFieldName = "newPassword";
	private Pattern passwordPattern;

	public void validate(ClientUpdateDto clientUpdateDto, ErrorCollection errorCollection) {
		passwordPattern = Pattern.compile(passwordRegexp);
		fieldCheck(clientUpdateDto.getNewPassword(), passwordFieldName, passwordPattern, errorCollection);
		clientParamsDtoValidator.validate(clientUpdateDto.getClientParamsDto(), errorCollection);
	}
}
