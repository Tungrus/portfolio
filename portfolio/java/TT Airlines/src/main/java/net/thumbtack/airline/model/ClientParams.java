package net.thumbtack.airline.model;

public class ClientParams extends NameParams {
	private String email;
	private String phoneNumber;

	public ClientParams(String name, String lastName, String patronymic, String email, String phoneNumber) {
		super(name, lastName, patronymic);
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientParams)) return false;
		if (!super.equals(o)) return false;

		ClientParams that = (ClientParams) o;

		if (!getEmail().equals(that.getEmail())) return false;
		return getPhoneNumber().equals(that.getPhoneNumber());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getEmail().hashCode();
		result = 31 * result + getPhoneNumber().hashCode();
		return result;
	}
}
