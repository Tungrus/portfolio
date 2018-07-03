package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.PlaneDao;
import net.thumbtack.airline.dao.SessionDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.DateDto;
import net.thumbtack.airline.dto.FlightInfoDto;
import net.thumbtack.airline.dto.FlightsQueryDto;
import net.thumbtack.airline.dto.response.*;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.PlaneInfo;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.transformers.dto.*;
import net.thumbtack.airline.transformers.model.ModelFlightTransformer;
import net.thumbtack.airline.transformers.model.ModelGetFlightsQueryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FlightService {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private FlightDao flightDao;

	@Autowired
	private PlaneDao planeDao;

	@Autowired
	public FlightService(@Qualifier("userDao") UserDao userDao, SessionDao sessionDao, FlightDao flightDao,
						 PlaneDao planeDao) {
		this.userDao = userDao;
		this.sessionDao = sessionDao;
		this.flightDao = flightDao;
		this.planeDao = planeDao;
	}

	public FlightsInfoResponse getFlightsInfo(JavaSessionID javaSessionID) {
		return FlightsInfoResponseTransformer.create(planeDao.getPlanes());
	}

	public AddFlightResponse addFlight(FlightInfoDto flightInfoDto, DateDto dateDto, String planeName) throws BaseApplicationException {
		PlaneInfo planeInfo = planeDao.getPlaneInfoByPlaneName(planeName);
		return AddFlightResponseTransformer.create(flightDao.addFlight(ModelFlightTransformer.create(flightInfoDto, dateDto,
				planeInfo)));
	}

	public ChangeFlightResponse changeFlight(FlightInfoDto flightInfoDto, DateDto dateDto, String planeName,
											 int flightId) throws BaseApplicationException {
		PlaneInfo planeInfo = planeDao.getPlaneInfoByPlaneName(planeName);

		return ChangeFlightResponseTransformer.create(flightDao.changeFlight(
				ModelFlightTransformer.create(flightInfoDto, dateDto, planeInfo, flightId)));
	}

	public DeleteFlightResponse deleteFlight(int flightId) throws DataNotFoundException {
		flightDao.deleteFlight(flightId);
		return new DeleteFlightResponse();
	}

	public AdminGetFlightResponse getFlight(int flightId) {
		return AdminGetFlightResponseTransformer.create(flightDao.getFlight(flightId));
	}

	public ApproveFlightResponse approveFlight(int flightId) throws DataNotFoundException {
		return ApproveFlightResponseTransformer.create(flightDao.approveFlight(flightId));
	}

	public GetFlightsByQueryResponse getFlightsByQuery(FlightsQueryDto queryDTO, JavaSessionID javaSessionID)
			throws Exception {
		int userId = sessionDao.getUserId(javaSessionID.getToken());
		List<Flight> listFlight = flightDao.getFlightByQuery(
				ModelGetFlightsQueryTransformer.create(queryDTO,userDao.getUser(userId).getUserType()));
		if (userDao.getUser(userId).getUserType().equals(UserType.ADMIN)) {
			List<AdminGetFlightResponse> adminGetFlightResponseList = new LinkedList<>();
			for (Flight flight : listFlight) {
				adminGetFlightResponseList.add(AdminGetFlightResponseTransformer.create(flight));
			}
			return GetFlightQueryAdminResponseTransformer.create(adminGetFlightResponseList);
		} else {
			List<ClientGetFlightResponse> clientGetFlightResponses = new LinkedList<>();
			for (Flight flight : listFlight) {
				clientGetFlightResponses.add(AdminGetFlightResponseTransformer.create(flight));
			}
			return GetFlightQueryClientResponseTransformer.create(clientGetFlightResponses);
		}
	}
}
