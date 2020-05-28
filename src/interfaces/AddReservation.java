package interfaces;

import entity.Guest;
import entity.Room;

public interface AddReservation {
	public void addReservation(Guest guest, Room room, boolean walkIn);
}
