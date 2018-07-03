package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.users.ClientTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.ClientService;
import net.thumbtack.airline.services.DebugService;
import net.thumbtack.airline.transformers.dto.ClientRegistrationRequestTransformer;
import net.thumbtack.airline.transformers.dto.ClientUpdateRequestTransformer;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ClientController.class, DebugController.class})
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
		ValidatorRunner.class, ValidatorFactory.class, ClientService.class})
public class ClientControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private DebugService debugService;
	private String clientRegistrationURL = "/api/client";
	private String clientUpdateURL = "/api/client";
	private String dropTablesURL = "/api/debug/clear";

	@Autowired
	private ObjectMapper mapper;
	private final String cookieName = "JavaSessionID";
	private ClientTestContext clientTestContext = new ClientTestContext();

	@Before
	public void initUser() throws Exception {
		clientTestContext.resetContext();
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
	}

	@Test
	public void registerClient() throws Exception {
		String userRequest = mapper.writeValueAsString(
				ClientRegistrationRequestTransformer.create(clientTestContext.getNormalClient()));
		MvcResult mvcResult = mvc.perform(post(clientRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(userRequest)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
		clientTestContext.setCookie(mvcResult.getResponse().getCookie(cookieName));
	}

	@Test
	public void changeParamsClientTest() throws Exception {
		String userRequest = mapper.writeValueAsString(
				ClientRegistrationRequestTransformer.create(clientTestContext.getNormalClient()));
		MvcResult mvcResult = mvc.perform(post(clientRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(userRequest)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
		clientTestContext.setCookie(mvcResult.getResponse().getCookie(cookieName));

		String updateRequestString = mapper.writeValueAsString(
				ClientUpdateRequestTransformer.create(clientTestContext.getNormalClient(), "newPassword"));

		mvc.perform(put(clientUpdateURL).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(updateRequestString).cookie(clientTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
}