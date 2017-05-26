package com.banktest.banker.api;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.banktest.banker.api.DealParameters;
import com.ritaja.xchangerate.util.Currency;

/**
 * To test the DealParameters and related properties
 * 
 * @author Abdullah
 *
 */
public class DealParametersTest {


	private DealParameters dealParameters ;

	@Before
	public void setUp() {
		dealParameters = new DealParameters(5000.0, 5.0, 0.05, 0.0, Currency.EUR);
	}

	@Test
	public void testDealParametersPrincipal() throws Exception {

		double principal = dealParameters.getPrincipal();

		assertEquals("Field DealParameters isn't set properly ", 5000, principal, 0.01);
		
		Field principalField = dealParameters.getClass().getDeclaredField("principal");
		principalField.setAccessible(true);
		principalField.set(dealParameters, 50001.0);
		
		principal = dealParameters.getPrincipal();

		assertEquals("Field DealParameters isn't set properly ", 50001.0, principal, 0.01);
		
		dealParameters.setPrincipal(40001.0);
		
		assertEquals("Field DealParameters isn't set properly ", "40001.0", principalField.get(dealParameters).toString());

	}
	
	@Test
	public void testDealParametersYears() throws Exception {

		double years = dealParameters.getYears();

		assertEquals("Field DealParameters isn't set properly ", 5, years, 0.01);
		
		Field principalField = dealParameters.getClass().getDeclaredField("years");
		principalField.setAccessible(true);
		principalField.set(dealParameters, 11.0);
		
		years = dealParameters.getYears();

		assertEquals("Field DealParameters isn't set properly ", 11, years, 0.01);
		
		dealParameters.setYears(12.0);
		
		assertEquals("Field DealParameters isn't set properly ", "12.0", principalField.get(dealParameters).toString());

	}
	
	@Test
	public void testDealParametersCompound() throws Exception {

		double compound = dealParameters.getCompound();

		assertEquals("Field DealParameters isn't set properly ", 0, compound, 0.01);
		
		Field compoundField = dealParameters.getClass().getDeclaredField("compound");
		compoundField.setAccessible(true);
		compoundField.set(dealParameters, 11.0);
		
		compound = dealParameters.getCompound();

		assertEquals("Field DealParameters isn't set properly ", 11.0, compound, 0.01);
		
		dealParameters.setCompound(12.0);
		
		assertEquals("Field DealParameters isn't set properly ", "12.0", compoundField.get(dealParameters).toString());

	}
	
	@Test
	public void testDealParametersCurrency() throws Exception {

		double rate = dealParameters.getRate();

		assertEquals("Field DealParameters isn't set properly ", 0.05, rate, 0.01);
		
		Field rateField = dealParameters.getClass().getDeclaredField("rate");
		rateField.setAccessible(true);
		rateField.set(dealParameters, 11.0);
		
		rate = dealParameters.getRate();

		assertEquals("Field DealParameters isn't set properly ", 11, rate, 0.01);
		
		dealParameters.setRate(12.0);
		
		assertEquals("Field DealParameters isn't set properly ", "12.0", rateField.get(dealParameters).toString());

	}
	
	@Test
	public void testDealParametersRate() throws Exception {

		Currency currency = dealParameters.getCurrency();

		assertEquals("Field DealParameters isn't set properly ", Currency.EUR, currency);
		
		Field rateField = dealParameters.getClass().getDeclaredField("currency");
		rateField.setAccessible(true);
		rateField.set(dealParameters, Currency.USD);
		
		currency = dealParameters.getCurrency();

		assertEquals("Field DealParameters isn't set properly ", Currency.USD, currency);
		
		dealParameters.setCurrency(Currency.AED);
		
		assertEquals("Field DealParameters isn't set properly ", "AED", rateField.get(dealParameters).toString());

	}
}
