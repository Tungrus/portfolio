package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.services.*;
import net.thumbtack.airline.transformers.dto.AdminRegistrationRequestTransformer;
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

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {PlaneController.class, AdminController.class, DebugController.class,})
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
		PlaneDaoImpl.class, FlightDaoImpl.class, PlaneService.class})
public class PlaneControllerTest {
	private final String adminGetPlanesPath = "/api/flights";
	private final String dropTablesURL = "/api/debug/clear";
	private final String adminRegistrationURL = "/api/admin";

	private final String cookieName = "JavaSessionID";

	private AdminTestContext adminTestContext = new AdminTestContext();

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	private Cookie registerAdmin() throws Exception {
		String adminDTOJSON = mapper.writeValueAsString(
				AdminRegistrationRequestTransformer.create(adminTestContext.getAdmin()));
		MvcResult result = mvc.perform(post(adminRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(adminDTOJSON)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		return result.getResponse().getCookie(cookieName);
	}

	@Before
	public void resetState() throws Exception {
		adminTestContext.resetContext();
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
		adminTestContext.setCookie(registerAdmin());
	}

	@Test
	public void getPlanes() throws Exception {
		mvc.perform(get(adminGetPlanesPath).cookie(adminTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}