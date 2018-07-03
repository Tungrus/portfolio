package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class FirstLastNameMismatchError extends BaseError {
	public FirstLastNameMismatchError() {
		super("FIRST_LAST_NAME_MISMATCH", "firstName lastName", "");//TODO add message
	}
}
