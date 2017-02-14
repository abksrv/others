package abk.carpark.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Using a kind dependency injection for loading queries at runtime. <br>
 * This class is independent of any Query or modification thereof. Facilitates dynamically adding additional queries. A normal
 * if-else/switch based processing of queries would have required recompiling this class on modification of a query string or
 * addition/deletion of new query.
 * 
 * @author z003cp8w
 *
 */
public class CommandRegistry
{
    Map<String, Query<?>> commandRegistry = new HashMap<>();
    private MultilevelCarPark carPark;
    
    public CommandRegistry(MultilevelCarPark carPark) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        loadCommands();
    }
    
    private void loadCommands() throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        File commandFolder = new File("bin/abk/carpark/commands");
        File[] listFiles = commandFolder.listFiles();
        for (File f : listFiles)
        {
            String className = convertToClassName(f.getPath());
            Class<?> forName = Class.forName(className);
            if (forName.isAssignableFrom(Query.class))
            {
                Query<?> queryInstance = ((Query<?>) forName.newInstance());
                queryInstance.setCarParkInstance(this.carPark);
                commandRegistry.put(queryInstance.getQueryName(), queryInstance);
            }
        }
    }
    
    private String convertToClassName(String path)
    {
        return path.substring(4).replace('/', '.');
    }
    
    Query<?> getQuery(String query)
    {
        return commandRegistry.get(query);
    }
}
