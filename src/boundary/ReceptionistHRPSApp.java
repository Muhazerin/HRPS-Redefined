package boundary;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.*;

import control.FileIO;
import control.GuestManager;
import control.MenuItemManager;
import control.ReservationManager;
import control.RoomManager;

import entity.Guest;
import entity.Room;
import entity.Reservation;
import entity.MenuItem;
import entity.RoomService;

public class ReceptionistHRPSApp {

	/**
	 * This is the main function for the HRPS App
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		FileIO fileIO = new FileIO();
		GuestManager guestMgr =  new GuestManager(sc, fileIO);
		RoomManager roomMgr = new RoomManager(sc, fileIO);
		
		// creating a thread pool with size = total number of rooms
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(roomMgr.getTotalNumberOfRooms());
		
		MenuItemManager menuItemMgr = new MenuItemManager(sc, fileIO);
		ReservationManager reservationMgr = new ReservationManager(sc, fileIO, scheduledExecutorService);
		reservationMgr.adjustObject(guestMgr);
		reservationMgr.adjustObject(roomMgr);
		
		int option = -1, option2 = -1;
		do {
			menu();
			option = intInputChecker(option, sc);
			switch (option) {
			case 0:
				scheduledExecutorService.shutdownNow();
				System.out.println("Exiting the program...");
				System.out.println("Bye bye...");
				break;
			case 1:
				option2 = -1;
				do {
					guestMenu();
					option2 = intInputChecker(option2, sc);
					guestOption(option2, guestMgr);
				} while (option2 != 0);
				break;
			case 2:
				option2 = -1;
				do {
					roomMenu();
					option2 = intInputChecker(option2, sc);
					roomOption(option2, roomMgr);
				} while (option2 != 0);
				break;
			case 3:
				option2 = -1;
				do {
					reservationMenu();
					option2 = intInputChecker(option2, sc);
					reservationOption(option2, guestMgr, roomMgr, reservationMgr, menuItemMgr);
				} while (option2 != 0);
				break;
			case 4:
				Guest guest = (Guest) guestMgr.selectObject();
				Room room = roomMgr.selectRoom(true);
				
				reservationMgr.addReservation(guest, room, true);
				break;
			case 5:
				option2 = -1;
				do {
					menuItemMenu();
					option2 = intInputChecker(option2, sc);
					menuItemOption(option2, menuItemMgr);
				} while (option2 != 0);
				break;
			default:
				System.out.println("Invalild choice");
				break;
			}
		} while (option != 0);
		
		sc.close();
	}

	/**
	 * This function prints the overview menu
	 */
	private static void menu() {
		System.out.println("\n+-----------------------------------------------------+");
		System.out.println("| Welcome to the Hotel Reservation and Payment System |");
		System.out.println("| What would you like to do today?                    |");
		System.out.println("+-----------------------------------------------------+");
		System.out.println("| 0. Exit the program                                 |");
		System.out.println("| 1. Guest Options                                    |");
		System.out.println("| 2. Room Options                                     |");
		System.out.println("| 3. Reservation Options                              |");
		System.out.println("| 4. Guest Walk-In                                    |");
		System.out.println("| 5. Menu Items Options                               |");
		System.out.println("+-----------------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This function prints the guest menu
	 */
	private static void guestMenu() {
		System.out.println("\n+---------------------------+");
		System.out.println("| Guest Options             |");
		System.out.println("+---------------------------+");
		System.out.println("| 0. Go back                |");
		System.out.println("| 1. Modify guest           |");
		System.out.println("| 2. Search and print guest |");
		System.out.println("| 3. Prints all guests      |");
		System.out.println("+---------------------------+");
		System.out.print("Enter choice: ");
	}
		
	/*
	 * This method contains the menu for roomOption
	 */
	private static void roomMenu() {
		System.out.println("\n+------------------------------------------+");
		System.out.println("| What would you like to do ?              |");
		System.out.println("| 0. Go back                               |");
		System.out.println("| 1. Create new room                       |");
		System.out.println("| 2. Update room details                   |");
		System.out.println("| 3. List Room                             |");
		System.out.println("| 4. Check room details                    |");
		System.out.println("+------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/* 
	 * This method contains the menu for reservationOption
	 */
	private static void reservationMenu() {
		System.out.println("\n+-------------------------------+");
		System.out.println("| What would you like to do ?   |");
		System.out.println("| 0. Go back                    |");
		System.out.println("| 1. Create reservation         |");
		System.out.println("| 2. Update reservation detail  |");
		System.out.println("| 3. Cancel a reservation       |");
		System.out.println("| 4. Print a reservation detail |");
		System.out.println("| 5. Print all reservation      |");
		System.out.println("| 6. Add room service           |");
		System.out.println("| 7. Print room services        |");
		System.out.println("+-------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/* 
	 * This method contains the menu for menuItemOption
	 */
	private static void menuItemMenu() {
		System.out.println("\n+-------------------------------+");
		System.out.println("| What would you like to do ?   |");
		System.out.println("| 0. Go back                    |");
		System.out.println("| 1. Add menu item              |");
		System.out.println("| 2. Modify menu item           |");
		System.out.println("| 3. Remove menu item           |");
		System.out.println("| 4. Print all menu items       |");
		System.out.println("+-------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/**
	 * This function handles all the operations related to guest
	 * @param 	option		the guest option
	 * @param 	guestMgr	the guestMgr to handle the guest operation
	 */
	private static void guestOption(int option, GuestManager guestMgr) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
//			case 1:
//				//guestMgr.add();
//				break;
			case 1:
				guestMgr.modify();
				break;
			case 2:
				guestMgr.printSingle();
				break;
			case 3:
				guestMgr.printAll();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	/**
	 * This function handles all the operations related to room
	 * @param 	option		the guest option
	 * @param 	roomMgr		the roomMgr to handle the guest operation
	 */
	private static void roomOption(int option, RoomManager roomMgr) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				roomMgr.addObject();
				break;
			case 2:
				roomMgr.modify();
				break;
			case 3:
				roomMgr.printAll();
				break;
			case 4:
				roomMgr.printSingle();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	private static void reservationOption(int option, GuestManager guestMgr, RoomManager roomMgr, ReservationManager reservationMgr, MenuItemManager menuUtemMgr) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				Guest guest = (Guest) guestMgr.selectObject();
				Room room = roomMgr.selectRoom(false);
				
				reservationMgr.addReservation(guest, room, false);
				break;
			case 2:
				reservationMgr.modify();
				break;
			case 3:
				break;
			case 4:
				reservationMgr.printSingle();
				break;
			case 5:
				reservationMgr.printAll();
				break;
			case 6:
				Reservation reservation = (Reservation) reservationMgr.selectObject();
				if (!Objects.equals(reservation, null) ) {
					ArrayList<MenuItem> menuItemList = menuUtemMgr.selectMenuItems();
					if (menuItemList.size() == 0) {
						System.out.println("Room service is not added");
					}
					else {
						RoomService roomService = new RoomService(menuItemList);
						reservationMgr.addRoomService(reservation, roomService);
						System.out.println("Room service is added");
					}
				}
				break;
			case 7:
				reservationMgr.printRoomServices();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	private static void menuItemOption(int option, MenuItemManager menuItemMgr) {
		switch (option) {
		case 0:
			System.out.println("Going back...");
			break;
		case 1:
			menuItemMgr.addObject();
			break;
		case 2:
			menuItemMgr.modify();
			break;
		case 3:
			menuItemMgr.removeObject();
			break;
		case 4:
			menuItemMgr.printAll();
			break;
		default:
			System.out.println("Invalid option");
			break;
	}
	}
	
	/**
	* Checks the input for integer
	* @param	option		the variable to store integer and return the integer (if valid)
	* @param 	sc 			Scanner object to retrieve the input
	* @return 				returns the option back (-1 for invalid option)
	*/
	private static int intInputChecker(int option, Scanner sc) {
		try {
			option = sc.nextInt();
			sc.nextLine();	// clear the buffer
			return option;
		}
		catch (Exception e){
			System.out.println("Input is not an integer");
			sc.nextLine();	// clear the buffer
			return -1;
		}
	}

}
