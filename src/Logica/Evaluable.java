package Logica;

public class Evaluable {
    private String nombre;
    private double nota, pesoPorcentaje, maximo;
    private Fecha fecha;
    private int id;
    public static int siguienteId = 1;
    private boolean esExamen;

    private Evaluable(){}
    
    public Evaluable(String nombre, double nota, double maximo, double pesoPorcentaje, int dia, int mes, int año, boolean esExamen){
        this(siguienteId, nombre, nota, maximo, pesoPorcentaje, dia, mes, año, esExamen);
        siguienteId++;
    }

    public Evaluable(int id, String nombre, double nota, double maximo, double pesoPorcentaje, int dia, int mes, int año, boolean esExamen){
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
        this.maximo = maximo;
        this.pesoPorcentaje = pesoPorcentaje;
        this.fecha = new Fecha(dia,mes,año);
        this.esExamen = esExamen;
    }

    public int getId(){
        return id;
    }
    public void setNota(double nota){
        this.nota = nota;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public double getNota() {
        return nota;
    }
    public double getPesoPorcentaje(){
        return pesoPorcentaje;
    }
    public void setPesoPorcentaje(double peso){
        this.pesoPorcentaje = peso;
    }
    public double getMaximo() {
        return maximo;
    }
    public void setMaximo(double m){
        maximo = m;
    }
    public void setEsExamen(boolean esExamen){
        this.esExamen  = esExamen;
    }
    public boolean notaAsignada(){
        return nota!=-1;
    }
    public void asignarNota(double nota){
        this.nota = nota;
    }
    public double getNotaSobre10(){
        return nota/maximo*10;
    }
    public String toString(){
        String resultado = id+";"+nombre+";"+nota+";"+maximo+";"+pesoPorcentaje+";"+esExamen+";"+fecha.getDia()+";"+fecha.getMes()+";"+fecha.getAño()+";";
        return resultado;
    }
    public int getDia(){
        return fecha.getDia();
    }
    public int getMes(){
        return fecha.getMes();
    }
    public int getAño(){
        return fecha.getAño();
    }
    public void setDia(int dia){
        this.fecha.setDia(dia);
    }
    public void setMes(int mes){
        fecha.setMes(mes);
    }
    public void setAño(int año){
        fecha.setAño(año);
    }
    public Fecha getFecha(){
        return fecha;
    }
    public boolean getEsExamen(){
        return esExamen;
    }
}
