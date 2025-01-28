package gestorAplicacion.gestionVentas; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseDatos.Teatro;
import uiMain.Main;


import gestorAplicacion.herramientas.*;
import gestorAplicacion.gestionObras.*;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;
import gestorAplicacion.gestionFinanciera.Tesoreria;

public class Cliente implements Serializable{
    private String obra;
    private Suscripcion suscripcion ;
    private long id;
    private Genero generoFavorito;
    private Actor actorFavorito;
    private ArrayList<Tiquete> ultimasCompras = new ArrayList<>();
    private ArrayList<Actor> historial = new ArrayList<>();
    private String correo;
    private String tipo;
    private CuentaBancaria cuentaBancaria;
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public Tiquete tiquete;

    //constructor solo con tipo de cliente y id
    public Cliente(String tipo, long id){
        this.tipo = tipo;
        this.id = id;
        this.cuentaBancaria = new CuentaBancaria(id, 0);
        Teatro.getInstancia().getClientes().add(this);
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
   /**
    * The function `pagarContratoActor` pays a contract to an actor if the type is "Empresa" and adds
    * the actor to the history if not already present.
    * 
    * @param actor The `actor` parameter in the `pagarContratoActor` method represents an object of the
    * class `Actor`. It is the actor for whom the contract payment is being processed.
    * @param duracion The parameter "duracion" in the method "pagarContratoActor" represents the
    * duration of the contract for which the actor will be paid. It is a long data type, which
    * typically represents a period of time in milliseconds. This duration is used to calculate the
    * price of the contract based on
    * @param tesoreria The parameter `tesoreria` seems to represent an instance of a class named
    * `Tesoreria`, which likely contains information related to treasury or finances. It appears to
    * have a method `getCuenta()` that retrieves an account associated with the treasury.
    * @return The method `pagarContratoActor` is returning a byte value. If the actor's type is not
    * "Empresa" or if the actor is not in the historical records, it will return -1. Otherwise, it will
    * transfer the contract price from the company's bank account to the treasury's account and return
    * 1.
    */
    public byte pagarContratoActor(Actor actor, long duracion, Tesoreria tesoreria){

        if (!tipo.equals("Empresa")){ return -1; }

        if (!Main.isIn(this.historial, actor)){
            historial.add(actor);
        }

        double precio = actor.getPrecioContrato(duracion);
        this.cuentaBancaria.transferencia(tesoreria.getCuenta(), precio);
        return 1;
    }

/**
 * The function `verificar` checks if a given element exists in a list of clients in a theater.
 * 
 * @param elemento The `verificar` method you provided is a static method that takes a `long` parameter
 * named `elemento`. This method iterates over a list of clients in a theater instance and checks if
 * any client has an ID equal to the `elemento` parameter. If a client with the specified
 * @return The method `verificar` is returning a boolean value. It returns `true` if the `elemento` is
 * found in the list of clients in the Teatro instance, and `false` otherwise.
 */
    public static boolean verificar(long elemento){
        for (int i=0; i < Teatro.getInstancia().getClientes().size();i++){
            if (Teatro.getInstancia().getClientes().get(i).getId()==elemento) {
                return true;
                
            }
            
        }
        return false;
    }
/**
 * This Java function compares the subscription level of a user to a specified level and returns a
 * boolean indicating if the user's subscription is lower.
 * 
 * @param s The method `verificarSuscripcion` takes a String parameter `s` and compares it with the
 * subscription level of an object. The method assigns values to variables `a` and `b` based on the
 * input `s` and the subscription level of the object. It then checks if `a
 * @return The method `verificarSuscripcion` is returning a boolean value. It is checking if the
 * numeric value represented by the current subscription type (`this.getSuscripcion()`) is greater than
 * or equal to the numeric value represented by the input string `s`. If the current subscription type
 * is of lower value than the input string, it will return `true`, otherwise it will return `false`.
 */
    public boolean verificarSuscripcion(String s){
    
        int a =0;
        int b =0;
        switch (s) {
            case "G":
            b=3;
                break;
        
            case "P":
            b=2;
                break;
            case "C":
            b=1;
                break;
            case "B":
            b=0;
                break;
        }
        if (this.getSuscripcion().name().equals("Basica")) {
            a=0;
                
            } else if (this.getSuscripcion().name().equals("Premium")) {
                a=1;
            } else if (this.getSuscripcion().name().equals("Vip")) {
                a=2;
            } else if (this.getSuscripcion().name().equals("Elite")){
                a=3;       
            }
            return !(a >=b);
    }
    
/**
 * The `asignar` function in Java searches for a Cliente object by its ID within a list of clients
 * associated with a Teatro instance.
 * 
 * @param id The `asignar` method takes a `long` parameter `id` which is used to search for a specific
 * `Cliente` object in the list of clients stored in the `Teatro` class. The method iterates through
 * the list of clients and returns the `Cliente` object with the
 * @return The method `asignar` is returning a `Cliente` object that matches the provided `id`. If a
 * `Cliente` object with the specified `id` is found in the list of clients in the Teatro instance,
 * that `Cliente` object is returned. Otherwise, `null` is returned.
 */
    public static Cliente asignar(long id){
        Cliente Asig = null;
        for(int i = 0;i < Teatro.getInstancia().getClientes().size();i++){
            if (Teatro.getInstancia().getClientes().get(i).getId()==id) {
                Asig = Teatro.getInstancia().getClientes().get(i);
                
                    
            }
                    
        }
        return Asig;
                
        
    }
/**
 * The function generates a random long integer until a unique value is obtained by checking against a
 * verification method.
 * 
 * @return The method `IdRandom()` is returning a randomly generated long integer value that is between
 * 0 and 999 (inclusive) and passes the verification check in the `verificar()` method.
 */
    public static long IdRandom(){
        boolean salir = false;
        long codigo = 0;
        Random random = new Random();

        while (!salir) {
            codigo = random.nextInt(999);
            if (verificar(codigo)) {
            }else{  
                salir = true;
            } 
        }
        return codigo;
    }

    public List<Actor> getHistorial(){ return historial; }
    public void setHistorial(ArrayList<Actor> historial){ this.historial = historial; }


    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        Cliente.clientes = clientes;
    }

