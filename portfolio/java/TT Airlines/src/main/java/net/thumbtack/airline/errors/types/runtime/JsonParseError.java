package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class JsonParseError extends BaseError{
	public JsonParseError() {
		super("JSON_PARSE_ERROR", "json", "");
	}
}
