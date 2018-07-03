package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class LoginNotFoundError extends BaseError {
	public LoginNotFoundError() {
		super("LOGIN_NOT_FOUND", "register", "register is not exist");
	}
}
