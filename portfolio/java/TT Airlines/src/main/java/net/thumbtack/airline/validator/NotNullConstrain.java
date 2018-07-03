package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.NullPropertyError;
import org.springframework.stereotype.Component;

@Component
public class NotNullConstrain extends BaseConstrain{
	public static boolean check(Object object, String fieldName, ErrorCollection errorCollection) {
		if(object == null) {
			errorCollection.addProperty(new NullPropertyError(fieldName));
			return false;
		}
		else {
			return true;
		}
	}
}
