

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


//This method uses our XMLParser and wraps the XML objects into
// ConfigItems which our factories can use to produce GUI elements
public class ConfigManagerXML5_7 implements ConfigManager5_7{

    private ArrayList<ConfigItem5_7> itemList;
    private int idx;
    public ConfigManagerXML5_7(File file)throws FileNotFoundException
    {
        this.parseXML(file);
        idx = 0;
    }
    // for processing a list of config items
    @Override
    public boolean hasMoreItems() throws InvalidConfigException5
    {
        //If the itemList has not been able to be initialized, then we can infer an Invalid config error
        if(itemList != null)
        {
            return idx != itemList.size();
        }
        else
        {
            throw new ConfigManager5_7.InvalidConfigException5("Parsing error", new Exception());
        }
    }
    @Override
    public ConfigItem5_7 nextItem() throws InvalidConfigException5
    {
        if(itemList != null)
        {
            if(idx < itemList.size()){
                idx+=1;
                return itemList.get(idx-1);
            }
            else
            {
                throw new ConfigManager5_7.InvalidConfigException5("Parsing error", new Exception());
            }
        }
        else 
        {
            throw new ConfigManager5_7.InvalidConfigException5("Parsing error", new Exception());
        }
    }
    //This is the method that uses our instance of XMLParser to wrap objects
    public void parseXML(File file) throws FileNotFoundException
    {
        try{
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxFactory.newSAXParser();
            try{
                this.itemList = new ArrayList<ConfigItem5_7>();
                XMLParser5_7 saxHandler = new XMLParser5_7(itemList);
                saxParser.parse(file,saxHandler);

            }catch(FileNotFoundException e)
            {
                throw e;
            }

        }catch(Exception e)
        {

        }
    }
    
}
