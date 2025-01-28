package gestorAplicacion.gestionObras;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import baseDatos.Teatro;
import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;

public abstract class Artista implements Serializable{
    private float calificacion;
    protected long id;
    private float promedio;
    protected CuentaBancaria cuenta;
    private ArrayList<ArrayList<LocalDateTime>> horario;
    private Clase clase;
    protected String nombre;
    private ArrayList<Float> calificaciones = new ArrayList<>();
    private ArrayList<Float> calificacionesPublico = new ArrayList<>();
    private static ArrayList<Artista> Artistas = new ArrayList<>();


    //Constructor
    public Artista(float calificacion, long id, float promedio, CuentaBancaria cuenta, Clase clase) {
        this.calificacion = calificacion;
        this.id = id;
        this.promedio = promedio;
        this.cuenta = new CuentaBancaria(id, 0);
        this.horario = new ArrayList<>(); //Lista donde se guarda el horario del artista
        this.clase = clase;
    }

    public Artista(String nombre, long id){
        this.nombre = nombre;
        this.horario = new ArrayList<>();
        this.id = id;
    }
    
    //GETTERS Y SETTERS

    //Calificacion
    public float getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    //ID
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //Promedio
    public float getPromedio() {
        return promedio;
    }
    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    //Cuenta
    public CuentaBancaria getCuenta() {
        return cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    //Horario
    public ArrayList<ArrayList<LocalDateTime>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<ArrayList<LocalDateTime>> horario) {
        this.horario = horario;
    }
    public void addHorario(ArrayList<LocalDateTime> horario){ this.horario.add(horario); }

    //Clase
    public Clase getClase() {
        return clase;
    }
    public void setClase(Clase clase) {
        this.clase = clase;
    }

    //nombre
    public String getNombre(){
        return this.nombre; 
    }
    public void setNombre(String nombre){
        this.nombre = nombre; 
    }

    //Arraylist artistas
    public static ArrayList<Artista> getArtistas() {
        return Artistas;
    }

    public static void setArtistas(ArrayList<Artista> artistas) {
        Artistas = artistas;
    }
    

/**
 * The function calculates the average of a list of floating-point numbers and sets the result as a new
 * value.
 * 
 * @param calificaciones The method `calcularCalificacion` calculates the average of a list of grades
 * stored in an ArrayList of Float values named `calificaciones`. The method iterates through each
 * grade in the list, sums them up, and then divides the total sum by the number of grades to obtain
 * the average. Finally
 */
    public void calcularCalificacion(ArrayList<Float> calificaciones){
        float u;
        int t;
        float v;
        u = 0;
        t = 0;
        for (float calificacion : calificaciones){
            u = u + calificacion;
            t ++;
        }
        v = u / t;
        setCalificacion(v);
    }

    //Calificaciones del público
    public ArrayList<Float> getCalificacionesPublico() {
        return calificacionesPublico;
    }

    public void setCalificacionesPublico(ArrayList<Float> calificacionesPublico) {
        this.calificacionesPublico = calificacionesPublico;
    }

    public void agregarCalificacionPublico(float calificacion) {
        this.calificacionesPublico.add(calificacion);
    }

    //Calificaciones
    public ArrayList<Float> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Float> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public void agregarCalificacion(float calificacion){
        this.calificaciones.add(calificacion);
    }
    
    public abstract boolean isDisponible(LocalDateTime inicio, LocalDateTime fin);

/**
 * The function searches for an artist by their ID within a collection of artists and returns the
 * artist if found, otherwise returns null.
 * 
 * @param id The `id` parameter is the unique identifier used to search for an artist in the list of
 * artists. The method `buscarArtistaPorId` iterates through the list of artists and returns the artist
 * whose ID matches the provided `id`. If no artist is found with the given ID, the
 * @return The method `buscarArtistaPorId` returns an `Artista` object if an artist with the specified
 * ID is found in the list of artists. If no artist is found with that ID, the method returns `null`.
 */
    public static Artista buscarArtistaPorId(long id) {
        for (Artista artista : Teatro.getInstancia().getArtistas()) {
            if (artista.getId() == id) {
                return artista; // Retorna el artista si coincide el ID
            }
        }
        return null; // Retorna null si no encuentra un artista con ese ID
    }

    // Método para inicializar calificaciones del público
/**
 * The function initializes public ratings for an artist by generating 5 simulated ratings.
 * 
 * @param artista The parameter `artista` is an object of type `Artista`, which likely represents an
 * artist in a system or application. The method `inicializarCalificacionesPublico` is a public method
 * that initializes the public ratings for the artist by generating 5 simulated ratings and adding them
 * to the
 */
    public void inicializarCalificacionesPublico(Artista artista) {
        for (int i = 0; i < 5; i++) { // Generar 5 calificaciones simuladas
            artista.agregarCalificacionPublico((float) (Math.round(Math.random() * 50) / 10.0));
        }
    }
}