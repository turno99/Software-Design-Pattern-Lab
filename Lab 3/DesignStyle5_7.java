
import java.util.Map;
// abstract class to prevent instantiation
public abstract class DesignStyle5_7 {
    public abstract Map<String, String> getDefaultProperties();

    public abstract Map<String,String> getOverWrittenProperties();
    // assign values to each individual properties 
    // after obtaining them from maps
    public void apply(ConfigItem5_7 item)
    {
        for(Map.Entry<String, String> property : getDefaultProperties().entrySet())
        {
            item.addPropertyUnique(property.getKey(), property.getValue());
        }
        for(Map.Entry<String, String> property : getOverWrittenProperties().entrySet())
        {
            item.addProperty(property.getKey(), property.getValue());
        }
    }
    // methods for setting color and font size from user input
    public abstract void setColorProperty(String colorCode);

    public abstract void setFontSizeProperty(int fontSize);
}
