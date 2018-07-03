package net.thumbtack.airline.errors.types;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class BaseError {
	private String errorCode;
	private String field;
	private String message;

	public BaseError() {
	}

	public BaseError(String errorCode, String field, String message) {
		this.errorCode = errorCode;
		this.field = field;
		this.message = message;
	}

	@JsonGetter("errorCode")
	public String getErrorCode() {
		return errorCode;
	}
	@JsonSetter("errorCode")
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@JsonGetter("field")
	public String getField() {
		return field;
	}
	@JsonSetter("field")
	public void setField(String field) {
		this.field = field;
	}

	@JsonGetter("message")
	public String getMessage() {
		return message;
	}
	@JsonSetter("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "errorCode='" + errorCode + '\'' +
				", field='" + field + '\'' +
				", message='" + message + '\'';
	}
}
