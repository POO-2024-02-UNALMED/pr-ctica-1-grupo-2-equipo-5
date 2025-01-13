package gestorAplicacion.gestionVentas; 

import java.util.ArrayList;

import gestorAplicacion.herramientas.*;
import gestorAplicacion.gestionObras.*;

public class Cliente {
    private long id;
    private Funcion funcion;
    private Genero generoFavorito;
    private Actor actorFavorito;
    private ArrayList<Tiquete> ultimasCompras = new ArrayList<>();
    private String correo;
    private String tipo;

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
}

