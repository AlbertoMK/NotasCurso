package Modelo;

import Logica.C_Asignatura;
import Logica.C_Evaluable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Periodo {
    private String nombre;
    private HashMap<Integer, List<Integer>> asignatura_evaluables;
    private HashMap<Integer, Double> asignatura_notaExtra;

    public Periodo(String nombre) {
        this.nombre = nombre;
        asignatura_evaluables = new HashMap<>();
        asignatura_notaExtra = new HashMap<>();
    }

    public Periodo(String nombre, HashMap<Integer, List<Integer>> asignatura_evaluables, HashMap<Integer, Double> asignatura_notaExtra) {
        this.nombre = nombre;
        this.asignatura_evaluables = asignatura_evaluables;
        this.asignatura_notaExtra = asignatura_notaExtra;
    }

    public Set<Integer> getAsignaturas() {
        return asignatura_evaluables.keySet();
    }

    public String getNombre() {
        return nombre;
    }

    public void añadirAsignatura(Asignatura asignatura) {
        asignatura_evaluables.put(Integer.valueOf(asignatura.getId()), new ArrayList<>());
        asignatura_notaExtra.put(Integer.valueOf(asignatura.getId()), Double.valueOf(0));
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        List<Integer> evaluables = asignatura_evaluables.get(asignatura.getId());
        for (Integer i : evaluables) {
            C_Evaluable.getInstance().eliminarEvaluable(i);
        }
        asignatura_evaluables.remove(Integer.valueOf(asignatura.getId()));
        asignatura_notaExtra.remove(Integer.valueOf(asignatura.getId()));
    }

    public void añadirEvaluable(Asignatura asignatura, Evaluable evaluable) {
        asignatura_evaluables.get(asignatura.getId()).add(evaluable.getId());
    }

    public void eliminarEvaluable(Asignatura asignatura, Evaluable evaluable) {
        //Importante mantener el Integer.valueOf porque sino te elimina el index del parámetro en vez del objeto, que es lo esperado en este caso.
        asignatura_evaluables.get(asignatura.getId()).remove(Integer.valueOf(evaluable.getId()));
    }

    public void setNotaExtra(Asignatura asignatura, double valor) {
        asignatura_notaExtra.put(asignatura.getId(), valor);
    }

    public boolean contieneEvaluableConNombreEnAsignatura(Asignatura asignatura, String nombreEvaluable) {
        List<Integer> idEvaluables = asignatura_evaluables.get(asignatura.getId());
        for (Integer id : idEvaluables) {
            if (C_Evaluable.getInstance().getEvaluableById(id).getNombre().equals(nombreEvaluable))
                return true;
        }
        return false;
    }

    public double getNotaMediaAsignatura(Asignatura asignatura) {
        double sumatorioPesos = 0;
        double sumatorioNotaPorPeso = 0;
        List<Integer> evaluablesId = asignatura_evaluables.get(asignatura.getId());
        double notaExtra = asignatura_notaExtra.get(asignatura.getId());
        for (Integer id : evaluablesId) {
            Evaluable evaluable = C_Evaluable.getInstance().getEvaluableById(id);
            if (evaluable.getNota() != -1) {
                sumatorioNotaPorPeso += evaluable.getNotaSobre10() * (evaluable.getPesoPorcentaje() / 100);
                sumatorioPesos += evaluable.getPesoPorcentaje();
            }
        }
        if (sumatorioPesos == 0)
            return -1;
        double media = sumatorioNotaPorPeso * 100 / sumatorioPesos;
        media += notaExtra;
        return media;
    }

    public HashMap<Asignatura, Double> getMediasAsignaturas() {
        HashMap<Asignatura, Double> medias = new HashMap<>();
        for (Integer id : asignatura_evaluables.keySet()) {
            Asignatura a = C_Asignatura.getInstance().getAsignaturaById(id);
            medias.put(a, getNotaMediaAsignatura(a));
        }
        return medias;
    }

    //Devuelve la nota media del periodo teniendo en cuenta los creéditos de cada asignatura.
    public double getNotaMediaPeriodo() {
        double numCreditos = 0;
        double sumatorioNotas = 0;
        for (Integer id : asignatura_evaluables.keySet()) {
            Asignatura asignatura = C_Asignatura.getInstance().getAsignaturaById(id);
            double nota = getNotaMediaAsignatura(asignatura);
            if (nota != -1) {
                numCreditos += asignatura.getCreditos();
                sumatorioNotas += nota * asignatura.getCreditos();
            }
        }
        if (numCreditos == 0)
            return -1;
        else
            return sumatorioNotas / numCreditos;
    }

    public double getNotaSeguraObtenidaAsignatura(Asignatura asignatura) {
        double resulado = 0;
        List<Integer> lista = asignatura_evaluables.get(asignatura.getId());
        for (Integer id : lista) {
            Evaluable evaluable = C_Evaluable.getInstance().getEvaluableById(id);
            if (evaluable.notaAsignada()) {
                resulado += evaluable.getNotaSobre10() * (evaluable.getPesoPorcentaje() / 100);
            }
        }

        resulado += asignatura_notaExtra.get(asignatura.getId());
        return resulado;
    }

    // Devuelve la nota segura obtenida teniendo en cuenta los créditos de cada asignatura
    public double getNotaSeguraObtenidaPeriodo() {
        if (asignatura_evaluables.keySet().size() == 0)
            return 0;
        double numCreditos = 0;
        double sumatorio = 0;
        for (Integer id : asignatura_evaluables.keySet()) {
            Asignatura asignatura = C_Asignatura.getInstance().getAsignaturaById(id);
            numCreditos += asignatura.getCreditos();
            if (getNotaSeguraObtenidaAsignatura(asignatura) != -1) {
                sumatorio += getNotaSeguraObtenidaAsignatura(asignatura) * asignatura.getCreditos();
            }
        }
        return sumatorio / numCreditos;
    }

    public double getNotaPerdidaAsignatura(Asignatura asignatura) {
        double resulado = 0;
        List<Integer> lista = asignatura_evaluables.get(asignatura.getId());
        for (Integer id : lista) {
            Evaluable evaluable = C_Evaluable.getInstance().getEvaluableById(id);
            if (evaluable.getNota() != -1) {
                resulado += (10 - evaluable.getNotaSobre10()) * (evaluable.getPesoPorcentaje() / 100);
            }
        }

        resulado -= asignatura_notaExtra.get(asignatura.getId());
        if (resulado < 0) resulado = 0;
        return resulado;
    }

    // Devuelve la nota perdida del periodo teniendo en cuenta los créditos de cada asignatura.
    public double getNotaPerdidaPeriodo() {
        if (asignatura_evaluables.keySet().size() == 0)
            return 0;
        double numCreditos = 0;
        double sumatorio = 0;
        for (Integer id : asignatura_evaluables.keySet()) {
            Asignatura asignatura = C_Asignatura.getInstance().getAsignaturaById(id);
            numCreditos += asignatura.getCreditos();
            sumatorio += getNotaPerdidaAsignatura(asignatura) * asignatura.getCreditos();
        }
        return sumatorio / numCreditos;
    }

    public double getSumaPesos(Asignatura asignatura) {
        double sumatorio = 0;
        List<Integer> lista = asignatura_evaluables.get(asignatura.getId());
        for (Integer idEvaluable : lista) {
            sumatorio += C_Evaluable.getInstance().getEvaluableById(idEvaluable).getPesoPorcentaje();
        }
        return sumatorio;
    }

    public double getSumaCreditos(){
        double suma = 0;
        for (Integer idA : asignatura_evaluables.keySet()) {
            suma += C_Asignatura.getInstance().getAsignaturaById(idA).getCreditos();
        }
        return suma;
    }

    public double getPorcentajeProgresoPeriodo() {
        if (getNumAsignaturas() == 0)
            return 0;
        else return getSumatorioPesosConNota() / getNumAsignaturas();
    }

    public double getPorcentajeProgresoAsignatura(Asignatura asignatura) {
        List<Integer> evaluables = asignatura_evaluables.get(asignatura.getId());
        double sumatorioPesos = 0;
        for (Integer i : evaluables) {
            Evaluable actual = C_Evaluable.getInstance().getEvaluableById(i);
            if (actual.notaAsignada())
                sumatorioPesos += actual.getPesoPorcentaje();
        }
        return sumatorioPesos;
    }

    public double getSumatorioPesosConNota() {
        double sumatorio = 0;
        for (Integer idAsigantura : asignatura_evaluables.keySet()) {
            sumatorio += getSumaPesosConNotaAsignatura(C_Asignatura.getInstance().getAsignaturaById(idAsigantura));
        }
        return sumatorio;
    }

    private double getSumaPesosConNotaAsignatura(Asignatura asignatura) {
        double sumatorio = 0;
        List<Integer> lista = asignatura_evaluables.get(asignatura.getId());
        for (Integer idEvaluable : lista) {
            if (C_Evaluable.getInstance().getEvaluableById(idEvaluable).notaAsignada())
                sumatorio += C_Evaluable.getInstance().getEvaluableById(idEvaluable).getPesoPorcentaje();
        }
        return sumatorio;
    }

    public double getNotaExtraAsignatura(Asignatura asignatura) {
        return asignatura_notaExtra.get(asignatura.getId());
    }

    public List<Integer> getEvaluables(Asignatura asignatura) {
        return asignatura_evaluables.get(asignatura.getId());
    }

    public int getNumAsignaturas() {
        return asignatura_evaluables.keySet().size();
    }

    public boolean containsAsignatura(Asignatura a) {
        return asignatura_evaluables.keySet().contains(a.getId());
    }

    public int getNumAsignaturasConNota() {
        int contador = 0;
        for (int id : asignatura_evaluables.keySet()) {
            if (getNotaMediaAsignatura(C_Asignatura.getInstance().getAsignaturaById(id)) != -1)
                contador++;
        }
        return contador;
    }

    public Evaluable getEvaluable(Asignatura asignatura, String nombreEvaluable) {
        C_Evaluable ce = C_Evaluable.getInstance();
        List<Integer> idEvaluables = asignatura_evaluables.get(asignatura.getId());
        for (Integer id : idEvaluables) {
            if (ce.getEvaluableById(id).getNombre().equals(nombreEvaluable))
                return ce.getEvaluableById(id);
        }
        return null;
    }

    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(nombre + "_");
        for (Integer clave : asignatura_evaluables.keySet()) {
            List<Integer> lista = asignatura_evaluables.get(clave);
            resultado.append(clave + ":");
            for (Integer id : lista) {
                resultado.append(id + ":");
            }
            resultado.append(",");
        }
        resultado.append(";");
        for (Integer clave : asignatura_evaluables.keySet()) {
            resultado.append(clave + ":" + asignatura_notaExtra.get(clave) + ",");
        }
        return resultado.toString();
    }
}
