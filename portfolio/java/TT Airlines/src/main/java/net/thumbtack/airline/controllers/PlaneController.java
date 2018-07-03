package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.response.GetPlanesResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("production")
public class PlaneController {
	private final String adminGetPlanesPath = "/api/flights";
	private final String cookieName = "JavaSessionID";

	@Autowired
	private PlaneService planeService;

	@Autowired
	private AdminService adminService;

	@GetMapping(value = adminGetPlanesPath, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GetPlanesResponse getPlanes(@CookieValue(cookieName) String javaSessionId) throws ApplicationSecurityException {
		adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return planeService.getPlanes();
	}
}
