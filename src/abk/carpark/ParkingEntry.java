package abk.carpark;

public class ParkingEntry {
	int slot;
	private Car car;

	@Override
	public String toString() {
		return slot + " " + car;
	}
}
