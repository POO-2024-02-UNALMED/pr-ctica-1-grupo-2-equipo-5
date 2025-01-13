package uiMain;
import gestorAplicacion.gestionObras.Actor;


public class Contador {
    
    private Actor actor;
    protected int numero; 
    
    public Contador(Actor actor, int numero){
    this.actor = actor;
    this.numero = numero;
    }

    public Actor getActor(){ return this.actor; }
}
