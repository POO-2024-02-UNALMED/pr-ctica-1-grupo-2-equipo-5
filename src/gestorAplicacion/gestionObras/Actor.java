package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import baseDatos.Teatro;

import java.text.NumberFormat;
import java.time.LocalDateTime;

import gestorAplicacion.gestionFinanciera.CuentaBancaria;
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
    private ArrayList<ArrayList<LocalDateTime>> tiempoActuado = new ArrayList<>();

    public Actor(String nombre, long id, int edad){ 
        super(nombre, id);
        Teatro.getInstancia().getActores().add(this); 
        Teatro.getInstancia().getArtistas().add(this); 
        // Inicializar todas las aptitudes del enum Aptitud, ya que todos los actores tienen todas las aptitudes
        for (Aptitud aptitud : Aptitud.values()) {
            aptitudes.add(aptitud); 
            historialCalificaciones.add(new ArrayList<>()); // Crear espacio para las calificaciones de esa aptitud
        }
        this.edad = edad;
        this.cuenta = new CuentaBancaria(id, 999999999999999999999999999.9);
    }

    public Actor(String nombre, long id){
        super(nombre, id);
        Teatro.getInstancia().getActores().add(this);
        Teatro.getInstancia().getArtistas().add(this);

        for (Aptitud aptitud : Aptitud.values()){
            aptitudes.add(aptitud);
            historialCalificaciones.add(new ArrayList<>());
        }
        this.cuenta = new CuentaBancaria(id, 999999999999999999999999999.9);
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

/**
 * This Java function retrieves the rating for a specific aptitude from a list of aptitudes and their
 * corresponding ratings.
 * 
 * @param aptitud The `aptitud` parameter in the `getCalificacionPorAptitud` method represents an
 * object of the `Aptitud` class. This method is designed to retrieve the calificación (rating)
 * associated with a specific `aptitud` from the `calificacionesAptitudes` list
 * @return The method `getCalificacionPorAptitud` returns a `double` value representing the
 * calificación (rating) associated with the given `Aptitud` object. If the `aptitud` is found in the
 * `aptitudes` list, the corresponding calificación is returned. Otherwise, it returns -1.
 */
    public double getCalificacionPorAptitud(Aptitud aptitud) {

        for (int i = 0; i < aptitudes.size(); i++){

            if (aptitudes.get(i) == aptitud){
                return i;
            }
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
    
/**
 * The function "obtenerAreasDeMejora" sorts a list of aptitudes based on their ratings and returns a
 * list of aptitudes that have a rating of 3.0 or lower.
 * 
 * @return The method `obtenerAreasDeMejora()` returns a List of `Aptitud` objects that represent areas
 * for improvement based on their ratings. The areas for improvement are determined by filtering out
 * the `Aptitud` objects with a rating less than or equal to 3.0.
 */
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

/**
 * The `registrarCalificacion` method registers a new rating for a specific skill in an actor's history
 * of ratings.
 * 
 * @param aptitud `aptitud` is an object of type `Aptitud`, which represents a particular skill or
 * ability. It is used to identify the specific aptitude for which a new rating is being registered in
 * the `registrarCalificacion` method.
 * @param calificacion The parameter `calificacion` in the `registrarCalificacion` method represents
 * the numerical score or rating that is being assigned to a particular `Aptitud` (aptitude) for a
 * specific actor. This score is added to the historical record of ratings for that aptitude.
 */
    public void registrarCalificacion(Aptitud aptitud, double calificacion) {
        int index = aptitudes.indexOf(aptitud); // Busca la posición (índice) de la aptitud en la lista de aptitudes
        if (index != -1) { 
            historialCalificaciones.get(index).add(calificacion); // Agrega la calificación al historial correspondiente
        } else { 
            throw new IllegalArgumentException("La aptitud no existe en este actor."); // Lanza una excepción si la aptitud no se encuentra
        }
    }

/**
 * The function "huboMejora" checks if there was an improvement in the aptitude based on the historical
 * ratings.
 * 
 * @param aptitud Aptitud is an object representing the aptitude of a person or a system. It likely
 * contains information about the performance or abilities of the individual or system.
 * @return The method `huboMejora` returns a boolean value indicating whether there was an improvement
 * in the aptitude based on the historical ratings.
 */
    public boolean huboMejora(Aptitud aptitud) {
        ArrayList<Double> calificaciones = getHistorialCalificaciones(aptitud);
        if (calificaciones == null || calificaciones.size() < 2) return false;
        int n = calificaciones.size();
        return calificaciones.get(n - 1) > calificaciones.get(n - 2);
    }

/**
 * The function checks if a certain aptitude has not improved in the last four attempts based on its
 * historical ratings.
 * 
 * @param aptitud Aptitud is an object representing the fitness or performance level of a certain
 * entity, such as a person, a machine, or a system. It typically contains information about the
 * performance metrics or scores achieved by the entity in a certain task or context.
 * @return The method `noHaMejoradoEnCuatroIntentos` returns a boolean value. It checks if the last
 * four entries in the list of `calificaciones` are in non-decreasing order. If they are in
 * non-decreasing order, it returns `true`, indicating that there has been no improvement in the last
 * four attempts. Otherwise, it returns `false`.
 */
    public boolean noHaMejoradoEnCuatroIntentos(Aptitud aptitud) {
        ArrayList<Double> calificaciones = getHistorialCalificaciones(aptitud);
        if (calificaciones == null || calificaciones.size() < 4) return false;
        int n = calificaciones.size();
        return calificaciones.get(n - 1) <= calificaciones.get(n - 2)
                && calificaciones.get(n - 2) <= calificaciones.get(n - 3)
                && calificaciones.get(n - 3) <= calificaciones.get(n - 4);
    }
    
/**
 * The Java function `sigueIgual()` checks if a list of aptitude scores is equal to a predefined
 * initial list.
 * 
 * @return The method `sigueIgual()` is returning a boolean value. It returns `true` if the list
 * `calificacionesAptitudes` is equal to the list `inicial`, which contains five elements with a value
 * of 0.0.
 */
    public boolean sigueIgual() {
        // Crear una lista con el valor inicial para comparar
        ArrayList<Double> inicial = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        return calificacionesAptitudes.equals(inicial); // Devuelve true si sigue igual
    }
// The above Java code defines a method `isDisponible` that checks if a given time slot (defined by
// `inicio` and `fin` LocalTime instances) is available or not based on the events stored in the
// `horario` list. It iterates through each event in the `horario` list and checks if the given time
// slot overlaps with any existing event. If an overlap is found, it returns `false` indicating that
// the time slot is not available (i.e., the schedule is occupied). If no overlap is found with any
// existing event, it returns `true` indicating
    public boolean isDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (ArrayList<LocalDateTime> evento : getHorario()) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        tiempoActuado.add(evento);        
        }
        return true; // Horario disponible
    }
}

