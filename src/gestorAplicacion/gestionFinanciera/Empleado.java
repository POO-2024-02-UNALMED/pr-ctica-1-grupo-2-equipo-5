package gestorAplicacion.gestionFinanciera;
import java.util.Random;
import java.time.LocalDateTime;

import gestorAplicacion.gestionObras.Artista;

import java.util.ArrayList;

public class Empleado {
    private final int salarioSeguridad = 6500;
    private final int salarioAseador = 5500;
    private final int salarioProfesor = 5500;
    private static ArrayList<Empleado> empleadosPorRendimiento = new ArrayList<>();
    private static ArrayList<Empleado> tipoSeguridad = new ArrayList<>();
    private static ArrayList<Empleado> tipoAseador = new ArrayList<>();
    private static ArrayList<Empleado> tipoProfesor = new ArrayList<>();
    private int TrabajoRealizado; //Es el total del trabajo realizado Pago
    private boolean trabajoCorrecto;
    private int metaSemanal;
    private int puntosPositivos;
    private String Ocupacion;
    private boolean disponible;
    private CuentaBancaria Cuenta;
    private float Deuda;
    private ArrayList<Float> trabajos = new ArrayList<>(); //Aqui se almacenan los trabajos que realizo ya sea que lo halla realizado o no.
    private ArrayList<ArrayList<LocalDateTime>> Horario = new ArrayList<>();

    //Metodo para establecer el horario
    public void establecerHorario(){
        
    }

    //Metodo para verificar si se hizo el trabajo bien
    public void VerificacionTrabajo(){
        Random random = new Random();
        double randomValue = random.nextDouble();
        if (randomValue > 5.0){
            this.setTrabajoCorrecto(true);
            this.puntosPositivos += 1;
        }
        else{
            this.setTrabajoCorrecto(false);
            
        }
    }

    //Calcular sueldo
    public double calcularSueldo(){
        if(this.Ocupacion == "Seguridad"){
            double Sueldo = this.TrabajoRealizado * salarioSeguridad;
            return Sueldo;
        }
        else if (this.Ocupacion == "Aseador"){
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
    //Trabajo
    public int getTrabajoRealizado() {
        return TrabajoRealizado;
    }

    public void setTrabajoRealizado(int trabajoRealizado) {
        TrabajoRealizado = trabajoRealizado;
    }

    public boolean isTrabajoCorrecto() {
        return trabajoCorrecto;
    }

    public void setTrabajoCorrecto(boolean trabajoCorrecto) {
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
        return Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
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
        return Cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        Cuenta = cuenta;
    }

    //Deuda
    public float getDeuda() {
        return Deuda;
    }

    public void setDeuda(float deuda) {
        Deuda = deuda;
    }

    //Trabajos
    public ArrayList<Float> getTrabajos() {
        return this.trabajos;
    }

    public void setTrabajos(ArrayList<Float> trabajos) {
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
        return Horario;
    }

    public void setHorario(ArrayList<ArrayList<LocalDateTime>> horario) {
        Horario = horario;
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
    
    public void casting(Artista artista) {
        artista.agregarCalificacion((int) (Math.random() * 5) + 1); // Inicializa calificaciones aleatorias
    }

}
