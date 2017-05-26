package com.banktest.rest.basicauth;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.banktest.rest.basicauth.AppAuthenticator;
import com.banktest.rest.basicauth.AppAuthorizer;
import com.banktest.rest.basicauth.User;

import io.dropwizard.auth.basic.BasicCredentials;

/**
 * To test the {@link AppAuthenticator} for ensuring the user principal and roles
 * 
 * @author Abdullah
 *
 */
public class AppAuthenticatorTest {

	
	BasicCredentials basicCredentials;
	User user;
	AppAuthorizer appAuthorizer;
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		basicCredentials = new BasicCredentials("user", "password");
		
		user = new User("user", new HashSet<String>(){{add("USER");}});
		
		appAuthorizer = new AppAuthorizer();
	}

	@Test
	public void testCompoundsInterestCalculator() throws Exception {
		boolean authorised = appAuthorizer.authorize(user, "USER");
		
		assertTrue("user role isn't set properly ", authorised);
	}

	
}
