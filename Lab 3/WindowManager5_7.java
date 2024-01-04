

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;
// singleton
public class WindowManager5_7 {
    private static WindowManager5_7 instance;
    private ArrayList<String> colors;
    private WindowManager5_7()
    {

    }

    public static WindowManager5_7 getInstance()
    {
        if(instance==null)
        {
            instance = new WindowManager5_7();
        }
        return instance;
    }

    private DesignStyle5_7 designStyle = new DefaultDesignStyle5_7();

    // method for taking color and font size input for each component
    private void promptChanges()
    {
        Scanner scanner = new Scanner(System.in);
        colors = new ArrayList<String>();
        // color codes for the list of colors to be selected from
        colors.add("#000000");
        colors.add("#ff0000");
        colors.add("#00ff00");
        colors.add("#0000ff");
        colors.add("#00ffff");
 
        int x = 0;
        while(x<1||x>5)
        {
            System.out.println("Set foreground color of current element");
            System.out.println("1) Black\n2) Red\n3) Green\n4) Blue\n5) Cyan");
            System.out.println("Choose the number option: ");
            x = scanner.nextInt();
            if(x<1||x>5)
            {
                System.out.println("Choose a proper option\n");
            }
        }
        designStyle.setColorProperty(colors.get(x-1));
        System.out.println("Set text size of current element: (Ineffective for Simplistic Design Style)");
        int y = scanner.nextInt();
        designStyle.setFontSizeProperty(y);
 
    }
    public void setDesignStyle(DesignStyle5_7 style)
    {
        designStyle = style;
    }
    // method for loading the UI with specified elements
    public void loadUI(ConfigManager5_7 config, int width, int height, String title) {
        try {
            Frame frame = new Frame(title); // frame for containing elements
            
            while (config.hasMoreItems()) {
                ConfigItem5_7 item = config.nextItem();                
                // designStyle.setColorProperty("#00ff00");
                // designStyle.setFontSizeProperty(20);
                promptChanges();
                designStyle.apply(item);
                ComponentFactory5_7 factory = ComponentFactoryProducer5_7.getInstance().getFactory(item.getName());
                Component comp = factory.create(item);
                frame.add(comp);
            }
            // configuring the frame
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);
            frame.setSize(width, height);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    frame.dispose();
                }
            });
        } catch (ConfigManager5_7.InvalidConfigException5 exp) {
            System.out.println("Error: Invalid Configuration");
        }
    }
}
