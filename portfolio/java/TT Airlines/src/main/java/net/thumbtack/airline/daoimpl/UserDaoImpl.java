package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.errors.types.runtime.AlreadyLoginError;
import net.thumbtack.airline.errors.types.runtime.BadLoginPassword;
import net.thumbtack.airline.errors.types.runtime.BadUserIdError;
import net.thumbtack.airline.errors.types.runtime.LoginNotFoundError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.mappers.UserParamsMapper;
import net.thumbtack.airline.model.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
	}

	protected UserParamsMapper getUserMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(UserParamsMapper.class);
	}

	@Override
	public void register(User user) throws RuntimeException, ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			try {
				getUserMapper(sqlSession).insert(user);
			} catch (PersistenceException exception) {
				sqlSession.rollback();
				AlreadyLoginError alreadyLoginError = new AlreadyLoginError();
				logger.info(alreadyLoginError.toString());
				throw new ApplicationSecurityException(alreadyLoginError);
			} catch (RuntimeException ex) {
				sqlSession.rollback();
				logger.error(ex.toString());
				throw ex;
			}
			sqlSession.commit();
		}
	}


	@Override
	public void changePassword(String login, String newPassword, String oldPassword) {

	}

	@Override
	public void dropTable() {
		try (SqlSession sqlSession = getSession()) {
			try {
				getUserMapper(sqlSession).dropTable();
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
		}
	}

	@Override
	public void deleteAll(String userType) {
		try (SqlSession sqlSession = getSession()) {
			try {
				getUserMapper(sqlSession).deleteAll(userType);
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
		}
	}

	@Override
	public void checkUser(User user) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			try {
				User foundUser = getUserMapper(sqlSession).getUserByIdFromUser(user);
				if(foundUser == null) {
					LoginNotFoundError loginNotFoundError = new LoginNotFoundError();
					logger.info(loginNotFoundError.toString());
					throw new ApplicationSecurityException(loginNotFoundError);
				}
				user.setId(foundUser.getId());
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
		}
	}

	@Override
	public User getUser(String login, String password) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			try {
				User user;
				user = getUserMapper(sqlSession).initUser(login, password);
				if(user == null) {
					BadLoginPassword badLoginPassword = new BadLoginPassword();
					logger.info(badLoginPassword.toString());
					throw new ApplicationSecurityException(badLoginPassword);
				}
				return user;
			} catch (RuntimeException e) {
				logger.error(e.toString());
				throw e;
			}
		}
	}

	@Override
	public User getUser(int userId) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			try {
				User user;
				user = getUserMapper(sqlSession).getUserById(userId);
				if(user == null) throw new ApplicationSecurityException(new BadUserIdError());
				return user;
			} catch (RuntimeException e) {
				logger.error(e.toString());
				throw e;
			}
		}
	}
}
