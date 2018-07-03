package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.stereotype.Component;

@Component
public class IdConstrain extends BaseConstrain{
	public static void check(int id, String fieldName, ErrorCollection errorCollection) {
		if(id <= 0) {

		}
	}
}
