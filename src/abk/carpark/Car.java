package abk.carpark;

public class Car {
	private String regNo;
	private String color;

	public Car(String regNo, String color) {
		super();
		this.regNo = regNo;
		this.color = color;
	}

	@Override
	public String toString() {
		return regNo + " " + color;
	}
}
