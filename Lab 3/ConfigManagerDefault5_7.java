

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



//This class is the default manager for storing ConfigItems
//This class uses config.txt files and takes the attributes
public class ConfigManagerDefault5_7 implements ConfigManager5_7{

    private final Scanner scanner;
    private ArrayList<ConfigItem5_7> itemList;
    private int idx;
    //Constructor 
    public ConfigManagerDefault5_7(File file) throws FileNotFoundException
    {
        this.scanner = new Scanner(file);
        idx = 0;
        itemList = new ArrayList<ConfigItem5_7>();
        try{
            
            parseText();
        }catch(InvalidConfigException5 e)
        {
            
        }
    }
    public ConfigManagerDefault5_7(String path) throws FileNotFoundException
    {
        this(new File(path));
    }
    // for processing elements in the list of config items
    @Override
    public boolean hasMoreItems() throws ConfigManager5_7.InvalidConfigException5
    {
        return idx < itemList.size();
    }
    @Override
    public ConfigItem5_7 nextItem()
    {
        idx+=1;
        return itemList.get(idx-1);
    }
    // for parsing individual items in the list
    public void parseText() throws InvalidConfigException5
    {
        String curLine;
        while(scanner.hasNextLine())
        {
            curLine = scanner.nextLine();
            String []tokens = curLine.split(",");
            String currentToken;
            int len = tokens.length;
            ConfigItem5_7 item = new ConfigItem5_7("Dummy");
            for(int i=0; i <len; i++)
            {
                currentToken = tokens[i].trim();
                if(i==0)item.setName(currentToken);
                else if(i == 1)
                {
                    item.addProperty("value", currentToken);
                }
                else
                {         
                    String [] pairToken = currentToken.split(":");
                    pairToken[0] = pairToken[0].trim();
                    pairToken[1] = pairToken[1].trim();
                    item.addProperty(pairToken[0], pairToken[1]);              
                }
            }
            itemList.add(item);
        }
        
    }

}