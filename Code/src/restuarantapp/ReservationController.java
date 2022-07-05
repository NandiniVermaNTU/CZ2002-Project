package restuarantapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Controls all the operation on reservation.
 * 
 * @author ZHANG MENGAO
 * @version 1.1
 */
public class ReservationController {
	/**
	 * store the list of reservations
	 */
	private static ArrayList<Reservation> rList;
	/**
	 * holds the directory to the reservation records.
	 */
	private static String rDir = "src\\csv\\Reservation.csv";

	/**
	 * necessary to initialize the controller
	 */
	public static void init() {
		rList = new ArrayList<Reservation>();
		loadReservation();
		checkIfDue();
	}

	/**
	 * necessary to execute when terminating the program.
	 */
	public static void terminate() {
		storeReservation();
	}

	/**
	 * method to store the reservations to the csv file
	 */
	private static void storeReservation() {
		StringBuilder sb = new StringBuilder();
		Reservation tempR;

		for (int i = 0; i < rList.size(); i++) {// get orders' information and store it in the string.
			tempR = rList.get(i);
			sb.append(tempR.getDate().getTime() + ",");
			sb.append(tempR.getPax() + ",");
			sb.append(tempR.getCustomerId() + ",");
			sb.append(tempR.getName() + ",");
			sb.append(tempR.getContact() + ",");
			sb.append(tempR.getTableNumber());

			sb.append("\n");
		}

		File f = new File(rDir);

		try {// write to the csv file that stores unfinished orders.
			FileWriter fw = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(sb.toString());
			writer.close();
			fw.close();
			System.out.println("Reservation stored successfully.");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IO error!");
		}
	}

	/**
	 * method to load the reservations from the csv file
	 */
	private static void loadReservation() {
		File f = new File(rDir);
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			Reservation tempR;
			Date date;
			int pax, tableNumber, customerId;
			String name, contact;
			while ((line = br.readLine()) != null) {
				tempArr = line.split(",");
				date = new Date(Long.valueOf(tempArr[0]));
				pax = Integer.valueOf(tempArr[1]);
				customerId = Integer.valueOf(tempArr[2]);
				name = tempArr[3];
				contact = tempArr[4];
				tableNumber = Integer.valueOf(tempArr[5]);
				tempR = new Reservation(date, pax, customerId, name, contact, tableNumber);
				rList.add(tempR);
			}
			br.close();
			fr.close();
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * checks whether there is any reservation has expired.
	 */
	public static void checkIfDue() {
		Date currentT = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentT);
		c.add(Calendar.MINUTE, -10);
		Date expireT = c.getTime();
		for (int i = rList.size() - 1; i >= 0; i--) {
			if (rList.get(i).getDate().before(expireT)) {
				System.out.println("\nWarning: Reservation for " + rList.get(i).getName() + "is Removed");
				rList.remove(i);
			}
		}
	}

	/**
	 * get method of the list of reservations
	 * 
	 * @return the list of reservations
	 */
	public static ArrayList<Reservation> getReservations() {
		return rList;
	}

	/**
	 * print the information of current reservations.
	 */
	public static void printReservations() {
		System.out.println("Current Reservations");
		int i = 0;
		for (Reservation r : rList) {
			System.out.print("id: " + i + " ");
			System.out.print("Customer Name: " + r.getName() + "\tContact: " + r.getContact() + "\t");
			System.out.println("Table Number: " + r.getTableNumber() + "\tPAX: " + r.getPax() + "\tDate: "
					+ r.getDate().toString());
			i++;
		}
	}

