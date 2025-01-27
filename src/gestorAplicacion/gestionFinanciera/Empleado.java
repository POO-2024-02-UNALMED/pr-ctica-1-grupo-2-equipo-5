package gestorAplicacion.gestionFinanciera;
import java.io.Serializable;
import java.time.LocalDateTime;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.herramientas.Aptitud;

import java.util.ArrayList;

public class Empleado implements Serializable{
    private String nombre;
    private Long id;
    private final int salarioSeguridad = 6500;
    private final int salarioAseador = 5500;
    private final int salarioProfesor = 5500;
    private static ArrayList<Empleado> empleadosPorRendimiento = new ArrayList<>();
    private static ArrayList<Empleado> tipoSeguridad = new ArrayList<>();
    private static ArrayList<Empleado> tipoAseador = new ArrayList<>();
    private static ArrayList<Empleado> tipoProfesor = new ArrayList<>();
    private double TrabajoRealizado; //Es el total del trabajo realizado Pago
    private ArrayList<Boolean> trabajoCorrecto = new ArrayList<>();
    private int metaSemanal;
    protected int puntosPositivos;
    private String ocupacion;
    private boolean disponible;
    private CuentaBancaria cuenta;
    private double deuda;
    private ArrayList<Double> trabajos = new ArrayList<>(); //Aqui se almacenan los trabajos que realizo ya sea que fue correcto o no.
    private ArrayList<ArrayList<LocalDateTime>> horario = new ArrayList<>();

    //Constructor
    public Empleado(String nombre, long ID, String ocupacion) {
        this.nombre = nombre;
        this.id = ID;
        this.metaSemanal = 6;
        this.cuenta = new CuentaBancaria(ID, 0);
        this.ocupacion = ocupacion;
        this.deuda = 0;
        this.horario = new ArrayList<>(); //Lista donde se guarda el horario 
        empleadosPorRendimiento.add(this);
    }

    //Constructor para funcionalidad 4

    public Empleado() {}

    //Calcular sueldo
    public double calcularSueldo(){
        if(this.ocupacion == "Seguridad"){
            double Sueldo = this.TrabajoRealizado * salarioSeguridad;
            return Sueldo;
        }
        else if (this.ocupacion == "Aseador"){
            double Sueldo = this.TrabajoRealizado * salarioAseador;
            return Sueldo;
        }
        else {
            double Sueldo = this.TrabajoRealizado * salarioProfesor;
            return Sueldo;
        }
    }

    public boolean verificacionMeta(){
        if (this.TrabajoRealizado >= metaSemanal) {
            return true;
        }
        else{
            return false;
        }
    }

    //Setter and Getters
    //Info
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Trabajo
    public double getTrabajoRealizado() {
        return TrabajoRealizado;
    }

    public void setTrabajoRealizado(double trabajoRealizado) {
        TrabajoRealizado = trabajoRealizado;
    }

    public ArrayList<Boolean> getTrabajoCorrecto() {
        return trabajoCorrecto;
    }

    public void setTrabajoCorrecto(ArrayList<Boolean> trabajoCorrecto) {
        this.trabajoCorrecto = trabajoCorrecto;
    }

    //Meta
    public int getMetaSemanal() {
        return metaSemanal;
    }

    public void setMetaSemanal(int metaSemanal) {
        this.metaSemanal = metaSemanal;
    }

    //Puntos
    public int getPuntosPositivos() {
        return puntosPositivos;
    }

    public void setPuntosPositivos(int puntosPositivos) {
        this.puntosPositivos = puntosPositivos;
    }

    //Ocupacion
    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    //Disponibilidad
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    //Cuenta Bancaria
    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    //Deuda
    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    //Trabajos
    public ArrayList<Double> getTrabajos() {
        return this.trabajos;
    }

    public void setTrabajos(ArrayList<Double> trabajos) {
        this.trabajos = trabajos;
    }

    //Empleados
    public static ArrayList<Empleado> getEmpleadosPorRendimiento() {
        return empleadosPorRendimiento;
    }

    public static void setEmpleadosPorRendimiento(ArrayList<Empleado> newEmpleadosPorRendimiento) {
        empleadosPorRendimiento = newEmpleadosPorRendimiento;
    }
    
    public static ArrayList<Empleado> getTipoSeguridad() {
        return tipoSeguridad;
    }

    public static void setTipoSeguridad(ArrayList<Empleado> tipoSeguridad) {
        Empleado.tipoSeguridad = tipoSeguridad;
    }

    public static ArrayList<Empleado> getTipoAseador() {
        return tipoAseador;
    }

    public static void setTipoAseador(ArrayList<Empleado> tipoAseador) {
        Empleado.tipoAseador = tipoAseador;
    }

    public static ArrayList<Empleado> getTipoProfesor() {
        return tipoProfesor;
    }

    public static void setTipoProfesor(ArrayList<Empleado> tipoProfesor) {
        Empleado.tipoProfesor = tipoProfesor;
    }

    //Horario
    public ArrayList<ArrayList<LocalDateTime>> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<ArrayList<LocalDateTime>> horario) {
        this.horario = horario;
    }

    //Salarios
    public int getSalarioSeguridad(){
        return this.salarioSeguridad;
    }
    public int getSalarioAseador(){
        return this.salarioAseador;
    }
    public int getSalarioProfesor(){
        return this.salarioProfesor;
    }
    
    public static boolean casting(Artista artista, ArrayList<Empleado> profesores) {
        if (!(artista instanceof Actor)) {
        return false; // Solo aplicable a actores
        }
        if (profesores == null || profesores.isEmpty()) {
            return false;
        }

        Aptitud[] aptitud = Aptitud.values();
        // Generar calificaciones aleatorias
        for (int i = 0; i < 5; i++) { // Por ejemplo, 5 calificaciones iniciales   //
            ((Actor)artista).setCalificacionPorAptitud(aptitud[i], (double) Math.round(Math.random() * 50) / 10.0);
        }
        return true;
    }
}