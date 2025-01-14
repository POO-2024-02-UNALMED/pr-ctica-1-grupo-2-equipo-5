package gestorAplicacion.gestionVentas; 

import java.util.ArrayList;
import java.util.List;
import gestorAplicacion.herramientas.input;

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
    private List<Actor> historial = new ArrayList<>();
    private String correo;
    private String tipo;

    //constructor solo con tipo de cliente
    public Cliente(String tipo){
        this.tipo = tipo;
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
        
        if (!input.isIn(this.historial, actor)){
            historial.add(actor);
        }

        int precio = (int) actor.getPrecioContrato();
        CuentaBancaria.transferencia(Tesoreria.getCuenta(), precio);
    }

    public List<Actor> getHistorial(){ return historial; }
    public void setHistorial(List<Actor> historial){ this.historial = historial; }
}

