package com.banktest.banker.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.banktest.banker.api.DealParameters;
import com.banktest.banker.api.SimpleInterestCalculator;
import com.ritaja.xchangerate.util.Currency;

/**
 * To test the SimpleInterestCalculaterTest and related properties
 * 
 * @author Abdullah
 *
 */
public class SimpleInterestCalculaterTest {

	private DealParameters dealParameters;

	SimpleInterestCalculator simpleInterestCalculator;

	@Before
	public void setUp() {
		dealParameters = new DealParameters(5000.0, 5.0, 0.05, 0.0, Currency.EUR);
		simpleInterestCalculator = new SimpleInterestCalculator();

	}

	@Test
	public void testCompoundsInterestCalculator() throws NoSuchFieldException, SecurityException {
		double deal = simpleInterestCalculator.calculateDeal(dealParameters);

		assertEquals("Field Id isn't set properly ", 12.5, deal, 0.001);

	}

}
