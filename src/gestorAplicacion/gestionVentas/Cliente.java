package gestorAplicacion.gestionVentas; 

import java.util.ArrayList;
import java.util.List;

import uiMain.Main;
import uiMain.Main.isIn;

import gestorAplicacion.herramientas.*;
import gestorAplicacion.gestionObras.*;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.gestionVentas.Tiquete;
import gestorAplicacion.herramientas.Genero;

import gestorAplicacion.gestionFinanciera.Tesoreria;

public class Cliente {
    private long id;
    private Funcion funcion;
    private Genero generoFavorito;
    private Actor actorFavorito;
    private ArrayList<Tiquete> ultimasCompras = new ArrayList<>();
    private ArrayList<Actor> historial = new ArrayList<>();
    private String correo;
    private String tipo;
    private CuentaBancaria cuenta;
    public static ArrayList<Cliente> clientes = new ArrayList<>();

    //constructor solo con tipo de cliente y id
    public Cliente(String tipo, long id){
        this.tipo = tipo;
        this.id = id;
        this.cuenta = new CuentaBancaria(id, 0);
        clientes.add(this);
    }    

    public String toString(){
        return "Identificación: " + this.id + "\n" + "Tipo: " + this.tipo;
    }

    //ID
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //FUNCION
    public Funcion getFuncion() {
        return funcion;
    }
    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    //GENERO FAVORITO
    public Genero getGeneroFavorito() {
        return generoFavorito;
    }
    public void setGeneroFavorito(Genero generoFavorito) {
        this.generoFavorito = generoFavorito;
    }

    //ACTOR FAVORITO
    public Actor getActorFavorito() {
        return actorFavorito;
    }
    public void setActorFavorito(Actor actorFavorito) {
        this.actorFavorito = actorFavorito;
    }

    //ULTIMAS COMPRAS
    public ArrayList<Tiquete> getUltimasCompras() {
        return ultimasCompras;
    }
    public void setUltimasCompras(ArrayList<Tiquete> ultimasCompras) {
        this.ultimasCompras = ultimasCompras;
    }

    //CORREO
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //TIPO
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 

    public void calificar(float calificacion, Artista objeto){
        objeto.agregarCalificacion(calificacion);
    }

    public void calificar(float calificacion, Obra obra){
        obra.agregarCalificacion(calificacion);
    }


    //metodo para contratar actor y enviar dinero a tesorería
    public void pagarAlquilerActor(Actor actor){

        if (tipo != "Empresa"){ return; }
        
        if (!Main.isIn(this.historial, actor)){
            historial.add(actor);
        }

        double precio = actor.getPrecioContrato();
        this.cuenta.transferencia(Tesoreria.getCuenta(), precio);
    }

    public List<Actor> getHistorial(){ return historial; }
    public void setHistorial(List<Actor> historial){ this.historial = historial; }

    public CuentaBancaria getCuenta(){ return this.cuenta; }
}

