import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAgregarAsignatura extends JFrame {
    public FrameAgregarAsignatura(Asignatura a,String accion){ //EDITAR, AÑADIR
        Color colorFondo = new Color(255, 245, 209);
        if(accion.equals("EDITAR")) setTitle("Editar asignatura");
        else setTitle("Agregar asignatura");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100,100,600,700);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(colorFondo);
        add(contentPanel);
        JLabel labelNombreAsignatura = new JLabel("Introduzca nombre de la asignatura");
        labelNombreAsignatura.setFont(new Font("",1,15));
        labelNombreAsignatura.setBounds(getWidth()/2-150,15,300,50);
        contentPanel.add(labelNombreAsignatura);

        JTextField nombreAsignatura = new JTextField();
        nombreAsignatura.setHorizontalAlignment(JTextField.CENTER);
        nombreAsignatura.setFont(new Font("",1,20));
        nombreAsignatura.setBounds(20,70,getWidth()-60,50);
        contentPanel.add(nombreAsignatura);

        JLabel labelNumeroSemestre = new JLabel("Número periodo (semestre/trimestre)");
        labelNumeroSemestre.setBounds(getWidth()/2-150,160,300,50);
        contentPanel.add(labelNumeroSemestre);

        JTextField numeroSemestre = new JTextField();
        numeroSemestre.setHorizontalAlignment(JTextField.CENTER);
        numeroSemestre.setBounds(getWidth()/2-30,220,30,30);
        contentPanel.add(numeroSemestre);

        JCheckBox checkBoxAnual = new JCheckBox("Asignatura anual");
        checkBoxAnual.setBackground(colorFondo);
        checkBoxAnual.setBounds(getWidth()/2-100,130,200,30);
        contentPanel.add(checkBoxAnual);
        checkBoxAnual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxAnual.isSelected()){
                    labelNumeroSemestre.setVisible(false);
                    numeroSemestre.setVisible(false);
                }
                else{
                    labelNumeroSemestre.setVisible(true);
                    numeroSemestre.setVisible(true);
                }
            }
        });

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.setBounds(getWidth()/2-200,260,400,250);
        contentPanel.add(colorChooser);

        if(accion.equals("EDITAR")) {
            nombreAsignatura.setText(a.getNombre());
            if (a.getSemestre() == -1) checkBoxAnual.setSelected(true);
            else numeroSemestre.setText(String.valueOf(a.getSemestre()));
            if(checkBoxAnual.isSelected()){
                labelNumeroSemestre.setVisible(false);
                numeroSemestre.setVisible(false);
            }
            colorChooser.setColor(a.getColor());
        }

        String textoBoton;
        if(accion.equals("EDITAR")) textoBoton = "Editar asignatura";
        else textoBoton = "Crear asignatura";
        JButton botonAceptar = new JButton(textoBoton);
        botonAceptar.setBounds(getWidth()/2-100,600,200,50);
        contentPanel.add(botonAceptar);
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nombreAsignatura.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Introduce un nombre para la asignatura");
                }
                else if(nombreAsignatura.getText().length()>21){
                    JOptionPane.showMessageDialog(null,"Introduce un nombre con menos de 21 caracteres para no tener problemas de formato");
                }
                else if(!checkBoxAnual.isSelected() && (numeroSemestre.getText().equals("")||numeroSemestre.getText().charAt(0)<'1'||numeroSemestre.getText().charAt(0)>'5')){
                    JOptionPane.showMessageDialog(null,"Introduce un valor entre 1 y 5 en el periodo");
                }
                else if(colorChooser.getColor().equals(Color.white)){
                    JOptionPane.showMessageDialog(null,"Elige un color para tu asignatura");
                }
                else {
                    if(accion.equals("EDITAR")){
                        int semestre;
                        if (checkBoxAnual.isSelected()) semestre = -1;
                        else semestre = Integer.parseInt(numeroSemestre.getText());
                        Curso curso = Curso.getInstance();
                        a.setNombre(nombreAsignatura.getText());
                        a.setSemestre(semestre);
                        a.setColor(colorChooser.getColor());
                        curso.guardarDatos();
                        Pantalla.getInstance().actualizarGraficosDashboard();
                        dispose();
                        JOptionPane.showMessageDialog(null, "Asignatura editada correctamente");
                    }
                    else {
                        int semestre;
                        if (checkBoxAnual.isSelected()) semestre = -1;
                        else semestre = Integer.parseInt(numeroSemestre.getText());
                        Curso curso = Curso.getInstance();
                        Asignatura a = curso.agregarAsignatura(nombreAsignatura.getText(), semestre, colorChooser.getColor());
                        Pantalla.getInstance().añadirAsignaturaAMenu(a);
                        curso.guardarDatos();
                        Pantalla.getInstance().actualizarGraficosDashboard();
                        dispose();
                        JOptionPane.showMessageDialog(null, "Asignatura creada correctamente");
                    }
                }
            }
        });
        setVisible(true);
    }
}