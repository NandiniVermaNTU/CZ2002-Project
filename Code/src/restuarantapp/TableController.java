/**
 * Controls table
 * @author Pua Yong Hao
 * @version 1.4
 */
package restuarantapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TableController {

	/**
	 * Array list for tables
	 */
	private static ArrayList<Table> tableList;

	/**
	 * Directories for Table csv file
	 */
	private static String tableDir = "src\\csv\\Table.csv";

	/**
	 * initialize all tables by reading from csv file
	 */
	public static void init() {
		tableList = new ArrayList<Table>();
		try {
			readCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * readsCSV file and initializes variables. called during init
	 * 
	 * @throws FileNotFoundException
	 */
	private static void readCSV() throws FileNotFoundException {
		try {
			File file = new File(tableDir);
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
	 * process a read string array from csv and convert into table object
	 * 
	 * @param arr Array of string from CSV
	 */
	private static void processCSVObject(String arr[]) {
		int tableNumber = Integer.parseInt(arr[0]);
		int seatingCapacity = Integer.parseInt(arr[1]);
		boolean availability = Boolean.parseBoolean(arr[2]);
		int customerID = Integer.parseInt(arr[3]);
		Table t = new Table(tableNumber, seatingCapacity, availability, customerID);
		tableList.add(t);
	}

	/**
	 * writes all current table data and changes to csv
	 */
	private static void writeCSV() {
		File file = new File(tableDir);
		try {
			FileWriter fw = new FileWriter(file);
			PrintWriter out = new PrintWriter(fw);
			out.append("Table Number,Seating Capacity,Availability,Customer ID\n");
			for (int i = 0; i < tableList.size(); i++) {
				Table t = tableList.get(i);
				String newLine = "" + t.getTableNumber() + "," + t.getCapacity() + "," + t.getAvailability() + ","
						+ t.getCustomerID();
				out.append(newLine + "\n");
			}
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Allocate table by setting the table's availability to false. Return false it
	 * it fails
	 * 
	 * @param tableNumber Table to be allocated
	 * @return true/false
	 */
	public static boolean allocate(int tableNumber) {
		if (checkAvailability(tableNumber)) {
			Table t = tableList.get(tableNumber - 1);
			t.setAvailability();
			writeCSV();
			return true;
		}
		return false;
	}

//	/**
//	 * Allocate table for walked in customer by setting the allocated table's
//	 * availability to false and return the allocated table number. Return -1 if it
//	 * fails
//	 * 
//	 * @param pax The number of customers
//	 * @return tableNumber
//	 */
//	public static int allocate(int pax, int customerID) {
//		int tableNumber = getAvailableTableNumberwithCapacity(pax);
//		if (tableNumber != -1) {
//			Table t = tableList.get(tableNumber - 1);
//			t.setAvailability();
//			t.setCustomerID(customerID);
//			writeCSV();
//			return tableNumber;
//		} else
//			return -1;
//	}

	/**
	 * Set the table's availability to true
	 * 
	 * @param tableNumber The table number that is getting deallocated
	 */
	public static void deallocate(int tableNumber) {
		if (!checkAvailability(tableNumber)) {
			Table t = tableList.get(tableNumber - 1);
			t.setAvailability();
			t.setCustomerID(-1);
			writeCSV();
		}
	}

	/**
	 * Return availability of table with given table number
	 * 
	 * @param tableNumber The table number that is getting checked
	 * @return availability
	 */
	public static boolean checkAvailability(int tableNumber) {
		Table t = tableList.get(tableNumber - 1);
		return t.getAvailability();
	}

	/**
	 * Return a list of table number that is available and has enough seating
	 * capacity for customers
	 * 
	 * @param pax Number of customer
	 * @return availableTableWithCapacity
	 */
	public static ArrayList<Integer> getAvailableTables(int pax) {
		ArrayList<Integer> availableTable = new ArrayList<Integer>();
		for (int i = 0; i < tableList.size(); i++) {
			Table t = tableList.get(i);
			if (t.getCapacity() >= pax)
				if (t.getAvailability())
					availableTable.add(t.getTableNumber());
		}
		return availableTable;
	}

	/**
	 * Returns a list of table number that has enough seating capacity for customers
	 * 
	 * @param pax Number of customer
	 * @return tableWithCapacity
	 */
	public static ArrayList<Integer> getTables(int pax) {
		ArrayList<Integer> tableWithCapacity = new ArrayList<Integer>();
		for (int i = 0; i < tableList.size(); i++) {
			Table t = tableList.get(i);
			if (t.getCapacity() >= pax)
				tableWithCapacity.add(t.getTableNumber());
		}
		return tableWithCapacity;
	}

	/**
	 * Getter for the tableList
	 * 
	 * @return tableList
	 */
	public static ArrayList<Table> getTables() {
		return tableList;
	}
}
