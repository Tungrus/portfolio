package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class PriceError extends BaseError{
	public PriceError(String field) {
		super(field.toUpperCase() + "_CONTAINS_BAD_VALUE", field, field + " can't contains this value");
	}
}
