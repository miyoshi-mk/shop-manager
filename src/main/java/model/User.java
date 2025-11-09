package model;

import java.io.Serializable;

//ユーザーアカウントクラス
public class User implements Serializable {

	//フィールド
	private int userId;
	private String userName;
	private String password;

	//コンストラクタ
	public User() {}

	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	//getter/setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
