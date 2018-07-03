package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.SessionDao;
import net.thumbtack.airline.errors.types.runtime.AlreadyLoginError;
import net.thumbtack.airline.errors.types.runtime.BadUserIdError;
import net.thumbtack.airline.errors.types.runtime.InvalidSessionError;
import net.thumbtack.airline.errors.types.runtime.UserSessionNotFound;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.mappers.SessionMapper;
import net.thumbtack.airline.model.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Random;

@Repository
public class SessionDaoIml extends BaseDaoImpl implements SessionDao {

	private final Logger logger = LoggerFactory.getLogger(SessionDaoIml.class);

	public SessionDaoIml() {
	}

	protected SessionMapper getSessionMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(SessionMapper.class);
	}

	public String getSessionToken(int ownerId, SqlSession sqlSession) {
		return getSessionMapper(sqlSession).getSessionToken(ownerId);
	}

	private String getSessionToken(User user) {
		try (SqlSession sqlSession = getSession()) {
			return getSessionToken(user.getId(), sqlSession);
		}
	}

	@Override
	public String login(User user) throws Exception {
		try (SqlSession sqlSession = getSession()) {
			try {
				login(user, sqlSession);
			}catch (PersistenceException ex) {
				sqlSession.rollback();
				AlreadyLoginError alreadyLoginError = new AlreadyLoginError();
				logger.info(alreadyLoginError.toString());
				throw new ApplicationSecurityException(alreadyLoginError);
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw new Exception();
			}
			sqlSession.commit();
		}
		return getSessionToken(user);
	}

	@Override
	public String getSessionToken(int ownerId) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			String sessionToken = getSessionMapper(sqlSession).getSessionToken(ownerId);
			if(sessionToken == null) {
				BadUserIdError badUserIdError = new BadUserIdError();
				logger.info(badUserIdError.toString());
				throw new ApplicationSecurityException(badUserIdError);
			}
			return sessionToken;
		}
	}

	private void login(User user, SqlSession sqlSession) { //todo redo to primary key for sessions table and autoincrementKeys
		getSessionMapper(sqlSession).loginUser(user.getId(), String.valueOf(user.getId()),
				new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public void logout(String sessionString) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			try {
				Integer userId = getSessionMapper(sqlSession).getSessionID(sessionString);
				if(userId == null) {
					UserSessionNotFound userSessionNotFound = new UserSessionNotFound();
					logger.info(userSessionNotFound.toString());
					throw new ApplicationSecurityException(userSessionNotFound);
				}
				getSessionMapper(sqlSession).logout(sessionString);
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
		}
	}

	@Override
	public int getUserId(String sessionString) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			Integer userId = getSessionMapper(sqlSession).getSessionID(sessionString);
			if(userId == null) {
				InvalidSessionError invalidSessionError = new InvalidSessionError();
				logger.info(invalidSessionError.toString());
				throw new ApplicationSecurityException(invalidSessionError);
			}
			return userId;
		}
	}
}
