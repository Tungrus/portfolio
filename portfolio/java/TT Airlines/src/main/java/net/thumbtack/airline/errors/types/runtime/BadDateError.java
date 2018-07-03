package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadDateError extends BaseError {
	public BadDateError() {
		super("BAD_DATE", "date", "this flight don't fly in this date");
	}
}
