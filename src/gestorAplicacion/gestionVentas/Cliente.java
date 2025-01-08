package gestorAplicacion.gestionVentas; 

import java.util.ArrayList;

import gestorAplicacion.Enumeraciones.*;
import gestorAplicacion.gestionObras.Actor;

public class Cliente {
    long id;
    Funcion funcion;
    Genero generoFavorito;
    Actor actorFavorito;
    ArrayList<Tiquete> ultimasCompras = new ArrayList<>();
    String correo;
    String tipo;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Funcion getFuncion() {
        return funcion;
    }
    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
    public Genero getGeneroFavorito() {
        return generoFavorito;
    }
    public void setGeneroFavorito(Genero generoFavorito) {
        this.generoFavorito = generoFavorito;
    }
    public Actor getActorFavorito() {
        return actorFavorito;
    }
    public void setActorFavorito(Actor actorFavorito) {
        this.actorFavorito = actorFavorito;
    }
    public ArrayList<Tiquete> getUltimasCompras() {
        return ultimasCompras;
    }
    public void setUltimasCompras(ArrayList<Tiquete> ultimasCompras) {
        this.ultimasCompras = ultimasCompras;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
}

