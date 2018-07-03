package net.thumbtack.airline.context.flights;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.context.dates.DatesTestContext;
import net.thumbtack.airline.dto.response.AddFlightResponse;
import net.thumbtack.airline.exception.BaseApplicationException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.transformers.dto.AddFlightRequestTransformer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FlightTestContext {

	private final String addFlightURL = "/api/flights";
	private List<Integer> activeFlightId = new ArrayList<>();
	private DatesTestContext datesTestContext = new DatesTestContext();
	private PlaneTestContext planeTestContext = new PlaneTestContext();
	private FlightInfoTestContext flightInfoTestContext = new FlightInfoTestContext();

	private Flight dailyFlight = new Flight(planeTestContext.getPlane1().getPlaneInfo(),
			flightInfoTestContext.getFlightInfoDaily(),
			datesTestContext.getDatesFromAllPeriod());
	/*private Flight dailyFlight = new Flight(planeTestContext.getPlane2().getPlaneInfo(), flightInfoTestContext.get,
			datesTestContext.getDatesFromAllPeriod());
	private Flight dailyFlight = new Flight(planeTestContext.getPlane3().getPlaneInfo(), flightInfoTestContext.get,
			datesTestContext.getDatesFromAllPeriod());
	private Flight dailyFlight = new Flight(planeTestContext.getPlane4().getPlaneInfo(), flightInfoTestContext.get,
			datesTestContext.getDatesFromAllPeriod());
	private Flight dailyFlight = new Flight(planeTestContext.getPlane5().getPlaneInfo(), flightInfoTestContext.get,
			datesTestContext.getDatesFromAllPeriod());*/

	public FlightTestContext() throws BaseApplicationException {
	}

	public Flight getDailyFlight() {
		return dailyFlight;
	}

	public void setDailyFlight(Flight dailyFlight) {
		this.dailyFlight = dailyFlight;
	}

	public void addActiveId(int id) {
		activeFlightId.add(id);
	}

	public int getActiveId(int n) {
		return activeFlightId.get(n);
	}

	public MvcResult addFlight(MockMvc mvc, ObjectMapper mapper, Cookie adminCookie) throws Exception {
		String flightAddRequest = mapper.writeValueAsString(
				AddFlightRequestTransformer.createFlightScheduleOnly(getDailyFlight()));
		return mvc.perform(post(addFlightURL).cookie(adminCookie).
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
				content(flightAddRequest)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
				andReturn();
	}

	public MvcResult addFlightAndSaveId(MockMvc mvc, ObjectMapper mapper, Cookie adminCookie) throws Exception {
		MvcResult mvcResult = addFlight(mvc, mapper, adminCookie);
		String responseBody = mvcResult.getResponse().getContentAsString();
		AddFlightResponse flightResponse = mapper.readValue(responseBody, AddFlightResponse.class);
		activeFlightId.add(flightResponse.getFlightId());
		return mvcResult;
	}

	public DatesTestContext getDatesTestContext() {
		return datesTestContext;
	}

	public void approveFlight(MockMvc mvc, ObjectMapper mapper, Cookie cookie) throws Exception {
		String path = "/api/flights/" + String.valueOf(getActiveId(0)) + "/approve";
		mvc.perform(put(path).cookie(cookie)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
}
