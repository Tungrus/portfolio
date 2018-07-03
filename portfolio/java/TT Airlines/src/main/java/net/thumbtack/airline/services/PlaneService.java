package net.thumbtack.airline.services;

import net.thumbtack.airline.dao.PlaneDao;
import net.thumbtack.airline.dto.response.GetPlanesResponse;
import net.thumbtack.airline.transformers.dto.GetPlanesResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaneService {
	@Autowired
	private PlaneDao planeDao;

	@Autowired
	public PlaneService(PlaneDao planeDao) {
		this.planeDao = planeDao;
	}

	public GetPlanesResponse getPlanes() {
		return GetPlanesResponseTransformer.create(planeDao.getPlanes());
	}
}
