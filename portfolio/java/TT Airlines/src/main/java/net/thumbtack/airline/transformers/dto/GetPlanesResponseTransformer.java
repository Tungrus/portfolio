package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.PlaneInfoDto;
import net.thumbtack.airline.dto.response.GetPlanesResponse;
import net.thumbtack.airline.model.PlaneInfo;

import java.util.LinkedList;
import java.util.List;

public class GetPlanesResponseTransformer {
	public static GetPlanesResponse create(List<PlaneInfo> planeInfos) {
		List<PlaneInfoDto> planeInfoDtoList = new LinkedList<>();
		for (PlaneInfo planeInfo : planeInfos) {
			planeInfoDtoList.add(PlaneInfoDtoTransformer.create(planeInfo));
		}
		return new GetPlanesResponse(planeInfoDtoList);
	}


}