	/**
	 * add the reservation after checking whether there is any conflict and return
	 * the final table number decided.
	 * 
	 * @param date       The start time of the reservation
	 * @param pax        The number of person this reservation is going to serve.
	 * @param customerId The customer Id of the reservation
	 * @param name       The name of the customer who made this reservation.
	 * @param contact    The contact information of the customer.
	 * @param tableList  The possible table numbers.
	 * @return the final table number this reservation is on. -1 if no suitable
	 *         table.
	 */
	public static int addReservation(Date date, int pax, int customerId, String name, String contact,
			ArrayList<Integer> tableList) {
		checkIfDue();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY, 1);
		if (date.before(c.getTime())) {// the reservation must be made at least one hour in advance.
			System.out
					.println("Too late to make a reservation! \nPlease make reservation at least one hour in advance");
			return -1;
		}
		Date endTime;
		Date endTimeNew;
		for (Reservation r : rList) {
			if (name.equals(r.getName())) {// check whether there is any customer book multiple times.
				System.out.println("One customer makes multiple reservation not allowed");
				return -1;
			}

			for (int i = tableList.size() - 1; i >= 0; i--) {
				if (tableList.get(i) == r.getTableNumber()) {// check whether there is conflict in time between two
					// reservations.
					c.setTime(r.getDate());
					c.add(Calendar.HOUR_OF_DAY, 1);
					endTime = c.getTime();
					c.setTime(date);
					c.add(Calendar.HOUR_OF_DAY, 1);
					endTimeNew = c.getTime();
					if (r.getDate().after(endTimeNew) || endTime.before(date)) {
						// if start time of the reservation is after the end time of the new reservation
						// or end time of the reservation is before the start time of new
						// reservation,then no conflict.
						continue;
					} else {
						// if have conflict, then remove this table from list.
						tableList.remove(i);
						break;
					}
				}
			}
		}
		if (tableList.size() == 0) {// if the reservation going to add has conflicts with the reservation on all
									// possible tables
			return -1;
		} else {// if not, just choose the first element.
			Reservation r = new Reservation(date, pax, customerId, name, contact, tableList.get(0));
			rList.add(r);
			return tableList.get(0);
		}
	}

	/**
	 * @param index the index of the reservation that are going to be deleted
	 * @return a boolean value indicating whether deletion is successful
	 */
	public static boolean deleteReservation(int index) {
		if (index < 0 || index >= rList.size()) {
			return false;
		} else {
			rList.remove(index);
			return true;
		}
	}

	/**
	 * check in function when a customer has come to the restaurant and ready to
	 * order.
	 * 
	 * @param id the customer's id who are going to check in
	 * @return the information of the reservation. 0-time 1-pax 2-customerId 3-name
	 *         4-contact 5-tableNumber; Returns null if not successful.
	 */
	public static String[] checkIn(int id) {
		Date currentTime = new Date();
		Date bound;
		String[] result = null;
		for (int i = 0; i < rList.size(); i++) {
			if (rList.get(i).getCustomerId() == id) {
				Calendar c = Calendar.getInstance();
				c.setTime(rList.get(i).getDate());
				c.add(Calendar.MINUTE, 10);
				bound = c.getTime();
				if (currentTime.after(rList.get(i).getDate()) && currentTime.before(bound)) {
					result = new String[6];
					result[0] = String.valueOf(rList.get(i).getDate().getTime());
					result[1] = String.valueOf(rList.get(i).getPax());
					result[2] = String.valueOf(rList.get(i).getCustomerId());
					result[3] = rList.get(i).getName();
					result[4] = rList.get(i).getContact();
					result[5] = String.valueOf(rList.get(i).getTableNumber());
					rList.remove(i);
					break;
				}

			}
		}
		return result;
	}

	/**
	 * Checks what tables are not occupied in the next one hour.
	 * 
	 * @param tableList An arraylist of Integer containing all the tableNumbers
	 * @return the arrayList of Integer containing the tableNumber that is not
	 *         occupied in the next one hour.
	 */
	public static ArrayList<Integer> checkReservation(ArrayList<Integer> tableList) {
		Date currentTime = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentTime);
		c.add(Calendar.HOUR_OF_DAY, 1);
		Date bound = c.getTime();

		for (Reservation r : rList) {
			if (r.getDate().before(bound)) {
				for (int i = tableList.size() - 1; i >= 0; i--) {
					if (tableList.get(i) == r.getTableNumber()) {
						tableList.remove(i);
					}
				}
			}
		}
		return tableList;
	}
}
