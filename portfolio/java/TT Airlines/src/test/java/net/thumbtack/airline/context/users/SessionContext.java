package net.thumbtack.airline.context.users;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class SessionContext {
	private List<Cookie> activeSessions = new ArrayList<>();

	public SessionContext(List<Cookie> activeSessions) {
		this.activeSessions = activeSessions;
	}

	public SessionContext() {
		this.activeSessions = new ArrayList<>();
	}

	public Cookie getCookie(int i) {
		return activeSessions.get(i);
	}

	public void setCookie(Cookie cookie) {
		activeSessions.add(cookie);
	}

	public List<Cookie> getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(List<Cookie> activeSessions) {
		this.activeSessions = activeSessions;
	}

	public void ResetContext() {
		activeSessions.clear();
	}
}
