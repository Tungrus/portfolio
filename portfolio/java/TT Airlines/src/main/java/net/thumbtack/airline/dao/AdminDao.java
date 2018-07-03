package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.AdminParams;
import net.thumbtack.airline.model.User;

public interface AdminDao {
	public Admin register(Admin admin) throws Exception;

	public Admin loadUserContext(User user) throws ApplicationSecurityException;

	public void deleteAll(String userType);

	public AdminParams updateAdmin(AdminParams adminParams, int userId, String newPassword) throws DataNotFoundException;

	public AdminParams getAdminById(int userId) throws DataNotFoundException;
}
