package com.banktest.rest.controller;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.banktest.banker.api.Banker;
import com.banktest.banker.api.CompoundsInterestCalculator;
import com.banktest.banker.api.DealsCalculator;
import com.banktest.banker.api.OnlineCurrencyConverter;
import com.banktest.banker.api.OnlineCurrencyConverterImpl;
import com.banktest.rest.basicauth.User;
import com.banktest.rest.dao.BankersDB;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;

import io.dropwizard.auth.Auth;

/**
 * The main Rest controller to handle the GET and POST of the Banking
 * application.
 * 
 * @author Abdullah
 *
 */
@Path("/bankers")
@Produces(MediaType.APPLICATION_JSON)
public class BankerRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankerRestController.class);

	private final Validator validator;
	private OnlineCurrencyConverter onlineCurrencyConverter;

	public BankerRestController(Validator validator) {
		this.validator = validator;
	}

	@PermitAll
	@GET
	public Response getBankers(@Auth User user) {
		return Response.ok(BankersDB.getEmployees()).build();
	}

	@RolesAllowed("USER")
	@GET
	@Path("/{id}")
	public Response getBankerById(@PathParam("id") Integer id, @Auth User user) {

		Banker banker = BankersDB.getEmployee(id);
		if (banker != null)
			return Response.ok(banker).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	// @RolesAllowed("ADMIN")
	@POST
	public Response createBanker(Banker banker, @Auth User user) throws URISyntaxException,
			CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException {
		LOGGER.info("Received a banker: {}", banker);
		BankersDB.updateEmployee(banker.getId(), banker);
		DealsCalculator dealsCalculatorService = new CompoundsInterestCalculator();
		double totalAmount = dealsCalculatorService.calculateDeal(banker.getDealParameters());
		LOGGER.info("Deal Calculated for the banker: {}", totalAmount);
		onlineCurrencyConverter = new OnlineCurrencyConverterImpl();
		BigDecimal convertedAmount = onlineCurrencyConverter.convertCurrency(new BigDecimal(totalAmount),
				banker.getDealParameters().getCurrency(), Currency.AED);
		LOGGER.info("Converted in Dollars : Deal Calculated for the banker: {}", convertedAmount);
		banker.getDealParameters().setDealOrCalculatedInterestinUSD(convertedAmount.doubleValue());

		return Response.ok(banker).build();

	}

	@PUT
	@Path("/{id}")
	public Response updateBankerById(@PathParam("id") Integer id, Banker banker) {
		// validation
		Set<ConstraintViolation<Banker>> violations = validator.validate(banker);
		Banker e = BankersDB.getEmployee(banker.getId());
		if (violations.size() > 0) {
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Banker> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
		}
		if (e != null) {
			banker.setId(id);
			BankersDB.updateEmployee(id, banker);
			return Response.ok(banker).build();
		} else
			return Response.status(Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeBankerById(@PathParam("id") Integer id) {
		Banker banker = BankersDB.getEmployee(id);
		if (banker != null) {
			BankersDB.removeEmployee(id);
			return Response.ok().build();
		} else
			return Response.status(Status.NOT_FOUND).build();
	}
}