package gestorAplicacion.gestionClases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.Enumeraciones.Aptitud;
import gestorAplicacion.gestionObras.Artista;

public class Clase{
    private Aptitud area;
    private Profesor profesor;
    private Artista alumno;
    private List<LocalDateTime[]> horario;
    private Boolean aprobada;
    private float costoMatricula;

    //Constructor
    public Clase(Aptitud area, Profesor profesor, Artista alumno, Boolean aprobada, float costoMatricula) {
        this.area = area;
        this.profesor = profesor;
        this.alumno = alumno;
        this.aprobada = aprobada;
        this.costoMatricula = costoMatricula;
        this.horario = new ArrayList<>();
    }
    
    //GETTERS Y SETTERS

    //Area
    public Aptitud getArea() {
        return area;
    }
    public void setArea(Aptitud area) {
        this.area = area;
    }

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
}