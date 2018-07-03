package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserDtoValidator extends BaseValidator {
	@Value("${validator.login.regexp}")
	private String loginRegexp;
	@Value("${validator.password.regexp}")
	private String passwordRegexp;

	private String loginFieldName = "login";
	private String passwordFieldName = "password";

	public void validate(UserDto userDto, ErrorCollection errorCollection) {
		Pattern passwordPattern = Pattern.compile(passwordRegexp);
		Pattern loginPattern = Pattern.compile(loginRegexp);
		fieldCheck(userDto.getLogin(), loginFieldName, loginPattern, errorCollection);
		fieldCheck(userDto.getPassword(), passwordFieldName, passwordPattern, errorCollection);
	}
}
