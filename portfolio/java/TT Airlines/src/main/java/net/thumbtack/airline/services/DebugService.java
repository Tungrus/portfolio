package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.response.DebugResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DebugService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDAO;
	@Autowired
	private FlightDao flightDao;
	@Autowired
	private DebugService debugService;

	public DebugResponse cleanDatabase() {
		userDAO.dropTable();
		flightDao.dropTable();
		return new DebugResponse();
	}
}
