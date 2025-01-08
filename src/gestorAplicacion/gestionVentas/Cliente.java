package gestorAplicacion.gestionVentas; 

import java.util.ArrayList;

import gestorAplicacion.Enumeraciones.*;
import gestorAplicacion.gestionObras.Actor;

public class Cliente {
    long id;
    Funcion funcion;
    Genero generoFavorito;
    Actor actorFavorito;
    ArrayList<Tiquete> ultimasCompras= new ArrayList<>();
    String correo;
    String tipo;
}
