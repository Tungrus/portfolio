package net.thumbtack.airline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.dto.response.DebugResponse;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.DebugService;
import net.thumbtack.airline.transformers.dto.AdminRegistrationRequestTransformer;
import net.thumbtack.airline.transformers.dto.AdminUpdateRequestTransformer;
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
@WebMvcTest(controllers = {AdminController.class, DebugController.class})
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
		ValidatorRunner.class, ValidatorFactory.class})
public class AdminControllerTest {
	@Autowired
	private MockMvc mvc;

	private final String adminRegistrationURL = "/api/admin";
	private final String adminUpdateURL = "/api/admin";
	private final String dropTablesURL = "/api/debug/clear";

	@Autowired
	private ObjectMapper mapper;
	private final String cookieName = "JavaSessionID";
	private AdminTestContext adminTestContext = new AdminTestContext();

	@Before
	public void initUser() throws Exception {
		adminTestContext.resetContext();
		String emptyResponse = mapper.writeValueAsString(new DebugResponse());
		mvc.perform(post(dropTablesURL)).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
				andExpect(content().string(emptyResponse));
	}

	@Test
	public void registerTest() throws Exception {
		String adminDTOJSON = mapper.writeValueAsString(
				AdminRegistrationRequestTransformer.create(adminTestContext.getAdmin()));
		MvcResult result = mvc.perform(post(adminRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(adminDTOJSON)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		result.getResponse().getContentAsString();
	}

	@Test
	public void updateAdminTest() throws Exception {
		String adminDTOJSON = mapper.writeValueAsString(
				AdminRegistrationRequestTransformer.create(adminTestContext.getAdmin()));
		MvcResult result = mvc.perform(post(adminRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(adminDTOJSON)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		adminTestContext.setCookie(result.getResponse().getCookie(cookieName));

		String adminUpdateRequest = mapper.writeValueAsString(
				AdminUpdateRequestTransformer.create(adminTestContext.getAdminParams(), "newPassword"));
		mvc.perform(put(adminUpdateURL).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(adminUpdateRequest).cookie(adminTestContext.getCookie(0))).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

	}

}