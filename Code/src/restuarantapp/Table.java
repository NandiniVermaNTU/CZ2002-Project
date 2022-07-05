/**
 * Represents the tables in the restaurant
 * @author Pua Yong Hao
 * @version 1.3
 */
package restuarantapp;

public class Table {

	/**
	 * Unique number for the table
	 */
	private int tableNumber;
	/**
	 * Seating capacity, in even sizes, with minimum of 2 and maximum of 10 pax
	 */
	private int seatingCapacity;
	/**
	 * Availability of the table
	 */
	private boolean availability;
	/**
	 * ID of Customer that occupies or reserves the table
	 */
	private int customerID;

	/**
	 * Creates table with table number and seating capacity. Its availability is set
	 * to true
	 * 
	 * @param tableNumber     Unique number for the table
	 * @param seatingCapacity Seating capacity, in even sizes, with minimum of 2 and
	 *                        maximum of 10 pax
	 */
	public Table(int tableNumber, int seatingCapacity) {
		this.tableNumber = tableNumber;
		this.seatingCapacity = evenCapacity(seatingCapacity);
		this.availability = true;
		this.customerID = -1;
	}

	/**
	 * Creates table with table number, seating capacity and availability.
	 * 
	 * @param tableNumber     Unique number for the table
	 * @param seatingCapacity Seating capacity, in even sizes, with minimum of 2 and
	 *                        maximum of 10 pax
	 * @param availability    Availability of the table
	 * @param customerID      Customer ID that using or reserves the table
	 */
	public Table(int tableNumber, int seatingCapacity, boolean availability, int customerID) {
		this.tableNumber = tableNumber;
		this.seatingCapacity = evenCapacity(seatingCapacity);
		this.availability = availability;
		this.customerID = customerID;
	}

	/**
	 * Return table number
	 * 
	 * @return tableNumber
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * Return seat capacity of the table
	 * 
	 * @return seatingCapacity
	 */
	public int getCapacity() {
		return seatingCapacity;
	}

	/**
	 * Return availability of the table
	 * 
	 * @return availability
	 */
	public boolean getAvailability() {
		return availability;
	}

	/**
	 * Return customer ID
	 * 
	 * @return customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * Set table number
	 * 
	 * @param tableNumber Unique number for the table
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * Set seatingCapacity capacity
	 * 
	 * @param seatingCapacity Seating capacity, in even sizes, with minimum of 2 and
	 *                        maximum of 10 pax
	 */
	public void setCapacity(int seatingCapacity) {
		this.seatingCapacity = evenCapacity(seatingCapacity);
	}

	/**
	 * Set availability
	 */
	public void setAvailability() {
		availability = !availability;
	}

	/**
	 * Set customer ID
	 * @param customerID Unique Customer ID
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * Ensure the seating capacity is in even sizes. If it is odd number, minus it
	 * by 1
	 * 
	 * @param seatingCapacity Seating capacity, in even sizes, with minimum of 2 and
	 *                        maximum of 10 pax
	 */
	private int evenCapacity(int seatingCapacity) {
		if (seatingCapacity >= 10) {
			return 10;
		} else if (seatingCapacity <= 2) {
			return 2;
		} else {
			// Make sure the seating capacity is in even sizes
			if (seatingCapacity % 2 == 0) {
				return seatingCapacity;
			} else {
				return (seatingCapacity - 1);
			}
		}
	}
}
