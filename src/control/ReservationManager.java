package control;

import interfaces.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Reservation;
import entity.Room;
import entity.Guest;

/**
 * 
 * @author muhazerin
 *
 */

public class ReservationManager extends EntityManager implements AddReservation, ModifyObject, PrintSingleObject, PrintAllObjects, AdjustObject{
	private ArrayList<Reservation> reservationList;
	private Scanner sc;
	
	public ReservationManager(Scanner sc, DataAccess dataAccess) {
		super(Reservation.class, dataAccess);
		
		this.sc = sc;
		reservationList = new ArrayList<Reservation>();
		Object[] objArray = super.getList();
		for (Object obj : objArray) {
			if (obj instanceof Reservation) {
				Reservation r = (Reservation) obj;
				reservationList.add(r);
			}
			else {
				System.out.println("Object is not instance of Reservation");
				break;
			}
		}
	}
	
	@Override
	public void addReservation(Guest guest, Room room, boolean walkIn) {
		LocalDate checkInDate = null;
		if (walkIn) {
			checkInDate = LocalDate.now();
		}
		else {
			boolean valid = false;
			DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
			while (!valid) {
				try {
					System.out.print("Enter check in date (yyyymmdd): ");
					String date = sc.nextLine();
					checkInDate = LocalDate.parse(date, dateFormatter);
					valid = true;
				}
				catch (DateTimeParseException e) {
					System.out.println("Invalid date format");
				}
			}
		}
		System.out.print("Enter the number of adults checking in: ");
		int noOfAdults = 0;
		while (noOfAdults < 1) {
			noOfAdults = validateChoice(noOfAdults, "Enter the number of adults checking in: ");
			if (noOfAdults < 1)
				System.out.println("Value should not be less than 1");
		}
		
		System.out.print("Enter the number of childrens checking in: ");
		int noOfChildren = -1;
		while (noOfChildren < 0) {
			noOfChildren = validateChoice(noOfChildren, "Enter the number of childrens checking in: ");
			if (noOfChildren < 0)
				System.out.println("Value should not be less than 0");
		}
		
		Reservation r = new Reservation(getCounter(), guest, room, checkInDate, noOfAdults, noOfChildren, walkIn);
		reservationList.add(r);
		setCounter(getCounter() + 1);
		writeToFile(reservationList, Reservation.class);
		System.out.println("Reservation has been added");
	}
	@Override
	public void modify() {
		ArrayList<Reservation> tempList = searchReservation();
		if (tempList.size() == 0) {
			// reservationList is empty or name not found
			System.out.println("name is not found in the guest list");
			return;
		}
		if (tempList.size() > 1) {
			// multiple guest name found
			System.out.println("Multiple reservation found. Please refine your search query");
			for (Reservation r : tempList) {
				print(r);
			}
			return;
		}
		Reservation r = tempList.get(0);
		
		int option = -1;
		do {
			System.out.println("\n+------------------------------+");
			System.out.println("| What you you like to modify? |");
			System.out.println("+------------------------------+");
			System.out.println("| 0. Nothing                   |");
			System.out.println("| 1. Number of children        |");
			System.out.println("| 2. Number of adults          |");
			System.out.println("+------------------------------+");
			System.out.print("Enter choice: ");
			option = validateChoice(option, "Enter choice: ");
			
			switch(option) {
				case 0:
					System.out.println("Going back...");
					writeToFile(reservationList, Reservation.class);
					break;
				case 1:
					int noOfChildren = -1;
					while (noOfChildren < 0) {
						System.out.print("Enter the number of children: ");
						noOfChildren = validateChoice(noOfChildren, "Enter the number of children: ");
						if (noOfChildren < 0)
							System.out.println("Value should not be less than 0");
					}
					r.setNoOfChildren(noOfChildren);
					break;
				case 2:
					int noOfAdults = 0;
					while (noOfAdults < 1) {
						System.out.print("Enter the number of adults: ");
						noOfAdults = validateChoice(noOfAdults, "Enter the number of adults: ");
						if (noOfAdults < 1)
							System.out.println("Value should not be less than 1");
					}
					r.setNoOfChildren(noOfAdults);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (option != 0);
	}
	@Override
	public void printSingle() {
		ArrayList<Reservation> tempList = searchReservation();
		if (tempList.size() == 0) {
			System.out.println("Name is not found in the reservation list");
			return;
		}
		if (tempList.size() > 1) {
			System.out.println("Multiple reservation found. Please refine your search query");
			for (Reservation reservation : tempList) {
				System.out.println("Name: " + reservation.getGuest().getName());
			}
			return;
		}
		print(tempList.get(0));
	}
	@Override
	public void printAll() {
		for (Reservation r : reservationList) {
			print(r);
		}
	}
	@Override
	public void adjustObject(EntityManager entityManager) {
		Object[] objArray = entityManager.getList();
		// do something if there are objects in the array
		if (objArray.length > 0) {
			// adjust reservation Guest if object is of Guest type
			if (objArray[0] instanceof Guest) {
				for (Reservation reservation : reservationList ) {
					Guest guest = (Guest) objArray[reservation.getGuest().getId() - 1];
					reservation.setGuest(guest);
				}
				return;
			}
			// adjust reservation room if object is of Room type
			if (objArray[0] instanceof Room) {
				for (Reservation reservation : reservationList) {
					Room room = (Room) objArray[reservation.getRoom().getId() - 1];
					reservation.setRoom(room);
				}
				return;
			}
		}
	}
	
	/*
	 * This method is used to ensure that user enters an integer
	 */
	private int validateChoice(int choice, String inputText) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextInt()) {
				System.out.println("Invalid Input. Please enter an integer");
				sc.nextLine();	// clear the input in the buffer
				System.out.print(inputText);
			}
			else {
				valid = true;
				choice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return choice;
	}
	private ArrayList<Reservation> searchReservation() {
		ArrayList<Reservation> tempList = new ArrayList<Reservation>();
		
		System.out.print("Enter guest name: ");
		String name = sc.nextLine();
		
		if (reservationList.size() == 0) {
			return tempList;
		}
		for (Reservation r : reservationList) {
			if (r.getGuest().getName().contains(name)) {
				tempList.add(r);
			}
		}
		
		return tempList;
	}
	private void print(Reservation r) {
		if (r.getStatus().equals(Reservation.ResStatus.CHECKED_OUT))
			System.out.printf("Name: %s, Room Number: %d-%d, Check In Date: %s, Check Out Date: %s, No of Adults: %d, No of Children: %d\n", r.getGuest().getName(), r.getRoom().getRoomLevel(), r.getRoom().getRoomNumber(), r.getCheckInDate().toString(), r.getCheckOutDate().toString(), r.getNoOfAdults(), r.getNoOfChildren());
		else
			System.out.printf("Name: %s, Room Number: %d-%d, Check In Date: %s, Check Out Date: %s, No of Adults: %d, No of Children: %d\n", r.getGuest().getName(), r.getRoom().getRoomLevel(), r.getRoom().getRoomNumber(), r.getCheckInDate().toString(), "NIL", r.getNoOfAdults(), r.getNoOfChildren());
	}
}
