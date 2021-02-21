package JDBCDAOs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import Models.User;

public class JDBCUserDAO implements UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JDBCUserDAO() {
	}

	public JDBCUserDAO(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public User getUserById(long id) {
		User user = new User();
		String sql = "SELECT * FROM users WHERE id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while (results.next()) {
			user = mapRowToUser(results);
		}
		return user;

	}

	public User mapRowToUser(SqlRowSet results) {
		User user = new User();
		user.setId(results.getLong("id"));
		user.setUserName(results.getString("userName"));
		user.setFirstName(results.getString("firstName"));
		user.setLastName(results.getString("lastName"));
		user.setPassword(results.getString("password"));
		return user;
	}
}
