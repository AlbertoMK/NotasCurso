package Grafica.Graficos;

import Grafica.Panel;
import Grafica.Ventana;
import Logica.Asignatura;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraficaBarras extends Panel {

    private JPanel panelLeyenda;
    private PanelBarras panelBarras;
    private JLabel leyenda;

    public GraficaBarras(HashMap<Asignatura, Double> notas){
        setSize(1080, 450);
        setBackground(Ventana.colorPrimario);
        panelBarras = new PanelBarras(notas);
        panelLeyenda = new JPanel();
        panelLeyenda.setLayout(null);
        panelLeyenda.setBounds(10, 0, 300, getHeight());
        panelBarras.setBounds(310, 0, 770, getHeight());

        //Panel leyenda
        leyenda = new JLabel("");
        leyenda.setFont(new Font("Arial Rounded MT Bold", 0, 19));
        leyenda.setVerticalAlignment(SwingConstants.CENTER);
        leyenda.setHorizontalAlignment(SwingConstants.CENTER);
        leyenda.setBounds(5, 0, panelLeyenda.getWidth() - 5, panelLeyenda.getHeight());
        StringBuilder textoLeyenda = new StringBuilder("<html>");
        for (Asignatura a : notas.keySet()) {
            textoLeyenda.append("<p style= 'color: rgb("+a.getColor().getRed()+" "+a.getColor().getGreen()+" "+a.getColor().getBlue()+");'>"+ a.getNombre()+"</p>\n");
        }
        textoLeyenda.append("</body>");
        leyenda.setText(textoLeyenda.toString());
        panelLeyenda.add(leyenda);
        panelLeyenda.setBackground(Ventana.colorPrimario);
        panelBarras.setBackground(Ventana.colorPrimario);
        add(panelLeyenda);
        add(panelBarras);
    }

    public void actualizar(HashMap<Asignatura, Double> notas){
        panelBarras.actualizar(notas);
        StringBuilder textoLeyenda = new StringBuilder("<html>");
        for (Asignatura a : notas.keySet()) {
            textoLeyenda.append("<p style= 'color: rgb("+a.getColor().getRed()+" "+a.getColor().getGreen()+" "+a.getColor().getBlue()+");'>"+ a.getNombre()+"</p>\n");
        }
        textoLeyenda.append("</html>");
        leyenda.setText(textoLeyenda.toString());
    }

    class PanelBarras extends Panel{

        private HashMap<Asignatura, Double> notas;

        public PanelBarras(HashMap<Asignatura, Double> notas){
            this.notas = notas;
        }

        public void actualizar(HashMap<Asignatura, Double> notas){
            this.notas = notas;
            this.repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int contador = 0;
            double sumaNotas = 0;
            for(Asignatura a : notas.keySet()){
                g.setColor(a.getColor());
                double nota = notas.get(a);
                if(nota == -1) {
                    nota = 0;
                    contador--;
                }
                g.fillRect(10 + contador * 35, getHeight() - (int)(getHeight() * (nota / 10)), 35, (int)(getHeight() * (nota / 10)));
                Color color = new Color((int)(a.getColor().getRed()*0.6), (int)(a.getColor().getGreen()*0.6), (int)(a.getColor().getBlue()*0.6));
                g.setColor(color);
                g.drawRect(10 + contador * 35, getHeight() - (int)(getHeight() * (nota / 10)), 35, (int)(getHeight() * (nota / 10)));
                contador++;
                sumaNotas += nota;
            }
            g.setColor(Ventana.colorTernario);
            Graphics2D g2 = (Graphics2D) g;
            if(notas.size() != 0) {
                g2.setStroke(new BasicStroke(2));
                g.drawLine(0, getHeight() - (int)(getHeight() * (sumaNotas / (contador *10))), getWidth(), getHeight() - (int)(getHeight() * (sumaNotas / (contador *10))));
            }
        }
    }
}
