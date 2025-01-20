package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.text.NumberFormat;

import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Genero;

public class Actor extends Artista{

    
    private static final long TASA = 1_000_000;

    //lista que almacenará todos los actores creados
    private static List<Actor> actors = new ArrayList<Actor>();
    private List<Genero> generos = new ArrayList<>();
    List<Float> notas = new ArrayList<Float>();
    private boolean reevaluacion = false;
    private double precioContrato;
    private int edad;
    private char sexo;
    private List <Aptitud> aptitudes = new ArrayList<>();
    public static NumberFormat cop = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public Actor(String nombre, long id){ 
        super(nombre, id);
        actors.add(this); 
        getArtistas().add(this);
    }

    public String toString(){
        return super.getNombre() + "\nId: " + super.getId() + "\nEdad: " + this.edad + "\nCalificación: " + super.getCalificacion() + "\nPrecio de contratación: " +  formatoPrecio(this.getPrecioContrato());
    }


    // get/set
    public static List<Actor> getActors(){ return actors; }

    public float getPromedio(){ return super.getPromedio(); }
    public void setPromedio(float promedio){ super.setPromedio(promedio); }

    public float getCalificacion(){ return super.getCalificacion(); }
    public void setCalificacion(float calificacion){ super.setCalificacion(calificacion); }

    public List<Float> getNotas(){ return this.notas; }
    public void setNotas(List<Float> notas){ this.notas = notas;}

    public List<Genero> getGeneros(){ return this.generos; }
    public void setGeneros(List<Genero> generos){ this.generos = generos; }
    public void addGenero(Genero genero){ this.generos.add(genero); }

    public double getPrecioContrato(){
        this.precioContrato = ((Math.pow(super.getCalificacion(), 2)) / 5 ) * TASA;
        return this.precioContrato;
    }

    public static String formatoPrecio(double cantidad){
        return cop.format(cantidad);
    }

    public int getEdad(){ return this.edad; }
    public void setEdad(int edad){ this.edad = edad; }

    public char getSexo(){ return this.sexo; }
    public void setSexo(char sexo){ this.sexo = sexo; }

    public void setAptitudes(List<Aptitud> aptitudes){ this.aptitudes = aptitudes; }
    public List<Aptitud> getAptitudes(){ return this.aptitudes; }
    public void addAptitud(Aptitud aptitud){ this.aptitudes.add(aptitud); }
    
}

