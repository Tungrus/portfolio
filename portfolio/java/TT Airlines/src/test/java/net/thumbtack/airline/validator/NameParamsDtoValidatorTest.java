package net.thumbtack.airline.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Import(NameParamsDtoValidator.class)
public class NameParamsDtoValidatorTest {
	@Autowired
	private NameParamsDtoValidator nameParamsDtoValidator;

	@Test
	public void validate() throws Exception {
		//ErrorCollection errorCollection = new ErrorCollection();
		//nameParamsDtoValidator.validateFirstName("name", errorCollection);//TODO fix strange things
	}

	@Test
	public void validateFirstName() throws Exception {
	}

	@Test
	public void validateLastName() throws Exception {
	}

}