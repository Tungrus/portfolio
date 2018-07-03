package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.requests.UserLoginRequest;
import net.thumbtack.airline.dto.response.AdminUsersGetResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.LogoutResponse;
import net.thumbtack.airline.dto.response.UserResponse;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.services.UserService;
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
public class SessionController {
	private final String loginURL = "/api/session";
	private final String userInformationURL = "/api/account";
	private final String getClientsURL = "/api/clients";
	private final String logoutURL = "/api/session";
	private final String cookieName = "JavaSessionID";

	@Autowired
	private ValidatorRunner validatorRunner;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	public SessionController(@Qualifier("userService") UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = loginURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserResponse login(@RequestBody UserLoginRequest userLoginRequest,
							  HttpServletResponse httpServletResponse) throws Exception {
		UserDto userDto = userLoginRequest.getUserDto();
		validatorRunner.run(userDto);
		httpServletResponse.addCookie(new Cookie(cookieName, userService.login(userDto).getToken()));
		return userService.getUserParams(userDto);
	}

	@GetMapping(value = userInformationURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserResponse getUserInformation(@CookieValue(cookieName) String javaSessionID) throws ApplicationSecurityException, DataNotFoundException {
		return userService.getUserParams(new JavaSessionID(javaSessionID));
	}

	@GetMapping(value = getClientsURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AdminUsersGetResponse getUsersData(@CookieValue(cookieName) String javaSessionID) throws ApplicationSecurityException {
		return userService.getClients(new JavaSessionID(javaSessionID));
	}

	@DeleteMapping(value = logoutURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LogoutResponse logout(@CookieValue(cookieName) String javaSessionID) throws ApplicationSecurityException {
		return userService.logout(new JavaSessionID(javaSessionID));
	}
}
