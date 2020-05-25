package control;

import interfaces.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import entity.Reservation;

/**
 * 
 * @author muhazerin
 *
 */

public class ReservationManager extends EntityManager implements PrintSingleObject, PrintAllObjects{
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
	public void add(String guestName, String roomNumber, boolean walkIn) {
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
		
		Reservation r = new Reservation(getCounter(), guestName, roomNumber, checkInDate, noOfAdults, noOfChildren, walkIn);
		reservationList.add(r);
		setCounter(getCounter() + 1);
		writeToFile(reservationList, Reservation.class);
		System.out.println("Reservation has been added");
	}
	@Override
	public void printSingle() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void printAll() {
		for (Reservation r : reservationList) {
			if (r.getStatus().equals(Reservation.ResStatus.CHECKED_OUT))
				System.out.printf("Name: %s, Room Number: %s, Check In Date: %s, Check Out Date: %s, No of Adults: %d, No of Children: %d\n", r.getGuestName(), r.getRoomName(), r.getCheckInDate().toString(), r.getCheckOutDate().toString(), r.getNoOfAdults(), r.getNoOfChildren());
			else
				System.out.printf("Name: %s, Room Number: %s, Check In Date: %s, Check Out Date: %s, No of Adults: %d, No of Children: %d\n", r.getGuestName(), r.getRoomName(), r.getCheckInDate().toString(), "NIL", r.getNoOfAdults(), r.getNoOfChildren());

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
}
