package Grafica.Graficos;

import Grafica.Panel;
import Grafica.Ventana;

import javax.swing.*;
import java.awt.*;

public class GraficoMedia extends Panel {
    private double media;
    JLabel labelNotaMedia, textoNotaMedia;

    public GraficoMedia(double media){
        this.media = media;
        setSize(200, 250);

        labelNotaMedia = new JLabel("NOTA MEDIA");
        add(labelNotaMedia);
        labelNotaMedia.setForeground(Ventana.colorTernario);
        labelNotaMedia.setBounds(0,0,getWidth(),50);
        labelNotaMedia.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaMedia.setFont(Ventana.font);

        textoNotaMedia = new JLabel("");
        textoNotaMedia.setBounds(0,125,getWidth(),50);
        textoNotaMedia.setHorizontalAlignment(SwingConstants.CENTER);
        textoNotaMedia.setFont(Ventana.font);
        add(textoNotaMedia);

        if( media == -1)
            textoNotaMedia.setText("Sin datos");
        else
            textoNotaMedia.setText(df.format(media));
    }

    public void actualizar(double media){
        this.media = media;
        if( media == -1)
            textoNotaMedia.setText("Sin datos");
        else
            textoNotaMedia.setText(df.format(media));
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        int grados;
        Graphics2D g2 = (Graphics2D) g;
        String mediaFormateada = df.format(media);
        mediaFormateada = mediaFormateada.replace(',', '.');
        media = Double.parseDouble(mediaFormateada);
        if (media == -1) {
            grados = 0;
        } else {
            grados = (int) (media * 36);
        }
        g2.setStroke(new BasicStroke(5));
        int r, green;
        if (media < 5) {
            r = 255;
            if (media == -1) green = 0;
            else {
                green = (int) (media * 51);
                if (green > 255) green = 255;
            }

        } else if (media == 5) {
            r = 255;
            green = 255;
        } else {
            green = 255;
            r = (int) (-51 * (media - 5) + 255);
            if (r < 0) r = 0;
        }
        g2.setColor(convertirPastel(new Color(r, green, 0)));
        if (grados > 360) grados = 360;
        if (grados < 0) grados = 0;
        g2.drawArc(getWidth() / 2 - 70, 80, 140, 140, 0, grados);
        g2.setColor(Ventana.colorSecundario);
        g2.drawArc(getWidth() / 2 - 70, 80, 140, 140, grados, 360 - grados);
    }
}
