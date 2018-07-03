package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class BadConfigException extends BaseApplicationException {
	public BadConfigException(ErrorCollection errorCollection) {
		super(errorCollection);
	}

	public BadConfigException(BaseError baseError) {
		super(baseError);
	}
}
