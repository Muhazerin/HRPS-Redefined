package entity;

import java.io.Serializable;

/**
 * 
 * @author muhazerin
 *
 */

public class SingleRoom extends Room implements Serializable{
	private static final long serialVersionUID = 1L;
	private double rate;
	private String roomType;
	
	// sets the rate of the room
	public SingleRoom(int id, BedType bedType, AvailabilityStatus availabilityStatus, boolean wifiEnabled,
			String facing, boolean smokingAllowed, int roomLevel, int roomNumber) {
		super(id, bedType, availabilityStatus, wifiEnabled, facing, smokingAllowed, roomLevel, roomNumber);
		rate = 50;
		roomType = "Single";
	}

	@Override
	public double getRate() {
		return rate;
	}

	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String getRoomType() {
		return roomType;
	}
}
