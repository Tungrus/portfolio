package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.OrderQueryDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderQueryDtoValidator extends BaseValidator{
	@Autowired
	private QueryDtoValidator queryDtoValidator;

	public void validate(OrderQueryDto query, ErrorCollection errorCollection) {
		queryDtoValidator.validateStringQueryParams(query.getQuery(), errorCollection);
		queryDtoValidator.validateDatesQueryParams(query.getQuery(), errorCollection);
		queryDtoValidator.validateOrderQueryParams(query.getQuery(), errorCollection);
	}
}
