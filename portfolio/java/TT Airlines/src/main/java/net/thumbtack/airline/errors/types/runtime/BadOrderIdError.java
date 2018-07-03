package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadOrderIdError extends BaseError{

	public BadOrderIdError() {
		super("BAD_ORDER_ID", "orderId", "order id not found");
	}
}
