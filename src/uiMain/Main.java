package uiMain;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

import java.time.LocalDateTime;
import java.text.NumberFormat;

import gestorAplicacion.gestionVentas.Cliente;
import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionClases.Profesor;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Contador;
import gestorAplicacion.herramientas.Genero;
import gestorAplicacion.gestionObras.Director;



public class Main {

    public static Scanner op;
    public static Scanner in = new Scanner(System.in);

    //------------------HERRAMIENTAS-------------------------//
    //pregunta y devuelve cadena respuesta
    public static String ask(String question){
        System.out.println(question);
        String answer = in.nextLine();
        return answer;
    }

    //para el método que acepta bytes se revisa si el numero hace parte de las opciones disponibles
    public static byte ask(String question, byte[] answers){
        System.out.println(question);
        byte answer = in.nextByte();
        
        while (!isIn(answers, answer)){
            System.out.println("\n\nLa respuesta introducida no hace parte de las opciones. Intente de nuevo:\n\n");
            System.out.println(question);
            answer = in.nextByte();
        }

        return answer;
    }

    //pregunta y devuelve long
    public static long longAsk(String question){
        System.out.println(question);
        long answer = in.nextLong();
        return answer;
    }

    public static boolean isIn(byte[] list, byte value){
        for (int i = 0; i < list.length; i++){
            if (value == list[i]){
                return true;
            }
        }

        return false;
    }

    public static <T> boolean isIn(List<T> list, T value){
        for (int i = 0; i < list.size(); i++){
            if (value.equals(list.get(i))){
                return true;
            }
        }
        return false;       
    }
    
    //---------------------------------------------------------//


    public static void main(String args[]){
    
        
    
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


    Tesoreria tesoreria = new Tesoreria();
    String dash = "~";

    System.out.println(dash.repeat(50));
    System.out.println("Sistema de administración del Teatro Carlos Mayolo");
    System.out.println(dash.repeat(50));

    byte[] options = {1, 2, 3, 4, 5, 6};
    byte task = ask("Seleccione la tarea a realizar: \n1. Venta de tiquetes.\n2. Gestión de empleados.\n3. Gestión de obras.\n4. Gestión de clases.\n5. Alquiler de actores.\n6. Cerrar el programa.", options);

    switch (task){

        case 1:
        gestionVentas();
        break;

        case 2:
        {}break;

        case 3:
            gestionObras();
            break;

        case 4:
        {}break;

        case 5: AlquilarActor(); break;

        case 6:
        {}break;
    }
    
    }

    public static void gestionVentas(){
        Cliente cliente= null;
        ArrayList <Integer> lista = new ArrayList<>();
        System.out.println("Ingrese la opcion correspondiente");
        System.out.println("Eres cliente nuevo?");
        System.out.println("1. NO");
        System.out.println("2. SI");

        byte [] opciones = {1,2};
        byte a = in.nextByte();

        while (!isIn(opciones, a)){
            System.out.println("\n\nLa respuesta introducida no hace parte de las opciones. Intente de nuevo:\n\n");
            System.out.println("Eres cliente nuevo?");
            System.out.println("1. NO");
            System.out.println("2. SI");
            a = in.nextByte();
        }
        Cliente c1 = new Cliente("null", 1);

        boolean salir = false;
    
        while (!salir) {
        switch (a) {
            case 1:
                System.out.print("Ingresa tu id :");
                long code = in.nextLong();
                
               

                if (Cliente.verificar(code)) {
                    System.out.println("Iniciando sesion...");
                    cliente=Cliente.asignar(code);
                    
                try {
                    // Pausa de 2 segundos (2000 milisegundos)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("La pausa fue interrumpida.");
                }
                System.out.println("Sesion Iniciada");
        
                salir = true;
                break;
                    

                }else {
                    System.out.println("Codigo no encontrado");
                    System.out.println("Tienes un codigo existente? :");
                    System.out.println("1. SI");
                    System.out.println("2. NO");
                    byte [] opcion = {1,2};
                    byte b = in.nextByte();

                    while (!isIn(opciones, a)){
                        System.out.println("\n\nLa respuesta introducida no hace parte de las opciones. Intente de nuevo:\n\n");
                        System.out.println("Tienes un codigo existente? :");
                        b = in.nextByte();
                    }
                    if (b == 1) {
                        break;
                        
                    }

                    

                    
                    
                }
            
                
            case 2:
                System.out.println("Creando Nuevo Codigo...");
                try {
                    // Pausa de 2 segundos (2000 milisegundos)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("La pausa fue interrumpida.");
                    
                }
                System.out.println("Codigo creado");
                code= Cliente.IdRandom();
                cliente = new Cliente(null, code);

                
                salir = true;
                break;
            }
        }
        System.out.println(cliente.getId());

        

    }

