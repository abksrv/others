package abk.carpark.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ParkingRegister {

	SlotRegNumBiMap regNumSlotbiMap;
	Map<Integer, String> colorToSlot = new HashMap<>();
	private int capacity;

	public ParkingRegister(int capacity) {
		this.capacity = capacity;
		regNumSlotbiMap = new SlotRegNumBiMap(capacity);
	}

	public ParkingEntry[] status() {
		ParkingEntry[] entries = new ParkingEntry[regNumSlotbiMap.size()];
		Set<Entry<Integer, String>> entrySet = colorToSlot.entrySet();
		int i = 0;
		for (Entry<Integer, String> e : entrySet) {
			Integer slot = e.getKey();
			String color = e.getValue();
			entries[i++] = new ParkingEntry(slot,
					regNumSlotbiMap.getBySlot(slot), color);
		}
		Arrays.sort(entries);
		return entries;
	}

	public boolean enter(int slot, String regNum, String color) {
		if (slot <= capacity) {
			regNumSlotbiMap.put(slot, regNum);
			colorToSlot.put(slot, color);
			return true;
		}
		return false;
	}

	public void exit(int slot) {
		if (regNumSlotbiMap.getBySlot(slot) == null)
			throw new IllegalArgumentException("No such entry!");
		regNumSlotbiMap.remove(slot);
		colorToSlot.remove(slot);
	}

	public List<String> getRegNumsByColor(String string) {
		List<String> list = new ArrayList<>();
		for (Entry<Integer, String> entry : colorToSlot.entrySet()) {
			if (entry.getValue().equals(string))
				list.add(regNumSlotbiMap.getBySlot(entry.getKey()));
		}
		return list;
	}

	public List<Integer> getSlotByColor(String string) {
		List<Integer> list = new ArrayList<>();
		for (Entry<Integer, String> entry : colorToSlot.entrySet()) {
			if (entry.getValue().equals(string))
				list.add(entry.getKey());
		}
		return list;
	}

	public int getSlotByRegNum(String string) {
		return regNumSlotbiMap.getByReg(string);
	}

	/**
	 * BiMap to store reg num and slot mapping for cars. Serves the purpose for
	 * looking up either by color or slot in a constant time.
	 * 
	 * @author AbK
	 *
	 */
	public static class SlotRegNumBiMap {

		String[] regNumAtSlotMinusOne;
		Map<String, Integer> regToSlotMap;

		public SlotRegNumBiMap(int capacity) {
			regNumAtSlotMinusOne = new String[capacity];
			regToSlotMap = new HashMap<String, Integer>();
		}

		public void put(int i, String string) {
			validateSlot(i);
			regNumAtSlotMinusOne[i - 1] = string;
			regToSlotMap.put(string, i);
		}

		private static void validateSlot(int i) {
			if (i <= 0)
				throw new IllegalArgumentException("Slot lesser than 1!");

		}

		public String getBySlot(int i) {
			if (i <= regNumAtSlotMinusOne.length)
				return regNumAtSlotMinusOne[i - 1];
			return null;
		}

		/**
		 * 
		 * @param reg
		 * @return slot, 0 if the reg num doesnt exist
		 */
		public int getByReg(String reg) {
			Integer slot = regToSlotMap.get(reg);
			if (slot == null)
				return 0;
			return slot;
		}

		public void remove(int i) {
			String slot = getBySlot(i);
			if (slot != null) {
				regNumAtSlotMinusOne[i - 1] = null;
				regToSlotMap.remove(slot);
			}
		}

		public int size() {
			return regToSlotMap.size();
		}
	}
}
