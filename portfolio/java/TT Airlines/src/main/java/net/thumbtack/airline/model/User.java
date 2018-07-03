package net.thumbtack.airline.model;

public class User {
	private int id;
	private String login;
	private String password;
	private UserType userType;


	public User(int id, String login, String password, UserType userType) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.userType = userType;
	}

	public User(int id, String login, String password, String userType) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.userType = UserType.valueOf(userType.toUpperCase());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (getId() != user.getId()) return false;
		if (!getLogin().equals(user.getLogin())) return false;
		if (!getPassword().equals(user.getPassword())) return false;
		return getUserType() == user.getUserType();
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + getLogin().hashCode();
		result = 31 * result + getPassword().hashCode();
		result = 31 * result + getUserType().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", register='" + login + '\'' +
				", password='" + password + '\'' +
				", userType=" + userType +
				'}';
	}
}
