package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.AdminGetFlightResponse;
import net.thumbtack.airline.dto.response.ClientGetFlightResponse;
import net.thumbtack.airline.dto.response.GetFlightsByQueryResponse;

import java.util.LinkedList;
import java.util.List;

public class GetFlightQueryAdminResponseTransformer {
	public static GetFlightsByQueryResponse create(List<AdminGetFlightResponse> flightList) {
		List<ClientGetFlightResponse> list = new LinkedList<>();
		for (AdminGetFlightResponse flight : flightList) {
			list.add(flight);
		}
		return new GetFlightsByQueryResponse(list);
	}
}
