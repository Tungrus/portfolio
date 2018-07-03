package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.regex.Pattern;

@Component
public class TimeConstrain {
	@Value("${validator.time.regexp}")
	private String timeRegexp;

	public void check(Time time, String fieldName, ErrorCollection errorCollection) {
		Pattern timePattern = Pattern.compile(timeRegexp);
		RegexpPropertyConstrain.check(time.toString(), timePattern, fieldName, errorCollection);
	}
}
