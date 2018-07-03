package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightInfoDtoValidator extends BaseValidator {

	@Autowired
	private NotEmptyFieldConstrain notEmptyFieldConstrain;

	@Autowired
	private TimeConstrain timeConstrain;

	public void validate(FlightInfoDto flightInfo, ErrorCollection errorCollection) {
		PriceConstrain.check(flightInfo.getPriceBusiness(), "priceBusiness", errorCollection);
		PriceConstrain.check(flightInfo.getPriceEconomy(), "priceEconomy", errorCollection);
		notEmptyFieldConstrain.check(flightInfo.getFlightName(), "flightName", errorCollection);
		notEmptyFieldConstrain.check(flightInfo.getFromTown(), "fromTown", errorCollection);
		notEmptyFieldConstrain.check(flightInfo.getToTown(), "toTown", errorCollection);
		timeConstrain.check(flightInfo.getDuration(), "duration", errorCollection);
		timeConstrain.check(flightInfo.getStartTime(), "start", errorCollection);
	}
}
