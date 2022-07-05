package restuarantapp;

import java.util.Date;

/**
 * Entity class holding the information of reservation
 * 
 * @author ZHANG MENGAO
 * @version 1.0
 */
public class Reservation {
	/**
	 * the id of the customer.
	 */
	private int customerId;

	/**
	 * records the date of the reservation
	 */
	private Date date;
	/**
	 * records the number of persons in this reservation
	 */
	private int pax;
	/**
	 * records the name of the customer
	 */
	private String name;
	/**
	 * records the contact information of the customer
	 */
	private String contact;
	/**
	 * records the table number being allocated
	 */
	private int tableNumber;

	/**
	 * constructor
	 * 
	 * @param date        the date of the reservation
	 * @param customerId  the id of the customer.
	 * @param pax         the number of persons in this reservation
	 * @param name        the name of the customer
	 * @param contact     the contact information of the customer
	 * @param tableNumber the table number being allocated
	 */
	public Reservation(Date date, int pax, int customerId, String name, String contact, int tableNumber) {
		this.date = date;
		this.customerId = customerId;
		this.pax = pax;
		this.name = name;
		this.contact = contact;
		this.tableNumber = tableNumber;
	}

	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * @param pax
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return tableNumber
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * @param tableNumber
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * getter for customerId
	 * 
	 * @return the id
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * setter for customerId
	 * 
	 * @param customerId
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
