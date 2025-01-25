package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

import gestorAplicacion.gestionObras.*;
import gestorAplicacion.herramientas.Suscripcion;
import test.funci_1;

public class Funcion {
    private Obra obra;
    private int tiquetesVendidos;
    private ArrayList<LocalDateTime> horario = new ArrayList<>();
    private Sala sala;
    private boolean calificador;
    private int audienciaEsperada;
    private boolean trabajador;
    static ArrayList <Funcion> funcionesCreadas= new ArrayList<>() ;


    // static{
    //     funci_1.prueba();
    // }
    public String tablaSillas(){
        String Nuevo="";
        ArrayList <Silla> s = this.getSala().getSillas();
        int h = 0;
        for (int i = 0; i < s.size(); i++) {
            char primerCaracter = s.get(i).getTipo().name().charAt(0);
            Nuevo=Nuevo+primerCaracter+s.get(i).getCodigo()+" "; 
            if ((i + 1) % 8 == 0) { 
                Nuevo = Nuevo+"\n";
            }
        }
        return Nuevo;
    }
    public void eliminarSilla(Integer i){
        ArrayList <Silla> s = this.getSala().getSillas();
        for (int k = 0; k < s.size(); k++) {
            if(s.get(k).getCodigo().equals(i)){
                s.remove(k);

            }

        }
        
    }
    
    public Funcion(Obra obra,Sala sala){
        this.sala = sala;
        this.obra=obra;
        funcionesCreadas.add((this));


    }
    //OBRA
    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    //TRABAJADOR
    public boolean getTrabajador(){
        return trabajador;
    }
    public void setTrabajador(boolean trabajador){
        trabajador = trabajador
    }
    //FUNCIONES
    public static ArrayList<Funcion> getFuncionesCreadas(){
        return funcionesCreadas;
    }
    public static void setFuncionesCreadas(ArrayList<Funcion> newFunciones){
        funcionesCreadas = newFunciones;
    }

    //TIQUETES VENDIDOS
    public int getTiquetesVendidos() {
        return tiquetesVendidos;
    }
    public void setTiquetesVendidos(int tiquetesVendidos) {
        this.tiquetesVendidos = tiquetesVendidos;
    }

