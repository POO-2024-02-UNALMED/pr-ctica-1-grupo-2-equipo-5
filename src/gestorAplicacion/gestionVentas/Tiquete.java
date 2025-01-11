package gestorAplicacion.gestionVentas;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Obra;

public class Tiquete {
    private Float valor;
    private Long id;
    private Cliente cliente;
    private Funcion funcion;
    private Actor personaje;
    // Falta aspecto Actor
    private Obra obra;

    // VALOR
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }

    //ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //CLIENTE
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //FUNCION
    public Funcion getFuncion() {
        return funcion;
    }
    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    //PERSONAJE
    public Actor getPersonaje() {
        return personaje;
    }
    public void setPersonaje(Actor personaje) {
        this.personaje = personaje;
    }

    //OBRA
    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    

    
}
