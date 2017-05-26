package com.banktest.banker.api;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.banktest.banker.api.Banker;
import com.banktest.banker.api.DealParameters;
import com.ritaja.xchangerate.util.Currency;

/**
 * To test the Banker and related properties
 * 
 * @author Abdullah
 *
 */
public class BankerTest {

	private Banker person;
	private DealParameters dealParameters ;

	@Before
	public void setUp() {
		dealParameters = new DealParameters(5000.0, 5.0, 0.05, 0.0, Currency.EUR);
		person = new Banker(1, "Loke", "Guitar", "UK@gmail.com",
				dealParameters);
	}

	@Test
	public void testBankerId() throws NoSuchFieldException, SecurityException {
		Field id = person.getClass().getDeclaredField("id");
		id.setAccessible(true);
		Integer retrievedId = person.getId();

		assertEquals("Field Id isn't set properly ", retrievedId, new Integer(1));

		person.setId(2);
		retrievedId = person.getId();
		assertEquals("Field Id isn't set properly ", retrievedId, new Integer(2));

	}

	@Test
	public void testBankerEmail() throws NoSuchFieldException, SecurityException {
		Field FirstName = person.getClass().getDeclaredField("email");
		FirstName.setAccessible(true);
		String retrievedEmail = person.getEmail();

		assertEquals("Field FirstName isn't set properly ", retrievedEmail, "UK@gmail.com");

		person.setEmail("UK1@gmail.com");
		retrievedEmail = person.getEmail();
		assertEquals("Field FirstName isn't set properly ", retrievedEmail, "UK1@gmail.com");

	}

	@Test
	public void testBankerFirstName() throws Exception{
		Field firstName = person.getClass().getDeclaredField("firstName");
		firstName.setAccessible(true);
		firstName.set(person, "Loke");
		String retrievedFirstName = person.getFirstName();

		assertEquals("Field FirstName isn't set properly ", retrievedFirstName, "Loke");

		person.setFirstName("Loke1");
		retrievedFirstName = person.getFirstName();
		assertEquals("Field FirstName isn't set properly ", retrievedFirstName, "Loke1");

	}
	
	@Test
	public void testBankerLastName() throws NoSuchFieldException, SecurityException {
		Field lastName = person.getClass().getDeclaredField("lastName");
		lastName.setAccessible(true);
		String retrievedLastName = person.getLastName();

		assertEquals("Field FirstName isn't set properly ", retrievedLastName, "Guitar");

		person.setLastName("Guitar1");
		retrievedLastName = person.getLastName();
		assertEquals("Field FirstName isn't set properly ", retrievedLastName, "Guitar1");

	}
	@Test
	public void testBankerDealParameters() throws NoSuchFieldException, SecurityException {

		DealParameters dealParametersLocal = person.getDealParameters();

		assertEquals("Field DealParameters isn't set properly ", dealParametersLocal, dealParameters);

		dealParametersLocal = new DealParameters(5001.0, 5.0, 0.05, 0.0, Currency.USD);
		person.setDealParameters(dealParametersLocal);
		
		DealParameters dealParametersTemp = person.getDealParameters();
		
		assertEquals("Field DealParameters isn't set properly ", dealParametersLocal, dealParametersTemp);

	}
}
