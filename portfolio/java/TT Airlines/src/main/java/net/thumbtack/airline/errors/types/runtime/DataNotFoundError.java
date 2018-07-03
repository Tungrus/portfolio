package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class DataNotFoundError extends BaseError {
	public DataNotFoundError(String field) {
		super(field.toUpperCase() + "IS_NOT_EXIST_IN_DATABASE", field,
				field + "is not exist in database");
	}
}
