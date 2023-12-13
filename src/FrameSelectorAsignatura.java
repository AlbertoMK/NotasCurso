import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSelectorAsignatura extends JFrame {
    public FrameSelectorAsignatura(String accion) { //Acciones: ELIMINAR, AÑADIREXAMEN, AÑADIRNOTA, ELIMINAREXAMEN, EDITARASIGNATURA, EDITAREXAMEN
        Color colorFondo = new Color(255, 245, 209);
        setTitle("Selector de asignatura");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 600, 700);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(colorFondo);
        add(contentPanel);

        JLabel label = new JLabel("Seleccione asignatura:");
        label.setBounds(getWidth() / 2 - 100, 20, 200, 50);
        label.setFont(new Font("", 1, 15));
        contentPanel.add(label);

        ButtonGroup bg = new ButtonGroup();
        Curso curso = Curso.getInstance();
        for (int i = 0; i < curso.getListaAsignaturas().size(); i++) {
            JRadioButton rb = new JRadioButton(curso.getListaAsignaturas().get(i).getNombre());
            rb.setForeground(curso.getListaAsignaturas().get(i).getColor());
            rb.setFont(new Font("",1,15));
            rb.setBackground(colorFondo);
            rb.setActionCommand(curso.getListaAsignaturas().get(i).getNombre());
            bg.add(rb);
            contentPanel.add(rb);
            rb.setBounds(10, 30 * i + 30, 500, 30);
        }

        JButton aceptar = new JButton("Aceptar");
        contentPanel.add(aceptar);
        aceptar.setBounds(getWidth() / 2 - 50, 600, 100, 50);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bg.getSelection()==null) {
                    JOptionPane.showMessageDialog(null, "Seleccione una asignatura");
                } else {
                    String seleccion = bg.getSelection().getActionCommand();
                    int index = 0;
                    for (int i = 0; i < curso.getListaAsignaturas().size(); i++) {
                        if (curso.getListaAsignaturas().get(i).getNombre().equals(seleccion))
                            index = i;
                    }
                    Asignatura seleccionada = curso.getListaAsignaturas().get(index);
                    if (accion.equals("ELIMINAR")) {
                        curso.eliminarAsignatura(seleccionada);
                        Pantalla.getInstance().eliminarAsignaturaMenu(seleccionada);
                        Curso.getInstance().guardarDatos();
                        Pantalla.getInstance().actualizarGraficosDashboard();
                        dispose();
                        JOptionPane.showMessageDialog(null, "Asignatura eliminada");
                    }
                    else if(accion.equals("AÑADIREXAMEN")){
                        new FrameAñadirExamen(seleccionada,null);
                        dispose();
                    }
                    else if(accion.equals("AÑADIRNOTA")){
                        new FrameSelectorExamen(seleccionada,"AÑADIRNOTA");
                        dispose();
                    }
                    else if(accion.equals("ELIMINAREXAMEN")){
                        new FrameSelectorExamen(seleccionada,"ELIMINAREXAMEN");
                        dispose();
                    }
                    else if(accion.equals("EDITARASIGNATURA")){
                        new FrameAgregarAsignatura(seleccionada,"EDITAR");
                        dispose();
                    }
                    else if(accion.equals("EDITAREXAMEN")){
                        new FrameSelectorExamen(seleccionada,"EDITAREXAMEN");
                        dispose();
                    }
                    else if(accion.equals("AÑADIRNOTAEXTRA")){
                        new FrameAñadirEliminarNotaExtra(seleccionada,true);
                        dispose();
                    }
                    else if(accion.equals("ELIMINARNOTAEXTRA")){
                        new FrameAñadirEliminarNotaExtra(seleccionada,false);
                        dispose();
                    }
                    curso.guardarDatos();
                }
            }
        });
        setVisible(true);
    }
}