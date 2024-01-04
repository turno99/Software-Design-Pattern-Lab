import java.io.FileNotFoundException;
public class App5_7{
    // main class
    public static void main(String[] args) throws FileNotFoundException
    {
        // menu for user interaction from console
        MenuHandler5_7 menu = new MenuHandler5_7();
        ConfigManager5_7 manager = null;
        // ask user for input file name
        try {
            manager = menu.showFilePathOption();
        } catch (Exception e) {
            
        }
        // ask user for desired design style     
        DesignStyle5_7 style = menu.showDesignOption();
        // load the UI
        try {
            WindowManager5_7.getInstance().setDesignStyle(style);
            WindowManager5_7.getInstance().loadUI(manager,1000,1000, menu.menuData.getFileName() + " " + menu.menuData.getDesignStyle());
        } catch (Exception e) {
            
        }
        
    }
   
}