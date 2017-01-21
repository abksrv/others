import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abk.carpark.api.Car;
import abk.carpark.api.ParkingRegister;

public class ParkingRegisterTest {

	private ParkingRegister parkingRegister;

	@Before
	public void setUp() {
		this.parkingRegister = new ParkingRegister();
	}

	@Test
	public void status_empty_returnEmptySet() {
		Assert.assertEquals(0, parkingRegister.status().size());
	}

	@Test
	public void enter_parkTwoCars_returnSetWithSameEntriesOrderedBySlot() {
		parkingRegister.enter(0, new Car("KA­01­HH­1234", "White"));
		Assert.assertEquals(0, parkingRegister.status().size());
	}
	
	
}
