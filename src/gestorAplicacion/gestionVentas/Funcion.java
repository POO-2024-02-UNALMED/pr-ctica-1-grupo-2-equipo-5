package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import baseDatos.Teatro;

import java.time.LocalTime;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

import gestorAplicacion.gestionObras.*;

public class Funcion implements Serializable{
    private Obra obra;
    private int tiquetesVendidos;
    private ArrayList<LocalDateTime> horario = new ArrayList<>();
    private ArrayList <Silla> sillas = new ArrayList<>();
    private Sala sala;
    private boolean calificador;
    private int audienciaEsperada;
    private boolean trabajador;
    static ArrayList <Funcion> funcionesCreadas= new ArrayList<>() ;
    static ArrayList <Funcion> funcionesALaVenta= new ArrayList<>();

    private ArrayList <Cliente> asistentes = new ArrayList<>();
    private float precio=0f;


    public String tablaSillas(){
        String Nuevo="";
        ArrayList <Silla> s = this.getSala().getSillas();
        for (int i = 0; i < s.size(); i++) {
                        
            if (s.get(i).getCodigo()==88){
                Nuevo=Nuevo+"        ";
            }else {
                char primerCaracter = s.get(i).getTipo().name().charAt(0);
                Nuevo=Nuevo+primerCaracter+"-"+String.format("%04d", s.get(i).getCodigo())+"  "; 
            }

            if ((i + 1) % 8 == 0) { 
                Nuevo = Nuevo+"\n";
            }
        }
        return Nuevo+"\n\n-ESCENARIO-";
    }

    public void eliminarSilla(Integer i){
        Silla sillaVacia = new Silla(88);
        ArrayList <Silla> s = this.getSala().getSillas();
        for (int k = 0; k < s.size(); k++) {
            if(s.get(k).getCodigo().equals(i)){
                s.set(k, sillaVacia);
                

            }

        }
        
    }
    
