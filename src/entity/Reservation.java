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
	private int guestId;
	private int roomId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int noOfAdults;
	private int noOfChildren;
	private ResStatus resStatus;
	//private ArrayList<RoomService> roomServiceList;
	
	public Reservation(int id, int guestId, int roomId, LocalDate checkInDate, int noOfAdults, int noOfChildren) {
		super(id);
		this.guestId = guestId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = null;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		resStatus = ResStatus.CONFIRMED;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
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
