package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

@Component
public class NameParamsDto {
	private String firstName;
	@Pattern(regexp = "[а-яА-Я]{4,32}")
	private String lastName;
	@Pattern(regexp = "[а-яА-Я]{4,32}")
	private String patronymic;

	public NameParamsDto(String firstName, String lastName,
						 String patronymic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
	}

	public NameParamsDto() {

	}

	@JsonGetter("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonSetter("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonGetter("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonGetter("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonGetter("patronymic")
	public String getPatronymic() {
		return patronymic;
	}

	@JsonSetter("patronymic")
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NameParamsDto)) return false;

		NameParamsDto that = (NameParamsDto) o;

		if (!getFirstName().equals(that.getFirstName())) return false;
		if (!getLastName().equals(that.getLastName())) return false;
		return getPatronymic() != null ? getPatronymic().equals(that.getPatronymic()) : that.getPatronymic() == null;
	}

	@Override
	public int hashCode() {
		int result = getFirstName().hashCode();
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "NameParamsDto{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", patronymic='" + patronymic + '\'' +
				'}';
	}
}
