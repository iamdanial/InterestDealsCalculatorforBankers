package com.banktest.banker.api;

import java.math.BigDecimal;

import org.json.JSONException;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;

/**
 * An interface to allow the conversion of different deal currency including all
 * the countries Direct implementations include
 * <code> OnlineCurrencyConverterImpl </code>
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public interface OnlineCurrencyConverter {
	/**
	 * A method to allow the conversion of currencies for all the countries as
	 * per requirement for business
	 * 
	 * @param amount
	 *            - the amount that needs conversion
	 * @param from
	 *            - the source currency
	 * @param to
	 *            - the destination or the final required currency
	 * @return - the converted amount into the required currency
	 * 
	 * @throws CurrencyNotSupportedException
	 *             - if the currency is not supported
	 * @throws JSONException
	 *             - if the object cannot be converted into json format / vice
	 *             versa
	 * @throws StorageException
	 *             - the storing problem
	 * @throws EndpointException
	 *             - the end point is not available
	 * @throws ServiceException
	 *             - the service being unavailable
	 */
	public BigDecimal convertCurrency(BigDecimal amount, Currency from, Currency to)
			throws CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException;
}