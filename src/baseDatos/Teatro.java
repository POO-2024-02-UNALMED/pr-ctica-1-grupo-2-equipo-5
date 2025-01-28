package baseDatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Cliente;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionVentas.Tiquete;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionObras.Director;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;


public class Teatro implements Serializable{
    
    private final long serialVersionUID = 1L;
    private static Teatro instancia;
    private Tesoreria tesoreria = new Tesoreria(0, 5000000);

    private Sala sala1 = new Sala(1, 100, 24);

    //empleados
    private ArrayList<Empleado> empleadosPorRendimiento = new ArrayList<>();
    private ArrayList<Empleado> tipoSeguridad = new ArrayList<>();
    private ArrayList<Empleado> tipoAseador = new ArrayList<>();
    private ArrayList<Empleado> tipoProfesor = new ArrayList<>();
    
    //artistas
    private ArrayList<Artista> artistas = new ArrayList<>();
    private List<Actor> actores = new ArrayList<Actor>();
    private List<Director> directors = new ArrayList<Director>(); //lista que almacenará todos los actores creados
    
    //obras
    private ArrayList<Obra> obras = new ArrayList<>();
    private ArrayList<Obra> estadoCriticoS;

    //clientes
    private ArrayList<Cliente> clientes = new ArrayList<>();
    
    //funciones
    private ArrayList <Funcion> funcionesCreadas= new ArrayList<>();
    private ArrayList <Cliente> asistentes = new ArrayList<>();

    //salas
    private ArrayList <Sala> salas = new ArrayList<>();

    //tiquetes
    private ArrayList <Tiquete> tiquetes=new ArrayList<>();

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Teatro getInstancia() {
        return instancia;
    }

    public static void setInstancia(Teatro instancia) {
        Teatro.instancia = instancia;
    }

    public Tesoreria getTesoreria() {
        return tesoreria;
    }

    public void setTesoreria(Tesoreria tesoreria) {
        this.tesoreria = tesoreria;
    }

    public Sala getSala1() {
        return sala1;
    }

    public void setSala1(Sala sala1) {
        this.sala1 = sala1;
    }

    public ArrayList<Empleado> getEmpleadosPorRendimiento() {
        return empleadosPorRendimiento;
    }

    public void setEmpleadosPorRendimiento(ArrayList<Empleado> empleadosPorRendimiento) {
        this.empleadosPorRendimiento = empleadosPorRendimiento;
    }

    public ArrayList<Empleado> getTipoSeguridad() {
        return tipoSeguridad;
    }

    public void setTipoSeguridad(ArrayList<Empleado> tipoSeguridad) {
        this.tipoSeguridad = tipoSeguridad;
    }

    public ArrayList<Empleado> getTipoAseador() {
        return tipoAseador;
    }

    public void setTipoAseador(ArrayList<Empleado> tipoAseador) {
        this.tipoAseador = tipoAseador;
    }

    public ArrayList<Empleado> getTipoProfesor() {
        return tipoProfesor;
    }

    public void setTipoProfesor(ArrayList<Empleado> tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public ArrayList<Obra> getObras() {
        return obras;
    }

    public void setObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    public ArrayList<Obra> getEstadoCriticoS() {
        return estadoCriticoS;
    }

    public void setEstadoCriticoS(ArrayList<Obra> estadoCriticoS) {
        this.estadoCriticoS = estadoCriticoS;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Funcion> getFuncionesCreadas() {
        return funcionesCreadas;
    }

    public void setFuncionesCreadas(ArrayList<Funcion> funcionesCreadas) {
        this.funcionesCreadas = funcionesCreadas;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public ArrayList<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(ArrayList<Tiquete> tiquetes) {
        this.tiquetes = tiquetes;
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }
    
    
}