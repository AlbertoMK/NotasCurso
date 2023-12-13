import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class dibujosFondo extends JPanel {
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);

        g.drawLine(getWidth()-1,0,getWidth()-1,getHeight());

        int grados;
        double media = Curso.getInstance().getNotaMediaCurso();
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
            if(r<0) r =0;
        }
        g2.setColor(new Color(r,green,0));
        if(grados > 360) grados = 360;
        if(grados < 0) grados = 0;
        g2.drawArc(3*getWidth()/4-100,130,140,140,0,grados);
        g2.setColor(new Color(201, 201, 201));
        g2.drawArc(3*getWidth()/4-100,130,140,140,grados,360-grados);

        double notaConseguida = Curso.getInstance().getNotaSeguraObtenida();
        if(notaConseguida == -1) notaConseguida = 0;
        int gradosConseguidos = Math.round((float)(notaConseguida*36));
        double notaPerdida = Curso.getInstance().getNotaPerdida();
        int gradosPerdidos = Math.round((float)(notaPerdida*36));
        int gradosRestantes = 360-(gradosConseguidos+gradosPerdidos);
        g2.setColor(Color.blue);
        g2.drawArc(getWidth()/4-100,130,140,140,0,gradosConseguidos);
        g2.setColor(new Color(201,201,201));
        g2.drawArc(getWidth()/4-100,130,140,140,gradosConseguidos,gradosRestantes);

        Curso curso = Curso.getInstance();

        double porcentaje = curso.sumatorioPesosConNota()/(curso.getListaAsignaturas().size()*100);

        g2.setColor(Color.green);
        g2.fillRect(getWidth()/2-200,10,(int)(400*porcentaje),50);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(0, 129, 11));
        g2.drawRect(getWidth()/2-200,10,400,50);

        for (int i = 0; i < curso.getListaAsignaturas().size(); i++) {
            Asignatura a = curso.getListaAsignaturas().get(i);
            g.setColor(a.getColor());
            int altura = (int)(a.getNotaMedia()*34.7);
            if(altura<0) altura = 0;

            g.fillRect(getWidth()-(curso.getListaAsignaturas().size()*40+50)+40*i,535+(347-altura),40,altura);
        }
        int alturaLinea = (int)(535+347-(curso.getNotaMediaCurso()*34.7));
        g2.setColor(new Color(148, 255, 133));
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(getWidth()-(curso.getListaAsignaturas().size()*40)-90,alturaLinea,getWidth()-20,alturaLinea);
    }
}