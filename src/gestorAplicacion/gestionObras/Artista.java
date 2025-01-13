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
    private List<LocalDateTime[]> horario;
    private Clase clase;

    //Constructor
    public Artista(float calificacion, long id, float promedio, CuentaBancaria cuenta, Clase clase) {
        this.calificacion = calificacion;
        this.id = id;
        this.promedio = promedio;
        this.cuenta = cuenta;
        this.horario = new ArrayList<>();
        this.clase = clase;
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
    public List<LocalDateTime[]> getHorario() {
        return horario;
    }
    public void setHorario(List<LocalDateTime[]> horario) {
        this.horario = horario;
    }

    //Clase
    public Clase getClase() {
        return clase;
    }
    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
