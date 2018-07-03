package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.FlightsQueryDto;
import net.thumbtack.airline.dto.requests.AddFlightRequest;
import net.thumbtack.airline.dto.requests.ChangeFlightRequest;
import net.thumbtack.airline.dto.requests.FlightsByQueryRequest;
import net.thumbtack.airline.dto.response.*;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.exception.ValidationException;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.FlightService;
import net.thumbtack.airline.validator.ValidatorRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("production")
public class FlightController {
	private final String changeFlightURL = "/api/flights/{flightNumber}";
	private final String deleteFlightURL = "/api/flights/{flightNumber}";
	private final String getFlightURL = "/api/flights/{flightNumber}";
	private final String approveFlightURL = "/api/flights/{flightNumber}/approve";
	private final String getFlightsListURL = "/api/flights";
	private final String addFlightURL = "/api/flights";

	private final String cookieName = "JavaSessionID";

	@Autowired
	private AdminService adminService;
	@Autowired
	private FlightService flightService;
	@Autowired
	private ValidatorRunner validatorRunner;

	@PutMapping(value = changeFlightURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ChangeFlightResponse changeFlight(@PathVariable("flightNumber") int flightId,
											 @RequestBody ChangeFlightRequest changeFlightRequest,
											 @CookieValue(cookieName) String javaSessionId) throws BaseApplicationException {
		validatorRunner.run(changeFlightRequest);
		adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return flightService.changeFlight(changeFlightRequest.getFlightInfoDto(), changeFlightRequest.getDateDto(),
				changeFlightRequest.getPlaneName(), flightId);
	}

	@DeleteMapping(value = deleteFlightURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public DeleteFlightResponse deleteFlight(@PathVariable("flightNumber") int flightId,
											 @CookieValue(cookieName) String javaSessionId) throws ApplicationSecurityException, DataNotFoundException {
		adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return flightService.deleteFlight(flightId);
	}

	@GetMapping(value = getFlightURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AdminGetFlightResponse getFlight(@CookieValue(cookieName) String javaSessionId,
											@PathVariable("flightNumber") int flightId) throws ApplicationSecurityException {
		adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return flightService.getFlight(flightId);
	}

	@PutMapping(value = approveFlightURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApproveFlightResponse approveFlight(@CookieValue(cookieName) String javaSessionId,
											   @PathVariable("flightNumber") int flightId) throws ApplicationSecurityException, DataNotFoundException {
		adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return flightService.approveFlight(flightId);
	}

	@GetMapping(value = getFlightsListURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GetFlightsByQueryResponse getFlightsByQuery(@RequestBody FlightsByQueryRequest flightsByQueryRequest,
													   @CookieValue(cookieName) String javaSessionId) throws Exception {
		FlightsQueryDto flightsQueryDto = flightsByQueryRequest.getQuery();
		validatorRunner.run(flightsQueryDto);
		return flightService.getFlightsByQuery(flightsQueryDto, new JavaSessionID(javaSessionId));//TODO fix get session from method
	}

	@PostMapping(value = addFlightURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AddFlightResponse addFlight(@CookieValue(cookieName) String javaSessionId,
									   @RequestBody AddFlightRequest addFlightRequest) throws BaseApplicationException {
		validatorRunner.run(addFlightRequest);
		adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return flightService.addFlight(addFlightRequest.getFlightInfoDto(), addFlightRequest.getDateDto(),
				addFlightRequest.getPlaneName());
	}


}
