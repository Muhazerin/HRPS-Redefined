# HRPS-Redefined
A redefined version of the First HRPS App as I dived deeper into the SOLID design principles and Java technologies

# Solid Principles Used
* Single-Responsibility Principle
  * SRP is used in many placed in this application. For example, the relationship between some entity and control classes. Example of entity classes used in SRP are "Guest", "Reservation", "Room", and "MenuItem". Example of control classes in SRP are "GuestManager", "ReservationManager", "RoomManager", and "MenuItemManager". Each entity classes have their own manager class to handle operations such as update, print details, etc. They are split to their respective manager classes instead of lumping all operations into one class 

* Open-Closed Principle
  * An example of OCP in this application is the abstract "Entity" class which many other classes in the Entity folder used. Classes such as "Guest", "Reservation", "Room", and "MenuItem" extends from "Entity" class. 

* Liskov Substitution Principle
  * The way I see LSP is that LSP is an extension of OCP. There is an abstract class and concrete class which derived from the abstract class. However, there are no additional methods added to the concrete class. An example of LSP used in the application is the Room class and its different subtypes; "SingleRoom", "StandardRoom", "VipSuiteRoom", "DeluxRoom". The advantage of LSP is that after the creation of the subtypes, "RoomManager" does not care about the subtype anymore. It uses "Room" class to handle its operation.

* Interface Segregation Principle
  * The way I understand interface is that an interface contains method(s) only. This method(s) would hold as a contract when a concrete class implements them. ISP just means instead of lumping all your methods into 1 interface, it says to seperate the methods to different interfaces. In this application, I represent public methods for manager class as contracts in which the manager class must perform so interfaces are used. Private methods are dependent on the respective manager class so no interface is used.

* Dependency Inversion Principle
  * DIP is mostly used for reading/writing to database. In this case, the application reads/writes to a file. A simple approach to this is to add the "FileIO" class to the manager class as shown [here](https://github.com/Muhazerin/cz2002-Assignment/blob/master/src/hrps/control/GuestMgr.java). However, this approach is not flexible. If one day, instead of writing to a txt file, I want to write to an XML file, I would have to make changes to the manager class to accomodate that. This shows that the manager class is dependent on the IO class. 
  * In this application, however, instead of a concrete class, I used an interface (DataAccess) as shown [here](https://github.com/Muhazerin/HRPS-Redefined/blob/master/src/control/EntityManager.java). This way, I just need to create a IO class that satisfy this contract and link the interface to a concrete class in the main class as shown [here](https://github.com/Muhazerin/HRPS-Redefined/blob/master/src/boundary/ReceptionistHRPSApp.java). If I ever need to read/write to and XML file, I need to create such a class that satisfy the contract and link it in the main class. No changes in done to the manager class. Using this approach, the manager class will not be dependent on the IO class.
