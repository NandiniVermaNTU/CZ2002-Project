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

### Sales and Revenue Functions
It asks for the desired range (in days)




## CSV Files
1. Staff Information – Staff ID, Name, Gender, Job Title are stored in **_Staff.csv_**
2. Table details – Table ID, Seating Capacity (ranging between 2 to 10), Availability (true/false) and Customer ID of customer (in case of reservation otherwise -1) are stored in **_Table.csv_**
3. Customer Information - Customer ID, Customer Name, isMembership (TRUE if customer is a member of the restaurant else false) Customer Contact and Customer Gender are stored in **_Customer.csv_**
4. Reservation information – Reservation time (in epoch), Number of customers (reservation pax), Customer ID (who made the reservation), Customer Name, Customer Contact and Reserved Table (Table ID) are stored in **_Reservation.csv_**
5. Invoice details - 

