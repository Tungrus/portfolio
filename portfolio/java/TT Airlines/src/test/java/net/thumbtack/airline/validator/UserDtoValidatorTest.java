package net.thumbtack.airline.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Import({UserDtoValidator.class})
public class UserDtoValidatorTest {
	@Autowired
	private UserDtoValidator validator;

	@Test
	public void validate() throws Exception {
	}

}