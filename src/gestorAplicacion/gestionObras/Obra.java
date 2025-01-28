package gestorAplicacion.gestionObras;

import java.util.ArrayList;

import baseDatos.Teatro;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

import gestorAplicacion.herramientas.*;
//import test.funci_1;
import gestorAplicacion.gestionVentas.*;

public class Obra implements Serializable{
    private int audienciaEsperada;
    private String nombre;
    private float calificacion;
    private ArrayList<Actor> reparto = new ArrayList<>();
    private ArrayList<Aptitud> papeles = new ArrayList<>();
    private Director director;
    private float costoProduccion;
    private ArrayList<Funcion> funcionesSemana = new ArrayList<>();
    private Genero genero;
    private int tiquetesTotales;
    private boolean estadoCriticoA;
    private static ArrayList<Obra> estadoCriticoS;
    private ArrayList<Float> calificaciones=new ArrayList<>();
    static ArrayList<Obra> obras=new ArrayList<>();
    private ArrayList<LocalTime> franjaHoraria;
    private Duration duracion;
    private Funcion funcionEstelar;
    private ArrayList<Funcion> funciones;
    private int funcionesRecomendadas;
    private float promedioArt;
    private boolean repartoDisponible;
    private float Asistencia=0;
    private float precio=0f;




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
        obras.add(this);
        Teatro.getInstancia().getObras().add(this);
    }

    public Obra(String nombre, ArrayList<Actor> reparto, ArrayList<Aptitud> papeles, Director director, float costoProduccion, Genero genero, 
    long duracion){
        audienciaEsperada = getAudienciaEsperada();
        this.nombre = nombre;
        calificacion = 0;
        this.reparto = reparto;
        this.papeles = papeles;
        this.director = director;
        promedioArt = calcPromedioArt(reparto);
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
        funcionesRecomendadas = funcionesRecomendadas(promedioArt);
        obras.add(this);
        this.franjaHoraria(genero);
        Teatro.getInstancia().getObras().add(this);
    }
    public Obra(String nombre,Genero genero,Duration duracion){
        this.nombre=nombre;
        this.genero=genero;
        this.duracion=duracion;
        obras.add(this);
        Teatro.getInstancia().getObras().add(this);


    }
    
    public Obra(Funcion funcionEstelar, Genero genero, String nombre){
        this.funcionEstelar = funcionEstelar;
        this.genero = genero;
        this.nombre = nombre;
        this.franjaHoraria(genero);
        this.duracion = Duration.between(funcionEstelar.getHorario().get(0), funcionEstelar.getHorario().get(1));
        obras.add(this);
        Teatro.getInstancia().getObras().add(this);
    }

