package com.banktest.rest.healthcheck;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;
/**
 * <code>AppHealthCheck</code> is responsible for the application healthcheck.
 * @author Abdullah
 *
 */
public class AppHealthCheck extends HealthCheck {
	private final Client client;

	public AppHealthCheck(Client client) {
		super();
		this.client = client;
	}

	@Override
	protected Result check() throws Exception {
		WebTarget webTarget = client.target("http://localhost:8080/bankers");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		@SuppressWarnings("rawtypes")
		ArrayList employees = response.readEntity(ArrayList.class);
		if(employees !=null && employees.size() > 0){
			return Result.healthy();
		}
		return Result.unhealthy("API Failed");
	}
}