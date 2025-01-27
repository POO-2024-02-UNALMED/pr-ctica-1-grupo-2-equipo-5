package test;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionObras.Director;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Genero;
import gestorAplicacion.gestionClases.Profesor;
import gestorAplicacion.gestionFinanciera.Empleado;
import uiMain.Main;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import baseDatos.Deserializador;
import baseDatos.Teatro;

public class funcionalidad4 {
    public static void main(String[] args) throws InterruptedException {

        //OBRAS
        String filename = "teatro.txt";
        String path = "src" + File.separator + "baseDatos" + File.separator + "temp" + File.separator + filename;
        Deserializador.loadState(path);

        Obra obra1 = new Obra("El gran show", Genero.CIRCO,Duration.ofHours(2).plusMinutes(30));
        Obra obra2 = new Obra("La tragedia de Romeo", Genero.DRAMA,Duration.ofHours(2).plusMinutes(30));
        Obra obra3 = new Obra("Risas aseguradas", Genero.COMEDIA, Duration.ofHours(2).plusMinutes(30));
        Obra obra4 = new Obra("El misterio en la mansion", Genero.TERROR,Duration.ofHours(2).plusMinutes(30));
        Obra obra5 = new Obra("Bailando en el escenario", Genero.MUSICAL, Duration.ofHours(2).plusMinutes(30));
        obra1.setCalificacion(1);
        obra1.setCalificacion(1);
        obra1.setCalificacion(2);

        obra2.setCalificacion(5);
        obra2.setCalificacion(8);
        obra2.setCalificacion(6);

        obra3.setCalificacion(7);
        obra3.setCalificacion(6);
        obra3.setCalificacion(8);

        obra4.setCalificacion(0);
        obra4.setCalificacion(0);
        obra4.setCalificacion(1);

        obra5.setCalificacion(10);
        obra5.setCalificacion(9);
        obra5.setCalificacion(8);

        


        

        // Crear profesores (se añadirán automáticamente a la lista tipoProfesor)
        ArrayList<Aptitud> aptitudesProfesor1 = new ArrayList<Aptitud>(Arrays.asList(Aptitud.CANTO, Aptitud.BAILE));
        ArrayList<Aptitud> aptitudesProfesor2 = new ArrayList<Aptitud>(Arrays.asList(Aptitud.DISCURSO, Aptitud.EMOCIONALIDAD, Aptitud.IMPROVISACION));

        Profesor profesor1 = new Profesor("Raúl Gómez", 1010131, aptitudesProfesor1);
        Profesor profesor2 = new Profesor("María López", 1011, aptitudesProfesor2);
        
        // Crear artistas (actores y un director)
        Artista actor1 = new Actor("Carlos Moreno", 12);
        Artista actor2 = new Actor("Ana Pérez", 11);
        Artista director1 = new Director("Luis Herrera", 22);

        actor2.getCuenta().ingresar(100000);
        
        Teatro.getInstancia().getTipoProfesor().add(0,profesor1);
        Teatro.getInstancia().getTipoProfesor().add(profesor2);



        
        ((Actor) actor2).setCalificacionPorAptitud(Aptitud.BAILE, 3.0);
        ((Actor) actor2).setCalificacionPorAptitud(Aptitud.DISCURSO, 3.0);
        ((Actor) actor2).setCalificacionPorAptitud(Aptitud.EMOCIONALIDAD, 2.9);
        ((Actor) actor2).setCalificacionPorAptitud(Aptitud.IMPROVISACION, 1.8);
        ((Actor) actor2).setCalificacionPorAptitud(Aptitud.CANTO, 1.0);
        
        // Caso 1: Actor válido con profesores disponibles
        Main.gestionClases();
    }
}

