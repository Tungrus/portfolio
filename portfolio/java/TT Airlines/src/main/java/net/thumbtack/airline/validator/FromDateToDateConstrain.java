package net.thumbtack.airline.validator;

import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.ToDateAfterFromDateError;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class FromDateToDateConstrain extends BaseConstrain {
	public static void check(Date fromDate, Date toDate, ErrorCollection errorCollection) {
		if(!toDate.after(fromDate)) {
			errorCollection.addProperty( new ToDateAfterFromDateError());
		}
	}
}
