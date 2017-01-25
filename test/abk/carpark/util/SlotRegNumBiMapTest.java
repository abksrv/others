package abk.carpark.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import abk.carpark.api.ParkingRegister;
import abk.carpark.api.ParkingRegister.SlotRegNumBiMap;

public class SlotRegNumBiMapTest {

	private static final String REG_NUM_1 = "KA­01­HH­1234";
	private static final String REG_NUM_2 = "KA­02­HH­1234";

	private ParkingRegister.SlotRegNumBiMap biMap;

	@Rule
	public ExpectedException e = ExpectedException.none();

	@Before
	public void setUp() {
		this.biMap = new SlotRegNumBiMap(2);
	}

	@Test
	public void put_insert_checkOIfThere() {
		biMap.put(1, REG_NUM_1);
		String bySlot = biMap.getBySlot(1);
		Assert.assertEquals(REG_NUM_1, bySlot);
	}

	@Test
	public void put_insertAtSlot0_throwException() {
		e.expect(IllegalArgumentException.class);
		biMap.put(0, REG_NUM_1);
	}

	@Test
	public void getBySlot_searchInvalidSlot_returnNull() {
		biMap.put(1, REG_NUM_1);
		String bySlot = biMap.getBySlot(2);
		Assert.assertNull(bySlot);
	}

	@Test
	public void getByReg_insert_checkSlot() {
		biMap.put(1, REG_NUM_1);
		int byReg = biMap.getByReg(REG_NUM_1);
		Assert.assertEquals(1, byReg);
	}

	@Test
	public void getByReg_searchInvalid_return0() {
		biMap.put(1, REG_NUM_1);
		int byReg = biMap.getByReg(REG_NUM_2);
		Assert.assertEquals(0, byReg);
	}

	@Test
	public void getByRegAndGetBySlotMustMutuallyAgree() {
		biMap.put(1, REG_NUM_1);
		biMap.put(2, REG_NUM_2);
		int byReg = biMap.getByReg(REG_NUM_2);
		String bySlot = biMap.getBySlot(byReg);
		Assert.assertEquals(REG_NUM_2, bySlot);
		String bySlot2 = biMap.getBySlot(1);
		int byReg2 = biMap.getByReg(bySlot2);
		Assert.assertEquals(1, byReg2);
	}

	@Test
	public void remove_shouldRemoveEntry() {
		biMap.put(1, REG_NUM_1);
		biMap.put(2, REG_NUM_2);
		biMap.remove(2);
		int byReg = biMap.getByReg(REG_NUM_2);
		String bySlot = biMap.getBySlot(2);
		Assert.assertNull(bySlot);
		Assert.assertEquals(0, byReg);
	}

	@Test
	public void size_empty_return0() {
		Assert.assertEquals(0, biMap.size());
	}

	@Test
	public void size_twoEntries_return2() {
		biMap.put(1, REG_NUM_1);
		biMap.put(2, REG_NUM_2);
		Assert.assertEquals(2, biMap.size());
	}
}