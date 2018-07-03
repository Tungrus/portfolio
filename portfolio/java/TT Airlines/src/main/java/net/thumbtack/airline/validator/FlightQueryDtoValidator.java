package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.FlightsQueryDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightQueryDtoValidator extends BaseValidator {
	@Autowired
	private QueryDtoValidator queryDtoValidator;

	public void validate(FlightsQueryDto flightsQueryDto, ErrorCollection errorCollection) {
		queryDtoValidator.validateStringQueryParams(flightsQueryDto.getQuery(), errorCollection);
		queryDtoValidator.validateDatesQueryParams(flightsQueryDto.getQuery(), errorCollection);
		queryDtoValidator.validateFlightQueryParams(flightsQueryDto.getQuery(), errorCollection);
	}
}
