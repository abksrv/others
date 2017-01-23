import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import abk.carpark.api.ParkingEntry;
import abk.carpark.api.ParkingRegister;

public class ParkingRegisterTest {

	private ParkingRegister parkingRegister;
	
	@Rule
	public ExpectedException e = ExpectedException.none();

	@Before
	public void setUp() {
		this.parkingRegister = new ParkingRegister();
	}

	@Test
	public void status_empty_returnEmptySet() {
		Assert.assertEquals(0, parkingRegister.status().length);
	}

	@Test
	public void enter_parkTwoCars_statusWithSize2() {
		parkingRegister.enter(1, "KA­01­HH­1234", "White");
		parkingRegister.enter(2, "KA­02­HH­1234", "Black");
		Assert.assertEquals(2, parkingRegister.status().length);
	}
	
	@Test
	public void exit_exitIllegalCar_throwException()
	{
		parkingRegister.enter(1, "KA­01­HH­1234", "White");
		e.expect(IllegalArgumentException.class);
		parkingRegister.exit(2);
	}
	
	@Test
	public void exit_exitCar_statusWithoutThatCar()
	{
		parkingRegister.enter(1, "KA­01­HH­1234", "White");
		parkingRegister.enter(2, "KA­02­HH­1234", "Black");
		parkingRegister.exit(2);
		ParkingEntry[] status = parkingRegister.status();
		ParkingEntry parkingEntry = new ParkingEntry(1, "KA­01­HH­1234", "White");
		ParkingEntry parkingEntry2 = new ParkingEntry(2, "KA­02­HH­1234", "Black");
		
		Assert.assertTrue(Arrays.asList(status).contains(parkingEntry));
		Assert.assertFalse(Arrays.asList(status).contains(parkingEntry2));
	}
	
}
