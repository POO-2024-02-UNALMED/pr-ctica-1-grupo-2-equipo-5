package gestorAplicacion.gestionVentas; 

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private String obra;
    private Suscripcion suscripcion ;
    private long id;
    private Funcion funcion;
    private Genero generoFavorito;
    private Actor actorFavorito;
    private ArrayList<Tiquete> ultimasCompras = new ArrayList<>();
    private ArrayList<Actor> historial = new ArrayList<>();
    private String correo;
    private String tipo;
    private CuentaBancaria cuentaBancaria;
    public static ArrayList<Cliente> clientes = new ArrayList<>();

    //constructor solo con tipo de cliente y id
    public Cliente(String tipo, long id){
        this.tipo = tipo;
        this.id = id;
        this.cuentaBancaria = new CuentaBancaria(id, 0);
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
    public byte pagarAlquilerActor(Actor actor, Tesoreria tesoreria){

        if (tipo != "Empresa" || this.cuentaBancaria.getSaldo() < actor.getPrecioContrato()){ return -1; }

        if (!Main.isIn(this.historial, actor)){
            historial.add(actor);
        }

        double precio = actor.getPrecioContrato();
        this.cuentaBancaria.transferencia(tesoreria.getCuenta(), precio);
        return 1;
    }


    public List<Actor> getHistorial(){ return historial; }
    public void setHistorial(List<Actor> historial){ this.historial = historial; }

    public static boolean verificar(long elemento){
        for (int i=0; i < clientes.size();i++){
            if (clientes.get(i).getId()==elemento) {
                return true;
                
            }
            
        }
        return false;
    }
    
    public static Cliente asignar(long id){
        Cliente Asig = null;
        for(int i = 0;i < clientes.size();i++){
            if (clientes.get(i).getId()==id) {
                Asig = clientes.get(i);
                
                    
            }
                    
        }
        return Asig;
                
        
    }
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
    public Cliente(String tipo, long id,Suscripcion suscripcion){
        this.tipo = tipo;
        this.id = id;
        this.suscripcion=suscripcion;
        this.cuentaBancaria = new CuentaBancaria(id, 0);
        clientes.add(this);
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }
    
    public String consultarPerfil(){
        String string2;
        String string = String.format("%30s %20s ","Su ID es :",this.getId()+"\n");
        if (this.getObra()==null) {
            string2 = String.format("%30s %20s ","Su ultima compra :","Ninguna\n");
            
        } else{
            string2 = String.format("%30s %20s ","Su ultima compra :",this.getObra()+"\n");

        }
        
        String string3 = String.format("%30s %20s ","Su suscripcion es :",this.getSuscripcion()+"\n");
        

        return string+string2+string3;

    }
    
    
}

