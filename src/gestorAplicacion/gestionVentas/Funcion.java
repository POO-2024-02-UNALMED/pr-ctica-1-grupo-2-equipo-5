package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

import gestorAplicacion.gestionObras.*;

public class Funcion {
    private Obra obra;
    private int tiquetesVendidos;
    private ArrayList<LocalDateTime> horario = new ArrayList<>();
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
    public Funcion(Obra obra, ArrayList<LocalDate> week) {
        this.obra = obra;
        this.tiquetesVendidos = 0;
        this.horario = createHorario(week);
        this.sala = null;
        this.calificador = doWeNeedACalificador();
        this.audienciaEsperada = obra.getAudienciaEsperada();
    }
    public Funcion(){

    }
    public Funcion(LocalDateTime hora){
        horario.add(hora);
        horario.add(hora);
    }
    
    public ArrayList<LocalDateTime> createHorario(ArrayList<LocalDate> week){
        ArrayList<LocalDateTime> horario = new ArrayList<>();
        LocalTime inicioFranja = this.obra.getFranjaHoraria().get(0);
        for (Sala sala : Sala.getSalas()){
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
                            break;
                        }
                        inicioFranjaITE = inicioFranjaITE.plusMinutes(30);
                    }
                }
                if (!horario.isEmpty()){
                    break;
                }
            }
            if (!horario.isEmpty()){
                this.getSala().anadirHorario(horario);
                break;
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
        return a;
    }
}



