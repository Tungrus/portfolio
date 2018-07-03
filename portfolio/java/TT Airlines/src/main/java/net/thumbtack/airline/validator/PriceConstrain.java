package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.PriceError;
import org.springframework.stereotype.Component;

@Component
public class PriceConstrain extends BaseConstrain {
	public static void check(int price, String fieldName, ErrorCollection errorCollection) {
		if(price <= 0) {
			errorCollection.addProperty(new PriceError(fieldName));
		}
	}
}
