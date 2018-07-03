package net.thumbtack.airline.model;

public class AdminParams extends NameParams {
	private String position;

	public AdminParams(String name, String lastName, String patronimic, String position) {
		super(name, lastName, patronimic);
		this.position = position;
	}

	public String getPositiont() {
		return position;
	}

	public void setPositiont(String position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdminParams)) return false;

		AdminParams that = (AdminParams) o;

		return getPositiont().equals(that.getPositiont());
	}

	@Override
	public int hashCode() {
		return getPositiont().hashCode();
	}

	@Override
	public String toString() {
		return "AdminParams{" +
				"position='" + position + '\'' +
				'}';
	}
}
