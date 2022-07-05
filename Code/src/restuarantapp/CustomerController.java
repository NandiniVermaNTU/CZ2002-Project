/**
 * Control class for Customer
 * @author Nandini Verma
 *
 */

package restuarantapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerController {
	/**
	 * File directory for Customer CSV
	 */
	private static String fileDir = "src\\csv\\Customer.csv";
	/**
	 * ArrayList storing the Customer at the restaurant
	 */
	private static ArrayList<Customer> customerList;

	/**
	 * Initialize variables and read from csv
	 */
	public static void init() {
		customerList = new ArrayList<Customer>();
		try {
			readCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	/**
	 * @param customerID Customer ID to search for
	 * @return Customer Customer Object that corresponds to the ID parameter. If
	 *         none found, return null
	 */
	public static Customer findCustomerByID(int customerID) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getCustomerID() == customerID)
				return customerList.get(i);
		}
		return null;
	}

	/**
	 * Reads customer data from csv
	 * 
	 * @throws FileNotFoundException
	 */
	private static void readCSV() throws FileNotFoundException {// read csv for menuitems
		try {
			File file = new File(fileDir);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				if (lineCount >= 1) {
					tempArr = line.split(",");
					processCSVObject(tempArr);

				}
				lineCount++;
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * Process the string array read from csv
	 * 
	 * @param arr String array read from csv
	 */
	private static void processCSVObject(String arr[]) {
		int customerID = Integer.parseInt(arr[0]);
		String customerName = arr[1];
		boolean isMembership = Boolean.parseBoolean(arr[2]);
		String customerContact = arr[3];
		gender customerGender = gender.valueOf(arr[4]);
		Customer c = new Customer(customerID, customerName, isMembership, customerContact, customerGender);
		addCustomer(c);
	}

	/**
	 * Adds a customer object to the customer list
	 * 
	 * @param c Customer object to add to list
	 */
	public static void addCustomer(Customer c) {
		customerList.add(c);
	}

	/**
	 * Prints customer list
	 */
	public static void printCustomerList() {
		for (int i = 0; i < customerList.size(); i++) {
			System.out.printf("ID:%d Name:%s \n", customerList.get(i).getCustomerID(),
					customerList.get(i).getCustomerName());
		}
	}

}
