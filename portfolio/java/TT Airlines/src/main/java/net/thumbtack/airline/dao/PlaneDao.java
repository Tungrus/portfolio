package net.thumbtack.airline.dao;

import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.PlaneInfo;

import java.util.List;

public interface PlaneDao {
	public List<PlaneInfo> getPlanes();

	public int getPlaneIdByName(String typePlaneName) throws DataNotFoundException;

	public int getPlaneIdByName(Flight flight) throws DataNotFoundException;

	public PlaneInfo getPlanes(Flight flight) throws DataNotFoundException;

	public void dropTable();

	public PlaneInfo getPlaneInfoByPlaneName(String planeName) throws DataNotFoundException;
}
