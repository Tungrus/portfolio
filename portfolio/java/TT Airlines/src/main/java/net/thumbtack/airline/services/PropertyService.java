package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.response.SettingsResponse;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Value("${validator.password.client.maxLength}")
	private int clientMaxPasswordLength;
	@Value("${validator.password.admin.maxLength}")
	private int adminMaxPasswordLength;
	@Value("${validator.login.client.maxLength}")
	private int clientMaxNameLength;
	@Value("${validator.login.admin.maxLength}")
	private int adminMaxNameLength;

	public SettingsResponse getSettings(int userId) throws ApplicationSecurityException {
		if(userDao.getUser(userId).getUserType().equals(UserType.ADMIN)) {
			return new SettingsResponse(adminMaxNameLength, adminMaxPasswordLength);
		} else {
			return new SettingsResponse(clientMaxNameLength, clientMaxPasswordLength);
		}
	}

	public SettingsResponse getSettings() {
		return new SettingsResponse(clientMaxNameLength, clientMaxPasswordLength);
	}
}
