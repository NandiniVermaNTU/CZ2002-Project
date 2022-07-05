package restuarantapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Controls order creation, printing invoice and showing revenue report
 * 
 * @author ZHANG MENGAO
 * @version 1.4
 */
public class OrderController {

	/**
	 * holds the current orders(not paid).
	 */
	private static ArrayList<Order> orders;

	/**
	 * Initialize the Staff reference who are creating orders
	 */
	private static Staff e;

	/**
	 * holding the directory to the Orders.csv
	 */
	private static final String orderDir = "src\\csv\\Order.csv";
	/**
	 * holding the directory to the Invoice.csv
	 */
	private static final String invoiceDir = "src\\csv\\Invioce.csv";

	/**
	 * Initialize the staff who are doing these operations.
	 * 
	 * @param employee staff object reference who are entering this system.
	 */
	public static void init(Staff employee) {
		e = employee;
		orders = new ArrayList<Order>();
		loadOrder();
		checkIfDue();
	}

	/**
	 * Must be called when the program terminates.
	 */
	public static void terminate() {
		storeOrder();
	}

	/**
	 * Get method for the order list.
	 * 
	 * @return An ArrayList of current orders
	 */
	public static ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * Check whether there is any order that exceeds time limit.
	 */
	public static void checkIfDue() {
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
		for (int i = orders.size() - 1; i >= 0; i--) {
			cal.setTime(orders.get(i).getTimeStamp());
			cal.add(Calendar.HOUR_OF_DAY, 1);
			if (d.after(cal.getTime())) {
				System.out.println("Warning: Order " + orders.get(i).getOrderId()
						+ " is deleted because it exceeds the one hour limit");
				orders.remove(i);
			}
		}
	}

