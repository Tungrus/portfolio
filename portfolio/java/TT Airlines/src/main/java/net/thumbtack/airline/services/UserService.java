package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.SessionDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.response.AdminUsersGetResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.LogoutResponse;
import net.thumbtack.airline.dto.response.UserResponse;
import net.thumbtack.airline.errors.types.runtime.WrongAdminSessionError;
import net.thumbtack.airline.errors.types.runtime.WrongClientSessionError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.User;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.transformers.dto.UserResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userService")
public class UserService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	private SessionDao sessionDao;
	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;
	@Autowired
	@Qualifier("clientDao")
	private ClientDao clientDao;

	@Autowired
	public UserService(@Qualifier("clientDao") UserDao userDao, SessionDao sessionDao, AdminDao adminDao, ClientDao clientDao) {
		this.userDao = userDao;
		this.sessionDao = sessionDao;
		this.adminDao = adminDao;
		this.clientDao = clientDao;
	}

	private String openSession(User user) throws Exception {
		return sessionDao.login(user);
	}

	public void deleteAll() {
		userDao.dropTable();
	}

	public JavaSessionID login(UserDto userDto) throws Exception {
		User user = userDao.getUser(userDto.getLogin(), userDto.getPassword());
		return new JavaSessionID(openSession(user));
	}

	public void checkPassword(User user) throws ApplicationSecurityException {
		userDao.checkUser(user);
	}

	// REVU UserResponse is a raw type. References to generic type UserResponse<Usr> should be parameterized
	public UserResponse getUserParams(UserDto userDto) throws ApplicationSecurityException, DataNotFoundException {
		User user = userDao.getUser(userDto.getLogin(), userDto.getPassword());
		return getUserContext(user);
	}

	// REVU UserResponse is a raw type. References to generic type UserResponse<Usr> should be parameterized
	protected UserResponse getUserContext(User user) throws ApplicationSecurityException, DataNotFoundException {
		if (user.getUserType().equals(UserType.ADMIN)) {
			return UserResponseTransformer.createUserResponse(adminDao.loadUserContext(user));
		} else {
			return UserResponseTransformer.createUserResponse(clientDao.loadUserContext(user));
		}
	}

	public LogoutResponse logout(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		sessionDao.logout(javaSessionID.getToken());
		return new LogoutResponse();
	}

	public void createSession(User user) throws Exception {
		checkPassword(user);
		openSession(user);
	}

	public UserResponse getUserParams(JavaSessionID javaSessionID) throws ApplicationSecurityException, DataNotFoundException {
		int userId = sessionDao.getUserId(javaSessionID.getToken());
		return getUserContext(userDao.getUser(userId));
	}

	public AdminUsersGetResponse getClients(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		int userId = sessionDao.getUserId(javaSessionID.getToken());
		User user = userDao.getUser(userId);
		if (user.getUserType().equals(UserType.ADMIN)) {
			return UserResponseTransformer.create(clientDao.getClients());
		} else {
			throw new ApplicationSecurityException(new WrongAdminSessionError("javaSessionId"));
		}
	}

	public int getUserIdBySession(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		return sessionDao.getUserId(javaSessionID.getToken());
	}

	protected Client getClientBySession(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		int userId = 0;
		userId = sessionDao.getUserId(javaSessionID.getToken());
		if(userId == 0) throw new ApplicationSecurityException(new WrongClientSessionError("javaSessionId"));
		return clientDao.getClientByID(userId);
	}

	public UserType getUserTypeBySession(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		int userId = 0;
		userId = sessionDao.getUserId(javaSessionID.getToken());

		return userDao.getUser(userId).getUserType();
	}

}
