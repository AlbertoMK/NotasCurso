import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAñadirExamen extends JFrame {
    public FrameAñadirExamen(Asignatura asignatura,Examen_Trabajo examen){
        Color colorFondo = new Color(255, 245, 209);
        if(examen!=null){
            setTitle("Editar examen / trabajo");
        }
        else  setTitle("Añadir examen / trabajo");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 600, 750);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(colorFondo);
        add(contentPanel);

        JLabel labelNombre = new JLabel("Inserte un nombre para el examen / trabajo");
        labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombre.setFont(new Font("",1,15));
        labelNombre.setBounds(getWidth()/2-200,20,400,30);
        contentPanel.add(labelNombre);

        JTextField textoNombre = new JTextField();
        textoNombre.setBounds(20,70,getWidth()-60,30);
        textoNombre.setHorizontalAlignment(JTextField.CENTER);
        contentPanel.add(textoNombre);

        ButtonGroup bg = new ButtonGroup();
        if(examen==null) {
            JRadioButton rExamen = new JRadioButton("Examen");
            JRadioButton rTrabajo = new JRadioButton("Trabajo");
            rExamen.setBounds(getWidth() / 2 - 150, 130, 100, 30);
            rTrabajo.setBounds(getWidth() / 2 + 50, 130, 100, 30);
            rExamen.setActionCommand("Examen");
            rTrabajo.setActionCommand("Trabajo");
            rExamen.setBackground(colorFondo);
            rTrabajo.setBackground(colorFondo);
            contentPanel.add(rExamen);
            contentPanel.add(rTrabajo);
            bg.add(rExamen);
            bg.add(rTrabajo);
        }

        JLabel labelMaximo = new JLabel("Introduce la nota máxima (10 por defecto)");
        labelMaximo.setBounds(getWidth()/2-150,180,300,30);
        contentPanel.add(labelMaximo);

        JTextField textoMaximo = new JTextField();
        textoMaximo.setBounds(getWidth()/2-15,230,30,30);
        textoMaximo.setHorizontalAlignment(JTextField.CENTER);
        contentPanel.add(textoMaximo);

        JLabel labelPeso = new JLabel("Introduce un peso sobre 100");
        labelPeso.setBounds(getWidth()/2-100,280,200,30);
        contentPanel.add(labelPeso);

        JTextField textoPeso = new JTextField();
        textoPeso.setBounds(getWidth()/2-15,330,30,30);
        textoPeso.setHorizontalAlignment(JTextField.CENTER);
        contentPanel.add(textoPeso);

        JLabel labelDia = new JLabel("Añade fecha de vencimiento");
        labelDia.setBounds(getWidth()/2-100,380,200,30);
        contentPanel.add(labelDia);

        JCheckBox checkSinFecha = new JCheckBox("No asignar fecha");
        contentPanel.add(checkSinFecha);
        checkSinFecha.setBounds(getWidth()/2-75,430,150,30);
        checkSinFecha.setBackground(colorFondo);

        JTextField dia = new JTextField();
        dia.setBounds(235,480,30,30);
        dia.setHorizontalAlignment(JTextField.CENTER);
        contentPanel.add(dia);

        JTextField mes = new JTextField();
        mes.setBounds(285,480,30,30);
        mes.setHorizontalAlignment(JTextField.CENTER);
        contentPanel.add(mes);

        JTextField año = new JTextField();
        año.setBounds(335,480,50,30);
        año.setHorizontalAlignment(JTextField.CENTER);
        contentPanel.add(año);

        JLabel dash1 = new JLabel("/");
        JLabel dash2 = new JLabel("/");
        dash1.setBounds(265,480,20,30);
        dash2.setBounds(315,480,20,30);
        dash1.setHorizontalAlignment(SwingConstants.CENTER);
        dash2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(dash1);
        contentPanel.add(dash2);

        checkSinFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkSinFecha.isSelected()){
                    dia.setVisible(false);
                    mes.setVisible(false);
                    año.setVisible(false);
                    dash1.setVisible(false);
                    dash2.setVisible(false);
                }
                else{
                    dia.setVisible(true);
                    mes.setVisible(true);
                    año.setVisible(true);
                    dash1.setVisible(true);
                    dash2.setVisible(true);
                }
            }
        });

        JLabel labelNota = new JLabel("Introduzca su nota");
        contentPanel.add(labelNota);
        labelNota.setBounds(getWidth()/2-100,530,200,30);

        JTextField textoNota = new JTextField();
        contentPanel.add(textoNota);
        textoNota.setBounds(getWidth()/2-15,580,30,30);

        if(examen==null){
            labelNota.setVisible(false);
            textoNota.setVisible(false);
        }
        if(examen!=null){
            textoNombre.setText(examen.getNombre());
            textoMaximo.setText(String.valueOf(examen.getMaximo()));
            textoPeso.setText(String.valueOf(examen.getPesoPorcentaje()));
            if(examen.getNota()!=-1)
                textoNota.setText(String.valueOf(examen.getNota()));

            if(examen.getAño()==-1){
                checkSinFecha.setSelected(true);
                dia.setVisible(false);
                mes.setVisible(false);
                año.setVisible(false);
                dash1.setVisible(false);
                dash2.setVisible(false);
            }
            else{
                dia.setText(String.valueOf(examen.getDia()));
                mes.setText(String.valueOf(examen.getMes()));
                año.setText(String.valueOf(examen.getAño()));
            }
        }

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBounds(getWidth()/2-50,630,100,50);
        contentPanel.add(aceptar);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double sumatorioPesos = 0;
                for (int i = 0; i < asignatura.getListaExamenes().size(); i++) {
                    sumatorioPesos += asignatura.getListaExamenes().get(i).getPesoPorcentaje();
                }
                for (int i = 0; i < asignatura.getListaTrabajos().size(); i++) {
                    sumatorioPesos += asignatura.getListaTrabajos().get(i).getPesoPorcentaje();
                }
                if(examen!=null) sumatorioPesos-=examen.getPesoPorcentaje();
                if(textoNombre.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Introduce un nombre");
                }
                else if(contieneComas(textoNombre.getText())){
                    JOptionPane.showMessageDialog(null,"Por motivos de formato, no incluyas comas en el nombre.");
                }
                else if(bg.getSelection()==null && examen==null){
                    JOptionPane.showMessageDialog(null,"Seleccione entre exámen o trabajo");
                }
                else if(textoPeso.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Introduce un peso");
                }
                else if(contieneComas(textoPeso.getText())){
                    JOptionPane.showMessageDialog(null,"Sustituye las comas por puntos en el peso");
                }
                else if((sumatorioPesos+Double.parseDouble(textoPeso.getText()))>100){
                    JOptionPane.showMessageDialog(null,"Los pesos de esta asignatura suman más de 100");
                }
                else if(!checkSinFecha.isSelected() && (dia.getText().equals("")||mes.getText().equals("")||año.getText().equals(""))){
                    JOptionPane.showMessageDialog(null,"Rellena todos los campos de la fecha");
                }
                else{
                    if(examen!=null){
                        if(contieneComas(textoNota.getText())){
                            JOptionPane.showMessageDialog(null,"Sustituye las comas por puntos en la nota");
                        }
                        else {
                            examen.setNombre(textoNombre.getText());
                            if(textoNota.getText().equals(""))
                                examen.setNota(-1);
                            else
                                examen.setNota(Double.parseDouble(textoNota.getText()));
                            examen.setPesoPorcentaje(Double.parseDouble(textoPeso.getText()));
                            examen.setMaximo(Double.parseDouble(textoMaximo.getText()));
                            int Dia;
                            int Mes;
                            int Año;
                            if (checkSinFecha.isSelected()) {
                                Dia = Mes = Año = -1;
                            } else {
                                Dia = Integer.parseInt(dia.getText());
                                Mes = Integer.parseInt(mes.getText());
                                Año = Integer.parseInt(año.getText());
                            }
                            examen.setDia(Dia);
                            examen.setMes(Mes);
                            examen.setAño(Año);
                            Curso.getInstance().guardarDatos();
                            Pantalla.getInstance().actualizarGraficosDashboard();
                            dispose();
                            JOptionPane.showMessageDialog(null, "Examen actualizado correctamente");
                        }
                    }
                    else {
                        int Dia;
                        int Mes;
                        int Año;
                        if (checkSinFecha.isSelected()) {
                            Dia = Mes = Año = -1;
                        } else {
                            Dia = Integer.parseInt(dia.getText());
                            Mes = Integer.parseInt(mes.getText());
                            Año = Integer.parseInt(año.getText());
                        }
                        double maximo;
                        if (textoMaximo.getText().equals("")) maximo = 10;
                        else maximo = Double.parseDouble(textoMaximo.getText());
                        Examen_Trabajo añadido;
                        if (bg.getSelection().getActionCommand().equals("Examen"))
                            añadido = asignatura.añadirExamen(textoNombre.getText(), maximo, Double.parseDouble(textoPeso.getText()), Dia, Mes, Año);
                        else
                            añadido = asignatura.añadirTrabajo(textoNombre.getText(), maximo, Double.parseDouble(textoPeso.getText()), Dia, Mes, Año);
                        Curso.getInstance().guardarDatos();
                        Pantalla.getInstance().actualizarGraficosDashboard();
                        dispose();
                        JOptionPane.showMessageDialog(null, "Examen / trabajo creado correctamente");
                    }
                }
            }
        });
        setVisible(true);
    }

    public static boolean contieneComas(String cadena){
        boolean resultado = false;
        for (int i = 0; i < cadena.length(); i++) {
            if(cadena.charAt(i)==',') resultado = true;
        }
        return resultado;
    }

}