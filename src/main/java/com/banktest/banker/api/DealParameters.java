package com.banktest.banker.api;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ritaja.xchangerate.util.Currency;
/**
 * A POJO to implement the data part for the banking application - the deal component
 * <code> DealParameters </code> contain all important variables or values to allow the calculation of 
 * necessary banking deals
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public class DealParameters {
	@NotBlank
	@Length(min = 2, max = 255)
	private double principal;
	@NotBlank
	@Length(min = 2, max = 255)
	private double years;
	@NotBlank
	@Length(min = 2, max = 255)
	private double rate;
	@NotBlank
	@Length(min = 2, max = 255)
	private double compound;
	@NotBlank
	@Length(min = 2, max = 255)
	private Currency currency;

	@Length(min = 2, max = 255)
	private double dealOrCalculatedInterest;
	
	@Length(min = 2, max = 255)
	private double dealOrCalculatedInterestinUSD;
	
	/**
	 * @param principal
	 * @param years
	 * @param rate
	 * @param compound
	 * @param currency
	 */
	public DealParameters(String principal, String years, String rate, String compound, String currency) {
		super();
		this.principal = new Double (principal).doubleValue();
		this.years = new Double (years).doubleValue();
		this.rate = new Double (rate).doubleValue();
		this.compound = new Double (compound).doubleValue();
		this.currency = Enum.valueOf(Currency.class, currency);
	}
	/**
	 * @param principal
	 * @param years
	 * @param rate
	 * @param compound
	 * @param currency
	 */
	public DealParameters(double principal, double years, double rate, double compound, Currency currency) {
		super();
		this.principal = principal;
		this.years = years;
		this.rate = rate;
		this.compound = compound;
		this.currency = currency;
	}

	/**
	 * @return the principal
	 */
	public double getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	/**
	 * @return the years
	 */
	public double getYears() {
		return years;
	}

	/**
	 * @param years
	 *            the years to set
	 */
	public void setYears(double years) {
		this.years = years;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the compound
	 */
	public double getCompound() {
		return compound;
	}

	/**
	 * @param compound
	 *            the compound to set
	 */
	public void setCompound(double compound) {
		this.compound = compound;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @return the dealOrCalculatedInterest
	 */
	public double getDealOrCalculatedInterest() {
		return dealOrCalculatedInterest;
	}

	/**
	 * @param dealOrCalculatedInterest the dealOrCalculatedInterest to set
	 */
	public void setDealOrCalculatedInterest(double dealOrCalculatedInterest) {
		this.dealOrCalculatedInterest = dealOrCalculatedInterest;
	}

	/**
	 * @return the dealOrCalculatedInterestinUSD
	 */
	public double getDealOrCalculatedInterestinUSD() {
		return dealOrCalculatedInterestinUSD;
	}

	/**
	 * @param dealOrCalculatedInterestinUSD the dealOrCalculatedInterestinUSD to set
	 */
	public void setDealOrCalculatedInterestinUSD(double dealOrCalculatedInterestinUSD) {
		this.dealOrCalculatedInterestinUSD = dealOrCalculatedInterestinUSD;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DealParameters [principal=" + principal + ", years=" + years + ", rate=" + rate + ", compound="
				+ compound + ", currency=" + currency + ", dealOrCalculatedInterest=" + dealOrCalculatedInterest
				+ ", dealOrCalculatedInterestinUSD=" + dealOrCalculatedInterestinUSD + "]";
	}
	/**
	 * 
	 */
	public DealParameters() {
		super();
	}



	
}
