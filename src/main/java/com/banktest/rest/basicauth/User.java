package com.banktest.rest.basicauth;

import java.security.Principal;
import java.util.Set;

/**
 * This class is responsible for implementing a simple role based access control
 * allowing relevant users
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 */
public class User implements Principal {
	private final String name;

	private final Set<String> roles;

	public User(String name) {
		this.name = name;
		this.roles = null;
	}

	public User(String name, Set<String> roles) {
		this.name = name;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return (int) (Math.random() * 100);
	}

	public Set<String> getRoles() {
		return roles;
	}
}
