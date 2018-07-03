package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class WrongClientSessionError extends BaseError {

	public WrongClientSessionError(String field) {
		super(field.toUpperCase() + "IS_NOT_CLIENT_SESSION", field, "user is not client");
	}
}
