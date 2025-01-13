package uiMain;

import java.util.ArrayList;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.herramientas.Contador;
import java.text.NumberFormat;
import java.util.Locale;
import gestorAplicacion.herramientas.input;

public class Main {

    public static void main(String args[]){

    input.setScanner( new Scanner(System.in) );
    


    //Inicio Funcionalidad 2
        // public void AsearSalas(){
        //     ArrayList<Sala> totalSalas = Sala.getSalas();
        //     ArrayList<Empleado> Empleados = Empleado.getEmpleadosPorRendimiento();
        //     for(Sala Elemento : totalSalas){
        //         if (Elemento.getAseado() == false && Elemento.isAseador() == false && Elemento.getOcupado() == false){
        //             for(Empleado Aseador : Empleados){
        //                 if(Aseador.getOcupacion() == "Aseador" && Aseador.isDisponible() == true){
        //                         Elemento.setAseador(true);
        //                         Elemento.setOcupado(true);
        //                         Aseador.setDisponible(false);
        //                         break;
        //                 }
        //             }
        //         }
        //     }
        // }

        // }


    String dash = "~";

    System.out.println(dash.repeat(50));
    System.out.println("Sistema de administración del Teatro Carlos Mayolo");
    System.out.println(dash.repeat(50));

    byte[] options = {1, 2, 3, 4, 5};
    byte task = input.ask("Seleccione la tarea a realizar: \n1. Venta de tiquetes.\n2. Gestión de empleados.\n3. Gestión de obras.\n4. Gestión de clases.\n5. Alquiler de actores.\n", options);

    switch (task){

        case 1:
        {}break;

        case 2:
        {}break;

        case 3:
        {}break;

        case 4:
        {}break;

        case 5: AlquilarActor();
    }
    
    }


    public static void AlquilarActor(){

        final float CALIFICACION_ALTA = 4.0f; //por ahora
        List<Actor> actorsForRental = new ArrayList<>(Actor.getActors());

        //primera ronda de preguntas
        byte[] options = new byte[4];
        options[0] = 1; options[1] = 2;
        
        //PREGUNTA NO. 1
        byte rolActor = input.ask("¿Qué tipo de papel desempeñará el actor?\n1. Rol principal.\n2. Rol secundario.", options);


        //reservar los de calificacion alta solo para roles principales
        if (rolActor == 1){ 
            actorsForRental.removeIf(actor -> actor.getCalificacion() < CALIFICACION_ALTA); 
        } else {
            actorsForRental.removeIf(actor -> actor.getCalificacion() > CALIFICACION_ALTA);}

        options[2] = 3; options[3] = 4;

        //PREGUNTA NO. 2
        byte tipoObra = input.ask("\n¿Qué tipo de obra es?\n1. Tragedia.\n2. Comedia.\n3. Romance.\n4. Tragicomedia.", options);
        
        //que se borren los actores que no tengan el género buscado en sus atributos
        switch(tipoObra){

            case 1:
            actorsForRental.removeIf(actor -> !input.isIn(actor.getGeneros(), "Tragedia"));
            break;

            case 2:
            actorsForRental.removeIf(actor -> !input.isIn(actor.getGeneros(), "Comedia"));
            break;

            case 3:
            actorsForRental.removeIf(actor -> !input.isIn(actor.getGeneros(), "Romance"));
            break;

            case 4:
            actorsForRental.removeIf(actor -> !input.isIn(actor.getGeneros(), "Tragicomedia"));
            break;
        }
        
        //para que la entrada de horarioCliente no se omita
        input.getScanner().nextLine();

        //PREGUNTA NO. 3
        String horarioCliente = input.ask("\n¿En qué horario necesita el actor? (Responda en formato 24 horas HH:MM)");

        //pendiente: diseñar lógica para revisar el horario

        //preseleccionados

        if (actorsForRental.size() == 0){ 
            System.out.println("\nNo hay artistas disponibles con los requerimientos pedidos."); return;
        } else {
            System.out.println("\n" + actorsForRental.size() + " actores encontrados durante la preselección.\n");
        }

        //pendiente: logica de revision de historial de empresa y prioridad de actores contratados
    
        //búsqueda avanzada

        byte[] two = {1, 2};
        byte advancedSearch = input.ask("¿Deseas hacer búsqueda avanzada? (incluye filtros por edad, sexo y  cantidad de obras actuadas).\n1. Sí\n2. No.\n", two);

        if (advancedSearch == 1){

            List<Contador> contadores = new ArrayList<Contador>();

            for (Actor actor : actorsForRental){
                contadores.add(new Contador(actor, 0));
            }

            byte edad = input.ask("¿Qué tipo de edad busca?\n1. Infantil\n2. Juvenil.\n3. Adulto.\n4. Adulto mayor", options);

            switch (edad){

                case 1: //infantil
                actorsForRental.removeIf(actor -> actor.getEdad() >= 15);
                break;

                case 2: //juvenil
                actorsForRental.removeIf(actor -> actor.getEdad() < 15);
                actorsForRental.removeIf(actor -> actor.getEdad() >= 24);
                break;

                case 3: //adulto
                actorsForRental.removeIf(actor -> actor.getEdad() < 24 );
                actorsForRental.removeIf(actor -> actor.getEdad() >= 70);
                break;

                case 4: //adulto mayor
                actorsForRental.removeIf(actor -> actor.getEdad() < 70);
                break;
            }

            for (Contador contador : contadores){

                if (input.isIn(actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            input.getScanner().nextLine();

    byte sexo = input.ask("¿Qué sexo debe tener el actor?\n1. Masculino.\n2. Femenino.\n", two);

            if (sexo == 1){ //si es masculino, remueve el sexo femenino y viceversa
                actorsForRental.removeIf(actor -> actor.getSexo() == 'F');
            } else {
                actorsForRental.removeIf(actor -> actor.getSexo() == 'M');
            }

            for (Contador contador : contadores){

                if (input.isIn(actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            input.getScanner().nextLine();

            //pendiente: agregar cantidad de obras actuadas

            contadores.removeIf(contador -> contador.numero < 2);

            if (contadores.size() == 0){
                System.out.println("No se encontraron actores que se ajusten bien a las características."); return;}

            List<Actor> advancedList = new ArrayList<Actor>();

            for (Contador contador : contadores){
                advancedList.add(contador.getActor());
            }
            
            actorsForRental = advancedList;
            
            System.out.println(actorsForRental.size() + " actores se ajustaron a dos o más características avanzadas.");

            }

        long presupuesto = input.longAsk("¿Cuál es el presupuesto máximo para el actor?\n");

        actorsForRental.removeIf(actor -> actor.getPrecioContrato() > presupuesto);

        if (actorsForRental.size() == 0){
            System.out.println("No se hallaron actores para el presupuesto");
        } else {

            //pendiente: imprimir lista de a 5 actores con todos los atributos.
        NumberFormat intToCop = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            
        for (Actor actor : actorsForRental){
            String precio = intToCop.format((int) actor.getPrecioContrato());
            System.out.println(actor); System.out.println("Precio de contratación: " + precio); System.out.println();}
            
                }
    }

}