package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.DepartureDao;
import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.OrdersDao;
import net.thumbtack.airline.dto.DepartureRegistrationDto;
import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.dto.response.DepartureClientRegistrationResponse;
import net.thumbtack.airline.dto.response.GetFreePlacesForRegistrationResponse;
import net.thumbtack.airline.errors.types.runtime.TooManyPlacesError;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.BadPlaceException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.model.PlaceInPlane;
import net.thumbtack.airline.model.PlaneInfo;
import net.thumbtack.airline.transformers.dto.DepartureClientRegistrationResponseTransformer;
import net.thumbtack.airline.transformers.dto.GetFreePlacesForRegistrationResponseTransformer;
import net.thumbtack.airline.transformers.model.ModelDepartureRegistrationTransformer;
import net.thumbtack.airline.transformers.model.ModelPlaceInPlaneTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class DepartureClientRegistrationService {
	@Autowired
	private DepartureDao departureDao;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private FlightDao flightDao;


	public DepartureClientRegistrationResponse register(DepartureRegistrationDto registrationDto) throws DataNotFoundException, BadPlaceException, ApplicationSecurityException {
		 return DepartureClientRegistrationResponseTransformer.create(departureDao.register(
				ModelDepartureRegistrationTransformer.create(registrationDto)));
	}

	public GetFreePlacesForRegistrationResponse getFreePlacesForRegistration(int orderId) throws DataNotFoundException {
		Set<Integer> orderIds = ordersDao.getAllOrderIdOnDeparture(orderId);
		Set<PlaceInPlane> placeInPlanes = new HashSet<>();
		for(int id : orderIds) {
			 placeInPlanes.addAll(departureDao.getPlacesByOrder(id));
		}
		PlaneInfo planeInfo = ordersDao.getPlaneInfoBy(orderId);
		Set<PlaceInPlane> freePlaces = ModelPlaceInPlaneTransformer.getFreePlacesInPlane(placeInPlanes, planeInfo);
		return GetFreePlacesForRegistrationResponseTransformer.create(freePlaces);
	}

	public boolean isFreeSpacesExist(int orderId, OrderDto orderDto) throws Exception {
		FlightInfo flightInfo = flightDao.getFlightInfo(orderDto.getOrderParamsDto().getFlightId());
		int flightDateId = flightDao.getFlightDateIdByDateAndFlightId(flightInfo.getId(), orderDto.getOrderParamsDto().getDate());
		ordersDao.getOrderIdsByFlightDateId(flightDateId);

		Set<Integer> orderIds = ordersDao.getAllOrderIdOnDeparture(orderId);
		Set<PlaceInPlane> placeInPlanes = new TreeSet<>();
		for(int id : orderIds) {
			placeInPlanes.addAll(departureDao.getPlacesByOrder(id));
		}
		PlaneInfo planeInfo = ordersDao.getPlaneInfoBy(orderId);
		Set<PlaceInPlane> freePlaces = ModelPlaceInPlaneTransformer.getFreePlacesInPlane(placeInPlanes, planeInfo);
		if(freePlaces.size() != 0) {
			return true;
		} else {
			throw new BadPlaceException(new TooManyPlacesError());
		}
	}
}
