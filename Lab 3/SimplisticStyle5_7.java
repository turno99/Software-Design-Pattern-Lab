

import java.util.HashMap;
import java.util.Map;

public class SimplisticStyle5_7 extends DesignStyle5_7{
    // maps for default and overwritten properties 
    private final Map<String, String> defaultProperties;
    private final Map<String, String> overWrittenProperties;
    // constructor initializes default properties for font
    public SimplisticStyle5_7()
    {
        defaultProperties = new HashMap<>();
        overWrittenProperties = new HashMap<>();
        defaultProperties.put("fontFace" , "TimesRoman");
        defaultProperties.put("fontStyle", "plain");
        defaultProperties.put("fontSize", "10");
    }
    @Override
    public Map<String, String> getDefaultProperties() {
        return defaultProperties;
    }

    @Override
    public Map<String, String> getOverWrittenProperties() {
        return overWrittenProperties;
    }

    public void setFontSizeProperty(int newSize)
    {
        
    }
    
    public void setColorProperty(String colorCode)
    {
        overWrittenProperties.put("fgColor", colorCode);        
    }
}
