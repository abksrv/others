package abk.carpark.commands;

import abk.carpark.api.MultilevelCarPark;
import abk.carpark.api.Query;

public class GetSlotNumberByRegNum implements Query<Integer>
{
    private static final String queryName = "slot_number_for_registration_number";
    private MultilevelCarPark carPark;
    
    @Override
    public Integer execute(String args)
    {
        return carPark.getParkingRegister().getSlotByRegNum(args);
    }
    
    @Override
    public String getQueryName()
    {
        return queryName;
    }
    
    @Override
    public void setCarParkInstance(MultilevelCarPark carPark)
    {
        this.carPark = carPark;
    }
}
