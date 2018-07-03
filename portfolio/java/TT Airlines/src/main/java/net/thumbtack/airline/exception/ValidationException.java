package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class ValidationException extends BaseApplicationException {

	public ValidationException(ErrorCollection errorCollection) {
		super(errorCollection);
	}


	public ValidationException(BaseError baseError) {
		super(baseError);
	}
}
