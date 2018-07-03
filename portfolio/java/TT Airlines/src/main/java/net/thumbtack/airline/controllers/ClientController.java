package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.ClientDto;
import net.thumbtack.airline.dto.ClientUpdateDto;
import net.thumbtack.airline.dto.requests.ClientRegistrationRequest;
import net.thumbtack.airline.dto.requests.ClientUpdateRequest;
import net.thumbtack.airline.dto.response.ClientUpdateResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.UserResponse;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.exception.ValidationException;
import net.thumbtack.airline.services.ClientService;
import net.thumbtack.airline.validator.ValidatorRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Profile("production")
public class ClientController {
	private final String clientRegistrationURL = "/api/client";
	private final String clientUpdateURL = "/api/client";

	private final String cookieName = "JavaSessionID";

	@Autowired
	@Qualifier("clientService")
	private ClientService clientService;

	@Autowired
	private ValidatorRunner validatorRunner;

	@PostMapping(value = clientRegistrationURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserResponse registerClient(@RequestBody ClientRegistrationRequest request,
									   HttpServletResponse response) throws Exception {
		ClientDto clientDto = request.getClientDto();
		validatorRunner.run(clientDto);
		UserResponse userResponse = clientService.register(clientDto);
		response.addCookie(new Cookie(cookieName, clientService.login(clientDto).getToken()));
		return userResponse;
	}

	@PutMapping(value = clientUpdateURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ClientUpdateResponse changeParamsClient(@RequestBody ClientUpdateRequest userChangeParams,
												   @CookieValue(cookieName) String cookie) throws ValidationException, ApplicationSecurityException, DataNotFoundException {
		ClientUpdateDto clientUpdateDto = userChangeParams.getClientUpdateDto();
		validatorRunner.run(clientUpdateDto);
		return clientService.updateClient(new JavaSessionID(cookie), clientUpdateDto);
	}
}
