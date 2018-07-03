package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.SessionDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.AdminDto;
import net.thumbtack.airline.dto.AdminUpdateDto;
import net.thumbtack.airline.dto.response.AdminRegistrationResponse;
import net.thumbtack.airline.dto.response.AdminUpdateResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.LogoutResponse;
import net.thumbtack.airline.errors.types.runtime.WrongAdminSessionError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.AdminParams;
import net.thumbtack.airline.model.User;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.transformers.dto.AdminUpdateResponseTransformer;
import net.thumbtack.airline.transformers.dto.UserResponseTransformer;
import net.thumbtack.airline.transformers.model.ModelAdminTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("adminService")
public class AdminService extends UserService {
	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	public AdminService(@Qualifier("userDao") UserDao userDao, SessionDao sessionDao,
						@Qualifier("adminDao") AdminDao adminDao, @Qualifier("clientDao") ClientDao clientDao) {
		super(userDao, sessionDao, adminDao, clientDao);
		this.adminDao = adminDao;
		this.userDao = userDao;
		this.sessionDao = sessionDao;
	}


	public AdminRegistrationResponse register(AdminDto adminDto)throws Exception {
		Admin admin = ModelAdminTransformer.create(adminDto);
		admin = adminDao.register(admin);
		return UserResponseTransformer.create(admin);
	}

	@Override
	public void deleteAll() {
		adminDao.deleteAll(UserType.ADMIN.toString());
	}

	public LogoutResponse logout(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		return super.logout(javaSessionID);
	}

	public void deleteAllAdmins() {
		adminDao.deleteAll(UserType.ADMIN.toString());
	}

	public int isAdminSession(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		int userId = sessionDao.getUserId(javaSessionID.getToken());
		User user = userDao.getUser(userId);
		if (user.getUserType().equals(UserType.ADMIN)) {
			return sessionDao.getUserId(javaSessionID.getToken());
		} else {
			throw new ApplicationSecurityException(new WrongAdminSessionError("session"));
		}
	}

	public AdminUpdateResponse updateAdmin(AdminUpdateDto adminUpdateDto, int userId) throws DataNotFoundException {
		AdminParams adminParams = adminDao.updateAdmin(ModelAdminTransformer.create(adminUpdateDto.getAdminParamsDto()),
				userId, adminUpdateDto.getNewPassword());
		return AdminUpdateResponseTransformer.create(adminParams);
	}
}
