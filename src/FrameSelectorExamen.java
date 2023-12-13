import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSelectorExamen extends JFrame {
    public FrameSelectorExamen(Asignatura asignatura,String accion) { //EDITAREXAMEN,
        Color colorFondo = new Color(255, 245, 209);
        setTitle("Selector de examen / trabajo");
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 600, 700);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(colorFondo);
        add(contentPanel);

        JLabel label = new JLabel("Seleccione examen / trabajo:");
        label.setBounds(getWidth() / 2 - 100, 20, 200, 30);
        label.setFont(new Font("", 1, 15));
        contentPanel.add(label);

        JLabel labelExamenes = new JLabel("Examenes:");
        labelExamenes.setBounds(getWidth() / 2 - 100, 100, 200, 30);
        labelExamenes.setFont(new Font("", 1, 15));
        contentPanel.add(labelExamenes);

        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < asignatura.getListaExamenes().size(); i++) {
            Examen_Trabajo e = asignatura.getListaExamenes().get(i);
            if(accion.equals("EDITAREXAMEN") || accion.equals("ELIMINAREXAMEN") || !e.notaAsignada()) {
                JRadioButton rb = new JRadioButton(e.getNombre());
                rb.setFont(new Font("", 1, 15));
                rb.setBackground(colorFondo);
                rb.setActionCommand(e.getNombre());
                bg.add(rb);
                contentPanel.add(rb);
                rb.setBounds(10, 25 * i + 160, 500, 30);
            }
        }

        JLabel labelTrabajos = new JLabel("Trabajos:");
        labelTrabajos.setBounds(getWidth() / 2 - 100, 190+55*bg.getButtonCount(), 200, 30);
        labelTrabajos.setFont(new Font("", 1, 15));
        contentPanel.add(labelTrabajos);

        for (int i = 0; i < asignatura.getListaTrabajos().size(); i++) {
            Examen_Trabajo e = asignatura.getListaTrabajos().get(i);
            if(accion.equals("EDITAREXAMEN") || accion.equals("ELIMINAREXAMEN") || !e.notaAsignada()) {
                JRadioButton rb = new JRadioButton(e.getNombre());
                rb.setFont(new Font("", 1, 15));
                rb.setBackground(colorFondo);
                rb.setActionCommand(e.getNombre());
                bg.add(rb);
                contentPanel.add(rb);
                rb.setBounds(10, 25 * i + labelTrabajos.getY()+30, 500, 30);
            }
        }
        JButton aceptar = new JButton("Aceptar");
        contentPanel.add(aceptar);
        aceptar.setBounds(getWidth() / 2 - 50, 600, 100, 50);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bg.getSelection()==null){
                    JOptionPane.showMessageDialog(null,"Seleccione una opción");
                }
                else {
                    Examen_Trabajo seleccionado;
                    String seleccion = bg.getSelection().getActionCommand();
                    int index = -1;
                    for (int i = 0; i < asignatura.getListaExamenes().size(); i++) {
                        if (asignatura.getListaExamenes().get(i).getNombre().equals(seleccion))
                            index = i;
                    }
                    if(index==-1){
                        for (int i = 0; i < asignatura.getListaTrabajos().size(); i++) {
                            if(asignatura.getListaTrabajos().get(i).getNombre().equals(seleccion))
                                index = i;
                        }
                        seleccionado = asignatura.getListaTrabajos().get(index);
                    }
                    else{
                        seleccionado = asignatura.getListaExamenes().get(index);
                    }
                    if(accion.equals("AÑADIRNOTA")) {
                        new FrameAsignarNota(seleccionado);
                        dispose();
                    }
                    else if(accion.equals("ELIMINAREXAMEN")){
                        asignatura.eliminarExamenOTrabajo(seleccionado);
                        Curso.getInstance().guardarDatos();
                        dispose();
                        JOptionPane.showMessageDialog(null,"Examen eliminado correctamente");
                    }
                    else if(accion.equals("EDITAREXAMEN")){
                        new FrameAñadirExamen(asignatura,seleccionado);
                        dispose();
                    }
                }
            }
        });

        setVisible(true);
    }
}