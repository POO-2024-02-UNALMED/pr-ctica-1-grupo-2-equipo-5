package test;

import java.time.Duration;

import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.herramientas.Genero;
import uiMain.Main;

public class funci_1 {
    public static void prueba() {
        
    
    Obra obra1 = new Obra("El gran show", Genero.CIRCO,Duration.ofHours(2).plusMinutes(30));
        Obra obra2 = new Obra("La tragedia de Romeo", Genero.DRAMA,Duration.ofHours(2).plusMinutes(30));
        Obra obra3 = new Obra("Risas aseguradas", Genero.COMEDIA, Duration.ofHours(2).plusMinutes(30));
        Obra obra4 = new Obra("El misterio en la mansion", Genero.TERROR,Duration.ofHours(2).plusMinutes(30));
        Obra obra5 = new Obra("Bailando en el escenario", Genero.MUSICAL, Duration.ofHours(2).plusMinutes(30));

        obra1.setCalificacion(8);
        obra1.setCalificacion(7);
        obra1.setCalificacion(9);

        obra2.setCalificacion(5);
        obra2.setCalificacion(8);
        obra2.setCalificacion(6);

        obra3.setCalificacion(7);
        obra3.setCalificacion(6);
        obra3.setCalificacion(8);

        obra4.setCalificacion(9);
        obra4.setCalificacion(7);
        obra4.setCalificacion(8);

        obra5.setCalificacion(10);
        obra5.setCalificacion(9);
        obra5.setCalificacion(8);
        Sala sala1 = new Sala(1, 100, 24);
        Sala sala2 = new Sala(2, 200, 48);
        Sala sala3 = new Sala(3, 50, 16);
        Sala sala4 = new Sala(4, 150, 24);
        Funcion func1 = new Funcion(obra1,sala1);
        Funcion func2 = new Funcion(obra2,sala2);
        Funcion func3 = new Funcion(obra3,sala3);
        Funcion func4 = new Funcion(obra4,sala4);
        
        
        




}}
