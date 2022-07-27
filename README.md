# Restaurant Reservation and Point of Sales System 
A Java based CLI (Command Line Interface) developed for CZ2002 - Object Oriented Design and Programming.

## Video Demonstration 
https://www.youtube.com/watch?v=HcF2U0MIF8s

## Other Contributors 
Zhao Mengao, Zixing Lin and Yong Hao

## Application Features
The application begins with asking the restaurant staff for their staff ID. Each staff member is issued a unique staff ID. On keying in a valid staff ID, a welcome message is displayed with the following 6 options to proceed further: 
1. Reservation Functions
2. Table/Check-in Functions
3. Order and Invoice Functions
4. Food Menu Functions
5. Sales and Revenue Functions
6. Exit Application 

### Reservation Functions 
It provides the following options:
1. Create new reservation
2. View current reservations
3. Delete reservation
4. Return to main menu

_**Create new reservation**_ asks for the customer ID making the reservation followed by the number of customers (between 1 to 10) for which the booking is made. The name of the customer to whom the keyed in customer ID belongs is also displayed. Next, the time for reservation in _dd-mm-yyyy, hh:mm:ss_ format is asked. If feasible (table with the specified pax is available on the desired date and time), the reservation is made successfully. Each reservation has a unique reservation ID. Expired reservations are removed automatically from Reservation.csv file. It is updated every time a staff member logs in the system. A attempt of reservation can fail if the desired reservation time is past (reservations should be made at least an hour in advance). 

_**View current reservations**_ shows the current reservations with the following details - Customer ID, Customer Name, Contact, Table Number, Number of customers, Date (day and time).

_**Delete reservation**_ asks for the reservation ID to delete. If a valid entry is made, the corresponding reservation is deleted. 

### Table/Check-in Functions 
It provides the following options:
1. Check-in [Walk-in customer]
2. Check-in [Reserved customer]
3. Check table availability
4. Return to main menu

_**Check-in [Walk-in customer]**_ asks for the number of customers followed by the customer ID of the customer on whose behalf the table will be booked. If a matching table is available, it is allocated, an order is created and the menu is displayed. The items in the menu can be added to the order by keying in the Item ID. 

_**Check-in [Reserved customer]**_ asks for the customer ID with which the reservation was made. If a valid customer ID is entered, an order is created successfully and the food menu is displayed. The items in the menu can be added to the order by keying in the Item ID. 

_**Check table availabilty**_ shows the status of all the 10 tables in the restaurant. If a table is available, its status is shown as <I> true </I> otherwise <I> false </I>. A table is identified by its unique Table ID.

### Order and Invoice Functions
It provides the following options:
1. View current orders
2. View/Change order detail
3. Quit to the main menu

Note that the customer needs to first check-in before placing an order. Thereafter, the menu will be displayed and an order can be placed by simply keying in the item ID of the desired items shown in the menu. To obtain more information about an item on the menu, **i + [item ID]** can be keyed in. For example, i 11 will display more information about item with ID 11 in the menu as retrieved from OrderItem.csv or OrderItemSet.csv.

_**View current orders**_ displays the current orders - order ID and Customer Name.

_**View/Change order detail**_ asks for the order ID of the order which needs to be modified. If a valid order ID (i.e. order ID of an existing order) is keyed in, it displays its details - Employee name (creator of order), Customer Name, Table allocated, Time at whoch order was created and Current order items. It then displays the following options:
1. Add items to this order
2. Delete items from this order
3. Cancel this order
4. Print invoice of this order
5. Back to last menu

_Add items to this order_ is similar to adding an item from the displayed menu to the order by keying in its item ID. After adding an item to an existing order, it is updated and displayed. 

_Delete items from this order_ asks for the item ID of the item to be deleted from the order. Multiple items can be deleted from an order simultaneously by keying in their item IDs separated by space. When done, hit enter. 

_Cancel this order_ will reconfirm if the employee is sure to cancel the order. There are 2 input options - y or n. If the staff enters y, the order is deleted and appropriate message is displayed. 

_Print invoice of this order_ displays the order bill of the customer. The final price of an order item is the summation of its price and the levied tax (7%). A discount of 25% is applicable for members of the restaurant only. 

### Food Menu Functions
It provides the following options:
1. View Menu
2. Add new food item or set package
3. Delete food item or set package
4. Edit food item or set info (Name/Price/Description)
5. Return to main menu

