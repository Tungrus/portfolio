package net.thumbtack.airline.errors.types.runtime;

import net.thumbtack.airline.errors.types.BaseError;

public class ScheduleError extends BaseError {

	public ScheduleError() {
		super("BAD_PERIOD", "period", "");
	}
}
