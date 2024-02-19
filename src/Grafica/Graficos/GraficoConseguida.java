package Grafica.Graficos;

import Grafica.Panel;
import Grafica.Ventana;

import javax.swing.*;
import java.awt.*;

public class GraficoConseguida extends Panel {

    private double notaConseguida, notaPerdida;
    private JLabel textoNotaConseguida, labelNotaMaxima;

    public GraficoConseguida(double notaConseguida, double notaPerdida){
        this.notaConseguida = notaConseguida;
        this.notaPerdida = notaPerdida;
        setSize(400, 300);
        setBackground(Ventana.colorPrimario);

        JLabel labelNotaConseguida = new JLabel("NOTA CONSEGUIDA / MÁXIMA");
        add(labelNotaConseguida);
        labelNotaConseguida.setForeground(Ventana.colorTernario);
        labelNotaConseguida.setBounds(0,0,getWidth(),50);
        labelNotaConseguida.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaConseguida.setFont(Ventana.font);

        textoNotaConseguida = new JLabel();
        if(notaConseguida == -1)
            textoNotaConseguida.setText("0");
        else
            textoNotaConseguida.setText(df.format(notaConseguida));
        add(textoNotaConseguida);
        textoNotaConseguida.setBounds(0, 125, getWidth(), 50);
        textoNotaConseguida.setHorizontalAlignment(SwingConstants.CENTER);
        textoNotaConseguida.setFont(Ventana.font);

        labelNotaMaxima = new JLabel("Max: "+df.format(10 - notaPerdida));
        add(labelNotaMaxima);
        labelNotaMaxima.setBounds(0, 240, getWidth(), 30);
        labelNotaMaxima.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaMaxima.setForeground(Ventana.colorTernario);
    }

    public void actualizar(double notaConseguida, double notaPerdida){
        this.notaConseguida = notaConseguida;
        this.notaPerdida = notaPerdida;
        if(notaConseguida == -1)
            textoNotaConseguida.setText("0");
        else
            textoNotaConseguida.setText(df.format(notaConseguida));
        labelNotaMaxima.setText("Max: "+df.format(10 - notaPerdida));
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        if (notaConseguida == -1) notaConseguida = 0;
        int gradosConseguidos = Math.round((float) (notaConseguida * 36));
        int gradosPerdidos = Math.round((float) (notaPerdida * 36));
        int gradosRestantes = 360 - (gradosConseguidos + gradosPerdidos);
        g2.setColor(Ventana.colorTernario);
        g2.drawArc(getWidth() / 2 - 70, 80, 140, 140, 0, gradosConseguidos);
        g2.setColor(Ventana.colorSecundario);
        g2.drawArc(getWidth() / 2 - 70, 80, 140, 140, gradosConseguidos, gradosRestantes);
    }
}
