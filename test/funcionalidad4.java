package test;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionObras.Director;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.herramientas.Genero;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionClases.Profesor;
import uiMain.Main;

import java.util.ArrayList;

public class funcionalidad4 {
    public static void main(String[] args) throws InterruptedException {

        //OBRAS

        Obra obra1 = new Obra("El gran show", Genero.CIRCO, "1h 30min");
        Obra obra2 = new Obra("La tragedia de Romeo", Genero.DRAMA, "2h");
        Obra obra3 = new Obra("Risas aseguradas", Genero.COMEDIA, "45min");
        Obra obra4 = new Obra("El misterio en la mansion", Genero.TERROR, "1h 15min");
        Obra obra5 = new Obra("Bailando en el escenario", Genero.MUSICAL, "2h 20min");

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
        Funcion func1 = new Funcion(obra1);
        Funcion func2 = new Funcion(obra2);
        Funcion func3 = new Funcion(obra3);
        Funcion func4 = new Funcion(obra4);
        Funcion func5 = new Funcion(obra5);


        

        // Crear profesores (se añadirán automáticamente a la lista tipoProfesor)
        Profesor profesor1 = new Profesor("Raúl Gómez", 1010);
        Profesor profesor2 = new Profesor("María López", 1011);

        // Crear artistas (actores y un director)
        Artista actor1 = new Actor("Carlos Moreno", 1234567);
        Artista actor2 = new Actor("Ana Pérez", 1111111);
        Artista director1 = new Director("Luis Herrera", 2222222);

        // Caso 1: Actor válido con profesores disponibles
        Main.gestionClases();

        /*// Caso 2: Actor válido pero SIN profesores disponibles
        System.out.println("\nCASO 2: Actor válido pero SIN profesores disponibles");
        Empleado.getTipoProfesor().clear(); // Eliminar todos los profesores
        Main.gestionClases();

        // Caso 3: Artista que NO es un actor
        System.out.println("\nCASO 3: Artista que no es un actor");
        Empleado.getTipoProfesor().add(profesor1); // Añadir al menos un profesor de nuevo
        Main.gestionClases();

        // Caso 4: Actor válido, profesores disponibles, y más de una iteración (casting exitoso)
        System.out.println("\nCASO 4: Actor válido con múltiples rondas de evaluación");
        Main.gestionClases();*/
    }
}

