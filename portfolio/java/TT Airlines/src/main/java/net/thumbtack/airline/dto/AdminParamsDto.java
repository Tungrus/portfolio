package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.constraints.NotNull;

public class AdminParamsDto extends NameParamsDto {
	@NotNull
	private String position;

	public AdminParamsDto(String firstName, String lastName, String patronimyc, String position) {
		super(firstName, lastName, patronimyc);
		this.position = position;
	}

	public AdminParamsDto() {
	}

	@JsonGetter("position")
	public String getPositiont() {
		return position;
	}

	@JsonSetter("position")
	public void setPositiont(String position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdminParamsDto)) return false;

		AdminParamsDto that = (AdminParamsDto) o;

		return getPositiont().equals(that.getPositiont());
	}

	@Override
	public int hashCode() {
		return getPositiont().hashCode();
	}

	@Override
	public String toString() {
		return "AdminParamsDto{" +
				"position='" + position + '\'' +
				'}';
	}
}
