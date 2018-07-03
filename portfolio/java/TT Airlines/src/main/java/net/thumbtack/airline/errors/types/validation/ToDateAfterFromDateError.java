package net.thumbtack.airline.errors.types.validation;

import net.thumbtack.airline.errors.types.BaseError;

public class ToDateAfterFromDateError extends BaseError{

	public ToDateAfterFromDateError() {
		super("TO_DATE_IS_AFTER_FROM_DATE", "date", "");//TODO add message
	}
}
