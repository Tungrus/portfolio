package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.model.User;

public interface UserDao {

	public void register(User user) throws ApplicationSecurityException;

	public void changePassword(String login, String newPassword, String oldPassword);//TODO add token to return;

	public void dropTable();

	public void deleteAll(String userType);

	public void checkUser(User user) throws ApplicationSecurityException;

	public User getUser(String login, String password) throws ApplicationSecurityException;

	public User getUser(int userId) throws ApplicationSecurityException;
}
