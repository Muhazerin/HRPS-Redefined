package boundary;

import java.util.Scanner;

import control.FileIO;
import control.GuestManager;
import control.ReservationManager;
import control.RoomManager;

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
		ReservationManager reservationMgr = new ReservationManager(sc, fileIO);
		
		int option = -1, option2 = -1;
		do {
			menu();
			option = intInputChecker(option, sc);
			switch (option) {
			case 0:
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
					reservationOption(option2, guestMgr, roomMgr, reservationMgr, sc);
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
		System.out.println("| 3. Remove reservation         |");
		System.out.println("| 4. Print a reservation detail |");
		System.out.println("| 5. Print all reservation      |");
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
				roomMgr.add();
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
	
	private static void reservationOption(int option, GuestManager guestMgr, RoomManager roomMgr, ReservationManager reservationMgr, Scanner sc) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				boolean walkIn = false;
				int choice = -1;
				
				while (choice == -1 || (choice != 1 && choice != 2)) {
					System.out.println("Press 1 for walk-in, 2 for reservation");
					System.out.print("Enter choice: ");
					choice = intInputChecker(choice, sc);
					if (choice != 1 && choice != 2) {
						System.out.println("Invalid integer");
					}
				}
				if (choice == 1) {
					walkIn = true;
				}
				
				int guestId = guestMgr.selectObject();
				int roomId = roomMgr.selectObject(walkIn);
				
				
				reservationMgr.add(guestId, roomId, walkIn);
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
