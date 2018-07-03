package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class BadIdError extends BaseError {

	public BadIdError(String field) {
		super(field + "_INVALID", field, field + " must meet the requirements");
	}
}
