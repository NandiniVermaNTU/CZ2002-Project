/**
 * Control Class for OrderItems
 * @author LIN ZIXING
 *
 */
package restuarantapp;
import java.awt.*;
import java.util.*;
import java.io.*;



public class OrderItemController {
		/**
		 * Array list for all order items, including sets
		 */
		private static ArrayList<OrderItem> allMenu;
		/**
		 * Array list for main courses only
		 */
		private static ArrayList<OrderItem> mainCourses; 
		/**
		 * Array list for sides only
		 */
		private static ArrayList<OrderItem> sides; 
		/**
		 * Array list for desserts only
		 */
		private static ArrayList<OrderItem> desserts; 
		/**
		 * Array list for drinks only
		 */
		private static ArrayList<OrderItem> drinks; 
		/**
		 * Array list for sets only
		 */
		private static ArrayList<OrderItem> sets;
		/**
		 * MaxID to keep track of current highest ID for adding new items
		 */
		public static int maxID = 0;
		/**
		 * Directory for Order Item CSV (Ala-carte)
		 */
		private static String itemDir = "src\\csv\\OrderItem.csv";
		/**
		 * Directory for Set packages CSV
		 */
		private static String setDir = "src\\csv\\OrderItemSet.csv";
		
		/**
		 * Initialize all order items by reading from csv file
		 */
		public static void init() {
			allMenu = new ArrayList<OrderItem>();
			mainCourses = new ArrayList<OrderItem>();
			sides = new ArrayList<OrderItem>();
			desserts = new ArrayList<OrderItem>();
			drinks = new ArrayList<OrderItem>();
			sets = new ArrayList<OrderItem>();

			try {
				readCSV(false);
				readCSV(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
			
		}
		
		/**
		 * Add menu item o into the menu lists
		 * @param o menu item to add to menu
		 */
		public static void addMenuItem(MenuItem o) {
			if(searchItemByID(o.getItemID())!=null) //if exist item with same id
				o.setID(++maxID);
			if(o.getItemID()>maxID) //set new Max ID
				maxID = o.getItemID();
			allMenu.add(o);
			switch(o.getType()) {
				case MainCourse:
					mainCourses.add(o);
					break;
				case Sides:
					sides.add(o);
					break;
				case Drinks:
					drinks.add(o);
					break;
				case Desserts:
					desserts.add(o);
					break;
			}
			//if(newItem)
				writeCSV(false);
		}
		
		/**
		 * Add set item into set and menu lists
		 * @param p setPackage to add to menu
		 */
		public static void addMenuItem(PromotionalSetPackage p){
			//System.out.println("Adding package");
			if(searchItemByID(p.getItemID())!=null) //if exist item with same id
				p.setID(++maxID);
			if(p.getItemID()>maxID) //set new Max ID
				maxID = p.getItemID();
			allMenu.add(p);
			sets.add(p);
			writeCSV(true);

		}
		/**
		 * Print menu items for a specific type
		 * @param type type of item to print
		 */
		public static void printMenuItems(OrderItemType type) {
			switch(type) {
				case MainCourse:
					printList(mainCourses);
					break;
				case Sides:
					printList(sides);
					break;
				case Drinks:
					printList(drinks);
					break;
				case Desserts:
					printList(desserts);
					break;
				case Set:
					printList(sets);
					break;

			}
		}
		
		/**
		 * Print all menu items
		 */
		public static void printAllMenuItems() {
			printList(allMenu);
		}
		
		/**
		 * Print all menu items in a simplified categorized manner
		 */
		public static void printAllMenuSimplified() {
			System.out.println("========Main Courses========");
			System.out.println("ItemID\tName\tPrice");
			for(int i = 0 ;i<mainCourses.size();i++){
				if(!mainCourses.get(i).deleted)
					System.out.printf("%d\t%s\t%.2f\n",mainCourses.get(i).getItemID(), mainCourses.get(i).getName(),mainCourses.get(i).getPrice());
			}
			
			System.out.println("========Sides========");
			System.out.println("ItemID\tName\tPrice");
			for(int i = 0 ;i<sides.size();i++){
				if(!sides.get(i).deleted)
					System.out.printf("%d\t%s\t%.2f\n",sides.get(i).getItemID(), sides.get(i).getName(),sides.get(i).getPrice());
			}
			
			System.out.println("========Desserts========");
			System.out.println("ItemID\tName\tPrice");
			for(int i = 0 ;i<desserts.size();i++){
				if(!desserts.get(i).deleted)
					System.out.printf("%d\t%s\t%.2f\n",desserts.get(i).getItemID(), desserts.get(i).getName(),desserts.get(i).getPrice());
			}
			
			System.out.println("========Drinks========");
			System.out.println("ItemID\tName\tPrice");
			for(int i = 0 ;i<drinks.size();i++){
				if(!drinks.get(i).deleted)
					System.out.printf("%d\t%s\t%.2f\n",drinks.get(i).getItemID(), drinks.get(i).getName(),drinks.get(i).getPrice());
			}
			
			System.out.println("========Sets========");
			System.out.println("ItemID\tName\tPrice");
			for(int i = 0 ;i<sets.size();i++){
				if(!sets.get(i).deleted)
					System.out.printf("%d\t%s\t%.2f\n",sets.get(i).getItemID(), sets.get(i).getName(),sets.get(i).getPrice());
			}
		}

		/**
		 * Print an array list of orderitems
		 * @param al ArrayList of order items to print
		 */
		public static void printList(ArrayList<OrderItem> al){
			for(int i = 0 ;i<al.size();i++){
				if(!al.get(i).deleted)
					al.get(i).printAttributes();
			}
		}

		/**
		 * get the array list for a type of menu
		 * @param type A string indicating the menu to return: MainCourse, Sides, Drinks, Desserts, Set. Default returned set is allmenu
		 * 
		 * @return arraylist The ArrayList of menu items to return
		 */
		public static ArrayList<OrderItem> getMenu(String type){
			switch(type) {
				case "MainCourse":
					return mainCourses;
				case "Sides":
					return sides;
				case "Drinks":
					return drinks;
				case "Desserts":
					return desserts;
				case "Set":
					return sets;
				default:
					return allMenu;
			}
		}
		
		
		/**
		 * readsCSV file and initializes variables. called during init
		 * @param isSet Flag to indicate if it should read the set package csv
		 * @throws FileNotFoundException
		 */
		private static void readCSV(boolean isSet) throws FileNotFoundException {
			if(!isSet) { //read csv for menuitems
				try {
					File file = new File(itemDir);
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					String[] tempArr;
					int lineCount = 0;
					while ((line = br.readLine()) != null) {
						if (lineCount >= 1) {
							tempArr = line.split(",");
							//for(int i = 0; i < tempArr.length; i++) {
							//System.out.print(tempStr + " ");
							processCSVObject(tempArr);
							//}
							//System.out.println();
						}
						lineCount++;
					}
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			else{  //read csv for set
				try {
					File file = new File(setDir);
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					String[] tempArr;
					ArrayList<String[]> lineList = new ArrayList<>();
					int id = -1;
					int lineCount = 0;
					while ((line = br.readLine()) != null) {
						if (lineCount >= 1) {
							tempArr = line.split(",");
							if(Integer.parseInt(tempArr[0])!=id) {
								if(!lineList.isEmpty())
									processCSVObject(lineList);
								lineList.clear();
								id = Integer.parseInt(tempArr[0]);
								lineList.add(tempArr);
							}
							else
								lineList.add(tempArr);
						}
						lineCount++;
					}
					if(!lineList.isEmpty())
						processCSVObject(lineList);
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}
			
			
		}
		
		/**
		 * Writes all current menu data and update changes to csv
		 * @param isSet Identifies if it needs to write to the sets csv
		 */
		public static void writeCSV(boolean isSet) {
			if (!isSet) {
				File file = new File(itemDir);
				try {
					FileWriter fw = new FileWriter(file);
					PrintWriter out = new PrintWriter(fw);
					out.append("ItemID,Type,Name,Price,Description\n");
					for (int i = 0; i < allMenu.size(); i++) {
						if (allMenu.get(i).getType() != OrderItemType.Set) {
							MenuItem o = (MenuItem) allMenu.get(i);
							String newLine = "" + o.getItemID() + "," + o.getType() + "," + o.getName() + "," + o.getPrice() + "," + o.getDescription();
							if (o.deleted)
								newLine += ",deleted";
							out.append(newLine + "\n");
						}
					}
					out.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			else{
				File file = new File(setDir);
				try {
					FileWriter fw = new FileWriter(file);
					PrintWriter out = new PrintWriter(fw);
					out.append("SetID,Name,Price,Description,ItemID\n");
					for (int i = 0; i < sets.size(); i++) {
						PromotionalSetPackage p = (PromotionalSetPackage) sets.get(i);
						//p.printAttributes();
						ArrayList<MenuItem> setItems = p.getPackageItems();

						for(int j = 0; j<setItems.size();j++) {
							//setItems.get(j).printAttributes();
							int o = setItems.get(j).getItemID();

							String newLine = "" + p.getItemID() + "," + p.getName() + "," + p.getPrice() + "," + p.getDescription()+","+o;
							if (setItems.get(j).deleted||p.deleted)
								newLine += ",deleted";
							out.append(newLine + "\n");
						}

					}
					out.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}


		/**
		 * Process a read string array from csv and convert into identifiable variable type in app
		 * @param arr String array read by csvreader
		 */
		private static void processCSVObject(String arr[]){
			int itemID = Integer.parseInt(arr[0]);
			OrderItemType type = OrderItemType.valueOf(arr[1]);
			String name = arr[2];
			float price = Float.parseFloat(arr[3]);
			String desc = arr[4];
			String deleted = "";
			//System.out.printf("Item ID: %d\nType:%s\nName: %s\nPrice: %f\nDesc: %s\n\n",itemID,type,name, price, desc);
			//OrderItem o = new OrderItem(itemID, type, name,price, desc);
			MenuItem o = new MenuItem(itemID, type, name,price, desc);
			try {
				deleted = arr[5]; //if an item exists in size 5, the item is a deleted item
			} catch ( IndexOutOfBoundsException e ) {

			}
			if(deleted!="")
				o.delete(); //delete item
			addMenuItem(o);
		}
		/**
		 * Process a read string array from csv and convert into a set object
		 * @param set List of string arrays read by csv reader
		 */
		private static void processCSVObject(ArrayList<String[]> set){
			int setID = Integer.parseInt(set.get(0)[0]);
			String name = set.get(0)[1];
			float price = Float.parseFloat(set.get(0)[2]);
			String desc = set.get(0)[3];
			String deleted = "";
			PromotionalSetPackage p1 =  (PromotionalSetPackage)searchItemByID(setID);
			int itemID;
			ArrayList<MenuItem> setItems = new ArrayList<>();
			for (int i = 0; i < set.size(); i++) {
				//System.out.printf("Set ID: %d\nName: %s\nPrice: %f\nDesc: %s\nItem ID: %d\n\n",setID,name, price, desc,Integer.parseInt(set.get(i)[4]));
				itemID = Integer.parseInt(set.get(i)[4]);
				MenuItem o1 = ((MenuItem) searchItemByID(itemID)).copy();
				try {
					deleted = set.get(i)[5]; //if an item exists in size 5, the item is a deleted item
				} catch (IndexOutOfBoundsException e) {

				}

				if (p1 != null){ //if the set package exists, just add the item to the set package{
					p1.addPackageItem(o1, false);
					if(deleted!=""){
						p1.removePackageItem(itemID);
						deleted = "";
					}
				}
				else {
					if(deleted!=""){
						o1.delete();
						deleted = "";
					}
					setItems.add(o1);
				}


			}
			if(p1 == null) {//if the set package does not exist
				p1 = new PromotionalSetPackage(setID, name, price, desc, setItems);
				addMenuItem(p1);
			}




		}

		/**
		 * Searches for an orderitem based on its ID
		 * @param id ItemID of orderitem
		 * @return orderitem OrderItem Object found
		 */
		public static OrderItem searchItemByID(int id){
			for(int i=0;i<allMenu.size();i++){
				if(allMenu.get(i).getItemID() == id) {
					//allMenu.get(i).printAttributes();
					//MenuItem m1 = (MenuItem) allMenu.get(i);
					return allMenu.get(i);
				}
			}
			return null;
		}
		
		/**
		 * @param itemID ItemID of item to delete
		 * @return 0 if unsuccessful, 1 if successful
		 */
		public static int delete(int itemID) {
			boolean isSet = false;
			for(int i = 0; i<allMenu.size();i++) {
				if(allMenu.get(i).getItemID() == itemID) {
					allMenu.get(i).delete();
					if(allMenu.get(i).getType() == OrderItemType.Set)
						isSet = true;
					break;
				}else if(i == allMenu.size()-1 )
					return 0;
			}
			if(isSet) {
				for(int i = 0; i<sets.size();i++) {
					if(sets.get(i).getItemID()==itemID) {
						sets.get(i).delete();
						break;
					}
				}
				writeCSV(true);
			}
			else {
				for(int i = 0; i<sets.size();i++) { //check if deleted item is within a set
					for(int j= 0; j<((PromotionalSetPackage)sets.get(i)).getPackageItems().size();j++){
						if(((PromotionalSetPackage)sets.get(i)).getPackageItems().get(j).getItemID()==itemID)
							((PromotionalSetPackage)sets.get(i)).getPackageItems().get(j).delete();
					}
				}
				writeCSV(true);
				writeCSV(false);
			}
			return 1;
		}



}
