import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PanelAsignatura extends JPanel {
    private Asignatura asignatura;
    private JLabel labelNombreAsignatura, textoNotaMedia, textoNotaConseguida, labelNotaMaxima, porcentajeProgreso;
    private ArrayList<JLabel> listaLabelsTabla;

    public PanelAsignatura(int ancho){
        listaLabelsTabla = new ArrayList<>();
        setLayout(null);
        asignatura = null;
        Color colorFondo = new Color(255, 245, 209);
        Color marron = new Color(185, 122, 87);
        Font f = new Font("",1,24);
        setBackground(colorFondo);
        labelNombreAsignatura = new JLabel("");
        add(labelNombreAsignatura);
        labelNombreAsignatura.setBounds(ancho/2-200,30,400,50);
        labelNombreAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombreAsignatura.setFont(new Font("",1,30));

        JLabel labelNotaMedia = new JLabel("NOTA MEDIA");
        add(labelNotaMedia);
        labelNotaMedia.setForeground(marron);
        labelNotaMedia.setBounds(0,140,(2*ancho/5),50);
        labelNotaMedia.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaMedia.setFont(f);

        textoNotaMedia = new JLabel("");
        add(textoNotaMedia);
        textoNotaMedia.setBounds(0,255,(2*ancho/5),50);
        textoNotaMedia.setHorizontalAlignment(SwingConstants.CENTER);
        textoNotaMedia.setFont(f);

        JLabel labelNotaConseguida = new JLabel("NOTA CONSEGUIDA / MÁXIMA");
        add(labelNotaConseguida);
        labelNotaConseguida.setForeground(marron);
        labelNotaConseguida.setBounds(3*ancho/5,140,ancho-(3*ancho/5),50);
        labelNotaConseguida.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaConseguida.setFont(f);

        textoNotaConseguida = new JLabel("");
        add(textoNotaConseguida);
        textoNotaConseguida.setBounds(3*ancho/5-10,255,ancho-(3*ancho/5),50);
        textoNotaConseguida.setHorizontalAlignment(SwingConstants.CENTER);
        textoNotaConseguida.setFont(f);

        labelNotaMaxima = new JLabel("");
        add(labelNotaMaxima);
        labelNotaMaxima.setBounds(3*ancho/5+(ancho-3*ancho/5)/2-85,350,150,30);
        labelNotaMaxima.setHorizontalAlignment(SwingConstants.CENTER);
        labelNotaMaxima.setForeground(new Color(136, 136, 136));

        porcentajeProgreso = new JLabel("");
        add(porcentajeProgreso);
        porcentajeProgreso.setBounds(ancho/2-50,300,100,50);
        porcentajeProgreso.setHorizontalAlignment(SwingConstants.CENTER);
        porcentajeProgreso.setVerticalAlignment(SwingConstants.CENTER);
        porcentajeProgreso.setFont(f);

        Font fontTabla = new Font("",1,17);
        JLabel tablaNombre = new JLabel("NOMBRE");
        add(tablaNombre);
        tablaNombre.setBounds(0,390,ancho/5,30);
        tablaNombre.setHorizontalAlignment(SwingConstants.CENTER);
        tablaNombre.setFont(fontTabla);
        JLabel tablaPeso = new JLabel("PESO");
        add(tablaPeso);
        tablaPeso.setBounds(ancho/5,390,ancho/5,30);
        tablaPeso.setHorizontalAlignment(SwingConstants.CENTER);
        tablaPeso.setFont(fontTabla);
            JLabel tablaMaximo = new JLabel("MÁXIMO");
        add(tablaMaximo);
        tablaMaximo.setBounds(2*ancho/5,390,ancho/5,30);
        tablaMaximo.setHorizontalAlignment(SwingConstants.CENTER);
        tablaMaximo.setFont(fontTabla);
        JLabel tablaFecha = new JLabel("FECHA");
        add(tablaFecha);
        tablaFecha.setBounds(3*ancho/5,390,ancho/5,30);
        tablaFecha.setHorizontalAlignment(SwingConstants.CENTER);
        tablaFecha.setFont(fontTabla);
        JLabel tablaNota = new JLabel("NOTA");
        add(tablaNota);
        tablaNota.setBounds(4*ancho/5,390,ancho/5,30);
        tablaNota.setHorizontalAlignment(SwingConstants.CENTER);
        tablaNota.setFont(fontTabla);
    }
    public void setAsignatura(Asignatura asignatura){
        this.asignatura = asignatura;
    }
    public void actualizarGraficos(){
        while(listaLabelsTabla.size()>0) {
            remove(listaLabelsTabla.get(0));
            listaLabelsTabla.remove(listaLabelsTabla.get(0));
        }
        actualizarLabels();
        repaint();
    }
    private void actualizarLabels(){
        DecimalFormat df = new DecimalFormat("#.#");
        labelNombreAsignatura.setForeground(asignatura.getColor());
        labelNombreAsignatura.setText(asignatura.getNombre());
        if(asignatura.getNotaMedia()==-1)
            textoNotaMedia.setText("Sin datos");
        else
            textoNotaMedia.setText(df.format(asignatura.getNotaMedia()));
        String notaConseguida;
        if(asignatura.getNotaSeguraObtenida()==-1)
            notaConseguida = String.valueOf(0);
        else
            notaConseguida = df.format(asignatura.getNotaSeguraObtenida());
        textoNotaConseguida.setText(notaConseguida);
        double notaMaxima = 10-asignatura.getNotaPerdida();
        labelNotaMaxima.setText("Max: "+df.format(notaMaxima));

        double sumatorioPesosConNota = 0;
        for (int i = 0; i < asignatura.getListaExamenes().size(); i++) {
            Examen_Trabajo e = asignatura.getListaExamenes().get(i);
            if(e.notaAsignada()){
                sumatorioPesosConNota+=e.getPesoPorcentaje();
            }
        }
        for (int i = 0; i < asignatura.getListaTrabajos().size(); i++) {
            Examen_Trabajo e = asignatura.getListaTrabajos().get(i);
            if(e.notaAsignada()){
                sumatorioPesosConNota+=e.getPesoPorcentaje();
            }
        }
        porcentajeProgreso.setText(df.format(sumatorioPesosConNota)+"%");
        int contador = 0;
        //LABELS TABLA
        for (int i = 0; i < asignatura.getListaExamenes().size(); i++) {
            Examen_Trabajo e = asignatura.getListaExamenes().get(i);
            JLabel nombre = new JLabel(e.getNombre());
            add(nombre);
            nombre.setBounds(0,470+30*contador,getWidth()/5,30);
            nombre.setHorizontalAlignment(SwingConstants.CENTER);
            listaLabelsTabla.add(nombre);

            JLabel peso = new JLabel(String.valueOf(e.getPesoPorcentaje()));
            add(peso);
            peso.setBounds(getWidth()/5,470+30*contador,getWidth()/5,30);
            peso.setHorizontalAlignment(SwingConstants.CENTER);
            listaLabelsTabla.add(peso);

            JLabel maximo = new JLabel(String.valueOf(e.getMaximo()));
            add(maximo);
            maximo.setBounds(2*getWidth()/5,470+30*contador,getWidth()/5,30);
            maximo.setHorizontalAlignment(SwingConstants.CENTER);
            listaLabelsTabla.add(maximo);

            if(e.getDia()!=-1){
                JLabel fecha = new JLabel(String.valueOf(e.getDia())+" / "+String.valueOf(e.getMes())+" / "+String.valueOf(e.getAño()));
                add(fecha);
                fecha.setBounds(3*getWidth()/5,470+contador*30,getWidth()/5,30);
                fecha.setHorizontalAlignment(SwingConstants.CENTER);
                listaLabelsTabla.add(fecha);
            }

            if(e.getNota()!=-1){
                JLabel nota = new JLabel(String.valueOf(e.getNota()));
                add(nota);
                nota.setBounds(4*getWidth()/5,470+30*contador,getWidth()/5,30);
                nota.setHorizontalAlignment(SwingConstants.CENTER);
                listaLabelsTabla.add(nota);
            }

            contador++;
        }
        for (int i = 0; i < asignatura.getListaTrabajos().size(); i++) {
            Examen_Trabajo e = asignatura.getListaTrabajos().get(i);
            JLabel nombre = new JLabel(e.getNombre());
            add(nombre);
            nombre.setBounds(0,470+30*contador,getWidth()/5,30);
            nombre.setHorizontalAlignment(SwingConstants.CENTER);
            listaLabelsTabla.add(nombre);

            JLabel peso = new JLabel(String.valueOf(e.getPesoPorcentaje()));
            add(peso);
            peso.setBounds(getWidth()/5,470+30*contador,getWidth()/5,30);
            peso.setHorizontalAlignment(SwingConstants.CENTER);
            listaLabelsTabla.add(peso);

            JLabel maximo = new JLabel(String.valueOf(e.getMaximo()));
            add(maximo);
            maximo.setBounds(2*getWidth()/5,470+30*contador,getWidth()/5,30);
            maximo.setHorizontalAlignment(SwingConstants.CENTER);
            listaLabelsTabla.add(maximo);

            if(e.getDia()!=-1){
                JLabel fecha = new JLabel(String.valueOf(e.getDia())+" / "+String.valueOf(e.getMes())+" / "+String.valueOf(e.getAño()));
                add(fecha);
                fecha.setBounds(3*getWidth()/5,470+contador*30,getWidth()/5,30);
                fecha.setHorizontalAlignment(SwingConstants.CENTER);
                listaLabelsTabla.add(fecha);
            }

            if(e.getNota()!=-1){
                JLabel nota = new JLabel(String.valueOf(e.getNota()));
                add(nota);
                nota.setBounds(4*getWidth()/5,470+30*contador,getWidth()/5,30);
                nota.setHorizontalAlignment(SwingConstants.CENTER);
                listaLabelsTabla.add(nota);
            }

            contador++;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        Color marron = new Color(185, 122, 87);
        g2.setColor(marron);
        g2.drawLine(0,120,2*getWidth()/5,120);
        g2.drawLine(3*getWidth()/5,120,getWidth(),120);
        //DIBUJO ROMBO
        g2.drawLine(getWidth()/2,105,getWidth()/2-12,120);
        g2.drawLine(getWidth()/2-12,120,getWidth()/2,135);
        g2.drawLine(getWidth()/2,135,getWidth()/2+12,120);
        g2.drawLine(getWidth()/2+12,120,getWidth()/2,105);

        int grados;
        double media = asignatura.getNotaMedia();
        DecimalFormat df = new DecimalFormat("#.##");
        String mediaFormateada = df.format(media);
        mediaFormateada = mediaFormateada.replace(',','.');
        media = Double.parseDouble(mediaFormateada);
        if(media==-1){
            grados = 0;
        }
        else{
            grados = (int)(media*36);
        }
        g2.setStroke(new BasicStroke(5));
        int r, green;
        if(media<5){
            r = 255;
            if(media==-1) green = 0;
            else{
                green = (int)(media*51);
                if(green>255) green = 255;
            }

        }
        else if(media==5){
            r = 255;
            green = 255;
        }
        else{
            green = 255;
            r= (int)(-51*(media-5)+255);
            if(r<0) r=0;
        }
        g2.setColor(new Color(r,green,0));
        if(grados > 360) grados = 360;
        if(grados < 0) grados = 0;
        g2.drawArc(getWidth()/5-70,210,140,140,0,grados);
        g2.setColor(new Color(201, 201, 201));
        g2.drawArc(getWidth()/5-70,210,140,140,grados,360-grados);

        double notaConseguida = asignatura.getNotaSeguraObtenida();
        if(notaConseguida == -1) notaConseguida = 0;
        int gradosConseguidos = Math.round((float)(notaConseguida*36));
        double notaPerdida = asignatura.getNotaPerdida();
        int gradosPerdidos = Math.round((float)(notaPerdida*36));
        int gradosRestantes = 360-(gradosConseguidos+gradosPerdidos);
        g2.setColor(Color.blue);
        g2.drawArc(3*getWidth()/5+(getWidth()-3*getWidth()/5)/2-70,210,140,140,0,gradosConseguidos);
        g2.setColor(new Color(201,201,201));
        g2.drawArc(3*getWidth()/5+(getWidth()-3*getWidth()/5)/2-70,210,140,140,gradosConseguidos,gradosRestantes);

        //BARRA PROGRESO
        double sumatorioPesosConNota = 0;
        for (int i = 0; i < asignatura.getListaExamenes().size(); i++) {
            Examen_Trabajo e = asignatura.getListaExamenes().get(i);
            if(e.notaAsignada()){
                sumatorioPesosConNota+=e.getPesoPorcentaje();
            }
        }
        for (int i = 0; i < asignatura.getListaTrabajos().size(); i++) {
            Examen_Trabajo e = asignatura.getListaTrabajos().get(i);
            if(e.notaAsignada()){
                sumatorioPesosConNota+=e.getPesoPorcentaje();
            }
        }
        g2.setColor(Color.green);
        g2.fillRect(getWidth()/2-300,300,(int)(6*sumatorioPesosConNota),50);
        g2.setColor(new Color(0, 129, 11));
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(getWidth()/2-300,300,600,50);
    }
}