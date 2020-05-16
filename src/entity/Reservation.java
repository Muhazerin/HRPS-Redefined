package entity;

import java.time.LocalDate;
import java.io.Serializable;
//import java.util.ArrayList;

/**
 * 
 * @author muhazerin
 *
 */

public class Reservation extends Entity implements Serializable{
	public static enum ResStatus {
		CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED, CHECKED_OUT;
	}
	private static final long serialVersionUID = 1L;
	private String guestName;
	private String roomNumber;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int noOfAdults;
	private int noOfChildren;
	private ResStatus resStatus;
	//private ArrayList<RoomService> roomServiceList;
	
	public Reservation(int id, String guestName, String roomNumber, LocalDate checkInDate, int noOfAdults, int noOfChildren, boolean walkIn) {
		super(id);
		this.guestName = guestName;
		this.roomNumber = roomNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = null;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		if (walkIn)
			resStatus = ResStatus.CHECKED_IN;
		else
			resStatus = ResStatus.CONFIRMED;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getRoomName() {
		return roomNumber;
	}
	public void setRoom(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNoOfAdults() {
		return noOfAdults;
	}
	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public ResStatus getStatus() {
		return resStatus;
	}
	public void setStatus(ResStatus resStatus) {
		this.resStatus = resStatus;
	}
	
}
