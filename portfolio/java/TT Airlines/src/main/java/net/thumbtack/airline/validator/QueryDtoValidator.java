package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.BadPropertyError;
import net.thumbtack.airline.querties.DatesQueryParams;
import net.thumbtack.airline.querties.FlightQueryParams;
import net.thumbtack.airline.querties.OrderQueryParams;
import net.thumbtack.airline.querties.StringQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Map;

@Component
public class QueryDtoValidator extends BaseValidator{

	@Autowired
	private NotEmptyFieldConstrain notEmptyFieldConstrain;

	@Autowired
	private DateConstrain dateConstrain;

	public void validateStringQueryParams(Map<String, Object> query, ErrorCollection errorCollection) {
		for(String key : StringQueryParams.getStringQueryParams()) {
			if(query.containsKey(key)) {
				notEmptyFieldConstrain.check((String)query.get(key), key, errorCollection);
			}
		}
	}

	public void validateDatesQueryParams(Map<String, Object> query, ErrorCollection errorCollection) {
		for(String key : DatesQueryParams.getBaseQueryParams()) {
			if(query.containsKey(key)) {
				dateConstrain.check(Date.valueOf((String)query.get(key)), key, errorCollection);
			}
		}
	}

	public void validateFlightQueryParams(Map<String, Object> query, ErrorCollection errorCollection) {
		for(String key : FlightQueryParams.getFlightParams()) {
			if(query.containsKey(key)) {
				if(!query.get(key).getClass().equals(Boolean.class)) {
					errorCollection.addProperty(new BadPropertyError(key));
				}
			}
		}
	}

	public void validateOrderQueryParams(Map<String, Object> query, ErrorCollection errorCollection) {
		for(String key : OrderQueryParams.getOrderQueryParams()) {
			if(query.containsKey(key)) {
				IdConstrain.check((int)query.get(key), key, errorCollection);
			}
		}
	}
}
