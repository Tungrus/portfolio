package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class LoginException extends BaseApplicationException {
	public LoginException(ErrorCollection errorCollection) {
		super(errorCollection);
	}

	public LoginException(BaseError baseError) {
		super(baseError);
	}
}
