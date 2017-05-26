package com.banktest.rest.basicauth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * A simple authentication mechanism to allow users with relevant roles to be authenticated to access application.
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 */
public class AppAuthenticator implements Authenticator<BasicCredentials, User> {
   
	// TODO: needs to be encrypted and stored appropriately
    private static final String PASSWORD = "password";
    
	private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
        "guest", ImmutableSet.of(),
        "user", ImmutableSet.of("USER"),
        "admin", ImmutableSet.of("ADMIN", "USER")
    );

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (VALID_USERS.containsKey(credentials.getUsername()) && PASSWORD.equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
        return Optional.empty();
    }
}
