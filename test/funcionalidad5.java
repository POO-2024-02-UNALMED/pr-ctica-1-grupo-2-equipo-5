package test;

import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionVentas.Cliente;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Genero;
import uiMain.Main;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class funcionalidad5 {

    public static Scanner in = new Scanner(System.in);
       public static void main(String[] args){
            //sujetos de prueba -------------------------//
            Actor actor1 = new Actor("Pedro Pascal", 10292122);
            List<Genero> genres = new ArrayList<>();
            genres.add(Genero.COMEDIA);
            genres.add(Genero.CIRCO);
            actor1.setGeneros(new ArrayList<>(genres));
            actor1.setCalificacion(4.1f);
            actor1.setSexo('M');
            actor1.setEdad(49);
            actor1.addAptitud(Aptitud.IMPROVISACION);
            actor1.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 4.1);

            genres.add(Genero.DRAMA);
            Actor actor2 = new Actor("Eddie Muprhy", 9032723);
            actor2.setGeneros(new ArrayList<>(genres));
            actor2.setCalificacion(3.8f);
            actor2.setSexo('M');
            actor2.setEdad(62);
            actor2.addAptitud(Aptitud.IMPROVISACION);
            actor2.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 4.1);
    
            genres.add(Genero.EXPERIMENTAL);
            Actor actor3 = new Actor("Emma Stone", 90234243);
            actor3.setGeneros(new ArrayList<>(genres));
            actor3.setCalificacion(4.6f);
            actor3.setSexo('F');
            actor3.setEdad(36);
            actor3.addAptitud(Aptitud.IMPROVISACION);
            actor3.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);

            genres.add(Genero.FANTASIA);
            Actor actor4 = new Actor("Antonio Banderas", 90234263);
            actor4.setGeneros(new ArrayList<>(genres));
            actor4.setCalificacion(4.7f);
            actor4.setSexo('M');
            actor4.setEdad(64);
            actor4.addAptitud(Aptitud.IMPROVISACION);
            actor4.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);

            genres.add(Genero.MUSICAL);
            Actor actor5= new Actor("Samuel L. Jackson", 91234203);
            actor5.setGeneros(new ArrayList<>(genres));
            actor5.setCalificacion(5.0f);
            actor5.setSexo('M');
            actor5.setEdad(76);
            actor5.addAptitud(Aptitud.IMPROVISACION);
            actor5.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);

            genres.add(Genero.ROMANCE);
            Actor actor6 = new Actor("Orson Welles", 90230543);
            actor6.setGeneros(new ArrayList<>(genres));
            actor6.setCalificacion(4.2f);
            actor6.setSexo('M');
            actor6.setEdad(85);
            actor6.addAptitud(Aptitud.IMPROVISACION);
            actor6.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);

            genres.add(Genero.TERROR);
            Actor actor7 = new Actor("John Travolta", 60234243);
            actor7.setGeneros(new ArrayList<>(genres));
            actor7.setCalificacion(4.5f);
            actor7.setSexo('M');
            actor7.setEdad(70);
            actor7.addAptitud(Aptitud.IMPROVISACION);
            actor7.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);

            Actor actor8 = new Actor("Carmen Maura", 90456243);
            actor8.setGeneros(new ArrayList<>(genres));
            actor8.setCalificacion(4.3f);
            actor8.setSexo('F');
            actor8.setEdad(79);
            actor8.addAptitud(Aptitud.IMPROVISACION);
            actor8.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);
            
            Actor actor9 = new Actor("Florina Lemaitre", 9076243);
            actor9.setGeneros(new ArrayList<>(genres));
            actor9.setCalificacion(3.9f);
            actor9.setSexo('F');
            actor9.setEdad(36);
            actor9.addAptitud(Aptitud.IMPROVISACION);
            actor9.setCalificacionPorAptitud(Aptitud.IMPROVISACION, 3.1);

            Cliente warner = new Cliente("Empresa", 246);
            warner.getHistorial().add(actor3);
            warner.getCuentaBancaria().ingresar( (double) 3_700_000 );
            Tesoreria tesoreria = new Tesoreria(0f, 100f);

            //--------------------------------------------//
            Main.ContratarActor();
        }
    }
