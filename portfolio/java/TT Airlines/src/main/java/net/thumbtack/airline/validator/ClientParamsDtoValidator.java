package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.ClientParamsDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClientParamsDtoValidator extends BaseValidator {
	@Autowired
	private NameParamsDtoValidator nameParamsDtoValidator;

	private String phoneNumberFieldName = "phoneNumber";

	private String emailFieldName = "email";

	@Value("${validator.phoneNumber.regexp}")
	private String phoneNumberRegexp;
	@Value("${validator.email.regexp}")
	private String emailRegexp;

	private Pattern phoneNumberPattern;
	private Pattern emailPattern;

	public void validate(ClientParamsDto clientParams, ErrorCollection errorCollection) {
		emailPattern = Pattern.compile(emailRegexp);
		phoneNumberPattern = Pattern.compile(phoneNumberRegexp);
		fieldCheck(clientParams.getPhoneNumber(), phoneNumberFieldName, phoneNumberPattern, errorCollection);
		fieldCheck(clientParams.getEmail(), emailFieldName, emailPattern, errorCollection);
		nameParamsDtoValidator.validate(clientParams, errorCollection);
	}
}
