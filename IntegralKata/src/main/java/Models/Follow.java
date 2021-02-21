package Models;

public class Follow {

	private long userId;
	private int userToFollow;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getUserToFollow() {
		return userToFollow;
	}
	public void setUserToFollow(int userToFollow) {
		this.userToFollow = userToFollow;
	}
	
}
