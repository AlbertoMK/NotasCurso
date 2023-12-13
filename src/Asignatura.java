import java.awt.Color;
import java.util.ArrayList;

public class Asignatura {
    private String nombre;
    private int semestre;
    private Color color;
    private ArrayList<Examen_Trabajo> listaExamenes;
    private ArrayList<Examen_Trabajo> listaTrabajos;
    private double notaExtra;

    public Asignatura(String nombre, int semestre, Color color,double notaExtra){
        this.nombre = nombre;
        this.semestre = semestre;
        this.color = color;
        listaExamenes = new ArrayList<>();
        listaTrabajos = new ArrayList<>();
        this.notaExtra=notaExtra;
    }

    public Asignatura(String nombre, Color color ,double notaExtra){
        this(nombre,0,color,notaExtra);
    }

    public Examen_Trabajo añadirExamen(String nombre, double maximo, double pesoPorcentaje, int dia, int mes, int año){
        Examen_Trabajo e = new Examen_Trabajo(nombre,maximo,pesoPorcentaje,dia,mes,año,this);
        listaExamenes.add(e);
        return e;
    }
    public void añadirExamen(String nombre, double nota, double maximo,double pesoPorcentaje,int dia, int mes, int año){
        listaExamenes.add(new Examen_Trabajo(nombre,nota, maximo, pesoPorcentaje, dia,mes,año,this));
    }

    public Examen_Trabajo añadirTrabajo(String nombre, double maximo, double pesoPorcentaje,int dia, int mes, int año){
        Examen_Trabajo e = new Examen_Trabajo(nombre,maximo,pesoPorcentaje,dia,mes,año,this);
        listaTrabajos.add(e);
        return e;
    }
    public void añadirTrabajo(String nombre, double nota, double maximo,double pesoPorcentaje,int dia, int mes, int año){
        listaTrabajos.add(new Examen_Trabajo(nombre,nota, maximo,pesoPorcentaje,dia,mes,año,this));
    }
    public void eliminarExamenOTrabajo(Examen_Trabajo examen_trabajo){
        if(listaExamenes.indexOf(examen_trabajo)!=-1){
            listaExamenes.remove(examen_trabajo);
        }
        else{
            listaTrabajos.remove(examen_trabajo);
        }
        Pantalla.getInstance().actualizarGraficosDashboard();
    }
    public void añadirNotaExtra(double notaExtra){
        this.notaExtra+=notaExtra;
    }
    public void eliminarNotaExtra(double notaExtra){
        this.notaExtra-=notaExtra;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getColorRGB(){
        return color.getRed()+";"+color.getGreen()+";"+color.getBlue();
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public int getSemestre(){
        return semestre;
    }
    public void setSemestre(int valor){
        semestre = valor;
    }
    public String getExamenes(){
        String resultado = "";
        for (int i = 0; i < listaExamenes.size(); i++) {
            resultado = resultado+ listaExamenes.get(i).toString()+";";
        }
        return resultado;
    }
    public String getTrabajos(){
        String resultado = "";
        for (int i = 0; i < listaTrabajos.size(); i++) {
            resultado = resultado+ listaTrabajos.get(i).toString()+";";
        }
        return resultado;
    }
    public double getNotaMedia(){
        double sumatorioPesos = 0;
        double sumatorioNotaPorPeso = 0;
        for (int i = 0; i < listaTrabajos.size(); i++) {
            if(listaTrabajos.get(i).getNota()!=-1) {
                sumatorioNotaPorPeso += listaTrabajos.get(i).getNotaSobre10() * (listaTrabajos.get(i).getPesoPorcentaje() / 100);
                sumatorioPesos += listaTrabajos.get(i).getPesoPorcentaje();
            }
        }
        for (int i = 0; i < listaExamenes.size(); i++) {
            if(listaExamenes.get(i).getNota()!=-1) {
                sumatorioNotaPorPeso += listaExamenes.get(i).getNotaSobre10() * (listaExamenes.get(i).getPesoPorcentaje() / 100);
                sumatorioPesos += listaExamenes.get(i).getPesoPorcentaje();
            }
        }
        if(sumatorioPesos==0 && notaExtra==0) return -1;
        if(sumatorioPesos==0) return notaExtra;
        double media = sumatorioNotaPorPeso*100/sumatorioPesos;
        media+=notaExtra;
        return media;
    }
    public double getNotaSeguraObtenida(){
        double resulado = 0;
        int contador = 0;
        for (int i = 0; i < listaExamenes.size(); i++) {
            Examen_Trabajo e = listaExamenes.get(i);
            if(e.getNota()!=-1){
                contador++;
                resulado+= e.getNotaSobre10()*(e.getPesoPorcentaje()/100);
            }
        }
        for (int i = 0; i < listaTrabajos.size(); i++) {
            Examen_Trabajo e = listaTrabajos.get(i);
            if(e.getNota()!=-1){
                contador++;
                resulado+= e.getNotaSobre10()*(e.getPesoPorcentaje()/100);
            }
        }
        resulado+=notaExtra;
        if(contador==0 && resulado==0) return -1;
        return resulado;
    }

    public double getNotaPerdida(){
        double resulado = 0;
        int contador = 0;
        for (int i = 0; i < listaExamenes.size(); i++) {
            Examen_Trabajo e = listaExamenes.get(i);
            if(e.getNota()!=-1){
                contador++;
                resulado+= (10-e.getNotaSobre10())*(e.getPesoPorcentaje()/100);
            }
        }
        for (int i = 0; i < listaTrabajos.size(); i++) {
            Examen_Trabajo e = listaTrabajos.get(i);
            if(e.getNota()!=-1){
                contador++;
                resulado+= (10-e.getNotaSobre10())*(e.getPesoPorcentaje()/100);
            }
        }
        resulado-=notaExtra;
        if(resulado<0) resulado = 0;
        return resulado;
    }

    public String toString(){
        String resultado = nombre+","+semestre+","+ getColorRGB()+","+notaExtra+"\n";
        if(listaExamenes.size()==0) resultado = resultado+"SIN DATOS";
        for (int i = 0; i < listaExamenes.size(); i++) {
            resultado = resultado+listaExamenes.get(i).toString()+",";
        }
        resultado = resultado+"\n";
        if(listaTrabajos.size()==0) resultado = resultado+"SIN DATOS";
        for (int i = 0; i < listaTrabajos.size(); i++) {
            resultado = resultado+listaTrabajos.get(i).toString()+",";
        }
        return resultado;
    }

    public ArrayList<Examen_Trabajo> getListaExamenes() {
        return listaExamenes;
    }

    public ArrayList<Examen_Trabajo> getListaTrabajos() {
        return listaTrabajos;
    }
}