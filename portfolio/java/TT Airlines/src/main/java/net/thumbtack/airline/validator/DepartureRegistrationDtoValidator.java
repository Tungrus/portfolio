package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.DepartureRegistrationDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartureRegistrationDtoValidator extends BaseValidator {
	@Autowired
	private PlaceDtoValidator placeDtoValidator;

	@Autowired
	private NameParamsDtoValidator nameParamsDtoValidator;

	private static String orderIdFieldName = "orderId";
	private static String ticketFieldName = "orderId";

	public void validate(DepartureRegistrationDto departureRegistrationDto, ErrorCollection errorCollection) {
		IdConstrain.check(departureRegistrationDto.getOrderId(), orderIdFieldName, errorCollection);
		IdConstrain.check(departureRegistrationDto.getTicketId(), ticketFieldName, errorCollection);
		placeDtoValidator.validate(departureRegistrationDto.getPlace(), errorCollection);
		nameParamsDtoValidator.validateFirstName(departureRegistrationDto.getFirstName(), errorCollection);
		nameParamsDtoValidator.validateLastName(departureRegistrationDto.getLastName(), errorCollection);
	}
}
