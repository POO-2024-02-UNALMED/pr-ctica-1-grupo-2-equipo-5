package gestorAplicacion.gestionVentas;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Obra;

public class Tiquete implements Serializable{
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
    void dividirN(String s){
        
        String[] partes = s.split(" ");
        int a = partes.length;
        if (a >2){
            for (int i =0;i < s.length();i++){

            }
        }

    }
   
    public String imprimirFactura(Cliente c,boolean b,float d,float p,float su){
        LocalDate fechaActual = LocalDate.now();
        String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        String s;
        c.getTiquete().setId(Tiquete.idTiquete());
        s="================================\n";
        s =s+"Tiquete # "+c.getTiquete().getId();
        s =s+"\n"+fechaFormateada;

        s=s+"\n================================\n";
        s=s+String.format("%10s %20s ","Producto","Precio")+"\n";
        s=s+"\n--------------------------------\n";
        s=s+String.format("%10s %20s ","Funcion",String.format("$%,.2f",p)+"\n");
        if (!b & !c.getSuscripcion().name().equals("Basica")){
            s=s+String.format("%10s %20s ","Suscripcion",String.format("$%,.2f",su))+"\n";
            s=s+String.format("%10s %20s ",c.getSuscripcion(),"")+"\n";
            
        }
        if (!c.getSuscripcion().name().equals("Basica")){
            s=s+"\nDescuento por ser "+c.getSuscripcion();
            s=s+"\n-"+String.format("$%,.2f",d);
        }
        s=s+"\n--------------------------------";
        s=s+"\n\n Valor total "+String.format("$%,.2f",c.getTiquete().getValor());
        s=s+"\nUsted ahorro "+String.format("$%,.2f",d);
        s=s+"\n================================\n";
        s=s+"GRACIAS POR SU COMPRA\n";
        s=s+"================================\n";

        return s;

    }
    

    

    
}
