package IntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import JDBCDAOs.JDBCUserDAO;
import Models.Post;
import Models.User;

class PostIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCPostDAO postDao;
	private JDBCUserDAO userDao;
	private Timestamp timestamp = Timestamp.valueOf("2021-02-21 11:21:59.738");

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setUpDataSource() {
		System.out.println("Before Class");
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/intigral"); 
		dataSource.setUsername("postgres");  //may need to change for a given user should set to env
		dataSource.setPassword("postgres1"); //may need to change for a given user should set to env
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource()  {
		System.out.println("After Class");
		dataSource.destroy();
	}

	// Before each test, there will be a user inserted into the database for testing.
	@Before
	public void setup() {
		System.out.println("Before test");
		String sqlInsertUser = "INSERT INTO users (id, userName, firstName, lastName, password) VALUES (12345, 'myUser', 'Me', 'lastNameMe', 'password');";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlInsertPost = "INSERT INTO post (time_stamp, uesrid, message) VALUES (?, 12345, 'This is a test message');";
		jdbcTemplate.update(sqlInsertUser);
		jdbcTemplate.update(sqlInsertPost, timestamp);
		postDao = new JDBCPostDAO(jdbcTemplate);
	}

	// After each test, the database will be rolledback any undoing any changes that were made to the database
	@After
	public void rollback() {
		System.out.println("After test");
		try {
			dataSource.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getUserByIdTest() {
		List <Post> actual = postDao.getTimelineOfUserById(12345);
		List <Post> expected = new ArrayList<Post>();
		expected.add(getPost(123, timestamp, 12345, "This is a test message"));
		assertEquals(expected.size(), actual.size());
	}
	
	private Post getPost(long id, Timestamp timestamp, int userId, String message) {
		Post post = new Post();
		post.setId(id);
		post.setTimeStamp(timestamp);
		post.setUserId(userId);
		post.setMessage(message);
		return post;
	}

	private void assertPostAreEqual(Post expected, Post actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTimeStamp(), actual.getTimeStamp());
		assertEquals(expected.getUserId(), actual.getUserId());
		assertEquals(expected.getMessage(), actual.getMessage());
	}
}
