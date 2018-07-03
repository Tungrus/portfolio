package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class UnresolvedUserTypeError extends BaseError {

	public UnresolvedUserTypeError() {
		super("BAD_USER_TYPE", "userType", "");
	}
}
