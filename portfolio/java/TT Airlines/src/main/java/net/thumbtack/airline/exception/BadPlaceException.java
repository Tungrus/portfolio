package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.types.BaseError;

public class BadPlaceException extends BaseApplicationException{
	public BadPlaceException(BaseError baseError) {
		super(baseError);
	}
}
