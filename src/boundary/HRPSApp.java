package boundary;

import control.GuestMgr;
import java.util.Scanner;

/**
 * 
 * @author muhazerin
 *
 */
public class HRPSApp {

	/**
	 * This is the main function for the HRPS App
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		GuestMgr guestMgr =  new GuestMgr(sc);
		
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
			default:
				System.out.println("Invalild choice");
				break;
			}
		} while (option != 0);
		
		
		//guestMgr.add();
		//guestMgr.printAll();
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
		System.out.println("+-----------------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This function prints the guest menu
	 */
	private static void guestMenu() {
		System.out.println("\n+----------------------+");
		System.out.println("| Guest Options        |");
		System.out.println("+----------------------+");
		System.out.println("| 0. Go back           |");
		System.out.println("| 1. Add Guest         |");
		System.out.println("| 2. Prints all guests |");
		System.out.println("+----------------------+");
		System.out.print("Enter choice: ");
	}
	
	/**
	 * This function handles all the operations related to guest
	 * @param 	option		the guest option
	 * @param 	guestMgr	the guestMgr to handle the guest operation
	 */
	private static void guestOption(int option, GuestMgr guestMgr) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				guestMgr.add();
				break;
			case 2:
				guestMgr.printAll();
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
