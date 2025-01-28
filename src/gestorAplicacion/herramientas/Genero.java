package gestorAplicacion.herramientas;

import gestorAplicacion.gestionObras.Director;
import java.util.ArrayList;

import baseDatos.Teatro;

public enum Genero {
    DRAMA, COMEDIA, MUSICAL, FANTASIA, TERROR, ROMANCE, CIRCO, EXPERIMENTAL;

    private ArrayList<Director> directores = new ArrayList<>();
    
    public ArrayList<Director> getDirectores() {
        ArrayList<Director> dirgenero = new ArrayList<>();
        for (Director director : Teatro.getInstancia().getDirectors()){
            if (director.getGenero() == this){
                dirgenero.add(director);
            }
        }
        return dirgenero;
    }
    public void setDirectores(ArrayList<Director> directores) {
        this.directores = directores;
    }
    public void anadirDirector(Director director){
        this.directores.add(director);
    }
}
