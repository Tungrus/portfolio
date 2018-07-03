package net.thumbtack.airline.controllers;

import net.thumbtack.airline.AirlineApplication;
import net.thumbtack.airline.daoimpl.*;
import net.thumbtack.airline.services.*;
import net.thumbtack.airline.utils.MyBatisUtils;
import net.thumbtack.airline.validator.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {OrderController.class, AdminController.class, DebugController.class, ClientController.class,
		FlightController.class, BadURLController.class})
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
public class BadURLControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private String url1 = "";
	private String url2 = "/";

	@Test
	public void handle() throws Exception {
		mockMvc.perform(post(url1)).andExpect(status().isOk());
		mockMvc.perform(get(url1)).andExpect(status().isOk());
		mockMvc.perform(put(url1)).andExpect(status().isOk());
		mockMvc.perform(delete(url1)).andExpect(status().isOk());

		mockMvc.perform(post(url2)).andExpect(status().isOk());
		mockMvc.perform(get(url2)).andExpect(status().isOk());
		mockMvc.perform(put(url2)).andExpect(status().isOk());
		mockMvc.perform(delete(url2)).andExpect(status().isOk());
	}

}