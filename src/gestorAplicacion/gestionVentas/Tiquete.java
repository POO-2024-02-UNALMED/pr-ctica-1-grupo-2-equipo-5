package gestorAplicacion.gestionVentas;
import java.util.ArrayList;
import java.util.Random;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.herramientas.Asiento;

public class Tiquete {
    private Float valor;
    private Long id;
    private Cliente cliente;
    private Funcion funcion;
    private Actor personaje;
    private static ArrayList <Tiquete> tiquetes=new ArrayList<>();
    private Obra obra;
    private Silla silla;

    


    public static ArrayList<Tiquete> getTiquetes() {
        return tiquetes;
    }
    public static void setTiquetes(ArrayList<Tiquete> tiquetes) {
        Tiquete.tiquetes = tiquetes;
    }

    public Tiquete(){

    };

    public Tiquete(Cliente cliente){
        this.cliente = cliente;
        tiquetes.add(this);

    }
    public static long idTiquete(){
        boolean salir = false;
        long codigo = 0;
        Random random = new Random();

        while (!salir) {
            codigo = random.nextInt(9999);
            if (verificar(codigo)) {
            }else{  
                salir = true;
            } 
        }
        return codigo;
    }
    public static boolean verificar(long elemento){
        for (int i=0; i < tiquetes.size();i++){
            if (tiquetes.get(i).getId()==elemento) {
                return true;
                
            }
            
        }
        return false;
    }

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
    public Silla getSilla() {
        return silla;
    }
    public void setSilla(Silla silla) {
        this.silla = silla;
    }

    

    
}
