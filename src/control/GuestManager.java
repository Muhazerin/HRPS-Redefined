package control;

import entity.Guest;
import interfaces.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author muhazerin
 *
 */

public class GuestManager extends EntityManager implements ModifyObject, PrintSingleObject, PrintAllObjects{	
	private ArrayList<Guest> guestList;
	private Scanner sc;
	
	public GuestManager(Scanner sc, DataAccess dataAccess) {
		super(Guest.class, dataAccess);
		
		this.sc = sc;
		guestList = new ArrayList<Guest>();
		Object[] objArray = super.getList();
		for (Object obj : objArray) {
			if (obj instanceof Guest) {
				Guest g = (Guest) obj;
				guestList.add(g);
			}
			else {
				System.out.println("Object is not instance of Guest");
				break;
			}
		}
		this.setCounter(guestList.size() + 1);
	}
	public int selectObject() {
		int guestId = -1;
		if (guestList.size() == 0) {
			// guestList is empty
			add();
			guestId = this.getCounter() - 1;
		}
		else {
			// there are items in the guestList,
			// so, must check whether the name is already inside guestList
			ArrayList<Guest> tempList = searchGuest();
			if (tempList.size() == 1) {
				// Guest exist in the guestList
				// but must still show info and ask for confirmation
				System.out.println("Guest found in the guestList");
				printSingle(tempList.get(0));
				String choice = "";
				do {
					System.out.print("Is it this guest? (Y/n): ");
					choice = sc.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						guestId = tempList.get(0).getId();
					}
					else if (choice.equalsIgnoreCase("n")) {
						System.out.println("Ok. Creating new guest");
						add();
						guestId = this.getCounter() - 1;
					}
					else {
						System.out.println("Invalid Choice");
					}
				} while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
			}
			else if (tempList.size() == 0) {
				// guest does not exist in the guestList
				add();
				guestId = this.getCounter() - 1;
			}
			else {
				// if there is multiple guests found in the guestList,
				// ask the user if the guest is in the guestList
				// if yes, specify the guest
				// if no, create new guest
				System.out.println("Multiple guests found in the guestList");
				for (Guest g : tempList) {
					printSingle(g);
				}
				String choice = "";
				do {
					System.out.print("Is the guest you are seaching for in this list? (Y/n): ");
					choice = sc.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						// specify the guest
						do {
							System.out.print("Enter guest's full name: ");
							String name = sc.nextLine();
							for (Guest g : tempList) {
								if (g.getName().equals(name)) {
									guestId = g.getId();
									break;
								}
							}
							if (guestId == -1) {
								System.out.println("Invalid name");
							}
						} while (guestId == -1);
					}
					else if (choice.equalsIgnoreCase("n")) {
						System.out.println("Ok. Creating new guest");
						add();
						guestId = this.getCounter() - 1;
					}
					else {
						System.out.println("Invalid Choice");
					}
				} while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
			}
		}
		return guestId;
	}
	private void add() {
		String nric, name, gender, nationality;
		
		System.out.print("Enter nric: ");
		nric = sc.nextLine();
		
		if (guestList.size() > 0) {
			for (Guest g : guestList) {
				if (g.getNRIC().equalsIgnoreCase(nric)) {
					System.out.println("Guest found in guest list");
					System.out.println("Guest is not added");
					System.out.printf("NRIC: %s, Name: %s, Gender: %s, Nationality: %s\n", g.getNRIC(), g.getName(), g.getGender(), g.getNationality());
					return;
				}
			}
		}
		
		System.out.print("Enter name: ");
		name = sc.nextLine();
		System.out.print("Enter gender: ");
		gender = sc.nextLine();
		System.out.print("Enter nationality: ");
		nationality = sc.nextLine();
		
		Guest g = new Guest(this.getCounter(), nric, name, gender, nationality);
		guestList.add(g);
		this.setCounter(guestList.size() + 1);
		this.writeToFile(guestList, Guest.class);
		System.out.println("Guest added");
	}
	@Override
	public void modify() {
		ArrayList<Guest> tempList = searchGuest();
		if (tempList.size() == 0) {
			System.out.println("Name is not found in the guest list");
			return;
		}
		if (tempList.size() > 1) {
			System.out.println("Multiple guest found. Please refine your search query");
			for (Guest g : tempList) {
				System.out.println("Name: " + g.getName());
			}
			return;
		}
		
		System.out.println("Modify algorithm not done yet");
		// Modify algorithm
		
	}
	@Override
	public void printSingle() {
		ArrayList<Guest> tempList = searchGuest();
		if (tempList.size() == 0) {
			System.out.println("Name is not found in the guest list");
			return;
		}
		if (tempList.size() > 1) {
			System.out.println("Multiple guest found. Please refine your search query");
			for (Guest g : tempList) {
				printSingle(g);
			}
			return;
		}
		printSingle(tempList.get(0));
	}
	private void printSingle(Guest g) {
		System.out.printf("NRIC: %s, Name: %s, Gender: %s, Nationality: %s\n", g.getNRIC(), g.getName(), g.getGender(), g.getNationality());
	}
	@Override
	public void printAll() {
		if (guestList.size() > 0) {
			for (Guest g : guestList) {
				System.out.printf("NRIC: %s, Name: %s, Gender: %s, Nationality: %s\n", g.getNRIC(), g.getName(), g.getGender(), g.getNationality());
			}
		}
		else {
			System.out.println("There's no guest in the guest list");
		}
	}
	private ArrayList<Guest> searchGuest() {
		ArrayList<Guest> tempGuest = new ArrayList<Guest>();
		System.out.print("Enter guest name: ");
		String name = sc.nextLine();
		
		if (guestList.size() == 0) {
			return tempGuest;
		}
		for (Guest g : guestList) {
			if (g.getName().contains(name)) {
				tempGuest.add(g);
			}
		}
		return tempGuest;
	}
}
