package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class BaseApplicationException extends Exception{
	private ErrorCollection errorCollection;

	public BaseApplicationException(ErrorCollection errorCollection) {
		this.errorCollection = errorCollection;
	}

	public BaseApplicationException(BaseError baseError) {
		errorCollection = new ErrorCollection();
		errorCollection.addProperty(baseError);
	}

	public ErrorCollection getErrorCollection() {
		return errorCollection;
	}
}
