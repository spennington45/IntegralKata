package IntegrationTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import Models.User;




public class UserIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCUserDAO dao;

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
		jdbcTemplate.update(sqlInsertUser);
		dao = new JDBCUserDAO(jdbcTemplate);
	}

	// After each test, the database will be rolledback any undoing any changes that were made to the database
	@After
	public void rollback() {
		System.out.println("After test");
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void getUserByIdTest() {
		User actual = dao.getUserById(12345);
		User expected = getUser("myUser", "Me", "lastNameMe", "password", 12345);
		assertUsersAreEqual(expected, actual);
	}
	
	private User getUser(String userName, String firstName, String lastName, String password, long id) {
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		return user;
	}

	private void assertUsersAreEqual(User expected, User actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getUserName(), actual.getUserName());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
	}

}
