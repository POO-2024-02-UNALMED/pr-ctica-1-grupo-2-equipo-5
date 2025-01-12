package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.List;

public class Actor {

    //lista que almacenará todos los actores creados
    private static List<Actor> actors = new ArrayList<Actor>();
    private List<String> generos = new ArrayList<String>();
    private float promedio;
    List<Float> notas = new ArrayList<Float>();

    //falta agregar personaje/(?)

    public Actor(){ actors.add(this); }


    // get/set
    public static List<Actor> getActors(){ return actors; }

    public float getPromedio(){ return this.promedio; }
    public void setPromedio(float promedio){ this.promedio = promedio; }

    public List<Float> getNotas(){ return this.notas; }
    public void setNotas(ArrayList<Float> notas){ this.notas = notas;}

    public List<String> getGeneros(){ return this.generos; }
    public void setGeneros(ArrayList<String> generos){ this.generos = generos; }
    
}
