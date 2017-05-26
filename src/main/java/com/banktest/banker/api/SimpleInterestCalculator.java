package com.banktest.banker.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code> SimpleInterestCalculator </code> is an implementation for the
 * interface {@link DealsCalculator }. As the name indicates, this allows the
 * calculation of the simple interest for the prinicipal amount
 * 
 * 
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public class SimpleInterestCalculator implements DealsCalculator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleInterestCalculator.class);

	public double calculateDeal(DealParameters dealParameters) {

		LOGGER.info("Principle: %.2f \n", dealParameters.getPrincipal());
		LOGGER.info("Years: ", dealParameters.getYears());
		LOGGER.info("Interest rate: ", dealParameters.getRate());

		double amount = (dealParameters.getPrincipal() * dealParameters.getRate() * dealParameters.getYears()) / 100;
		dealParameters.setDealOrCalculatedInterest(amount);

		LOGGER.info("Calculated Interest is : ", amount);

		return amount;
	}

}
