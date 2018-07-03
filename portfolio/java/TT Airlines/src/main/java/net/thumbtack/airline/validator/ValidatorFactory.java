package net.thumbtack.airline.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {
	@Autowired
	private AdminDtoValidator adminDtoValidator;
	@Autowired
	private AdminUpdateDtoValidator adminUpdateDtoValidator;
	@Autowired
	private UserDtoValidator userDtoValidator;
	@Autowired
	private ClientDtoValidator clientDtoValidator;
	@Autowired
	private ClientUpdateDtoValidator clientUpdateDtoValidator;
	@Autowired
	private DateDtoValidator dateDtoValidator;
	@Autowired
	private FlightInfoDtoValidator flightInfoDtoValidator;
	@Autowired
	private PlaneNameValidator planeNameValidator;
	@Autowired
	private OrderDtoValidator orderDtoValidator;
	@Autowired
	private FlightQueryDtoValidator flightQueryDtoValidator;
	@Autowired
	private OrderQueryDtoValidator orderQueryDtoValidator;
	@Autowired
	private DepartureRegistrationDtoValidator departureRegistrationDtoValidator;

	public UserDtoValidator getUserDtoValidator() {
		return userDtoValidator;
	}

	public AdminDtoValidator getAdminDtoValidator() {
		return adminDtoValidator;
	}

	public AdminUpdateDtoValidator getAdminUpdateDtoValidator() {
		return adminUpdateDtoValidator;
	}

	public ClientDtoValidator getClientDtoValidator() {
		return clientDtoValidator;
	}

	public ClientUpdateDtoValidator getClientUpdateDtoValidator() {
		return clientUpdateDtoValidator;
	}

	public DateDtoValidator getDateDtoValidator() {
		return dateDtoValidator;
	}

	public FlightInfoDtoValidator getFlightInfoDtoValidator() {
		return flightInfoDtoValidator;
	}

	public PlaneNameValidator getPlaneNameValidator() {
		return planeNameValidator;
	}

	public OrderDtoValidator getOrderDtoValidator() {
		return orderDtoValidator;
	}

	public FlightQueryDtoValidator getFlightQueryDtoValidator() {
		return flightQueryDtoValidator;
	}

	public OrderQueryDtoValidator getOrderQueryDtoValidator() {
		return orderQueryDtoValidator;
	}

	public DepartureRegistrationDtoValidator getDepartureRegistrationDtoValidator() {
		return departureRegistrationDtoValidator;
	}
}
