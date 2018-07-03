package net.thumbtack.airline.transformers.model;

import net.thumbtack.airline.dto.ClientDto;
import net.thumbtack.airline.dto.ClientParamsDto;
import net.thumbtack.airline.dto.ClientUpdateDto;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.model.*;

public class ModelUserTransformer {
	public static User create(UserDto userDto, UserType userType) {
		return new User(0, userDto.getLogin(), userDto.getPassword(), userType);
	}

	public static AdminParams create(String position, NameParams nameParams) {
		return new AdminParams(nameParams.getFirstName(), nameParams.getLastName(), nameParams.getPatronymic(), position);
	}

	public static Client create(ClientDto clientDto) {
		return new Client(0, clientDto.getLogin(), clientDto.getPassword(), create(clientDto.getClientParamsDto()));
	}

	public static ClientParams create(ClientParamsDto clientParamsDto) {
		return new ClientParams(clientParamsDto.getFirstName(), clientParamsDto.getLastName(),
				clientParamsDto.getPatronymic(), clientParamsDto.getEmail(), clientParamsDto.getPhoneNumber());
	}

	public static ClientParams create(String phoneNuber, String email, NameParams nameParams) {
		return new ClientParams(nameParams.getFirstName(), nameParams.getLastName(), nameParams.getPatronymic(),
				email, phoneNuber);
	}

	public static Client create(User user, ClientParams clientParams) {
		return new Client(user.getId(), user.getLogin(), user.getPassword(), clientParams);
	}

	public static Client create(ClientUpdateDto clientUpdateDto, Client baseClient) {
		return new Client(baseClient.getId(), baseClient.getLogin(), clientUpdateDto.getNewPassword(),
				create(clientUpdateDto.getClientParamsDto()));
	}
}
