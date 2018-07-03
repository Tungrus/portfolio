package net.thumbtack.airline.utils.dates;

import java.sql.Date;
import java.util.List;

public class Dates {
	private List<Date> dates;
	private Date toDate;
	private Date fromDate;
	private String period;

	public Dates(List<Date> dates) {
		this.dates = dates;
	}

	public Dates(List<Date> dates, Date toDate, Date fromDate, String period) {
		this.dates = dates;
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.period = period;
	}

	public Dates(String period, Date toDate, Date fromDate) {
		this.toDate = toDate;
		this.fromDate = fromDate;
		this.period = period;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
