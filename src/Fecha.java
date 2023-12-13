import java.util.ArrayList;

public class Fecha {
    private int dia,mes,año;

    public Fecha(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia){
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes){
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año){
        this.año = año;
    }

    public boolean anteriorQue(Fecha fecha){
        if(fecha.año>año) return true;
        if(año> fecha.año) return false;
        if(fecha.mes>mes) return true;
        if(mes> fecha.mes) return false;
        if(fecha.dia>dia) return true;
        if(dia>fecha.dia) return false;
        return true;
    }

    public static ArrayList<Examen_Trabajo> ordenarPorFechas(ArrayList<Examen_Trabajo> lista){
        ArrayList<Examen_Trabajo> copia = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if(!lista.get(i).notaAsignada() && lista.get(i).getAño()!=-1)
                copia.add(lista.get(i));
        }
        ArrayList<Examen_Trabajo> resultado = new ArrayList<>();
        while(copia.size()>0){
            Examen_Trabajo menor=copia.get(0);
            for (int i = 1; i < copia.size(); i++) {
                if(copia.get(i).getFecha().anteriorQue(menor.getFecha())){
                    menor = copia.get(i);
                }
            }
            resultado.add(menor);
            copia.remove(menor);
        }
        return resultado;
    }
}