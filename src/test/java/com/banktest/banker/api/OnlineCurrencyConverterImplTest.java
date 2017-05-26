package com.banktest.banker.api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.banktest.banker.api.OnlineCurrencyConverterImpl;
import com.ritaja.xchangerate.util.Currency;

/**
 * To test the {@link OnlineCurrencyConverterImplTest } for the conversion of currencies
 * 
 * @author Abdullah
 *
 */
public class OnlineCurrencyConverterImplTest {

	OnlineCurrencyConverterImpl onlineCurrencyConverterImpl;

	@Before
	public void setUp() {
		
		onlineCurrencyConverterImpl = new OnlineCurrencyConverterImpl();

	}

	@Test
	public void testCompoundsInterestCalculator() throws Exception {
		BigDecimal deal = onlineCurrencyConverterImpl.convertCurrency(BigDecimal.valueOf(1000), Currency.AED,
				Currency.EUR);

		assertEquals("Field Id isn't set properly ",272.29 ,deal.doubleValue(), 0.001);

	}

}
