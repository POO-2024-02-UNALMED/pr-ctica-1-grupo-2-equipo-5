package uiMain;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionObras.Actor;
import uiMain.Contador;

public class AlquilerActores {

    public static final float PROMEDIO_ALTO = 4.0f; //por ahora
    
    //acepta entradas del usuario
    static Scanner in = new Scanner(System.in);

    //lista con todos los actores para hacer selección, se importa del atributo de clase Actors
    public List<Actor> actorsForRental;

    public AlquilerActores(){
        this.actorsForRental = new ArrayList<>(Actor.getActors());
    }

    //metodo sobrecargado para uso con diferentes tipos que pregunta y escanea respuesta
    public String ask(String question){
        System.out.println(question);
        String answer = in.nextLine();
        return answer;
    }

    //para el método que acepta enteros se revisa si el numero hace parte de las opciones disponibles
    public int ask(String question, int[] answers){
        System.out.println(question);
        int answer = in.nextInt();
        
        while (!isIn(answers, answer)){
            System.out.println("\n\nLa respuesta introducida no hace parte de las opciones. Intente de nuevo:\n\n");
            System.out.println(question);
            answer = in.nextInt();
        }

        return answer;
    }

    public long longAsk(String question){
        System.out.println(question);
        long answer = in.nextLong();
        return answer;
    }

    //revisa si un entero está en una lista del mismo tipo
    public boolean isIn(int[] list, int value){
        for (int i = 0; i < list.length; i++){
            if (value == list[i]){
                return true;
            }
        }

        return false;
    }

    public boolean isIn(List<String> list, String value){
        for (int i = 0; i < list.size(); i++){
            if (value.equals(list.get(i))){
                return true;
            }
        }

        return false;
    }

