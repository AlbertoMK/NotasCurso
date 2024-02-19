package Grafica;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Boton extends JButton {
    public Boton(String texto){
        super(texto);
        setSize(150, 30);
        setBackground(Ventana.colorSecundario);
        setForeground(Ventana.colorTernario);
        setBorder(new LineBorder(Ventana.colorTernario));
        setFocusPainted(false);
        setFont(new Font("Arial Rounded MT Bold", 0, 13));
    }
}
