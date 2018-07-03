package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.PlaneInfoDto;
import net.thumbtack.airline.dto.response.FlightsInfoResponse;
import net.thumbtack.airline.model.PlaneInfo;

import java.util.ArrayList;
import java.util.List;

public class FlightsInfoResponseTransformer {
	public static FlightsInfoResponse create(List<PlaneInfo> planeInfos) {
		List result = new ArrayList<PlaneInfoDto>();
		for (PlaneInfo planeInfo : planeInfos) {
			result.add(create(planeInfo));
		}
		return new FlightsInfoResponse(result);
	}

	public static PlaneInfoDto create(PlaneInfo planeInfo) {
		return new PlaneInfoDto(planeInfo.getPlaneNameType(), planeInfo.getBussinesRows(), planeInfo.getEconomyRows(),
				planeInfo.getPlacesInBusinessRow(), planeInfo.getPlacesInEconomyRow());
	}

}
