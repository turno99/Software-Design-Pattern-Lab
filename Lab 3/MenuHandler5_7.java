import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// class for console interaction
public class MenuHandler5_7 {
    // for input
    private Scanner scanner;
    public MenuData5_7 menuData;
    public MenuHandler5_7()
    {
        scanner = new Scanner(System.in);
        menuData = new MenuData5_7();
    }
    // for parsing the path to the config file provided by user
    public ConfigManager5_7 showFilePathOption() throws FileNotFoundException
    {
        System.out.println("Enter file path from current directory (Ending with .txt or .xml): ");
        String path = scanner.nextLine();

        menuData.setFileName(path);
        String []pathParts = path.split("\\.");

        String extension = pathParts[pathParts.length-1];
        try {
            if(extension.equalsIgnoreCase("xml"))
            {
                return new ConfigManagerXML5_7(new File(path));
            }
            else if(extension.equalsIgnoreCase("txt"))
            {
                return new ConfigManagerDefault5_7(new File(path));
            }
            else throw new FileNotFoundException("Not desired format");
        } catch (Exception e) {
            
        }
        return null;
    }
    // for selecting the design style from user input
    public DesignStyle5_7 showDesignOption()
    { 
        int x = 5;
        while(x<1||x>2)
        {
            System.out.println("1) Simplistic Design\n2) High Detail Design\nType option number you prefer: ");
            x = scanner.nextInt();
            if(x<1||x>2)System.out.println("Choose appropriately");
        }
        switch(x)
        {
            case 1:
                menuData.setDesignStyle("Simplistic Design");
                return new SimplisticStyle5_7();
            case 2:
                menuData.setDesignStyle("High Detail Design");
                return new HighDetailStyle5_7();
        }
        return null;
    }

}
