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
4. Food menu functions
5. Sales and Revenue Functions
6. Exit Application 

### Reservation Functions 
It provides the following options:
1. Create new reservation
2. View current reservations
3. Delete reservation
4. Return to main menu

**View current reservations** shows the current reservations with the following details - Customer ID, Customer Name, Contact, Table Number, Number of customers, Date (day and time).

### Table/Check-in Functions 
It provides the following options:
1. Check-in [Walk-in customer]
2. Check-in [Reserved customer]
3. Check table availability
4. Return to main menu
**Check-in [Walk-in customer]** asks for the number of customers followed by the customer ID of the customer on whose behalf the table will be booked. If a matching table is available, it is allocated, an order is created and the menu is displayed. The items in the menu can be added to the order by keying in the Item ID. 
**Check-in [Reserved customer]** asks for the customer ID with which the reservation was made. If a valid customer ID is entered, an order is created successfully and the food menu is displayed. The items in the menu can be added to the order by keying in the Item ID. 
**Check table availabilty** shows the status of all the 10 tables in the restaurant. If a table is available, its status is shown as <I> true </I> otherwise <I> false </I>. A table is identified by its unique Table ID.

