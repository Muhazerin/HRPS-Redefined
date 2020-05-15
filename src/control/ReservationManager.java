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
	public void add(int guestId, int roomId, boolean walkIn) {
		LocalDate checkInDate = null;
		if (walkIn ) {
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
		System.out.println("this ran. ReservationManager");
	}
	@Override
	public void printSingle() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void printAll() {
		// TODO Auto-generated method stub
		
	}
}
