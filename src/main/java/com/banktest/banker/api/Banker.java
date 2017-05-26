package com.banktest.banker.api;
 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
 
/**
 * A POJO to implement the data part for the banking application
 * it includes <code> DealParameters </code> to have the necessary banking deals
 * 
 * @author Abdullah
 * @since 26/05/2017
 * @version 1.0
 *
 */
public class Banker {
     
    @NotNull
    private Integer id;
    @NotBlank @Length(min=2, max=255)
    private String firstName;
    @NotBlank @Length(min=2, max=255)
    private String lastName;
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    private DealParameters dealParameters;
    
    

	/**
	 * 
	 */
	public Banker() {
		super();
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param dealParameters
	 */
	public Banker(Integer id, String firstName, String lastName, String email, DealParameters dealParameters) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dealParameters = dealParameters;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dealParameters
	 */
	public DealParameters getDealParameters() {
		return dealParameters;
	}

	/**
	 * @param dealParameters the dealParameters to set
	 */
	public void setDealParameters(DealParameters dealParameters) {
		this.dealParameters = dealParameters;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Banker [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", dealParameters=" + dealParameters + "]";
	}
	
    
    

	
	

	
	

}