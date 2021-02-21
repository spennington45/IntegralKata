package JDBCDAOs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import Models.Post;
import Models.User;

@Component
public class JDBCPostDAO implements PostDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JDBCPostDAO() {
	}

	public JDBCPostDAO(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public List<Post> getTimelineOfUserById(long id) {
		List<Post> output = new ArrayList<Post>();
		String sql = "SELECT * FROM post WHERE userId = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while (results.next()) {
			output.add(mapRowToPost(results));
		}
		return output;
	}

	public Post mapRowToPost(SqlRowSet results) {
		Post post = new Post();
		post.setId(results.getLong("id"));
		post.setTimeStamp(results.getTimestamp("time_stamp"));
		post.setUserId(results.getInt("userId"));
		post.setMessage(results.getString("message"));
		return post;
	}
	
}
