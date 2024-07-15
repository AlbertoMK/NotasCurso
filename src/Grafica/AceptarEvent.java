package Grafica;

import Modelo.Asignatura;

import java.util.EventObject;

public class AceptarEvent extends EventObject {
    private Asignatura asignatura;

    public AceptarEvent(Object source, Asignatura asignatura){
        super(source);
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }
}
