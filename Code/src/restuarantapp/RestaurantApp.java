package restuarantapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Boundary Class and primary UI for the system
 * 
 * @author LIN ZIXING
 *
 */
public class RestaurantApp {
	static Scanner sc = new Scanner(System.in);
	/**
	 * The staff that is currently using the application
	 */
	static Staff s = null;

	/**
	 * Landing page for application
	 */
	public static void staffSelect() {
		System.out.println("****[Restaurant Reservation and Point of Sale System]****");

		while (s == null) {
			System.out.println("Please enter your staff ID");
			int staffID = sc.nextInt();
			s = StaffController.findStaffByID(staffID);
			if (s == null)
				System.out.println("Please enter a valid staff ID");
		}
		System.out.printf("Hello, %s\n", s.getName());
	}

	/**
	 * Main menu for application after staff log in
	 */
	public static void mainMenu() {
		System.out.println("\nWelcome to the Restaurant Reservation and Point of Sale System");

		int input;
		do {
			System.out.println("=====[MAIN MENU]=====");
			System.out.println("Please select the operations you would like to perform");
			System.out.println("1. Reservation Functions");
			System.out.println("2. Table/Check in Functions");
			System.out.println("3. Order and Invoice Functions");
			System.out.println("4. Food Menu Functions");
			System.out.println("5. Sales and Revenue Functions");
			System.out.println("6. Exit Application");
			input = sc.nextInt();

			switch (input) {
			case 1:
				reservationMenu();
				break;
			case 2:
				tableMenu();
				break;
			case 3:
				orderMenu();
				break;
			case 4:
				foodMenu();
				break;
			case 5:
				printRevenue();
				break;
			case 6:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		} while (input != 6);
	}

	/**
	 * Menu for reservation functions
	 */
	public static void reservationMenu() {
		int input = 0;
		while (input != 4) {
			System.out.println("\n=====[Reservation Functions]=====");
			System.out.println("1. Create new reservation");
			System.out.println("2. View current reservations");
			System.out.println("3. Delete reservation");
			System.out.println("4. Return to main menu");
			input = sc.nextInt();
			switch (input) {
			case 1:
				createReservation();
				break;
			case 2:
				ReservationController.printReservations();
				break;
			case 3:
				int resToDel;
				System.out.println("Please input the Reservation ID to delete");
				resToDel = sc.nextInt();
				ReservationController.deleteReservation(resToDel);
				break;
			case 4:
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("Invalid input! Please try again.");
				break;
			}
		}
	}

	/**
	 * Menu for reservation creation
	 */
	public static void createReservation() {
		ReservationController.checkIfDue();
		int pax = 0;
		System.out.println("\n=====[New Reservation]=====");
		System.out.println("Please enter customer id:");
		int id = sc.nextInt();
		Customer c = CustomerController.findCustomerByID(id);
		System.out.println("Creating reservation for " + c.getCustomerName());

		do {
			System.out.println("Please enter number of pax between 1 to 10");
			pax = sc.nextInt();
			if (pax <= 10)
				break;
			else
				System.out.println("Please enter a valid value between 1 to 10");
		} while (pax > 10);

		String pattern = "dd-MM-yyyy,HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String inputTime;
		Date reservationTime;
		System.out.println("Please enter the time in the format of 'dd-mm-yyyy,hh:mm:ss'");
		while (true) {
			try {
				inputTime = sc.next();
				reservationTime = simpleDateFormat.parse(inputTime);
				break;
			} catch (ParseException e) {
				System.out.println("time format does not match!");
			}
		}

		int tableNumber = ReservationController.addReservation(reservationTime, pax, id, c.getCustomerName(),
				c.getCustomerContact(), TableController.getTables(pax));
		if (tableNumber != -1) {
			System.out.println("Succseeful");
		} else
			System.out.println("Failed");
	}

	/**
	 * Menu for table functions
	 */
	public static void tableMenu() {
		int input = 0;
		while (input != 4) {
			System.out.println("\n=====[Check in/Table Functions]=====");
			System.out.println("1. Check in [WALK IN CUSTOMER]");
			System.out.println("2. Check in [RESERVED CUSTOMER]");
			System.out.println("3. Check table availability");
			System.out.println("4. Return to main menu");
			input = sc.nextInt();
			switch (input) {
			case 1:
				int pax = 0;
				int customerID = 0;
				System.out.println("Please input the pax of customers");
				pax = sc.nextInt();
				if (pax >= 1) {
					ArrayList<Integer> availableTables = ReservationController
							.checkReservation(TableController.getAvailableTables(pax));
					if (availableTables.size() == 0) {
						System.out.println("Allocation failed");
						break;
					}
					System.out.println("Please input customerID");
					customerID = sc.nextInt();
					System.out.println(availableTables.get(0));
					TableController.allocate(availableTables.get(0));

					System.out.printf("Successful Allocation - Customer %d has been allocated to Table Numer %d\n",
							customerID, availableTables.get(0));
					OrderController.createOrder(availableTables.get(0),
							CustomerController.findCustomerByID(customerID));
				}
				else
					System.out.println("This is not a valid pax of customer");
				break;
			case 2:
				System.out.println("Please input customer id");
				int id = sc.nextInt();
				String resInfo[] = ReservationController.checkIn(id);
				if (resInfo != null) {
					TableController.allocate(Integer.valueOf(resInfo[5]));
					OrderController.createOrder(Integer.parseInt(resInfo[5]),
							CustomerController.findCustomerByID(Integer.valueOf(resInfo[2])), Long.valueOf(resInfo[0]));
				} else
					System.out.println("This is not a valid check in");

				break;
			case 3:
				System.out.println("Table id\tAvailability");
				for (int i = 0; i < TableController.getTables().size(); i++) {
					System.out.println((i + 1) + "\t\t" + TableController.checkAvailability(i + 1));
				}
				break;
			case 4:
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("Invalid input, please try again");
				break;
			}
		}
	}

	/**
	 * Order Menu functions
	 */
	public static void orderMenu() {
		OrderController.checkIfDue();// remove the order that takes longer than one hour;
		int option;
		do {
			System.out.println("\n*****[Order Menu]*****");
			System.out.println("1. View current orders");
			System.out.println("2. View/Change order detail");
			System.out.println("3. Quit to the main menu");
			System.out.println("********************************");
			option = sc.nextInt();
			switch (option) {
			case 1:
				OrderController.printCurrentOrder();
				break;
			case 2:
				orderDetail();
				break;
			case 3:
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("Invalid input! Please try again");
			}
		} while (option != 3);
	}

	/**
	 * to print revenue of a specified range.
	 */
	private static void printRevenue() {
		int input;
		System.out.println("Please enter the range you want (in days):(-1 to exit)");
		do {
			input = sc.nextInt();
			if (input == -1)
				break;
			else if (input < -1)
				System.out.println("Invalid input!");
			else {
				OrderController.printRevenue(input);
				break;
			}
		} while (true);
	}

	/**
	 * Extra Order Menu to perform further functions on a specific order
	 */
	public static void orderDetail() {
		System.out.println("Please enter the order ID");
		System.out.println("(If you don't know the order ID, please enter -1 to return to last level)");
		Scanner sc = new Scanner(System.in);
		boolean found = false;
		int id = sc.nextInt();
		for (int i = 0; i < OrderController.getOrders().size(); i++) {
			if (OrderController.getOrders().get(i).getOrderId() == id) {
				found = true;

				int option;
				do {
					OrderController.getOrders().get(i).printOrderDetail();
					System.out.println("*****************************************");
					System.out.println("*\tPlease select the option\t*");
					System.out.println("*\t1. Add items to this order\t*");
					System.out.println("*\t2. Delete items from this order\t*");
					System.out.println("*\t3. Cancel this order\t\t*");
					System.out.println("*\t4. Print invoice of this order\t*");
					System.out.println("*\t5. Back to last menu\t\t*");
					System.out.println("*****************************************");

					option = sc.nextInt();
					switch (option) {
					case 1:
						OrderController.addItem(OrderController.getOrders().get(i));
						break;
					case 2:
						OrderController.deleteItem(OrderController.getOrders().get(i));
						break;
					case 3:
						TableController.deallocate(OrderController.getOrders().get(i).getTableNumber());
						OrderController.deleteOrder(i);
						break;
					case 4:
						TableController.deallocate(OrderController.getOrders().get(i).getTableNumber());
						OrderController.printInvoice(OrderController.getOrders().get(i));
						break;
					case 5:
						System.out.println("Returning to last menu");
						break;
					default:
						System.out.println("Invalid input! Please try again.");
						break;
					}
				} while (option != 5 && option != 4 && option != 3);
			}
		}
		if (!found)
			System.out.println("Sorry, we cannot find such order.");
		return;
	}

	/**
	 * Menu to manage food items
	 */
	public static void foodMenu() {
		int input = 0;
		int input2 = 0;
		while (input != 5) {
			System.out.println("\n=====[Food Menu Functions]=====");
			System.out.println("1. View Menu");
			System.out.println("2. Add new food item or set package");
			System.out.println("3. Delete food item or set package");
			System.out.println("4. Edit food item or set info (Name/Price/Description)");
			System.out.println("5. Return to main menu");
			input = sc.nextInt();
			switch (input) {
			case 1:
				foodDisplayMenu();
				break;
			case 2:
				editOrderItemMenu(true);
				break;
			case 3:
				editOrderItemMenu(false);
				break;
			case 4:
				editFoodInfoMenu();
				break;
			case 5:
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("Invalid input! Please try again.");
				break;
			}
		}
	}

	/**
	 * Menu to display food
	 */
	public static void foodDisplayMenu() {
		int input = 0;
		while (input != 7) {
			System.out.println("\n=====[Menu Display Function]=====");
			System.out.println("1. View Main Course Menu");
			System.out.println("2. View Sides Menu");
			System.out.println("3. View Drinks Menu");
			System.out.println("4. View Dessert Menu");
			System.out.println("5. View Promotional Set menu");
			System.out.println("6. View all menu items");
			System.out.println("7. Return to previous menu");
			input = sc.nextInt();
			switch (input) {
			case 1:
				OrderItemController.printMenuItems(OrderItemType.MainCourse);
				break;
			case 2:
				OrderItemController.printMenuItems(OrderItemType.Sides);
				break;
			case 3:
				OrderItemController.printMenuItems(OrderItemType.Drinks);
				break;
			case 4:
				OrderItemController.printMenuItems(OrderItemType.Desserts);
				break;
			case 5:
				OrderItemController.printMenuItems(OrderItemType.Set);
				break;
			case 6:
				OrderItemController.printAllMenuSimplified();
				break;
			case 7:
				System.out.println("Returning to previous menu");
				break;
			default:
				System.out.println("Invalid input! Please try again.");
				break;
			}
		}
		// foodMenu();
	}

	/**
	 * Menu to edit an order item
	 * 
	 * @param add Boolean to identify if it will perform an adding or removal
	 *            function
	 */
	public static void editOrderItemMenu(boolean add) {
		int input = 0;
		if (add) {
			String name = "";
			float price = 0.0f;
			OrderItemType type = OrderItemType.MainCourse;
			String description = "";
			System.out.println("Please select item type: ");
			System.out.println("1. Main Course ");
			System.out.println("2. Sides ");
			System.out.println("3. Drinks");
			System.out.println("4. Desserts");
			System.out.println("5. Sets");
			System.out.println("6. Return to previous menu");
			while(input<=0||input>5) {
				input = sc.nextInt();
				switch (input) {
				case 1:
					type = OrderItemType.MainCourse;
					break;
				case 2:
					type = OrderItemType.Sides;
					break;
				case 3:
					type = OrderItemType.Drinks;
					break;
				case 4:
					type = OrderItemType.Desserts;
					break;
				case 5:
					type = OrderItemType.Set;
					break;
				case 6:
					System.out.println("Returning to main menu");
					return;
				default:
					System.out.println("Please provide a valid input");
					break;
				}
			}

			System.out.println("Please enter item name: ");
			sc.nextLine();
			name = sc.nextLine();
			System.out.println("Please enter item price: ");
			price = sc.nextFloat();
			System.out.println("Please enter item description: ");
			sc.nextLine();
			description = sc.nextLine();
			if (type == OrderItemType.Set) {
				OrderItemController.printAllMenuSimplified();
				System.out.print("Input item IDs to the set: (Enter -1 to quit)");
				input = 0;
				ArrayList<MenuItem> ml = new ArrayList<MenuItem>();
				while (input != -1) {
					input = sc.nextInt();

					if (input != -1 &&OrderItemController.searchItemByID(input)!= null )
						ml.add((MenuItem) OrderItemController.searchItemByID(input));
					else if(OrderItemController.searchItemByID(input)== null&&input!=-1)
						System.out.println("Please provide a valid input!");
				}
				if(ml.size()>0) {
					PromotionalSetPackage p1 = new PromotionalSetPackage(++OrderItemController.maxID, name, price,
							description, ml);
					OrderItemController.addMenuItem(p1);
					System.out.println("******The following set has been added******");
					p1.printAttributes();
				}
				else {
					System.out.println("There are no items in this set package! Set creation failed");
				}
			} else {
				MenuItem m = new MenuItem(++OrderItemController.maxID, type, name, price, description);
				OrderItemController.addMenuItem(m);
				System.out.println("******The following item has been added******");
				m.printAttributes();
			}

		} else {
			System.out.println("Please enter item ID to remove");
			input = sc.nextInt();
			System.out.println("xxxxx Item to be removed: xxxxx");
			OrderItemController.searchItemByID(input).printAttributes();
			if (OrderItemController.delete(input) == 0)
				System.out.println("Item deletion failed!");
			else
				System.out.println("Item successfully deleted!");

		}
		// foodMenu();
	}

	/**
	 * Menu to edit order item info, or edit contents of a set
	 */
	public static void editFoodInfoMenu() {
		int input = 0;
		int input2 = 0;
		int input3 = 0;
		System.out.println("Please select the operation to perform:");
		System.out.println("1. Edit Info of food item or set (Name, Price, Description)");
		System.out.println("2. Add item to existing set package");
		System.out.println("3. Remove item from existing set package");
		System.out.println("4. Return to previous menu");
		input = sc.nextInt();
		switch (input) {
		case 1:
			String name;
			float price;
			String desc;
			System.out.println("Please enter the item ID of item to edit");
			input2 = sc.nextInt();
			if (OrderItemController.searchItemByID(input2) != null
					&& !OrderItemController.searchItemByID(input2).deleted) {
				OrderItemController.searchItemByID(input2).printAttributes();
				System.out.println("Please enter new information");
				System.out.println("Please enter new name (Enter -1 to retain name):");
				sc.nextLine(); // clear buffer
				name = sc.nextLine();
				if (!name.contains("-1"))
					OrderItemController.searchItemByID(input2).setName(name);

				System.out.println("Please enter new price (Enter -1 to retain price):");
				price = sc.nextFloat();
				if (price != -1)
					OrderItemController.searchItemByID(input2).setPrice(price);

				System.out.println("Please enter new description (Enter -1 to retain description):");
				sc.nextLine(); // clear buffer
				desc = sc.nextLine();
				if (!desc.contains("-1"))
					OrderItemController.searchItemByID(input2).setDescription(desc);

				System.out.println("******Current Item Info******");
				OrderItemController.searchItemByID(input2).printAttributes();

			} else {
				System.out.println("Error - Item not found or deleted");
			}
			break;
		case 2:
			System.out.println("Please enter the item ID of the set to add to");
			input2 = sc.nextInt();

			if (OrderItemController.searchItemByID(input2).getType() == OrderItemType.Set
					&& (OrderItemController.searchItemByID(input2) != null
							&& !OrderItemController.searchItemByID(input2).deleted)) {
				OrderItemController.searchItemByID(input2).printAttributes();
				System.out.println("Please enter the item ID of the food item you wish to add to the set");
				input3 = sc.nextInt();
				if (OrderItemController.searchItemByID(input3) != null
						&& !OrderItemController.searchItemByID(input3).deleted) {
					((PromotionalSetPackage) OrderItemController.searchItemByID(input2))
							.addPackageItem((MenuItem) OrderItemController.searchItemByID(input3), true);
					System.out.println("Item successfully added!");
					OrderItemController.searchItemByID(input2).printAttributes();

				} else
					System.out.println("Error - Item not found or deleted");
			} else
				System.out.println("Error - Set not found or deleted");
			break;
		case 3:
			System.out.println("Please enter the item ID of the set to remove from");
			input2 = sc.nextInt();
			if (OrderItemController.searchItemByID(input2).getType() == OrderItemType.Set
					&& (OrderItemController.searchItemByID(input2) != null
							|| OrderItemController.searchItemByID(input2).deleted)) {
				((PromotionalSetPackage) OrderItemController.searchItemByID(input2)).printAttributes();
				System.out.println("Please enter the item ID to remove from this set");
				input3 = sc.nextInt();
				((PromotionalSetPackage) OrderItemController.searchItemByID(input2)).removePackageItem(input3);
				System.out.printf("Item %d removed!\n", input3);
			} else
				System.out.println("Error - Set not found or deleted");
			break;
		case 4:
			System.out.println("Returning to previous menu");
			break;
		}

	}

	public static void main(String args[]) {
		CustomerController.init();
		OrderItemController.init();
		StaffController.init();
		staffSelect();
		OrderController.init(s);
		ReservationController.init();
		TableController.init();
		mainMenu();
		OrderController.terminate();
		ReservationController.terminate();
	}
}
