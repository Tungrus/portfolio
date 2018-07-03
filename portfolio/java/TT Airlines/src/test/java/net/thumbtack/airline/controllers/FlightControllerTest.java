package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.flights.FlightQueryTestContext;
import net.thumbtack.airline.context.flights.FlightTestContext;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.requests.FlightsByQueryRequest;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.ClientService;
import net.thumbtack.airline.services.DebugService;
import net.thumbtack.airline.services.FlightService;
import net.thumbtack.airline.transformers.dto.ChangeFlightRequestTransformer;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {FlightController.class, AdminController.class, DebugController.class,})
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
		PlaneDaoImpl.class, FlightDaoImpl.class})
public class FlightControllerTest {
	private final String dropTablesURL = "/api/debug/clear";
	private final String changeFlightURL = "/api/flights/";
	private final String deleteFlightURL = "/api/flights/";
	private final String getFlightsListURL = "/api/flights";
	private final String getFlightURL = "/api/flights/";
	private final String approveFlightURL = "/api/flights/{flightNumber}/approve";

	private final String cookieName = "JavaSessionID";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	private FlightTestContext flightTestContext = new FlightTestContext();
	private AdminTestContext adminTestContext = new AdminTestContext();
	private FlightQueryTestContext flightQueryTestContext = new FlightQueryTestContext();

	public FlightControllerTest() throws BaseApplicationException {
	}

	@Before
	public void initData() throws Exception {
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
		adminTestContext.registrateAdmin(mvc, mapper);
	}

	@Test
	public void changeFlight() throws Exception {
		flightTestContext.addFlightAndSaveId(mvc, mapper, adminTestContext.getCookie(0));
		String changeFlightRequestJsonString = mapper.writeValueAsString(
				ChangeFlightRequestTransformer.createFlightScheduleOnly(flightTestContext.getDailyFlight()));
		String path = changeFlightURL + String.valueOf(flightTestContext.getActiveId(0)) + "/";
		mvc.perform(put(path).
				contentType(MediaType.APPLICATION_JSON).
				content(changeFlightRequestJsonString).
				cookie(adminTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void deleteFlightResponse() throws Exception {
		flightTestContext.addFlightAndSaveId(mvc, mapper, adminTestContext.getCookie(0));
		String path = deleteFlightURL + String.valueOf(flightTestContext.getActiveId(0)) + "/";
		mvc.perform(delete(path).
				cookie(adminTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void getFlight() throws Exception {
		flightTestContext.addFlightAndSaveId(mvc, mapper, adminTestContext.getCookie(0));
		String path = getFlightURL + String.valueOf(flightTestContext.getActiveId(0)) + "/";
		mvc.perform(get(path).cookie(adminTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void approveFlight() throws Exception {
		flightTestContext.addFlightAndSaveId(mvc, mapper, adminTestContext.getCookie(0));
		String path = "/api/flights/" + String.valueOf(flightTestContext.getActiveId(0)) + "/approve";
		mvc.perform(put(path).cookie(adminTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void getFlightsByQuery() throws Exception {
		flightTestContext.addFlight(mvc, mapper, adminTestContext.getCookie(0));

		String queryRequestJson = mapper.writeValueAsString(new FlightsByQueryRequest(
				flightQueryTestContext.getFlightQueryDTO()));
		mvc.perform(get(getFlightsListURL).cookie(adminTestContext.getCookie(0)).
				contentType(MediaType.APPLICATION_JSON).
				content(queryRequestJson)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void addFlight() throws Exception {
		flightTestContext.addFlight(mvc, mapper, adminTestContext.getCookie(0));
	}
}