package com.banktest.rest.basicauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.banktest.rest.basicauth.AppAuthenticator;
import com.banktest.rest.basicauth.User;

import io.dropwizard.auth.basic.BasicCredentials;

/**
 * To test the {@link AppAuthenticator} for ensuring the user principal and roles
 * 
 * @author Abdullah
 *
 */
public class AppAuthorizerTest {

	
	BasicCredentials basicCredentials;
	
	AppAuthenticator appAuthenticator;
	@Before
	public void setUp() {
		basicCredentials = new BasicCredentials("user", "password");
		appAuthenticator = new AppAuthenticator();
	}

	@Test
	public void testCompoundsInterestCalculator() throws Exception {
		Optional<User> user = appAuthenticator.authenticate(basicCredentials);
		
		assertEquals("user isn't set properly ", user.get().getName(), "user");
		assertTrue("user role isn't set properly ", user.get().getRoles().contains("USER"));
	}

	
}
