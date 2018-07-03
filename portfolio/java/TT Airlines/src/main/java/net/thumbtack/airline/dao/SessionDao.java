package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.model.User;

public interface SessionDao {
	public String login(User user) throws Exception;

	public String getSessionToken(int ownerId) throws ApplicationSecurityException;

	public void logout(String sessionString) throws ApplicationSecurityException;

	public int getUserId(String sessionString) throws ApplicationSecurityException;
}
