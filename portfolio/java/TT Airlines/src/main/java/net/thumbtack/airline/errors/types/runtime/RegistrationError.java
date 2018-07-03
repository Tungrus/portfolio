package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class RegistrationError extends BaseError {

	public RegistrationError(String field) {
		super(field.toUpperCase() + "IS_DUPLICATED", field, "this user already exists");
	}
}
