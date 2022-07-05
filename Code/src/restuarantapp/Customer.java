/**
 * Contains all the information about a customer of the restaurant 
 * 
 * @author Nandini Verma
 *
 */

package restuarantapp;

public class Customer {
	/**
	 * Stores whether or not the customer is a member of the restaurant
	 */
	private boolean isMembership;

	/**
	 * Stores the customer ID of a customer
	 */
	private int customerID;

	/**
	 * Stores the contact of a customer
	 */
	private String customerContact;

	/**
	 * Stores the name of a customer
	 */
	private String customerName;

	/**
	 * Stores the gender of a customer
	 */
	private gender customerGender;

	/**
	 * Store information of staff who created the order
	 * 
	 * @param isMembership    Whether the customer is a member of the restaurant or not
	 * @param customerID      The customer ID of a customer
	 * @param customerContact The contact of a customer
	 * @param customerName    The name of a customer
	 * @param customerGender  The gender of a customer
	 */

	public Customer(int customerID, String customerName, boolean isMembership, String customerContact, gender customerGender)
	{
		this.isMembership = isMembership;
		this.customerName = customerName;
		this.customerID = customerID;
		this.customerContact = customerContact;
		this.customerGender = customerGender;
	}

	/**
	 * Set whether the customer is a member of the restaurant or not
	 * 
	 * @param isMembership boolean value (true/false) for whether or not the
	 *                     customer is a member of the restaurant
	 */
	public void setMembership(boolean isMembership) {
		this.isMembership = isMembership;
	}

	/**
	 * Return whether or not the customer is a member of the restaurant
	 * 
	 * @return isMembership
	 */
	public boolean getMembership() {
		return this.isMembership;
	}

	/**
	 * Set Customer ID
	 * 
	 * @param customerID Customer ID of the customer
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * Return the Customer ID of the customer
	 * 
	 * @return customerID
	 */
	public int getCustomerID() {
		return this.customerID;
	}

	/**
	 * Set Customer Name
	 * 
	 * @param customerName Name of the customer
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Return the Customer Name
	 * 
	 * @return customerName
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * Set Customer Contact
	 * 
	 * @param customerContact Contact of the customer
	 */
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	/**
	 * Return the Contact of the customer
	 * 
	 * @return customerContact
	 */
	public String getCustomerContact() {
		return this.customerContact;
	}

	/**
	 * Set Customer Gender
	 * 
	 * @param customerGender Gender of the customer
	 */
	public void setCustomerGender(gender customerGender) {
		this.customerGender = customerGender;
	}

	/**
	 * Return the gender of the customer
	 * 
	 * @return customerGender
	 */
	public gender getCustomerGender() {
		return this.customerGender;
	}
}
