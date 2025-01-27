package gestorAplicacion.gestionClases;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.herramientas.Aptitud;

public class Profesor extends Empleado implements Serializable{
    private String nombre;
    private ArrayList<Aptitud> especializaciones;

    // Constructor
    public Profesor(String nombre, long id) {
        super(nombre, id, "Profesor");
        this.nombre = nombre;
        this.especializaciones = new ArrayList<>();
    }

    public Profesor(String nombre, long id, ArrayList<Aptitud> especializaciones) {
        super(nombre, id, "Profesor");
        this.especializaciones = especializaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Aptitud> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializacion(ArrayList<Aptitud> especializaciones) {
        this.especializaciones = especializaciones;
    }

    public void agregarEspecializacion(Aptitud aptitud) {
        if (!especializaciones.contains(aptitud)) {
            especializaciones.add(aptitud);
        }
    }

    public boolean tieneEspecializacion(Aptitud aptitud) {
        return especializaciones.contains(aptitud);
    }

    // Método para agregar puntos positivos
    public void agregarPuntos(int puntos) {
        this.puntosPositivos += puntos;
    }
}
    
