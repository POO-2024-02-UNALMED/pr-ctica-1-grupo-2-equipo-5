package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import baseDatos.Teatro;

import java.text.NumberFormat;

import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Genero;

public class Actor extends Artista{

    private static final long serialVersionUID = 1L;
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
    private ArrayList<Double> calificacionesAptitudes = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0)); // Calificaciones asociadas a las aptitudes
    private ArrayList<ArrayList<Double>> historialCalificaciones = new ArrayList<>();

    public Actor(String nombre, long id){ 
        super(nombre, id);
        actors.add(this); 
        getArtistas().add(this);
        Teatro.getInstancia().getActores().add(this);
        // Inicializar todas las aptitudes del enum Aptitud, ya que todos los actores tienen todas las aptitudes
        for (Aptitud aptitud : Aptitud.values()) {
            aptitudes.add(aptitud); 
            historialCalificaciones.add(new ArrayList<>()); // Crear espacio para las calificaciones de esa aptitud
        }
    }

    public String toString(){
        return super.getNombre() + "\nId: " + super.getId() + "\nEdad: " + this.edad + "\nCalificación: " + super.getCalificacion() + "\nPrecio de contratación: " +  formatoPrecio(this.precioContrato);
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

    public double getPrecioContrato(long horas){
        this.precioContrato = ((((Math.pow(super.getCalificacion(), 2)) / 5 ) * TASA) / 8) * horas;
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

    public static long getTasa() {
        return TASA;
    }

    public static void setActors(List<Actor> actors) {
        Actor.actors = actors;
    }

    public boolean isReevaluacion() {
        return reevaluacion;
    }

    public void setReevaluacion(boolean reevaluacion) {
        this.reevaluacion = reevaluacion;
    }

    public void setPrecioContrato(double precioContrato) {
        this.precioContrato = precioContrato;
    }

    public static NumberFormat getCop() {
        return cop;
    }

    public static void setCop(NumberFormat cop) {
        Actor.cop = cop;
    }

    public double getPrecioContrato() {
        return precioContrato;
    }

    public ArrayList<Double> getCalificacionesAptitudes() {
        return calificacionesAptitudes;
    }

    public void setCalificacionesAptitudes(ArrayList<Double> calificacionesAptitudes) {
        this.calificacionesAptitudes = calificacionesAptitudes;
    }

    public ArrayList<ArrayList<Double>> getHistorialCalificaciones() {
        return historialCalificaciones;
    }

    public void setHistorialCalificaciones(ArrayList<ArrayList<Double>> historialCalificaciones) {
        this.historialCalificaciones = historialCalificaciones;
    }

    public double getCalificacionPorAptitud(Aptitud aptitud) {
        int index = aptitudes.indexOf(aptitud);
        if (index != -1) {
            return calificacionesAptitudes.get(index);
        }
        return -1; 
    }

    // Actualizar la calificación de una aptitud específica
    public void setCalificacionPorAptitud(Aptitud aptitud, double calificacion) {
        int index = aptitudes.indexOf(aptitud);
        if (index != -1) {
            calificacionesAptitudes.set(index, calificacion);
        }
    }
    
    public List<Aptitud> obtenerAreasDeMejora() {
        // Crear una lista de índices y ordenar según las calificaciones
        List<Integer> indicesOrdenados = new ArrayList<>();
        for (int i = 0; i < aptitudes.size(); i++) {
            indicesOrdenados.add(i);
        }
    
        // Ordenar los índices basándonos en las calificaciones
        indicesOrdenados.sort((i, j) -> Double.compare(calificacionesAptitudes.get(i), calificacionesAptitudes.get(j)));
    
        // Crear una lista ordenada de aptitudes según las calificaciones
        List<Aptitud> areasDeMejora = new ArrayList<>();
        for (int index : indicesOrdenados) {
            if (getCalificacionPorAptitud(aptitudes.get(index)) <= 3.0) {
                areasDeMejora.add(aptitudes.get(index));
            }
        }
    
        return areasDeMejora;
    }

    public ArrayList<Double> getHistorialCalificaciones(Aptitud aptitud) {
        int index = aptitudes.indexOf(aptitud);
        return index != -1 ? historialCalificaciones.get(index) : null;
    }

    public void registrarCalificacion(Aptitud aptitud, double calificacion) {
        int index = aptitudes.indexOf(aptitud); // Busca la posición (índice) de la aptitud en la lista de aptitudes
        if (index != -1) { 
            historialCalificaciones.get(index).add(calificacion); // Agrega la calificación al historial correspondiente
        } else { 
            throw new IllegalArgumentException("La aptitud no existe en este actor."); // Lanza una excepción si la aptitud no se encuentra
        }
    }

    public boolean huboMejora(Aptitud aptitud) {
        ArrayList<Double> calificaciones = getHistorialCalificaciones(aptitud);
        if (calificaciones == null || calificaciones.size() < 2) return false;
        int n = calificaciones.size();
        return calificaciones.get(n - 1) > calificaciones.get(n - 2);
    }

    public boolean noHaMejoradoEnCuatroIntentos(Aptitud aptitud) {
        ArrayList<Double> calificaciones = getHistorialCalificaciones(aptitud);
        if (calificaciones == null || calificaciones.size() < 4) return false;
        int n = calificaciones.size();
        return calificaciones.get(n - 1) <= calificaciones.get(n - 2)
                && calificaciones.get(n - 2) <= calificaciones.get(n - 3)
                && calificaciones.get(n - 3) <= calificaciones.get(n - 4);
    }
    
    public boolean sigueIgual() {
        // Crear una lista con el valor inicial para comparar
        ArrayList<Double> inicial = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        return calificacionesAptitudes.equals(inicial); // Devuelve true si sigue igual
    }
}

