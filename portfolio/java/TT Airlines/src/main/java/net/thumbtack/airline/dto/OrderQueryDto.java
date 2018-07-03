package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;
import java.util.TreeMap;

public class OrderQueryDto {
	private Map<String, Object> query;


	public OrderQueryDto() {
		query = new TreeMap<>();
	}

	public OrderQueryDto(Map<String, Object> query) {
		this.query = query;
	}

	@JsonAnyGetter
	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	@JsonAnySetter
	public void setValue(String key, Object value) {
		query.put(key, value);
	}
}
