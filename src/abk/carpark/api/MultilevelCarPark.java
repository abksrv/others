package abk.carpark.api;

public class MultilevelCarPark
{
    private SlotKeeper slotKeeper;
    private ParkingRegister parkingRegister;
    
    public MultilevelCarPark(SlotKeeper slotKeeper, ParkingRegister parkingRegister)
    {
        super();
        this.slotKeeper = slotKeeper;
        this.parkingRegister = parkingRegister;
    }
    
    public boolean enter(Car car)
    {
        return false;
    }
    
    public void leave(int slot)
    {
        
    }
    
    public ParkingRegister getParkingRegister()
    {
        return parkingRegister;
    }
}
