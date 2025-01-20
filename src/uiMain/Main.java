package uiMain;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

import java.time.LocalDateTime;

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
import gestorAplicacion.herramientas.Suscripcion;
import gestorAplicacion.gestionObras.Director;



public class Main {
    static Tesoreria tesoreria = new Tesoreria(0, 100);
    public static Scanner in = new Scanner(System.in);

    public static boolean supportsColor = (System.console() != null && System.getenv().get("TERM") != null);

    //------------------HERRAMIENTAS-------------------------//
    //pregunta y devuelve cadena respuesta
    public static String ask(String question){
        customPrint(question, true);
        String answer = in.nextLine();
        return answer;
    }

    //para el método que acepta bytes se revisa si el numero hace parte de las opciones disponibles
    public static byte ask(String question, byte[] answers, String color){
        customPrint(question, true, color);
        byte answer = in.nextByte();
        
        while (!isIn(answers, answer)){
            customPrint("La respuesta introducida no hace parte de las opciones. Intente de nuevo:", true, "red");
            customPrint(question);
            answer = in.nextByte();
        }

        return answer;
    }

    //pregunta y devuelve long
    public static long longAsk(String question){
        customPrint(question);
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

    //revisa si hay superposición de horarios dado dos argumentos 
    public static boolean isDisponible(LocalDateTime inicio, LocalDateTime fin, ArrayList<ArrayList<LocalDateTime>> horario) {
        for (ArrayList<LocalDateTime> evento : horario) {
            if (inicio.isBefore(evento.get(1)) && fin.isAfter(evento.get(0))) {
                return false; // Horario ocupado
            }
        }
        return true; // Horario disponible
    }

    public static int LARGO_LINEAS = 100;
    public static char separador = '│';
    public static String vacio = " ";

    public static String formatString(String cadena, boolean isCentrado){
        vacio = " ";
        int largo_vacio = LARGO_LINEAS - cadena.length();
        String cadenaConFormato = "";

        if (largo_vacio == 0){
            return separador + cadenaConFormato + separador;
        } else { //supongamos que la cadena siempre será menor o igual al largo de lineas

            if (!isCentrado){
                vacio = vacio.repeat(largo_vacio);
                cadenaConFormato =  separador + cadena + vacio + separador;
                return cadenaConFormato;

            } else{ // si se va a centrar el texto

                if (largo_vacio % 2 == 0){ //si es par, se pueden dividir los vacios a los lados en iguales partes
                        vacio = vacio.repeat( (largo_vacio)/2 );
                        cadenaConFormato = separador + vacio + cadena + vacio + separador;
                        return cadenaConFormato; 
                } else {//espacio vacio impar
                        vacio = vacio.repeat( ((largo_vacio)/2) + 1);
                        cadenaConFormato = separador + vacio + cadena + (vacio.substring(0, vacio.length() - 1)) + separador;
                        return cadenaConFormato;
                        //sea el vacio total 9, se suma 1 (10) y se divide entre dos (5), se coloca un vacio normal y otro cortado por un caracter
                }

            }
        } 
    }
    
    public static void customPrint(String cadena){
        customPrint(cadena, true, "");
    }

    public static void customPrint(String cadena, String color){
        customPrint(cadena, true, color);
    }

    public static void customPrint(String cadena, boolean isCentrado){
        customPrint(cadena, true, "");
    }

    public static void customPrint(String cadena, boolean isCentrado, String color){
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";
        String chosenColor = null;

        switch(color){ 
            case "red":
            chosenColor = red; break;

            case "green":
            chosenColor = green; break;

            case "yellow":
            chosenColor = yellow; break;

            case "blue":
            chosenColor = blue; break;

            default:
            chosenColor = reset; break;
        }

        if(!supportsColor){
            chosenColor = reset;
        }

        System.out.println(chosenColor + "╭" + "─".repeat(LARGO_LINEAS) + "╮" + reset);
        String[] cadenas = cadena.split("\n");

        for (String linea : cadenas){
            System.out.println(chosenColor + formatString(linea, isCentrado) + reset);
        }

        System.out.println(chosenColor + "╰" + "─".repeat(LARGO_LINEAS ) + "╯" + reset);
    }
    //---------------------------------------------------------//


    public static void main(String args[]){    
        byte task = -1;
        

        while (task != 6){
            String dash = "~";
            customPrint("Teatro Carlos Mayolo", true);

            byte[] options = {1, 2, 3, 4, 5, 6};
            task = ask("Seleccione la tarea a realizar: \n1. Venta de tiquetes.\n2. Gestión de empleados.\n3. Gestión de obras.\n4. Gestión de clases.\n5. Alquiler de actores.\n6. Cerrar el programa.", options, "");

            switch (task){

                case 1:
                gestionVentas();
                break;

                case 2:
                gestionEmpleados();
                break;

                case 3:
                gestionObras();
                break;

                case 4:
                //gestionClases();
                break;

                case 5: 
                AlquilarActor(); 
                break;

                case 6:
                {}break;
            }
        }
    }

    public static void gestionVentas(){
        
        
        byte [] opciones_2 = {1,2};
        Cliente cliente= null;
        ArrayList <Integer> lista = new ArrayList<>();
        customPrint(
        "Ingrese la opcion correspondiente\n"+
        "Eres cliente nuevo? \n"+ 
        "1. NO\n"+
        "2. SI\n");
        

        
        byte a = in.nextByte();
        in.nextLine();

        while (!isIn(opciones_2, a)){

            customPrint("La respuesta introducida no hace parte de las opciones. Intente de nuevo:\n\n"+
            "Ingrese la opcion correspondiente\n"+
            "Eres cliente nuevo? \n"+ 
            "1. NO\n"+
            "2. SI\n");
            a = in.nextByte();
            in.nextLine();
        }
        Cliente c1 = new Cliente("null", 1);

        boolean salir = false;
    
        while (!salir) {
        switch (a) {
            case 1:
                customPrint("Ingresa tu id :");
                long code = in.nextLong();
                in.nextLine();                
               

                if (Cliente.verificar(code)) {
                    customPrint("Iniciando sesion...");
                    cliente=Cliente.asignar(code);
                    
                try {
                    // Pausa de 2 segundos (2000 milisegundos)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    customPrint("La pausa fue interrumpida.");
                }
                customPrint("Sesion Iniciada");
        
                salir = true;
                break;
                    

                }else {
                    customPrint("Codigo no encontrado\n"+
                    "Ingrese la opcion correspondiente\n"+
                    "Tienes un codigo existente? : \n"+ 
                    "1. Si\n"+
                    "2. NO\n");
                    
                    byte [] opcion = {1,2};
                    byte b = in.nextByte();
                    in.nextLine();

                    while (!isIn(opciones_2, b)){
                        customPrint("La respuesta introducida no hace parte de las opciones. \n"+
                        "Intente de nuevo:\n"+
                        "Tienes un codigo existente? : \n"+ 
                        "1. NO\n"+
                        "2. SI\n");
                        b = in.nextByte();
                        in.nextLine();
                    }
                    if (b == 1) {
                        break;
                        
                    }

                    

                    
                    
                }
            
                
            case 2:
                customPrint("Creando Nuevo Codigo...");
                try {
                    // Pausa de 2 segundos (2000 milisegundos)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    customPrint("La pausa fue interrumpida.");
                    
                }
                
                code= Cliente.IdRandom();
                cliente = new Cliente(null, code,Suscripcion.Basica);
                customPrint("Codigo "+cliente.getId()+ " creado");

                
                salir = true;
                
            }
        }
        customPrint("Que desea hacer\n\n"+
            "Ingrese la opcion correspondiente\n"+
            "1. Consultar obra y comprar tiquete\n"+ 
            "2. Mejorar Suscripcion\n"+
            "3. consular perfil"
            
            );
        
        int d = in.nextByte();
        in.nextLine();
        while (d !=2 & d != 1 & d != 3){

            customPrint("La respuesta introducida no hace parte de las opciones. \n"+
            "Intente de nuevo:\n"+
            "Que desea hacer\n\n"+
            "Ingrese la opcion correspondiente\n"+
            "1. Consultar obra y comprar tiquete\n"+ 
            "2. Mejorar Suscripcion\n"+
            "3. consular perfil"
            
            );
            d = in.nextByte();
            in.nextLine();
        }
        switch (d) {
            case 1:
            customPrint(String.format("%30s %15s %10s %10s", "Nombre Obra", "Genero", "Duracion","Precio")+"\n"+Obra.generarTabla());
            customPrint("Que obra desea comprar? \n");
            String input = in.nextLine().toLowerCase();
            
            while (Obra.nombres(input)){
                customPrint("Obra no encontrada \n"+
                "Ingrese un nombre valido :");
                input = in.nextLine().toLowerCase();
                

            }
            customPrint("Obra comprada \n\n"+Obra.imprimirObra(Obra.buscarObra(input)));
            cliente.setObra(input);
            


                break;
            case 2:
                customPrint("Su suscripcion actual es "+cliente.imprimirSuscripcion());
                customPrint(Suscripcion.tiposSuscipcion());
                
                customPrint(
            "Que suscripcion desea aadquirir?\n\n"
            );
            String suscripcion = in.nextLine().toLowerCase();
                
            while (Suscripcion.tipos(suscripcion)){

            customPrint("La respuesta introducida no hace parte de las opciones. \n"+
            "Intente de nuevo:\n"+
            "Que suscripcion desea aadquirir\n\n"
            
            );
            suscripcion = in.nextLine().toLowerCase();
        }
        switch (suscripcion) {
            case "basica":
                cliente.setSuscripcion(Suscripcion.Basica);
                break;
            case "vip":
                cliente.setSuscripcion(Suscripcion.Vip);
                break;
            case "premium":
                cliente.setSuscripcion(Suscripcion.Premium);
                break;

                
        
        }
            customPrint("Suscripcion "+cliente.getSuscripcion()+" aplicada");
            
            

                break;
            case 3:
                customPrint(cliente.getId()+"\n"+cliente.getObra()+"\n"+cliente.getSuscripcion());

                break;
        
            
                
        }
    



        

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
        customPrint(String.valueOf(i + 1) + ". Crear nueva obra");
        customPrint("¿Qué obra desea consultar? Por favor indique el número sin punto");
        if (in.hasNextLine()) in.nextLine();
        String obraSel = in.nextLine();
        if (Integer.parseInt(obraSel) <= i)
            eleccion = Obra.getObras().get(Integer.parseInt(obraSel) - 1);
        else if (Integer.parseInt(obraSel) > i){
            customPrint("Por favor ingrese el nombre de la nueva obra");
            String nombre = in.nextLine();
            customPrint("Por favor seleccione uno por uno el reparto de la nueva obra");
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
                String d = in.nextLine();
                s = Integer.parseInt(d);
                if (s == 0){
                    break;
                }
                else{
                    Actor elegido = Actor.getActors().get(s - 1);
                    reparto.add(elegido);
                }
                    System.out.println("Por favor indica en qué se debe enfocar el actor (Solo puedes seleccionar una opción, sin embargo, varios actores pueden enfocarse en la misma opción) recuerde digitar solo el número de la opción");
                    System.out.println("1. Canto");
                    System.out.println("2. Baile");
                    System.out.println("3. Discurso");
                    System.out.println("4. Emocionalidad");
                    System.out.println("5. Improvisación");
                    byte u = in.nextByte();
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
            byte l = in.nextByte();
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
            int dir = in.nextInt();
            Director director = genero.getDirectores().get(dir);

            System.out.println("Por favor, ingresa el costo de producción");
            float costoProduccion = in.nextFloat();

            System.out.println("Por favor ingresa la duración de la obra, usa el formato HHmmSS, no separes con :,- ni otro símbolo similar.");
            long dur = in.nextLong();
            

            eleccion = new Obra(nombre, reparto, papeles, director, costoProduccion, genero, dur);  
            }
        
            System.out.println("Has seleccionado" + " " + eleccion.getNombre());
            System.out.println("¿Cuántas funciones te gustaría crear para esta obra?");
            int a = eleccion.getFuncionesRecomendadas();
            boolean continuar = false;
            do {
                int rut = in.nextInt();
    
                if (a + 2 > rut) {
                    System.out.println("ALERTA, PUEDEN SER DEMASIADAS FUNCIONES PARA ESTA OBRA");
                    System.out.println("¿DESEA CONTINUAR?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
    
                    byte sc = in.nextByte();
    
                    switch (sc) {
                        case 1:
                            continuar = true; // Acepta y sale del ciclo
                            break;
                        case 2:
                            System.out.println("¿Cuántas funciones te gustaría crear para esta obra?");
                            break; // Repite el ciclo para pedir de nuevo el dato
                        default:
                            System.out.println("Opción no válida, intente nuevamente.");
                            break;
                    }
                }
                else if (a - 2 < rut){
                    System.out.println("ALERTA, PUEDEN SER MUY POCAS FUNCIONES PARA ESTA OBRA");
                    System.out.println("¿DESEA CONTINUAR?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    byte sc = in.nextByte();
    
                    switch (sc) {
                        case 1:
                            continuar = true; // Acepta y sale del ciclo
                            break;
                        case 2:
                            System.out.println("¿Cuántas funciones te gustaría crear para esta obra?");
                            break; // Repite el ciclo para pedir de nuevo el dato
                    }
                }
                else {
                    continuar = true; // Acepta si la cantidad es adecuada
                }
            } while (!continuar);
        
    }

    public static void AlquilarActor(){

    byte[] two = {1, 2};
    byte menuLog = ask("Seleccione:\n1. Empresa registrada.\n2. Empresa nueva.", two, "");
    byte ACTORES_POR_PAGINA = 5;

    List<Actor> historialEmpresa = new ArrayList<>();
    Cliente empresa = new Cliente("auxiliar", 0);

    menuSwitch:
    switch (menuLog){

        case 1:
        long idEntrada = longAsk("Ingrese el número de identificación:");
        boolean idFlag = false;
        
        for (Cliente cliente : Cliente.clientes){

            if (cliente.getId() == idEntrada){
                customPrint("Cliente confirmado en base de datos.", true, "green");
                historialEmpresa = cliente.getHistorial();
                empresa = cliente;
                idFlag = true;
                break menuSwitch;
            }

        }

        if (!idFlag){
            customPrint("El número de identificación no existe en la base de datos.", true, "red"); return;
        }

        case 2:
        long newId = longAsk("Genere un nuevo número de identificación.");
        
        for (Cliente cliente : Cliente.clientes){
            while (cliente.getId() == newId){
                customPrint("Esta identificación ya existe en la base de datos, intente con una diferente.", true, "red");
                newId = longAsk("Genere un nuevo número de identificación, si está en uso, este será automáticamente actualizado.");
            }
        }

        empresa = new Cliente("Empresa", newId);
        customPrint("Cliente creado:\n" + empresa, true, "green");
        
    }

        final float CALIFICACION_ALTA = 4.0f; //por ahora
        List<Actor> actorsForRental = new ArrayList<>(Actor.getActors());

        //primera ronda de preguntas
        byte[] options = new byte[8];
        options[0] = 1; options[1] = 2;
        
        //PREGUNTA NO. 1
        byte rolActor = ask("¿Qué tipo de papel desempeñará el actor?\n1. Rol principal.\n 2. Rol secundario.", options, "");

        //reservar los de calificacion alta solo para roles principales
        if (rolActor == 1){ 
            actorsForRental.removeIf(actor -> actor.getCalificacion() < CALIFICACION_ALTA); 
        } else {
            actorsForRental.removeIf(actor -> actor.getCalificacion() > CALIFICACION_ALTA);}

        options[2] = 3; options[3] = 4; options[4] = 5; options[5] = 6; options[6] = 7; options[7] = 8;

        //PREGUNTA NO. 2
        byte tipoObra = ask("¿Qué tipo de obra es?\n1. Circo.\n2. Comedia.\n3. Drama.\n4. Experimental.\n5. Fantasía.\n6. Musical.\n7. Romance.\n8. Terror.", options, "");
        
        //que se borren los actores que no tengan el género buscado en sus atributos
        switch(tipoObra){

            case 1:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.CIRCO));
            break;

            case 2:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.COMEDIA));
            break;

            case 3:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.DRAMA));
            break;

            case 4:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.EXPERIMENTAL));
            break;

            case 5:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.FANTASIA));
            break;

            case 6:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.MUSICAL));
            break;

            case 7:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.ROMANCE));
            break;

            case 8:
            actorsForRental.removeIf(actor -> !actor.getGeneros().contains(Genero.TERROR));
            break;
        }

        //para que la entrada de horarioCliente no se omita
        in.nextLine();

        //PREGUNTA NO. 3
        String horarioCliente = ask("¿En qué horario necesita el actor? (Responda en formato HH:MM)");

        //pendiente: diseñar lógica para revisar el horario

        //preseleccionados

        if (actorsForRental.size() == 0){ 
            customPrint("No hay artistas disponibles con los requerimientos pedidos.", true, "red"); return;
        } else {
            customPrint(actorsForRental.size() + " actores encontrados durante la preselección.", true, "green");
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
        byte advancedSearch = ask("¿Deseas hacer búsqueda avanzada? (incluye filtros por edad y sexo).\n1. Sí.\n2. No.", two, "blue");

        if (advancedSearch == 1){

            List<Contador> contadores = new ArrayList<Contador>();

            for (Actor actor : actorsForRental){
                contadores.add(new Contador(actor, 0));
            }

            byte edad = ask("¿Qué tipo de edad busca?\n1. Infantil\n2. Juvenil.\n3. Adulto.\n4. Adulto mayor", options, "");

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

            byte sexo = ask("¿Qué sexo debe tener el actor?\n1. Masculino.\n2. Femenino.", two, "");

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
                customPrint("No se encontraron actores que se ajusten bien a las características.", true, "red"); return;}

            List<Actor> advancedList = new ArrayList<Actor>();

            for (Contador contador : contadores){
                advancedList.add(contador.getActor());
            }
            
            actorsForRental = advancedList;
            advancedList = null;
            
            customPrint(actorsForRental.size() + " actores se ajustaron a dos o más características avanzadas.", true, "green");

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

        long presupuesto = longAsk("¿Cuál es el presupuesto máximo para el actor?" + "\nTenga en cuenta que el rango de los precios es de " + Actor.formatoPrecio(minActorPrecio) + " a " + Actor.formatoPrecio(maxActorPrecio));

        actorsForRental.removeIf(actor -> actor.getPrecioContrato() > presupuesto);

        if (actorsForRental.size() == 0){
            customPrint("No se hallaron actores para el presupuesto");
        } else {

            int lastIdx = 0;
            int paginasCompletas = (int) (actorsForRental.size() / ACTORES_POR_PAGINA);
            int paginasTotales = 0;
            boolean paginasResiduales = false;
            boolean continuar = true;
            int residualTimes = 0;
            int x = 0;
            int idx = 0;
            int lastResidualIdx = 0;
            Actor actorEscogido = null;//new Actor("auxiliar", 0);
            String page = "";

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

                lastIdx = -1;
                x = 0;
                idx = 0;
                lastResidualIdx = 0;
                residualTimes = 0;


                // 5(x+1) = 5x + 5, que es el final del rango de 5 valores que se va a imprimir
                while( (ACTORES_POR_PAGINA*(x+1) <= actorsForRental.size()) && continuar ){ //mientras exista el ultimo valor del rango de la pagina dentro de la lista
                    for (int i = ACTORES_POR_PAGINA*x; i < ACTORES_POR_PAGINA*(x+1); i ++){//por cada rango de 5 valores que tiene pagina
                        idx = (i+1)%ACTORES_POR_PAGINA;
                        if(idx == 0){ idx = ACTORES_POR_PAGINA; }

                        page += (idx) + ". " + actorsForRental.get(i) + "\n";
                        lastIdx = i;
                        page += "\n";
                }
                x++;
                page += "\n" + "Página " + x + "/" + paginasTotales;
                customPrint(page);;

                seguirBuscando = ask("Ingrese 0 para ver la siguiente página\nSi ya decidió el actor, presione su número", byteActores, "");
                page = "";

                System.out.println();
                
                if (seguirBuscando != 0){

                    continuar = false;
                    //para conseguir el indice, se le debe restar al ultimo indice del intervalo la diferencia entre 5 y la opcion escogida
                    //si la opcion fue la 1, seria ultimoIndice - 4, si fue la 5, ultimoIndice - 0, o sea, el mismo.
                    actorEscogido = actorsForRental.get(lastIdx - (ACTORES_POR_PAGINA - seguirBuscando));
                }

            }

            page = "";
            //si el tamaño de la lista no es múltiplo de 5, sobran valores por mostrar y se desea continuar       
            if (paginasResiduales && continuar){

                for (int i = lastIdx+1; i < actorsForRental.size(); i++){
                    page += (i - lastIdx) + ". " + actorsForRental.get(i) + "\n\n";
                    //System.out.println();
                    lastResidualIdx = i - lastIdx;
                    residualTimes ++;
                    
                }

                page += "Página " + (paginasCompletas + 1) + "/" + (paginasTotales);
                customPrint(page);

                seguirBuscando = ask("Ingrese 0 para ver la siguiente página\nSi ya decidió el actor, presione su número", byteActores, "");
                //System.out.println();
                
                if (seguirBuscando != 0){
                    
                    continuar = false;
                    //para conseguir el indice, se le debe restar al ultimo indice del intervalo 
                    //la diferencia entre la cantidad de actores en la ultima pagina (menor a 5) y la opcion escogida
                    //si la opcion fue la 1, seria ultimoIndice - 4, si fue la 5, ultimoIndice - 0, o sea, el mismo.
                    actorEscogido = actorsForRental.get((lastIdx + lastResidualIdx) - (residualTimes - seguirBuscando));
                }

            }
        }

        customPrint("El actor escogido fue " + actorEscogido.getNombre() + " por un precio de " +  Actor.formatoPrecio(actorEscogido.getPrecioContrato()));
        byte codigoCompra = empresa.pagarAlquilerActor(actorEscogido, tesoreria);
        if (codigoCompra == -1){
            customPrint("Saldo insuficiente", true, "red");
        } else{
            customPrint("Pago recibido!", true, "green");
        }
        customPrint("Saldo disponible: " + Actor.formatoPrecio(empresa.getCuentaBancaria().getSaldo()));

                
        }

    }

    //Base para funcionalidad 2
    public static void gestionEmpleados(){
 
        //Pagar nomina a empleados:
        double fondos = tesoreria.getCuenta().getSaldo() + tesoreria.getDineroEnCaja();
        double totalSaldos = 0;
        //Verificacion de fondos:
        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
            totalSaldos = totalSaldos + Persona.calcularSueldo();
        }
        //Realizar pago
        if(totalSaldos > fondos){
            double cantPagada = 0;
            customPrint("Upps... No se puede realizar los pagos adecuadamente", "Red");
            customPrint("Realizando pagos de manera equitativa...");
            for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                cantPagada = cantPagada + ((Persona.calcularSueldo() + Persona.getDeuda())*0.5);
                Persona.setDeuda((Persona.getDeuda() + (Persona.calcularSueldo()) * 0.5));   //Establecer cuanto se le debe a la persona
                tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.getDeuda() + Persona.calcularSueldo())*0.5);
            }
            customPrint("Pago existoso", true, "green");
            String msg = "Se pago un total de " + cantPagada;
            customPrint(msg);
            customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
            customPrint("Saldo disponible " + tesoreria.getCuenta().getSaldo());
        }
        else{
            //Verificacion fondos Bonificacion
            totalSaldos = 0;
            double cantPagada = 0;
            if(tesoreria.verificacionMeta() != true){
                //Verificacion Metas Personales
                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                    if(Persona.verificacionMeta() != true){
                        Persona.setMetaSemanal(Persona.getMetaSemanal()-5); //Disminucion de meta
                        totalSaldos = totalSaldos + (Persona.calcularSueldo() + Persona.getDeuda());
                    }
                    else{
                        Persona.setMetaSemanal(Persona.getMetaSemanal() + 10);  //Aumento en la meta
                        totalSaldos = totalSaldos + ((Persona.calcularSueldo() * 1.15) + Persona.getDeuda());
                    }
                }
                //Realizacion Pago Solo con Deuda
                if(totalSaldos > fondos){
                    totalSaldos = 0;
                    customPrint("Ups... No se pueden aplicar las bonificaciones personales");
                    customPrint("Realizando Pagos");
                    for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                        cantPagada = cantPagada + (Persona.calcularSueldo() + Persona.getDeuda());
                        totalSaldos = totalSaldos + Persona.calcularSueldo();
                    }
                    if(cantPagada > fondos){
                        customPrint("No se pudo realizar los pagos junto a la deuda");
                        customPrint("Realizando pago del Sueldo Base");
                        tesoreria.pagarSueldoBase(null, cantPagada);
                        customPrint("Pago existoso", true, "green");
                        String msg = "Se pago un total de " + totalSaldos;
                        customPrint(msg);
                        customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                        customPrint("Saldo disponible " + tesoreria.getCuenta().getSaldo());
                        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                            if(Persona.verificacionMeta() == true){
                                Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo()*0.15); //Se añade la bonificacion a la deuda solo a aquellas que la cumplieron
                            }
                        }
                    }
                    else{
                        for(Empleado Persona: Empleado.getEmpleadosPorRendimiento()){
                            tesoreria.getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda() + Persona.calcularSueldo());
                        }
                        customPrint("Pago existoso", true, "green");
                        String msg = "Se pago un total de " + cantPagada;
                        customPrint(msg);
                        customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                        customPrint("Saldo disponible " + tesoreria.getCuenta().getSaldo());
                    }
                    
                }
                //Realizacion Pago Boni + Deuda
                else{
                    for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                        if(Persona.verificacionMeta() == true){
                            tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.15) + Persona.getDeuda());
                        }
                        else{
                            tesoreria.getCuenta().transferencia(Persona.getCuenta(), Persona.calcularSueldo() + Persona.getDeuda());
                        }
                    }
                    customPrint("Pago existoso", true, "green");
                    String msg = "Se pago un total de " + totalSaldos;
                    customPrint(msg);
                    customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                    customPrint("Saldo disponible " + tesoreria.getCuenta().getSaldo());
                }
            }
            else{
                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                    if(Persona.verificacionMeta() != true){
                        Persona.setMetaSemanal(Persona.getMetaSemanal()-5); //Disminucion de meta
                        totalSaldos = totalSaldos + (Persona.calcularSueldo() * 1.3);
                    }
                    else{
                        Persona.setMetaSemanal(Persona.getMetaSemanal() + 10);  //Aumento en la meta
                        totalSaldos = totalSaldos + (Persona.calcularSueldo() * 1.45);
                    }
                }
                //Sin fondos suficientes para todas las bonificaciones
                if (totalSaldos > fondos) {
                    
                }
                else{
                    for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                        if(Persona.verificacionMeta() == true){
                            tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.8) + Persona.getDeuda());
                        }
                        else{
                            tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.3) + Persona.getDeuda());
                        }
                    }
                }
            }
        }
    }
 ///BASE PARA FUNCIONALIDAD 4
    public static void gestionClases() throws InterruptedException {

        byte[] two = {1, 2};

        customPrint("Bienvenido a la gestión de clases.");
        Thread.sleep(2000);
        long idArtista = longAsk("Ingrese el ID del artista:");

        Artista artista = Artista.buscarArtistaPorId(idArtista); 
        
        if (artista == null) {
            customPrint("Artista no encontrado.", "red");
            Thread.sleep(1000);
            byte crearArtista =  ask("¿Desea crear un nuevo Artista?\n" + "1. Sí\n" + "2. No", two, "" );

            switch (crearArtista) {
                case 1:
                String nombreArtista = ask("Ingrese el nombre del nuevo artista:");

                String tipoArtista = ask("Ingrese el tipo de artista (director/actor)");
    
                if (tipoArtista.equals("director")) {
                    // Crear un nuevo director
                    Director nuevoDirector = new Director(nombreArtista, idArtista);
                    customPrint("Nuevo director agregado: " + nombreArtista + "con ID" + idArtista, "green");
                } else if (tipoArtista.equals("actor")) {
                    // Crear un nuevo actor
                    Actor nuevoActor = new Actor(nombreArtista, idArtista);
                    customPrint("Nuevo actor agregado: " + nombreArtista + "con ID" + idArtista, "green");
                } else {
                    customPrint("Tipo de artista no válido. Debe ser 'director' o 'actor'.", "red");
                }
                break;
            
                case 2:
                    break;
            }

        }
        

        customPrint("Calificaciones actuales del artista " + artista.getNombre() + ":");
        artista.mostrarCalificacionesOInicializar(artista);    

        System.out.println("Obras en estado crítico:");
        //listarObrasCriticas();// Crear este método, no sin antes preguntarle a Danna específicamente sobre las "Obras críticas"

        System.out.print("¿Desea programar una clase? (s/n): ");

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
        }  
                                                
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
}

