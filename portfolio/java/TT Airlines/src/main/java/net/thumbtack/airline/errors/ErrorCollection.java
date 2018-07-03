package net.thumbtack.airline.errors;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import net.thumbtack.airline.errors.types.BaseError;

import java.util.ArrayList;
import java.util.List;

public class ErrorCollection {
	@JsonUnwrapped
	private List<BaseError> errors;

	public ErrorCollection() {
		errors = new ArrayList<>();
	}

	public ErrorCollection(List<BaseError> errors) {
		this.errors = errors;
	}
	
	@JsonGetter("errors")
	public List<BaseError> getErrors() {
		return errors;
	}
	@JsonSetter("errors")
	public void setErrors(List<BaseError> errors) {
		this.errors = errors;
	}

	public void addProperty(BaseError baseError) {
		errors.add(baseError);
	}

	public int size() {
		return errors.size();
	}
}
