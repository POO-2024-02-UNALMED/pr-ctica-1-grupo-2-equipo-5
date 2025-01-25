package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.herramientas.Asiento;
public class Sala {
    private static ArrayList <Sala> salas = new ArrayList<>();
    private ArrayList <Silla> sillas = new ArrayList<>();
    private int numeroSala;
    private int metrosCuadrados;
    private Boolean aseado;
    private Boolean ocupado;
    private Empleado trabajador;
    private ArrayList<ArrayList<LocalDateTime>> horario;
    private int capacidad;


    //SILLAS
    public ArrayList<Silla> getSillas() {
        return sillas;
    }
    public void setSillas(ArrayList<Silla> sillas) {
        this.sillas = sillas;
    }

    //ASEADO
    public Boolean getAseado() {
        return aseado;
    }
    public void setAseado(Boolean aseado) {
        this.aseado = aseado;
    }

    //OCUPADO
    public Boolean getOcupado() {
        return ocupado;
    }
    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    //Metros cuadrados
    public int getMetrosCuadrados(){
        return this.metrosCuadrados;
    }
    public void setMetrosCuadrados(int newMetros){
        this.metrosCuadrados = newMetros;
    }

    //Numero de la Sala
    public int getNumeroSala(){
        return this.numeroSala;
    }
    public void setNumeroSala(int newNumero){
        this.numeroSala = newNumero;
    }

    //Total Salas
    public static ArrayList<Sala> getSalas(){
        return salas;
    }
    public static void setSalas(ArrayList<Sala> newSalas){
        salas = newSalas;
    }

    //Presencia trabajador
    public Empleado getTrabajador(){
        return this.trabajador;
    }
    public void setTrabajador(Empleado newTrabajador){
        this.trabajador = newTrabajador;
    }


    public ArrayList<ArrayList<LocalDateTime>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<ArrayList<LocalDateTime>> horario) {
        this.horario = horario;
    }
    public boolean isDisponible(LocalDateTime inicio, LocalDateTime fin) {
        for (ArrayList<LocalDateTime> evento : horario) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int calcCapacidad(ArrayList<Silla> sillas){
        int u;
        u = 0;
        for (int i = 0; i == sillas.size(); i++){
            u = u + 1;
        }
        return u;    
    }

    public void anadirHorario(ArrayList<LocalDateTime> a){
        ArrayList<ArrayList<LocalDateTime>> y;
        y = getHorario();
        y.add(a);
        setHorario(y);
    }
    public Sala(ArrayList<Silla> sillas, int numeroSala, int metrosCuadrados, Boolean aseado, Boolean ocupado,
            Empleado trabajador, ArrayList<ArrayList<LocalDateTime>> horario, int capacidad) {
        this.sillas = sillas;
        this.numeroSala = numeroSala;
        this.metrosCuadrados = metrosCuadrados;
        this.aseado = aseado;
        this.ocupado = ocupado;
        this.trabajador = trabajador;
        this.horario = horario;
        this.capacidad = capacidad;
    }
    public Sala(){
        salas.add(this);
    }

    public ArrayList<Silla> createSillas(int capacidad){
        float u = capacidad / 16;
        int f = 0;
        int s = 10;
        int o = 100;
        int p = 1000;
        int contador = 0;
        ArrayList<Silla> sillas = new ArrayList<>();
        for (int i = 0; i < u * 2; i++){
            Silla silla = new Silla(Asiento.GOLD, f);
            sillas.add(silla);
            f++;
            contador++;
        }
        for (int i = 0; i < u * 2; i++){
            Silla silla = new Silla(Asiento.PREMIUM, s);
            sillas.add(silla);
            s++;
            contador++;
        }
        for (int i = 0; i < u * 4; i++){
            Silla silla = new Silla(Asiento.COMFORT, o);
            sillas.add(silla);
            o++;
            contador++;
        }
        for (int i = 0; i < u * 8; i++){
            Silla silla = new Silla(Asiento.BASICO, p);
            sillas.add(silla);
            p++;
            contador++;
        }

        int t = capacidad - contador;
        for (int i = 0; i < t; i++){
            Silla silla = new Silla(Asiento.BASICO, p);
            sillas.add(silla);
            p++;
            contador++;
        }

        if (!(contador == capacidad)){
            System.out.println("The problem is on here dude");
        }
        return sillas;
    }
    public Sala(int numeroSala,  int metrosCuadrados, int capacidad){
        sillas = createSillas(capacidad);
        this.numeroSala = numeroSala;
        this.metrosCuadrados = metrosCuadrados;
        aseado = true;
        ocupado = false;
        trabajador = null;
        horario = new ArrayList<>();
        this.capacidad = capacidad;
        salas.add(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.numeroSala);
    }
}
