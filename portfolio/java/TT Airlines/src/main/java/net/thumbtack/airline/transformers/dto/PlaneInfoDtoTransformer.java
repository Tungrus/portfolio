package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.PlaneInfoDto;
import net.thumbtack.airline.model.PlaneInfo;

public class PlaneInfoDtoTransformer {
	public static PlaneInfoDto create(PlaneInfo planeInfo) {
		return new PlaneInfoDto(planeInfo.getPlaneNameType(), planeInfo.getBussinesRows(), planeInfo.getEconomyRows(),
				planeInfo.getPlacesInBusinessRow(), planeInfo.getPlacesInEconomyRow());
	}
}
