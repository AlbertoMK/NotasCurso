package Grafica.Graficos;

import Grafica.Panel;
import Grafica.Ventana;
import Logica.Asignatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class GraficaBarras extends Panel {

    private JPanel panelLeyenda;
    private PanelBarras panelBarras;
    private JLabel leyenda;

    public GraficaBarras(HashMap<Asignatura, Double> notas) {
        setSize(1080, 450);
        setBackground(Ventana.colorPrimario);
        panelBarras = new PanelBarras(notas);
        panelLeyenda = new JPanel();
        panelLeyenda.setLayout(null);
        panelLeyenda.setBounds(10, 0, 300, getHeight());
        panelBarras.setLocation(310, 0);

        //Panel leyenda
        leyenda = new JLabel("");
        leyenda.setFont(new Font("Arial Rounded MT Bold", 0, 19));
        leyenda.setVerticalAlignment(SwingConstants.CENTER);
        leyenda.setHorizontalAlignment(SwingConstants.CENTER);
        leyenda.setBounds(5, 0, panelLeyenda.getWidth() - 5, panelLeyenda.getHeight());
        StringBuilder textoLeyenda = new StringBuilder("<html>");
        for (Asignatura a : notas.keySet()) {
            textoLeyenda.append("<p style= 'color: rgb(" + a.getColor().getRed() + " " + a.getColor().getGreen() + " " + a.getColor().getBlue() + ");'>" + a.getNombre() + "</p>\n");
        }
        textoLeyenda.append("</body>");
        leyenda.setText(textoLeyenda.toString());
        panelLeyenda.add(leyenda);
        panelLeyenda.setBackground(Ventana.colorPrimario);
        panelBarras.setBackground(Ventana.colorPrimario);
        add(panelLeyenda);
        add(panelBarras);
    }

    public void actualizar(HashMap<Asignatura, Double> notas) {
        panelBarras.actualizar(notas);
        StringBuilder textoLeyenda = new StringBuilder("<html>");
        for (Asignatura a : notas.keySet()) {
            textoLeyenda.append("<p style= 'color: rgb(" + a.getColor().getRed() + " " + a.getColor().getGreen() + " " + a.getColor().getBlue() + ");'>" + a.getNombre() + "</p>\n");
        }
        textoLeyenda.append("</html>");
        leyenda.setText(textoLeyenda.toString());
    }

    class PanelBarras extends Panel {

        private HashMap<Asignatura, Double> notas;
        private HashMap<Asignatura, JLabel> labels;
        private HashMap<Integer, Asignatura> indices;

        public PanelBarras(HashMap<Asignatura, Double> notas) {
            setSize(770, 450);
            this.notas = notas;
            labels = new HashMap<>();
            indices = new HashMap<>();
            int i = 0;
            for (Asignatura a : notas.keySet()) {
                double nota = notas.get(a);
                if (nota != -1) {
                    indices.put(i, a);
                    JLabel label = new JLabel(String.valueOf(nota));
                    label.setVisible(false);
                    label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
                    label.setForeground(a.getColor());
                    label.setBackground(Color.green);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBounds(10 + i * 35, getHeight() - (int) (getHeight() * (nota / 10)) - 20, 35, 20);
                    add(label);
                    labels.put(a, label);
                    i++;
                }
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    for (Asignatura a : labels.keySet()) {
                        labels.get(a).setVisible(false);
                    }
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    for (Asignatura a : labels.keySet()) {
                        labels.get(a).setVisible(false);
                    }
                    int index = (e.getX() - 10) / 35;
                    if (indices.containsKey(index)) {
                        labels.get(indices.get(index)).setVisible(true);
                    }
                }
            });
        }

        public void actualizar(HashMap<Asignatura, Double> notas) {
            this.notas = notas;
            for (Asignatura a : labels.keySet()) {
                remove(labels.get(a));
            }
            indices.clear();
            labels.clear();
            int i = 0;
            for (Asignatura a : notas.keySet()) {
                double nota = notas.get(a);
                if (nota != -1) {
                    indices.put(i, a);
                    JLabel label = new JLabel(String.valueOf(nota));
                    label.setVisible(false);
                    label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
                    label.setForeground(a.getColor());
                    label.setBackground(Color.green);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBounds(10 + i * 35, getHeight() - (int) ((getHeight() - 30) * (nota / 10)) - 30, 35, 20);
                    add(label);
                    labels.put(a, label);
                    i++;
                }
            }
            this.repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int contador = 0;
            double sumaNotas = 0;
            for (Asignatura a : notas.keySet()) {
                g.setColor(a.getColor());
                double nota = notas.get(a);
                if (nota == -1) {
                    nota = 0;
                    contador--;
                }
                g.fillRect(10 + contador * 35, getHeight() - (int) ((getHeight() - 30) * (nota / 10)), 35, (int) ((getHeight() - 30) * (nota / 10)));
                Color color = new Color((int) (a.getColor().getRed() * 0.6), (int) (a.getColor().getGreen() * 0.6), (int) (a.getColor().getBlue() * 0.6));
                g.setColor(color);
                g.drawRect(10 + contador * 35, getHeight() - (int) ((getHeight() - 30) * (nota / 10)), 35, (int) ((getHeight() - 30) * (nota / 10)));
                contador++;
                sumaNotas += nota;
            }
            g.setColor(Ventana.colorTernario);
            Graphics2D g2 = (Graphics2D) g;
            if (notas.size() != 0) {
                g2.setStroke(new BasicStroke(2));
                g.drawLine(0, getHeight() - (int) (getHeight() * (sumaNotas / (contador * 10))), getWidth(), getHeight() - (int) (getHeight() * (sumaNotas / (contador * 10))));
            }
        }
    }
}
