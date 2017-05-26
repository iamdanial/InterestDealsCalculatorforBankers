package com.banktest.banker.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <code> CompoundsInterestCalculator </code> is responsible for implementing the necessary
 * functionality to calculate the deal for compound interest
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public class CompoundsInterestCalculator implements DealsCalculator {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompoundsInterestCalculator.class);
	public double calculateDeal(DealParameters dealParameters) {

		LOGGER.info("Principle: %.2f \n", dealParameters.getPrincipal());
		LOGGER.info("Years: " , dealParameters.getYears());
		LOGGER.info("Interest rate: " , dealParameters.getRate());

		
		double totalInterest = 0;
		double totalAmountWithInterest = 0;
		for (int x = 1; x <= dealParameters.getYears(); x++) {
			if (dealParameters.getCompound() > 0) {
				totalAmountWithInterest = dealParameters.getPrincipal() * Math.pow(1 + (dealParameters.getRate() / dealParameters.getCompound()), x);
				LOGGER.info("Year " + x + ": %.2f \n", totalAmountWithInterest);
			} else {
				totalAmountWithInterest = dealParameters.getPrincipal() * Math.pow(1 + dealParameters.getRate(), x);
			}
		}
		
		totalInterest = totalAmountWithInterest - dealParameters.getPrincipal();
		dealParameters.setDealOrCalculatedInterest(totalInterest);
		
		LOGGER.info("Total amount componded over the " , dealParameters.getYears(),  " years:"," is : " ,totalInterest);
		return totalInterest;
	}

}
