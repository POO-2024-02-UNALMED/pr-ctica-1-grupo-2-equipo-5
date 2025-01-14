package gestorAplicacion.gestionObras;

import gestorAplicacion.herramientas.*;

public class Director extends Artista {
    private Genero genero;

    public Director(String nombre, long id, Genero genero){
        super(nombre, id);
        this.genero = genero;
        genero.anadirDirector(this);
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    

}
