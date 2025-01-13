package gestorAplicacion.gestionVentas;

import java.util.ArrayList;
import gestorAplicacion.gestionObras.Obra;

public class Funcion {
    private Obra obra;
    private int tiquetesVendidos;
    private ArrayList<ArrayList<Integer>> horario = new ArrayList<>();
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
    public ArrayList<ArrayList<Integer>> getHorario() {
        return horario;
    }
    public void setHorario(ArrayList<ArrayList<Integer>> horario) {
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
    public Funcion(Obra obra, ArrayList<ArrayList<Integer>> horario,
            gestorAplicacion.gestionVentas.Sala sala, boolean calificador, int audienciaEsperada) {
        this.obra = obra;
        this.tiquetesVendidos = 0;
        this.horario = horario;
        Sala = sala;
        this.calificador = calificador;
        this.audienciaEsperada = audienciaEsperada;
    }
    

}
