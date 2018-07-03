package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.User;

import java.util.List;

public interface ClientDao {
	public Client register(Client client) throws ApplicationSecurityException;

	public Client loadUserContext(User user) throws DataNotFoundException;

	public Client getClientByID(int userId) throws ApplicationSecurityException;

	public void deleteAll(String user);

	public List<Client> getClients();

	public void updateClient(Client client) throws DataNotFoundException;
}
