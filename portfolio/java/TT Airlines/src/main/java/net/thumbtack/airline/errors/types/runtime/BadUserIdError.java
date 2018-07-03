package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadUserIdError extends BaseError {
	public BadUserIdError() {
		super("ID_NOT_FOUND", "id", "user id not found");
	}
}