    public static void gestionObras(){
        int i;
        i = 0;
        Obra eleccion;
        eleccion = null;

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
            

            eleccion = new Obra(nombre, reparto, papeles, director, costoProduccion, genero, dur);  
            }
        
            System.out.println("Has seleccionado" + " " + eleccion.getNombre());
            System.out.println("¿Cuántas funciones te gustaría crear para esta obra?");
            int a = eleccion.getFuncionesRecomendadas();
            int rut = op.nextInt();
            if (a + 2 > rut){
                System.out.println("ALERTA, PUEDEN SER DEMASIADAS FUNCIONES PARA ESTA OBRA");
                System.out.println("¿DESEA CONTINUAR?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                byte sc = op.nextByte();
                switch (sc) {
                    case 1:
                        
                        break;
                    case 2:
                        
                
                    default:
                        break;
                }
            }
    }

    public static void AlquilarActor(){

    byte[] two = {1, 2};
    byte menuLog = ask("\nSeleccione:\n1. Empresa registrada.\n2. Empresa nueva.", two);
    byte ACTORES_POR_PAGINA = 5;

    List<Actor> historialEmpresa = new ArrayList<>();
    Cliente empresa = new Cliente("auxiliar", 0);

    menuSwitch:
    switch (menuLog){

        case 1:
        long idEntrada = longAsk("\nIngrese el número de identificación:");
        boolean idFlag = false;
        
        for (Cliente cliente : Cliente.clientes){

            if (cliente.getId() == idEntrada){
                System.out.println("\nCliente confirmado en base de datos.");
                historialEmpresa = cliente.getHistorial();
                empresa = cliente;
                idFlag = true;
                break menuSwitch;
            }

        }

        if (!idFlag){
            System.out.println("\nEl número de identificación no existe en la base de datos."); return;
        }

        case 2:
        long newId = longAsk("\nGenere un nuevo número de identificación.");
        
        for (Cliente cliente : Cliente.clientes){
            while (cliente.getId() == newId){
                System.out.println("\nEsta identificación ya existe en la base de datos, intente con una diferente.");
                newId = longAsk("\nGenere un nuevo número de identificación, si está en uso, este será automáticamente actualizado.");
            }
        }

        empresa = new Cliente("Empresa", newId);
        System.out.println("\nCliente creado:\n" + empresa);
        
    }

        final float CALIFICACION_ALTA = 4.0f; //por ahora
        List<Actor> actorsForRental = new ArrayList<>(Actor.getActors());

        //primera ronda de preguntas
        byte[] options = new byte[4];
        options[0] = 1; options[1] = 2;
        
        //PREGUNTA NO. 1
        byte rolActor = ask("\n¿Qué tipo de papel desempeñará el actor?\n1. Rol principal.\n2. Rol secundario.", options);

        //reservar los de calificacion alta solo para roles principales
        if (rolActor == 1){ 
            actorsForRental.removeIf(actor -> actor.getCalificacion() < CALIFICACION_ALTA); 
        } else {
            actorsForRental.removeIf(actor -> actor.getCalificacion() > CALIFICACION_ALTA);}

        options[2] = 3; options[3] = 4;

        //PREGUNTA NO. 2
        byte tipoObra = ask("\n¿Qué tipo de obra es?\n1. Tragedia.\n2. Comedia.\n3. Romance.\n4. Tragicomedia.", options);
        
        //que se borren los actores que no tengan el género buscado en sus atributos
        switch(tipoObra){

            case 1:
            actorsForRental.removeIf(actor -> !isIn(actor.getGeneros(), "Tragedia"));
            break;

            case 2:
            actorsForRental.removeIf(actor -> !isIn(actor.getGeneros(), "Comedia"));
            break;

            case 3:
            actorsForRental.removeIf(actor -> !isIn(actor.getGeneros(), "Romance"));
            break;

            case 4:
            actorsForRental.removeIf(actor -> !isIn(actor.getGeneros(), "Tragicomedia"));
            break;
        }
        
        //para que la entrada de horarioCliente no se omita
        in.nextLine();

        //PREGUNTA NO. 3
        String horarioCliente = ask("\n¿En qué horario necesita el actor? (Responda en formato 24 horas HH:MM)");

        //pendiente: diseñar lógica para revisar el horario

        //preseleccionados

        if (actorsForRental.size() == 0){ 
            System.out.println("\nNo hay artistas disponibles con los requerimientos pedidos."); return;
        } else {
            System.out.println("\n" + actorsForRental.size() + " actores encontrados durante la preselección.");
        }

        boolean firstElementAdded = true;

        List<Actor> reorderedActors = new ArrayList<>();

        //reordenacion de actores si aparecen en el historial de empresa
        //si han sido contratados, agregar en nueva lista al principio, sino, al final
        for (Actor actor : actorsForRental){
            if (!firstElementAdded){
                reorderedActors.add(0, actor);
                firstElementAdded = false;
            } else if (isIn(historialEmpresa, actor)){
                reorderedActors.add(0, actor);
            } else{
               reorderedActors.add(actor);
            }

        }

        //convertir nueva lista reordenada en lista de actores
        actorsForRental = reorderedActors;
        reorderedActors = null;

        //búsqueda avanzada
        byte advancedSearch = ask("\n¿Deseas hacer búsqueda avanzada? (incluye filtros por edad, sexo y  cantidad de obras actuadas).\n1. Sí\n2. No.", two);

        if (advancedSearch == 1){

            List<Contador> contadores = new ArrayList<Contador>();

            for (Actor actor : actorsForRental){
                contadores.add(new Contador(actor, 0));
            }

            byte edad = ask("\n¿Qué tipo de edad busca?\n1. Infantil\n2. Juvenil.\n3. Adulto.\n4. Adulto mayor", options);

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

                if (isIn(actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            in.nextLine();

            byte sexo = ask("\n¿Qué sexo debe tener el actor?\n1. Masculino.\n2. Femenino.", two);

            if (sexo == 1){ //si es masculino, remueve el sexo femenino y viceversa
                actorsForRental.removeIf(actor -> actor.getSexo() == 'F');
            } else {
                actorsForRental.removeIf(actor -> actor.getSexo() == 'M');
            }

            for (Contador contador : contadores){

                if (isIn(actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            in.nextLine();

            contadores.removeIf(contador -> contador.numero < 2);

            if (contadores.size() == 0){
                System.out.println("\nNo se encontraron actores que se ajusten bien a las características."); return;}

            List<Actor> advancedList = new ArrayList<Actor>();

            for (Contador contador : contadores){
                advancedList.add(contador.getActor());
            }
            
            actorsForRental = advancedList;
            advancedList = null;
            
            System.out.println("\n" + actorsForRental.size() + " actores se ajustaron a dos o más características avanzadas.");

            }

        double minActorPrecio = actorsForRental.get(0).getPrecioContrato();
        double maxActorPrecio = actorsForRental.get(0).getPrecioContrato();

        //conseguir precio de contrato min y max para que el usuario sepa el rango
        for (Actor actor : actorsForRental){

            if (actor.getPrecioContrato() > maxActorPrecio){
                maxActorPrecio = actor.getPrecioContrato();
            } else if (actor.getPrecioContrato() < minActorPrecio){
                minActorPrecio = actor.getPrecioContrato();
            }

        }    

        long presupuesto = longAsk("\n¿Cuál es el presupuesto máximo para el actor?" + "\nTenga en cuenta que el rango de los precios es de " + Actor.formatoPrecio(minActorPrecio) + " a " + Actor.formatoPrecio(maxActorPrecio) + "\n");

        actorsForRental.removeIf(actor -> actor.getPrecioContrato() > presupuesto);

        if (actorsForRental.size() == 0){
            System.out.println("\nNo se hallaron actores para el presupuesto");
        } else {

        NumberFormat intToCop = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

            int lastIdx = 0;
            int paginasCompletas = (int) (actorsForRental.size() / ACTORES_POR_PAGINA);
            int paginasTotales = 0;
            boolean paginasResiduales = false;
            boolean continuar = true;
            int residualTimes = 0;
            int x = 0;
            int idx = 0;
            int lastResidualIdx = 0;
            Actor actorEscogido = new Actor("auxiliar", 0);

            if (actorsForRental.size()%ACTORES_POR_PAGINA != 0){
                paginasResiduales = true;
                paginasTotales = paginasCompletas + 1;
            }


            byte[] byteActores = new byte[ACTORES_POR_PAGINA + 1];
            for (int i = 0; i < byteActores.length; i++){
                byteActores[i] = (byte) i;
            }

            byte seguirBuscando = 0;
            
            while (continuar){

                lastIdx = 0;
                x = 0;
                idx = 0;
                lastResidualIdx = 0;
                residualTimes = 0;

                // 5(x+1) = 5x + 5, que es el final del rango de 5 valores que se va a imprimir
                while( (ACTORES_POR_PAGINA*(x+1) <= actorsForRental.size()) && continuar ){ //mientras exista el ultimo valor del rango de la pagina dentro de la lista
                    for (int i = ACTORES_POR_PAGINA*x; i < ACTORES_POR_PAGINA*(x+1); i ++){//por cada rango de 5 valores que tiene pagina
                        idx = (i+1)%ACTORES_POR_PAGINA;
                        if(idx == 0){ idx = ACTORES_POR_PAGINA; }

                        System.out.println((idx) + ". " + actorsForRental.get(i));
                        lastIdx = i;
                        System.out.println();
                }
                x++;
                System.out.println("Página " + x + "/" + paginasTotales);
                System.out.println();

                seguirBuscando = ask("Ingrese 0 para ver la siguiente página\nSi ya decidió el actor, presione su número", byteActores);

                System.out.println();
                
                if (seguirBuscando != 0){

                    continuar = false;
                    //para conseguir el indice, se le debe restar al ultimo indice del intervalo la diferencia entre 5 y la opcion escogida
                    //si la opcion fue la 1, seria ultimoIndice - 4, si fue la 5, ultimoIndice - 0, o sea, el mismo.
                    actorEscogido = actorsForRental.get(lastIdx - (ACTORES_POR_PAGINA - seguirBuscando));
                }

            }

            //si el tamaño de la lista no es múltiplo de 5, sobran valores por mostrar y se desea continuar       
            if (paginasResiduales && continuar){

                for (int i = lastIdx+1; i < actorsForRental.size(); i++){
                    System.out.println((i - lastIdx) + ". " + actorsForRental.get(i));
                    System.out.println();
                    lastResidualIdx = i - lastIdx;
                    residualTimes ++;
                    
                }

                System.out.println("Página " + (paginasCompletas + 1) + "/" + (paginasTotales));
                System.out.println();

                seguirBuscando = ask("Ingrese 0 para ver la siguiente página\nSi ya decidió el actor, presione su número", byteActores);
                System.out.println();
                
                if (seguirBuscando != 0){
                    
                    continuar = false;
                    //para conseguir el indice, se le debe restar al ultimo indice del intervalo 
                    //la diferencia entre la cantidad de actores en la ultima pagina (menor a 5) y la opcion escogida
                    //si la opcion fue la 1, seria ultimoIndice - 4, si fue la 5, ultimoIndice - 0, o sea, el mismo.
                    actorEscogido = actorsForRental.get((lastIdx + lastResidualIdx) - (residualTimes - seguirBuscando));
                }

            }
        }

        System.out.println("Su actor escogido fue " + actorEscogido.getNombre() + " por un precio de " +  Actor.formatoPrecio(actorEscogido.getPrecioContrato()));
        empresa.pagarAlquilerActor(actorEscogido);
        System.out.println("Pago recibido!");
        System.out.println("Saldo disponible: " + Actor.formatoPrecio(empresa.getCuenta().getSaldo()));

                
        }

    }
}
            

 /* 
 ///BASE PARA FUNCIONALIDAD 4

    Scanner scanner = new Scanner(System.in);
    List<Artista> artistas = new ArrayList<>(); // Lista de artistas registrados, puede ser un atributo estático
    List<Clase> clases = new ArrayList<>(); // Lista de clases programadas, puede ser un atributo estático

    System.out.println("Bienvenido a la gestión de clases.");
    System.out.println("Ingrese el ID del artista: ");
    long idArtista = scanner.nextLong();
    scanner.nextLine(); 

    Artista artista = buscarArtistaPorId(artistas, idArtista); //Crear método de  busca de artista
    if (artista == null) {
        System.out.println("Artista no encontrado. Creando un nuevo artista.");
        System.out.print("Ingrese el nombre del nuevo artista: ");
        String nombreArtista = scanner.nextLine();
        artista = new Artista(idArtista, nombreArtista); //Crea un artista, ahora bien, preguntar a Danna
                                                        //si el tipo de artista, ya sea actor, director, etc, se crea random

        artistas.add(artista);// Se podría crear un arraylist que almacene todos los artista, si es que no existe aún

        Profesor profesor = new Profesor("Profesor Inicial", true); //ORGANIZAR CLASE PROFESOR
        profesor.casting(artista);
    }

    System.out.println("Calificaciones actuales del artista " + artista.getNombre() + ":");
    artista.mostrarCalificaciones(); //Crear este método

    System.out.println("Obras en estado crítico:");
    listarObrasCriticas();// Crear este método, no sin antes preguntarle a Danna específicamente sobre las "Obras críticas"

    System.out.print("¿Desea programar una clase? (s/n): ");
    String respuesta = scanner.nextLine();
    if (respuesta.equalsIgnoreCase("s")) {
            programarClase(scanner, artista, clases); //Crear método para programar clase
    }

    System.out.println("Fin de la gestión de clases.");
    scanner.close();
    

    private static Artista buscarArtistaPorId(List<Artista> artistas, long idArtista2) {
            for (Artista artista : artistas) {
                if (artista.getId() == idArtista2) {
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

        Sala sala = estaDisponible(); //Adpatar método de sala
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

        Tesoreria.recibirPago(costo); //Crear método o adaptar otros para esta función
        Clase nuevaClase = new Clase(materia, nivel, sala, profesor, costo, horario);
        clases.add(nuevaClase);
        System.out.println("Clase programada exitosamente para el artista " + artista.getNombre() + ".");
    }

    private static Sala buscarSalaDisponible(List<Sala> salas) {
    for (Sala sala : salas) {
        if (sala.isDisponible(null, null) && sala.isAseada()) {
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
