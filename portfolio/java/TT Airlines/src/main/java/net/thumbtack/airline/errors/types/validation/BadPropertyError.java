package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class BadPropertyError extends BaseError {


	public BadPropertyError(String field) {
		super(field.toUpperCase() + "_IS_NOT_CONTAINS_RIGHT_PROPERTIES", field,
				field + " must contain acceptable properties");
	}
}
