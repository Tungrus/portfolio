package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class RegexPropertyError extends BaseError {
	public RegexPropertyError(String field) {
		super(field.toUpperCase() + "_DOES_NOT_MATCH_TO_DEMANDS", field,
				field.toUpperCase() + " field must meet the requirements");
	}
}
