package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class NotEmptyFieldConstrain extends BaseValidator {
	@Value("${validator.notNull.regexp}")
	private String notNullRegex;

	public void check(String object, String fieldName, ErrorCollection errorCollection) {
		Pattern notNullPattern = Pattern.compile(notNullRegex);
		fieldCheck(object, fieldName, notNullPattern, errorCollection);
	}
}