/**
 * This Java function returns a recommended value based on a given floating-point parameter.
 * 
 * @param promedioArt It seems like there is a variable `calificacion` being used in the code snippet
 * you provided, but it is not defined in the function `funcionesRecomendadas`. If `calificacion` is
 * supposed to be a parameter of the function, you should add it to the function signature like
 * @return a value based on the input parameter `calificacion`. If `calificacion` is less than 2, it
 * returns 3. If `calificacion` is between 2 and 3, it returns 5. If `calificacion` is between 3 and 4,
 * it returns 7. Otherwise, it returns 10.
 */
    public int funcionesRecomendadas(float promedioArt){
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

/**
 * The function calculates the expected audience based on a given rating by multiplying the rating by
 * 12.
 * 
 * @param calificacion The parameter `calificacion` represents the rating or score given to a
 * particular item or event. In the provided method `calcAudienciaEsperada`, this rating is used to
 * calculate the expected audience by multiplying the rating by 12 and then setting the calculated
 * value as the expected audience.
 */
    public void calcAudienciaEsperada(float calificacion){
        int u;
        u = (int) calificacion * 12;
        this.setAudienciaEsperada(u);
    }
/**
 * This Java function calculates the average of a list of floating-point numbers and sets it as a class
 * attribute.
 * 
 * @param calificaciones The method `calcularCalificacion` calculates the average of a list of grades
 * stored in an ArrayList. It iterates over each grade in the list, sums them up, and then divides the
 * total sum by the number of grades to get the average. Finally, it sets the calculated average as the
 */
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
 /**
  * The `franjaHoraria` method iterates through a list of plays to find the time range of the main show
  * for a specific genre.
  * 
  * @param genero The `genero` parameter in the `franjaHoraria` method represents the genre of a play
  * or performance. This method is designed to find the time frame (franja horaria) during which
  * performances of a specific genre are scheduled. The method iterates through all the plays in the
  * theater
  */
    public void franjaHoraria(Genero genero){
        Genero u;
        Funcion a = new Funcion(LocalDateTime.of(2024, 1, 02, 00, 00));
        ArrayList<LocalTime> franja = new ArrayList<>();
        franja.add(LocalTime.of(00,00));
        franja.add(LocalTime.of(23,59));
        ArrayList<Obra> obrasGenero = new ArrayList<>();
        for (Obra obra : Teatro.getInstancia().getObras()){
            u = obra.getGenero();
            if (u == genero){
                obrasGenero.add(obra);
            }
        }
        for (Obra obra: obrasGenero){
            a = obra.funcionEstelar;
            if (a != null) {
                ArrayList<LocalTime> fstar = a.extraerHora(a.getHorario());
                if (!fstar.isEmpty() && fstar.size() <= 2) {// Asegúrate de que i tenga al menos 2 elementos
                    if (fstar.get(0).isAfter(franja.get(0))) {
                        franja.set(0, fstar.get(0));
                    }
                    if (fstar.get(1).isBefore(franja.get(1))) {
                        franja.set(1, fstar.get(1));
                    }
                }
            } else {
                // Manejo del caso donde funcionEstelar es null
                break;
            }
        setFranjaHoraria(franja);
        }
    }
/**
 * The function `getDuracionFormato` converts a duration into a formatted string displaying hours and
 * minutes.
 * 
 * @return The method `getDuracionFormato` returns a formatted string representing the duration in
 * hours and minutes.
 */
    public String getDuracionFormato() {
        long horas = duracion.toHours();
        long minutos = duracion.toMinutes() % 60;
        return String.valueOf(horas) +":"+ String.valueOf(minutos);
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

/**
 * The `calcFuncionEstelar` method iterates through a list of `Funcion` objects to find the one with
 * the highest number of tickets sold and sets it as the "Funcion Estelar".
 * 
 * @param funciones The `calcFuncionEstelar` method you provided seems to be trying to find the
 * function with the highest number of tickets sold from a list of functions. However, there is a
 * logical error in the code where you are assigning `d = s` instead of `s = d` when
 */
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

/**
 * The function `createDuration` takes a long format representing hours, minutes, and seconds in a
 * specific format and creates a Duration object in Java.
 * 
 * @param format The `format` parameter in the `createDuration` method represents a time value in the
 * format of `HHmmss`, where:
 * @return The method `createDuration` returns a `Duration` object representing the duration calculated
 * from the input `format` value, which is in the format of `HHmmss` (hours, minutes, seconds).
 */
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
/**
 * The function calculates the average of a list of grades stored in an ArrayList.
 * 
 * @return The method `promedioCalificacion` is returning the average of the calificaciones (grades)
 * stored in the ArrayList `c`.
 */
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
/**
 * The function checks if an "Obra" object has any calificaciones and returns true if it does, false
 * otherwise.
 * 
 * @param obra The method `calificacionVacia` takes an `Obra` object as a parameter. The `Obra` class
 * likely has a method `getCalificaciones()` which returns a list of calificaciones (ratings or
 * grades). The method checks if the list of calificaciones in the given `Ob
 * @return The method `calificacionVacia` is returning a boolean value.
 */
    public static boolean calificacionVacia(Obra obra){
        boolean valor = true;
        if (obra.getCalificaciones().size()==0) {
            valor = false;
        }
        return valor;
        

    }
    public void recurrencia(){
        this.Asistencia++;
    

    }
    
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
/**
 * The function calculates the price of a work based on its average rating, attendance, and a base
 * price.
 * 
 * @param obra The given code snippet is a Java method named `precioFuncion` that calculates the price
 * of a work of art based on its average rating, attendance, and a base price. The method takes an
 * object of type `Obra` as a parameter.
 * @return The method `precioFuncion` is returning the calculated `precioBase` value, which represents
 * the final price of a given `Obra` object based on its average rating, attendance, and a base price
 * of 10000.
 */
    public static float precioFuncion(Obra obra){
            float prom = obra.promedioCalificacion();
            float precioBase=10000;
            float ad = (obra.getAsistencia()*500);
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
            obra.setPrecio(precioBase);

            
            
    
            return precioBase;
    
        }
    public static String imprimirObra(Obra obra){
            String string = String.format("%30s %20s %20s %20s",obra.getNombre(),obra.getGenero(),obra.getDuracionFormato(),String.format("$%,.2f",precioObra(obra.nombre))+"\n");
            return string;
        }
        

    public static Obra buscarObra(String nombre){
        for (Obra obra : Teatro.getInstancia().getObras()) {
            if ((obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
                return obra;
            }
            
        }
        return null;


    }
    public static float precioObra(String nombre){
        for (Obra obra : Teatro.getInstancia().getObras()) {
            if ((obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
                return precioFuncion(obra);
            }
            
        }
        return 0;


    }


    public static boolean nombres(String nombre){
        ArrayList<String> listaNombres=new ArrayList<>();
        for (Obra a : Teatro.getInstancia().getObras()) {
            listaNombres.add(a.getNombre().toLowerCase());
            
        }
        if(listaNombres.contains(nombre)){
            return false;

        }
        return true;


    }

/**
 * The function "actualizarEstadoCritico" creates a list of works in a critical state by iterating
 * through all works in a theater and checking their critical state.
 */
    public static void actualizarEstadoCritico() {
        estadoCriticoS = new ArrayList<Obra>();
        for (Obra obra : Teatro.getInstancia().getObras()) {
            if (obra.checkEstadoCritico()) {
                estadoCriticoS.add(obra);
            }
        }
    }
    
    public static ArrayList<Obra> mostrarObrasCriticas() {
        ArrayList<Obra> obrasCriticas = new ArrayList<>();
    
        // Recopilar las obras en estado crítico
        for (Obra obra : Teatro.getInstancia().getObras()) {
            if (obra.promedioCalificacion() <= 2.0 && !obra.getNombre().trim().equals("NOTFORITE")) { // Estado crítico definido como <= 2.0
                obrasCriticas.add(obra);
            }
        }
        return obrasCriticas;
    }

/**
 * This Java function calculates the average rating of actors in a given ArrayList.
 * 
 * @param reparto The `calcPromedioArt` method calculates the average rating of actors in a given
 * ArrayList. The parameter `reparto` is an ArrayList of Actor objects representing the cast or
 * ensemble of a production. Each Actor object has a `calificacion` attribute that stores the rating of
 * the actor.
 * @return The method `calcPromedioArt` is returning the average rating of the actors in the `reparto`
 * ArrayList.
 */
    public float calcPromedioArt(ArrayList<Actor> reparto){
        int i = 0;
        float f = 0f;
        if (!reparto.isEmpty()){
            for (Actor actor : reparto){
                i = i + 1;
                f = f + actor.getCalificacion();
            }
        }
        else{
            i = 1;
        }
        return f / i;
    }

    public float getPromedioArt() {
        return promedioArt;
    }
    public void setPromedioArt(float promedioArt) {
        this.promedioArt = promedioArt;
    }
    public boolean getRepartoDisponible() {
        return repartoDisponible;
    }
    public void setRepartoDisponible(boolean repartoDisponible) {
        this.repartoDisponible = repartoDisponible;
    }

/**
 * This Java function checks if all actors in a cast are available within a specified time frame and
 * updates the availability status of the cast accordingly.
 * 
 * @param inicio The parameter `inicio` is a `LocalDateTime` object representing the start date and
 * time for checking actor availability in a movie production.
 * @param fin The parameter `fin` in the `isRepartoDisponible` method represents the end date and time
 * for a particular event or activity. It is of type `LocalDateTime`, which is a class in Java that
 * represents a date and time without a time zone. This parameter is used to check
 * @return The method `isRepartoDisponible` is returning a boolean value - either `true` or `false`
 * based on whether all actors in the cast are available for the specified time frame.
 */
    public boolean isRepartoDisponible(LocalDateTime inicio, LocalDateTime fin){
        ArrayList<Actor> genteDisponibleFR = new ArrayList<>();
        for (Actor actor : this.getReparto()){
            if(actor.isDisponible(inicio, fin)){
                genteDisponibleFR.add(actor);
            }
            else{
                break;
            }
        }
        if (genteDisponibleFR.size()==this.getReparto().size()){
            setRepartoDisponible(true);
            return true;
        }
        else{
            setRepartoDisponible(false);
            return false;
        }
    }

    public void addFuncion(Funcion funcion){
        this.funcionesSemana.add(funcion);
    }
    public float getAsistencia() {
        return Asistencia;
    }
    public void setAsistencia(float asistencia) {
        Asistencia = asistencia;
    }
 /**
  * The function generates a formatted table of theater plays excluding a specific one.
  * 
  * @return The `generarTabla` method is returning a formatted string that contains information about
  * each play in the theater's collection. The information includes the play's name, genre, duration,
  * and price formatted in a specific way. The method excludes any play with the name "NOTFORITE" from
  * the table.
  */
    public static String generarTabla(){
        String Nuevo="";
        
        for (Obra obra : Teatro.getInstancia().getObras()) {
            if (!obra.getNombre().equals("NOTFORITE")){
            String string = String.format("%30s %20s %20s %20s",obra.getNombre(),obra.getGenero(),obra.getDuracionFormato(),String.format("$%,.2f",precioObra(obra.nombre))+"\n");
        Nuevo = Nuevo +string;

            }
    }
    return Nuevo;
}
    
}
