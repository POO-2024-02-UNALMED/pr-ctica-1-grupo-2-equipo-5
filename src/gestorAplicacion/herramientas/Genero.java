package gestorAplicacion.herramientas;

import gestorAplicacion.gestionObras.Director;
import java.util.ArrayList;

public enum Genero {
    DRAMA, COMEDIA, MUSICAL, FANTASIA, TERROR, ROMANCE, CIRCO, EXPERIMENTAL;

    private ArrayList<Director> directores;
    
    public ArrayList<Director> getDirectores() {
        return directores;
    }
    public void setDirectores(ArrayList<Director> directores) {
        this.directores = directores;
    }
    public void anadirDirector(Director director){
        this.directores.add(director);
    }
}
