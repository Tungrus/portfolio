package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.PlaneInfoDto;
import net.thumbtack.airline.model.PlaneInfo;

public class ModelPlaneTransformer {
	public static PlaneInfo create(String planeName) {
		return new PlaneInfo(planeName);
	}

	public static PlaneInfo create(PlaneInfoDto planeInfoDto) {
		return new PlaneInfo(0, planeInfoDto.getTypePlaneName(), planeInfoDto.getBussinesRows(),
				planeInfoDto.getEconomyRows(), planeInfoDto.getPlacesInBusinessRow(),
				planeInfoDto.getPlacesInEconomyRow());
	}

}
