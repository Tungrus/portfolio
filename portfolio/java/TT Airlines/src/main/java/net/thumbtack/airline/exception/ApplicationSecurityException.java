package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class ApplicationSecurityException extends BaseApplicationException{

	public ApplicationSecurityException(ErrorCollection errorCollection) {
		super(errorCollection);
	}

	public ApplicationSecurityException(BaseError baseError) {
		super(baseError);

	}
}
