package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadConfigFileError extends BaseError {

	public BadConfigFileError() {
		super("BAD_CONFIG_FILE_ERROR", "command line args", "should contain more than 1 value");
	}
}
