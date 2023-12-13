import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    private ArrayList<Asignatura> listaAsignaturas;
    private static Curso instance = null;

    private Curso() {
        listaAsignaturas = new ArrayList<>();
    }

    public static Curso getInstance(){
        if(instance == null){
            instance = new Curso();
        }
        return instance;
    }

    public Asignatura agregarAsignatura(String nombre, int semestre, Color color) {
        Asignatura a = new Asignatura(nombre, semestre, color,0);
        listaAsignaturas.add(a);
        return a;
    }

    public void agregarAsignatura(String nombre, Color color) {
        listaAsignaturas.add(new Asignatura(nombre, color,0));
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        listaAsignaturas.remove(asignatura);
    }

    public void agregarNota() {
        Scanner s = new Scanner(System.in);
        ArrayList<Examen_Trabajo> añadidas = new ArrayList<>();
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            for (int j = 0; j < listaAsignaturas.get(i).getListaExamenes().size(); j++) {
                System.out.println(añadidas.size() + 1 + ". " + listaAsignaturas.get(i).getNombre() + ". " + listaAsignaturas.get(i).getListaExamenes().get(j).getNombre());
                añadidas.add(listaAsignaturas.get(i).getListaExamenes().get(j));
            }
        }
        int index = s.nextInt();
        System.out.println("Introduzca nota: ");
        double nota = s.nextDouble();
        añadidas.get(index - 1).asignarNota(nota);
    }

    public void imprimir() {
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            System.out.println(listaAsignaturas.get(i).toString());
        }
    }

    public ArrayList<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public double getNotaMediaCurso() {
        double sumatorioMedias = 0;
        int contador = 0;
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            if (listaAsignaturas.get(i).getNotaMedia() != -1) {
                sumatorioMedias += listaAsignaturas.get(i).getNotaMedia();
                contador++;
            }
        }
        if (contador == 0) return -1;
        return sumatorioMedias / contador;
    }

    public double getNotaSeguraObtenida() {
        double notaTotal = 0;
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            double nota = listaAsignaturas.get(i).getNotaSeguraObtenida();
            if (nota != -1) {
                notaTotal += nota;
            }
        }
        if (listaAsignaturas.size() == 0) return -1;
        return notaTotal / listaAsignaturas.size();
    }

    public double getNotaPerdida() {
        double notaTotal = 0;
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            double nota = listaAsignaturas.get(i).getNotaPerdida();
            if (nota != -1) {
                notaTotal += nota;
            }
        }
        if (listaAsignaturas.size() == 0) return 0;
        return notaTotal / listaAsignaturas.size();
    }

    public ArrayList<Examen_Trabajo> getAllExams() {
        ArrayList<Examen_Trabajo> resultado = new ArrayList<>();
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            for (int j = 0; j < listaAsignaturas.get(i).getListaExamenes().size(); j++) {
                resultado.add(listaAsignaturas.get(i).getListaExamenes().get(j));
            }
            for (int j = 0; j < listaAsignaturas.get(i).getListaTrabajos().size(); j++) {
                resultado.add(listaAsignaturas.get(i).getListaTrabajos().get(j));
            }
        }
        return resultado;
    }

    public double sumatorioPesosConNota(){
        double sumatorio = 0;
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            Asignatura a = listaAsignaturas.get(i);
            for (int j = 0; j < a.getListaExamenes().size(); j++) {
                Examen_Trabajo e = a.getListaExamenes().get(j);
                if(e.notaAsignada()) sumatorio += e.getPesoPorcentaje();
            }
            for (int j = 0; j < a.getListaTrabajos().size(); j++) {
                Examen_Trabajo e = a.getListaTrabajos().get(j);
                if(e.notaAsignada()) sumatorio += e.getPesoPorcentaje();
            }
        }
        return sumatorio;
    }
    public void guardarDatos() {
        FileWriter f;
        try {
            f = new FileWriter("BBDD.txt", false);
            for (int i = 0; i < listaAsignaturas.size(); i++) {
                f.write(listaAsignaturas.get(i).toString() + "\n");
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

    public void cargarDatos() {
        Scanner s;
        try {
            s = new Scanner(new FileReader("BBDD.txt"));
            while (s.hasNext()) {
                String lineaAsignatura = s.nextLine();
                String[] arr = lineaAsignatura.split(",");
                String nombreAsignatura = arr[0];
                int semestre = Integer.parseInt(arr[1]);
                String[] arr2 = arr[2].split(";");
                int r = Integer.parseInt(arr2[0]);
                int g = Integer.parseInt(arr2[1]);
                int b = Integer.parseInt(arr2[2]);
                Color color = new Color(r, g, b);
                double notaExtra = Double.parseDouble(arr[3]);
                Asignatura a = new Asignatura(nombreAsignatura, semestre, color, notaExtra);
                listaAsignaturas.add(a);

                String lineaExamenes = s.nextLine();
                if (!lineaExamenes.equals("SIN DATOS")) {
                    String arr3[] = lineaExamenes.split(","); //se guardan los distintos examenes
                    for (int i = 0; i < arr3.length; i++) { //distintos datos pertenecientes al mismo examen
                        String arr4[] = arr3[i].split(";");
                        String nombreExamen = arr4[0];
                        double nota = Double.parseDouble(arr4[1]);
                        double maximo = Double.parseDouble(arr4[2]);
                        double peso = Double.parseDouble(arr4[3]);
                        int dia = Integer.parseInt(arr4[4]);
                        int mes = Integer.parseInt(arr4[5]);
                        int año = Integer.parseInt(arr4[6]);
                        a.añadirExamen(nombreExamen, nota, maximo, peso, dia, mes, año);
                    }
                }

                String lineaTrabajos = s.nextLine();
                if (!lineaTrabajos.equals("SIN DATOS")) {
                    String arr3[] = lineaTrabajos.split(","); //se guardan los distintos examenes
                    for (int i = 0; i < arr3.length; i++) { //distintos datos pertenecientes al mismo examen
                        String arr4[] = arr3[i].split(";");
                        String nombreExamen = arr4[0];
                        double nota = Double.parseDouble(arr4[1]);
                        double maximo = Double.parseDouble(arr4[2]);
                        double peso = Double.parseDouble(arr4[3]);
                        int dia = Integer.parseInt(arr4[4]);
                        int mes = Integer.parseInt(arr4[5]);
                        int año = Integer.parseInt(arr4[6]);
                        a.añadirTrabajo(nombreExamen, nota, maximo, peso, dia, mes, año);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}