
/**
 * Control class for Staff
 * @author LIN ZIXING
 *
 */
package restuarantapp;

import java.awt.*;
import java.util.*;
import java.io.*;


public class StaffController {
	/**
	 * File directory for Staff CSV
	 */
	private static String fileDir = "src\\csv\\Staff.csv";
	/**
	 * ArrayList storing the staff at the restaurant
	 */
	private static ArrayList<Staff> staffList;
	/**
	 * Initialize variables and read from csv
	 */
	public static void init() {
		staffList = new ArrayList<Staff>();
		try {
			readCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
	
	/**
	 * @param staffID Staff ID to search for
	 * @return Staff Staff Object that corresponds to the ID parameter. If none found, return null
	 */
	public static Staff findStaffByID(int staffID) {
		for(int i =0;i<staffList.size();i++) {
			if(staffList.get(i).getEmployeeID() == staffID)
				return staffList.get(i);
		}
		return null;
	}
	
	/**
	 * Reads staff data from csv
	 * @throws FileNotFoundException
	 */
	private static void readCSV() throws FileNotFoundException {//read csv for menuitems
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
	 * @param arr String array read from csv
	 */
	private static void processCSVObject(String arr[]){
		int staffID = Integer.parseInt(arr[0]);
		String name = arr[1];
		gender g = gender.valueOf(arr[2]);
		jobTitle title = jobTitle.valueOf(arr[3]);
		Staff s = new Staff(name, staffID, title, g);
		addStaff(s);
	}
	
	/**
	 * Adds a staff object to the staff list
	 * @param s Staff object to add to list
	 */
	public static void addStaff(Staff s) {
		staffList.add(s);
	}

	/**
	 * Prints staff list
	 */
	public static void printStaffList(){
		for(int i =0;i<staffList.size();i++) {
			System.out.printf("ID:%d Name:%s \n",staffList.get(i).getEmployeeID(), staffList.get(i).getName());
		}
	}

}
