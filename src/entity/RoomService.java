package entity;

import java.util.ArrayList;
import java.io.Serializable;

public class RoomService implements Serializable{
	public static enum RoomServiceStatus {
		ORDERED, PREPARING, DELIVERED;
	}
	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> roomService;
	private RoomServiceStatus roomServiceStatus;
	
	public RoomService(ArrayList<MenuItem> roomService) {
		this.roomService = roomService;
		roomServiceStatus = RoomServiceStatus.ORDERED;
	}
	public RoomServiceStatus getRoomServiceStatus() {
		return roomServiceStatus;
	}
	public void setRoomServiceStatus(RoomServiceStatus roomServiceStatus) {
		this.roomServiceStatus = roomServiceStatus;
	}
	public ArrayList<MenuItem> getRoomService() {
		return roomService;
	}
	public void setRoomService(ArrayList<MenuItem> roomService) {
		this.roomService = roomService;
	}
}
