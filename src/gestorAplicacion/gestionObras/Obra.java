package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalTime;

import gestorAplicacion.herramientas.*;
import gestorAplicacion.gestionVentas.*;

public class Obra {
    private int audienciaEsperada;
    private String nombre;
    private float calificacion;
    private ArrayList<Actor> reparto;
    private Director director;
    private float costoProduccion;
    private ArrayList<Funcion> funcionesSemana;
    private Genero genero;
    private int tiquetesTotales;
    private boolean estadoCriticoA;
    private static ArrayList<Obra> estadoCriticoS;
    private ArrayList<Float> calificaciones;
    private ArrayList<Obra> obras;
    private ArrayList<LocalDateTime> franjaHoraria;
    private Duration duracion;
    private Funcion funcionEstelar;
    private ArrayList<Funcion> funciones;

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

    public void calcAudienciaEsperada(float calificacion){
        int u;
        u = (int) calificacion * 12;
        this.setAudienciaEsperada(u);
    }
    public void calcularCalificacion(ArrayList<Float> calificaciones){
        float u;
        int t;
        float v;
        u = 0;
        t = 0;
        for (float calificacion : calificaciones){
            u = u + calificacion;
            t ++;
        }
        v = u / t;
        setCalificacion(v);
    }
    public void agregarCalificacion(float calificacion){
        this.calificaciones.add(calificacion);
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Float> getCalificaciones() {
        return calificaciones;
    }
    public void setCalificaciones(ArrayList<Float> calificaciones) {
        this.calificaciones = calificaciones;
    }
    public void franjaHoraria(Genero genero){
        Genero u;
        Funcion a;
        ArrayList<LocalTime> franja;
        franja.add(LocalTime.of(24,00));
        franja.add(LocalTime.of(00,00)));
        ArrayList<Obra> obrasGenero = new ArrayList<>();
        for (Obra obra : obras){
            u = obra.getGenero();
            if (u == genero){
                obrasGenero.add(obra);
            }
        }
        for (Obra obra: obrasGenero){
            a = obra.funcionEstelar;
            a.extraerHora(franjaHoraria);
        }
    }
    public String getDuracionFormato() {
        long horas = duracion.toHours();
        long minutos = duracion.toMinutes() % 60;
        return String.valueOf(horas) + String.valueOf(minutos);
    }
    public ArrayList<Obra> getObras() {
        return obras;
    }
    public void setObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }
    public ArrayList<LocalDateTime> getFranjaHoraria() {
        return franjaHoraria;
    }
    public void setFranjaHoraria(ArrayList<LocalDateTime> franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
    public Duration getDuracion() {
        return duracion;
    }
    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public void calcFuncionEstelar(ArrayList<Funcion> funciones){
        Funcion u = new Funcion();
        Funcion v = new Funcion();
        int s;
        u.setTiquetesVendidos(0);
        s = u.getTiquetesVendidos();
        int d;
        for (Funcion funcion : funciones){
            d = funcion.getTiquetesVendidos();
            if (s < d){
                d = s;
                v = funcion;
            }
        }
        this.setFuncionEstelar(v);
    }
    public Funcion getFuncionEstelar() {
        return funcionEstelar;
    }
    public void setFuncionEstelar(Funcion funcionEstelar) {
        this.funcionEstelar = funcionEstelar;
    }
    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }
    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = funciones;
    }
}
