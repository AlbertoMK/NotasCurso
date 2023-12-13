public class Examen_Trabajo {
    private String nombre;
    private double nota,pesoPorcentaje;
    private double maximo;
    private Fecha fecha;
    private Asignatura asignatura;

    public Examen_Trabajo(String nombre, double maximo, double pesoPorcentaje, int dia, int mes, int año, Asignatura asignatura){
        this.nombre = nombre;
        this.nota = -1;
        this.maximo = maximo;
        this.pesoPorcentaje = pesoPorcentaje;
        this.fecha = new Fecha(dia,mes,año);
        this.asignatura = asignatura;
    }

    public Examen_Trabajo(String nombre, double nota, double maximo, double pesoPorcentaje, int dia, int mes, int año, Asignatura asignatura){
        this.nombre = nombre;
        this.maximo = maximo;
        this.nota = nota;
        this.pesoPorcentaje = pesoPorcentaje;
        this.fecha = new Fecha(dia,mes,año);
        this.asignatura = asignatura;
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
        String resultado = nombre+";"+nota+";"+maximo+";"+pesoPorcentaje+";"+fecha.getDia()+";"+fecha.getMes()+";"+fecha.getAño()+";";
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
    public Asignatura getAsignatura(){
        return asignatura;
    }
}