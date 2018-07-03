package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.ClientDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoValidator {
	@Autowired
	private ClientParamsDtoValidator clientParamsDtoValidator;
	@Autowired
	private UserDtoValidator userDtoValidator;

	public void validate(ClientDto clientDto, ErrorCollection errorCollection) {
		userDtoValidator.validate(clientDto, errorCollection);
		clientParamsDtoValidator.validate(clientDto.getClientParamsDto(), errorCollection);
	}
}
