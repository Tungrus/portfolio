package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class WrongAdminSessionError extends BaseError {

	public WrongAdminSessionError(String field) {
		super(field.toUpperCase() + "IS_NOT_ADMIN_SESSION", field, "user is not admin");
	}
}
