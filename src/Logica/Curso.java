package Logica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Curso {
    private ArrayList<Periodo> periodos;
    private static Curso instance = null;

    private Curso() {
        periodos = new ArrayList<>();
    }

    public static Curso getInstance() {
        if (instance == null) {
            instance = new Curso();
        }
        return instance;
    }

    public Periodo getPeriodoByName(String name) {
        for (Periodo p : periodos) {
            if (p.getNombre().equals(name))
                return p;
        }
        return null;
    }

    public void añadirPeriodo(String nombre) {
        periodos.add(new Periodo(nombre));
    }

    public void añadirPeriodo(Periodo periodo) {
        periodos.add(periodo);
    }

    public void asignarAsignatura(int indicePeriodo, int idAsignatura) {
        periodos.get(indicePeriodo).añadirAsignatura(C_Asignatura.getInstance().getAsignaturaById(idAsignatura));
    }

    public void desasignarAsignatura(int indicePeriodo, int idAsignatura) {
        periodos.get(indicePeriodo).eliminarAsignatura(C_Asignatura.getInstance().getAsignaturaById(idAsignatura));
    }

    public void eliminarAsignatura(int idAsignatura) { //elimina la asignatura de todos los periodos
        for (Periodo periodo : periodos) {
            periodo.eliminarAsignatura(C_Asignatura.getInstance().getAsignaturaById(idAsignatura));
        }
    }

    public double getNotaMediaCurso() { //hace la media de todas las asignaturas del curso con nota
        int totalAsignaturas = 0;
        double sumatorio = 0;
        for (Periodo periodo : periodos) {
            int numAsignaturas = periodo.getNumAsignaturasConNota();
            sumatorio += (periodo.getNotaMediaPeriodo() * numAsignaturas);
            totalAsignaturas += numAsignaturas;
        }
        if (totalAsignaturas == 0)
            return -1;
        else
            return sumatorio / totalAsignaturas;
    }

    public HashMap<Asignatura, Double> getMediasAsignaturas() {
        HashMap<Asignatura, Double> notas = new HashMap<>();
        HashMap<Asignatura, Integer> repeticiones = new HashMap<>();
        for (Periodo p : periodos) {
            for (Integer i : p.getAsignaturas()) {
                Asignatura a = C_Asignatura.getInstance().getAsignaturaById(i);
                if (!notas.containsKey(a)) {
                    notas.put(a, p.getNotaMediaAsignatura(a));
                } else {
                    if (p.getNotaMediaAsignatura(a) != -1) {
                        if (notas.get(a) == -1) {
                            notas.put(a, p.getNotaMediaAsignatura(a));
                        } else {
                            notas.put(a, p.getNotaMediaAsignatura(a) + notas.get(a));
                            if (repeticiones.containsKey(a))
                                repeticiones.put(a, repeticiones.get(a) + 1);
                            else
                                repeticiones.put(a, 2);
                        }
                    }
                }
            }
        }
        for (Asignatura a : repeticiones.keySet()) { //editar si hay periodos que cuentan más que otros
            notas.put(a, notas.get(a) / repeticiones.get(a));
        }
        return notas;
    }

    public double getNotaSeguraObtenidaCurso() {
        double notaTotal = 0;
        int numAsignaturas = 0;
        for (Periodo periodo : periodos) {
            int asignaturas = periodo.getNumAsignaturas();
            numAsignaturas += asignaturas;
            notaTotal += (periodo.getNotaSeguraObtenidaPeriodo() * asignaturas);
        }
        if (numAsignaturas == 0)
            return 0;
        else
            return notaTotal / numAsignaturas;
    }

    public double getNotaPerdidaCurso() {
        double notaTotal = 0;
        int numAsignaturas = 0;
        for (Periodo periodo : periodos) {
            int asignaturas = periodo.getNumAsignaturas();
            notaTotal += (periodo.getNotaPerdidaPeriodo() * asignaturas);
            numAsignaturas += asignaturas;
        }
        if (numAsignaturas == 0)
            return 0;
        else
            return notaTotal / numAsignaturas;
    }

    public double getProgreso() {
        double sumatorio = 0;
        int numAsignaturas = 0;
        for (Periodo p : periodos) {
            sumatorio += p.getSumatorioPesosConNota();
            numAsignaturas += p.getNumAsignaturas();
        }
        if (numAsignaturas == 0)
            return -1;
        return sumatorio / numAsignaturas;
    }

    public ArrayList<Periodo> getPeriodos() {
        return periodos;
    }

    public void guardar() {
        FileWriter f = null;
        try {
            f = new FileWriter("BBDD/periodos.txt");
            for (Periodo periodo : periodos) {
                f.write(periodo.toString() + "\n");
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

    public void cargar() {
        Scanner s;
        //nombrePeriodo $ idA1 : idEv1 : idEv2 , idA2 : idEv3 : idEv4 ; idA1 : ne , idA2 : ne
        try {
            s = new Scanner(new FileReader("BBDD/periodos.txt"));
            while (s.hasNext()) {
                String linea = s.nextLine();
                String nombre = linea.split("_")[0];
                linea = linea.split("_")[1];
                try {
                    String evaluables = linea.split(";")[0];
                    String[] asiganturasEvaluables = evaluables.split(",");
                    HashMap<Integer, List<Integer>> hmEvaluables = new HashMap<>();
                    for (String linea2 : asiganturasEvaluables) {
                        String[] valores = linea2.split(":");
                        int idAsignatura = Integer.parseInt(valores[0]);
                        ArrayList<Integer> idEvaluables = new ArrayList<>();
                        for (int i = 1; i < valores.length; i++) {
                            idEvaluables.add(Integer.parseInt(valores[i]));
                        }
                        hmEvaluables.put(idAsignatura, idEvaluables);
                    }
                    String notasExtra = linea.split(";")[1];
                    HashMap<Integer, Double> hmNotasExtra = new HashMap<>();
                    String[] asignaturasNotaExtra = notasExtra.split(",");
                    for (String linea2 : asignaturasNotaExtra) {
                        int idAsignatura = Integer.parseInt(linea2.split(":")[0]);
                        double notaExtra = Double.parseDouble(linea2.split(":")[1]);
                        hmNotasExtra.put(idAsignatura, notaExtra);
                    }
                    Periodo periodo = new Periodo(nombre, hmEvaluables, hmNotasExtra);
                    añadirPeriodo(periodo);
                } catch (ArrayIndexOutOfBoundsException ex){
                    añadirPeriodo(nombre);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
}