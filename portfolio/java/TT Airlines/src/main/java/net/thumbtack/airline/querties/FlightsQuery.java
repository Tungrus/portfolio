package net.thumbtack.airline.querties;

import java.util.Date;
import java.util.Map;

public class FlightsQuery {
	private Map<String, Object> query;

	public FlightsQuery(Map<String, Object> query) {
		this.query = query;
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	public String getValueByKey(String key) {//TODO add check to input keys
		return (String)query.get(key);
	}

	public Date getDateByKey(String key) {
		return (Date)query.get(key);
	}
}
