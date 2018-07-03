package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.RegexPropertyError;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegexpPropertyConstrain extends BaseConstrain{
	public static boolean check(String object, Pattern pattern, String field,
								ErrorCollection errorCollection) throws NullPointerException {
		if(pattern.matcher(object).matches()) {
			return true;
		}
		else {
			errorCollection.addProperty(new RegexPropertyError(field));
			return false;
		}
	}
}
