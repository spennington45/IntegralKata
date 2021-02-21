package UnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import Models.Post;


class PostTest {

	@Test
	public void postModelGettersAndSettersTest() {
		Post post = new Post();
		Timestamp timestamp = Timestamp.valueOf("2021-02-21 11:21:59.738");
		post.setTimeStamp(timestamp);
		post.setUserId(1);
		post.setMessage("This is a test message!");

		
		
		assertEquals(timestamp, post.getTimeStamp());
		
		assertEquals(1, post.getUserId());
		
		assertEquals("This is a test message!", post.getMessage());
		
	}
}
