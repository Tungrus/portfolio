package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.errors.types.runtime.BadUserIdError;
import net.thumbtack.airline.errors.types.runtime.DataNotFoundError;
import net.thumbtack.airline.errors.types.runtime.RegistrationError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.mappers.ClientMapper;
import net.thumbtack.airline.mappers.ClientParamsMapper;
import net.thumbtack.airline.mappers.NameParamsMapper;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.ClientParams;
import net.thumbtack.airline.model.NameParams;
import net.thumbtack.airline.model.User;
import net.thumbtack.airline.transformers.model.ModelUserTransformer;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clientDao")
public class ClientDaoImpl extends UserDaoImpl implements ClientDao {
	private final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);

	public ClientDaoImpl() {
	}

	protected ClientParamsMapper getClientParamsMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(ClientParamsMapper.class);
	}

	protected NameParamsMapper getNameParamsMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(NameParamsMapper.class);
	}

	protected ClientMapper getClientMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(ClientMapper.class);
	}

	@Override
	public Client register(Client client) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			try {
				getUserMapper(sqlSession).insert(client);
				getNameParamsMapper(sqlSession).insert(client.getClientParams(), client.getId());
				getClientParamsMapper(sqlSession).insert(client.getClientParams(), client.getId());
			} catch (PersistenceException ex) {
				sqlSession.rollback();
				RegistrationError registrationError = new RegistrationError("login");
				logger.error(registrationError.toString());
				throw new ApplicationSecurityException(registrationError);
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
			return client;
		}
	}

	@Override
	public Client loadUserContext(User user) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			try {
				ClientParams params;
				String phoneNumber = getClientParamsMapper(sqlSession).getPhoneNumberById(user.getId());
				if(phoneNumber == null) {
					DataNotFoundError dataNotFoundError = new DataNotFoundError("userId");
					logger.info(dataNotFoundError.toString());
					throw new DataNotFoundException(dataNotFoundError);
				}
				String email = getClientParamsMapper(sqlSession).getEmailById(user.getId());
				if(email == null) {
					DataNotFoundError dataNotFoundError = new DataNotFoundError("userId");
					logger.info(dataNotFoundError.toString());
					throw new DataNotFoundException(dataNotFoundError);
				}
				NameParams nameParams = getNameParamsMapper(sqlSession).getNameParamsById(user.getId());
				if(nameParams == null) {
					DataNotFoundError dataNotFoundError = new DataNotFoundError("userId");
					logger.info(dataNotFoundError.toString());
					throw new DataNotFoundException(dataNotFoundError);
				}
				params = ModelUserTransformer.create(phoneNumber, email, nameParams);
				return ModelUserTransformer.create(user, params);
			} catch (RuntimeException e) {
				logger.error(e.toString());
				throw e;
			}
		}
	}

	@Override
	public List<Client> getClients() {
		try (SqlSession sqlSession = getSession()) {
			return getClientParamsMapper(sqlSession).getClients();
		} catch (Exception e){
			logger.error(e.toString());
			throw e;
		}
	}

	@Override
	public void updateClient(Client client) throws DataNotFoundException {
		try (SqlSession sqlSession = getSession()) {
			try {
				int countOfChangedFields = getClientMapper(sqlSession).updateClient(client);
				if(countOfChangedFields <= 1) {
					DataNotFoundError dataNotFoundError = new DataNotFoundError("userId");
					logger.info(dataNotFoundError.toString());
					throw new DataNotFoundException(dataNotFoundError);

				}
			} catch (RuntimeException e) {
				sqlSession.rollback();
				logger.error(e.toString());
				throw e;
			}
			sqlSession.commit();
		}
	}

	@Override
	public Client getClientByID(int userId) throws ApplicationSecurityException {
		try (SqlSession sqlSession = getSession()) {
			Client client = getClientMapper(sqlSession).getClientById(userId);
			if(client == null) {
				BadUserIdError badUserIdError = new BadUserIdError();
				logger.info(badUserIdError.toString());
				throw new ApplicationSecurityException(badUserIdError);
			}
			return client;
		}
	}
}

