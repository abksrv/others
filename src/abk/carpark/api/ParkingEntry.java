package abk.carpark.api;

public class ParkingEntry implements Comparable<ParkingEntry> {
	int slot;
	private Car car;

	public ParkingEntry(int slot, String regNum, String color) {
		this.slot = slot;
		this.car = new Car(regNum, color);
	}

	@Override
	public String toString() {
		return slot + "\t" + car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + slot;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingEntry other = (ParkingEntry) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (slot != other.slot)
			return false;
		return true;
	}

	@Override
	public int compareTo(ParkingEntry o) {
		if (this.slot < o.slot)
			return -1;
		else if (this.slot > o.slot)
			return 1;
		return 0;
	}
}
