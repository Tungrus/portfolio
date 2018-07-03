package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightInfo;
import net.thumbtack.airline.querties.FlightsQuery;

import java.sql.Date;
import java.util.List;

public interface FlightDao {
	public Flight addFlight(Flight flight) throws DataNotFoundException;

	public Flight changeFlight(Flight flight) throws DataNotFoundException;

	public void deleteFlight(int flightId) throws DataNotFoundException;

	public Flight getFlight(int flightId);

	public Flight approveFlight(int flightId) throws DataNotFoundException;

	public List<Flight> getFlightByQuery(FlightsQuery query);

	public FlightInfo getFlightInfo(int flightId) throws ApplicationSecurityException;

	public void dropTable();

	public int getFlightDateIdByDateAndFlightId(int flightId, Date date) throws DataNotFoundException;
}
