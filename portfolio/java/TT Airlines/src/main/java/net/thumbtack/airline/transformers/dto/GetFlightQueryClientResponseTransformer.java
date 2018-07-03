package net.thumbtack.airline.transformers.dto;

import net.thumbtack.airline.dto.response.ClientGetFlightResponse;
import net.thumbtack.airline.dto.response.GetFlightsByQueryResponse;

import java.util.List;

public class GetFlightQueryClientResponseTransformer {
	public static GetFlightsByQueryResponse create(List<ClientGetFlightResponse> list) {
		return new GetFlightsByQueryResponse(list);
	}
}
