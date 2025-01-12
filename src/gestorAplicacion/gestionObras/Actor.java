package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.List;

public class Actor extends Artista{

    
    private static final long TASA = 1_000_000;

    //lista que almacenará todos los actores creados
    private static List<Actor> actors = new ArrayList<Actor>();
    private List<String> generos = new ArrayList<String>();
    List<Float> notas = new ArrayList<Float>();
    private boolean reevaluacion = false;
    private double precioContrato;
    private int edad;
    private String sexo;

    //falta agregar personaje/(?)

    public Actor(){ 
        actors.add(this); 
        this.calificacion = 2.5f;
        getPrecioContrato();
    }


    // get/set
    public static List<Actor> getActors(){ return actors; }

    public float getPromedio(){ return this.promedio; }
    public void setPromedio(float promedio){ this.promedio = promedio; }

    public float getCalificacion(){ return this.calificacion; }
    public void setCalificacion(float calificacion){ this.calificacion = calificacion; }

    public List<Float> getNotas(){ return this.notas; }
    public void setNotas(List<Float> notas){ this.notas = notas;}

    public List<String> getGeneros(){ return this.generos; }
    public void setGeneros(List<String> generos){ this.generos = generos; }

    public double getPrecioContrato(){
        this.precioContrato = ((Math.pow(calificacion, 2)) / 5 ) * TASA;
        return this.precioContrato;
    }

    public int getEdad(){ return this.edad; }
    public void setEdad(int edad){ this.edad = edad; }

    public String getSexo(){ return this.sexo; }
    public void setSexo(String sexo){ this.sexo = sexo; }
    
}
