package control;

import entity.Guest;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestMgr extends EntityManager{	
	private ArrayList<Guest> guestList;
	private Scanner sc;
	
	public GuestMgr(Scanner sc) {
		super(Guest.class);
		
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
	
	private void addGuest(String nric, String name, String gender, String nationality) {
		Guest g = new Guest(this.getCounter(), nric, name, gender, nationality);
		guestList.add(g);
		this.setCounter(guestList.size() + 1);
		this.writeToFile(guestList, Guest.class);
		System.out.println("Guest added");
	}
	
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
}
