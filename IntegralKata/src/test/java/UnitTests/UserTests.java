package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;


public class UserTests {

	@Test
	public void userModelGettersAndSettersTest() {
		User user = new User();
		
		user.setUserName("testUserName");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setPassword("abc123");
		
		assertEquals("testUserName", user.getUserName());
		
		assertEquals("firstName", user.getFirstName());
		
		assertEquals("lastName", user.getLastName());
		
		assertEquals("abc123", user.getPassword());
		
	}
	
}
