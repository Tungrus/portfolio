package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.OrderParamsDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderParamsDtoValidator extends BaseValidator {
	@Autowired
	private DateConstrain dateConstrain;

	public void validate(OrderParamsDto orderParamsDto, ErrorCollection errorCollection) {
		IdConstrain.check(orderParamsDto.getFlightId(), "flightId", errorCollection);
		dateConstrain.check(orderParamsDto.getDate(), "date", errorCollection);
	}
}
