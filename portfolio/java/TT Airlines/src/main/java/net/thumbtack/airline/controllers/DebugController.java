package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.services.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("debug")
public class DebugController {
	private final String debugPath = "/api/debug/clear";

	@Autowired
	private DebugService debugService;

	@PostMapping(value = debugPath, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public DebugResponse cleanDatabase() {
		return debugService.cleanDatabase();
	}
}
