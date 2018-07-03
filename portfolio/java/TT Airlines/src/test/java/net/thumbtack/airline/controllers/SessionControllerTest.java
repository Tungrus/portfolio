package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.context.users.ClientTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.ClientService;
import net.thumbtack.airline.services.DebugService;
import net.thumbtack.airline.services.UserService;
import net.thumbtack.airline.transformers.dto.AdminRegistrationRequestTransformer;
import net.thumbtack.airline.transformers.dto.ClientRegistrationRequestTransformer;
import net.thumbtack.airline.transformers.dto.UserLoginRequestTransformer;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {AdminController.class, DebugController.class, ClientController.class,
		SessionController.class})
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
		ValidatorRunner.class, ValidatorFactory.class, ClientService.class, UserService.class})
public class SessionControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;


	private List<String> activeSessions = new ArrayList<>();

	private final String cookieName = "JavaSessionID";
	private final String clientRegistrationURL = "/api/client";
	private final String dropTablesURL = "/api/debug/clear";
	private final String adminRegistrationURL = "/api/admin";
	private final String logoutURL = "/api/session";
	private final String loginURL = "/api/session";
	private final String userInformationURL = "/api/account";
	private final String getClientsURL = "/api/clients";

	private ClientTestContext clientTestContext = new ClientTestContext();
	private AdminTestContext adminTestContext = new AdminTestContext();

	private String registrateClient() throws Exception {
		String userRegistrationRequestJson = mapper.writeValueAsString(
				ClientRegistrationRequestTransformer.create(clientTestContext.getNormalClient()));
		MvcResult result = mvc.perform(post(clientRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(userRegistrationRequestJson)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
		return result.getResponse().getCookie(cookieName).getValue();
	}

	private String registrateAdmin() throws Exception {
		String adminRegistrationRequestJson = mapper.writeValueAsString(
				AdminRegistrationRequestTransformer.create(adminTestContext.getAdmin()));
		MvcResult result = mvc.perform(post(adminRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(adminRegistrationRequestJson)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		return result.getResponse().getCookie(cookieName).getValue();
	}

	private void logout(String sessionString) throws Exception {
		mvc.perform(delete(logoutURL).
				cookie(new Cookie(cookieName, sessionString))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

	}

	@Before
	public void initUser() throws Exception {
		clientTestContext.resetContext();
		adminTestContext.resetContext();
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
		activeSessions.clear();
		activeSessions.add(registrateAdmin());
		activeSessions.add(registrateClient());
	}

	@Test
	public void login() throws Exception {
		this.logout(activeSessions.get(0));
		this.logout(activeSessions.get(1));
		String adminRequestString = mapper.writeValueAsString(
				UserLoginRequestTransformer.create(adminTestContext.getAdmin()));
		String clientRequestString = mapper.writeValueAsString(
				UserLoginRequestTransformer.create(clientTestContext.getNormalClient()));
		MvcResult clientResult = mvc.perform(post(loginURL).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(adminRequestString)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andReturn();
		MvcResult adminResult = mvc.perform(post(loginURL).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(clientRequestString)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andReturn();
	}

	@Test
	public void getUserInformation() throws Exception {
		mvc.perform(get(userInformationURL).cookie(new Cookie(cookieName, activeSessions.get(0)))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mvc.perform(get(userInformationURL).cookie(new Cookie(cookieName, activeSessions.get(1)))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void getUsersData() throws Exception {
		mvc.perform(get(getClientsURL).cookie(new Cookie(cookieName, activeSessions.get(0)))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

	}

	@Test
	public void logout() throws Exception {

		mvc.perform(delete(logoutURL).
				cookie(new Cookie(cookieName, activeSessions.get(0)))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

}