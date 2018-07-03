package net.thumbtack.airline.model;

public class Admin extends User {
	private AdminParams adminParams;

	public Admin(int id, String login, String password, AdminParams adminParams) {
		super(id, login, password, UserType.ADMIN);
		this.adminParams = adminParams;
	}

	public AdminParams getAdminParams() {
		return adminParams;
	}

	public void setAdminParams(AdminParams adminParams) {
		this.adminParams = adminParams;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Admin)) return false;
		if (!super.equals(o)) return false;

		Admin admin = (Admin) o;

		return getAdminParams().equals(admin.getAdminParams());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getAdminParams().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"adminParams=" + adminParams +
				'}';
	}
}
