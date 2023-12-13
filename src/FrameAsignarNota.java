import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAsignarNota extends JFrame {
    public FrameAsignarNota(Examen_Trabajo examen){
        Color colorFondo = new Color(255, 245, 209);
        setTitle("Asignar nota");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 400, 400);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(colorFondo);
        add(contentPanel);

        JLabel label = new JLabel("Inserte una nota:");
        label.setBounds(getWidth()/2-100,20,200,30);
        contentPanel.add(label);

        JTextField nota = new JTextField();
        nota.setBounds(getWidth()/2-15,80,30,30);
        contentPanel.add(nota);

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBounds(getWidth()/2-50,300,100,50);
        contentPanel.add(aceptar);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nota.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Introduzca una nota");
                }
                else if(Double.parseDouble(nota.getText())>examen.getMaximo()){
                    JOptionPane.showMessageDialog(null,"La nota no puede superar el valor máximo: "+examen.getMaximo());
                }
                else{
                    double valorNota = Double.parseDouble(nota.getText());
                    examen.setNota(valorNota);
                    Curso.getInstance().guardarDatos();
                    Pantalla.getInstance().actualizarGraficosDashboard();
                    dispose();
                    JOptionPane.showMessageDialog(null,"Nota añadida correctamente");
                }
            }
        });
        setVisible(true);
    }
}