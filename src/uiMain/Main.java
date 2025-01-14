package uiMain;

import java.util.List;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.time.LocalDateTime;

import gestorAplicacion.gestionVentas.Cliente;
import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionClases.Profesor;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;

import gestorAplicacion.herramientas.input;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Contador;
import gestorAplicacion.herramientas.Genero;
import gestorAplicacion.gestionObras.Director;

import java.text.NumberFormat;

public class Main {

    public static Scanner op;
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

    Cliente empresa = new Cliente("Empresa"); //temporal

    switch (task){

        case 1:
        {}break;

        case 2:
        {}break;

        case 3:
            gestionObras();
            break;

        case 4:
        {}break;

        case 5: AlquilarActor(empresa);
    }
    
    }


    public static void gestionObras(){
        int i;
        i = 0;
        Obra eleccion;
        Obra nuevaObra;

        for (Obra obra : Obra.getObras()){          
            i = i + 1;
            System.out.println(String.valueOf(i) + "." + obra.getNombre());
        }
        System.out.println(String.valueOf(i + 1) + ". Crear nueva obra");
        System.out.println("¿Qué obra desea consultar? Por favor indique el número sin punto");
        String answer = op.nextLine();
        if (Integer.parseInt(answer) <= i)
            eleccion = Obra.getObras().get(Integer.parseInt(answer) - 1);
        else if (Integer.parseInt(answer) > i){
            System.out.println("Por favor ingrese el nombre de la nueva obra");
            String nombre = op.nextLine();
            System.out.println("Por favor seleccione uno por uno el reparto de la nueva obra");
            int f;
            f = 0;
            for (Actor actor : Actor.getActors()){
                f = f + 1;
                System.out.println(String.valueOf(f) + "."+ actor.getNombre());
            }
            int s;
            s = 1;
            ArrayList<Actor> reparto = new ArrayList<>();
            ArrayList<Aptitud> papeles = new ArrayList<>();
            while (s != 0){
                System.out.println("Digita el número del actor que desea agregar sin punto, si ya terminaste de añadir el reparto por favor ingresa 0");
                String d = op.nextLine();
                s = Integer.parseInt(d);
                if (s == 0){
                    break;
                }
                else;
                    Actor elegido = Actor.getActors().get(s - 1);
                    reparto.add(elegido);
                    }
                    System.out.println("Por favor indica en qué se debe enfocar el actor (Solo puedes seleccionar una opción, sin embargo, varios actores pueden enfocarse en la misma opción) recuerde digitar solo el número de la opción");
                    System.out.println("1. Canto");
                    System.out.println("2. Baile");
                    System.out.println("3. Discurso");
                    System.out.println("4. Emocionalidad");
                    System.out.println("5. Improvisación");
                    byte u = op.nextByte();
                    switch (u){
                        case 1:
                            papeles.add(Aptitud.CANTO);
                            break;
                        case 2:
                            papeles.add(Aptitud.BAILE);
                            break;
                        case 3:
                            papeles.add(Aptitud.DISCURSO);
                            break;
                        case 4:
                            papeles.add(Aptitud.EMOCIONALIDAD);
                            break;
                        case 5: 
                            papeles.add(Aptitud.IMPROVISACION);
                            break;
                    }
            
            System.out.println("Por favor, elige el género de la obra, recuerda solo ingresar el número sin punto.");
            System.out.println("1. Drama");
            System.out.println("2. Comedia");
            System.out.println("3. Musical");
            System.out.println("4. Fantasía");
            System.out.println("5. Terror");
            System.out.println("6. Romace");
            System.out.println("7. Circo");
            System.out.println("8. Experimental");
            byte l = op.nextByte();
            Genero genero;
            genero = null;
            switch (l) {
                case 1:
                    genero = Genero.DRAMA;
                    break;
                case 2:
                    genero = Genero.COMEDIA;
                    break;
                case 3:
                    genero = Genero.MUSICAL;
                    break;
                case 4:
                    genero = Genero.FANTASIA;
                    break;
                case 5:
                    genero = Genero.TERROR;
                    break;
                case 6:
                    genero = Genero.ROMANCE;
                    break;
                case 7: 
                    genero = Genero.CIRCO;
                    break;
                case 8:
                    genero = Genero.EXPERIMENTAL;
                    break;
            }

            System.out.println("Por favor, elige al director que se encarga de la obra");
            int x;
            x = 0;
            for (Director director : genero.getDirectores()){
                x = x + 1;
                System.out.println(String.valueOf(x) + "."+ director.getNombre());
            }
            int dir = op.nextInt();
            Director director = genero.getDirectores().get(dir);

            System.out.println("Por favor, ingresa el costo de producción");
            float costoProduccion = op.nextFloat();

            System.out.println("Por favor ingresa la duración de la obra, usa el formato HHmmSS, no separes con :,- ni otro símbolo similar.");
            long dur = op.nextLong();
            

            nuevaObra = new Obra(nombre, reparto, papeles, director, costoProduccion, genero, dur);  
            }
            
    }

    public static void AlquilarActor(Cliente empresa){

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

        boolean firstElementAdded = false;

        List<Actor> historialEmpresa = empresa.getHistorial();

        List<Actor> reorderedActors = new ArrayList<>();

        for (Actor actor : actorsForRental){
            if (!firstElementAdded){
                reorderedActors.add(0, actor);
            } else if (input.isIn(historialEmpresa, actor)){
                reorderedActors.add(0, actor);
            } else{
                reorderedActors.add(actor);
            }

        }

        actorsForRental = reorderedActors;
        reorderedActors = null;

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
/* 
 ///BASE PARA FUNCIONALIDAD 4

    Scanner scanner = new Scanner(System.in);
    List<Artista> artistas = new ArrayList<>(); // Lista de artistas registrados
    List<Clase> clases = new ArrayList<>(); // Lista de clases programadas

    System.out.println("Bienvenido a la gestión de clases.");
    System.out.println("Ingrese el ID del artista: ");
    int idArtista = scanner.nextInt();
    scanner.nextLine(); 

    Artista artista = buscarArtistaPorId(artistas, idArtista);
    if (artista == null) {
        System.out.println("Artista no encontrado. Creando un nuevo artista.");
        System.out.print("Ingrese el nombre del nuevo artista: ");
        String nombreArtista = scanner.nextLine();
        artista = new Artista(idArtista, nombreArtista);
        artistas.add(artista);
        Profesor profesor = new Profesor("Profesor Inicial", true); //ORGANIZAR CLASE PROFESOR
        profesor.casting(artista);
    }

    System.out.println("Calificaciones actuales del artista " + artista.getNombre() + ":");
    artista.mostrarCalificaciones();

    System.out.println("Obras en estado crítico:");
    listarObrasCriticas();

    System.out.print("¿Desea programar una clase? (s/n): ");
    String respuesta = scanner.nextLine();
    if (respuesta.equalsIgnoreCase("s")) {
            programarClase(scanner, artista, clases);
    }

    System.out.println("Fin de la gestión de clases.");
    scanner.close();
    

    private static Artista buscarArtistaPorId(List<Artista> artistas, int id) {
        for (Artista artista : artistas) {
            if (artista.getId() == id) {
                return artista;
            }
        }
        return null;
    }

    private static void listarObrasCriticas() {
       // Simulación de obras críticas en el sistema                            //CREAR ESTE MÉTODO PARA FUN4
        System.out.println("Obra 1: Muy mala calificación en Actuación.");
        System.out.println("Obra 2: Muy mala calificación en Dirección.");
    } 

    private static void programarClase(Scanner scanner, Artista artista, List<Clase> clases) {
        System.out.print("Ingrese la materia de la clase: ");
        String materia = scanner.nextLine();

        System.out.print("Ingrese el nivel de la clase (1 = Introducción, 2 = Profundización, 3 = Perfeccionamiento): ");
        int nivel = scanner.nextInt();
        scanner.nextLine();

        Sala sala = estaDisponible();
        if (sala == null) {
            System.out.println("No hay salas disponibles y aseadas.");
            return;
        }

        Profesor profesor = buscarProfesorDisponible();
        if (profesor == null) {
            System.out.println("No hay profesores disponibles.");
            return;
        }

        System.out.print("Ingrese la hora de inicio de la clase (formato: yyyy-MM-ddTHH:mm): ");
        LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Ingrese la hora de fin de la clase (formato: yyyy-MM-ddTHH:mm): ");
        LocalDateTime fin = LocalDateTime.parse(scanner.nextLine());

        ArrayList<LocalDateTime> horario = {inicio, fin};
        double costo = calcularCosto(nivel);
        if (!artista.getCuentaBancaria().pagar(costo)) {
            System.out.println("Saldo insuficiente para pagar la clase.");
            return;
        }

        Tesoreria.recibirPago(costo);
        Clase nuevaClase = new Clase(materia, nivel, sala, profesor, costo, horario);
        clases.add(nuevaClase);
        System.out.println("Clase programada exitosamente para el artista " + artista.getNombre() + ".");
    }

    private static Sala buscarSalaDisponible(List<Sala> salas) {
    for (Sala sala : salas) {
        if (sala.isDisponible() && sala.isAseada()) {
            return sala;                                        //AGREAGAR MÉTODOS
            }
        }
    return null; // No se encontró una sala disponible y aseada
    }*  

    private static Profesor buscarProfesorDisponible(List<Profesor> profesores) {
    for (Profesor profesor : profesores) {
        if (profesor.isDisponible()) {
            return profesor;
            }
        }
    return null; // No se encontró un profesor disponible
    }

    private static double calcularCosto(int nivel) {
    switch (nivel) {
        case 1: return 50.0; // Costo para nivel Introducción
        case 2: return 75.0; // Costo para nivel Profundización
        case 3: return 100.0; // Costo para nivel Perfeccionamiento
        default: return 0.0;
        }
    }
}
*/
