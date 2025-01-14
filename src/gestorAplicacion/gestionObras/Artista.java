package gestorAplicacion.gestionObras;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;

public abstract class Artista {
    private float calificacion;
    private long id;
    private float promedio;
    private CuentaBancaria cuenta;
    private ArrayList<ArrayList<LocalDateTime>> horario;
    private Clase clase;
    private String nombre;
    private ArrayList<Float> calificaciones = new ArrayList<>();

    //Constructor
    public Artista(float calificacion, long id, float promedio, CuentaBancaria cuenta, Clase clase) {
        this.calificacion = calificacion;
        this.id = id;
        this.promedio = promedio;
        this.cuenta = cuenta;
        this.horario = new ArrayList<>(); //Lista donde se guarda el horario del artista
        this.clase = clase;
    }

    public Artista(String nombre, long id){
        this.nombre = nombre;
        this.id = id;
    }
    
    //GETTERS Y SETTERS

    //Calificacion
    public float getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    //ID
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //Promedio
    public float getPromedio() {
        return promedio;
    }
    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    //Cuenta
    public CuentaBancaria getCuenta() {
        return cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    //Horario
    public ArrayList<ArrayList<LocalDateTime>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<ArrayList<LocalDateTime>> horario) {
        this.horario = horario;
    }

    //Clase
    public Clase getClase() {
        return clase;
    }
    public void setClase(Clase clase) {
        this.clase = clase;
    }

    //nombre
    public String getNombre(){ return this.nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    

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

    public ArrayList<Float> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Float> calificaciones) {
        this.calificaciones = calificaciones;
    }
    public void agregarCalificacion(float calificacion){
        this.calificaciones.add(calificacion);
    }
    
    public boolean isDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (ArrayList<LocalDateTime> evento : horario) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }
}
