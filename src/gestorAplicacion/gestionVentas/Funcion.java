package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;

import gestorAplicacion.gestionObras.Obra;

public class Funcion {
    private Obra obra;
    private int tiquetesVendidos;
    private ArrayList<LocalDateTime> horario;
    private Sala sala;
    private boolean calificador;
    private int audienciaEsperada;
    static Funcion[] funcionesCreadas;


    //OBRA
    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }

    //TIQUETES VENDIDOS
    public int getTiquetesVendidos() {
        return tiquetesVendidos;
    }
    public void setTiquetesVendidos(int tiquetesVendidos) {
        this.tiquetesVendidos = tiquetesVendidos;
    }

    //HORARIO
    public LocalDateTime[] getHorario() {
        return horario;
    }
    public void setHorario(LocalDateTime[] horario) {
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

    public Sala salaDisponible(int audienciaEsperada){
        return sala;
    }
    public Funcion(Obra obra, boolean calificador, int audienciaEsperada) {
        this.obra = obra;
        this.tiquetesVendidos = 0;
        this.horario = createHorario(sala);
        sala = salaDisponible(audienciaEsperada);
        this.calificador = calificador;
        this.audienciaEsperada = audienciaEsperada;
    }
    public Funcion(){

    }
    public Funcion()
    
    public ArrayList<LocalDateTime> createHorario(Sala sala){
        for (int a = 0; a < (sala.getHorario().size()); a++){
            
        }
    }

        public void createFunciones(Obra obra, int numero, float calificacion) {
        Scanner sc = new Scanner(System.in);
        byte co;
        if (numero > obra.funcionesRecomendadas(calificacion) + 2){
            System.out.println("ALERTA, DEMASIADAS FUNCIONES");
            System.out.println("¿DESEA CONTINUAR?");
            co = sc.nextByte();
            switch (co) {
                case 1:
                for (int i = 0; i < numero; i++){
                    Funcion funcion = new Funcion(this.obra, calificador, audienciaEsperada);
                }
                
                    break;
            
                default:
                    break;
            }
        }
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
}
