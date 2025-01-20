package gestorAplicacion.gestionObras;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionClases.Profesor;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;
import gestorAplicacion.gestionFinanciera.Empleado;
import uiMain.Main;

public abstract class Artista {
    private float calificacion;
    protected long id;
    private float promedio;
    private CuentaBancaria cuenta;
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
        this.cuenta = cuenta;
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
    
    public boolean isDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (ArrayList<LocalDateTime> evento : horario) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }

    public static Artista buscarArtistaPorId(long id) {
        for (Artista artista : Artista.getArtistas()) {
            if (artista.getId() == id) {
                return artista; // Retorna el artista si coincide el ID
            }
        }
        return null; // Retorna null si no encuentra un artista con ese ID
    }

    public void mostrarCalificacionesOInicializar(Artista artista) throws InterruptedException{
        // Validar si el artista es nuevo (sin calificaciones en ambas listas)
        if (artista.getCalificaciones().isEmpty()) {
            Main.customPrint("El artista es nuevo. Inicializando calificaciones...");
            Thread.sleep(2000);

            // Llamar al método casting() para inicializar calificaciones de calificadores
            Empleado.casting(artista, Empleado.getTipoProfesor());
    
            // Inicializar calificaciones del público (simuladas aleatoriamente)
            inicializarCalificacionesPublico(artista);
    
            Main.customPrint("Calificaciones inicializadas exitosamente.", "green");
        }
    
        // Mostrar las calificaciones del artista
        Main.customPrint("Calificaciones del artista: " + artista.getNombre());
        Main.customPrint("Calificaciones de calificadores: " + artista.getCalificaciones());
        Main.customPrint("Calificaciones del público: " + artista.getCalificacionesPublico());
    }
    
    // Método para inicializar calificaciones del público
    private void inicializarCalificacionesPublico(Artista artista) {
        for (int i = 0; i < 5; i++) { // Generar 5 calificaciones simuladas
            artista.agregarCalificacionPublico((int) (Math.random() * 5) + 1);
        }
    }
}