    //HORARIO
    public ArrayList<LocalDateTime> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<LocalDateTime> horario) {
        this.horario = horario;
    }

    //SALA
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    //CALIFICADOR
    public boolean isCalificador() {
        return calificador;
    }
    public void setCalificador(boolean calificador) {
        this.calificador = calificador;
    }

    //AUDIENCIA ESPERADA
    public int getAudienciaEsperada() {
        System.out.println("ehhh");
        return audienciaEsperada;

    }
    public void setAudienciaEsperada(int audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    public Sala salaDisponible(Sala sala){
        return sala;
    }
    public Funcion(Obra obra, ArrayList<LocalDate> week) {
        this.obra = obra;
        this.tiquetesVendidos = 0;
        this.horario = createHorario(week);
        this.sala = getSala();
        this.calificador = doWeNeedACalificador();
        this.audienciaEsperada = obra.getAudienciaEsperada();
        System.out.println("yeee");
        funcionesCreadas.add(this);
    }
    public Funcion(){

    }
    public Funcion(LocalDateTime hora){
        horario.add(hora);
        horario.add(hora);
    }

    public Funcion(ArrayList<LocalDateTime> horario){
        this.horario = horario;
    }
    
    public ArrayList<LocalDateTime> createHorario(ArrayList<LocalDate> week){
        ArrayList<LocalDateTime> horario = new ArrayList<>();
        LocalTime inicioFranja = this.obra.getFranjaHoraria().get(0);
        System.err.println(Sala.getSalas());
        System.out.println(inicioFranja);
        for (Sala sala : Sala.getSalas()){
            System.out.println("salas");
            if (sala.getCapacidad() > this.obra.getAudienciaEsperada()){
                for (LocalDate day : week){
                    System.out.println("weekn");
                    LocalTime inicioFranjaITE = inicioFranja;
                    while (inicioFranjaITE.isBefore(this.obra.getFranjaHoraria().get(1))
                    && inicioFranjaITE.plusSeconds(this.getObra().getDuracionFormatoS()).isBefore(LocalTime.of(22,00)))
                    {
                        System.out.println("franja");
                        LocalDateTime i = LocalDateTime.of(day, inicioFranjaITE) ;
                        LocalDateTime v = i.plusSeconds(this.obra.getDuracionFormatoS());
                        System.out.println(i);
                        if(this.getObra().isRepartoDisponible(i, v) && sala.isDisponible(i,v)){
                            horario.add(i);
                            horario.add(v);
                            this.setSala(sala);
                            System.out.println(sala.getNumeroSala());
                            this.getSala().anadirHorario(horario);
                            return horario;
                        }
                        inicioFranjaITE = inicioFranjaITE.plusMinutes(30);
                    }
                }
            }
        }
        return horario;
    }

    public ArrayList<LocalTime> extraerHora(ArrayList<LocalDateTime> horario){
        ArrayList<LocalTime> a = new ArrayList<>();
        for (LocalDateTime tiempo : horario){
            // Extraer la hora, minutos y segundos
            int hora = tiempo.getHour();
            int minutos = tiempo.getMinute();
            int segundos = tiempo.getSecond();
        a.add(LocalTime.of(hora, minutos, segundos));
        }
        return a;
    }
    
    public boolean doWeNeedACalificador(){
        boolean a = false;
        for (Actor actor : this.getObra().getReparto()){
            if (actor.isReevaluacion()){
                a = true;
            }
        }
        System.out.println("jmmm");
        return a;
    }
    public static String generarTabla(){
        String Nuevo="";
        for (Funcion funcion : funcionesCreadas) {
            String string = String.format("%30s %20s %20s %20s",funcion.obra.getNombre(),funcion.obra.getGenero(),funcion.obra.getDur(),String.format("$%,.2f",precioFuncion(funcion))+"\n");
        Nuevo = Nuevo +string;

        
    }
    return Nuevo;
}
public static boolean calificacionVacia(Obra obra){
    boolean valor = true;
    if (obra.getCalificaciones().size()==0) {
        valor = false;
    }
    return valor;
    

}

    public static float precioFuncion(Funcion funcion){
        float prom = funcion.obra.promedioCalificacion();
        float precioBase=10000;
        float ad = (funcion.obra.getAsistencia()*500);
        if (prom > 8) {
            precioBase =( precioBase +(prom*800)+ad);
            
        } else if(prom > 5)
        {
            precioBase = (precioBase +(prom*400)+ad);
        } else if (prom > 3){
            precioBase = (precioBase +(prom*200+ad));
        }else{
            precioBase = (precioBase +(prom*100+ad));
        }
        
        

        return precioBase;

    }

public static String imprimirFuncion(Funcion funcion){
        String string = String.format("%30s %15s %10s %20s",funcion.obra.getNombre(),funcion.obra.getGenero(),funcion.obra.getDur(),String.format("$%,.2f",precioFuncion(funcion)));
        return string;
    }
    

public static Funcion buscarFuncion(String nombre){
    for (Funcion funcion : funcionesCreadas) {
        if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
            return funcion;
        }
        
    }
    return null;


}
public static float mostrarPrecioFuncion(String nombre){
    for (Funcion funcion : funcionesCreadas) {
        if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
            return precioFuncion(funcion);
        }
        
    }
    return 0;


}


public static boolean nombres(String nombre){
    ArrayList<String> listaNombres=new ArrayList<>();
    for (Funcion a : funcionesCreadas) {
        listaNombres.add(a.obra.getNombre().toLowerCase());
        
    }
    if(listaNombres.contains(nombre)){
        return false;

    }
    return true;


}
}



