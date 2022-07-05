
/**
 * Class Represents the individual ala carte item that can be ordered. A subclass of OrderItem.
 * @author LIN ZIXING
 *
 */
package restuarantapp;

public class MenuItem extends OrderItem {





	/**
	 * Creates a menu item with item id, name, price, type and description
	 * 
     * @param itemID Unique identifier for item
     * @param type Type of item of OrderItemType
     * @param name Name of item
     * @param price Price of item
     * @param description Item description
	 */
	public MenuItem(int itemID,OrderItemType type, String name, float price,  String description) {
		super(itemID, type,name, price,description);
	}







	/**
	 * Print the id, name, price, type and description of item
	 */
	@Override
	public void printAttributes() {
		System.out.println("\n[Menu Item Info]");
		super.printAttributes();

	}
	/**
	 * Return a copy of menu item
	 */
	public MenuItem copy(){
		MenuItem m = new MenuItem(super.getItemID(),super.getType(),super.getName(), super.getPrice(), super.getDescription());
		return m;
	}

}
