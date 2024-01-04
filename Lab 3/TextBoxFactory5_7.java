

import java.awt.*;
/**
 * TextBox factory
 * (specific properties: value)
 */
public class TextBoxFactory5_7 implements ComponentFactory5_7 {
    // default values for font
    protected static final int default_font_size = 10;
    protected static final String default_font_face = "TimesRoman";
    protected static final int default_font_style = Font.PLAIN;

    // map key for content (value)
    protected static final String VALUE = "value";
    // method for creating a textBox
    @Override
    public Component create(ConfigItem5_7 item) {
        TextArea textBox = new TextArea();
        String text = item.getProperty(VALUE);
        text = text.replace("\\n", "\n");
        textBox.setText(text);
        textBox.setEditable(false);
        textBox.setFocusable(false);
        // set position
        int posX = Integer.parseInt(item.getProperty("X", "0"));
        int posY = Integer.parseInt(item.getProperty("Y", "0"));
        textBox.setLocation(posX, posY);

        // set width and height
        String widthValue = item.getProperty("width");
        String heightValue = item.getProperty("height");
        if (widthValue != null && heightValue != null) {
            int width = Integer.parseInt(widthValue);
            int height = Integer.parseInt(heightValue);
            textBox.setSize(width, height);
        }

        // set foreground and background color
        String fgColor = item.getProperty("fgColor");
        if (fgColor != null) textBox.setForeground(Color.decode(fgColor));
        String bgColor = item.getProperty("bgColor");
        if (bgColor != null) textBox.setBackground(Color.decode(bgColor));

        // set font
        int fontSize = Integer.parseInt(item.getProperty("fontSize", Integer.toString(default_font_size)));
        Font font = new Font(default_font_face, default_font_style, fontSize);
        textBox.setFont(font);
        return textBox;
    }
}
