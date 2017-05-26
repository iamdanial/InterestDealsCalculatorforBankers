package com.banktest.banker.api;

/**
 * An interface to allow the extensibility of different deals including the
 * present and future. Direct implementations include
 * <li><code> CompoundsInterestCalculator </code></li>
 * <li><code> SimpleInterestCalculator </code></li>
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public interface DealsCalculator {
	/**
	 * A method to allow implementing different deals for bankers
	 * @param dealParameters - the dealParameters, all necessary parameters to allow calculation of appropriate deals
	 * @return the deals' end result
	 */
	public double calculateDeal(DealParameters dealParameters);

}
