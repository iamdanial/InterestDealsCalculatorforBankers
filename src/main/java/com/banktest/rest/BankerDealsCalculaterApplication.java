package com.banktest.rest;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.banktest.rest.basicauth.AppAuthenticator;
import com.banktest.rest.basicauth.AppAuthorizer;
import com.banktest.rest.basicauth.User;
import com.banktest.rest.controller.BankerRestController;
import com.banktest.rest.controller.ClientController;
import com.banktest.rest.healthcheck.AppHealthCheck;
import com.banktest.rest.healthcheck.HealthCheckController;

/**
 * <code>BankerDealsCalculaterApplication</code> is responsible to implement the relevant controls for the main banking application
 * @author Abdullah
 *
 */
public class BankerDealsCalculaterApplication extends Application<Configuration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BankerDealsCalculaterApplication.class);

	@Override
	public void initialize(Bootstrap<Configuration> b) {
	}

	@Override
	public void run(Configuration c, Environment e) throws Exception 
	{
		LOGGER.info("Registering REST resources");
		
		e.jersey().register(new BankerRestController(e.getValidator()));

		final Client client = new JerseyClientBuilder(e)
				.build("DemoRestClient");
		e.jersey().register(new ClientController(client));

		// Application health check
		e.healthChecks().register("APIHealthCheck", new AppHealthCheck(client));

		// Run multiple health checks
		e.jersey().register(new HealthCheckController(e.healthChecks()));
		
		//Setup Basic Security
		e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new AppAuthenticator())
                .setAuthorizer(new AppAuthorizer())
                .setRealm("App Security")
                .buildAuthFilter()));
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        e.jersey().register(RolesAllowedDynamicFeature.class);
	}

	public static void main(String[] args) throws Exception {
		new BankerDealsCalculaterApplication().run(args);
	}
}