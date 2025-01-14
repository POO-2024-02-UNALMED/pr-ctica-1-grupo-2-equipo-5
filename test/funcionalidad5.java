package test;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionVentas.Cliente;
import uiMain.Main;
import java.util.List;
import java.util.ArrayList;
import gestorAplicacion.herramientas.input;
import java.util.Scanner;

public class funcionalidad5 {
       public static void main(String[] args){
            //sujetos de prueba -------------------------
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
            actor2.setCalificacion(2.8f);
            actor2.setSexo('M');
            actor2.setEdad(62);
    
            genres.add("Romance");
            Actor actor3 = new Actor("Emma Stone", 90234243);
            actor3.setGeneros(genres);
            actor3.setCalificacion(4.8f);
            actor3.setSexo('F');
            actor3.setEdad(36);
            
            Cliente warner = new Cliente("Empresa");

            //--------------------------------------------

            input.setScanner( new Scanner(System.in) );
            Main.AlquilarActor(warner);
        }
    }
