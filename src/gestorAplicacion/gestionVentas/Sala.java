package gestorAplicacion.gestionVentas;

import java.util.ArrayList;

public class Sala {
    private static ArrayList <Sala> salas = new ArrayList<>();
    private ArrayList <Silla> sillas = new ArrayList<>();
    private int numeroSala;
    private int metrosCuadrados;
    private Boolean aseado;
    private Boolean ocupado;
    private boolean aseador;


    //SILLAS
    public ArrayList<Silla> getSillas() {
        return sillas;
    }
    public void setSillas(ArrayList<Silla> sillas) {
        this.sillas = sillas;
    }

    //ASEADO
    public Boolean getAseado() {
        return aseado;
    }
    public void setAseado(Boolean aseado) {
        this.aseado = aseado;
    }

    //OCUPADO
    public Boolean getOcupado() {
        return ocupado;
    }
    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    //Metros cuadrados
    public int getMetrosCuadrados(){
        return this.metrosCuadrados;
    }
    public void setMetrosCuadrados(int newMetros){
        this.metrosCuadrados = newMetros;
    }
   
    //Numero de la Sala
    public int getNumeroSala(){
        return this.numeroSala;
    }
    public void setNumeroSala(int newNumero){
        this.numeroSala = newNumero;
    }

    //Total Salas
    public static ArrayList<Sala> getSalas(){
        return salas;
    }
    public static void setSalas(ArrayList<Sala> newSalas){
        salas = newSalas;
    }

    //Presencia Aseador
    public boolean isAseador() {
        return aseador;
    }
    public void setAseador(boolean aseador) {
        this.aseador = aseador;
    }
    
}
