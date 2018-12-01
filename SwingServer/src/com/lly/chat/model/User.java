package com.lly.chat.model;

import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String password;
	private String nickname;
	private String sex;
	private int age;

	public User() {
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User(String username, String password, String nickname, String sex, int age) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.sex = sex;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", nickname=" + nickname + ", sex=" + sex
				+ ", age=" + age + "]";
	}


}
