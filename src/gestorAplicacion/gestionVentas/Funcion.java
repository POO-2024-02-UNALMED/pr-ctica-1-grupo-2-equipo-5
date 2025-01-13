package gestorAplicacion.gestionVentas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.gestionObras.Obra;

public class Funcion {
    private Obra obra;
    private int tiquetesVendidos;
    private LocalDateTime[] horario;
    private Sala Sala;
    private boolean calificador;
    private int audienciaEsperada;


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
    public List<LocalDateTime[]> getHorario() {
        return horario;
    }
    public void setHorario(LocalDateTime[] horario) {
        this.horario = horario;
    }

    //SALA
    public Sala getSala() {
        return Sala;
    }
    public void setSala(Sala sala) {
        Sala = sala;
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

    public Sala salaDisponible(int audienciaEsperada, Sala sala){
        return sala;
    }
    public Funcion(Obra obra, gestorAplicacion.gestionVentas.Sala sala, boolean calificador, int audienciaEsperada) {
        this.obra = obra;
        this.tiquetesVendidos = 0;
        this.horario = createHorario(sala);
        Sala = salaDisponible(audienciaEsperada, sala);
        this.calificador = calificador;
        this.audienciaEsperada = audienciaEsperada;
    }
    
    public LocalDateTime[] createHorario(Sala sala){
        for (int a = 0; a < (sala.getHorario().size()); a++){
            
        }
    }
}
