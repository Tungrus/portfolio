package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.SettingsResponse;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.services.PropertyService;
import net.thumbtack.airline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {
	private final String settingsURL = "/api/settings";
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private PropertyService propertyService;
	private final String cookieName = "JavaSessionID";

	@GetMapping(value = settingsURL)
	public SettingsResponse getResponse(@CookieValue(value = cookieName, required = false) String javaSessionId) {
		try {
			int userId = userService.getUserIdBySession(new JavaSessionID(javaSessionId));
			return propertyService.getSettings(userId);
		} catch (ApplicationSecurityException e) {
			return propertyService.getSettings();
		}
	}
}
