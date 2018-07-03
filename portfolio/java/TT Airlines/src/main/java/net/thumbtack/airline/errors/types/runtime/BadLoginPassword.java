package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadLoginPassword extends BaseError {

	public BadLoginPassword() {
		super("PASSWORD_LOGIN_NOT_FOUND", "login, password", "login-password combination not found");
	}
}
