package Grafica.Graficos;

import Grafica.Panel;
import Grafica.Ventana;

import javax.swing.*;
import java.awt.*;

public class GraficoBarraProgreso extends Panel {

    private double sumatorioPesos;
    JLabel porcentajeProgreso;

    public GraficoBarraProgreso(double sumatorioPesos){
        setBackground(Ventana.colorPrimario);
        this.sumatorioPesos = sumatorioPesos;
        setSize(620, 80);

        porcentajeProgreso = new JLabel(df.format(sumatorioPesos)+"%");
        porcentajeProgreso.setForeground(Ventana.colorTernario);
        porcentajeProgreso.setFont(Ventana.font);
        porcentajeProgreso.setBounds(getWidth()/2-50,10,100,50);
        porcentajeProgreso.setHorizontalAlignment(SwingConstants.CENTER);
        porcentajeProgreso.setVerticalAlignment(SwingConstants.CENTER);
        add(porcentajeProgreso);
    }

    public void actualizar(double pesos){
        sumatorioPesos = pesos;
        porcentajeProgreso.setText(df.format(sumatorioPesos)+"%");
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Ventana.colorSecundario);
        g2.fillRect(10,10,(int)(6*sumatorioPesos),50);
        g2.setColor(Ventana.colorTernario);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(getWidth()/2-300,10,600,50);
    }
}
