package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.DepartureRegistrationDto;
import net.thumbtack.airline.dto.requests.DepartureClientRegistrationRequest;
import net.thumbtack.airline.dto.response.DepartureClientRegistrationResponse;
import net.thumbtack.airline.dto.response.GetFreePlacesForRegistrationResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.BadPlaceException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.exception.ValidationException;
import net.thumbtack.airline.services.ClientService;
import net.thumbtack.airline.services.DepartureClientRegistrationService;
import net.thumbtack.airline.services.UserService;
import net.thumbtack.airline.validator.ValidatorRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("production")
public class DepartureController {
	private final static String departureRegistrationURL = "/api/places";
	private final static String placesForRegistrationURL = "/api/places/{orderId}";

	private final String cookieName = "JavaSessionID";

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("clientService")
	private ClientService clientService;

	@Autowired
	private DepartureClientRegistrationService departureClientRegistrationService;

	@Autowired
	private ValidatorRunner validatorRunner;

	@PostMapping(value = departureRegistrationURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public DepartureClientRegistrationResponse registerOnFlight(@RequestBody DepartureClientRegistrationRequest
																			  departureClientRegistrationRequest,
																  @CookieValue(cookieName) String cookie)
			throws ValidationException, ApplicationSecurityException, DataNotFoundException, BadPlaceException {
		DepartureRegistrationDto departureRegistrationDto = departureClientRegistrationRequest.getRegistrationDto();
		validatorRunner.run(departureRegistrationDto);
		clientService.isClientSession(new JavaSessionID(cookie));
		return departureClientRegistrationService.register(departureRegistrationDto);
	}

	@GetMapping(value = placesForRegistrationURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GetFreePlacesForRegistrationResponse getFreePlacesForRegistration(@CookieValue(cookieName) String javaSessionId,
																			 @PathVariable("orderId") int orderId)
			throws ApplicationSecurityException, DataNotFoundException {
		clientService.isClientSession(new JavaSessionID(javaSessionId));
		return departureClientRegistrationService.getFreePlacesForRegistration(orderId);
	}
}
