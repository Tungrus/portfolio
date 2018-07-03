package net.thumbtack.airline.model;


public class Client extends User {
	private ClientParams clientParams;

	public Client(int id, String login, String password, ClientParams clientParams) {
		super(id, login, password, UserType.CLIENT);
		this.clientParams = clientParams;
	}

	public Client(int id, String login, String password, String fristName, String lastName, String patronymic,
				  String email, String phoneNumber, String userType) {
		super(id, login, password, UserType.CLIENT);
		clientParams = new ClientParams(fristName, lastName, patronymic, email, phoneNumber);
	}

	public ClientParams getClientParams() {
		return clientParams;
	}

	public void setClientParams(ClientParams clientParams) {
		this.clientParams = clientParams;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Client)) return false;
		if (!super.equals(o)) return false;

		Client client = (Client) o;

		return getClientParams().equals(client.getClientParams());
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getClientParams().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Client{" +
				"clientParams=" + clientParams +
				'}';
	}

	public void UpdateClient(Client client) {
		this.clientParams = client.getClientParams();
		this.setPassword(client.getPassword());
	}

}
