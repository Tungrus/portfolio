package net.thumbtack.airline.validator;

import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.errors.ErrorCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoValidator extends BaseValidator {

	@Autowired
	private PassengerDtoValidator passengerDtoValidator;
	@Autowired
	private OrderParamsDtoValidator orderParamsDtoValidator;

	public void validate(OrderDto orderDto, ErrorCollection errorCollection) {
		passengerDtoValidator.validate(orderDto.getPassengersDto(), errorCollection);
		orderParamsDtoValidator.validate(orderDto.getOrderParamsDto(), errorCollection);
	}
}
