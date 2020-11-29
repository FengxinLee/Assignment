package pojo;

import java.io.Serializable;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;

	private String userPasswd;

	private int userRole;

	public User() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return this.userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public int getUserRole() {
		return this.userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPasswd=" + userPasswd + ", userRole=" + userRole + "]";
	}

}