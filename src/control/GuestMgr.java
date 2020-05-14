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

public class GuestMgr extends EntityManager implements AddObject, ModifyObject, PrintSingleObject, PrintAllObjects{	
	private ArrayList<Guest> guestList;
	private Scanner sc;
	
	public GuestMgr(Scanner sc, DataAccess dataAccess) {
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
	@Override
	public void add() {
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
		
		addGuest(nric, name, gender, nationality);
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
				System.out.println("Name: " + g.getName());
			}
			return;
		}
		System.out.printf("NRIC: %s, Name: %s, Gender: %s, Nationality: %s\n", tempList.get(0).getNRIC(), tempList.get(0).getName(), tempList.get(0).getGender(), tempList.get(0).getNationality());
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
	private void addGuest(String nric, String name, String gender, String nationality) {
		Guest g = new Guest(this.getCounter(), nric, name, gender, nationality);
		guestList.add(g);
		this.setCounter(guestList.size() + 1);
		this.writeToFile(guestList, Guest.class);
		System.out.println("Guest added");
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
