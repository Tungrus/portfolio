package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class NullPropertyError extends BaseError {
	public NullPropertyError(String field) {
		super(field.toUpperCase() + "_CANNOT_BE_NULL", field, field + " cannot be empty");
	}
}
