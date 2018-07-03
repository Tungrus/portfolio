package net.thumbtack.airline.exception.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import net.thumbtack.airline.dto.response.ErrorResponse;
import net.thumbtack.airline.errors.types.BaseError;
import net.thumbtack.airline.errors.types.UnexpectedError;
import net.thumbtack.airline.errors.types.runtime.JsonParseError;
import net.thumbtack.airline.exception.BaseApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BaseExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public UnexpectedError handler(Exception e) {
		logger.error(e.toString());
		return new UnexpectedError(e.toString());
	}

	@ExceptionHandler(BaseApplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handler(BaseApplicationException applicationException) {
		for(BaseError error : applicationException.getErrorCollection().getErrors()) {
			logger.info(error.toString());
		}
		return new ErrorResponse(applicationException.getErrorCollection());
	}

	@ExceptionHandler(JsonParseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handler(JsonParseException applicationException) {
		logger.info(applicationException.getMessage());
		return new ErrorResponse(new JsonParseError());
	}


}
