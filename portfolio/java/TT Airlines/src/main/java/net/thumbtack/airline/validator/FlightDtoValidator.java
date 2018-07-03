package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightDtoValidator extends BaseValidator {
	@Autowired
	private DateDtoValidator dateDtoValidator;

	@Autowired
	private FlightInfoDtoValidator flightInfoDtoValidator;

	public void validate(FlightDto flightDto, ErrorCollection errorCollection) {
		dateDtoValidator.validate(flightDto.getDateDto(), errorCollection);
		flightInfoDtoValidator.validate(flightDto.getFlightInfoDto(), errorCollection);
	}
}
