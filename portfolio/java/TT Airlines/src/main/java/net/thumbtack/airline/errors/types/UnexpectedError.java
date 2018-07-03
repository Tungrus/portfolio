package net.thumbtack.airline.errors.types;

public class UnexpectedError extends BaseError {

	public UnexpectedError(String message) {
		super("UNEXPECTED_ERROR", "unknown", message);
	}
}
