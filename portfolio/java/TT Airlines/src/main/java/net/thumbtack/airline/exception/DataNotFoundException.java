package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class DataNotFoundException extends BaseApplicationException {
	public DataNotFoundException(ErrorCollection errorCollection) {
		super(errorCollection);
	}

	public DataNotFoundException(BaseError baseError) {
		super(baseError);
	}
}
