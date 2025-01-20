package test;

import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.herramientas.Genero;

public class funci_1 {
    public static void prueba() {
        
    Funcion func1 = new Funcion();
    Obra obra1 = new Obra("El gran show", Genero.CIRCO, "1h 30min");
        Obra obra2 = new Obra("La tragedia de Romeo", Genero.DRAMA, "2h");
        Obra obra3 = new Obra("Risas aseguradas", Genero.COMEDIA, "45min");
        Obra obra4 = new Obra("El misterio en la mansion", Genero.TERROR, "1h 15min");
        Obra obra5 = new Obra("Bailando en el escenario", Genero.MUSICAL, "2h 20min");

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
        




}}
