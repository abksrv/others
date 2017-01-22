package abk.carpark.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * BiMap to store reg num and slot mapping for cars. Serves the purpose for
 * looking up either by color or slot in a constant time.
 * 
 * @author AbK
 *
 */
public class SlotRegNumBiMap {

	ArrayList<String> regNumAtSlotMinusOne;
	Map<String, Integer> regToSlotMap;

	public SlotRegNumBiMap() {
		regNumAtSlotMinusOne = new ArrayList<String>();
		regToSlotMap = new HashMap<String, Integer>();
	}

	public void put(int i, String string) {
		validateSlot(i);
		regNumAtSlotMinusOne.ensureCapacity(i - 1);
		regNumAtSlotMinusOne.add(i - 1, string);
		regToSlotMap.put(string, i);
	}

	private static void validateSlot(int i) {
		if (i <= 0)
			throw new IllegalArgumentException("Slot lesser than 1!");

	}

	public String getBySlot(int i) {
		if (i <= regNumAtSlotMinusOne.size())
			return regNumAtSlotMinusOne.get(i - 1);
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
			regNumAtSlotMinusOne.remove(i - 1);
			regToSlotMap.remove(slot);
		}
	}

	public int size() {
		return regToSlotMap.size();
	}
}
