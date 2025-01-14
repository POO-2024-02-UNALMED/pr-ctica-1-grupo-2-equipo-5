package gestorAplicacion.gestionClases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionVentas.Sala;

public class Clase{
    //private Aptitud area; Por ahora comento esta porque no sé exactamente como usarla
    private Profesor profesor;
    private Artista alumno;
    private List<LocalDateTime[]> horario;
    private Boolean aprobada;
    private float costoMatricula;
    private String materiaNombre; // Nombre de la materia que se imparte en la clase
    private int nivel; /* Nivel de la clase: 1 = Introducción, 2 = Profundización, 3 = Perfeccionamiento
                        creo que es prudente añadirlo para la funcionalidad 4*/
    private Sala sala; // Sala asignada para la clase

    //Constructor
    /*public Clase(Aptitud area, Profesor profesor, Artista alumno, Boolean aprobada, float costoMatricula) {
        //this.area = area; (Por ahora comentado por lo dicho anteriormente)
        this.profesor = profesor;
        this.alumno = alumno;               CONSTRUCTOR ANTIGUO
        this.aprobada = aprobada;
        this.costoMatricula = costoMatricula;
        this.horario = new ArrayList<>();
    }*/
    public Clase(Profesor profesor, Artista alumno, List<LocalDateTime[]> horario, Boolean aprobada,
            float costoMatricula, String materiaNombre, int nivel, Sala sala) {
        this.profesor = profesor;
        this.alumno = alumno;
        this.horario = new ArrayList<>();
        this.aprobada = aprobada;
        this.costoMatricula = costoMatricula;
        this.materiaNombre = materiaNombre;
        this.nivel = nivel;
        this.sala = sala;
    }
    
    //GETTERS Y SETTERS

    

    //Profesor
    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    //Alumno (Artista)
    public Artista getAlumno() {
        return alumno;
    }
    public void setAlumno(Artista alumno) {
        this.alumno = alumno;
    }

    //Horario
    public List<LocalDateTime[]> getHorario() {
        return horario;
    }
    public void setHorario(List<LocalDateTime[]> horario) {
        this.horario = horario;
    }

    //Aprobada
    public Boolean getAprobada() {
        return aprobada;
    }
    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    //Matricula Costo
    public float getCostoMatricula() {
        return costoMatricula;
    }
    public void setCostoMatricula(float costoMatricula) {
        this.costoMatricula = costoMatricula;
    }

    public String getMateriaNombre() {
        return materiaNombre;
    }

    public void setMateriaNombre(String materiaNombre) {
        this.materiaNombre = materiaNombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}