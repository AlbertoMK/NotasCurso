import Grafica.Ventana;
import Logica.C_Asignatura;
import Logica.C_Evaluable;
import Modelo.Curso;

public class App {
    public static void main(String[] args) {
        C_Evaluable.getInstance().cargar();
        C_Asignatura.getInstance().cargar();
        Curso.getInstance().cargar();
        Ventana ventana = new Ventana();
    }
}
