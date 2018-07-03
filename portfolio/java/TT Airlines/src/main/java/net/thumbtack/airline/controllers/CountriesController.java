package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.response.CountriesResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.services.CountriesService;
import net.thumbtack.airline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("production")
public class CountriesController {
	private static final String getCountriesListURL = "/api/countries";
	private final String cookieName = "JavaSessionID";

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private CountriesService countriesService;

	@GetMapping(value = getCountriesListURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CountriesResponse getCountries(@CookieValue(cookieName) String javaSessionId) throws ApplicationSecurityException {
		userService.getUserTypeBySession(new JavaSessionID(javaSessionId));
		return countriesService.getCountries();
	}
}
