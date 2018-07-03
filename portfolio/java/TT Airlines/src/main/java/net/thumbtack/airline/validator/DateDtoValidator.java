package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightScheduleDto;
import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.BadPropertyError;
import net.thumbtack.airline.errors.types.validation.EmptyQueryError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DateDtoValidator extends BaseValidator {
	private static String dateDtoFieldName = "dateDto";
	private static String[] acceptableKeys = {"schedule", "dates"};

	@Autowired
	private DateConstrain dateConstrain;
	@Autowired
	private NotEmptyFieldConstrain notEmptyFieldConstrain;

	public void validate(DateDto dateDto, ErrorCollection errorCollection) {

		Map<String, Object> flightSchedule = dateDto.getFlightSchedule();
		if (flightSchedule.size() == 0) {
			errorCollection.addProperty(new EmptyQueryError(dateDtoFieldName));
		}
		if (flightSchedule.containsKey(acceptableKeys[0])) {
			validateScheduleDto((FlightScheduleDto)flightSchedule.get(acceptableKeys[0]),errorCollection);
		} else if (flightSchedule.containsKey(acceptableKeys[1])) {
			validateDateList(flightSchedule.get(acceptableKeys[1]), errorCollection);
		} else {
			errorCollection.addProperty(new BadPropertyError(dateDtoFieldName));
		}
	}

	public void validateDateList(Object dates, ErrorCollection errorCollection) {
		NotNullConstrain.check(dates, acceptableKeys[1], errorCollection);
		try {
			List<java.sql.Date> dateList = (List<java.sql.Date>) dates;
		} catch (ClassCastException e) {
			errorCollection.addProperty(new BadPropertyError(acceptableKeys[1]));
		}
	}

	public void validateScheduleDto(FlightScheduleDto flightScheduleDto, ErrorCollection errorCollection) {
		if(flightScheduleDto == null) {
			errorCollection.addProperty(new BadPropertyError(acceptableKeys[0]));
		}
		dateConstrain.check(flightScheduleDto.getFromDate(), "fromDate", errorCollection);
		dateConstrain.check(flightScheduleDto.getToDate(), "toDate", errorCollection);
		notEmptyFieldConstrain.check(flightScheduleDto.getPeriod(), "period", errorCollection);
	}
}