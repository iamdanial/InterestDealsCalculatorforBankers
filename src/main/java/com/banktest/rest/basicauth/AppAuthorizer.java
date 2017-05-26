package com.banktest.rest.basicauth;

import io.dropwizard.auth.Authorizer;

/**
 * A simple authorisation mechanism to allow users with relevant roles to access
 * application.
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 */
public class AppAuthorizer implements Authorizer<User> {

	@Override
	public boolean authorize(User user, String role) {
		return user.getRoles() != null && user.getRoles().contains(role);
	}
}