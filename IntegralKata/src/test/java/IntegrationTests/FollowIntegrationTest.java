package IntegrationTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import Models.Follow;
import Models.Post;


public class FollowIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCFollowDAO dao;

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
		String sqlInsertUser1 = "INSERT INTO users (id, userName, firstName, lastName, password) VALUES (12345, 'myUser', 'Me', 'lastNameMe', 'password');";
		String sqlInsertUser2 = "INSERT INTO users (id, userName, firstName, lastName, password) VALUES (123456, 'myUser', 'Me', 'lastNameMe', 'password');";
		String sqlInsertFollow = "INSERT INTO follwo (userId, userToFollow) VALUES (12345, 123456);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertUser1);
		jdbcTemplate.update(sqlInsertUser2);
		jdbcTemplate.update(sqlInsertFollow);
		dao = new JDBCUserDAO(jdbcTemplate);
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
	public void getFollowsByUserIdTest() {
		List <Follow> actual = dao.getFollowsByUserId(12345);
		List <Follow> expected = new ArrayList<Follow>();
		expected.add(getGollows(12345, 123456));
		assertEquals(expected.size(), actual.size());
	}

	private Follow getGollows(long userId, int userToFollow) {
		Follow follow = new Follow();
		follow.setUserId(userId);
		follow.setUserToFollow(userToFollow);
		return follow;
	}
}
