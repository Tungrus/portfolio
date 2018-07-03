package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.context.dates.DatesTestContext;
import net.thumbtack.airline.utils.dates.Dates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelDateTransformerTest {
	private DatesTestContext datesTestContext = new DatesTestContext();

	@Test
	public void create() throws Exception {
		Dates dates1 = datesTestContext.getDatesFromAllPeriod();
		Dates dates2 = datesTestContext.getDatesFromOddPeriod();
		Dates dates3 = datesTestContext.getDatesFromEvenPeriod();
		Dates dates4 = datesTestContext.getDatesFromDaysOfWeek();
		Dates dates5 = datesTestContext.getDatesFromDaysOfMonth();//TODO fix manualyCheck
		assertEquals(dates1.getDates().size(), dates2.getDates().size() + dates3.getDates().size());
		assertEquals(dates4.getDates().size(), dates1.getDates().size());
	}

}