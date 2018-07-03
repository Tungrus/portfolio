package net.thumbtack.airline.controllers;

import net.thumbtack.airline.config.StarterConfiguration;
import net.thumbtack.airline.errors.types.BaseError;
import net.thumbtack.airline.errors.types.runtime.BadUrlError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Profile("production")
public class BadURLController {
	private final static Logger logger = LoggerFactory.getLogger(StarterConfiguration.class);
	@RequestMapping(value = "*")
	public BaseError handle() {
		return new BadUrlError();
	}
}
