package net.thumbtack.airline.exception;

import net.thumbtack.airline.errors.types.BaseError;

public class AddOrderException extends BaseApplicationException {

	public AddOrderException(BaseError baseError) {
		super(baseError);
	}
}
