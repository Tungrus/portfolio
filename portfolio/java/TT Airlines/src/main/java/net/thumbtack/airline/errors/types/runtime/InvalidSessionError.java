package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class InvalidSessionError extends BaseError {
	public InvalidSessionError() {
		super("BAD_SESSION", "javaSessionId", "user must be register to this operation");
	}
}
