package gestorAplicacion.gestionFinanciera;

import java.util.ArrayList;

public class Empleado {
    Boolean trabajoCorrecto;
    int metaSemanal;
    int puntosPositivos;
    String Ocupacion;
    Float Deuda;
    ArrayList<Float> trabajoNoPagado = new ArrayList<>();
    ArrayList<Empleado> Empleados = new ArrayList<>();
    ArrayList<ArrayList<String>> Horario = new ArrayList<>();

    public Boolean ifTrabajoCorrecto(){
        return this.trabajoCorrecto;
    }
    public void setTrabajoCorrecto(){

    }
    public int getMetaSemanal(){
        return metaSemanal;
    }
    public void setMetaSemanal(int NewMeta){
        this.metaSemanal = NewMeta;
    }
    public int getPuntosPositivos(){
        return this.puntosPositivos;
    }
    public void setPuntosPositivos(int newPuntos){
        this.puntosPositivos = newPuntos;
    }
    public String getOcupacion(){
        return this.Ocupacion;
    }
    public void setOcupacion(String NewOcupacion){
        this.Ocupacion = NewOcupacion;
    }

}
