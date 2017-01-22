package abk.carpark.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import abk.carpark.util.SlotRegNumBiMap;

public class ParkingRegister {

	SlotRegNumBiMap biMap = new SlotRegNumBiMap();
	Map<String, List<Integer>> colorToSlot = new HashMap<>();

	public ParkingRegister() {
	}

	public ParkingEntry[] status() {
		ParkingEntry[] entries = new ParkingEntry[biMap.size()];
		Set<Entry<String, List<Integer>>> entrySet = colorToSlot.entrySet();
		int i = 0;
		for (Entry<String, List<Integer>> e : entrySet) {
			String color = e.getKey();
			for (Integer slot : e.getValue()) {
				entries[i++] = new ParkingEntry(slot, biMap.getBySlot(slot),
						color);
			}
		}
		Arrays.sort(entries);
		return entries;
	}

	public void enter(int slot, String regNum, String color) {
		biMap.put(slot, regNum);
		List<Integer> list = colorToSlot.get(color);
		if (list == null) {
			list = new ArrayList<Integer>();
			colorToSlot.put(color, list);
		}
		list.add(slot);
	}

	public void exit(int i) {
		if (biMap.getBySlot(i) == null)
			throw new IllegalArgumentException("No such entry!");
		biMap.remove(i);
	}
}
