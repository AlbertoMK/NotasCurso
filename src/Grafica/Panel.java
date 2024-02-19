package Grafica;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public abstract class Panel extends JPanel {

    protected static DecimalFormat df = new DecimalFormat("#.##");

    public Panel(){
        setLayout(null);
    }

    public static Color convertirPastel(Color c){
        int r = c.getRed() + (255 - c.getRed()) / 8 * 3;
        int g = c.getGreen() + (255 - c.getGreen()) / 8 * 3;
        int b = c.getBlue() + (255 - c.getBlue()) / 8 * 3;
        return new Color(r,g,b);
    }
}
