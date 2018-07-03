package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaneNameValidator extends BaseValidator {
	@Autowired
	private NotEmptyFieldConstrain notEmptyFieldConstrain;

	public void validate(String planeName, ErrorCollection errorCollection) {
		notEmptyFieldConstrain.check(planeName, "planeName", errorCollection);
	}
}
