package gestorAplicacion.gestionClases;

import gestorAplicacion.gestionFinanciera.Empleado;

public class Profesor extends Empleado {
    private String nombre;

    // Constructor
    public Profesor(String nombre, long id) {
        super(nombre, id); 
        this.nombre = nombre;
        Empleado.getTipoProfesor().add(this); // Añade el profesor a la lista de Empleado
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
    
