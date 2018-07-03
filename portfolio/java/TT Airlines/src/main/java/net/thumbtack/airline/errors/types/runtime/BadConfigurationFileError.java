package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadConfigurationFileError extends BaseError {

	public BadConfigurationFileError(String field, String message) {
		super("BAD_CONFIG_ERROR", field, message);
	}
}
