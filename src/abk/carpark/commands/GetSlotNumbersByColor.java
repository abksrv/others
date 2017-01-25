package abk.carpark.commands;

import java.util.List;

import abk.carpark.api.MultilevelCarPark;
import abk.carpark.api.Query;

public class GetSlotNumbersByColor implements Query<List<Integer>>
{

    private static final String queryName = "slot_numbers_for_cars_with_colour";
    private MultilevelCarPark carPark;
    
    @Override
    public List<Integer> execute(String args)
    {
        return carPark.getParkingRegister().getSlotByColor(args);
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
