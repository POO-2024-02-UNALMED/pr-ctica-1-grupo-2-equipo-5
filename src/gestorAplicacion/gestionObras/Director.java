package gestorAplicacion.gestionObras;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Teatro;
import gestorAplicacion.herramientas.*;

public class Director extends Artista{

    private Genero genero;
    private static List<Director> directors = new ArrayList<Director>(); //lista que almacenará todos los actores creados

    public Director(String nombre, long id){
        super(nombre, id);
        Teatro.getInstancia().getDirectors().add(this);
        Teatro.getInstancia().getArtistas().add(this); 
    }

    public Director(String nombre, long id, Genero genero){
        super(nombre, id);
        this.genero = genero;
        genero.anadirDirector(this);
        directors.add(this);
        getArtistas().add(this);
        Teatro.getInstancia().getDirectors().add(this);
        Teatro.getInstancia().getArtistas().add(this);
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public static List<Director> getDirectors() {
        return directors;
    }

    public static void setDirectors(List<Director> directors) {
        Director.directors = directors;
    }
    
    public String toString(){
        return "Nombre: " + this.nombre + "\n" + "Identificación: " + this.id + "\n" + "Género: " + this.genero;
    }
    public boolean isDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (ArrayList<LocalDateTime> evento : getHorario()) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }
}
