package gestorAplicacion.herramientas;
import gestorAplicacion.gestionObras.Actor;

public class Contador {
    
    private Actor actor;
    public int numero; 
    
    public Contador(Actor actor, int Numero){
    this.actor = actor;
    this.numero = numero;
    }

    public Actor getActor(){ return this.actor; }
}
