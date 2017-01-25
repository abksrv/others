package abk.carpark.api;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import abk.carpark.api.ParkingEntry;
import abk.carpark.api.ParkingRegister;

public class ParkingRegisterTest {

	private static final String COLOR_BLACK = "Black";
	private static final String COLOR_WHITE = "White";

	private static final String REG_NUM_1 = "KA­01­HH­1234";
	private static final String REG_NUM_2 = "KA­02­HH­1234";
	private static final String REG_NUM_3 = "KA­03­HH­1234";
	private static final String REG_NUM_4 = "KA­04­HH­1234";

	private ParkingRegister parkingRegister;

	@Rule
	public ExpectedException e = ExpectedException.none();

	@Before
	public void setUp() {
		this.parkingRegister = new ParkingRegister(3);
	}

	@Test
	public void status_empty_returnEmptySet() {
		Assert.assertEquals(0, parkingRegister.status().length);
	}

	@Test
	public void enter_parkTwoCars_statusWithSize2() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_2, COLOR_BLACK);
		Assert.assertEquals(2, parkingRegister.status().length);
	}

	 
	
	@Test
	public void exit_exitIllegalCar_throwException() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		e.expect(IllegalArgumentException.class);
		parkingRegister.exit(2);
	}

	@Test
	public void exit_exitCar_statusWithoutThatCar() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_2, COLOR_BLACK);
		parkingRegister.exit(2);
		ParkingEntry[] status = parkingRegister.status();
		ParkingEntry parkingEntry = new ParkingEntry(1, REG_NUM_1, COLOR_WHITE);
		ParkingEntry parkingEntry2 = new ParkingEntry(2, REG_NUM_2, COLOR_BLACK);

		Assert.assertTrue(Arrays.asList(status).contains(parkingEntry));
		Assert.assertFalse(Arrays.asList(status).contains(parkingEntry2));
	}

	@Test
	public void status_multipleEnterExit_checkConsistentStatus() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_2, COLOR_BLACK);
		parkingRegister.exit(2);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		ParkingEntry[] status = parkingRegister.status();
		ParkingEntry parkingEntry = new ParkingEntry(1, REG_NUM_1, COLOR_WHITE);
		ParkingEntry parkingEntry2 = new ParkingEntry(2, REG_NUM_3, COLOR_WHITE);

		Assert.assertEquals(2, status.length);
		Assert.assertTrue(Arrays.asList(status).contains(parkingEntry));
		Assert.assertTrue(Arrays.asList(status).contains(parkingEntry2));
	}

	@Test
	public void getRegNumsByColor_empty_returnEmptyList() {
		Assert.assertEquals(0, parkingRegister.getRegNumsByColor(COLOR_WHITE)
				.size());
	}

	@Test
	public void getRegNumsByColor_twoWhiteCars_returnRegNums() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		parkingRegister.enter(3, REG_NUM_2, COLOR_BLACK);
		List<String> regNums = parkingRegister.getRegNumsByColor(COLOR_WHITE);
		Assert.assertEquals(2, regNums.size());
		Assert.assertTrue(regNums.contains(REG_NUM_1));
		Assert.assertTrue(regNums.contains(REG_NUM_3));
	}

	@Test
	public void getRegNumsByColor_oneBlackCar_returnRegNums() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_BLACK);
		parkingRegister.enter(2, REG_NUM_2, COLOR_BLACK);
		parkingRegister.exit(2);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		List<String> regNums = parkingRegister.getRegNumsByColor(COLOR_BLACK);
		Assert.assertEquals(1, regNums.size());
		Assert.assertTrue(regNums.contains(REG_NUM_1));
	}

	@Test
	public void getSlotByColor_empty_returnEmptyList() {
		Assert.assertEquals(0, parkingRegister.getSlotByColor(COLOR_WHITE)
				.size());
	}

	@Test
	public void getSlotByColor_twoWhiteCars_returnSlots() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		parkingRegister.enter(3, REG_NUM_2, COLOR_BLACK);
		List<Integer> regNums = parkingRegister.getSlotByColor(COLOR_WHITE);
		Assert.assertEquals(2, regNums.size());
		Assert.assertTrue(regNums.contains(1));
		Assert.assertTrue(regNums.contains(2));
	}

	@Test
	public void getSlotByColor_oneBlackCar_returnSlot() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_BLACK);
		parkingRegister.enter(2, REG_NUM_2, COLOR_BLACK);
		parkingRegister.exit(2);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		List<Integer> regNums = parkingRegister.getSlotByColor(COLOR_BLACK);
		Assert.assertEquals(1, regNums.size());
		Assert.assertTrue(regNums.contains(1));
	}

	@Test
	public void getSlotByRegNum_parkTwo_corresNum() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		int slot = parkingRegister.getSlotByRegNum(REG_NUM_1);
		Assert.assertEquals(1, slot);
	}

	@Test
	public void getSlotByRegNum_InvalidNum_return0() {
		parkingRegister.enter(1, REG_NUM_1, COLOR_WHITE);
		parkingRegister.enter(2, REG_NUM_3, COLOR_WHITE);
		int slot = parkingRegister.getSlotByRegNum(REG_NUM_2);
		Assert.assertEquals(0, slot);
	}
}
