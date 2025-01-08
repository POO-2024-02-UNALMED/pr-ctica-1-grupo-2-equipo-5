package gestorAplicacion.gestionVentas;

import java.util.ArrayList;
import gestorAplicacion.gestionObras.Obra;

public class Funcion {
    Obra obra;
    Tiquete tiquetesVendidos;
    ArrayList<ArrayList<Integer>> horario = new ArrayList<>();
    Sala Sala;
    boolean calificador;
    long audienciaEsperada;



    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    public Tiquete getTiquetesVendidos() {
        return tiquetesVendidos;
    }
    public void setTiquetesVendidos(Tiquete tiquetesVendidos) {
        this.tiquetesVendidos = tiquetesVendidos;
    }
    public ArrayList<ArrayList<Integer>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<ArrayList<Integer>> horario) {
        this.horario = horario;
    }
    public Sala getSala() {
        return Sala;
    }
    public void setSala(Sala sala) {
        Sala = sala;
    }
    public boolean isCalificador() {
        return calificador;
    }
    public void setCalificador(boolean calificador) {
        this.calificador = calificador;
    }
    public long getAudienciaEsperada() {
        return audienciaEsperada;
    }
    public void setAudienciaEsperada(long audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

}
