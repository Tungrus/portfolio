package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.BadPlaceException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Departure;
import net.thumbtack.airline.model.PlaceInPlane;

import java.util.Set;

public interface DepartureDao {
	public Departure register(Departure departure) throws DataNotFoundException, BadPlaceException, ApplicationSecurityException;

	public Set<PlaceInPlane> getPlacesByOrder(int orderId) throws DataNotFoundException;
}