    public Funcion(Obra obra,Sala sala){
        this.sillas=sala.getSillas();
        this.sala = sala;
        this.obra=obra;
        funcionesCreadas.add((this));
        Teatro.getInstancia().getFuncionesCreadas().add(this);


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
    public void setTrabajador(boolean newtrabajador){
        trabajador = newtrabajador;
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
        return audienciaEsperada;

    }
    public void setAudienciaEsperada(int audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    public Sala salaDisponible(Sala sala){
        return sala;
    }
    public ArrayList<Funcion> actualizarFuncionesVenta(ArrayList<Funcion> funcionesCreadas){
        ArrayList<Funcion> funcionesALaVenta = new ArrayList<>();
        if (!funcionesCreadas.isEmpty())
            for (Funcion funcion : funcionesCreadas){
                if (!funcion.getHorario().isEmpty()){
                    if (funcion.getHorario().get(0).isAfter(LocalDateTime.now())){
                    funcionesALaVenta.add(funcion);
                    }
                }
                else{
                    break;
                }
            }
            return funcionesALaVenta;
    }
    public Funcion(Obra obra, ArrayList<LocalDate> week) {
        this.obra = obra;
        this.tiquetesVendidos = 0;
        this.horario = createHorario(week);
        this.sala = getSala();
        this.calificador = doWeNeedACalificador();
        this.audienciaEsperada = obra.getAudienciaEsperada();
        funcionesCreadas.add(this);
        funcionesALaVenta = actualizarFuncionesVenta(funcionesCreadas);
        if (this.sala != null){
        this.sillas = this.sala.getSillas();
        }
        Teatro.getInstancia().getFuncionesCreadas().add(this);
    }
    public Funcion(){
        Teatro.getInstancia().getFuncionesCreadas().add(this);

    }
    public Funcion(LocalDateTime hora){
        horario.add(hora);
        horario.add(hora);
        Teatro.getInstancia().getFuncionesCreadas().add(this);
    }

    public Funcion(ArrayList<LocalDateTime> horario){
        this.horario = horario;
        Teatro.getInstancia().getFuncionesCreadas().add(this);
    }
    
 /**
  * The `createHorario` function generates a schedule for a given week based on available time slots
  * and room capacities for a theater performance.
  * 
  * @param week The `createHorario` method takes an `ArrayList` of `LocalDate` objects named `week` as
  * a parameter. This `week` parameter represents a collection of dates for which the method will
  * create a schedule.
  * @return The method `createHorario` is returning an ArrayList of LocalDateTime objects representing
  * the scheduled time slots for a performance of a play.
  */
    public ArrayList<LocalDateTime> createHorario(ArrayList<LocalDate> week){
        ArrayList<LocalDateTime> horario = new ArrayList<>();
        LocalTime inicioFranja = this.obra.getFranjaHoraria().get(0);
        for (Sala sala : Teatro.getInstancia().getSalas()){
            if (sala.getCapacidad() > this.obra.getAudienciaEsperada()){
                for (LocalDate day : week){
                    LocalTime inicioFranjaITE = inicioFranja;
                    while (inicioFranjaITE.isBefore(this.obra.getFranjaHoraria().get(1))
                    && inicioFranjaITE.plusSeconds(this.getObra().getDuracionFormatoS()).isBefore(LocalTime.of(22,00)))
                    {
                        LocalDateTime i = LocalDateTime.of(day, inicioFranjaITE) ;
                        LocalDateTime v = i.plusSeconds(this.obra.getDuracionFormatoS());
                        if(this.getObra().isRepartoDisponible(i, v) && sala.isDisponible(i,v)){
                            horario.add(i);
                            horario.add(v);
                            this.setSala(sala);
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

/**
 * The function extracts the time (hour, minute, second) from a list of LocalDateTime objects and
 * returns a list of LocalTime objects.
 * 
 * @param horario The parameter `horario` is an ArrayList of LocalDateTime objects, which contain both
 * date and time information. The method `extraerHora` aims to extract the time (hour, minute, and
 * second) from each LocalDateTime object in the input ArrayList and return an ArrayList of LocalTime
 * objects containing only the
 * @return The method `extraerHora` returns an `ArrayList` of `LocalTime` objects extracted from the
 * `LocalDateTime` objects in the input `ArrayList`.
 */
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
    
/**
 * The function `doWeNeedACalificador` checks if any actor in the cast of a play requires reevaluation.
 * 
 * @return The method `doWeNeedACalificador` is returning a boolean value, which is determined by
 * iterating through the actors in the cast of a play and checking if any of them require reevaluation.
 * If at least one actor requires reevaluation, the method will return `true`, otherwise it will return
 * `false`.
 */
    public boolean doWeNeedACalificador(){
        boolean a = false;
        for (Actor actor : this.getObra().getReparto()){
            if (actor.isReevaluacion()){
                a = true;
            }
        }
        return a;
    }
    
/**
 * The function "generarTabla" generates a table of functions with a specific name from a list of
 * created functions in a theater.
 * 
 * @param nombre The `generarTabla` method takes a `nombre` parameter, which is a String representing
 * the name of a play. The method then iterates through a list of functions and generates a table of
 * functions that match the provided play name. The table includes the function number, play name, and
 * the
 * @return The `generarTabla` method returns a formatted string containing information about the
 * functions with a specific name. It iterates through the list of created functions in the Teatro
 * instance, checks if the function's associated work name matches the input name (case-insensitive
 * comparison), and if it does, it appends the function's name and the first scheduled time to the
 * output string. The output string is
 */
    public static String generarTabla(String nombre){
        int in=0;
        String Nuevo="";
        String string ="";
        for (Funcion funcion : Teatro.getInstancia().getFuncionesCreadas()) {
            if (funcion.obra != null && funcion.obra.getNombre() != null) {
                if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
                    if(!funcion.getObra().getNombre().equals("NOTFORITE")){
                    in++;
                    string = in+String.format("%20s %30s",funcion.obra.getNombre(),funcion.getHorario().get(0));
                    Nuevo = Nuevo +"\n"+string;
                    }
                }
            }
        }
        return Nuevo;
    }
/**
 * This function checks if the number of occurrences of a given play name in the list of created
 * functions is greater than or equal to a specified index.
 * 
 * @param i The parameter `i` in the `indiceFuncion` method represents the minimum number of
 * occurrences of a specific `nombre` (name) of an `obra` (work) within the list of `Funcion` objects
 * in the `Teatro` class. The method iterates through the list
 * @param nombre The `nombre` parameter in the `indiceFuncion` method is a String that represents the
 * name of a function or performance. The method iterates through a list of created functions in a
 * theater and checks if the name of the function matches the provided `nombre` parameter
 * (case-insensitive comparison).
 * @return The method `indiceFuncion` is returning a boolean value. It checks if the number of
 * occurrences of a given `nombre` (name) in the list of created `Funcion` objects in the `Teatro`
 * class is greater than or equal to the specified index `i`. If the count is greater than or equal to
 * `i`, it returns `true`, otherwise it returns `false
 */
public static boolean indiceFuncion(int i,String nombre){
    int in =0;
    
    for (Funcion funcion : Teatro.getInstancia().getFuncionesCreadas()) {
        if (funcion.obra!=null){
        if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
            in++;

        }
    }}

return in >= i;
}
/**
 * The function `escogerFuncion` takes an integer `i` and a string `nombre`, iterates through a list of
 * created functions in a theater instance, and returns the function with the specified name if it
 * matches the input name and position.
 * 
 * @param i The parameter `i` in the `escogerFuncion` method is used to specify the index of the
 * function to be selected based on the provided `nombre` (name) parameter. The method iterates through
 * the list of created functions in the theater and checks if the function's associated play's
 * @param nombre The `nombre` parameter in the `escogerFuncion` method is a string that represents the
 * name of a function. The method iterates through a list of functions and returns the function with
 * the specified name when it matches the given `nombre` parameter.
 * @return The function `escogerFuncion` returns a `Funcion` object based on the input parameters `i`
 * and `nombre`. It iterates through the list of created functions in the theater, checks if the
 * function has an associated play, and compares the name of the play with the input `nombre`. If a
 * match is found, it increments the counter `in` and if the counter matches
 */
public static Funcion escogerFuncion(int i,String nombre){
    int in=0;
    for (Funcion funcion : Teatro.getInstancia().getFuncionesCreadas()) {
        if (funcion.obra!=null){
        if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
            in++;
            if (in==i){
                return funcion;
            }
        }
            
        }
        
    }
    return null;

}
public static boolean calificacionVacia(Obra obra){
    boolean valor = true;
    if (obra.getCalificaciones().size()==0) {
        valor = false;
    }
    return valor;
    

}

    public float getPrecio() {
    return precio;
}

public void setPrecio(float precio) {
    this.precio = precio;
}

/**
 * The function calculates the price of a performance based on the average rating of the play and the
 * attendance, with different pricing tiers based on the average rating.
 * 
 * @param funcion The `precioFuncion` method calculates the price of a function based on the average
 * rating of the play associated with the function and the attendance of the function. The price is
 * determined as follows:
 * @return The function `precioFuncion` is returning the calculated price based on the average rating
 * of the play associated with the function, the attendance of the play, and some predefined pricing
 * rules.
 */
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
        String string = String.format("%30s %15s %10s %20s",funcion.obra.getNombre(),funcion.obra.getGenero(),funcion.obra.getDuracion().toMinutes(),String.format("$%,.2f",precioFuncion(funcion)));
        return string;
    }
    

/**
 * The function searches for a specific function by name in a list of created functions within a
 * theater instance.
 * 
 * @param nombre The `buscarFuncion` method is used to search for a function by its name in a theater.
 * The method iterates through the list of created functions in the theater and compares the name of
 * the function's associated play (obra) with the provided name (case-insensitive comparison). If a
 * match
 * @return The method `buscarFuncion` is returning a `Funcion` object that matches the input `nombre`
 * parameter. If a matching `Funcion` object is found, it is returned; otherwise, `null` is returned.
 */
public static Funcion buscarFuncion(String nombre){
    for (Funcion funcion : Teatro.getInstancia().getFuncionesCreadas()) {
        if (funcion.obra!=null){
        if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
            return funcion;
        }
        
    }}
    return null;


}
/**
 * The function "mostrarPrecioFuncion" searches for a function by name in a theater's list of created
 * functions and returns its price if found.
 * 
 * @param nombre The method `mostrarPrecioFuncion` takes a `String` parameter called `nombre`, which
 * represents the name of a play. The method then iterates through the list of created functions in the
 * theater and checks if the name of the play matches the provided `nombre`. If a match is found
 * @return The method `mostrarPrecioFuncion` is returning a float value, which is the price of a
 * function with the given name. If a function with the specified name is found in the list of created
 * functions in the theater, the price of that function is returned. If no function with the given name
 * is found, the method returns 0.
 */
public static float mostrarPrecioFuncion(String nombre){
    for (Funcion funcion : Teatro.getInstancia().getFuncionesCreadas()) {
        if (funcion.obra!=null){
        if ((funcion.obra.getNombre().toLowerCase()).equals(nombre.toLowerCase())){
            return precioFuncion(funcion);
        }
    }
    }
    return 0;


}


/**
 * The function checks if a given name is already in a list of names and returns true if it is not
 * found.
 * 
 * @param nombre The given code snippet defines a method called `nombres` that takes a `String`
 * parameter named `nombre`. This method checks if the provided `nombre` is already present in a list
 * of names obtained from a collection of theater functions. If the `nombre` is found in the list, the
 * @return The method `nombres` returns a boolean value. If the input `nombre` is found in the list of
 * names obtained from the `Funcion` objects in the `Teatro` instance, then it returns `false`.
 * Otherwise, it returns `true`.
 */
public static boolean nombres(String nombre){
    ArrayList<String> listaNombres=new ArrayList<>();
    for (Funcion a : Teatro.getInstancia().getFuncionesCreadas()) {
        listaNombres.add(a.obra.getNombre().toLowerCase());
        
    }
    if(listaNombres.contains(nombre)){
        return false;

    }
    return true;


}
public static ArrayList<Funcion> getFuncionesALaVenta() {
    return funcionesALaVenta;
}
public static void setFuncionesALaVenta(ArrayList<Funcion> funcionesALaVenta) {
    Funcion.funcionesALaVenta = funcionesALaVenta;
}
/**
 * The function checks if a given element is present in a list of objects and returns true if it is not
 * found.
 * 
 * @param elemento The method `verificar` is checking if a given `elemento` is present in a list of
 * `sillas` by comparing its `codigo` with the `elemento`. If the `elemento` matches the `codigo` of
 * any `silla` in the list, the method
 * @return The method is returning a boolean value. If the element is found in the list of sillas, it
 * returns false. Otherwise, it returns true.
 */
public  boolean verificar(long elemento){
    for (int i=0; i < sillas.size();i++){
        if (sillas.get(i).getCodigo()==elemento) {
            return false;
            
        }
        
    }
    return true;
}
/**
 * The function assigns a chair based on a given element, returning the first chair if no match is
 * found.
 * 
 * @param elemento It looks like the method `asignarSilla` is trying to find a `Silla` object in a list
 * based on a given `elemento` (presumably a float value). If the `elemento` matches the `codigo` of a
 * `Silla` object in the
 * @return If the element with the specified code is found in the list of sillas, then that specific
 * Silla object is returned. If the element is not found, then the first Silla object in the list is
 * returned.
 */
public Silla asignarSilla(float elemento){
    for (int i=0; i < sillas.size();i++){
        if (sillas.get(i).getCodigo()==elemento) {
            return sillas.get(i);
        }
        
    }
    return sillas.get(0);

}
/**
 * The function assigns a type of chair based on a given element code by iterating through a list of
 * chairs and returning the first character of the chair's type as a string.
 * 
 * @param elemento The method `asignarTipoSilla` takes a `long` parameter named `elemento`. This
 * parameter is used to search for a specific chair in a list of chairs (`sillas`). The method iterates
 * through the list of chairs and compares the `codigo` of each chair with the
 * @return The method `asignarTipoSilla` returns the first character of the `name` of the `Tipo` enum
 * associated with the `Silla` object in the `sillas` list that has a `codigo` matching the `elemento`
 * parameter. If no matching `Silla` object is found, an empty string is returned.
 */
public String asignarTipoSilla(long elemento){
    for (int i=0; i < sillas.size();i++){
        if (sillas.get(i).getCodigo()==elemento) {
            return ""+sillas.get(i).getTipo().name().charAt(0);
            
        }
        
    }
    return "";

}
public ArrayList<Silla> getSillas() {
    return sillas;
}
public void setSillas(ArrayList<Silla> sillas) {
    this.sillas = sillas;
}
public ArrayList<Cliente> getAsistentes() {
    return asistentes;
}
public void setAsistentes(ArrayList<Cliente> asistentes) {
    this.asistentes = asistentes;
}
}



