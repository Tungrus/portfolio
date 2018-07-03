package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.AdminParamsDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AdminParamsDtoValidator extends BaseValidator {
	@Autowired
	private NameParamsDtoValidator nameParamsDtoValidator;

	@Value("${validator.position.regexp}")
	private String positionRegexp;

	private String positionFieldName = "position";

	public void validate(AdminParamsDto adminParams, ErrorCollection errorCollection) {
		Pattern positionPattern = Pattern.compile(positionRegexp);
		fieldCheck(adminParams.getPositiont(), positionFieldName, positionPattern, errorCollection);
		nameParamsDtoValidator.validate(adminParams, errorCollection);
	}
}
