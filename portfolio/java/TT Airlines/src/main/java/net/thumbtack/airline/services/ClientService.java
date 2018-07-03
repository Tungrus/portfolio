package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.SessionDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.ClientDto;
import net.thumbtack.airline.dto.ClientUpdateDto;
import net.thumbtack.airline.dto.response.ClientUpdateResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.UserResponse;
import net.thumbtack.airline.errors.types.runtime.WrongClientSessionError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.User;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.transformers.dto.ClientUpdateResponseTransformer;
import net.thumbtack.airline.transformers.dto.UserResponseTransformer;
import net.thumbtack.airline.transformers.model.ModelUserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("clientService")
public class ClientService extends UserService {
	@Autowired
	@Qualifier("clientDao")
	private ClientDao clientDao;
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	private SessionDao sessionDao;


	@Autowired
	public ClientService(@Qualifier("clientDao") UserDao userDao, SessionDao sessionDao,
						 @Qualifier("adminDao") AdminDao adminDao, @Qualifier("clientDao") ClientDao clientDao) {
		super(userDao, sessionDao, adminDao, clientDao);
		this.clientDao = clientDao;
		this.userDao = userDao;
		this.sessionDao = sessionDao;
	}

	public UserResponse register(ClientDto clientDto) throws ApplicationSecurityException {
		Client client = ModelUserTransformer.create(clientDto);
		clientDao.register(client);
		return UserResponseTransformer.create(client);
	}

	@Override
	public void deleteAll() {
		clientDao.deleteAll(UserType.CLIENT.toString());
	}

	public ClientUpdateResponse updateClient(JavaSessionID javaSessionID, ClientUpdateDto clientUpdaterDTO) throws ApplicationSecurityException, DataNotFoundException {
		Client client = getClientBySession(javaSessionID);
		client = ModelUserTransformer.create(clientUpdaterDTO, client);
		clientDao.updateClient(client);
		return ClientUpdateResponseTransformer.create(clientDao.getClientByID(client.getId()));
	}

	public int isClientSession(JavaSessionID javaSessionID) throws ApplicationSecurityException {
		int userId = sessionDao.getUserId(javaSessionID.getToken());
		User user = userDao.getUser(userId);
		if (user.getUserType().equals(UserType.CLIENT)) {
			return sessionDao.getUserId(javaSessionID.getToken());
		} else {
			throw new ApplicationSecurityException(new WrongClientSessionError("session"));
		}
	}
}
