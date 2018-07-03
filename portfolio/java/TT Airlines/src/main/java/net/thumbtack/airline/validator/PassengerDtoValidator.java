package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.PassengerDto;
import net.thumbtack.airline.errors.ErrorCollection;
import net.thumbtack.airline.errors.types.validation.RegexPropertyError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerDtoValidator extends BaseValidator {
	private static String typeOfPlaceFieldName = "typeOfPlace";
	private static String passportNumberFieldName = "passportNumber";
	private static String nationalityFieldName = "nationality";

	@Autowired
	private NameParamsDtoValidator nameParamsDtoValidator;

	@Autowired
	private NotEmptyFieldConstrain notEmptyFieldConstrain;

	@Autowired
	private NotNullConstrain notNullConstrain;

	public void validate(PassengerDto passengerDto, ErrorCollection errorCollection) {
		typeOfPlaceChecker(passengerDto.getTypeOfPlace(), errorCollection);
		notEmptyFieldConstrain.check(passengerDto.getPassportNumber(), passportNumberFieldName, errorCollection);
		notNullConstrain.check(passengerDto.getNationality(), nationalityFieldName, errorCollection);
		nameParamsDtoValidator.validateFirstName(passengerDto.getFirstName(), errorCollection);
		nameParamsDtoValidator.validateLastName(passengerDto.getLastName(), errorCollection);
	}

	public void typeOfPlaceChecker(String object, ErrorCollection errorCollection) {
		if(!(object.equals("BUSINESS") || object.equals("ECONOMY"))) {
			errorCollection.addProperty(new RegexPropertyError(typeOfPlaceFieldName));
		}
	}

	public void validate(List<PassengerDto> passengerDtos, ErrorCollection errorCollection) {
		for(PassengerDto passengerDto : passengerDtos) {
			validate(passengerDto, errorCollection);
		}
	}

}
