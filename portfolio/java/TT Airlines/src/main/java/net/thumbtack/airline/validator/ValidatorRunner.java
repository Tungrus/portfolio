package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.*;
import net.thumbtack.airline.dto.requests.AddFlightRequest;
import net.thumbtack.airline.dto.requests.ChangeFlightRequest;
import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorRunner {
	@Autowired
	private ValidatorFactory validatorFactory;

	private static void isValidationSuccess(ErrorCollection errorCollection) throws ValidationException { //TODO refact from static to normal class with DI and 1 method validate();
		if (errorCollection.size() > 0) {
			throw new ValidationException(errorCollection);
		}
	}

	public void run(UserDto userDto) throws ValidationException{
		ErrorCollection errorCollection = new ErrorCollection();

		validatorFactory.getUserDtoValidator().validate(userDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(AdminDto adminDto) throws ValidationException  {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getAdminDtoValidator().validate(adminDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(ClientDto clientDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getClientDtoValidator().validate(clientDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(ClientUpdateDto clientUpdateDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getClientUpdateDtoValidator().validate(clientUpdateDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(AdminUpdateDto adminUpdateDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getAdminUpdateDtoValidator().validate(adminUpdateDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(ChangeFlightRequest changeFlightRequest) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getDateDtoValidator().validate(changeFlightRequest.getDateDto(), errorCollection);
		validatorFactory.getFlightInfoDtoValidator().validate(changeFlightRequest.getFlightInfoDto(), errorCollection);
		validatorFactory.getPlaneNameValidator().validate(changeFlightRequest.getPlaneName(), errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(OrderDto orderDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getOrderDtoValidator().validate(orderDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(FlightsQueryDto flightsQueryDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getFlightQueryDtoValidator().validate(flightsQueryDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(AddFlightRequest addFlightRequest) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getDateDtoValidator().validate(addFlightRequest.getDateDto(), errorCollection);
		validatorFactory.getFlightInfoDtoValidator().validate(addFlightRequest.getFlightInfoDto(), errorCollection);
		validatorFactory.getPlaneNameValidator().validate(addFlightRequest.getPlaneName(), errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(OrderQueryDto orderQueryDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getOrderQueryDtoValidator().validate(orderQueryDto, errorCollection);
		isValidationSuccess(errorCollection);
	}

	public void run(DepartureRegistrationDto departureRegistrationDto) throws ValidationException {
		ErrorCollection errorCollection = new ErrorCollection();
		validatorFactory.getDepartureRegistrationDtoValidator().validate(departureRegistrationDto, errorCollection);
		isValidationSuccess(errorCollection);
	}
}
