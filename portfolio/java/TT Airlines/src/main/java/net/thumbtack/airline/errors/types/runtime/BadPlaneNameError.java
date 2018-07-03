package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class BadPlaneNameError extends BaseError {
	public BadPlaneNameError() {
		super("BAD_PLANE_NAME", "planeName", "plane name not found in database");
	}
}
