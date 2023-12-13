import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Curso curso = Curso.getInstance();
        curso.cargarDatos();
        Pantalla.getInstance();
    }
}