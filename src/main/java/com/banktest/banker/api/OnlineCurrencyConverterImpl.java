package com.banktest.banker.api;

import java.math.BigDecimal;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;
import com.ritaja.xchangerate.util.Strategy;


/**
 * <code> OnlineCurrencyConverterImpl </code> is an implementation 
 * for the interface {@link OnlineCurrencyConverter }.
 * As the name indicates, this allows the calculation of the conversion of the currencies
 * using the online implmentation.
 * 
 * 
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public class OnlineCurrencyConverterImpl implements OnlineCurrencyConverter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineCurrencyConverterImpl.class);


	public BigDecimal convertCurrency(BigDecimal amount, Currency from, Currency to)
			throws CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException {

		// create the appropriate survive object
		CurrencyConverter converter = new CurrencyConverterBuilder().strategy(Strategy.YAHOO_FINANCE_FILESTORE)
				.buildConverter();

		// set the refresh rate to set timer to update new exchange rates
		converter.setRefreshRateSeconds(86400);

		// convert between currencies
		// general format of conversion is convertCurrency(amount, fromCurrency,
		// toCurrency)
		// example conversion of 100 USD to EUR is:
		BigDecimal convertedAmount = converter.convertCurrency(amount, from, Currency.USD);
		
		LOGGER.info("Converted amount : " +  (convertedAmount != null ? convertedAmount.doubleValue() : "null"));

		return convertedAmount;

	}
}
