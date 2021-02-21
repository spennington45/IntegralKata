package JDBCDAOs;

import java.util.List;

import Models.Post;

public interface PostDAO {

	public List<Post> getTimelineOfUserById(long id);
}
