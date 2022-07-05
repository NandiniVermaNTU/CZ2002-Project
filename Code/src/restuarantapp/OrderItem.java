
/**
 * OrderItem class representing all items that can be ordered
 * @author LIN ZIXING
 *
 */
package restuarantapp;
public class OrderItem {

    /**
     * Item ID of the order item
     */
    private int itemID;
    /**
     * Name of the order item
     */
    private String name;
    /**
     * Price of the order item
     */
    private float price;

    /**
     * Type of menu item
     */
    private OrderItemType type;
    /**
     * Description of order item
     */
    private String description;

    /**
     * Deleted indicator
     */
    public boolean deleted = false;

    /**
     * Creates Order Item with default values
     */
    public OrderItem(){
        itemID = -1;
        name = "NULL";
        price = -1;
        type = OrderItemType.MainCourse;
        description = "NULL";
    }
    /**
     * Creates Order Item with item id, name and price
     *
     * @param itemID Unique identifier for item
     * @param type Type of item of OrderItemType
     * @param name Name of item
     * @param price Price of item
     * @param description Item description
     */
    public OrderItem(int itemID, OrderItemType type,String name, float price,  String description){
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }


    /**
     * Returns ID of Order Item
     *
     * @return itemID Unique identifier for item
     */
    public int getItemID(){return itemID;}
    /**
     * Returns Name of Order Item
     *
     * @return name Name of item
     */
    public String getName(){return name;}
    /**
     * Returns Price of Order Item
     *
     * @return price Price of Item
     */
    public float getPrice(){return price;}

    /**
     * Changes ID of an item
     *
     * @param itemID New ID of item
     */
    public void setID(int itemID){
        this.itemID = itemID;
        printAttributes();
    }
    /**
     * Changes price of an item
     *
     * @param price New price for item
     */
    public void setPrice(float price){
        this.price = price;
        OrderItemController.writeCSV(true);
        OrderItemController.writeCSV(false);
        OrderItemController.init();
    }

    /**
     * Changes name of an item
     *
     * @param name New name of item
     */
    public void setName(String name){

        this.name = name;
        OrderItemController.writeCSV(true);
        OrderItemController.writeCSV(false);
        OrderItemController.init();
        //printAttributes();
    }
    /**
     * Changes type of item to stated type
     *
     * @param type New OrderItemType for item
     */
    public void setType(OrderItemType type) {

        this.type = type;
        OrderItemController.writeCSV(true);
        OrderItemController.writeCSV(false);
        OrderItemController.init();
    }

    /**
     * Obtain the type of item
     *
     * @return type Type of item, in OrderItemType
     */
    public OrderItemType getType() {
        return type;
    }

    /**
     * Changes the description of item
     *
     * @param description New Description of Item to set
     */
    public void setDescription(String description) {
        this.description = description;
        OrderItemController.writeCSV(true);
        OrderItemController.writeCSV(false);
        OrderItemController.init();
    }

    /**
     * Get the description of the item
     *
     * @return description Description of item
     */
    public String getDescription() {

        return description;
    }

    /**
     * Prints all attributes of the Order Item
     */
    public void printAttributes(){

        System.out.printf("Item ID: %d\nType: %s\nName: %s\nPrice: %.2f\nDescription: %s\n", itemID,type,name, price,description);
    }

    /**
     * Delete the order item (mark as deleted)
     */
    public void delete(){
        deleted = true;
        //OrderItemController.writeCSV(false);
    }

    /**
     * Return a copy of this OrderItem
     */
    public OrderItem copy(){
        OrderItem o = new OrderItem(itemID,type,name, price, description);
        return o;
    }


}
