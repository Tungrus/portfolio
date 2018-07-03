package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;

import java.util.regex.Pattern;

public class BaseValidator {
	protected static void fieldCheck(String object, String fieldName, Pattern pattern, ErrorCollection errorCollection) {
		if(NotNullConstrain.check(object, fieldName, errorCollection)) {
			RegexpPropertyConstrain.check(object, pattern, fieldName, errorCollection);
		}
	}
}
