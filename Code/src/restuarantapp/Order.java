
package restuarantapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represent the order that the customers make. Contains all the information
 * about the order
 * 
 * @author ZHANG MENGAO
 * @version 1.2
 */
public class Order {

	/**
	 * Count the total number of orders
	 */
	private static int orderNumber;

	/**
	 * Uniquely identify the Order
	 */
	private int OrderID;

	/**
	 * the list of order items currently placed.
	 */
	private ArrayList<OrderItem> orderedItems;

	/**
	 * records creator's object reference
	 */
	private Staff creator;

	/**
	 * holds the table number that serves this order
	 */
	private int tableNumber;

	/**
	 * holds the customer object reference that made this order
	 */
	private Customer customer;

	/**
	 * identifies the time when the order was created
	 */
	private Date timeStamp;

	/**
	 * hold the correct method to calculate tax
	 */
	private CalculateTax cTax;

	/**
	 * hold the correct method to calculate discount
	 */
	private CalculateDiscount cDiscount;

	/**
	 * create a new order and initialize the necessary information and initialize
	 * the timeStamp to the current time
	 * 
	 * @param creator     The employee object reference who created this order
	 * @param tableNumber Table number that are allocated to this order
	 * @param customer    The customer object reference who made this order
	 * @param t           The calculate tax method to get the tax;
	 * @param d           The calculate discount method based on customer type
	 */
	public Order(Staff creator, int tableNumber, Customer customer, CalculateTax t, CalculateDiscount d) {
		this.creator = creator;
		this.tableNumber = tableNumber;
		this.customer = customer;
		this.orderedItems = new ArrayList<OrderItem>();
		timeStamp = new Date();
		this.OrderID = orderNumber;
		cTax = t;
		cDiscount = d;
		orderNumber++;
	}

	/**
	 * Construct which contains all the attributes. Mainly used for recovering from
	 * storage or wants to specifying the time.
	 * 
	 * @param orderId     The order's id
	 * @param creator     The employee object reference who created this order
	 * @param customer    The customer object reference who made this order
	 * @param tableNumber Table number that are allocated to this order
	 * @param timeStamp   The time when the order was created
	 * @param t           The calculate tax method to get the tax;
	 * @param d           The calculate discount method based on customer type
	 */
	public Order(int orderId, Staff creator, Customer customer, int tableNumber, Date timeStamp, CalculateTax t,
			CalculateDiscount d) {
		this.creator = creator;
		this.tableNumber = tableNumber;
		this.customer = customer;
		this.orderedItems = new ArrayList<OrderItem>();
		this.timeStamp = timeStamp;
		this.OrderID = orderId;
		cTax = t;
		cDiscount = d;
	}

	/**
	 * add an order item to the order
	 * 
	 * @param item The object reference for the orderItem that is going to be added.
	 * @return an boolean value indicating whether adding is successful.
	 */
	public boolean addOrderItem(OrderItem item) {
		if (item == null) {
			return false;
		}
		orderedItems.add(item);
		return true;
	}

	/**
	 * delete Ordered item
	 * 
	 * @param index the index of the item that you want to delete
	 * @return an boolean value indicating whether deleting is successful.
	 */
	public boolean deleteOrderItem(int index) {
		if (index < 0 || index > orderedItems.size())
			return false;
		orderedItems.remove(index);
		return true;
	}

	/**
	 * print all the detail of the order.
	 */
	public void printOrderDetail() {
		System.out.println("Employee name(creator):" + creator.getName());
		System.out.println("Customer name: " + customer.getCustomerName());
		System.out.println("Table allocated: " + tableNumber);
		System.out.println("Order created at: " + timeStamp.toString());
		if (orderedItems.size() == 0) {
			System.out.println("Currently, there is no items in this order.");
			return;
		}

		System.out.println("Current Ordered Items: ");
		for (int i = 1; i <= orderedItems.size(); i++) {
			System.out.println(i + " " + orderedItems.get(i - 1).getName());
		}
	}

	/**
	 * Print the invoice of this order.
	 * 
	 * @return An array of string containing the information about the order
	 *         invoice. returns null if the order contains no item.
	 */
	public String[] printOrderInvoice() {
		if (orderedItems.size() == 0) {
			System.out.println("There is no items in this order!");
			return null;
		}
		String[] invoice = new String[orderedItems.size() + 5];
		System.out.println("Employee name(creator):" + (invoice[0] = creator.getName()));
		System.out.println("Customer name: " + (invoice[1] = customer.getCustomerName()));
		System.out.println("Table allocated: " + (invoice[2] = String.valueOf(tableNumber)));
		invoice[3] = String.valueOf(timeStamp.getTime());
		System.out.println("Order created at: " + timeStamp.toString());
		System.out.println("Ordered Items: ");
		float afterTax, afterDiscount, total = 0;
		for (int i = 1; i <= orderedItems.size(); i++) {
			OrderItem item = orderedItems.get(i - 1);
			afterTax = cTax.calculateTax(item.getPrice());
			afterDiscount = cDiscount.calculateDiscount(afterTax);
			total += afterDiscount;
			invoice[i + 3] = String.valueOf(item.getItemID()) + " " + String.valueOf(afterDiscount);
			System.out.printf(i + " " + item.getName() + "\toriginal price: " + item.getPrice() + "tax: "
					+ (afterTax - item.getPrice()) + "\tfinal price: %.2f \n", afterDiscount);
		}
		invoice[orderedItems.size() + 4] = String.valueOf(total);
		System.out.printf("Total Price: %.2f \n", total);
		System.out.println("Thank you! Have a nice day! ");
		return invoice;

	}

	/**
	 * get method for Staff object.
	 * 
	 * @return Staff object
	 */
	public Staff getEmployee() {
		return creator;
	}

	/**
	 * get method for customer object
	 * 
	 * @return customer object
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * get method for table number
	 * 
	 * @return table number
	 */
	public int getTableNumber() {
		return this.tableNumber;
	}

	/**
	 * get method for time stamp
	 * 
	 * @return time stamp
	 */
	public Date getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * get method for order ID
	 * 
	 * @return order ID
	 */
	public int getOrderId() {
		return this.OrderID;
	}

	/**
	 * get method for the ordered items
	 * 
	 * @return ordered items
	 */
	public ArrayList<OrderItem> getItems() {
		return orderedItems;
	}

	/**
	 * setter for the order number
	 * 
	 * @param newOrderNumber
	 */
	public static void setOrderNumber(int newOrderNumber) {
		orderNumber = newOrderNumber;
	}

	/**
	 * getter for the order number
	 * 
	 * @return
	 */
	public static int getOrderNumber() {
		return orderNumber;
	}
}
