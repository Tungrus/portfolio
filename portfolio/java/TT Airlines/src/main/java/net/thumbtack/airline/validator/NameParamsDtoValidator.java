package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.NameParamsDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class NameParamsDtoValidator extends BaseValidator {//TODO get Field Name By Reflection

	@Value("${validator.firstName.regexp}")
	private String firstNameRegex;
	private String firstNameFieldName = "firstName";

	@Value("${validator.lastName.regexp}")
	private String lastNameRegex;
	private String lastNameFieldName = "lastName";

	@Value("${validator.patronymic.regexp}")
	private String patronymicRegex;
	private String patronymicFieldName = "patronymic";

	public void validate(NameParamsDto nameParamsDto, ErrorCollection errorCollection) { //TODO refact to map
		validateFirstName(nameParamsDto.getFirstName(), errorCollection);
		validateLastName(nameParamsDto.getLastName(), errorCollection);
		validatePatronymic(nameParamsDto.getPatronymic(), errorCollection);
	}

	public void validateFirstName(String firstName, ErrorCollection errorCollection) {
		Pattern firstNamePattern = Pattern.compile(firstNameRegex);
		fieldCheck(firstName, firstNameFieldName, firstNamePattern, errorCollection);
	}

	public void validateLastName(String lastName, ErrorCollection errorCollection) {
		Pattern lastNamePattern = Pattern.compile(lastNameRegex);
		fieldCheck(lastName, lastNameFieldName, lastNamePattern, errorCollection);
	}

	private ErrorCollection validatePatronymic(String patronymic, ErrorCollection errorCollection) {
		Pattern patronymicPattern = Pattern.compile(patronymicRegex);
		if(patronymic != null) {
			RegexpPropertyConstrain.check(patronymic, patronymicPattern, patronymicFieldName, errorCollection);
		}
		return errorCollection;
	}

}
