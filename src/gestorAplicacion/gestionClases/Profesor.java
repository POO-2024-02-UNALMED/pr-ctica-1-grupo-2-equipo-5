package gestorAplicacion.gestionClases;

import java.util.ArrayList;

import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.herramientas.Aptitud;

public class Profesor extends Empleado {
    private String nombre;
    private ArrayList<Aptitud> especializaciones;

    // Constructor
    public Profesor(String nombre, long id) {
        super();
        this.nombre = nombre;
        this.especializaciones = new ArrayList<>();
        Empleado.getTipoProfesor().add(this); // Añade el profesor a la lista de Empleado
    }

    public Profesor(String nombre, long id, ArrayList<Aptitud> especializaciones) {
        super();
        this.nombre = nombre;
        this.especializaciones = especializaciones;
        Empleado.getTipoProfesor().add(this); // Añade el profesor a la lista de Empleado
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
}
    
