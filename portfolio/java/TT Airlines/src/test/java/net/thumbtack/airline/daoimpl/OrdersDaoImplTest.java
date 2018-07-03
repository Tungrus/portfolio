package net.thumbtack.airline.daoimpl;

import net.thumbtack.airline.context.flights.FlightTestContext;
import net.thumbtack.airline.context.orders.OrderTestContext;
import net.thumbtack.airline.context.users.AdminTestContext;
import net.thumbtack.airline.dto.requests.AddFlightRequest;
import net.thumbtack.airline.dto.requests.OrderQueryRequest;
import net.thumbtack.airline.dto.response.AddFlightResponse;
import net.thumbtack.airline.dto.response.AdminRegistrationResponse;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.UserType;
import net.thumbtack.airline.querties.OrderQueryParams;
import net.thumbtack.airline.services.AdminService;
import net.thumbtack.airline.services.DebugService;
import net.thumbtack.airline.services.FlightService;
import net.thumbtack.airline.services.OrderService;
import net.thumbtack.airline.transformers.dto.AddFlightRequestTransformer;
import net.thumbtack.airline.transformers.dto.AdminRegistrationRequestTransformer;
import net.thumbtack.airline.transformers.dto.OrderQueryRequestTransformer;
import net.thumbtack.airline.utils.MyBatisUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import({OrdersDaoImpl.class, MyBatisUtils.class, FlightService.class, FlightDaoImpl.class, DebugService.class, UserDaoImpl.class,
	OrderService.class, })
public class OrdersDaoImplTest {
	private OrdersDaoImpl ordersDao = new OrdersDaoImpl();
	private OrderTestContext orderTestContext = new OrderTestContext();
	private FlightTestContext flightTestContext = new FlightTestContext();
	private AdminTestContext adminTestContext = new AdminTestContext();
	@Autowired
	private FlightService flightService;
	@Autowired
	private DebugService debugService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;

	public OrdersDaoImplTest() throws BaseApplicationException {
	}

	@Before
	public void reset() throws Exception {
		debugService.cleanDatabase();
		AdminRegistrationResponse adminRegistrationResponse = adminService.register(
				AdminRegistrationRequestTransformer.create(adminTestContext.getAdmin()).getAdminDto());
		Flight flight = flightTestContext.getDailyFlight();
		AddFlightRequest addFlightRequest = AddFlightRequestTransformer.createFlightScheduleOnly(flight);
		AddFlightResponse addFlightResponse = flightService.addFlight(addFlightRequest.getFlightInfoDto(), addFlightRequest.getDateDto(),
				addFlightRequest.getPlaneName());
		orderTestContext.getNormalOrder().getFlightInfo().setId(addFlightResponse.getFlightId());
		orderTestContext.getNormalOrder().setFlightDate(flightTestContext.getDatesTestContext().getFromDate());
		orderTestContext.getNormalOrder().setUserId(adminRegistrationResponse.getId());
	}

	@Test
	public void addOrder() throws Exception {
		ordersDao.addOrder(orderTestContext.getNormalOrder());
	}

	@Test
	public void getOrdersQuery() throws Exception {
		ordersDao.addOrder(orderTestContext.getNormalOrder());
		Map<String, Object> query = new HashMap<>();
		query.put(OrderQueryParams.getClientId(), orderTestContext.getNormalOrder().getUserId());
		OrderQueryRequest orderQueryRequest = OrderQueryRequestTransformer.create(query);
		orderService.getOrderQueryResponse(orderQueryRequest.getOrderQueryDto(), UserType.CLIENT);
	}

	@Test
	public void getPlaneInfoBy() throws Exception {
	}

	@Test
	public void getFlightDateId() throws Exception {
	}

	@Test
	public void getOrderIdsByFlightDateId() throws Exception {
	}

	@Test
	public void getAllOrderIdOnDeparture() throws Exception {
	}

}