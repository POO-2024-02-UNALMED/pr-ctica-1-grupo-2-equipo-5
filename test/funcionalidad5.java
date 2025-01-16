package test;

import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionVentas.Cliente;
import uiMain.Main;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class funcionalidad5 {

    public static Scanner in = new Scanner(System.in);
       public static void main(String[] args){
            //sujetos de prueba -------------------------//
            Actor actor1 = new Actor("Pedro Pascal", 10292122);
            List<String> genres = new ArrayList<String>();
            genres.add("Comedia");
            genres.add("Tragicomedia");
            actor1.setGeneros(genres);
            actor1.setCalificacion(4.1f);
            actor1.setSexo('M');
            actor1.setEdad(49);
    
            genres.add("Comedia");
            Actor actor2 = new Actor("Eddie Muprhy", 9032723);
            actor2.setGeneros(genres);
            actor2.setCalificacion(3.8f);
            actor2.setSexo('M');
            actor2.setEdad(62);
    
            genres.add("Romance");
            Actor actor3 = new Actor("Emma Stone", 90234243);
            actor3.setGeneros(genres);
            actor3.setCalificacion(4.6f);
            actor3.setSexo('F');
            actor3.setEdad(36);

            Actor actor4 = new Actor("Antonio Banderas", 90234263);
            actor4.setGeneros(genres);
            actor4.setCalificacion(4.7f);
            actor4.setSexo('M');
            actor4.setEdad(64);

            Actor actor5= new Actor("Samuel L. Jackson", 91234203);
            actor5.setGeneros(genres);
            actor5.setCalificacion(5.0f);
            actor5.setSexo('M');
            actor5.setEdad(76);

            Actor actor6 = new Actor("Orson Welles", 90230543);
            actor3.setGeneros(genres);
            actor3.setCalificacion(4.2f);
            actor3.setSexo('M');
            actor3.setEdad(85);

            Actor actor7 = new Actor("John Travolta", 60234243);
            actor7.setGeneros(genres);
            actor7.setCalificacion(4.5f);
            actor7.setSexo('M');
            actor7.setEdad(70);

            Actor actor8 = new Actor("Carmen Maura", 90456243);
            actor8.setGeneros(genres);
            actor8.setCalificacion(4.3f);
            actor8.setSexo('F');
            actor8.setEdad(79);
            
            Actor actor9 = new Actor("Florina Lemaitre", 9076243);
            actor9.setGeneros(genres);
            actor9.setCalificacion(3.9f);
            actor9.setSexo('F');
            actor9.setEdad(36);

            Cliente warner = new Cliente("Empresa", 246);
            warner.getHistorial().add(actor3);
            warner.getCuentaBancaria().ingresar( (double) 6_000_000 );
            Tesoreria tesoreria = new Tesoreria();

            //--------------------------------------------//

            Main.AlquilarActor();
        }
    }
