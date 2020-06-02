package com.enhancesys.jobengine.consumer.model;

import java.util.Set;

public class User 
{

	private Long userId;
	private String username;
	private String password;
	private Set<String> roles;
	
	
	
	public User(Long userId, String username, String password) {
		super();
		this.userId   = userId;
		this.username = username;
		this.password = password;
	}

	public User(Long userId,  String password) {
		super();
		this.userId   = userId;
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ "]";
	}

	
	
}