    public boolean isIn(List<Actor> list, Actor value){

        for (int i = 0; i < list.size(); i++){
            if (value.equals(list.get(i))){
                return true;
            }
        }
        return false;       
    }

//main para ejecutar la lógica del código y revisar fallos
    public static void main(String[] args){


        //sujetos de prueba -------------------------
        Actor actor1 = new Actor();
        List<String> genres = new ArrayList<String>();
        genres.add("Comedia");
        genres.add("Tragicomedia");
        actor1.setGeneros(genres);
        actor1.setPromedio(4.1f);

        genres.add("Comedia");
        Actor actor2 = new Actor();
        actor2.setGeneros(genres);
        actor2.setPromedio(2.5f);

        genres.add("Romance");
        Actor actor3 = new Actor();
        actor3.setGeneros(genres);
        actor3.setPromedio(4.8f);
        actor3.setEdad(22);
        actor3.setSexo("F");
        //--------------------------------------------

        System.out.println(Actor.getActors());

        //primera ronda de preguntas
        int[] options = new int[4];
        options[0] = 1; options[1] = 2;

        AlquilerActores alquiler = new AlquilerActores();

        //PREGUNTA NO. 1
        int rolActor = alquiler.ask("¿Qué tipo de papel desempeñará el actor?\n1. Rol principal.\n2. Rol secundario.", options);

        //reservar los de promedio alto solo para roles principales
        if (rolActor == 1){ 
            alquiler.actorsForRental.removeIf(actor -> actor.getPromedio() < PROMEDIO_ALTO); 
        } else {
            alquiler.actorsForRental.removeIf(actor -> actor.getPromedio() > PROMEDIO_ALTO);}

        options[2] = 3; options[3] = 4;

        //PREGUNTA NO. 2
        int tipoObra = alquiler.ask("\n¿Qué tipo de obra es?\n1. Tragedia.\n2. Comedia.\n3. Romance.\n4. Tragicomedia.", options);
        
        //que se borren los actores que no tengan el género buscado en sus atributos
        switch(tipoObra){

            case 1:
            alquiler.actorsForRental.removeIf(actor -> !alquiler.isIn(actor.getGeneros(), "Tragedia"));
            break;

            case 2:
            alquiler.actorsForRental.removeIf(actor -> !alquiler.isIn(actor.getGeneros(), "Comedia"));
            break;

            case 3:
            alquiler.actorsForRental.removeIf(actor -> !alquiler.isIn(actor.getGeneros(), "Romance"));
            break;

            case 4:
            alquiler.actorsForRental.removeIf(actor -> !alquiler.isIn(actor.getGeneros(), "Tragicomedia"));
            break;
        }
        
        //para que la entrada de horarioCliente no se omita
        in.nextLine();


        //PREGUNTA NO. 3
        String horarioCliente = alquiler.ask("\n¿En qué horario necesita el actor? (Responda en formato 24 horas HH:MM)");

        //pendiente: diseñar lógica para revisar el horario

        //preseleccionados

        if (alquiler.actorsForRental.size() == 0){ 
            System.out.println("\nNo hay artistas disponibles con los requerimientos pedidos."); return;
        } else {
            System.out.println("\n" + alquiler.actorsForRental.size() + " actores encontrados durante la preselección.");
        }

        //pendiente: logica de revision de historial de empresa y prioridad de actores contratados
    
        //búsqueda avanzada

        int[] two = {1, 2};
        int advancedSearch = alquiler.ask("¿Deseas hacer búsqueda avanzada? (incluye filtros por edad, sexo y  cantidad de obras actuadas).\n1. Sí\n2. No.\n", two);

        if (advancedSearch == 1){

            List<Contador> contadores = new ArrayList<Contador>();

            for (Actor actor : alquiler.actorsForRental){
                contadores.add(new Contador(actor, 0));
            }

            int edad = alquiler.ask("¿Qué tipo de edad busca?\n1. Infantil\n2. Juvenil.\n3. Adulto.\n4. Adulto mayor", options);

            switch (edad){

                case 1: //infantil
                alquiler.actorsForRental.removeIf(actor -> actor.getEdad() >= 15);
                break;

                case 2: //juvenil
                alquiler.actorsForRental.removeIf(actor -> actor.getEdad() < 15);
                alquiler.actorsForRental.removeIf(actor -> actor.getEdad() >= 24);
                break;

                case 3: //adulto
                alquiler.actorsForRental.removeIf(actor -> actor.getEdad() < 24 );
                alquiler.actorsForRental.removeIf(actor -> actor.getEdad() >= 70);
                break;

                case 4: //adulto mayor
                alquiler.actorsForRental.removeIf(actor -> actor.getEdad() < 70);
                break;
            }

            for (Contador contador : contadores){

                if (alquiler.isIn(alquiler.actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            in.nextLine();

            int sexo = alquiler.ask("¿Qué sexo debe tener el actor?\n1. Masculino.\n2. Femenino.\n", two);

            if (sexo == 1){ //si es masculino, remueve el sexo femenino y viceversa
                alquiler.actorsForRental.removeIf(actor -> actor.getSexo().equals("F"));
            } else {
                alquiler.actorsForRental.removeIf(actor -> actor.getSexo().equals("M"));
            }

            for (Contador contador : contadores){

                if (alquiler.isIn(alquiler.actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            in.nextLine();

            //pendiente: agregar cantidad de obras actuadas

            contadores.removeIf(contador -> contador.numero < 2);

            if (contadores.size() == 0){
                System.out.println("No se encontraron actores que se ajusten bien a las características."); return;}

            List<Actor> advancedList = new ArrayList<Actor>();

            for (Contador contador : contadores){
                advancedList.add(contador.getActor());
            }
            
            alquiler.actorsForRental = advancedList;
            
            System.out.println(alquiler.actorsForRental.size() + " actores se ajustaron a dos o más características avanzadas.");

            }

        long presupuesto = alquiler.longAsk("¿Cuál es el presupuesto máximo para el actor?");

        alquiler.actorsForRental.removeIf(actor -> actor.getPrecioContrato() > presupuesto);

        if (alquiler.actorsForRental.size() == 0){
            System.out.println("No se hallaron actores para el presupuesto");
        } else {

            //pendiente: imprimir lista de a 5 actores con todos los atributos.
            System.out.println(alquiler.actorsForRental);

        }
    
    }
}

