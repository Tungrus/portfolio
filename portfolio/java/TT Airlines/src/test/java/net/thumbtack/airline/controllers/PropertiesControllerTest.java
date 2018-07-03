package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.context.users.ClientTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.services.*;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {PropertiesController.class, ClientController.class, DebugController.class, AdminController.class})
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
		PropertyService.class})
public class PropertiesControllerTest {
	private final String dropTablesURL = "/api/debug/clear";
	private final String settingsURL = "/api/settings";

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;

	private AdminTestContext adminTestContext = new AdminTestContext();
	private ClientTestContext clientTestContext = new ClientTestContext();

	@Before
	public void resetData() throws Exception {
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
		adminTestContext.resetContext();
		adminTestContext.registrateAdmin(mvc, mapper);
		clientTestContext.resetContext();
		clientTestContext.registrateClient(mvc, mapper);

	}

	@Test
	public void getResponse() throws Exception {
		mvc.perform(get(settingsURL).cookie(adminTestContext.getActiveSessions().get(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mvc.perform(get(settingsURL).cookie(clientTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mvc.perform(get(settingsURL)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

	}

}