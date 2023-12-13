import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pantalla extends JFrame {

    private JPanel contentPanel, panelDashboard, panelDashboardLeft, panelDashboardRight;
    private PanelAsignatura panelAsignaturas;
    private JMenuBar barraMenu;
    private JMenu dashBoardMenu,asignaturaMenu;
    private JLabel labelNotaObtenida, labelMediaCurso, labelMaximaNotaPosible, labelAsignaturasGrafico, labelProgreso;
    private JLabel primerExamen_Nombre, primerExamen_Asignatura, primerExamen_Fecha,
              segundoExamen_Nombre, segundoExamen_Asignatura, segundoExamen_Fecha, tercerExamen_Nombre, tercerExamen_Asignatura, tercerExamen_Fecha;
    private static Pantalla instance = null;
    
    public static Pantalla getInstance(){
        if(instance == null)
            instance = new Pantalla();
        return instance;
    }
    
    private Pantalla(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        getContentPane().add(contentPanel);
        panelDashboard = new JPanel();
        panelDashboardLeft = new dibujosFondo();
        panelDashboardRight = new JPanel();

        contentPanel.add(panelDashboard,"panelDashboard");

        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        dashBoardMenu = new JMenu("Dashboard");
        barraMenu.add(dashBoardMenu);
        JMenuItem i1 = new JMenuItem("Ir a dashboard");
        dashBoardMenu.add(i1);
        asignaturaMenu = new JMenu("Asignatura");
        barraMenu.add(asignaturaMenu);

        Curso curso = Curso.getInstance();
        for (int i = 0; i < curso.getListaAsignaturas().size(); i++) {
            Asignatura a = curso.getListaAsignaturas().get(i);
            JMenuItem j = new JMenuItem(a.getNombre());
            asignaturaMenu.add(j);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelAsignaturas.setAsignatura(a);
                    cambiadorPaneles(2);
                }
            });
        }

        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiadorPaneles(1);
            }
        });

        initPantalla();
    }

    private void initPanelDashboard(){
        Color colorFondo = new Color(255, 245, 209);
        Font h1 = new Font("font1",1,20);
        panelDashboardLeft.setBackground(colorFondo);
        panelDashboardRight.setBackground(colorFondo);
        panelDashboardRight.setLayout(null);
        panelDashboard.setLayout(null);
        panelDashboardLeft.setBounds(0,0,getWidth()/2,getHeight());
        panelDashboardLeft.setLayout(null);
        panelDashboard.add(panelDashboardLeft);
        panelDashboardRight.setBounds(getWidth()/2,0,getWidth()/2,getHeight());
        panelDashboard.add(panelDashboardRight);

        JLabel labelNotaYaObtenida = new JLabel("NOTA YA ASEGURADA");
        panelDashboardLeft.add(labelNotaYaObtenida);
        labelNotaYaObtenida.setFont(h1);
        labelNotaYaObtenida.setBounds(panelDashboardLeft.getWidth()/4-150,50,300,50);

        DecimalFormat df = new DecimalFormat("#.#");
        double notaObtenida = Curso.getInstance().getNotaSeguraObtenida();
        if(notaObtenida==-1) notaObtenida = 0;

        double notaMaxima = 10- Curso.getInstance().getNotaPerdida();
        labelMaximaNotaPosible = new JLabel("Max: "+df.format(notaMaxima));
        panelDashboardLeft.add(labelMaximaNotaPosible);
        labelMaximaNotaPosible.setForeground(new Color(136, 136, 136));
        labelMaximaNotaPosible.setBounds(panelDashboardLeft.getWidth()/4-50,280,100,30);

        labelNotaObtenida = new JLabel(df.format(notaObtenida));
        panelDashboardLeft.add(labelNotaObtenida);
        labelNotaObtenida.setBounds(panelDashboardLeft.getWidth()/4-100,130,140,140);
        labelNotaObtenida.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaObtenida.setFont(new Font("",1,30));

        JLabel labelNotaMediaCurso = new JLabel("NOTA MEDIA CURSO");
        panelDashboardLeft.add(labelNotaMediaCurso);
        labelNotaMediaCurso.setFont(h1);
        labelNotaMediaCurso.setBounds(3*panelDashboardLeft.getWidth()/4-130,50,300,50);

        double media = Curso.getInstance().getNotaMediaCurso();
        if(media==-1) media = 0;
        labelMediaCurso = new JLabel(df.format(media));
        labelMediaCurso.setBounds(3*panelDashboardLeft.getWidth()/4-100,130,140,140);
        labelMediaCurso.setHorizontalAlignment(SwingConstants.CENTER);
        labelMediaCurso.setFont(new Font("",1,30));
        panelDashboardLeft.add(labelMediaCurso);

        labelProgreso = new JLabel("");
        panelDashboardLeft.add(labelProgreso);
        labelProgreso.setBounds(panelDashboardLeft.getWidth()/2-200,10,400,50);
        labelProgreso.setHorizontalAlignment(SwingConstants.CENTER);
        labelProgreso.setVerticalAlignment(SwingConstants.CENTER);
        Curso curso = Curso.getInstance();
        double porcentaje;
        if(curso.getListaAsignaturas().size()>0)
            porcentaje = 100*curso.sumatorioPesosConNota()/(curso.getListaAsignaturas().size()*100);
        else porcentaje = 0;
        labelProgreso.setText(df.format(porcentaje)+"%");
        labelProgreso.setFont(h1);

        JPanel panelProximosExamenes = new JPanel();
        panelDashboardLeft.add(panelProximosExamenes);
        panelProximosExamenes.setLayout(null);
        panelProximosExamenes.setBackground(colorFondo);
        panelProximosExamenes.setBounds(0,350, panelDashboardLeft.getWidth()-5,180);

        JLabel labelProximosExamenes = new JLabel("PRÓXIMOS EXÁMENES / TRABAJOS");
        panelProximosExamenes.add(labelProximosExamenes);
        labelProximosExamenes.setFont(h1);
        labelProximosExamenes.setBounds(panelDashboardLeft.getWidth()/2-200,0,400,30);

        primerExamen_Nombre = new JLabel();
        panelProximosExamenes.add(primerExamen_Nombre);
        primerExamen_Nombre.setBounds(0,50, panelDashboardLeft.getWidth()/3,30);
        primerExamen_Nombre.setFont(new Font("",1,15));
        primerExamen_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
        primerExamen_Asignatura = new JLabel();
        panelProximosExamenes.add(primerExamen_Asignatura);
        primerExamen_Asignatura.setBounds(0,80, panelDashboardLeft.getWidth()/3,30);
        primerExamen_Asignatura.setHorizontalAlignment(SwingConstants.CENTER);
        primerExamen_Fecha = new JLabel();
        panelProximosExamenes.add(primerExamen_Fecha);
        primerExamen_Fecha.setBounds(0,120, panelDashboardLeft.getWidth()/3,30);
        primerExamen_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
        segundoExamen_Nombre = new JLabel();
        panelProximosExamenes.add(segundoExamen_Nombre);
        segundoExamen_Nombre.setBounds(panelDashboardLeft.getWidth()/3,50, panelDashboardLeft.getWidth()/3,30);
        segundoExamen_Nombre.setFont(new Font("",1,15));
        segundoExamen_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
        segundoExamen_Asignatura = new JLabel();
        panelProximosExamenes.add(segundoExamen_Asignatura);
        segundoExamen_Asignatura.setBounds(panelDashboardLeft.getWidth()/3,80, panelDashboardLeft.getWidth()/3,30);
        segundoExamen_Asignatura.setHorizontalAlignment(SwingConstants.CENTER);
        segundoExamen_Fecha = new JLabel();
        panelProximosExamenes.add(segundoExamen_Fecha);
        segundoExamen_Fecha.setBounds(panelDashboardLeft.getWidth()/3,120, panelDashboardLeft.getWidth()/3,30);
        segundoExamen_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
        tercerExamen_Nombre = new JLabel();
        panelProximosExamenes.add(tercerExamen_Nombre);
        tercerExamen_Nombre.setBounds(2*panelDashboardLeft.getWidth()/3,50, panelDashboardLeft.getWidth()/3,30);
        tercerExamen_Nombre.setFont(new Font("",1,15));
        tercerExamen_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
        tercerExamen_Asignatura = new JLabel();
        panelProximosExamenes.add(tercerExamen_Asignatura);
        tercerExamen_Asignatura.setBounds(2*panelDashboardLeft.getWidth()/3,80, panelDashboardLeft.getWidth()/3,30);
        tercerExamen_Asignatura.setHorizontalAlignment(SwingConstants.CENTER);
        tercerExamen_Fecha = new JLabel();
        panelProximosExamenes.add(tercerExamen_Fecha);
        tercerExamen_Fecha.setBounds(2*panelDashboardLeft.getWidth()/3,120, panelDashboardLeft.getWidth()/3,30);
        tercerExamen_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
        actualizarLabelsProximosExamenes();

        String textLabelAsignaturasGrafico = "<html>";
        ArrayList<Asignatura> lista = Curso.getInstance().getListaAsignaturas();
        for (int i = 0; i < lista.size(); i++) {
            textLabelAsignaturasGrafico = textLabelAsignaturasGrafico +
                      "<p style= 'color: rgb("+lista.get(i).getColor().getRed()+" "+lista.get(i).getColor().getGreen()+" "+lista.get(i).getColor().getBlue()+");'>"+
                      lista.get(i).getNombre()+"</p></br>";
        }
        textLabelAsignaturasGrafico = textLabelAsignaturasGrafico+"</html>";
        labelAsignaturasGrafico = new JLabel(textLabelAsignaturasGrafico);
        panelDashboardLeft.add(labelAsignaturasGrafico);
        labelAsignaturasGrafico.setBounds(20,535,500,375);
        labelAsignaturasGrafico.setVerticalAlignment(SwingConstants.TOP);

        JButton botonAgregarAsignatura = new JButton("Agregar asignatura");
        panelDashboardRight.add(botonAgregarAsignatura);
        botonAgregarAsignatura.setBounds(50,50,200,50);
        botonAgregarAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameAgregarAsignatura(null,"AÑADIR");
            }
        });

        JButton eliminarAsignatura = new JButton("Eliminar asignatura");
        panelDashboardRight.add(eliminarAsignatura);
        eliminarAsignatura.setBounds(260,50,200,50);
        eliminarAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("ELIMINAR");
            }
        });

        JButton editarAsignatura = new JButton("Editar asignatura");
        panelDashboardRight.add(editarAsignatura);
        editarAsignatura.setBounds(470,50,200,50);
        editarAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("EDITARASIGNATURA");
            }
        });

        JButton botonAñadirExamen = new JButton("Añadir examen / trabajo");
        panelDashboardRight.add(botonAñadirExamen);
        botonAñadirExamen.setBounds(50,120,200,50);
        botonAñadirExamen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("AÑADIREXAMEN");
            }
        });

        JButton botonEliminarExamen = new JButton("Eliminar examen / trabajo");
        panelDashboardRight.add(botonEliminarExamen);
        botonEliminarExamen.setBounds(260,120,200,50);
        botonEliminarExamen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("ELIMINAREXAMEN");
            }
        });

        JButton botonEditarNota = new JButton("Editar examen / trabajo");
        panelDashboardRight.add(botonEditarNota);
        botonEditarNota.setBounds(470,120,200,50);
        botonEditarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("EDITAREXAMEN");
            }
        });

        JButton botonAñadirNota = new JButton("Añadir nota");
        panelDashboardRight.add(botonAñadirNota);
        botonAñadirNota.setBounds(50,190,200,50);
        botonAñadirNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("AÑADIRNOTA");
            }
        });

        JButton botonAñadirNotaExtra = new JButton("Añadir nota extra");
        panelDashboardRight.add(botonAñadirNotaExtra);
        botonAñadirNotaExtra.setBounds(260,190,200,50);
        botonAñadirNotaExtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("AÑADIRNOTAEXTRA");
            }
        });

        JButton botonEliminarNotaExtra = new JButton("Eliminar nota extra");
        panelDashboardRight.add(botonEliminarNotaExtra);
        botonEliminarNotaExtra.setBounds(470,190,200,50);
        botonEliminarNotaExtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameSelectorAsignatura("ELIMINARNOTAEXTRA");
            }
        });

        panelDashboardLeft.repaint();
    }

    private void initPantalla() {
        cambiadorPaneles(1);
        setLocationRelativeTo(null);
        setTitle("Gestor Notas");
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initPanelDashboard();
        initPanelAsignaturas();
    }

    private void initPanelAsignaturas() {
        panelAsignaturas = new PanelAsignatura(getWidth());
        contentPanel.add(panelAsignaturas,"panelAsignaturas");
    }

    public void añadirAsignaturaAMenu(Asignatura a){
        JMenuItem j = new JMenuItem(a.getNombre());
        asignaturaMenu.add(j);
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelAsignaturas.setAsignatura(a);
                cambiadorPaneles(2);
            }
        });
    }

    public void eliminarAsignaturaMenu(Asignatura a){
        int i = 0;
        boolean eliminado = false;
        while(!eliminado && i<asignaturaMenu.getItemCount()){
            if(asignaturaMenu.getItem(i).getText().equals(a.getNombre())){
                asignaturaMenu.remove(i);
                eliminado = true;
            }
            i++;
        }
    }

    public void cambiadorPaneles(int index){
        switch (index){
            case 1: ((CardLayout)(contentPanel.getLayout())).show(contentPanel,"panelDashboard");
            break;
            case 2: ((CardLayout)(contentPanel.getLayout())).show(contentPanel,"panelAsignaturas");
                    panelAsignaturas.actualizarGraficos();
                    break;
        }
    }
    private void actualizarLabelsProximosExamenes(){
        ArrayList<Examen_Trabajo> examenesOrdenados = Fecha.ordenarPorFechas(Curso.getInstance().getAllExams());
        if(examenesOrdenados.size()>=1){
            Examen_Trabajo e = examenesOrdenados.get(0);
            primerExamen_Nombre.setText(e.getNombre());
            Asignatura a = e.getAsignatura();
            primerExamen_Asignatura.setText("<html><h1 style= 'color: rgb("+a.getColor().getRed()+" "+a.getColor().getGreen()+" "+a.getColor().getBlue()+");'>"+
                      a.getNombre()+"</h1></html>");
            primerExamen_Fecha.setText(e.getFecha().getDia()+" / "+e.getFecha().getMes()+" / "+e.getFecha().getAño());
        }
        else{
            primerExamen_Nombre.setText("");
            primerExamen_Asignatura.setText("");
            primerExamen_Fecha.setText("");
        }
        if(examenesOrdenados.size()>=2){
            Examen_Trabajo e = examenesOrdenados.get(1);
            segundoExamen_Nombre.setText(e.getNombre());
            Asignatura a = e.getAsignatura();
            segundoExamen_Asignatura.setText("<html><h1 style= 'color: rgb("+a.getColor().getRed()+" "+a.getColor().getGreen()+" "+a.getColor().getBlue()+");'>"+
                      a.getNombre()+"</h1></html>");
            segundoExamen_Fecha.setText(e.getFecha().getDia()+" / "+e.getFecha().getMes()+" / "+e.getFecha().getAño());
        }
        else {
            segundoExamen_Nombre.setText("");
            segundoExamen_Asignatura.setText("");
            segundoExamen_Fecha.setText("");
        }
        if(examenesOrdenados.size()>=3){
            Examen_Trabajo e = examenesOrdenados.get(2);
            tercerExamen_Nombre.setText(e.getNombre());
            Asignatura a = e.getAsignatura();
            tercerExamen_Asignatura.setText("<html><h1 style= 'color: rgb("+a.getColor().getRed()+" "+a.getColor().getGreen()+" "+a.getColor().getBlue()+");'>"+
                      a.getNombre()+"</h1></html>");
            tercerExamen_Fecha.setText(e.getFecha().getDia()+" / "+e.getFecha().getMes()+" / "+e.getFecha().getAño());
        }
        else{
            tercerExamen_Nombre.setText("");
            tercerExamen_Asignatura.setText("");
            tercerExamen_Fecha.setText("");
        }
    }
    public void actualizarGraficosDashboard(){
        DecimalFormat df = new DecimalFormat("#.#");
        double media = Curso.getInstance().getNotaMediaCurso();
        if(media==-1) media = 0;
        labelMediaCurso.setText(df.format(media));
        double notaObtenida = Curso.getInstance().getNotaSeguraObtenida();
        labelNotaObtenida.setText(df.format(notaObtenida));
        double notaMaxima = 10- Curso.getInstance().getNotaPerdida();
        labelMaximaNotaPosible.setText("Max: "+df.format(notaMaxima));

        Curso curso = Curso.getInstance();
        double porcentaje;
        if(curso.getListaAsignaturas().size()>0)
            porcentaje = 100*curso.sumatorioPesosConNota()/(curso.getListaAsignaturas().size()*100);
        else porcentaje = 0;
        porcentaje = 100*curso.sumatorioPesosConNota()/(curso.getListaAsignaturas().size()*100);
        labelProgreso.setText(df.format(porcentaje)+"%");

        String textLabelAsignaturasGrafico = "<html>";
        ArrayList<Asignatura> lista = Curso.getInstance().getListaAsignaturas();
        for (int i = 0; i < lista.size(); i++) {
            textLabelAsignaturasGrafico = textLabelAsignaturasGrafico +
                      "<p style= 'color: rgb("+lista.get(i).getColor().getRed()+" "+lista.get(i).getColor().getGreen()+" "+lista.get(i).getColor().getBlue()+");'>"+
                      lista.get(i).getNombre()+"</p></br>";
        }
        textLabelAsignaturasGrafico = textLabelAsignaturasGrafico+"</html>";
        labelAsignaturasGrafico.setText(textLabelAsignaturasGrafico);

        actualizarLabelsProximosExamenes();
        panelDashboardLeft.repaint();
    }
}