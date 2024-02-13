package Logica;

import java.awt.Color;

public class Asignatura {
    private int id;
    private static int siguienteId = 1;
    private String nombre;
    private Color color;
    private double creditos;

    public Asignatura(String nombre, Color color, double creditos) {
        this(siguienteId, nombre, color, creditos);
        siguienteId++;
    }

    public Asignatura(int id, String nombre, Color color, double creditos){
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.creditos = creditos;
    }

    public int getId(){
        return id;
    }
    public static int getSiguienteId(){
        return siguienteId;
    }
    public static void setSiguienteId(int valor){
        siguienteId = valor;
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
    public void setCreditos(double creditos){
        this.creditos = creditos;
    }
    public double getCreditos(){
        return creditos;
    }

    public String toString(){
        return id + "," + nombre + ","+ creditos + ","+ getColorRGB();
    }
}
