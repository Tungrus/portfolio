package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadUrlError extends BaseError {
	public BadUrlError() {
		super("BAD_URL", "url", "");
	}
}
