package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;

@Component
public class DateConstrain extends BaseConstrain {
	@Value("${validator.date.regexp}")
	private String dateRegexp;

	public void check(String date, String fieldName, ErrorCollection errorCollection) {
		Pattern datePattern = Pattern.compile(dateRegexp);
		RegexpPropertyConstrain.check(date, datePattern, fieldName, errorCollection);
	}

	public void check(Date date, String fieldName, ErrorCollection errorCollection) {
		Pattern datePattern = Pattern.compile(dateRegexp);
		RegexpPropertyConstrain.check(date.toString(), datePattern, fieldName, errorCollection);
	}

}
