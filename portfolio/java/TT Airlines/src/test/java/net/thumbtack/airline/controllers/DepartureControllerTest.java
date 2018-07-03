package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.flights.FlightTestContext;
import net.thumbtack.airline.context.orders.OrderTestContext;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.context.users.ClientTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.PlaceDto;
import net.thumbtack.airline.dto.requests.DepartureClientRegistrationRequest;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.services.*;
import net.thumbtack.airline.transformers.dto.DepartureClientRegistrationRequestTransformer;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { AdminController.class, ClientController.class, FlightController.class, OrderController.class,
		DepartureController.class, DebugController.class})
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
		PlaneDaoImpl.class, FlightDaoImpl.class, OrderService.class, OrdersDaoImpl.class, UserService.class,
		DepartureController.class, DepartureClientRegistrationService.class, DepartureDaoImpl.class})

public class DepartureControllerTest {
	private final String dropTablesURL = "/api/debug/clear";

	private final static String departureRegistrationURL = "/api/places";
	private final static String placesForRegistrationURL = "/api/places/{orderId}";

	private ClientTestContext clientTestContext = new ClientTestContext();
	private AdminTestContext adminTestContext = new AdminTestContext();
	private FlightTestContext flightTestContext = new FlightTestContext();
	private OrderTestContext orderTestContext = new OrderTestContext();

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;

	public DepartureControllerTest() throws BaseApplicationException {
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
		orderTestContext.registerOrder(mvc, mapper, clientTestContext.getCookie(0));
	}

	private void register(String placeRepresentation) throws Exception {
		DepartureClientRegistrationRequest departureClientRegistrationRequest = DepartureClientRegistrationRequestTransformer
				.create(orderTestContext.getOrderIdByNumber(0),
						orderTestContext.getPassengersTestContext().getServedPassengerDtos().get(0),
						new PlaceDto(placeRepresentation));
		String requestString = mapper.writeValueAsString(departureClientRegistrationRequest);
		mvc.perform(post(departureRegistrationURL).
				content(requestString).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				cookie(clientTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void registerOnFlight() throws Exception {
		DepartureClientRegistrationRequest departureClientRegistrationRequest = DepartureClientRegistrationRequestTransformer
				.create(orderTestContext.getOrderIdByNumber(0),
				orderTestContext.getPassengersTestContext().getServedPassengerDtos().get(0),
				new PlaceDto("1A"));
		String requestString = mapper.writeValueAsString(departureClientRegistrationRequest);
		mvc.perform(post(departureRegistrationURL).
				content(requestString).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				cookie(clientTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void getFreePlacesForRegistration() throws Exception {
		register("1A");
		MvcResult mvcResult = mvc.perform(get("/api/places/" + orderTestContext.getActiveOrderIds().get(0)).
				cookie(clientTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
		String json = mvcResult.getResponse().getContentAsString();
	}

}