    public  Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion  suscripcion) {
        this.suscripcion = suscripcion;
    }
    public Suscripcion imprimirSuscripcion(){
        Suscripcion nombre = this.getSuscripcion();
        return nombre;

    }
    public Cliente(String tipo, long id,Suscripcion suscripcion,Silla silla){
        this.tipo = tipo;
        this.id = id;
        this.suscripcion=suscripcion;
        this.cuentaBancaria = new CuentaBancaria(id, 0);
        this.tiquete.setSilla(silla);
        Teatro.getInstancia().getClientes().add(this);
    }
    public Cliente(long id,Suscripcion suscripcion){
        this.id = id;
        this.suscripcion=suscripcion;
        this.cuentaBancaria = new CuentaBancaria(id, 0);
        this.tipo = "Persona";
        Teatro.getInstancia().getClientes().add(this);
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }
    
/**
 * The `consultarPerfil` function in Java generates a formatted string containing user profile
 * information such as user ID, last purchase, and subscription status.
 * 
 * @return The method `consultarPerfil` is returning a formatted string that contains information about
 * the user's profile. The returned string includes the user's ID, their last purchase (if any), and
 * their subscription status.
 */
    public String consultarPerfil(){
        String string2;
        String string = String.format("%30s %20s ","Usuario N. ",this.getId()+"\n");
        if (this.getObra()==null) {
            string2 = String.format("%30s %20s ","Su ultima compra :","Ninguna\n");
            
        } else{
            string2 = String.format("%30s %20s ","Su ultima compra :",this.getObra()+"\n");

        }
        
        String string3 = String.format("%30s %20s ","Su suscripcion es :",this.getSuscripcion()+"\n");
        

        return string+string2+string3;

    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }



    
    
}

