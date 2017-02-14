package abk.carpark.commands;

import java.util.List;

import abk.carpark.api.MultilevelCarPark;
import abk.carpark.api.Query;

public class GetRegistrationNumsByColor implements Query<List<String>>
{
    
    private static final String queryName = "registration_numbers_for_cars_with_colour";
    private MultilevelCarPark carPark;
    
    @Override
    public List<String> execute(String args)
    {
        return carPark.getParkingRegister().getRegNumsByColor(args);
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
