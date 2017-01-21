package abk.carpark.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RegColorBiMapTest {

	private SlotColorBiMap biMap;

	@Rule
	public ExpectedException e = ExpectedException.none();

	@Before
	public void setUp() {
		this.biMap = new SlotColorBiMap();
	}

	@Test
	public void put_insert_checkOIfThere() {
		biMap.put(1, "KA­01­HH­1234");
		String bySlot = biMap.getBySlot(1);
		Assert.assertEquals("KA­01­HH­1234", bySlot);
	}

	@Test
	public void put_insertAtSlot0_throwException() {
		e.expect(IllegalArgumentException.class);
		biMap.put(0, "KA­01­HH­1234");
	}

	@Test
	public void getBySlot_searchInvalidSlot_returnNull() {
		biMap.put(1, "KA­01­HH­1234");
		String bySlot = biMap.getBySlot(2);
		Assert.assertNull(bySlot);
	}

	@Test
	public void getByReg_insert_checkSlot() {
		biMap.put(1, "KA­01­HH­1234");
		int byReg = biMap.getByReg("KA­01­HH­1234");
		Assert.assertEquals(1, byReg);
	}

	@Test
	public void getByReg_searchInvalid_return0() {
		biMap.put(1, "KA­01­HH­1234");
		int byReg = biMap.getByReg("KA­02­HH­1234");
		Assert.assertEquals(0, byReg);
	}
	@Test
	public void getByRegAndGetBySlotMustMutuallyAgree() {
		biMap.put(1, "KA­01­HH­1234");
		biMap.put(2, "KA­02­HH­1234");
		int byReg = biMap.getByReg("KA­02­HH­1234");
		String bySlot = biMap.getBySlot(byReg);
		Assert.assertEquals("KA­02­HH­1234", bySlot);
		String bySlot2 = biMap.getBySlot(1);
		int byReg2 = biMap.getByReg(bySlot2);
		Assert.assertEquals(1, byReg2);
	}
	
	@Test
	public void remove_shouldRemoveEntry()
	{
		biMap.put(1, "KA­01­HH­1234");
		biMap.put(2, "KA­02­HH­1234");
		biMap.remove(2);
		int byReg = biMap.getByReg("KA­02­HH­1234");
		String bySlot = biMap.getBySlot(2);
		Assert.assertNull(bySlot);
		Assert.assertEquals(0, byReg);
	}
}
