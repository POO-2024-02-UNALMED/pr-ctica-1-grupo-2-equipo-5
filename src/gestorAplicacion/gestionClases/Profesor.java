package gestorAplicacion.gestionClases;

import java.util.ArrayList;
import java.util.Random;

import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.herramientas.Aptitud;

public class Profesor extends Empleado{
    private ArrayList<Aptitud> especializaciones;

    // Constructor
    public Profesor(String nombre, long id) {
        super(nombre, id, "Profesor");
        this.nombre = nombre;
        this.especializaciones = new ArrayList<>();
        // Obtener dos aptitudes aleatorias sin repetir
        Aptitud[] valores = Aptitud.values();
        Random rand = new Random();
        
        while (especializaciones.size() < 2) {
            Aptitud seleccionada = valores[rand.nextInt(valores.length)];
            if (!especializaciones.contains(seleccionada)) {
                especializaciones.add(seleccionada);
            }
        }

    }

    public Profesor(String nombre, long id, ArrayList<Aptitud> especializaciones) {
        super(nombre, id, "Profesor");
        this.especializaciones = especializaciones;
    }

    public String getNombre() {
        return super.nombre;
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
    
