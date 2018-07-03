package net.thumbtack.airline.controllers;

import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.dto.OrderQueryDto;
import net.thumbtack.airline.dto.requests.AddOrderRequest;
import net.thumbtack.airline.dto.requests.OrderQueryRequest;
import net.thumbtack.airline.dto.response.AddOrderResponse;
import net.thumbtack.airline.dto.response.JavaSessionID;
import net.thumbtack.airline.dto.response.OrderQueryResponse;
import net.thumbtack.airline.exception.AddOrderException;
import net.thumbtack.airline.exception.ApplicationSecurityException;
import net.thumbtack.airline.exception.DataNotFoundException;
import net.thumbtack.airline.exception.ValidationException;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.services.ClientService;
import net.thumbtack.airline.services.OrderService;
import net.thumbtack.airline.services.UserService;
import net.thumbtack.airline.validator.ValidatorRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("production")
public class OrderController {
	private final static String addOrderURL = "/api/orders";
	private final static String getOrderQueryURL = "/api/orders";

	private final static String cookieName = "JavaSessionID";

	@Autowired
	private ValidatorRunner validatorRunner;

	@Autowired
	private OrderService orderService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("clientService")
	private ClientService clientService;

	@PostMapping(value = addOrderURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AddOrderResponse addOrder(@CookieValue(cookieName) String javaSessionId,
									 @RequestBody AddOrderRequest addOrderRequest) throws ValidationException, ApplicationSecurityException, DataNotFoundException, AddOrderException {
		OrderDto orderDto = addOrderRequest.getOrderDto();
		validatorRunner.run(orderDto);
		int userId = clientService.isClientSession(new JavaSessionID(javaSessionId));
		AddOrderResponse addOrderResponse = orderService.addOrder(userId, orderDto);
		return addOrderResponse;
	}

	@GetMapping(value = getOrderQueryURL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public OrderQueryResponse GetOrderQuery(@CookieValue(cookieName) String javaSessionId,
											@RequestBody OrderQueryRequest orderQueryRequest) throws Exception {
		OrderQueryDto orderQueryDto = orderQueryRequest.getOrderQueryDto();
		validatorRunner.run(orderQueryDto);
		UserType userType = userService.getUserTypeBySession(new JavaSessionID(javaSessionId));
		return orderService.getOrderQueryResponse(orderQueryDto, userType);
	}
}

