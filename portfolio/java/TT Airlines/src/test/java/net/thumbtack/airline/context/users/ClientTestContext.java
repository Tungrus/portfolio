package net.thumbtack.airline.context.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.ClientParams;
import net.thumbtack.airline.transformers.dto.ClientRegistrationRequestTransformer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientTestContext extends SessionContext {

	private final String clientRegistrationURL = "/api/client";
	private final String cookieName = "JavaSessionID";

	private Map<String, Client> clientMap = new TreeMap<>();
	private final String normalClient = "NormalUser";

	private final String loginNormal = "client";
	private final String passwordNormal = "1234";
	private final String nameNormal = "client";
	private final String lastNameNormal = "BBBB";
	private final String patronymicNull = null;
	private final String phoneNumberNormal = "+7-913-627-16-15";
	private final String emailNormal = "pervishkodenis@gmail.com";

	public ClientTestContext() {
		super();
		Client normalUser = new Client(0, loginNormal, passwordNormal, new ClientParams(nameNormal, lastNameNormal,
				patronymicNull, emailNormal, phoneNumberNormal));
		clientMap.put(normalClient, normalUser);
	}

	public Client getNormalClient() {
		return clientMap.get(normalClient);
	}

	public String getLoginNormal() {
		return loginNormal;
	}

	public String getPasswordNormal() {
		return passwordNormal;
	}

	public String getNameNormal() {
		return nameNormal;
	}

	public String getLastNameNormal() {
		return lastNameNormal;
	}

	public String getPatronymicNull() {
		return patronymicNull;
	}

	public String getPhoneNumberNormal() {
		return phoneNumberNormal;
	}

	public String getEmailNormal() {
		return emailNormal;
	}

	public void resetContext() {
		Client normalUser = new Client(0, loginNormal, passwordNormal, new ClientParams(nameNormal, lastNameNormal,
				patronymicNull, emailNormal, phoneNumberNormal));
		clientMap.put(normalClient, normalUser);
	}

	public MvcResult registrateNormalClient(MockMvc mvc, String clientRegistrationRequest) throws Exception {
		MvcResult result = mvc.perform(post(clientRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(clientRegistrationRequest)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
		setCookie(result.getResponse().getCookie(cookieName));
		return result;
	}

	public MvcResult registrateClient(MockMvc mvc, ObjectMapper mapper) throws Exception {
		String requestString = mapper.writeValueAsString(ClientRegistrationRequestTransformer.create(getNormalClient()));
		return registrateNormalClient(mvc, requestString);
	}
}
