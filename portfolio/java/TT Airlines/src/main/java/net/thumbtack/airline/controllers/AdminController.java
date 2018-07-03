package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.AdminDto;
import net.thumbtack.airline.dto.AdminUpdateDto;
import net.thumbtack.airline.dto.requests.AdminRegistrationRequest;
import net.thumbtack.airline.dto.requests.AdminUpdateRequest;
import net.thumbtack.airline.dto.response.AdminRegistrationResponse;
import net.thumbtack.airline.dto.response.AdminUpdateResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.exception.ValidationException;
import net.thumbtack.airline.services.AdminService;
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
public class AdminController {
	private final String adminRegistrationPattern = "/api/admin";
	private final String cookieName = "JavaSessionID";
	private final String adminUpdateURL = "/api/admin";

	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;

	@Autowired
	private AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@Autowired
	private ValidatorRunner validatorRunner;

	@PostMapping(value = adminRegistrationPattern, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AdminRegistrationResponse registerAdmin(@RequestBody AdminRegistrationRequest adminRegistrationRequest,
												   HttpServletResponse httpServletResponse) throws Exception {
		AdminDto adminDto = adminRegistrationRequest.getAdminDto();
		validatorRunner.run(adminDto);
		AdminRegistrationResponse userResponse = adminService.register(adminDto);
		httpServletResponse.addCookie(new Cookie(cookieName, adminService.login(adminDto).getToken()));
		return userResponse;
	}

	@PutMapping(value = adminUpdateURL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AdminUpdateResponse changeParamsAdmin(@RequestBody AdminUpdateRequest adminUpdateRequest,
												 @CookieValue(cookieName) String javaSessionId) throws DataNotFoundException, ApplicationSecurityException, ValidationException {
		AdminUpdateDto adminUpdateDto = adminUpdateRequest.getAdminUpdateDto();
		validatorRunner.run(adminUpdateDto);
		int userId = adminService.isAdminSession(new JavaSessionID(javaSessionId));
		return adminService.updateAdmin(adminUpdateDto, userId);
	}
}
