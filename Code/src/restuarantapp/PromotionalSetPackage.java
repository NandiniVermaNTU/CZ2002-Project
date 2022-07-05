/**
 * Represents a Promotional Set Package that contains
 * a set of menu items with promotion. A subclass of OrderItem.
 * @author ZHANG MENGAO, LIN ZIXING
 * @version 1.0
 */

package restuarantapp;
import java.util.ArrayList;
public class PromotionalSetPackage extends OrderItem {

	/**
	 * Variable that holds the MenuItems this package offers.
	 */
	private ArrayList<MenuItem> packageItem;
	/**
	 * Constructor to initialize the menu items.
	 *
     * @param itemID Unique identifier for item
     * @param name Name of item
     * @param price Price of item
     * @param description Item description
     * @param items ArrayList of MenuItems to be part of the set package
	 */
	public PromotionalSetPackage(int itemID, String name, float price, String description, ArrayList<MenuItem> items ) {
		super(itemID,OrderItemType.Set, name, price, description);
		this.packageItem = items;
	}

	/**
	 * Get the package items
	 */
	public ArrayList<MenuItem> getPackageItems() {
		return packageItem;
	}





	/**
	 * Print method for the set attributes, as well as ID and name for package items
	 */
	@Override
	public void printAttributes() {
		System.out.print("\n[Set Package Info] \n");
		super.printAttributes();
		System.out.print("Package items: \n");
		for (MenuItem items : packageItem) {
			if(!items.deleted) {
			System.out.print(items.getItemID() + "\t");
			System.out.print(items.getName() + "\n");
			}
		}
		//System.out.print("\nSet Price: "+super.getPrice());
		System.out.println();
	}

	/**
	 * Print method for only package items
	 */
	public void displayItems() {
		System.out.print("Package items: ");
		for (MenuItem items : packageItem)
			System.out.print(items.getName() + "\t");
		System.out.println();
	}


	/**
	 * Adds a package item to the set and write to csv
	 * @param m, write
	 */
	public void addPackageItem(MenuItem m, boolean write) {
		//System.out.printf("Adding %s to Promotional Set %s ..\n",m.getName(),super.getName());
		packageItem.add(m);
		//displayItems();
		if(write)
			OrderItemController.writeCSV(true);
	}

	/**
	 * Remove a package item from the set based on the itemID
	 * @param itemID
	 */
	public void removePackageItem(int itemID){
		for(int i = 0;i < packageItem.size();i++){
			if(packageItem.get(i).getItemID() == itemID){
				packageItem.get(i).delete();
				break;
			}
		}
		OrderItemController.writeCSV(true);
	}


}
