package net.thumbtack.airline.model;

public class NameParams {
	private String firstName;
	private String lastName;
	private String patronymic;


	public NameParams(String firstName, String lastName, String patronymic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
	}

	public NameParams() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof NameParams)) return false;

		NameParams that = (NameParams) o;

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
		return "NameParams{" +
				"name='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", patronymic='" + patronymic + '\'' +
				'}';
	}
}
