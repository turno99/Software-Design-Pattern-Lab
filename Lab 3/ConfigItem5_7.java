
import java.util.HashMap;
import java.util.Map;

// A data class that contains all important attributes and names
// for the javaFX ui components. All XML and Config files will
// be parsed into ConfigItem objects

public class ConfigItem5_7 {
    //Name of the ConfigItem
    private String name;
    //A hashmap with Keys like font-family, and properties like sans-serif
    private final Map<String,String> properties = new HashMap<String,String>();

    //Public Constructor
    public ConfigItem5_7(String name)
    {
        this.name = name;
    }
    //Setter and getter methods
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    //Fetch a key's mapped value 
    public String getProperty(String key)
    {
        return properties.get(key);
    }
    
    public String getProperty(String key, String defaultValue)
    {
        return properties.getOrDefault(key, defaultValue);
    }

    //Remove key-val map 
    public void removeProperty(String key)
    {
        this.properties.remove(key);
    }
    //Method to add new key-value pairs indefinitely overwriting previous key-value pairs
    public void addProperty(String key, String val)
    {
        this.properties.put(key,val);
    }
    //Method to add a new key-value pair if it already does not exist
    //This distinction is needed for different design styles
    public void addPropertyUnique(String key, String val)
    {
        this.properties.putIfAbsent(key,val);
    }
    @Override
    public String toString() {
        return String.format(
            "{name=%s, props=%s}", 
            this.name, this.properties
        );
    }

}
