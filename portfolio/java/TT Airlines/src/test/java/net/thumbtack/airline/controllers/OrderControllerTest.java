package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.flights.FlightTestContext;
import net.thumbtack.airline.context.orders.OrderTestContext;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.context.users.ClientTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.requests.OrderQueryRequest;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.querties.OrderQueryParams;
import net.thumbtack.airline.services.*;
import net.thumbtack.airline.transformers.dto.OrderQueryRequestTransformer;
import net.thumbtack.airline.utils.MyBatisUtils;
import net.thumbtack.airline.validator.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {OrderController.class, AdminController.class, DebugController.class, ClientController.class,
		FlightController.class})
@Import({AirlineApplication.class, MyBatisUtils.class, AdminService.class, SessionDaoIml.class,
		UserDaoImpl.class, AdminDaoImpl.class, ClientDaoImpl.class, DebugService.class, FlightDaoImpl.class,
		BaseValidator.class, DateConstrain.class, FromDateToDateConstrain.class, IdConstrain.class,
		NotEmptyFieldConstrain.class, NotNullConstrain.class, PriceConstrain.class, RegexpPropertyConstrain.class,
		TimeConstrain.class, NameParamsDtoValidator.class, ClientParamsDtoValidator.class,
		DepartureRegistrationDtoValidator.class, DateDtoValidator.class, FlightDtoValidator.class,
		FlightInfoDtoValidator.class, OrderDtoValidator.class, OrderParamsDtoValidator.class,
		PassengerDtoValidator.class, PlaceDtoValidator.class, PlaneNameValidator.class, FlightQueryDtoValidator.class,
		OrderQueryDtoValidator.class, QueryDtoValidator.class, AdminParamsDtoValidator.class, AdminDtoValidator.class,
		AdminUpdateDtoValidator.class, ClientDtoValidator.class, ClientUpdateDtoValidator.class, UserDtoValidator.class,
		ValidatorRunner.class, ValidatorFactory.class, ClientService.class, FlightService.class, ClientService.class,
		PlaneDaoImpl.class, FlightDaoImpl.class, OrderService.class, OrdersDaoImpl.class, UserService.class})
public class OrderControllerTest {
	private final String dropTablesURL = "/api/debug/clear";
	private final static String getOrderQueryURL = "/api/orders";

	private OrderTestContext orderTestContext = new OrderTestContext();
	private FlightTestContext flightTestContext = new FlightTestContext();
	private AdminTestContext adminTestContext = new AdminTestContext();
	private ClientTestContext clientTestContext = new ClientTestContext();

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	public OrderControllerTest() throws BaseApplicationException {
	}

	@Before
	public void resetContext() throws Exception {
		orderTestContext.resetTestContext();
		flightTestContext = new FlightTestContext();
		adminTestContext.resetContext();
		clientTestContext.resetContext();
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
		adminTestContext.registrateAdmin(mvc, mapper);
		clientTestContext.registrateClient(mvc, mapper);
		flightTestContext.addFlightAndSaveId(mvc, mapper, adminTestContext.getCookie(0));
		orderTestContext.getNormalOrder().getFlightInfo().setId(flightTestContext.getActiveId(0));
		flightTestContext.approveFlight(mvc, mapper, adminTestContext.getCookie(0));
	}

	@Test
	public void addOrder() throws Exception {
		orderTestContext.registerOrder(mvc, mapper, clientTestContext.getCookie(0));
	}

	@Test
	public void getOrderQuery() throws Exception {
		orderTestContext.registerOrder(mvc, mapper, clientTestContext.getCookie(0));
		Map<String, Object> query = new HashMap<>();
		query.put(OrderQueryParams.getClientId(), orderTestContext.getNormalOrder().getUserId());
		OrderQueryRequest orderQueryRequest = OrderQueryRequestTransformer.create(query);
		String queryRequestString = mapper.writeValueAsString(orderQueryRequest);
		mvc.perform(get(getOrderQueryURL).
				cookie(clientTestContext.getCookie(0)).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(queryRequestString)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}