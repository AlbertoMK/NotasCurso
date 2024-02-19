package Logica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class C_Evaluable {
    private ArrayList<Evaluable> listaEvaluables;
    private static C_Evaluable instance = null;

    private C_Evaluable(){
        listaEvaluables = new ArrayList<>();
    }

    public static C_Evaluable getInstance(){
        if(instance == null)
            instance = new C_Evaluable();
        return instance;
    }

    public void añadirEvaluable(Evaluable evaluable){
        listaEvaluables.add(evaluable);
    }

    public void eliminarEvaluable(Evaluable evaluable){
        listaEvaluables.remove(evaluable);
    }

    public void eliminarEvaluable(int idEvaluable){
        Evaluable ev = null;
        for (Evaluable e : listaEvaluables) {
            if(e.getId() == idEvaluable)
                ev = e;
        }
        listaEvaluables.remove(ev);
    }

    public Evaluable getEvaluableById(int id){
        int i = 0;
        while(i < listaEvaluables.size() && listaEvaluables.get(i).getId() != id){
            i++;
        }
        if(i == listaEvaluables.size())
            return null;
        else
            return listaEvaluables.get(i);
    }

    public ArrayList<Evaluable> getEvaluablesOrdenados(ArrayList<Evaluable> lista){
        ArrayList<Evaluable> copia = new ArrayList<>();
        for (Evaluable ev : lista){
            copia.add(ev);
        }

        copia.sort(new Comparator<Evaluable>() {
            @Override
            public int compare(Evaluable e1, Evaluable e2) {
                if(e1.getFecha().anteriorQue(e2.getFecha()))
                    return -1;
                else
                    return 1;
            }
        });

        return copia;
    }

    public void guardar(){
        FileWriter f = null;
        try {
            f = new FileWriter("BBDD/evaluables.txt", false);
            f.write(Evaluable.siguienteId + "\n");
            for (Evaluable evaluable : listaEvaluables){
                f.write(evaluable.toString()+"\n");
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
            s = new Scanner(new FileReader("BBDD/evaluables.txt"));
            Evaluable.siguienteId = Integer.parseInt(s.nextLine());
            //id nombre nota maximo peso esExamen fecha
            while(s.hasNext()){
                String linea = s.nextLine();
                String[] parametros = linea.split(";");
                int id = Integer.parseInt(parametros[0]);
                String nombre = parametros[1];
                double nota = Double.parseDouble(parametros[2]);
                double maximo = Double.parseDouble(parametros[3]);
                double peso = Double.parseDouble(parametros[4]);
                boolean esExamen = Boolean.parseBoolean(parametros[5]);
                int dia = Integer.parseInt(parametros[6]);
                int mes = Integer.parseInt(parametros[7]);
                int año = Integer.parseInt(parametros[8]);
                Evaluable evaluable = new Evaluable(id, nombre, nota, maximo, peso, dia, mes, año, esExamen);
                listaEvaluables.add(evaluable);
            }
        } catch (FileNotFoundException e) {
        }
    }
}
