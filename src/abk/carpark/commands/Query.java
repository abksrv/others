package abk.carpark.commands;

public interface Query<T>
{
    T execute(String args);
}
