package UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;


class PostTest {

	@Test
	public void postModelGettersAndSettersTest() {
		Post post = new Post();
		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		post.setTimeStamp(timestamp);
		post.setUserId(1);
		post.setMessage("This is a test message!");

		
		assertEquals("testUserName", post.getTimeStamp());
		
		assertEquals("firstName", post.getUserId());
		
		assertEquals("lastName", post.getMessage());
		
	}
}
