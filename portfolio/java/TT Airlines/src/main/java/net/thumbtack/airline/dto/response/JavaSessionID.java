package net.thumbtack.airline.dto.response;

public class JavaSessionID {
	private String token;

	public JavaSessionID(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
