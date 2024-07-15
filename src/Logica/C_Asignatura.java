package Logica;

import Modelo.Asignatura;
import Modelo.ExcepcionAsignatura;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class C_Asignatura {
    private static C_Asignatura instance = null;
    private ArrayList<Asignatura> listaAsignaturas;

    private C_Asignatura(){
        listaAsignaturas = new ArrayList<>();
    }

    public static C_Asignatura getInstance(){
        if(instance == null)
            instance = new C_Asignatura();
        return instance;
    }

    public ArrayList<Asignatura> getListaAsignaturas(){
        return listaAsignaturas;
    }

    public void añadirAsignatura(Asignatura a) throws ExcepcionAsignatura {
        if(existeAsignatura(a.getNombre()))
            throw new ExcepcionAsignatura("Ese nombre de asignatura ya está creado.");

        listaAsignaturas.add(a);
    }

    public int getNumAsignaturas(){
        return listaAsignaturas.size();
    }

    public void eliminarAsignatura(Asignatura a){
        listaAsignaturas.remove(a);
    }

    public Asignatura getAsignaturaById(int id){
        boolean encontrado = false;
        int i = 0;
        while(i < listaAsignaturas.size() && !encontrado){
            if(listaAsignaturas.get(i).getId() == id)
                encontrado = true;
            else
                i++;
        }

        if(encontrado)
            return listaAsignaturas.get(i);
        else
            return null;
    }

    public Asignatura getAsignaturaByName(String name){
        boolean encontrado = false;
        int i = 0;
        while(i < listaAsignaturas.size() && !encontrado){
            if(listaAsignaturas.get(i).getNombre().equals(name))
                encontrado = true;
            else
                i++;
        }

        if(encontrado)
            return listaAsignaturas.get(i);
        else
            return null;
    }

    private boolean existeAsignatura(String nombre){
        boolean resultado = false;
        for(Asignatura a : listaAsignaturas){
            if(a.getNombre().equals(nombre))
                resultado = true;
        }
        return resultado;
    }

    public void guardar(){
        FileWriter f = null;
        try {
            f = new FileWriter("BBDD/asignaturas.txt", false);
            f.write(Asignatura.getSiguienteId()+"\n");
            for(Asignatura a : listaAsignaturas){
                f.write(a.toString()+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargar(){
        Scanner s;
        try {
            s = new Scanner(new FileReader("BBDD/asignaturas.txt"));
            Asignatura.setSiguienteId(Integer.parseInt(s.nextLine()));
            //id, nombre, creditos, r ; g ; b
            while(s.hasNext()){
                String linea = s.nextLine();
                String[] parametros = linea.split(",");
                int id = Integer.parseInt(parametros[0]);
                String nombre = parametros[1];
                double creditos = Double.parseDouble(parametros[2]);
                String[] colores = parametros[3].split(";");
                Asignatura asignatura = new Asignatura(id, nombre, new Color(Integer.parseInt(colores[0]), Integer.parseInt(colores[1]), Integer.parseInt(colores[2])), creditos);
                listaAsignaturas.add(asignatura);
            }
        } catch (FileNotFoundException e) {
        }
    }
}
