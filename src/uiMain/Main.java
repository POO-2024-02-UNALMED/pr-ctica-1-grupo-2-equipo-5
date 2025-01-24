package uiMain;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gestorAplicacion.gestionVentas.Cliente;
import gestorAplicacion.gestionClases.Clase;
import gestorAplicacion.gestionClases.Profesor;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionVentas.Funcion;

import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Contador;
import gestorAplicacion.herramientas.Genero;
import gestorAplicacion.herramientas.Suscripcion;
import test.funci_1;
import test.funci_5;
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

    public static boolean canBeTime(String time){ //formato HH:MM
        try{
            LocalTime hour = LocalTime.parse(time);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static LocalTime timeAsk(String question){
        customPrint(question);
        String input = in.nextLine();

        while (!canBeTime(input)){
            customPrint("La respuesta introducida no está en el formato 24 horas (HH:MM). Intente de nuevo:", true, "red");
            customPrint(question);
            input = in.nextLine();     
        }

        LocalTime answer = LocalTime.parse(input);
        return answer;
    }

    // Método para ingresar horarios de manera más intuitiva. Usado en funcionalidad 4
    public static LocalDateTime solicitarHorario(String tipo) {
        LocalDateTime horario = null;

        while (horario == null) {
            try {
                String fecha = ask("Ingrese la fecha del " + tipo + " (formato: Año-Mes-Día, Ejemplo: 2025-01-23):");
                
                String hora = ask("Ingrese la hora del " + tipo + " (formato: Hora:Minuto, Ejemplo: 14:30):");
                
                // Combinar fecha y hora ingresadas
                String fechaHora = fecha + "T" + hora;
                
                // Formatear y parsear la fecha y hora
                horario = LocalDateTime.parse(fechaHora, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                customPrint("Formato inválido. Asegúrese de seguir el formato especificado. Intente nuevamente.", "red");
            }
        }
        return horario;
    }

    //para el método que acepta bytes se revisa si el numero hace parte de las opciones disponibles
    public static byte ask(String question, byte[] answers, String color){
        byte answer = -1;        
        
        while (true){
            customPrint(question, true, color);
            String input = in.nextLine();
            

            if (canBeByte(input)){ //si se puede pasar a byte
                answer = Byte.parseByte(input); //convertir a byte
                if (isIn(answers, answer)){ //si está en las repsuestas buscadas
                    return answer; //retornar opción elegida

                } else { //si no hace parte de las opciones elegidas
                    customPrint("La respuesta introducida no hace parte de las opciones.\nIntente de nuevo", true, "red");
                }
            } else {// si no se puede pasar a byte
                customPrint("La respuesta introducida no es un número entero o excede el rango.\nIntente de nuevo", true, "red");
            }
        }
    }

    public static boolean canBeByte(String cadena){
        try{
            Byte.parseByte(cadena);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean canBeLong(String cadena){
        try{
            Long.parseLong(cadena);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //pregunta y devuelve long
    public static long longAsk(String question){
        customPrint(question);

        String input = in.nextLine();

        while(!canBeLong(input)){
            customPrint("La respuesta introducida no es numérica o está fuera del rango. Intente de nuevo.", true, "red");
            customPrint(question);
            input = in.nextLine();
        }
        
        long answer = Long.parseLong(input);
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

    public static ArrayList<LocalDate> getWeek(){
        LocalDate today = LocalDate.now(); // Día actual
        ArrayList<LocalDate> week = new ArrayList<>();

        // Generar los próximos 7 días
        for (int i = 0; i < 7; i++) {
            week.add(today.plusDays(i)); // Sumar días al día actual
        }
        return week;
    }
    Sala sala1 = new Sala();

    public static void main(String args[]){  

        byte task = -1;

        while (task != 6){
            String dash = "~";
            customPrint("Teatro Carlos Mayolo", true);

            byte[] options = {1, 2, 3, 4, 5, 6};
            task = ask("Seleccione la tarea a realizar: \n1. Venta de tiquetes.\n2. Gestión de empleados.\n3. Gestión de obras.\n4. Gestión de clases.\n5. Contratación de actores.\n6. Cerrar el programa.", options, "");

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
                ContratarActor(); 
                break;

                case 6:
                {}break;
            }
        }
    }

    public static void gestionVentas(){
        funci_1.prueba();
        
        
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
            "2. SI\n","red");
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
                customPrint("Sesion Iniciada","green");
        
                salir = true;
                break;
                    

                }else {
                    customPrint("Codigo no encontrado\n"+
                    "Ingrese la opcion correspondiente\n"+
                    "Tienes un codigo existente? : \n"+ 
                    "1. Si\n"+
                    "2. NO\n","red");
                    
                    byte [] opcion = {1,2};
                    byte b = in.nextByte();
                    in.nextLine();

                    while (!isIn(opciones_2, b)){
                        customPrint("La respuesta introducida no hace parte de las opciones. \n"+
                        "Intente de nuevo:\n"+
                        "Tienes un codigo existente? : \n"+ 
                        "1. NO\n"+
                        "2. SI\n","red");
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
                customPrint("Codigo "+cliente.getId()+ " creado","green");

                
                salir = true;
                
            }
        }
        customPrint(cliente.consultarPerfil());
        

        
            customPrint(
        "Ingrese la opcion correspondiente\n"+
        "Desea mejorar su suscripcion? \n"+ 
        "1. Si\n"+
        "2. No\n","blue");
        

        
        a = in.nextByte();
        in.nextLine();
            while (a != 1 & a != 2) {
                customPrint("La respuesta introducida no hace parte de las opciones.\n"+
        "Ingrese la opcion correspondiente\n"+
        "Desea mejorar su suscripcion? \n"+ 
        "1. SI\n"+
        "2. NO\n","red");
        a = in.nextByte();
        in.nextLine();

                
            }
            
            
        
        if(a == 1){
            customPrint(Suscripcion.tiposSuscipcion());
                
                customPrint(
            "Que suscripcion desea aadquirir?\n\n"
            );
            String suscripcion = in.nextLine().toLowerCase();
                
            while (Suscripcion.tipos(suscripcion)){

            customPrint("La respuesta introducida no hace parte de las opciones. \n"+
            "Intente de nuevo:\n"+
            "Que suscripcion desea aadquirir\n\n","red"
            
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
            case "elite":
                cliente.setSuscripcion(Suscripcion.Elite);
                break;

                
        
        }
            customPrint("Suscripcion "+cliente.getSuscripcion()+" aplicada","green");

        }
        customPrint("Estas son las funciones disponibles\n\n"+String.format("%30s %22s %22s %15s", "Nombre Obra", "Genero", "Duracion","Precio")+"\n"+Funcion.generarTabla());
            customPrint("Que funcion desea comprar? \n");
            String input = in.nextLine().toLowerCase();
            
            while (Obra.nombres(input)){
                customPrint("Funcion no encontrada \n"+
                "Ingrese un nombre valido :","red");
                input = in.nextLine().toLowerCase();
                

            }
            customPrint("Funcion seleccionada: \n\n"+Obra.imprimirObra(Obra.buscarObra(input)));
            cliente.setObra(input);

            float descuento=0;
            if (cliente.getSuscripcion().name().equals("Basica")) {

                descuento = 0;


            } else if (cliente.getSuscripcion().name().equals("Vip")) {

                descuento = 0.25f;
                

            } else if (cliente.getSuscripcion().name().equals("Premium")) {

                descuento = 0.10f;
                
            } else if (cliente.getSuscripcion().name().equals("Elite")){

                descuento = 1;
                
            }
            customPrint("El precio final luego de descuento es :"+String.format("$%,.2f",Funcion.mostrarPrecioFuncion(input))+"\n Realizando transaccion...");
            try {
                // Pausa de 2 segundos (4000 milisegundos)
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                customPrint("La pausa fue interrumpida.");
                
            }
            customPrint("compra realizada");
            
        
                

        
        
       



        

    }

    public static void gestionObras(){
        int i;
        i = 0;
        Obra eleccion;
        eleccion = null;
        String menuObras = "";
        ArrayList<LocalDate> week = getWeek();
        if (!Obra.getObras().isEmpty()){
            for (Obra obra : Obra.getObras()){          
                i = i + 1;
                String item = String.valueOf(i) + "." + obra.getNombre() + "\n";
                menuObras = menuObras + item;
            }
        }
        customPrint(menuObras + String.valueOf(i + 1) + ". Crear nueva obra");
        customPrint("Por favor indique el número de su elección sin punto");
        String obraSel = in.nextLine();
        if (Integer.parseInt(obraSel) <= i){
            eleccion = Obra.getObras().get(Integer.parseInt(obraSel) - 1);
        }
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
                customPrint("Digita el número del actor que desea agregar sin punto\n Si ya terminaste de añadir el reparto por favor ingresa 0");
                String d = in.nextLine();
                s = Integer.parseInt(d);
                if (s == 0){
                    break;
                }
                else{
                    Actor elegido = Actor.getActors().get(s - 1);
                    reparto.add(elegido);
                }
                    customPrint("Por favor indica en qué se debe enfocar el actor (Solo puedes seleccionar una opción, sin embargo, varios actores pueden enfocarse en la misma opción) recuerde digitar solo el número de la opción\n1. Canto\n2. Baile\n3. Discurso\n4. Emocionalidad\n5. Improvisación");
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
            Director eleccionDir = null;
            String menuDirectores = "";
            if (!genero.getDirectores().isEmpty()){
                for (Director director : genero.getDirectores()){
                    x = x + 1;
                    String item =(String.valueOf(x) + "."+ director.getNombre() + "\n");
                    menuDirectores = menuDirectores + item;
                }
            }
            customPrint(menuDirectores + String.valueOf(x + 1) + ". Crear nuevo director");
            customPrint("Indique <3");
            if (in.hasNextLine()) {in.nextLine();};
            String directorSel = in.nextLine();
            if (Integer.parseInt(directorSel) <= x){
                eleccionDir = genero.getDirectores().get(Integer.parseInt(directorSel) - 1);
            }
            else if (Integer.parseInt(directorSel) > x){
                customPrint("Por favor ingrese el nombre del nuevo director");
                String nDirector = in.nextLine();
                customPrint("Por favor ingrese el número de documento del nuevo director");
                long idDirector = in.nextLong();
                eleccionDir = new Director(nDirector, idDirector, genero);
                customPrint("Director creado: \n" + eleccionDir);
                }
            Director director = eleccionDir;
            x = 0;
                

            System.out.println("Por favor, ingresa el costo de producción");
            float costoProduccion = in.nextFloat();

            System.out.println("Por favor ingresa la duración de la obra, usa el formato HHmmSS, no separes con :,- ni otro símbolo similar.");
            long dur = in.nextLong();
            

            eleccion = new Obra(nombre, reparto, papeles, director, costoProduccion, genero, dur);  
            }
            customPrint(String.valueOf(eleccion.getPromedioArt()));
            customPrint("Has seleccionado" + " " + eleccion.getNombre());
            customPrint("¿Cuántas funciones te gustaría crear para esta obra?");
            int a = eleccion.getFuncionesRecomendadas();
            boolean continuar = false;
            int drut = 0;
            do {
                int rut = in.nextInt();
    
                if (a + 2 < rut) {
                    customPrint("ALERTA, PUEDEN SER DEMASIADAS FUNCIONES PARA ESTA OBRA\n DESEA CONTINUAR?\n 1. Sí\n 2. No");
                    byte sc = in.nextByte();
    
                    switch (sc) {
                        case 1:
                            continuar = true; // Acepta y sale del ciclo
                            drut = rut;
                            break;
                        case 2:
                            System.out.println("¿Cuántas funciones te gustaría crear para esta obra?");
                            break; // Repite el ciclo para pedir de nuevo el dato
                        default:
                            System.out.println("Opción no válida, intente nuevamente.");
                            break;
                    }
                }
                else if (a - 2 > rut){
                    customPrint("ALERTA, PUEDEN SER MUY POCAS FUNCIONES PARA ESTA OBRA\n DESEA CONTINUAR?\n 1. Sí\n 2. No");
                    byte sc = in.nextByte();
    
                    switch (sc) {
                        case 1:
                            continuar = true; // Acepta y sale del ciclo
                            drut = rut;
                            break;
                        case 2:
                            System.out.println("¿Cuántas funciones te gustaría crear para esta obra?");
                            break; // Repite el ciclo para pedir de nuevo el dato
                    }
                }
                else {
                    continuar = true;
                    drut = rut; // Acepta si la cantidad es adecuada
                }
            } while (!continuar);   

            if (!(drut==0)){
                System.out.println("I work");
            }
            
            for (int numeroFunciones = 0; numeroFunciones < drut; numeroFunciones++){
                ArrayList<LocalDate> weekn = getWeek();
                Funcion funcion = new Funcion(eleccion, weekn);
                eleccion.addFuncion(funcion);
                customPrint("Funcion creada\nHora:  " + funcion.getHorario() + "\nSala: " + funcion.getSala());
                customPrint("me too");
            }
        
    }

    public static void ContratarActor(){

    byte[] two = {1, 2};
    byte menuLog = ask("Seleccione:\n1. Empresa registrada.\n2. Empresa nueva.", two, "");
    byte ACTORES_POR_PAGINA = 5;

    List<Actor> historialEmpresa = new ArrayList<>();
    Cliente empresa = new Cliente("auxiliar", 0);

    menuSwitch:
    switch (menuLog){

        case 1:
        long idEntrada = longAsk("Ingrese el número de identificación.");
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
                newId = longAsk("Genere un nuevo número de identificación.");
            }
        }

        while (newId <= 0){
            customPrint("La identificación debe ser un número entero positivo.", true, "red");
            newId = longAsk("Genere un nuevo número de identificación.");
        }

        empresa = new Cliente("Empresa", newId);
        customPrint("Cliente creado:\n" + empresa, true, "green");
        
    }

        final float CALIFICACION_ALTA = 4.0f; //por ahora
        List<Actor> actorsForRental = new ArrayList<>(Actor.getActors());

        //primera ronda de preguntas
        byte[] options = new byte[8];
        options[0] = 1; options[1] = 2;
        
        //antes de empezar, remover aquellos actores en condición de reevaluación
        actorsForRental.removeIf(actor -> actor.isReevaluacion());

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

        byte[] five = {1, 2, 3, 4, 5};
        byte aptitud = ask("¿En qué aptitud debería sobresalir el actor?\n1. Canto.\n2. Baile.\n3. Discurso.\n4. Emocionalidad\n5. Improvisación.", five, "");
        byte CALIFICACION_APTITUD_ALTA = 4;

        switch (aptitud){

            case 1:
            actorsForRental.removeIf(actor -> actor.getCalificacionPorAptitud(Aptitud.CANTO) < CALIFICACION_APTITUD_ALTA);
            break;

            case 2:
            actorsForRental.removeIf(actor -> actor.getCalificacionPorAptitud(Aptitud.BAILE) < CALIFICACION_APTITUD_ALTA);
            break;

            case 3:
            actorsForRental.removeIf(actor -> actor.getCalificacionPorAptitud(Aptitud.DISCURSO) < CALIFICACION_APTITUD_ALTA);
            break;
            
            case 4:
            actorsForRental.removeIf(actor -> actor.getCalificacionPorAptitud(Aptitud.EMOCIONALIDAD) < CALIFICACION_APTITUD_ALTA);
            break;

            case 5:
            actorsForRental.removeIf(actor -> actor.getCalificacionPorAptitud(Aptitud.IMPROVISACION) < CALIFICACION_APTITUD_ALTA);
            break;

        }

        //PREGUNTA NO. 3, 4, 5 (HORARIOS)
        String diasCadena = "¿Para qué día se necesita la contratación?\n";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("es"));

        for (int i = 0; i < getWeek().size(); i++){
            diasCadena += (i+1) + ". " + getWeek().get(i).format(formatter) + "\n";
        }

        byte[] seven = {1, 2, 3, 4, 5, 6, 7};

        byte dia = ask(diasCadena, seven, "");

        LocalDate diaEscogido = getWeek().get( dia-1 );

        customPrint("Lineamiento interno de horarios:\n1. El tiempo mínimo de contratación es de 4 horas.\n2. El tiempo máximo de contratación es de 8 horas.\n3. Solo se puede contratar desde las 10:00 hasta las 22:00.", true, "blue");

        LocalTime inicioHorario = timeAsk("Introduzca horario de inicio de la contratación (Responda en formato HH:MM).");
        LocalTime finHorario = timeAsk("Introduzca horario de fin de la contratación (Responda en formato HH:MM).");

        LocalTime horaMin = LocalTime.of(8, 0);
        LocalTime horaMax = LocalTime.of(22, 0); 

        if (inicioHorario.isBefore(horaMin) || finHorario.isAfter(horaMax)){
            customPrint("El horario de contratación ocurre fuera de los límites del lineamiento.", true, "red"); return;
        }

        LocalDateTime fechaInicio = diaEscogido.atTime(inicioHorario);
        LocalDateTime fechaFin = diaEscogido.atTime(finHorario);

        if (fechaFin.isBefore(fechaInicio)){
            customPrint("La hora de fin del horario ocurre antes que la del inicio.", true, "red"); return;
        }

        Duration duration = Duration.between(fechaInicio, fechaFin);
        long duracionContrato = duration.toHours();
        
        if (duracionContrato < 4){
            customPrint("El tiempo mínimo de contratación es de 4 horas.", true, "red"); return;
        }

        if (duracionContrato > 8){
            customPrint("El tiempo máximo de contratación es de 8 horas.", true, "red"); return;
        }

        actorsForRental.removeIf(actor -> !actor.isDisponible(fechaInicio, fechaFin));

        //preseleccionados

        if (actorsForRental.size() == 0){ 
            customPrint("No hay artistas disponibles con los requerimientos pedidos.", true, "red"); return;
        } else {
            customPrint(actorsForRental.size() + " actor/es encontrado/s durante la preselección.", true, "green");
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
        byte advancedSearch = ask("¿Se desea hacer búsqueda avanzada? (incluye filtros por edad y sexo).\n1. Sí.\n2. No.", two, "blue");

        if (advancedSearch == 1){

            List<Contador> contadores = new ArrayList<Contador>();

            for (Actor actor : actorsForRental){
                contadores.add(new Contador(actor, 0));
            }

            byte edad = ask("¿Qué tipo de edad se busca?\n1. Infantil\n2. Juvenil.\n3. Adulto.\n4. Adulto mayor", options, "");

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


            byte sexo = ask("¿Qué sexo debe tener el actor?\n1. Masculino.\n2. Femenino.", two, "");

            if (sexo == 1){ //si es masculino, remueve el sexo femenino y viceversa
                actorsForRental.removeIf(actor -> actor.getSexo() == 'F');
            } else {
                actorsForRental.removeIf(actor -> actor.getSexo() == 'M');
            }

            for (Contador contador : contadores){

                if (isIn(actorsForRental, contador.getActor())){ contador.numero ++; }

            }

            contadores.removeIf(contador -> contador.numero < 2);

            if (contadores.size() == 0){
                customPrint("No se encontraron actores que se ajusten bien a las características.", true, "red"); return;}

            List<Actor> advancedList = new ArrayList<Actor>();

            for (Contador contador : contadores){
                advancedList.add(contador.getActor());
            }
            
            actorsForRental = advancedList;
            advancedList = null;
            
            customPrint(actorsForRental.size() + " actor/es se ajustaron a dos o más características avanzadas.", true, "green");

            }

        double minActorPrecio = actorsForRental.get(0).getPrecioContrato(duracionContrato);
        double maxActorPrecio = actorsForRental.get(0).getPrecioContrato(duracionContrato);

        //conseguir precio de contrato min y max para que el usuario sepa el rango
        for (Actor actor : actorsForRental){

            if (actor.getPrecioContrato(duracionContrato) > maxActorPrecio){
                maxActorPrecio = actor.getPrecioContrato(duracionContrato);
            } else if (actor.getPrecioContrato(duracionContrato) < minActorPrecio){
                minActorPrecio = actor.getPrecioContrato(duracionContrato);
            }

        }    

        long presupuesto = longAsk("¿Cuál es el presupuesto máximo para el actor?" + "\nTenga en cuenta que el rango de los precios es de " + Actor.formatoPrecio(minActorPrecio) + " a " + Actor.formatoPrecio(maxActorPrecio));

        actorsForRental.removeIf(actor -> actor.getPrecioContrato(duracionContrato) > presupuesto);

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
            byte[] byteActores = null;


            if (actorsForRental.size()%ACTORES_POR_PAGINA != 0){ //cuando no se pueden dividir los actores en paginas de la misma cantidad
                paginasResiduales = true;
                paginasTotales = paginasCompletas + 1;
                byteActores = new byte[ACTORES_POR_PAGINA + 1];

                if (actorsForRental.size() < ACTORES_POR_PAGINA){
                    for (int i = 0; i < actorsForRental.size(); i++){
                        byteActores[i] = (byte) i;
                    }
                } else{
                    for (int i = 0; i < byteActores.length; i++){
                        byteActores[i] = (byte) i;
                    }
                }

            } else{ //si se divide perfectamente
                byteActores = new byte[ACTORES_POR_PAGINA + 1];

                for (int i = 0; i < byteActores.length; i++){
                    byteActores[i] = (byte) i;
                }

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
                customPrint(page);

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

                //reevaluar si los actores que salen en la siguiente pagina son 5 (ACTORES POR PAGINA) o menos
                byte[] residualByteActores = new byte[lastResidualIdx + 1];
                for (int i = 0; i <= lastResidualIdx; i++){
                    residualByteActores[i] = (byte) i;
                }

                seguirBuscando = ask("Ingrese 0 para ver la siguiente página\nSi ya decidió el actor, presione su número", residualByteActores, "");
                
                if (seguirBuscando != 0){
                    
                    continuar = false;
                    //para conseguir el indice, se le debe restar al ultimo indice del intervalo 
                    //la diferencia entre la cantidad de actores en la ultima pagina (menor a 5) y la opcion escogida
                    //si la opcion fue la 1, seria ultimoIndice - 4, si fue la 5, ultimoIndice - 0, o sea, el mismo.
                    actorEscogido = actorsForRental.get((lastIdx + lastResidualIdx) - (residualTimes - seguirBuscando));
                }

            }
        }

        customPrint("El actor escogido fue " + actorEscogido.getNombre() + " por un precio de " +  Actor.formatoPrecio(actorEscogido.getPrecioContrato(duracionContrato)));
        byte codigoCompra = empresa.pagarContratoActor(actorEscogido, duracionContrato, tesoreria);
        if (codigoCompra == -1){
            customPrint("Saldo insuficiente", true, "red");
        } else{
            customPrint("Pago recibido!", true, "green");
            ArrayList<LocalDateTime> horarioFinal = new ArrayList<>(); horarioFinal.add(fechaInicio); horarioFinal.add(fechaFin);
            actorEscogido.addHorario(horarioFinal);
        }
        customPrint("Saldo disponible: " + Actor.formatoPrecio(empresa.getCuentaBancaria().getSaldo()), true, "blue");

                
        }

    }

    //Base para funcionalidad 2
    public static void gestionEmpleados(){
        final String[] nombres = {"Miguel", "Juan", "Danna", "Carlos", "Oscar", "Julian", "Maria", "Paula", "Esteban", "Sara", "Frank", "Pablo", "Jimena", "Daniela", "Ana", "Emma", "Samuel"};
        final String [] Apellidos = {"Perez", "Hernandez", "Montoya", "Velez", "Aguirre", "Salazar", "Restrepo", "Rodriguez", "Garcia", "Lopez", "Sanchez", "Ramirez", "Gonzales", "Gomez", "Martinez"};
        //Obtener lista de empleados por ocupacion:
        //Aseador
        String msgBase = "\n";
        for(Empleado Persona : Empleado.getTipoSeguridad()){
            if(msgBase != "\n"){
                msgBase = msgBase + Persona;
            }
            else{
                msgBase = Persona + msgBase;
            }
        }
        customPrint("Seguridad", true, "red");
        customPrint(msgBase);
        msgBase = "\n";
        for(Empleado Persona : Empleado.getTipoAseador()){
            if(msgBase != "\n"){
                msgBase = msgBase + Persona;
            }
            else{
                msgBase = Persona + msgBase;
            }
        }
        customPrint("Aseador", true, "red");
        customPrint(msgBase);
        msgBase = "\n";
        for(Empleado Persona : Empleado.getTipoProfesor()){
            if(msgBase != "\n"){
                msgBase = msgBase + Persona;
            }
            else{
                msgBase = Persona + msgBase;
            }
        }
        customPrint("Profesor", true, "red");
        customPrint(msgBase);
        
        String question = "Deseas Contratar o Despedir a algun empleado \n1. Si \n2. No";
        byte[] options = {1,2};
        byte respuesta = ask(question, options, "green");

        switch (respuesta) {
            case 1:
                question = "Operacion a realizar \n1.Contratar \n2.Despedir \n0. Salir";
                byte[] options2 = {0,1,2};
                byte answer = ask(question, options2, "green");
                if(answer == 1){
                    String pregunta = "Que tipo de Empleado deseas: 0. Salir \n1. Aseador \n2. Seguridad \n3. Profesor";
                    byte[] option = {0,1,2,3};
                    byte res = ask(pregunta, option, "red");
                    Random random = new Random();
                    switch (res) {
                        case 1:
                            String Aseador = "Aseador";
                            int nombre_A = random.nextInt(nombres.length);
                            int apellido_A = random.nextInt(Apellidos.length);
                            long id_A = random.nextInt(1000000 - 100 + 1) + 100;
                            Empleado nuevo_empleado_A = new Empleado(nombres[nombre_A] + " " + Apellidos[apellido_A], id_A, Aseador);
                            customPrint("Se contrato a " + nuevo_empleado_A.getNombre());
                            break;
                        case 2:
                            String Seguridad = "Seguridad";
                            int nombre_S = random.nextInt(nombres.length);
                            int apellido_S = random.nextInt(Apellidos.length);
                            long id_S = random.nextInt(1000000 - 100 + 1) + 100;
                            Empleado nuevo_empleado_S = new Empleado(nombres[nombre_S] + " " + Apellidos[apellido_S], id_S, Seguridad);
                            customPrint("Se contrato a " + nuevo_empleado_S.getNombre());
                            break;
                        case 3:
                            String Profesor = "Profesor";
                            int nombre_P = random.nextInt(nombres.length);
                            int apellido_P = random.nextInt(Apellidos.length);
                            long id_P = random.nextInt(1000000 - 100 + 1) + 100;
                            Empleado nuevo_empleado_P = new Empleado(nombres[nombre_P] + " " + Apellidos[apellido_P], id_P, Profesor);
                            customPrint("Se contrato a " + nuevo_empleado_P.getNombre());
                            break;
                        case 0:
                            break;
                    }
                }
                else{
                    if(answer == 2){
                        question = "Introduce el id del trabajador a despedir";
                        long buscar_id = longAsk(question);
                        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                            if(Persona.getId() == buscar_id){
                                double liquidacion = (Persona.calcularSueldo()*1.2) + Persona.getDeuda();
                                tesoreria.getCuenta().transferencia(Persona.getCuenta(), liquidacion);
                                Empleado.getEmpleadosPorRendimiento().remove(Persona);
                                customPrint("Se despidio a " + Persona.getNombre());
                            }
                        }
                    }
                    else{
                        break;
                    }
                }
        
            case 2:
                break;
        }
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            customPrint("La pausa fue interrumpida.");
            
        }
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
 //FUNCIONALIDAD 4
    public static void gestionClases() throws InterruptedException {

        byte[] dos = {1,2};

        customPrint("Bienvenido a la gestión de clases.");
        Thread.sleep(2000);
        long idArtista = longAsk("Ingrese el ID del artista:");
        
        Artista artista = Artista.buscarArtistaPorId(idArtista);
        
        if (artista == null) {
            customPrint("Artista no encontrado.", "red");
            Thread.sleep(1000);
            byte crearArtista = ask("¿Desea crear un nuevo Artista?\n" + "1. Sí\n" + "2. No", new byte[]{1, 2}, "");
        
            switch (crearArtista) {
                case 1:
                    String nombreArtista = ask("Ingrese el nombre del nuevo artista:");
                    String tipoArtista = ask("Ingrese el tipo de artista (director/actor)");
        
                    if (tipoArtista.equals("director")) {
                        // Crear un nuevo director
                        new Director(nombreArtista, idArtista);
                        customPrint("Nuevo director agregado: " + nombreArtista + " con ID " + idArtista, "green");
                        customPrint("Recuerde que los directores no reciben clases.", "yellow");
                        return; // Salir del flujo de clases
                    } else if (tipoArtista.equals("actor")) {
                        // Crear un nuevo actor
                        Actor nuevoActor = new Actor(nombreArtista, idArtista);
                        customPrint("Nuevo actor agregado: " + nombreArtista + " con ID " + idArtista, "green");
                        artista = nuevoActor; // Asignar al artista actual
                    } else {
                        customPrint("Tipo de artista no válido. Debe ser 'director' o 'actor'.", "red");
                        return; // Salir si el tipo es inválido
                    }
                    break;
        
                case 2:
                    customPrint("Finalizando gestión de clases.", "blue");
                    return; // Salir si no desea crear un nuevo artista
            }
        }
        
        // Si el artista no es un actor, finalizar el flujo
        if (artista instanceof Actor) {
            Actor actor = (Actor) artista;
        
            // Mostrar áreas de mejora recomendadas
            List<Aptitud> areasDeMejora = actor.obtenerAreasDeMejora();
            customPrint("Áreas recomendadas para mejorar:");
            for (int i = 0; i < Math.min(3, areasDeMejora.size()); i++) {
                Aptitud aptitud = areasDeMejora.get(i);
                double calificacion = actor.getCalificacionPorAptitud(aptitud);
                customPrint("- " + aptitud + " (Calificación: " + calificacion + ")", "yellow");
            }
        
            // Preguntar si quiere seguir la recomendación
            byte respuesta = ask("¿Desea programar una clase basada en las áreas recomendadas?\n1. Sí\n2. No", dos, "");
            Aptitud areaSeleccionada = null;
        
            if (respuesta == 1) {
                // Seleccionar el área de mejora más baja recomendada
                areaSeleccionada = areasDeMejora.get(0);
                customPrint("Se seleccionó el área '" + areaSeleccionada + "' automáticamente.");
            } else {
                byte respuesta1 = ask("¿Desea programar otra clase?\n1. Sí\n2. No", dos, "");
                if (respuesta1 == 1){
                // Permitir al usuario elegir cualquier aptitud
                    customPrint("Seleccione un área para programar una clase:");
                    for (int i = 0; i < actor.getAptitudes().size(); i++) {
                        Aptitud aptitud = actor.getAptitudes().get(i);
                        customPrint((i + 1) + ". " + aptitud);
                    }
                    byte[] opcionesAptitudes = new byte[actor.getAptitudes().size()];
                    for (byte i = 0; i < opcionesAptitudes.length; i++) {
                        opcionesAptitudes[i] = (byte) (i + 1);
                    }
                    
                    byte opcion = ask("Ingrese el número del área deseada:", opcionesAptitudes, "");
                    areaSeleccionada = actor.getAptitudes().get(opcion - 1);
                }
                else {
                    customPrint("Finalizando gestión de clases.", "blue");
                    return;
                }
            }
        
            // Determinar el nivel de la clase
            double calificacionActual = actor.getCalificacionPorAptitud(areaSeleccionada);
            String nivelClase;
            if (calificacionActual < 3.0) {
                nivelClase = "Introducción";
            } else if (calificacionActual < 4.0) {
                nivelClase = "Profundización";
            } else {
                nivelClase = "Perfeccionamiento";
            }

            customPrint("Se seleccionó el área '" + areaSeleccionada + "' con nivel de clase: " + nivelClase);

            // Uso del método
            LocalDateTime inicio = solicitarHorario("inicio");
            LocalDateTime fin = solicitarHorario("fin");

            // Validar que la hora de fin sea posterior a la de inicio
            if (fin.isBefore(inicio)) {
                customPrint("El horario de fin debe ser posterior al horario de inicio. Intente nuevamente.", "red");
                inicio = solicitarHorario("inicio");
                fin = solicitarHorario("fin");
            }
        
            // Buscar una sala disponible en el horario deseado
            Sala salaAsignada = null;
            for (Sala sala : Sala.getSalas()) {
                if (sala.getAseado() && sala.isDisponible(inicio, fin)) {
                    salaAsignada = sala;
                    break;
                }
            }
        
            if (salaAsignada == null) {
                customPrint("No hay salas disponibles en el horario deseado o no están limpias.", "red");
                return;
            }
        
            customPrint("Sala asignada: " + salaAsignada.getNumeroSala());
        
            // Asignar el horario a la sala
            ArrayList<LocalDateTime> nuevoHorario = new ArrayList<>();
            nuevoHorario.add(inicio);
            nuevoHorario.add(fin);
            salaAsignada.anadirHorario(nuevoHorario);
        
            // Buscar un profesor capacitado y disponible (aleatoriamente)
            Profesor profesorAsignado = null;
            for (Empleado empleado : Empleado.getTipoProfesor()) {
                if (empleado instanceof Profesor) {
                    Profesor profesor = (Profesor) empleado;
        
                    // Simular disponibilidad aleatoria
                    boolean disponible = Math.random() > 0.5; // 50% de probabilidad de estar disponible
                    if (profesor.tieneEspecializacion(areaSeleccionada) && disponible) {
                        profesorAsignado = profesor;
                        break;
                    }
                }
            }
        
            if (profesorAsignado == null) {
                customPrint("No hay profesores disponibles con especialización en el área seleccionada o están ocupados.", "red");
                return;
            }
        
            customPrint("Profesor asignado: " + profesorAsignado.getNombre());
        
            // Clase programada exitosamente
            customPrint("Clase programada exitosamente en el área '" + areaSeleccionada + "' con el profesor '" 
                + profesorAsignado.getNombre() + "' en la sala '" + salaAsignada.getNumeroSala() + "'.", "green");
        } else {
            customPrint("Solo los actores pueden recibir clases.", "red");
        }

        




            //Pago de la clase
    }// Fin del método
}

