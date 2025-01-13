package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.Enumeraciones.*;
import gestorAplicacion.gestionVentas.*;

public class Obra {
    private int audienciaEsperada;
    private float calificacion;
    private ArrayList<Actor> reparto;
    private Director director;
    private float costoProduccion;
    private ArrayList<Funcion> funcionesSemana;
    private Genero genero;
    private int tiquetesTotales;
    private boolean estadoCriticoA;
    private static ArrayList<Obra> estadoCriticoS;
    public int getAudienciaEsperada() {
        return audienciaEsperada;
    }
    public void setAudienciaEsperada(int audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }
    public float getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }
    public ArrayList<Actor> getReparto() {
        return reparto;
    }
    public void setReparto(ArrayList<Actor> reparto) {
        this.reparto = reparto;
    }
    public Director getDirector() {
        return director;
    }
    public void setDirector(Director director) {
        this.director = director;
    }
    public float getCostoProduccion() {
        return costoProduccion;
    }
    public void setCostoProduccion(float costoProduccion) {
        this.costoProduccion = costoProduccion;
    }
    public ArrayList<Funcion> getFuncionesSemana() {
        return funcionesSemana;
    }
    public void setFuncionesSemana(ArrayList<Funcion> funcionesSemana) {
        this.funcionesSemana = funcionesSemana;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public int getTiquetesTotales() {
        return tiquetesTotales;
    }
    public void setTiquetesTotales(int tiquetesTotales) {
        this.tiquetesTotales = tiquetesTotales;
    }
    public boolean isEstadoCriticoA() {
        return estadoCriticoA;
    }
    public void setEstadoCriticoA(boolean estadoCriticoA) {
        this.estadoCriticoA = estadoCriticoA;
    }
    public static ArrayList<Obra> getEstadoCriticoS() {
        return estadoCriticoS;
    }
    public static void setEstadoCriticoS(ArrayList<Obra> estadoCriticoS) {
        Obra.estadoCriticoS = estadoCriticoS;
    }
    public Obra(int audienciaEsperada, float calificacion, ArrayList<Actor> reparto, Director director,
            float costoProduccion, ArrayList<Funcion> funcionesSemana, Genero genero, int tiquetesTotales,
            boolean estadoCriticoA) {
        this.audienciaEsperada = audienciaEsperada;
        this.calificacion = calificacion;
        this.reparto = reparto;
        this.director = director;
        this.costoProduccion = costoProduccion;
        this.funcionesSemana = funcionesSemana;
        this.genero = genero;
        this.tiquetesTotales = tiquetesTotales;
        this.estadoCriticoA = estadoCriticoA;
    }
    
    public void createFunciones(Obra obra, int numero, float calificacion) {

        Scanner sc = new Scanner(System.in);
        byte co;
        if (numero > obra.funcionesRecomendadas(calificacion) + 2){
            System.out.println("ALERTA, DEMASIADAS FUNCIONES");
            System.out.println("¿DESEA CONTINUAR?");
            co = sc.nextByte();
            switch (co) {
                case 1:
                for (int i = 0; i < numero; i++){
                    Funcion funcion = new Funcion(Obra obra, 0, ArrayList, , i + 1);
                }
                    break;
            
                default:
                    break;
            }
        }
    }

    public int funcionesRecomendadas(float calificacion){
        if (calificacion < 2){
            return 3;
        }
        if (calificacion >= 2 && calificacion < 3) {
            return 5;
        }
        if (calificacion >= 3 && calificacion < 4){
            return 7;
        }
        else{
            return 10;
        }
    }
}
