package com.banktest.banker.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.banktest.banker.api.CompoundsInterestCalculator;
import com.banktest.banker.api.DealParameters;
import com.banktest.banker.api.OnlineCurrencyConverterImpl;
import com.ritaja.xchangerate.util.Currency;

/**
 * To test the {@link OnlineCurrencyConverterImpl} for calculating the compound interest deal
 * 
 * @author Abdullah
 *
 */
public class CompoundsInterestCalculaterTest {

	
	private DealParameters dealParameters ;
	
	CompoundsInterestCalculator compoundsInterestCalculator;
	@Before
	public void setUp() {
		dealParameters = new DealParameters(5000.0, 5.0, 0.05, 0.0, Currency.EUR);
		compoundsInterestCalculator = new CompoundsInterestCalculator();
		
	}

	@Test
	public void testCompoundsInterestCalculator() throws NoSuchFieldException, SecurityException {
		double deal = compoundsInterestCalculator.calculateDeal(dealParameters);

		assertEquals("Field Id isn't set properly ", deal, 1381.40781, 0.001);

		

	}

	
}
