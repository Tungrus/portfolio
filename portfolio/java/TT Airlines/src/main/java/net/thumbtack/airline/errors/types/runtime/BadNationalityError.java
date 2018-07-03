package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadNationalityError extends BaseError {
	public BadNationalityError() {
		super("NATIONALITY_NOT_EXIST", "nationality", "");//TODO add message
	}
}
