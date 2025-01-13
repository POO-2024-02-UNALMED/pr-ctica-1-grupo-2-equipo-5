package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private static ArrayList <Sala> salas = new ArrayList<>();
    private ArrayList <Silla> sillas = new ArrayList<>();
    private int numeroSala;
    private int metrosCuadrados;
    private Boolean aseado;
    private Boolean ocupado;
    private boolean aseador;
    private ArrayList<List<LocalDateTime[]>> horario;


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
    public ArrayList<List<LocalDateTime[]>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<List<LocalDateTime[]>> horario) {
        this.horario = horario;
    }
    public boolean estaDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (List<LocalDateTime[]> full : horario) {
            for (LocalDateTime[] evento : full)
                if (inicio.isBefore(evento[1]) && fin.isAfter(evento[0])) {
                    return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }
}