	/**
	 * Store the current orders into csv
	 */
	private static void storeOrder() {
		StringBuilder sb = new StringBuilder();
		Order order;
		ArrayList<OrderItem> items;

		sb.append(Order.getOrderNumber());// get the current order number and store it
		sb.append("\n");
		String prefix;
		for (int i = 0; i < orders.size(); i++) {// get orders' information and store it in the string.
			order = orders.get(i);
			sb.append(order.getOrderId() + ",");
			sb.append(order.getEmployee().getEmployeeID() + ",");
			sb.append(order.getCustomer().getCustomerID() + ",");
			sb.append(order.getTableNumber() + ",");
			sb.append(order.getTimeStamp().getTime() + ",");
			items = order.getItems();
			prefix = "";
			for (OrderItem item : items) {
				sb.append(prefix);
				prefix = ",";
				sb.append(item.getItemID());
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

		File f = new File(orderDir);

		try {// write to the csv file that stores unfinished orders.
			FileWriter fw = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(sb.toString());
			writer.close();
			fw.close();
			System.out.println("Order Stored successfully.");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IO error!");
		}
	}

	/**
	 * load orders from csv
	 */
	private static void loadOrder() {
		String line = "";
		String[] tempArr;
		int size, orderId, tableNumber, employeeId, customerId;
		Date timeStamp;

		try {
			File f = new File(orderDir);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			int orderNumber = Integer.valueOf(line);
			Order.setOrderNumber(orderNumber);

			while ((line = br.readLine()) != null) {
				if (line.equals("\n")) {
					break;
				}
				tempArr = line.split(",");
				size = tempArr.length;
				orderId = Integer.valueOf(tempArr[0]);
				employeeId = Integer.valueOf(tempArr[1]);
				customerId = Integer.valueOf(tempArr[2]);
				tableNumber = Integer.valueOf(tempArr[3]);
				timeStamp = new Date(Long.valueOf(tempArr[4]));

				CalculateTax t = new GST();
				CalculateDiscount d;
				Customer customer = CustomerController.findCustomerByID(customerId);
				if (customer.getMembership())
					d = new WithDiscount();
				else
					d = new WithoutDiscount();
				Order order = new Order(orderId, StaffController.findStaffByID(employeeId), customer, tableNumber,
						timeStamp, t, d);
				for (int i = 5; i < size; i++) {
					order.addOrderItem(OrderItemController.searchItemByID(Integer.valueOf(tempArr[i])));
				}
				orders.add(order);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Order.csv not found!");
			return;
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException! Can't read order number.");
			return;
		} catch (IOException e) {
			System.out.println("IO exception");
		}
	}

	/**
	 * Method to create order and store relevant information Then direct to add
	 * Item.
	 * 
	 * @param tableNumber the table number this order allocated.
	 * @param customer    the customer who are ordering it.
	 * @return an boolean value indicating whether creation is successful.
	 */
	public static boolean createOrder(int tableNumber, Customer customer) {
		if (customer == null) {
			System.out.println("invalid customer!");
			return false;
		}

		CalculateTax t = new GST();
		CalculateDiscount d;
		if (customer.getMembership())
			d = new WithDiscount();
		else
			d = new WithoutDiscount();
		Order newOrder = new Order(e, tableNumber, customer, t, d);
		orders.add(newOrder);
		System.out.println("New order created successfully!");

		addItem(newOrder);
		return true;
	}

	/**
	 * Method to create order at the specified time stamp and store relevant
	 * information Then direct to add Item.
	 * 
	 * @param tableNumber the table number this order allocated.
	 * @param customer    the customer who are ordering it.
	 * @param timeStamp   the time when the order was created.
	 * @return an boolean value indicating whether creation is successful.
	 */
	public static boolean createOrder(int tableNumber, Customer customer, long timeStamp) {
		if (customer == null) {
			System.out.println("invalid customer!");
			return false;
		}

		CalculateTax t = new GST();
		CalculateDiscount d;
		if (customer.getMembership())
			d = new WithDiscount();
		else
			d = new WithoutDiscount();
		Date date = new Date(timeStamp);
		Order newOrder = new Order(Order.getOrderNumber(), e, customer, tableNumber, date, t, d);
		Order.setOrderNumber(Order.getOrderNumber() + 1);
		orders.add(newOrder);
		System.out.println("New order created successfully!");

		addItem(newOrder);
		return true;
	}

	/**
	 * Print the current unpaid orders' IDs and customer's name
	 */
	public static void printCurrentOrder() {
		System.out.println("Current orders: ");
		for (int i = 0; i < orders.size(); i++) {
			System.out.println("order ID: " + orders.get(i).getOrderId() + "\tcustomer name: "
					+ orders.get(i).getCustomer().getCustomerName());
		}
	}

	/**
	 * add items to an order
	 * 
	 * @param order the order reference which is going to be changed
	 */
	public static void addItem(Order order) {
		OrderItemController.printAllMenuSimplified();
		OrderItem tempItem;
		int option;
		String input;
		OrderItem item;
		boolean added;
		Scanner sc = new Scanner(System.in);

		System.out.print("Add the items: (Enter -1 to quit)");
		do {
			System.out.println("Please Select options (for more information, add [i]+[space] before the option)");
			input = sc.nextLine();

			if (input.split(" ")[0].equals("i")) {
				option = Integer.valueOf(input.split(" ")[1]);
				tempItem = OrderItemController.searchItemByID(option);
				if (tempItem == null) {
					System.out.println("invalid input!");
					continue;
				}
				tempItem.printAttributes();
			} else {
				option = Integer.valueOf(input.split(" ")[0]);
				if (option == -1)
					break;
				item = OrderItemController.searchItemByID(option);
				added = order.addOrderItem(item);
				if (added) {
					System.out.println("Successfully added");
				} else {
					System.out.println("invalid input!");
				}
			}
		} while (option != -1);
	}

	/**
	 * Delete item from an order
	 * 
	 * @param order the order reference which is going to be changed
	 */
	public static void deleteItem(Order order) {

		System.out.println("Please enter the index of the item you want to delete");
		System.out.println("(You can input multiple items sperated by space and end by hitting enter");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		Integer[] indexs = new Integer[input.split(" ").length];
		try {
			for (int i = 0; i < indexs.length; i++) {
				indexs[i] = Integer.valueOf(input.split(" ")[i]);
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid output!");
			return;
		}
		Arrays.sort(indexs);
		boolean status;
		for (int i = indexs.length - 1; i >= 0; i--) {
			status = order.deleteOrderItem(indexs[i] - 1);
			if (!status) {
				System.out.println("invalid input: " + indexs[i]);
				return;
			}
		}
	}

	/**
	 * Delete the order
	 * 
	 * @param index The index of the order
	 */
	public static void deleteOrder(int index) {
		System.out.println("Are you sure to delete this order? [y/n]");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if (s.equals("y") || s.equals("Y")) {
			TableController.deallocate(orders.get(index).getTableNumber());
			orders.remove(index);
			System.out.println("Deleted!");
		} else if (s.equals("n") || s.equals("N")) {
			return;
		} else
			System.out.println("Invalid input!");
	}

	/**
	 * print invoice and store the data into the csv.
	 * 
	 * @param order
	 */
	public static void printInvoice(Order order) {
		String[] invoice = order.printOrderInvoice();

		if (invoice == null)
			return;

		TableController.deallocate(order.getTableNumber());
		String newInvoice = String.join(",", invoice);
		File f = new File(invoiceDir);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
			writer.write(newInvoice + "\n");
			writer.close();
			System.out.println("Invoice stored successfully");
			orders.remove(order);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IO error!");
		}
	}

	/**
	 * Print the revenue for the last several days.
	 * 
	 * @param days (number of day) specifies the range.
	 */
	public static void printRevenue(int days) {
		File f;
		FileReader fr;
		BufferedReader br;
		String[] tempArr, tempOrderItemArr;
		String line = "";

		ArrayList<OrderItem> orderItems = OrderItemController.getMenu("dummy");// dummy string to get all the items
		float[] itemRevenue = new float[orderItems.size()];
		int[] itemAmount = new int[orderItems.size()];
		float totalRevenue = 0;
		int itemId;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -days);
		Date bound = c.getTime();
		Date invoiceTime;

		try {
			f = new File(invoiceDir);
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				if (line.equals("\n") || line.equals("")) {
					break;
				}
				tempArr = line.split(",");
				invoiceTime = new Date(Long.valueOf(tempArr[3]));
				if (invoiceTime.after(bound)) {
					for (int i = 4; i < tempArr.length - 1; i++) {
						tempOrderItemArr = tempArr[i].split(" ");
						if (tempOrderItemArr.length != 2) {
							System.out.println("format error!");
							return;
						}
						itemId = Integer.valueOf(tempOrderItemArr[0]);
						itemAmount[itemId - 1]++;
						itemRevenue[itemId - 1] += Float.valueOf(tempOrderItemArr[1]);
					}
					totalRevenue += Float.valueOf(tempArr[tempArr.length - 1]);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("IO exception");
			return;
		}

		OrderItem tempOrderItem;
		System.out.println("Here is the report for the revenue in the last " + days + "day(s)");
		System.out.println("item id\titem name\t\ttitem type\titem sold\titem revenue");
		for (int i = 0; i < orderItems.size(); i++) {
			if (itemAmount[i] == 0)
				continue;
			tempOrderItem = OrderItemController.searchItemByID(i + 1);
			System.out.print((i + 1) + "\t" + tempOrderItem.getName() + "\t");
			System.out.print(tempOrderItem.getType() + "\t" + itemAmount[i]);
			System.out.printf("\t%.2f\n", itemRevenue[i]);
		}
		System.out.println("Total revenue: " + totalRevenue);
	}
}
