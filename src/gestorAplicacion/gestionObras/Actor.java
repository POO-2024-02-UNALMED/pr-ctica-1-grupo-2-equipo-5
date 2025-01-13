package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.List;
import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;

public class Actor extends Artista{

    
    private static final long TASA = 1_000_000;

    //lista que almacenará todos los actores creados
    private static List<Actor> actors = new ArrayList<Actor>();
    private List<String> generos = new ArrayList<String>();
    List<Float> notas = new ArrayList<Float>();
    private boolean reevaluacion = false;
    private double precioContrato;
    private int edad;
    private char sexo;

    public Actor(String nombre, long id){ 
        super(nombre, id);
        actors.add(this); 
    }

    public String toString(){
        return "Nombre: " + super.getNombre() + "\nId: " + super.getId() + "\nEdad: " + this.edad + "\nCalificación: " + super.getCalificacion();
    }


    // get/set
    public static List<Actor> getActors(){ return actors; }

    public float getPromedio(){ return super.getPromedio(); }
    public void setPromedio(float promedio){ super.setPromedio(promedio); }

    public float getCalificacion(){ return super.getCalificacion(); }
    public void setCalificacion(float calificacion){ super.setCalificacion(calificacion); }

    public List<Float> getNotas(){ return this.notas; }
    public void setNotas(List<Float> notas){ this.notas = notas;}

    public List<String> getGeneros(){ return this.generos; }
    public void setGeneros(List<String> generos){ this.generos = generos; }

    public double getPrecioContrato(){
        this.precioContrato = ((Math.pow(super.getCalificacion(), 2)) / 5 ) * TASA;
        return this.precioContrato;
    }

    public int getEdad(){ return this.edad; }
    public void setEdad(int edad){ this.edad = edad; }

    public char getSexo(){ return this.sexo; }
    public void setSexo(char sexo){ this.sexo = sexo; }

}
