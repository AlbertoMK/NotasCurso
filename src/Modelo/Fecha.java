package Modelo;

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

    public String toString(){
        return String.format("%d / %d / %d",dia, mes, año);
    }
}
