package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class FollowUnitTest {

	@Test
	public void followModelGettersAndSettersTest() {
		Follow follwo = new Follow();
		follwo.setUserId(1);
		follwo.setUserToFollow(2);
		
		assertEquals(1, follwo.getUserId());
		
		assertEquals(2, follwo.getUserToFollow());
	}

}
