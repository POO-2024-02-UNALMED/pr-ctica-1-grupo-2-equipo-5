package gestorAplicacion.gestionObras;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalTime;

import gestorAplicacion.herramientas.*;
import gestorAplicacion.gestionVentas.*;

public class Obra {
    private int audienciaEsperada;
    private String nombre;
    private float calificacion;
    private ArrayList<Actor> reparto;
    private ArrayList<Aptitud> papeles;
    private Director director;
    private float costoProduccion;
    private ArrayList<Funcion> funcionesSemana;
    private Genero genero;
    private int tiquetesTotales;
    private boolean estadoCriticoA;
    private static ArrayList<Obra> estadoCriticoS;
    private ArrayList<Float> calificaciones=new ArrayList<>();
    public static ArrayList<Obra> obras=new ArrayList<>();
    private ArrayList<LocalTime> franjaHoraria;
    private Duration duracion;
    public String dur;
    private Funcion funcionEstelar;
    private ArrayList<Funcion> funciones;
    private int funcionesRecomendadas;

    public int getAudienciaEsperada() {
        return audienciaEsperada;
    }
    public void setAudienciaEsperada(int audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }
    public float getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
        calificaciones.add(calificacion);
    }
    public ArrayList<Actor> getReparto() {
        return reparto;
    }
    public void setReparto(ArrayList<Actor> reparto) {
        this.reparto = reparto;
    }
    public Director getDirector() {
        return director;
    }
    public void setDirector(Director director) {
        this.director = director;
    }
    public float getCostoProduccion() {
        return costoProduccion;
    }
    public void setCostoProduccion(float costoProduccion) {
        this.costoProduccion = costoProduccion;
    }
    public ArrayList<Funcion> getFuncionesSemana() {
        return funcionesSemana;
    }
    public void setFuncionesSemana(ArrayList<Funcion> funcionesSemana) {
        this.funcionesSemana = funcionesSemana;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public int getTiquetesTotales() {
        return tiquetesTotales;
    }
    public void setTiquetesTotales(int tiquetesTotales) {
        this.tiquetesTotales = tiquetesTotales;
    }
    public boolean isEstadoCriticoA() {
        return estadoCriticoA;
    }
    public void setEstadoCriticoA(boolean estadoCriticoA) {
        this.estadoCriticoA = estadoCriticoA;
    }
    public static ArrayList<Obra> getEstadoCriticoS() {
        return estadoCriticoS;
    }
    public static void setEstadoCriticoS(ArrayList<Obra> estadoCriticoS) {
        Obra.estadoCriticoS = estadoCriticoS;
    }
    public Obra(int audienciaEsperada, float calificacion, ArrayList<Actor> reparto, Director director,
            float costoProduccion, ArrayList<Funcion> funcionesSemana, Genero genero, int tiquetesTotales,
            boolean estadoCriticoA) {
        this.audienciaEsperada = audienciaEsperada;
        this.calificacion = calificacion;
        this.reparto = reparto;
        this.director = director;
        this.costoProduccion = costoProduccion;
        this.funcionesSemana = funcionesSemana;
        this.genero = genero;
        this.tiquetesTotales = tiquetesTotales;
        this.estadoCriticoA = estadoCriticoA;
    }

    public Obra(String nombre, ArrayList<Actor> reparto, ArrayList<Aptitud> papeles, Director director, float costoProduccion, Genero genero, 
    long duracion){
        audienciaEsperada = getAudienciaEsperada();
        this.nombre = nombre;
        calificacion = 0;
        this.reparto = reparto;
        this.papeles = papeles;
        this.director = director;
        this.costoProduccion = costoProduccion;
        funcionesSemana = new ArrayList<>();
        this.genero = genero;
        tiquetesTotales = 0;
        this.estadoCriticoA = checkEstadoCritico();
        calificaciones = new ArrayList<>();
        this.franjaHoraria = new ArrayList<>();
        this.duracion = createDuration(duracion);
        funcionEstelar = null;
        funciones = new ArrayList<>();
        funcionesRecomendadas = funcionesRecomendadas(calificacion);
    }
    public Obra(String nombre,Genero genero,String duracion){
        this.nombre=nombre;
        this.genero=genero;
        this.dur=duracion;
        obras.add(this);

    }
    

    public int funcionesRecomendadas(float calificacion){
        if (calificacion < 2){
            return 3;
        }
        if (calificacion >= 2 && calificacion < 3) {
            return 5;
        }
        if (calificacion >= 3 && calificacion < 4){
            return 7;
        }
        else{
            return 10;
        }
    }

    public void calcAudienciaEsperada(float calificacion){
        int u;
        u = (int) calificacion * 12;
        this.setAudienciaEsperada(u);
    }
    public void calcularCalificacion(ArrayList<Float> calificaciones){
        float u;
        int t;
        float v;
        u = 0;
        t = 0;
        for (float calificacion : calificaciones){
            u = u + calificacion;
            t ++;
        }
        v = u / t;
        setCalificacion(v);
    }
    public void agregarCalificacion(float calificacion){
        this.calificaciones.add(calificacion);
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Float> getCalificaciones() {
        return calificaciones;
    }
    public void setCalificaciones(ArrayList<Float> calificaciones) {
        this.calificaciones = calificaciones;
    }
    public void franjaHoraria(Genero genero){
        Genero u;
        Funcion a = new Funcion(LocalDateTime.of(2025, 1, 00, 00, 00));
        ArrayList<LocalTime> franja = a.extraerHora(a.getHorario());
        ArrayList<LocalTime> i = new ArrayList<>();
        franja.add(LocalTime.of(24,00));
        franja.add(LocalTime.of(00,00));
        ArrayList<Obra> obrasGenero = new ArrayList<>();
        for (Obra obra : obras){
            u = obra.getGenero();
            if (u == genero){
                obrasGenero.add(obra);
            }
        }
        for (Obra obra: obrasGenero){
            a = obra.funcionEstelar;
            i = a.extraerHora(a.getHorario());
            if (i.get( 0).isBefore(franja.get(0))){
                franja.set(0, i.get(0));
            }
            if (i.get(1).isAfter(franja.get(1))){
                franja.set(1, i.get(1));
            }
        }
        setFranjaHoraria(franja);
    }
    public String getDuracionFormato() {
        long horas = duracion.toHours();
        long minutos = duracion.toMinutes() % 60;
        return String.valueOf(horas) + String.valueOf(minutos);
    }
    public static ArrayList<Obra> getObras() {
        return obras;
    }
    public void setObras(ArrayList<Obra> obras) {
        Obra.obras = obras;
    }
    public ArrayList<LocalTime> getFranjaHoraria() {
        return franjaHoraria;
    }
    public void setFranjaHoraria(ArrayList<LocalTime> franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
    public Duration getDuracion() {
        return duracion;
    }
    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public void calcFuncionEstelar(ArrayList<Funcion> funciones){
        Funcion u = new Funcion();
        Funcion v = new Funcion();
        int s;
        u.setTiquetesVendidos(0);
        s = u.getTiquetesVendidos();
        int d;
        for (Funcion funcion : funciones){
            d = funcion.getTiquetesVendidos();
            if (s < d){
                d = s;
                v = funcion;
            }
        }
        this.setFuncionEstelar(v);
    }
    public Funcion getFuncionEstelar() {
        return funcionEstelar;
    }
    public void setFuncionEstelar(Funcion funcionEstelar) {
        this.funcionEstelar = funcionEstelar;
    }
    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }
    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = funciones;
    }

    public long getDuracionFormatoS(){
        long seconds;
        seconds = duracion.toSeconds();
        return seconds;
    }

    public boolean checkEstadoCritico(){
        if(this.calificacion < 1){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<Aptitud> getPapeles() {
        return papeles;
    }
    public void setPapeles(ArrayList<Aptitud> papeles) {
        this.papeles = papeles;
    }

    public Duration createDuration(long format){
        long horas = format / 10000;
        long minutos = (format % 10000) / 100;
        long segundos = (format % 10000) % 100;
        Duration duracion = Duration.ofHours(horas).plusMinutes(minutos).plusSeconds(segundos);
        return duracion;
    }
    public int getFuncionesRecomendadas() {
        return funcionesRecomendadas;
    }
    public void setFuncionesRecomendadas(int funcionesRecomendadas) {
        this.funcionesRecomendadas = funcionesRecomendadas;
    }
    public float promedioCalificacion(){
        float promedio=0;
        ArrayList<Float> c = this.getCalificaciones();
        if (!calificacionVacia(this)) {
            return 0;
        }
        for (Float cal : c) {
            promedio =promedio+ cal;
        
        }
        return (promedio / c.size());
    }
    public static boolean calificacionVacia(Obra obra){
        boolean valor = true;
        if (obra.getCalificaciones().size()==0) {
            valor = false;
        }
        return valor;
        

    }
    public static float precioFuncion(Obra obra){
            float prom = obra.promedioCalificacion();
            float precioBase=10000;
            if (prom > 8) {
                precioBase = precioBase +(prom*800);
                
            } else if(prom > 5)
            {
                precioBase = precioBase +(prom*400);
            } else if (prom > 3){
                precioBase = precioBase +(prom*200);
            }else{
                precioBase = precioBase +(prom*100);
            }
            
            
    
            return precioBase;
    
        }
    public static String imprimirObra(Obra obra){
            String string = String.format("%30s %15s %10s %20s",obra.getNombre(),obra.getGenero(),obra.dur,String.format("$%,.2f",precioFuncion(obra)));
            return string;
        }
        
    public static String generarTabla(){
            String Nuevo="";
            for (Obra obra : obras) {
                String string = String.format("%30s %15s %10s %20s",obra.getNombre(),obra.getGenero(),obra.dur,String.format("$%,.2f",precioFuncion(obra))+"\n");
            Nuevo = Nuevo +string;

            
        }
        return Nuevo;
    }
    public static Obra buscarObra(String nombre){
        for (Obra obra : obras) {
            if ((obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
                return obra;
            }
            
        }
        return obras.get(0);


    }


    public static boolean nombres(String nombre){
        ArrayList<String> listaNombres=new ArrayList<>();
        for (Obra a : Obra.obras) {
            listaNombres.add(a.getNombre().toLowerCase());
            
        }
        if(listaNombres.contains(nombre)){
            return false;

        }
        return true;


    }
    
    

}
