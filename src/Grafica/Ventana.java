package Grafica;

import Grafica.Graficos.*;
import Logica.*;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Ventana extends javax.swing.JFrame {

    private final int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
    private String datoDialog;
    private ButtonGroup buttonGroupAsignaturas;
    public final static java.awt.Color colorPrimario = new Color(230, 240, 255);
    public final static java.awt.Color colorSecundario = new Color(165, 212, 255);
    public final static java.awt.Color colorTernario = new Color(0, 37, 128);
    public final static java.awt.Font font = new java.awt.Font("Arial Rounded MT Bold", Font.PLAIN, 24);
    public final static Font font2 = new Font("Arial Rounded MT Bold", Font.PLAIN, 15);
    private final static DefaultListCellRenderer.UIResource posicionCentral = new DefaultListCellRenderer.UIResource();
    private DefaultListModel<String> modeloListaPeriodos;
    private DefaultListModel<String> modeloListaAsignaturas;
    private DefaultTableModel modeloTablaEvaluables;
    private javax.swing.JButton aceptarDialog;
    private javax.swing.JButton aceptarEditorAsignatura;
    private javax.swing.JButton aceptarEvaluable;
    private javax.swing.JButton añadirEvaluable;
    private javax.swing.JTextField añoEvaluable;
    private javax.swing.JButton cancelarEvaluable;
    private javax.swing.JButton aceptarSelectorAsignatura;
    private JButton botonEliminarAsignatura;
    private JButton botonEditarAsignatura;
    private javax.swing.JCheckBox checkboxEsExamen;
    private javax.swing.JColorChooser colorEditorAsignatura;
    private javax.swing.JScrollPane contenedorListaAsignaturas;
    private javax.swing.JScrollPane contenedorListaPeriodosAsignatura;
    private javax.swing.JScrollPane contenedorListaPeriodos;
    private javax.swing.JScrollPane contenedorTablaEvaluables;
    private javax.swing.JLabel dash1;
    private javax.swing.JLabel dash2;
    private javax.swing.JTextField diaEvaluable;
    private javax.swing.JButton asignarAsignaturaPeriodo;
    private javax.swing.JMenu jMenuAcciones;
    private javax.swing.JMenu jMenuAsignaturas;
    private javax.swing.JMenu jMenuCurso;
    private javax.swing.JMenuItem jMenuIrCurso;
    private javax.swing.JLabel labelAsignaturaEvaluable;
    private javax.swing.JLabel labelAsignaturasPeriodo;
    private javax.swing.JLabel labelFechaEvaluable;
    private javax.swing.JLabel labelMaximoEvaluable;
    private javax.swing.JLabel labelNombreEvaluable;
    private javax.swing.JLabel labelNotaEvaluable;
    private javax.swing.JLabel labelNotaExtra;
    private javax.swing.JLabel labelPeriodoEvaluable;
    private javax.swing.JLabel labelPeriodosAsignatura;
    private javax.swing.JLabel labelPeriodosCurso;
    private javax.swing.JLabel labelPesoEvaluable;
    private javax.swing.JList<String> listaAsignaturas;
    private javax.swing.JList<String> listaPeriodos;
    private javax.swing.JMenuItem menuAgregarAsignatura;
    private javax.swing.JMenuItem menuAgregarPeriodo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuEliminarAsignatura;
    private javax.swing.JMenuItem menuEliminarPeriodo;
    private javax.swing.JTextField mesEvaluable;
    private javax.swing.JLabel nombreEditorAsignatura;
    private javax.swing.JDialog dialog;
    private javax.swing.JButton notaExtra;
    private javax.swing.JPanel panelAsignatura;
    private javax.swing.JPanel panelAsignaturaPeriodo;
    private javax.swing.JPanel panelAñadirAsignatura;
    private javax.swing.JPanel panelAñadirEvaluable;
    private javax.swing.JPanel panelCurso;
    private javax.swing.JPanel panelPeriodo;
    private javax.swing.JPanel panelSeleccionarAsignatura;
    private JPanel panelPeriodosAsignatura;
    private JPanel panelDialog;
    private JPanel panelRadioButtonsAsignaturas;
    private GraficoMedia graficaNotaMediaCurso;
    private GraficoConseguida graficaNotaConseguidaCurso;
    private GraficoBarraProgreso graficaProgresoCurso;
    private GraficaBarras graficaBarrasCurso;
    private GraficaBarras graficaBarrasPeriodo;
    private GraficoBarraProgreso graficaProgresoPeriodo;
    private GraficoMedia graficaNotaMediaPeriodo;
    private GraficoConseguida graficaNotaConseguidaPeriodo;
    private GraficoConseguida graficaNotaConseguidaAsignaturaPeriodo;
    private GraficoBarraProgreso graficaProgresoAsignaturaPeriodo;
    private GraficoMedia graficaNotaMediaAsignaturaPeriodo;
    private GraficoMedia graficaNotaMediaAsignatura;
    private GraficoConseguida graficaNotaConseguidaAsignatura;
    private javax.swing.JPanel panelPeriodosCurso;
    private javax.swing.JPanel panelBotonesAsignaturaPeriodo;
    private javax.swing.JPanel panelAsignaturasPeriodo;
    private javax.swing.JTable tablaEvaluables;
    private javax.swing.JTextField textoAsignaturaEvaluable;
    private javax.swing.JTextField textoMaximoEvaluable;
    private javax.swing.JTextField textoNombreEditorAsignatura;
    private javax.swing.JTextField textoNombreEvaluable;
    private javax.swing.JTextField textoNotaAsignarNota;
    private javax.swing.JTextField textoNotaEvaluable;
    private javax.swing.JTextField textoDialog;
    private javax.swing.JTextField textoPeriodoEvaluable;
    private javax.swing.JTextField textoPesoEvaluable;
    private JTextField textoCreditosAsignatura;
    private javax.swing.JLabel tituloAsignarNota;
    private javax.swing.JLabel tituloAsignatura;
    private javax.swing.JLabel tituloAsignaturaPeriodo;
    private javax.swing.JLabel periodoAsignatura;
    private javax.swing.JLabel tituloCrearEvaluable;
    private javax.swing.JLabel tituloCurso;
    private javax.swing.JLabel tituloEditorAsignatura;
    private javax.swing.JLabel tituloDialog;
    private javax.swing.JLabel tituloPeriodo;
    private javax.swing.JLabel tituloSeleccionarAsignatura;
    private DefaultListModel<String> modeloListaPeriodosAsignatura;
    private JList<String> listaPeriodosAsignatura;

    public Ventana() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        for (Asignatura a : C_Asignatura.getInstance().getListaAsignaturas()) {
            JMenuItem item = new JMenuItem(a.getNombre());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarPanelAsignatura(a);
                }
            });
            jMenuAsignaturas.add(item);
        }
        mostrarPanelCurso();
        setVisible(true);
    }

    private void initComponents() {

        dialog = new JDialog();
        tituloDialog = new JLabel();
        textoDialog = new JTextField();
        tituloAsignarNota = new JLabel();
        textoNotaAsignarNota = new JTextField();
        panelCurso = new JPanel();
        tituloCurso = new JLabel();
        labelPeriodosCurso = new JLabel();
        contenedorListaPeriodos = new JScrollPane();
        listaPeriodos = new JList<>();
        listaPeriodosAsignatura = new JList<>();
        panelAsignaturaPeriodo = new JPanel();
        tituloAsignaturaPeriodo = new JLabel();
        contenedorTablaEvaluables = new JScrollPane();
        tablaEvaluables = new JTable();
        labelNotaExtra = new JLabel();
        panelAñadirEvaluable = new JPanel();
        tituloCrearEvaluable = new JLabel();
        labelNombreEvaluable = new JLabel();
        checkboxEsExamen = new JCheckBox();
        labelAsignaturaEvaluable = new JLabel();
        labelPeriodoEvaluable = new JLabel();
        labelFechaEvaluable = new JLabel();
        labelNotaEvaluable = new JLabel();
        textoNombreEvaluable = new JTextField();
        textoAsignaturaEvaluable = new JTextField();
        textoPeriodoEvaluable = new JTextField();
        labelPesoEvaluable = new JLabel();
        labelMaximoEvaluable = new JLabel();
        textoPesoEvaluable = new JTextField();
        textoMaximoEvaluable = new JTextField();
        textoNotaEvaluable = new JTextField();
        diaEvaluable = new JTextField();
        mesEvaluable = new JTextField();
        añoEvaluable = new JTextField();
        dash1 = new JLabel();
        dash2 = new JLabel();
        panelPeriodo = new JPanel();
        contenedorListaAsignaturas = new JScrollPane();
        listaAsignaturas = new JList<>();
        tituloPeriodo = new JLabel();
        labelAsignaturasPeriodo = new JLabel();
        panelAsignatura = new JPanel();
        tituloAsignatura = new JLabel();
        labelPeriodosAsignatura = new JLabel();
        panelAñadirAsignatura = new JPanel();
        tituloEditorAsignatura = new JLabel();
        nombreEditorAsignatura = new JLabel();
        textoNombreEditorAsignatura = new JTextField();
        colorEditorAsignatura = new JColorChooser();
        panelSeleccionarAsignatura = new JPanel();
        tituloSeleccionarAsignatura = new JLabel();
        menuBar = new JMenuBar();
        jMenuCurso = new JMenu();
        jMenuIrCurso = new JMenuItem();
        jMenuAsignaturas = new JMenu();
        jMenuAcciones = new JMenu();
        menuAgregarPeriodo = new JMenuItem();
        menuEliminarPeriodo = new JMenuItem();
        menuAgregarAsignatura = new JMenuItem();
        menuEliminarAsignatura = new JMenuItem();

        dialog.setResizable(false);
        panelDialog = new JPanel();
        panelDialog.setLayout(null);
        panelDialog.setBackground(colorPrimario);
        dialog.setBounds(ancho / 2 - 250, alto / 2 - 150, 500, 300);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                datoDialog = null;
                dialog.dispose();
            }
        });
        dialog.add(panelDialog);

        tituloDialog.setHorizontalAlignment(SwingConstants.CENTER);
        tituloDialog.setForeground(colorTernario);
        tituloDialog.setFont(font);
        tituloDialog.setBounds(0, 10, dialog.getWidth(), 50);
        panelDialog.add(tituloDialog);

        textoDialog.setHorizontalAlignment(JTextField.CENTER);
        textoDialog.setBounds(dialog.getWidth() / 3, 80, dialog.getWidth() / 3, 30);
        textoDialog.setFont(font2);
        textoDialog.setBackground(colorSecundario);
        textoDialog.setForeground(colorTernario);
        panelDialog.add(textoDialog);

        aceptarDialog = new Boton("ACEPTAR");
        aceptarDialog.setBounds(dialog.getWidth() / 2 - 75, 170, 150, 30);
        panelDialog.add(aceptarDialog);
        aceptarDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aceptarDialog();
            }
        });
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new CardLayout());

        //------------------------------------------------- PANEL CURSO -----------------------------------------------------

        panelCurso.setBackground(colorPrimario);
        panelCurso.setLayout(null);

        graficaNotaMediaCurso = new GraficoMedia(8.5);
        graficaNotaMediaCurso.setLocation(ancho / 6 - graficaNotaMediaCurso.getWidth() / 2, 100);
        panelCurso.add(graficaNotaMediaCurso);

        graficaNotaConseguidaCurso = new GraficoConseguida(3.5, 2.6);
        graficaNotaConseguidaCurso.setLocation(5 * ancho / 6 - graficaNotaConseguidaCurso.getWidth() / 2, 100);
        panelCurso.add(graficaNotaConseguidaCurso);

        graficaProgresoCurso = new GraficoBarraProgreso(63);
        graficaProgresoCurso.setLocation(ancho / 2 - graficaProgresoCurso.getWidth() / 2, 225);
        panelCurso.add(graficaProgresoCurso);

        graficaBarrasCurso = new GraficaBarras(Curso.getInstance().getMediasAsignaturas());
        graficaBarrasCurso.setLocation(0, alto - graficaBarrasCurso.getHeight() - 50);
        panelCurso.add(graficaBarrasCurso);

        tituloCurso.setFont(font);
        tituloCurso.setForeground(colorTernario);
        tituloCurso.setHorizontalAlignment(SwingConstants.CENTER);
        tituloCurso.setText("CURSO");
        tituloCurso.setBounds(0, 30, ancho, 50);
        panelCurso.add(tituloCurso);

        panelPeriodosCurso = new JPanel();
        panelPeriodosCurso.setBackground(colorPrimario);
        panelPeriodosCurso.setLayout(null);
        panelPeriodosCurso.setBounds(1080, 2 * alto / 3, ancho - 1080, alto / 3);

        labelPeriodosCurso.setFont(font);
        labelPeriodosCurso.setBounds(0, 10, panelPeriodosCurso.getWidth(), 50);
        labelPeriodosCurso.setForeground(colorTernario);
        labelPeriodosCurso.setHorizontalAlignment(SwingConstants.CENTER);
        labelPeriodosCurso.setVerticalAlignment(SwingConstants.CENTER);
        labelPeriodosCurso.setText("PERIODOS");
        panelPeriodosCurso.add(labelPeriodosCurso);

        modeloListaPeriodos = new DefaultListModel<>() {
            String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }

            public void addElement(String periodo) {
                String[] copia = new String[strings.length + 1];
                for (int i = 0; i < strings.length; i++) {
                    copia[i] = strings[i];
                }
                copia[copia.length - 1] = periodo;
                strings = copia;
            }

            public void clear() {
                strings = new String[0];
            }

            public boolean removeElement(Object periodo) {
                String[] copia = new String[strings.length - 1];
                int ocupacion = 0;
                for (int i = 0; i < strings.length; i++) {
                    if (!strings[i].equals(periodo)) {
                        copia[ocupacion] = strings[i];
                        ocupacion++;
                    }
                }
                strings = copia;
                return false;
            }
        };
        listaPeriodos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!listaPeriodos.isSelectionEmpty()) {
                        Periodo p = Curso.getInstance().getPeriodoByName(listaPeriodos.getSelectedValue());
                        mostrarPanelPeriodo(p);
                    }
                }
            }
        });
        listaPeriodos.setModel(modeloListaPeriodos);
        listaPeriodos.setFont(new Font("Arial Rounded MT Bold", 0, 15));
        posicionCentral.setHorizontalAlignment(SwingConstants.CENTER);
        listaPeriodos.setCellRenderer(posicionCentral);
        listaPeriodos.setBackground(colorSecundario);
        contenedorListaPeriodos.setViewportView(listaPeriodos);
        contenedorListaPeriodos.setBounds(panelPeriodosCurso.getWidth() / 2 - 200, 90, 400, 150);
        panelPeriodosCurso.add(contenedorListaPeriodos);
        panelCurso.add(panelPeriodosCurso);
        getContentPane().add(panelCurso, "panelCurso");

        //----------------------------------- PANEL ASIGNATURA PERIODO ---------------------------------------------

        panelAsignaturaPeriodo.setLayout(null);
        panelAsignaturaPeriodo.setBackground(colorPrimario);
        tituloAsignaturaPeriodo.setFont(font);
        tituloAsignaturaPeriodo.setHorizontalAlignment(SwingConstants.CENTER);
        periodoAsignatura = new JLabel();
        periodoAsignatura.setBounds(0, 80, ancho, 50);
        periodoAsignatura.setForeground(colorTernario);
        periodoAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        panelAsignaturaPeriodo.add(periodoAsignatura);
        tituloAsignaturaPeriodo.setBounds(0, 30, ancho, 50);
        panelAsignaturaPeriodo.add(tituloAsignaturaPeriodo);

        graficaNotaMediaAsignaturaPeriodo = new GraficoMedia(7.5);
        graficaNotaMediaAsignaturaPeriodo.setLocation(ancho / 6 - graficaNotaMediaAsignaturaPeriodo.getWidth() / 2, 100);
        panelAsignaturaPeriodo.add(graficaNotaMediaAsignaturaPeriodo);

        graficaNotaConseguidaAsignaturaPeriodo = new GraficoConseguida(7.8, 1.5);
        graficaNotaConseguidaAsignaturaPeriodo.setLocation(5 * ancho / 6 - graficaNotaConseguidaAsignaturaPeriodo.getWidth() / 2, 100);
        panelAsignaturaPeriodo.add(graficaNotaConseguidaAsignaturaPeriodo);

        graficaProgresoAsignaturaPeriodo = new GraficoBarraProgreso(45);
        graficaProgresoAsignaturaPeriodo.setLocation(ancho / 2 - graficaProgresoAsignaturaPeriodo.getWidth() / 2, 225);
        panelAsignaturaPeriodo.add(graficaProgresoAsignaturaPeriodo);

        tablaEvaluables.setRowHeight(30);
        tablaEvaluables.getTableHeader().setReorderingAllowed(false);
        tablaEvaluables.setGridColor(colorTernario);
        tablaEvaluables.setBackground(colorSecundario);
        tablaEvaluables.setForeground(colorTernario);
        tablaEvaluables.setFont(new Font("Arial Rounded MT Bold", 0, 15));
        modeloTablaEvaluables = new DefaultTableModel(
                  new Object[][]{},
                  new String[]{
                            "Nombre", "Peso", "Máximo", "Fecha", "Nota"
                  }
        ) {
            Class[] types = new Class[]{
                      String.class, Double.class, Double.class, String.class, Double.class
            };

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tablaEvaluables.setModel(modeloTablaEvaluables);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaEvaluables.getColumnCount(); i++) {
            tablaEvaluables.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(colorTernario);
        headerRenderer.setForeground(colorPrimario);
        JTableHeader header = tablaEvaluables.getTableHeader();
        header.setPreferredSize(new Dimension(ancho, 24));
        for (int i = 0; i < tablaEvaluables.getColumnCount(); i++) {
            header.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        contenedorTablaEvaluables.setViewportView(tablaEvaluables);
        contenedorTablaEvaluables.getViewport().setBackground(colorPrimario);
        contenedorTablaEvaluables.setBounds(0, alto - (65 + 12 * 31), ancho, 24 + 12 * 31);
        panelAsignaturaPeriodo.add(contenedorTablaEvaluables);

        labelNotaExtra.setForeground(colorTernario);
        labelNotaExtra.setFont(new Font("Arial Rounded MT Bold", 0, 15));
        labelNotaExtra.setBounds(ancho / 2 - 75, 175, 150, 50);
        labelNotaExtra.setHorizontalAlignment(SwingConstants.CENTER);
        panelAsignaturaPeriodo.add(labelNotaExtra);

        panelBotonesAsignaturaPeriodo = new JPanel();
        panelBotonesAsignaturaPeriodo.setLayout(null);
        panelBotonesAsignaturaPeriodo.setBounds(ancho / 2 - 150, 300, 300, 400);
        panelBotonesAsignaturaPeriodo.setBackground(colorPrimario);

        añadirEvaluable = new Boton("Añadir evaluable");
        añadirEvaluable.setLocation(panelBotonesAsignaturaPeriodo.getWidth() / 2 - 75, 10);
        panelBotonesAsignaturaPeriodo.add(añadirEvaluable);

        notaExtra = new Boton("Editar nota extra");
        notaExtra.setLocation(panelBotonesAsignaturaPeriodo.getWidth() / 2 - 75, 50);
        panelBotonesAsignaturaPeriodo.add(notaExtra);

        panelAsignaturaPeriodo.add(panelBotonesAsignaturaPeriodo);
        getContentPane().add(panelAsignaturaPeriodo, "panelAsignaturaPeriodo");

        //----------------------------- PANEL CREAR EVALUABLE --------------------------

        panelAñadirEvaluable.setLayout(null);
        panelAñadirEvaluable.setBackground(colorPrimario);

        tituloCrearEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        tituloCrearEvaluable.setBounds(0, 30, ancho, 50);
        tituloCrearEvaluable.setFont(font);
        tituloCrearEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(tituloCrearEvaluable);

        labelNombreEvaluable.setText("NOMBRE: ");
        labelNombreEvaluable.setBounds(ancho / 3, 180, 150, 30);
        labelNombreEvaluable.setFont(font2);
        labelNombreEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(labelNombreEvaluable);

        textoNombreEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        textoNombreEvaluable.setBounds(ancho / 3 + 200, 180, 250, 30);
        textoNombreEvaluable.setFont(font2);
        textoNombreEvaluable.setForeground(colorTernario);
        textoNombreEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(textoNombreEvaluable);

        labelAsignaturaEvaluable.setText("ASIGNATURA: ");
        labelAsignaturaEvaluable.setBounds(ancho / 3, 250, 150, 30);
        labelAsignaturaEvaluable.setFont(font2);
        labelAsignaturaEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(labelAsignaturaEvaluable);

        textoAsignaturaEvaluable.setEditable(false);
        textoAsignaturaEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        textoAsignaturaEvaluable.setBounds(ancho / 3 + 200, 250, 250, 30);
        textoAsignaturaEvaluable.setFont(font2);
        textoAsignaturaEvaluable.setForeground(colorPrimario);
        textoAsignaturaEvaluable.setBackground(colorTernario);
        panelAñadirEvaluable.add(textoAsignaturaEvaluable);

        labelPeriodoEvaluable.setText("PERIODO: ");
        labelPeriodoEvaluable.setBounds(ancho / 3, 320, 150, 30);
        labelPeriodoEvaluable.setFont(font2);
        labelPeriodoEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(labelPeriodoEvaluable);

        textoPeriodoEvaluable.setEditable(false);
        textoPeriodoEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        textoPeriodoEvaluable.setBounds(ancho / 3 + 200, 320, 250, 30);
        textoPeriodoEvaluable.setFont(font2);
        textoPeriodoEvaluable.setForeground(colorPrimario);
        textoPeriodoEvaluable.setBackground(colorTernario);
        panelAñadirEvaluable.add(textoPeriodoEvaluable);

        checkboxEsExamen.setText("ES EXAMEN");
        checkboxEsExamen.setBackground(colorPrimario);
        checkboxEsExamen.setBounds(ancho / 3, 390, 150, 50);
        checkboxEsExamen.setFont(font2);
        checkboxEsExamen.setForeground(colorTernario);
        panelAñadirEvaluable.add(checkboxEsExamen);

        labelPesoEvaluable.setText("PESO: ");
        labelPesoEvaluable.setBounds(ancho / 3, 460, 150, 30);
        labelPesoEvaluable.setFont(font2);
        labelPesoEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(labelPesoEvaluable);

        textoPesoEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        textoPesoEvaluable.setBounds(ancho / 3 + 200, 460, 50, 30);
        textoPesoEvaluable.setFont(font2);
        textoPesoEvaluable.setForeground(colorTernario);
        textoPesoEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(textoPesoEvaluable);

        labelMaximoEvaluable.setText("MÁXIMO: ");
        labelMaximoEvaluable.setBounds(ancho / 3, 530, 150, 30);
        labelMaximoEvaluable.setFont(font2);
        labelMaximoEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(labelMaximoEvaluable);

        textoMaximoEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        textoMaximoEvaluable.setBounds(ancho / 3 + 200, 530, 50, 30);
        textoMaximoEvaluable.setFont(font2);
        textoMaximoEvaluable.setForeground(colorTernario);
        textoMaximoEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(textoMaximoEvaluable);

        labelFechaEvaluable.setText("FECHA (opc):");
        labelFechaEvaluable.setBounds(ancho / 3, 590, 150, 30);
        labelFechaEvaluable.setForeground(colorTernario);
        labelFechaEvaluable.setFont(font2);
        panelAñadirEvaluable.add(labelFechaEvaluable);

        diaEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        diaEvaluable.setBounds(ancho / 3 + 200, 590, 50, 30);
        diaEvaluable.setFont(font2);
        diaEvaluable.setForeground(colorTernario);
        diaEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(diaEvaluable);

        dash1.setBounds(ancho / 3 + 260, 590, 10, 30);
        dash1.setFont(font2);
        dash1.setForeground(colorTernario);
        dash1.setHorizontalAlignment(SwingConstants.CENTER);
        dash1.setText("/");
        panelAñadirEvaluable.add(dash1);

        mesEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        mesEvaluable.setBounds(ancho / 3 + 280, 590, 50, 30);
        mesEvaluable.setFont(font2);
        mesEvaluable.setForeground(colorTernario);
        mesEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(mesEvaluable);

        dash2.setBounds(ancho / 3 + 340, 590, 10, 30);
        dash2.setFont(font2);
        dash2.setForeground(colorTernario);
        dash2.setHorizontalAlignment(SwingConstants.CENTER);
        dash2.setText("/");
        panelAñadirEvaluable.add(dash2);

        añoEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        añoEvaluable.setBounds(ancho / 3 + 360, 590, 100, 30);
        añoEvaluable.setFont(font2);
        añoEvaluable.setForeground(colorTernario);
        añoEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(añoEvaluable);

        labelNotaEvaluable.setText("NOTA (opc): ");
        labelNotaEvaluable.setBounds(ancho / 3, 660, 150, 30);
        labelNotaEvaluable.setFont(font2);
        labelNotaEvaluable.setForeground(colorTernario);
        panelAñadirEvaluable.add(labelNotaEvaluable);

        textoNotaEvaluable.setHorizontalAlignment(SwingConstants.CENTER);
        textoNotaEvaluable.setBounds(ancho / 3 + 200, 660, 50, 30);
        textoNotaEvaluable.setFont(font2);
        textoNotaEvaluable.setForeground(colorTernario);
        textoNotaEvaluable.setBackground(colorSecundario);
        panelAñadirEvaluable.add(textoNotaEvaluable);

        cancelarEvaluable = new Boton("CANCELAR");
        cancelarEvaluable.setBounds(ancho / 3 - 75, 740, 150, 30);
        cancelarEvaluable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelCurso();
            }
        });
        panelAñadirEvaluable.add(cancelarEvaluable);

        aceptarEvaluable = new Boton("ACEPTAR");
        aceptarEvaluable.setBounds(ancho / 3 + 375, 740, 150, 30);
        panelAñadirEvaluable.add(aceptarEvaluable);

        getContentPane().add(panelAñadirEvaluable, "panelCrearEvaluable");

        //--------------------------------- PANEL PERIODO -------------------------------------

        panelPeriodo.setLayout(null);
        panelPeriodo.setBackground(colorPrimario);

        tituloPeriodo.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPeriodo.setFont(font);
        tituloPeriodo.setForeground(colorTernario);
        tituloPeriodo.setBounds(0, 30, ancho, 50);
        panelPeriodo.add(tituloPeriodo);

        graficaNotaMediaPeriodo = new GraficoMedia(0);
        graficaNotaMediaPeriodo.setLocation(ancho / 6 - graficaNotaMediaPeriodo.getWidth() / 2, 100);
        panelPeriodo.add(graficaNotaMediaPeriodo);

        graficaNotaConseguidaPeriodo = new GraficoConseguida(0, 0);
        graficaNotaConseguidaPeriodo.setLocation(5 * ancho / 6 - graficaNotaConseguidaPeriodo.getWidth() / 2, 100);
        panelPeriodo.add(graficaNotaConseguidaPeriodo);

        graficaProgresoPeriodo = new GraficoBarraProgreso(0);
        graficaProgresoPeriodo.setLocation(ancho / 2 - graficaProgresoPeriodo.getWidth() / 2, 225);
        panelPeriodo.add(graficaProgresoPeriodo);

        graficaBarrasPeriodo = new GraficaBarras(new HashMap<>());
        graficaBarrasPeriodo.setLocation(0, alto - graficaBarrasCurso.getHeight() - 50);
        panelPeriodo.add(graficaBarrasPeriodo);

        panelAsignaturasPeriodo = new JPanel();
        panelAsignaturasPeriodo.setLayout(null);
        panelAsignaturasPeriodo.setBackground(colorPrimario);
        panelAsignaturasPeriodo.setBounds(1080, 5 * alto / 8, ancho - 1080, 3 * alto / 8);

        labelAsignaturasPeriodo.setHorizontalAlignment(SwingConstants.CENTER);
        labelAsignaturasPeriodo.setText("ASIGNATURAS");
        labelAsignaturasPeriodo.setBounds(0, 10, panelAsignaturasPeriodo.getWidth() - 70, 30);
        labelAsignaturasPeriodo.setFont(font);
        labelAsignaturasPeriodo.setForeground(colorTernario);
        panelAsignaturasPeriodo.add(labelAsignaturasPeriodo);

        asignarAsignaturaPeriodo = new JButton();
        asignarAsignaturaPeriodo.setBackground(colorPrimario);
        asignarAsignaturaPeriodo.setBorder(null);
        ImageIcon icono = new ImageIcon("Icono añadir.png");
        asignarAsignaturaPeriodo.setIcon(icono);
        asignarAsignaturaPeriodo.setBounds(panelAsignaturasPeriodo.getWidth() - 60, 10, 50, 50);
        panelAsignaturasPeriodo.add(asignarAsignaturaPeriodo);

        modeloListaAsignaturas = new DefaultListModel<>() {
            String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }

            public void addElement(String periodo) {
                String[] copia = new String[strings.length + 1];
                for (int i = 0; i < strings.length; i++) {
                    copia[i] = strings[i];
                }
                copia[copia.length - 1] = periodo;
                strings = copia;
            }

            public void clear() {
                strings = new String[0];
            }

            public boolean removeElement(Object periodo) {
                String[] copia = new String[strings.length - 1];
                int ocupacion = 0;
                for (int i = 0; i < strings.length; i++) {
                    if (!strings[i].equals(periodo)) {
                        copia[ocupacion] = strings[i];
                        ocupacion++;
                    }
                }
                strings = copia;
                return false;
            }
        };
        listaAsignaturas.setModel(modeloListaAsignaturas);
        listaAsignaturas.setFont(font2);
        listaAsignaturas.setBackground(colorSecundario);
        listaAsignaturas.setCellRenderer(posicionCentral);

        contenedorListaAsignaturas.setViewportView(listaAsignaturas);
        contenedorListaAsignaturas.setBounds(0, 60, panelAsignaturasPeriodo.getWidth(), panelAsignaturasPeriodo.getHeight() - 60);
        panelAsignaturasPeriodo.add(contenedorListaAsignaturas);

        panelPeriodo.add(panelAsignaturasPeriodo);
        getContentPane().add(panelPeriodo, "panelPeriodo");

        // ------------------------------------ PANEL ASIGNATURA -------------------------------------

        panelAsignatura.setBackground(colorPrimario);
        panelAsignatura.setLayout(null);

        tituloAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        tituloAsignatura.setBounds(0, 50, ancho, 50);
        tituloAsignatura.setFont(font);
        tituloAsignatura.setForeground(colorTernario);
        panelAsignatura.add(tituloAsignatura);

        graficaNotaMediaAsignatura = new GraficoMedia(0);
        graficaNotaMediaAsignatura.setLocation(ancho / 6 - graficaNotaMediaAsignatura.getWidth() / 2, 100);
        panelAsignatura.add(graficaNotaMediaAsignatura);
        graficaNotaConseguidaAsignatura = new GraficoConseguida(1, 1);
        graficaNotaConseguidaAsignatura.setLocation(5 * ancho / 6 - graficaNotaConseguidaAsignatura.getWidth() / 2, 100);
        panelAsignatura.add(graficaNotaConseguidaAsignatura);

        //TODO mover lista periodos al centro, agregar botones de eliminar y editar asignatura

        botonEliminarAsignatura = new JButton();
        botonEliminarAsignatura.setBounds(ancho / 2 - 102, 250, 64, 64);
        botonEliminarAsignatura.setBackground(colorPrimario);
        botonEliminarAsignatura.setBorder(null);
        ImageIcon iconoPapelera = new ImageIcon("papelera.png");
        botonEliminarAsignatura.setIcon(iconoPapelera);
        panelAsignatura.add(botonEliminarAsignatura);

        botonEditarAsignatura = new JButton();
        botonEditarAsignatura.setBounds(ancho / 2 +2, 250, 64, 64);
        botonEditarAsignatura.setBackground(colorPrimario);
        botonEditarAsignatura.setBorder(null);
        ImageIcon iconoEditar = new ImageIcon("editar.png");
        botonEditarAsignatura.setIcon(iconoEditar);
        panelAsignatura.add(botonEditarAsignatura);

        panelPeriodosAsignatura = new JPanel();
        panelPeriodosAsignatura.setBackground(colorPrimario);
        panelPeriodosAsignatura.setLayout(null);
        panelPeriodosAsignatura.setBounds(ancho / 2, 2 * alto / 3, ancho / 2, alto / 3);

        modeloListaPeriodosAsignatura = new DefaultListModel<>() {
            String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }

            public void addElement(String periodo) {
                String[] copia = new String[strings.length + 1];
                for (int i = 0; i < strings.length; i++) {
                    copia[i] = strings[i];
                }
                copia[copia.length - 1] = periodo;
                strings = copia;
            }

            public void clear() {
                strings = new String[0];
            }

            public boolean removeElement(Object periodo) {
                String[] copia = new String[strings.length - 1];
                int ocupacion = 0;
                for (int i = 0; i < strings.length; i++) {
                    if (!strings[i].equals(periodo)) {
                        copia[ocupacion] = strings[i];
                        ocupacion++;
                    }
                }
                strings = copia;
                return false;
            }
        };
        listaPeriodosAsignatura.setModel(modeloListaPeriodosAsignatura);
        listaPeriodosAsignatura.setFont(font2);
        listaPeriodosAsignatura.setCellRenderer(posicionCentral);
        listaPeriodosAsignatura.setBackground(colorSecundario);

        contenedorListaPeriodosAsignatura = new JScrollPane();
        contenedorListaPeriodosAsignatura.setViewportView(listaPeriodosAsignatura);
        contenedorListaPeriodosAsignatura.setBounds(panelPeriodosAsignatura.getWidth() / 2 - 200, 90, 400, 150);
        panelPeriodosAsignatura.add(contenedorListaPeriodosAsignatura);

        labelPeriodosAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        labelPeriodosAsignatura.setForeground(colorTernario);
        labelPeriodosAsignatura.setFont(font);
        labelPeriodosAsignatura.setText("PERIODOS");
        labelPeriodosAsignatura.setBounds(0, 10, panelPeriodosAsignatura.getWidth(), 50);
        panelPeriodosAsignatura.add(labelPeriodosAsignatura);

        panelAsignatura.add(panelPeriodosAsignatura);
        getContentPane().add(panelAsignatura, "panelAsignatura");

        // -------------------------------- EDITOR ASIGNATURAS ----------------------------------

        panelAñadirAsignatura.setLayout(null);
        panelAñadirAsignatura.setBackground(colorPrimario);

        tituloEditorAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        tituloEditorAsignatura.setText("EDITOR ASIGNATURA");
        tituloEditorAsignatura.setForeground(colorTernario);
        tituloEditorAsignatura.setFont(font);
        tituloEditorAsignatura.setBounds(0, 30, ancho, 50);
        panelAñadirAsignatura.add(tituloEditorAsignatura);

        nombreEditorAsignatura.setText("NOMBRE:");
        nombreEditorAsignatura.setForeground(colorTernario);
        nombreEditorAsignatura.setFont(font2);
        nombreEditorAsignatura.setBounds(ancho / 3, 150, 200, 30);
        panelAñadirAsignatura.add(nombreEditorAsignatura);

        textoNombreEditorAsignatura.setBounds(ancho / 3 + 200, 150, 250, 30);
        textoNombreEditorAsignatura.setForeground(colorTernario);
        textoNombreEditorAsignatura.setBackground(colorSecundario);
        textoNombreEditorAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        textoNombreEditorAsignatura.setFont(font2);
        panelAñadirAsignatura.add(textoNombreEditorAsignatura);

        JLabel creditosEditorAsignatura = new JLabel("CRÉDITOS (opc):");
        creditosEditorAsignatura.setForeground(colorTernario);
        creditosEditorAsignatura.setFont(font2);
        creditosEditorAsignatura.setBounds(ancho / 3, 200, 150, 30);
        panelAñadirAsignatura.add(creditosEditorAsignatura);

        textoCreditosAsignatura = new JTextField();
        textoCreditosAsignatura.setBounds(ancho / 3 + 200, 200, 100, 30);
        textoCreditosAsignatura.setForeground(colorTernario);
        textoCreditosAsignatura.setBackground(colorSecundario);
        textoCreditosAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        textoCreditosAsignatura.setFont(font2);
        panelAñadirAsignatura.add(textoCreditosAsignatura);

        JLabel labelColorEditorAsignatura = new JLabel("COLOR:");
        labelColorEditorAsignatura.setForeground(colorTernario);
        labelColorEditorAsignatura.setFont(font2);
        labelColorEditorAsignatura.setBounds(ancho / 3, 250, 150, 30);
        panelAñadirAsignatura.add(labelColorEditorAsignatura);

        colorEditorAsignatura.setBounds(ancho / 3 + 200, 250, 600, 350);
        colorEditorAsignatura.getPreviewPanel().setBackground(colorPrimario);
        for (AbstractColorChooserPanel ccpanel : colorEditorAsignatura.getChooserPanels()) {
            ccpanel.setBackground(colorPrimario);
        }
        panelAñadirAsignatura.add(colorEditorAsignatura);

        aceptarEditorAsignatura = new Boton("ACEPTAR");
        aceptarEditorAsignatura.setBounds(ancho / 3 - 75, 700, 150, 30);
        panelAñadirAsignatura.add(aceptarEditorAsignatura);

        JButton cancelarEditorAsignatura = new Boton("CANCELAR");
        cancelarEditorAsignatura.setBounds(2 * ancho / 3 - 75, 700, 150, 30);
        cancelarEditorAsignatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mostrarPanelCurso();
            }
        });
        panelAñadirAsignatura.add(cancelarEditorAsignatura);

        getContentPane().add(panelAñadirAsignatura, "panelEditorAsignatura");

        // ----------------------------------- PANEL SELECTOR ASIGNATURA --------------------------------------

        panelSeleccionarAsignatura.setBackground(colorPrimario);
        panelSeleccionarAsignatura.setLayout(null);

        tituloSeleccionarAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        tituloSeleccionarAsignatura.setText("SELECCIONAR ASIGNATURA");
        tituloSeleccionarAsignatura.setBounds(0, 10, ancho, 50);
        tituloSeleccionarAsignatura.setForeground(colorTernario);
        tituloSeleccionarAsignatura.setFont(font);
        panelSeleccionarAsignatura.add(tituloSeleccionarAsignatura);

        panelRadioButtonsAsignaturas = new JPanel();
        panelRadioButtonsAsignaturas.setLayout(null);
        panelRadioButtonsAsignaturas.setBounds(0, 100, ancho, alto - 250);
        panelRadioButtonsAsignaturas.setBackground(colorPrimario);
        panelSeleccionarAsignatura.add(panelRadioButtonsAsignaturas);

        buttonGroupAsignaturas = new ButtonGroup();

        aceptarSelectorAsignatura = new Boton("ACEPTAR");
        aceptarSelectorAsignatura.setLocation(ancho / 2 - aceptarSelectorAsignatura.getWidth() / 2, alto - 150);
        panelSeleccionarAsignatura.add(aceptarSelectorAsignatura);

        getContentPane().add(panelSeleccionarAsignatura, "panelSelectorAsignatura");

        // -------------------------------- MENUS ------------------------------------

        jMenuCurso.setText("Curso");

        jMenuIrCurso.setText("Ir a curso");
        jMenuIrCurso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
        jMenuCurso.add(jMenuIrCurso);
        jMenuIrCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelCurso();
            }
        });

        menuBar.add(jMenuCurso);

        jMenuAsignaturas.setText("Asignaturas");
        menuBar.add(jMenuAsignaturas);

        jMenuAcciones.setText("Acciones");

        menuAgregarPeriodo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
        menuAgregarPeriodo.setText("Agregar periodo");
        menuAgregarPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPeriodoActionPerformed();
            }
        });
        jMenuAcciones.add(menuAgregarPeriodo);

        menuEliminarPeriodo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        menuEliminarPeriodo.setText("Eliminar Periodo");
        jMenuAcciones.add(menuEliminarPeriodo);

        menuAgregarAsignatura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        menuAgregarAsignatura.setText("Agregar asignatura");
        menuAgregarAsignatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarAsignatura();
            }
        });
        jMenuAcciones.add(menuAgregarAsignatura);

        menuEliminarAsignatura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        menuEliminarAsignatura.setText("Eliminar asignatura");
        jMenuAcciones.add(menuEliminarAsignatura);
        menuBar.add(jMenuAcciones);
        setJMenuBar(menuBar);
        pack();
    }

    private String mostrarDialog(String titulo) {
        textoDialog.setText("");
        tituloDialog.setText(titulo);
        dialog.setVisible(true);
        return datoDialog;
    }

    private void aceptarDialog() {
        datoDialog = textoDialog.getText();
        dialog.dispose();
    }


    private void agregarAsignatura() {
        textoNombreEditorAsignatura.setText("");
        textoCreditosAsignatura.setText("");
        colorEditorAsignatura.setColor(null);
        for (ActionListener actionListener : aceptarEditorAsignatura.getActionListeners()) {
            aceptarEditorAsignatura.removeActionListener(actionListener);
        }
        aceptarEditorAsignatura.addActionListener(new ActionListener() {
            AceptarListener lst = new AceptarListener() {
                @Override
                public void onAceptar(AceptarEvent evento) {
                    try {
                        C_Asignatura.getInstance().añadirAsignatura(evento.getAsignatura());
                        C_Asignatura.getInstance().guardar();
                        JOptionPane.showMessageDialog(null, "Asignatura añadida correctamente.");
                        JMenuItem item = new JMenuItem(evento.getAsignatura().getNombre());
                        item.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mostrarPanelAsignatura(evento.getAsignatura());
                            }
                        });
                        jMenuAsignaturas.add(item);
                    } catch (ExcepcionAsignatura ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR DE CREACIÓN DE ASIGNATURA", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        mostrarPanelCurso();
                    }
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                double creditos;
                try {
                    if (textoCreditosAsignatura.getText().equals(""))
                        creditos = -1;
                    else
                        creditos = Double.parseDouble(textoCreditosAsignatura.getText());
                    if (textoNombreEditorAsignatura.getText().length() < 3)
                        JOptionPane.showMessageDialog(null, "Ingrese un nombre de al menos 3 caracteres", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                    else {
                        Asignatura a = new Asignatura(textoNombreEditorAsignatura.getText(), colorEditorAsignatura.getColor(), creditos);
                        AceptarEvent evento = new AceptarEvent(this, a);
                        lst.onAceptar(evento);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de crédito no válido, ingrese un número real", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelEditorAsignatura");
    }

    private void agregarEvaluable(Asignatura asignatura, Periodo periodo) {
        tituloCrearEvaluable.setText("CREAR EVALUABLE");
        textoNombreEvaluable.setText("");
        checkboxEsExamen.setSelected(false);
        textoPesoEvaluable.setText("");
        diaEvaluable.setText("");
        mesEvaluable.setText("");
        añoEvaluable.setText("");
        textoNotaEvaluable.setText("");
        textoAsignaturaEvaluable.setText(asignatura.getNombre());
        textoPeriodoEvaluable.setText(periodo.getNombre());
        textoMaximoEvaluable.setText("10");
        for (ActionListener al : aceptarEvaluable.getActionListeners()) {
            aceptarEvaluable.removeActionListener(al);
        }
        aceptarEvaluable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textoNombreEvaluable.getText().length() < 3)
                        throw new RuntimeException("El nombre del evaluable debe tener al menos 3 caracteres");
                    double nota;
                    if (textoNotaEvaluable.getText().equals(""))
                        nota = -1;
                    else {
                        try {
                            nota = Double.parseDouble(textoNotaEvaluable.getText());
                        } catch (NumberFormatException ex) {
                            throw new RuntimeException("El formato del campo nota debe ser un número entero o decimal con .");
                        }
                    }
                    int dia, mes, año;
                    if (diaEvaluable.getText().equals("") || mesEvaluable.getText().equals("") || añoEvaluable.getText().equals("")) {
                        dia = mes = año = -1;
                    } else {
                        try {
                            dia = Integer.parseInt(diaEvaluable.getText());
                            mes = Integer.parseInt(mesEvaluable.getText());
                            año = Integer.parseInt(añoEvaluable.getText());
                        } catch (NumberFormatException ex) {
                            throw new RuntimeException("El formato de los campos de la fecha deben ser un número entero");
                        }
                    }
                    double peso = -1;
                    try {
                        peso = Double.parseDouble(textoPesoEvaluable.getText());
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException("El formato del campo peso debe ser un número entero o decimal con .");
                    }
                    double maximo = -1;
                    try {
                        maximo = Double.parseDouble(textoMaximoEvaluable.getText());
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException("El formato del campo maximo debe ser un número entero o decimal con .");
                    }
                    if (periodo.getSumaPesos(asignatura) + peso > 100)
                        throw new RuntimeException("Los pesos de esta asignatura para este periodo superan el 100%");
                    if (periodo.contieneEvaluableConNombreEnAsignatura(asignatura, textoNombreEvaluable.getText()))
                        throw new RuntimeException("El nombre de este evaluable ya está en uso en este periodo y asignatura");
                    Evaluable ev = new Evaluable(textoNombreEvaluable.getText(), nota, maximo, peso, dia, mes, año, checkboxEsExamen.isSelected());
                    C_Evaluable.getInstance().añadirEvaluable(ev);
                    periodo.añadirEvaluable(asignatura, ev);
                    C_Evaluable.getInstance().guardar();
                    Curso.getInstance().guardar();
                    mostrarPanelCurso();
                    JOptionPane.showMessageDialog(null, "Evaluable creado con éxito");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelCrearEvaluable");
    }

    private void modificarAsignatura(Asignatura asignatura){
        textoNombreEditorAsignatura.setText(asignatura.getNombre());
        if(asignatura.getCreditos() == -1)
            textoCreditosAsignatura.setText("");
        else
            textoCreditosAsignatura.setText(String.valueOf(asignatura.getCreditos()));
        colorEditorAsignatura.setColor(asignatura.getColor());
        for (ActionListener actionListener : aceptarEditorAsignatura.getActionListeners()) {
            aceptarEditorAsignatura.removeActionListener(actionListener);
        }
        aceptarEditorAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double creditos;
                try {
                    if (textoCreditosAsignatura.getText().equals(""))
                        creditos = -1;
                    else
                        creditos = Double.parseDouble(textoCreditosAsignatura.getText());
                    if (textoNombreEditorAsignatura.getText().length() < 3)
                        JOptionPane.showMessageDialog(null, "Ingrese un nombre de al menos 3 caracteres", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                    else {
                        boolean modificado = false;
                        int i = 0;
                        while(i < jMenuAsignaturas.getItemCount() && !modificado){
                            if(jMenuAsignaturas.getItem(i).getText().equals(asignatura.getNombre())) {
                                jMenuAsignaturas.getItem(i).setText(textoNombreEditorAsignatura.getText());
                                modificado = true;
                            }
                            i++;
                        }
                        asignatura.setNombre(textoNombreEditorAsignatura.getText());
                        asignatura.setCreditos(creditos);
                        asignatura.setColor(colorEditorAsignatura.getColor());
                        C_Asignatura.getInstance().guardar();
                        JOptionPane.showMessageDialog(null, "Asignatura editada correctamente");
                        mostrarPanelCurso();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de crédito no válido, ingrese un número real", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelEditorAsignatura");
    }

    private void modificarEvaluable(Evaluable evaluable, Asignatura asignatura, Periodo periodo) {
        tituloCrearEvaluable.setText("MODIFICAR EVALUABLE");
        textoNombreEvaluable.setText(evaluable.getNombre());
        checkboxEsExamen.setSelected(evaluable.getEsExamen());
        textoPesoEvaluable.setText(String.valueOf(evaluable.getPesoPorcentaje()));
        if (evaluable.getDia() == -1) {
            diaEvaluable.setText("");
            mesEvaluable.setText("");
            añoEvaluable.setText("");
        } else {
            diaEvaluable.setText(String.valueOf(evaluable.getDia()));
            mesEvaluable.setText(String.valueOf(evaluable.getMes()));
            añoEvaluable.setText(String.valueOf(evaluable.getAño()));
        }
        if (evaluable.notaAsignada())
            textoNotaEvaluable.setText(String.valueOf(evaluable.getNota()));
        else
            textoNotaEvaluable.setText("");
        textoAsignaturaEvaluable.setText(asignatura.getNombre());
        textoPeriodoEvaluable.setText(periodo.getNombre());
        textoMaximoEvaluable.setText(String.valueOf(evaluable.getMaximo()));
        for (ActionListener al : aceptarEvaluable.getActionListeners()) {
            aceptarEvaluable.removeActionListener(al);
        }
        aceptarEvaluable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textoNombreEvaluable.getText().length() < 3)
                        throw new RuntimeException("El nombre del evaluable debe tener al menos 3 caracteres");
                    double nota;
                    if (textoNotaEvaluable.getText().equals(""))
                        nota = -1;
                    else {
                        try {
                            nota = Double.parseDouble(textoNotaEvaluable.getText());
                        } catch (NumberFormatException ex) {
                            throw new RuntimeException("El formato del campo nota debe ser un número entero o decimal con .");
                        }
                    }
                    int dia, mes, año;
                    if (diaEvaluable.getText().equals("") || mesEvaluable.getText().equals("") || añoEvaluable.getText().equals("")) {
                        dia = mes = año = -1;
                    } else {
                        try {
                            dia = Integer.parseInt(diaEvaluable.getText());
                            mes = Integer.parseInt(mesEvaluable.getText());
                            año = Integer.parseInt(añoEvaluable.getText());
                        } catch (NumberFormatException ex) {
                            throw new RuntimeException("El formato de los campos de la fecha deben ser un número entero");
                        }
                    }
                    double peso = -1;
                    try {
                        peso = Double.parseDouble(textoPesoEvaluable.getText());
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException("El formato del campo peso debe ser un número entero o decimal con .");
                    }
                    double maximo = -1;
                    try {
                        maximo = Double.parseDouble(textoMaximoEvaluable.getText());
                    } catch (NumberFormatException ex) {
                        throw new RuntimeException("El formato del campo maximo debe ser un número entero o decimal con .");
                    }
                    if (periodo.getSumaPesos(asignatura) - evaluable.getPesoPorcentaje() + peso > 100)
                        throw new RuntimeException("Los pesos de esta asignatura para este periodo superan el 100%");
                    if (!evaluable.getNombre().equals(textoNombreEvaluable.getText()) && periodo.contieneEvaluableConNombreEnAsignatura(asignatura, textoNombreEvaluable.getText()))
                        throw new RuntimeException("El nombre de este evaluable ya está en uso en este periodo y asignatura");
                    evaluable.setNombre(textoNombreEvaluable.getText());
                    evaluable.setNota(nota);
                    evaluable.setMaximo(Double.parseDouble(textoMaximoEvaluable.getText()));
                    evaluable.setPesoPorcentaje(Double.parseDouble(textoPesoEvaluable.getText()));
                    evaluable.setDia(dia);
                    evaluable.setMes(mes);
                    evaluable.setAño(año);
                    evaluable.setEsExamen(checkboxEsExamen.isSelected());
                    C_Evaluable.getInstance().guardar();
                    mostrarPanelCurso();
                    JOptionPane.showMessageDialog(null, "Evaluable modificado con éxito");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelCrearEvaluable");
    }

    private void agregarPeriodoActionPerformed() {
        String nombrePeriodo = mostrarDialog("Nombre del periodo");
        if (nombrePeriodo != null) {
            if (nombrePeriodo.length() < 3)
                JOptionPane.showMessageDialog(null, "Ingrese un nombre de al menos 3 caracteres", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
            else if (nombrePeriodo.contains("_"))
                JOptionPane.showMessageDialog(null, "No introduzcas el símbolo '_' en el nombre del periodo", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
            else {
                Curso.getInstance().añadirPeriodo(nombrePeriodo);
                Curso.getInstance().guardar();
            }
        }
        mostrarPanelCurso();
    }

    private void mostrarPanelAsignaturaPeriodo(Asignatura asignatura, Periodo periodo) {
        labelNotaExtra.setText("Nota Extra: " + periodo.getNotaExtraAsignatura(asignatura));
        tituloAsignaturaPeriodo.setText(asignatura.getNombre());
        tituloAsignaturaPeriodo.setForeground(asignatura.getColor());
        periodoAsignatura.setText(periodo.getNombre());
        graficaNotaMediaAsignaturaPeriodo.actualizar(periodo.getNotaMediaAsignatura(asignatura));
        graficaNotaConseguidaAsignaturaPeriodo.actualizar(periodo.getNotaSeguraObtenidaAsignatura(asignatura), periodo.getNotaPerdidaAsignatura(asignatura));
        graficaProgresoAsignaturaPeriodo.actualizar(periodo.getPorcentajeProgresoAsignatura(asignatura));

        modeloTablaEvaluables.setRowCount(0);
        int contador = 0;
        for (Integer id : periodo.getEvaluables(asignatura)) {
            Evaluable ev = C_Evaluable.getInstance().getEvaluableById(id);
            String fecha;
            String nota;
            if (ev.getFecha().getDia() == -1)
                fecha = "-";
            else
                fecha = ev.getFecha().toString();
            if (ev.notaAsignada())
                nota = String.valueOf(ev.getNota());
            else
                nota = "-";
            modeloTablaEvaluables.addRow(new Object[]{ev.getNombre(), ev.getPesoPorcentaje(), ev.getMaximo(), fecha, nota});
            contador++;
        }

        /*
        Hago esta instrucción ya que ListSelectionModel no dispone de una función porpia para borrar los Listeners anteriores.
        Si no se borran los Listeners anteriores se duplican las acciones y hace cosas raras (repetidas)
        De esta manera cambio el modelo del JTable y así te deshaces de sus Listeners. Al setear otro nuevo, puedes agregarle un Listener ya que este no tendrá ninguno previamente
         */
        tablaEvaluables.setSelectionModel(new DefaultListSelectionModel());
        ListSelectionModel lsm = tablaEvaluables.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tablaEvaluables.getSelectedRow();
                    if (selectedRow != -1) {
                        if (tablaEvaluables.getValueAt(selectedRow, 0) != null)
                            modificarEvaluable(periodo.getEvaluable(asignatura, tablaEvaluables.getValueAt(selectedRow, 0).toString()), asignatura, periodo);
                    }
                }
            }
        });

        for (int i = contador; i < 12; i++) {
            modeloTablaEvaluables.addRow(new Object[]{null, null, null, null, null});
        }

        for (ActionListener al : añadirEvaluable.getActionListeners()) {
            añadirEvaluable.removeActionListener(al);
        }

        añadirEvaluable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEvaluable(asignatura, periodo);
            }
        });

        for (ActionListener al : notaExtra.getActionListeners()) {
            notaExtra.removeActionListener(al);
        }
        notaExtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorNotaExtra = mostrarDialog("Asignar nota extra");
                double valorNotaExtraNumerico = -1;
                try {
                    valorNotaExtraNumerico = Double.parseDouble(valorNotaExtra);
                    if (valorNotaExtraNumerico < 0)
                        JOptionPane.showMessageDialog(null, "La nota extra debe ser un número mayor o igual a 0", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                    else {
                        periodo.setNotaExtra(asignatura, valorNotaExtraNumerico);
                        Curso.getInstance().guardar();
                    }
                    mostrarPanelCurso();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La nota extra debe ser un número entero o decimal con .", "ERROR DE INPUT", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelAsignaturaPeriodo");
    }

    private void mostrarPanelSelectorAsignatura(Periodo periodo) {
        int contador = 0;
        for (Component c : panelRadioButtonsAsignaturas.getComponents()) {
            panelRadioButtonsAsignaturas.remove(c);
            buttonGroupAsignaturas.remove((JRadioButton) c);
        }
        for (Asignatura a : C_Asignatura.getInstance().getListaAsignaturas()) {
            JRadioButton rb = new JRadioButton(a.getNombre());
            rb.setActionCommand(a.getNombre());
            rb.setBackground(colorPrimario);
            rb.setFont(font);
            rb.setForeground(a.getColor());
            rb.setHorizontalAlignment(SwingConstants.CENTER);
            rb.setBounds(panelRadioButtonsAsignaturas.getWidth() / 2 - 200, 10 + 30 * contador, 400, 30);
            panelRadioButtonsAsignaturas.add(rb);
            buttonGroupAsignaturas.add(rb);
            contador++;
        }
        for (ActionListener ac : aceptarSelectorAsignatura.getActionListeners()) {
            aceptarSelectorAsignatura.removeActionListener(ac);
        }
        aceptarSelectorAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonGroupAsignaturas.getSelection() == null) {
                    mostrarPanelCurso();
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna asignatura", "ERROR DE SELECCIÓN DE ASIGNATURA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Asignatura a = C_Asignatura.getInstance().getAsignaturaByName(buttonGroupAsignaturas.getSelection().getActionCommand());
                    if (periodo.getAsignaturas().contains(a.getId())) {
                        mostrarPanelCurso();
                        JOptionPane.showMessageDialog(null, "Esta asignatura ya forma parte del periodo", "ERROR DE SELECCIÓN DE ASIGNATURA", JOptionPane.ERROR_MESSAGE);
                    } else {
                        periodo.añadirAsignatura(a);
                        Curso.getInstance().guardar();
                        mostrarPanelCurso();
                        JOptionPane.showMessageDialog(null, "Asignatura añadida correctamente");
                    }
                }
            }
        });
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelSelectorAsignatura");
    }

    private void mostrarPanelPeriodo(Periodo periodo) {
        for (ActionListener ac : asignarAsignaturaPeriodo.getActionListeners()) {
            asignarAsignaturaPeriodo.removeActionListener(ac);
        }
        asignarAsignaturaPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelSelectorAsignatura(periodo);
            }
        });
        for (ListSelectionListener ls : listaAsignaturas.getListSelectionListeners()) {
            listaAsignaturas.removeListSelectionListener(ls);
        }
        listaAsignaturas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!listaAsignaturas.isSelectionEmpty()) {
                        Asignatura asignatura = C_Asignatura.getInstance().getAsignaturaByName(listaAsignaturas.getSelectedValue());
                        mostrarPanelAsignaturaPeriodo(asignatura, periodo);
                    }
                }
            }
        });
        listaAsignaturas.clearSelection();
        tituloPeriodo.setText(periodo.getNombre());
        graficaBarrasPeriodo.actualizar(periodo.getMediasAsignaturas());
        graficaProgresoPeriodo.actualizar(periodo.getPorcentajeProgresoPeriodo());
        graficaNotaMediaPeriodo.actualizar(periodo.getNotaMediaPeriodo());
        graficaNotaConseguidaPeriodo.actualizar(periodo.getNotaSeguraObtenidaPeriodo(), periodo.getNotaPerdidaPeriodo());
        modeloListaAsignaturas.clear();
        for (Integer a : periodo.getAsignaturas()) {
            modeloListaAsignaturas.addElement(C_Asignatura.getInstance().getAsignaturaById(a).getNombre());
        }
        listaAsignaturas.updateUI();
        listaAsignaturas.setCellRenderer(posicionCentral);
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelPeriodo");
    }

    private void mostrarPanelAsignatura(Asignatura asignatura) {
        Curso c = Curso.getInstance();
        tituloAsignatura.setForeground(asignatura.getColor());
        tituloAsignatura.setText(asignatura.getNombre());

        graficaNotaMediaAsignatura.actualizar(c.getNotaMediaAsignatura(asignatura));
        graficaNotaConseguidaAsignatura.actualizar(c.getNotaSeguraObtenidaAsignatura(asignatura), c.getNotaPerdidaAsignatura(asignatura));

        for (ActionListener al : botonEliminarAsignatura.getActionListeners()) {
            botonEliminarAsignatura.removeActionListener(al);
        }

        botonEliminarAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curso.getInstance().eliminarAsignatura(asignatura);
                C_Asignatura.getInstance().eliminarAsignatura(asignatura);
                Curso.getInstance().guardar();
                C_Asignatura.getInstance().guardar();
                C_Evaluable.getInstance().guardar();
                boolean eliminado = false;
                int i = 0;
                while(i < jMenuAsignaturas.getItemCount() && !eliminado){
                    if(jMenuAsignaturas.getItem(i).getText().equals(asignatura.getNombre())) {
                        jMenuAsignaturas.remove(jMenuAsignaturas.getItem(i));
                        eliminado = true;
                    }
                    i++;
                }
                mostrarPanelCurso();
                JOptionPane.showMessageDialog(null, "Asignatura borrada correctamente.");
            }
        });

        for (ActionListener al : botonEditarAsignatura.getActionListeners()) {
            botonEditarAsignatura.removeActionListener(al);
        }

        botonEditarAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarAsignatura(asignatura);
            }
        });

        listaPeriodosAsignatura.clearSelection();
        modeloListaPeriodosAsignatura.clear();
        for (Periodo p : Curso.getInstance().getPeriodos()) {
            if (p.containsAsignatura(asignatura)) {
                modeloListaPeriodosAsignatura.addElement(p.getNombre());
            }
        }

        for (ListSelectionListener al : listaPeriodosAsignatura.getListSelectionListeners()) {
            listaPeriodosAsignatura.removeListSelectionListener(al);
        }

        listaPeriodosAsignatura.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!listaPeriodosAsignatura.isSelectionEmpty()) {
                        Periodo p = Curso.getInstance().getPeriodoByName(listaPeriodosAsignatura.getSelectedValue());
                        mostrarPanelAsignaturaPeriodo(asignatura, p);
                    }
                }
            }
        });

        listaPeriodosAsignatura.updateUI();
        listaPeriodosAsignatura.setCellRenderer(posicionCentral);
        panelAsignatura.repaint();
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelAsignatura");
    }

    private void mostrarPanelCurso() {
        Curso c = Curso.getInstance();
        graficaNotaMediaCurso.actualizar(c.getNotaMediaCurso());

        double progreso;
        if (c.getProgreso() == -1)
            progreso = 0;
        else
            progreso = c.getProgreso();
        graficaProgresoCurso.actualizar(progreso);

        graficaNotaConseguidaCurso.actualizar(c.getNotaSeguraObtenidaCurso(), c.getNotaPerdidaCurso());

        graficaBarrasCurso.actualizar(c.getMediasAsignaturas());

        listaPeriodos.clearSelection();
        modeloListaPeriodos.clear();
        for (Periodo p : c.getPeriodos()) {
            modeloListaPeriodos.addElement(p.getNombre());
        }
        listaPeriodos.updateUI();
        listaPeriodos.setCellRenderer(posicionCentral);
        panelCurso.repaint();
        ((CardLayout) (getContentPane().getLayout())).show(getContentPane(), "panelCurso");
    }
}
