package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.errors.types.runtime.BadUserIdError;
import net.thumbtack.airline.errors.types.runtime.DataNotFoundError;
import net.thumbtack.airline.errors.types.runtime.RegistrationError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.mappers.AdminMapper;
import net.thumbtack.airline.mappers.AdminParamsMapper;
import net.thumbtack.airline.mappers.NameParamsMapper;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.AdminParams;
import net.thumbtack.airline.model.User;
import net.thumbtack.airline.transformers.model.ModelClientTransformer;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public class AdminDaoImpl extends UserDaoImpl implements AdminDao {
	private final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

	public AdminDaoImpl() {
		super();
	}

	protected AdminParamsMapper getAdminParamsMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(AdminParamsMapper.class);
	}

	protected NameParamsMapper getNameParamsMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(NameParamsMapper.class);
	}

	protected AdminMapper getAdminMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(AdminMapper.class);
	}

	@Override
	public Admin register(Admin admin) throws Exception {
		try (SqlSession sqlSession = getSession()) {
			try {
				getUserMapper(sqlSession).insert(admin);
				getAdminParamsMapper(sqlSession).insert(admin.getAdminParams(), admin.getId());
				getNameParamsMapper(sqlSession).insert(admin.getAdminParams(), admin.getId());
			} catch (PersistenceException ex) {
				logger.error(new RegistrationError("login").toString());
				sqlSession.rollback();
				throw new ApplicationSecurityException(new RegistrationError("login"));
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
			return admin;
		}
	}

	@Override
	public Admin loadUserContext(User user) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			String position = getAdminParamsMapper(sqlSession).getPositionById(user.getId());
			AdminParams adminParams = getAdminParamsMapper(sqlSession).getAdminParamsById(user.getId());
			if(adminParams == null ) {
				BadUserIdError badUserIdError = new BadUserIdError();
				logger.error(badUserIdError.toString());
				throw new ApplicationSecurityException(new BadUserIdError());
			}
			return ModelClientTransformer.create(user, adminParams);
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
			sqlSession.commit();
		}
	}

	@Override
	public AdminParams getAdminById(int userId) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			AdminParams adminParams = getAdminParamsMapper(sqlSession).getAdminParamsById(userId);
			if(adminParams == null) {
				DataNotFoundError dataNotFoundError = new DataNotFoundError("login");
				logger.error(dataNotFoundError.toString());
				throw new DataNotFoundException(dataNotFoundError);
			}
			return adminParams;
		}
	}

	@Override
	public AdminParams updateAdmin(AdminParams adminParams, int userId, String newPassword) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			try {
				getAdminMapper(sqlSession).updateAdmin(adminParams, userId, newPassword);
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
		}
		return getAdminById(userId);
	}

}
