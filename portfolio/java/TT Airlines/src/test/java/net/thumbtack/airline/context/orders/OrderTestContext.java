package net.thumbtack.airline.context.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.context.dates.DatesTestContext;
import net.thumbtack.airline.context.flights.FlightInfoTestContext;
import net.thumbtack.airline.context.passengers.PassengersTestContext;
import net.thumbtack.airline.dto.ServedPassengerDto;
import net.thumbtack.airline.dto.requests.AddOrderRequest;
import net.thumbtack.airline.dto.response.AddOrderResponse;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.OrderParams;
import net.thumbtack.airline.transformers.dto.AddOrderRequestTransformer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderTestContext {
	private final String addOrderURL = "/api/orders";

	private List<Integer> activeOrderIds = new ArrayList<>();
	private DatesTestContext datesTestContext = new DatesTestContext();
	private PassengersTestContext passengersTestContext = new PassengersTestContext();
	private FlightInfoTestContext flightInfoTestContext = new FlightInfoTestContext();
	private Order normalOrder = new Order(0, 0, new OrderParams(0),
			flightInfoTestContext.getFlightInfoDaily(), passengersTestContext.getPassengers(),
			datesTestContext.getFromDate());

	public OrderTestContext() {
		DatesTestContext datesTestContext = new DatesTestContext();
		PassengersTestContext passengersTestContext = new PassengersTestContext();
		FlightInfoTestContext flightInfoTestContext = new FlightInfoTestContext();
		normalOrder = new Order(0, 0, new OrderParams(0),
				flightInfoTestContext.getFlightInfoDaily(), passengersTestContext.getPassengers(),
				datesTestContext.getFromDate());
	}

	public void resetTestContext() {
		datesTestContext = new DatesTestContext();
		passengersTestContext.resetContext();
		flightInfoTestContext = new FlightInfoTestContext();
		normalOrder = new Order(0, 0, new OrderParams(0),
				flightInfoTestContext.getFlightInfoDaily(), passengersTestContext.getPassengers(),
				datesTestContext.getFromDate());
		activeOrderIds = new ArrayList<>();
	}

	public DatesTestContext getDatesTestContext() {
		return datesTestContext;
	}

	public void setDatesTestContext(DatesTestContext datesTestContext) {
		this.datesTestContext = datesTestContext;
	}

	public PassengersTestContext getPassengersTestContext() {
		return passengersTestContext;
	}

	public void setPassengersTestContext(PassengersTestContext passengersTestContext) {
		this.passengersTestContext = passengersTestContext;
	}

	public FlightInfoTestContext getFlightInfoTestContext() {
		return flightInfoTestContext;
	}

	public void setFlightInfoTestContext(FlightInfoTestContext flightInfoTestContext) {
		this.flightInfoTestContext = flightInfoTestContext;
	}

	public Order getNormalOrder() {
		return normalOrder;
	}

	public void setNormalOrder(Order normalOrder) {
		this.normalOrder = normalOrder;
	}

	public MvcResult registerOrder(MockMvc mvc, ObjectMapper mapper, Cookie cookie) throws Exception {
		AddOrderRequest addOrderRequest = AddOrderRequestTransformer.createTest(normalOrder);
		String orderString = mapper.writeValueAsString(addOrderRequest);
		MvcResult mvcResult = mvc.perform(post(addOrderURL).
				cookie(cookie).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(orderString)).
				andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		AddOrderResponse addOrderResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), AddOrderResponse.class);
		activeOrderIds.add(addOrderResponse.getOrderId());
		List<ServedPassengerDto> passengersDTO = addOrderResponse.getOrderDto().getPassengersDto();
		passengersTestContext.setServedPassengerDtos(passengersDTO);
		return mvcResult;
	}

	public String getAddOrderURL() {
		return addOrderURL;
	}

	public List<Integer> getActiveOrderIds() {
		return activeOrderIds;
	}

	public void setActiveOrderIds(List<Integer> activeOrderIds) {
		this.activeOrderIds = activeOrderIds;
	}

	public void addOrderId(Integer integer) {
		activeOrderIds.add(integer);
	}

	public int getOrderIdByNumber(int number) {
		return activeOrderIds.get(number);
	}
}
