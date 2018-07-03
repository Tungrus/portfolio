package net.thumbtack.airline.context.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.AdminParams;
import net.thumbtack.airline.transformers.dto.AdminRegistrationRequestTransformer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminTestContext extends SessionContext {
	private final String adminRegistrationURL = "/api/admin";
	private final String cookieName = "JavaSessionID";

	private String login = "admin"; //TODO add map with deferent users as values
	private String password = "1234";
	private String name = "admin";
	private String lastName = "BBBB";
	private String patronymic = null;
	private String position = "admin";
	private AdminParams adminParams = new AdminParams(name, lastName, patronymic, position);
	private Admin admin = new Admin(0, login, password, adminParams);

	public AdminTestContext() {
		super();
		login = "admin";
		password = "1234";
		name = "admin";
		lastName = "BBBB";
		patronymic = null;
		position = "admin";
		adminParams = new AdminParams(name, lastName, patronymic, position);
		admin = new Admin(0, login, password, adminParams);
	}

	public void resetContext() {
		login = "AAAA";
		password = "BBBB";
		name = "AAAA";
		lastName = "BBBB";
		patronymic = null;
		position = "admin";
		adminParams = new AdminParams(name, lastName, patronymic, position);
		admin = new Admin(0, login, password, adminParams);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public AdminParams getAdminParams() {
		return adminParams;
	}

	public void setAdminParams(AdminParams adminParams) {
		this.adminParams = adminParams;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public MvcResult registrateAdmin(MockMvc mvc, ObjectMapper mapper) throws Exception {
		String adminRegistrationRequestJson = mapper.writeValueAsString(
				AdminRegistrationRequestTransformer.create(admin));
		MvcResult mvcResult = mvc.perform(post(adminRegistrationURL).
				contentType(MediaType.APPLICATION_JSON).
				content(adminRegistrationRequestJson)).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
				andReturn();
		super.setCookie(mvcResult.getResponse().getCookie(cookieName));
		return mvcResult;
	}

}
