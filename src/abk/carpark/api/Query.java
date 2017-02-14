package abk.carpark.api;

public interface Query<T>
{
    T execute(String args);
    
    String getQueryName();
    
    void setCarParkInstance(MultilevelCarPark carPark);
}
