package net.thumbtack.airline.dto.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.BaseError;

public class ErrorResponse {
	@JsonUnwrapped
	private ErrorCollection errorCollection;

	public ErrorResponse() {
		errorCollection = new ErrorCollection();
	}

	public ErrorResponse(BaseError baseError) {
		errorCollection = new ErrorCollection();
		errorCollection.addProperty(baseError);
	}

	public ErrorResponse(ErrorCollection errorCollection) {
		this.errorCollection = errorCollection;
	}

	@JsonSetter("errorCollection")
	public ErrorCollection getErrorCollection() {
		return errorCollection;
	}
	@JsonSetter("errorCollection")
	public void setErrorCollection(ErrorCollection errorCollection) {
		this.errorCollection = errorCollection;
	}
}
