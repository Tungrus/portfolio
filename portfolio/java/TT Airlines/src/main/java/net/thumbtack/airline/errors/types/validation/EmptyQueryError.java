package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class EmptyQueryError extends BaseError {

	public EmptyQueryError(String field) {
		super(field.toUpperCase() + "_IS_EMPTY", field, field + " cannot be empty");
	}
}
