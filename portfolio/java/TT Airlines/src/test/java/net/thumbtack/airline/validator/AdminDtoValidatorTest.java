package net.thumbtack.airline.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Import({NameParamsDtoValidator.class, AdminParamsDtoValidator.class, AdminDtoValidator.class, UserDtoValidator.class})
public class AdminDtoValidatorTest {
	private AdminDtoValidator adminDtoValidator1 = new AdminDtoValidator();
	@Autowired
	private AdminDtoValidator adminDtoValidator;

	@Test
	public void validate() throws Exception {
	}

}