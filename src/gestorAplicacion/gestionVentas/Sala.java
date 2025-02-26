package gestorAplicacion.gestionVentas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import baseDatos.Teatro;
import gestorAplicacion.herramientas.Asiento;
public class Sala implements Serializable{
    private static ArrayList <Sala> salas = new ArrayList<>();
    private ArrayList <Silla> sillas = new ArrayList<>();
    private int numeroSala;
    private double metrosCuadrados;
    private Boolean aseado;
    private Boolean ocupado;
    private ArrayList<ArrayList<LocalDateTime>> horario;
    private int capacidad;


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
    public double getMetrosCuadrados(){
        return this.metrosCuadrados;
    }
    public void setMetrosCuadrados(double newMetros){
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

    public ArrayList<ArrayList<LocalDateTime>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<ArrayList<LocalDateTime>> horario) {
        this.horario = horario;
    }
    public boolean isDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (ArrayList<LocalDateTime> evento : horario) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

/**
 * The function calculates the capacity by counting the number of elements in an ArrayList of Silla
 * objects.
 * 
 * @param sillas The method `calcCapacidad` is designed to calculate the capacity based on the number
 * of elements in the `ArrayList` of `Silla` objects provided as input. However, there seems to be a
 * logical error in the for loop condition.
 * @return The method is intended to calculate the capacity of a given ArrayList of Silla objects.
 * However, there is a logical error in the for loop condition. The loop will not execute as expected
 * because the condition `i == sillas.size()` is incorrect. It should be `i < sillas.size()` to iterate
 * over all elements in the ArrayList.
 */
    public int calcCapacidad(ArrayList<Silla> sillas){
        int u;
        u = 0;
        for (int i = 0; i == sillas.size(); i++){
            u = u + 1;
        }
        return u;    
    }

    public void anadirHorario(ArrayList<LocalDateTime> a){
        ArrayList<ArrayList<LocalDateTime>> y;
        y = getHorario();
        y.add(a);
        setHorario(y);
    }

/**
 * The function `createSillas` generates a list of `Silla` objects with different seat types based on a
 * given capacity.
 * 
 * @param capacidad The `createSillas` method you provided seems to be creating a list of `Silla`
 * objects based on the given `capacidad` parameter. However, the calculation of `u` might not be
 * accurate as it is dividing an integer by another integer, which could result in a truncated value
 * @return This method `createSillas` returns an ArrayList of Silla objects. The Silla objects are
 * created based on the input `capacidad` and are added to the ArrayList based on different seat types
 * (Asiento.GOLD, Asiento.PREMIUM, Asiento.COMFORT, Asiento.BASICO) and seat numbers. The method
 * ensures that the total number of Silla objects
 */
    public ArrayList<Silla> createSillas(int capacidad){
        float u = capacidad / 16;
        int f = 0;
        int s = 10;
        int o = 100;
        int p = 1000;
        int contador = 0;
        ArrayList<Silla> sillas = new ArrayList<>();
        for (int i = 0; i < u * 2; i++){
            Silla silla = new Silla(Asiento.GOLD, f);
            sillas.add(silla);
            f++;
            contador++;
        }
        for (int i = 0; i < u * 2; i++){
            Silla silla = new Silla(Asiento.PREMIUM, s);
            sillas.add(silla);
            s++;
            contador++;
        }
        for (int i = 0; i < u * 4; i++){
            Silla silla = new Silla(Asiento.COMFORT, o);
            sillas.add(silla);
            o++;
            contador++;
        }
        for (int i = 0; i < u * 8; i++){
            Silla silla = new Silla(Asiento.BASICO, p);
            sillas.add(silla);
            p++;
            contador++;
        }

        int t = capacidad - contador;
        for (int i = 0; i < t; i++){
            Silla silla = new Silla(Asiento.BASICO, p);
            sillas.add(silla);
            p++;
            contador++;
        }

        return sillas;
    }
    public Sala(int numeroSala,  double metrosCuadrados, int capacidad){
        sillas = createSillas(capacidad);
        this.numeroSala = numeroSala;
        this.metrosCuadrados = metrosCuadrados;
        aseado = true;
        ocupado = false;
        horario = new ArrayList<>();
        this.capacidad = capacidad;
        salas.add(this);
        Teatro.getInstancia().getSalas().add(this);
    }
    
    public Sala(ArrayList<Silla> sillas, int numeroSala, int metrosCuadrados, Boolean aseado, Boolean ocupado, ArrayList<ArrayList<LocalDateTime>> horario, int capacidad) {
        this.sillas = sillas;
        this.numeroSala = numeroSala;
        this.metrosCuadrados = metrosCuadrados;
        this.aseado = aseado;
        this.ocupado = ocupado;
        this.horario = horario;
        this.capacidad = capacidad;
    }
    public Sala(){
        salas.add(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.numeroSala);
    }
}