_**View Menu**_ renders the following options:
1. View Main Course Menu
2. View Sides Menu
3. View Drinks Menu
4. View Dessert Menu
5. View Promotional Set Menu
6. View all menu items
7. Return to previous menu

For acquiring detailed information about menu items, options 1 to 5 can be explored. Option 6 provides an overview of the entire menu.

_View Main Course Menu_ provides a list of all the main course items available in the menu in detail (i.e. with a short and succint description). 
_View Sides Menu_ provides a list of all the side dishes available in the menu in detail.
_View Drinks Menu_ provides a list of all the beverages available in the menu in detail.
_View Dessert Menu_ provides a list of all the sweet dishes available in the menu in detail. 
_View all menu items_ prints a list of all the items present in the menu. 

_**Add new food item or set package**_ allows the addition of an item to the menu. It asks for the item type to be added - Main Course, Sides, Drinks, Desserts and Sets. For all types other than Sets, it further asks for the name of the new item, its price and description. Then it displays the details of the newly added menu item. An item ID is also assigned to it (Item IDs are assigned in consecutive order). For SETS, it asks for the name of the new set package, its price and description followed by the selection of items to be included in the new set meal. Item IDs are to be entered with spaces. To finish selection, -1 is to be entered. An appropriate message saying that the new set has been added to the menu is displayed along with its details. The corresponding csv file (OrderItemSet.csv or OrderItem.csv) is updated concurrently. 

_**Delete food item or set package**_ asks for the ID of the order item in the menu which is to be deleted. If the item ID keyed in is valid, it shows the details of the order item which has been deleted with an appropriate message. After deleting an item from the menu, it is not deleted from the database. It can still be viewed in the respective csv file. However, a deleted tag is added to it and it is not visible when the menu is printed, order is created or invoice is printed. 

_**Edit food item or set info (Name/Price/Description)**_ enables editting of the details of an order item in the menu. It renders the following options:
1. Edit Info of food item or set (Name, Price, Description)
2. Add item to existing set package
3. Remove item from existing set package
4. Return to previous menu

_Edit Info of food item or set (Name, Price, Description)_ asks for the ID of the order item whose details are to be edited. Upon keying in a valid item ID, the subsequent item information is displayed. Prompts for entering new information are displayed. -1 is to be entered for the details which are to be kept same. After changing any detail, the new order item information is displayed. The corresponding csv file is also updated in real time. 

_Add item to existing set package_ allows addition of an item to an existing set package on the menu. It asks for the item ID of the set and displays its information. Then, it asks for the ID of the item to be added to the set. If a valid item ID is keyed in, it is added to the desired set package. 

_Remove item from existing set package_ allows deletion of an item to an existing set package on the menu. It asks for the item ID of the set and displays its information. Then, it asks for the ID of the item to be deleted from the set. If a valid item ID is keyed in, it is deleted from the desired set package.

### Sales and Revenue Functions
It asks for the desired range (in days) and displays the corresponding report - Item ID, Item name, Item type, Item sold (quantity) and Item Revenue. It also displays the total revenue (summation) generated during the keyed in duration. 

## CSV Files
1. Staff Information – Staff ID, Name, Gender, Job Title are stored in **_Staff.csv_**
2. Table details – Table ID, Seating Capacity (ranging between 2 to 10), Availability (true/false) and Customer ID of customer (in case of reservation otherwise -1) are stored in **_Table.csv_**
3. Customer Information - Customer ID, Customer Name, isMembership (TRUE if customer is a member of the restaurant else false) Customer Contact and Customer Gender are stored in **_Customer.csv_**
4. Reservation information – Reservation time (in epoch), Number of customers (reservation pax), Customer ID (who made the reservation), Customer Name, Customer Contact and Reserved Table (Table ID) are stored in **_Reservation.csv_**
5. Invoice details - Restaurant staff name (who served them), Customer Name, Number of customers (pax), Reservation time (in epoch),  
6. Individual order item details - Item ID, Type (MainCourse, Sides, Deserts, Drinks), Name, Price and Description are contained in **_OrderItem.csv_**
7. Set order item details - Set ID, Name, Price and Descriptiom are stored in **_OrderItemSet.csv_**
8. Order item details - Item ID, Type, Name, Price and Description are stored in **_OrderItem.csv_**

