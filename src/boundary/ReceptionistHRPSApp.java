package boundary;

import java.util.Scanner;

import control.DataSource;
import control.EntityManager;
import control.GuestMgr;

public class ReceptionistHRPSApp {

	/**
	 * This is the main function for the HRPS App
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		DataSource dataSource = new DataSource();
		GuestMgr guestMgr =  new GuestMgr(sc, dataSource);
		
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
					entityManagerOption(option2, guestMgr);
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
		System.out.println("\n+---------------------------+");
		System.out.println("| Guest Options             |");
		System.out.println("+---------------------------+");
		System.out.println("| 0. Go back                |");
		System.out.println("| 1. Add guest              |");
		System.out.println("| 2. Modify guest           |");
		System.out.println("| 3. Search and print guest |");
		System.out.println("| 4. Prints all guests      |");
		System.out.println("+---------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/**
	 * This function handles all the operations related to guest
	 * @param 	option		the guest option
	 * @param 	guestMgr	the guestMgr to handle the guest operation
	 */
	private static void entityManagerOption(int option, EntityManager entityMgr) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				entityMgr.add();
				break;
			case 2:
				entityMgr.modify();
				break;
			case 3:
				entityMgr.printSingle();
				break;
			case 4:
				entityMgr.printAll();
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
