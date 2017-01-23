package abk.carpark.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import abk.carpark.util.SlotRegNumBiMap;

public class ParkingRegister
{
    
    SlotRegNumBiMap regNumSlotbiMap = new SlotRegNumBiMap();
    Map<Integer, String> colorToSlot = new HashMap<>();
    
    public ParkingRegister()
    {
    }
    
    public ParkingEntry[] status()
    {
        ParkingEntry[] entries = new ParkingEntry[regNumSlotbiMap.size()];
        Set<Entry<Integer, String>> entrySet = colorToSlot.entrySet();
        int i = 0;
        for (Entry<Integer, String> e : entrySet)
        {
            Integer slot = e.getKey();
            String color = e.getValue();
            entries[i++] = new ParkingEntry(slot, regNumSlotbiMap.getBySlot(slot),
                color);
        }
        Arrays.sort(entries);
        return entries;
    }
    
    public void enter(int slot, String regNum, String color)
    {
        regNumSlotbiMap.put(slot, regNum);
        colorToSlot.put(slot, color);
    }
    
    public void exit(int slot)
    {
        if (regNumSlotbiMap.getBySlot(slot) == null)
            throw new IllegalArgumentException("No such entry!");
        regNumSlotbiMap.remove(slot);
        colorToSlot.remove(slot);
    }

    public List<String> getRegNumsByColor(String string)
    {
        List<String> list = new ArrayList<>();
        for(Entry<Integer, String> entry:colorToSlot.entrySet())
        {
            if(entry.getValue().equals(string))
                list.add(regNumSlotbiMap.getBySlot(entry.getKey()));
        }
        return list;
    }

    public List<Integer> getSlotByColor(String string)
    {
        List<Integer> list = new ArrayList<>();
        for(Entry<Integer, String> entry:colorToSlot.entrySet())
        {
            if(entry.getValue().equals(string))
                list.add(entry.getKey());
        }
        return list;
    }

    public int getSlotByRegNum(String string)
    {
        return regNumSlotbiMap.getByReg(string);
    }
}
