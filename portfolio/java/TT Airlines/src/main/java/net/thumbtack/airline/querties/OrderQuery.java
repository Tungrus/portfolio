package net.thumbtack.airline.querties;

import java.util.HashMap;
import java.util.Map;

public class OrderQuery {
	private Map<String, Object> query;

	public OrderQuery() {
		query = new HashMap<>();
	}

	public OrderQuery(Map<String, Object> query) {
		this.query = query;
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	public void setProperty(String key, Object value) {
		query.put(key, value);
	}

	public Object getProperty(String key) {
		return query.get(key);
	}
}
