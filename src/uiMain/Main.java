package uiMain;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Collections;
import java.util.Comparator;
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
import gestorAplicacion.gestionFinanciera.CuentaBancaria;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.gestionVentas.Silla;
import gestorAplicacion.gestionVentas.Tiquete;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionObras.Artista;
import gestorAplicacion.gestionObras.Obra;
import gestorAplicacion.herramientas.Aptitud;
import gestorAplicacion.herramientas.Asiento;
import gestorAplicacion.herramientas.Genero;
import gestorAplicacion.herramientas.InterfaceTipos;
import gestorAplicacion.herramientas.Suscripcion;
import gestorAplicacion.gestionObras.Director;
import test.funcionalidad2;


public class Main {
    static Tesoreria tesoreria = new Tesoreria(0, 100);
    public static Scanner in = new Scanner(System.in);
    //public static boolean supportsColor = (System.console() != null && System.getenv().get("TERM") != null);
    public static boolean supportsColor = true;

    //------------------HERRAMIENTAS-------------------------//
    //pregunta y devuelve cadena respuesta
    public static String ask(String question){
        customPrint(question, true, "blue");
        String answer = in.nextLine();
        return answer;
    }

    public static void wait(int millis){
        try {
            Thread.sleep(millis);
        } catch (Exception e) {}

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
            customPrint(question, true, "blue");
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

    public static boolean canBeFloat(String cadena){
        try{
            Float.parseFloat(cadena);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean canBeInt(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //pregunta y devuelve long
    public static long longAsk(String question){
        customPrint(question, "blue");

        String input = in.nextLine();

        while(!canBeLong(input)){
            customPrint("La respuesta introducida no es numérica o está fuera del rango. Intente de nuevo.", true, "red");
            customPrint(question);
            input = in.nextLine();
        }
        
        long answer = Long.parseLong(input);
        return answer;
    }
    
    public static int intAsk(String question){
        customPrint(question, "blue");

        String input = in.nextLine();

        while(!canBeInt(input)){
            customPrint("La respuesta introducida no es numérica o está fuera del rango. Intente de nuevo.", true, "red");
            customPrint(question);
            input = in.nextLine();
        }
        
        int answer = Integer.parseInt(input);
        return answer;
    }

    public static float floatAsk(String question){
        customPrint(question, "blue");
        String input = in.nextLine();

        while (!canBeFloat(input)){
            customPrint("La respuesta introducida no es numérica o está fuera del rango. Intente de nuevo.", true, "red");
            customPrint(question);
            input = in.nextLine();
        }
        float answer = Float.parseFloat(input);
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

        System.out.println(chosenColor + "┌" + "─".repeat(LARGO_LINEAS) + "┐" + reset);
        String[] cadenas = cadena.split("\n");

        for (String linea : cadenas){
            System.out.println(chosenColor + formatString(linea, isCentrado) + reset);
        }

        System.out.println(chosenColor + "└" + "─".repeat(LARGO_LINEAS ) + "┘" + reset);
    }

    public static LocalDateTime[] setSchedule(String pregunta, LocalTime horaMin, LocalTime horaMax, int duracionMinHoras, int duracionMaxHoras, boolean date, String advertenciaHorarioincompatible){

        String preguntaCompleta = pregunta;
        LocalTime inicioHorario = null;
        LocalTime finHorario = null;
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;
        LocalDate diaEscogido = null;
        byte[] seven = {1, 2, 3, 4, 5, 6, 7};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("es"));

        if(date){
            
            preguntaCompleta += "\n";

            for (int i = 0; i < getWeek().size(); i++){
                preguntaCompleta += (i+1) + ". " + getWeek().get(i).format(formatter) + "\n";

            }

            byte dia = ask(preguntaCompleta, seven, "");
            diaEscogido = getWeek().get( dia-1 );

        } else{

            customPrint(preguntaCompleta);
        }

        while(true){
            inicioHorario = timeAsk("Introduzca horario de inicio (Responda en formato HH:MM).");
            finHorario = timeAsk("Introduzca horario de fin (Responda en formato HH:MM).");


            if (inicioHorario.isBefore(horaMin) || finHorario.isAfter(horaMax) || finHorario.isBefore(inicioHorario) ){
                customPrint(advertenciaHorarioincompatible, true, "red");
                continue;
            }

            if (date){
            fechaInicio = diaEscogido.atTime(inicioHorario);
            fechaFin = diaEscogido.atTime(finHorario);
            
            } else{

                fechaInicio = getWeek().get(0).atTime(inicioHorario);
                fechaFin = getWeek().get(0).atTime(finHorario);
            }

            Duration duration = Duration.between(fechaInicio, fechaFin);

            long duracionHorario = duration.toHours();

            if (duracionHorario < duracionMinHoras){
                customPrint("El tiempo mínimo es de " + duracionMinHoras + " horas.", true, "red"); 
                continue;
            }

            if (duracionHorario > duracionMaxHoras){
                customPrint("El tiempo máximo es de " + duracionMaxHoras + " horas.", true, "red");
                continue;
            }

            break;
        }

        return new LocalDateTime[]{fechaInicio, fechaFin};

    }
    //---------------------------------------------------------//

    public static ArrayList<LocalDate> getWeek(){
        LocalDate today = LocalDate.now(); // Día actual
        ArrayList<LocalDate> week = new ArrayList<>();

        // Generar los próximos 7 días
        for (int i = 1; i < 8; i++) {
            week.add(today.plusDays(i)); // Sumar días al día actual
        }
        return week;
    }
// creación de salas
    Sala sala1 = new Sala(1, 100, 24);

    public static void main(String args[]) throws InterruptedException {  
        funcionalidad2.empezar();
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
                gestionClases();
                break;

                case 5: 
                ContratarActor(); 
                break;

                case 6:
                {}break;
            }
        }

        
        Sala sala1 = new Sala(1, 100, 24);
        Sala sala2 = new Sala(2, 200, 48);
        Sala sala3 = new Sala(3, 50, 16);
        Sala sala4 = new Sala(4, 150, 24);
    }

    public static void gestionVentas(){
        Silla silla = new Silla(Asiento.BASICO);
        Tiquete tiquete = new Tiquete();

        InterfaceTipos asiento = Asiento.BASICO;
        InterfaceTipos suscription = Suscripcion.Basica;
        String confirmacion="";
        float dineroTesoreria=0;
        byte [] opciones_2 = {1,2,3};
        Cliente cliente= null;
        ArrayList <Integer> lista = new ArrayList<>();
        customPrint(
        "Ingrese la opcion correspondiente\n"+
        "Eres cliente nuevo? \n"+ 
        "1. NO\n"+
        "2. SI\n"+
        "3. MENU PRINCIPAL");
        

        
        byte p_ = in.nextByte();
        in.nextLine();

        while (!isIn(opciones_2, p_)){

            customPrint("La respuesta introducida no hace parte de las opciones. Intente de nuevo:\n\n"+
            "Ingrese la opcion correspondiente\n"+
            "Eres cliente nuevo? \n"+ 
            "1. NO\n"+
            "2. SI\n"+
            "3. MENU PRINCIPAL","red");
            p_ = in.nextByte();
            in.nextLine();
        }
        

        boolean salir = false;
    
        while (!salir) {
        switch (p_) {
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
                    "2. NO\n"+
                    "3. MENU PRINCIPAL","red");
                    
                    byte [] opcion = {1,2,3};
                    byte by = in.nextByte();
                    in.nextLine();

                    while (!isIn(opcion, by)){
                        customPrint("La respuesta introducida no hace parte de las opciones. \n"+
                        "Intente de nuevo:\n"+
                        "Tienes un codigo existente? : \n"+ 
                        "1. NO\n"+
                        "2. SI\n"+
                        "3. MENU PRINCIPAL","red");
                        by = in.nextByte();
                        in.nextLine();
                    }
                    if (by == 1) {
                        break;
                        
                    }else if(by==3){
                        return;
                    }

                    

                    
                    
                }
            case 3:
                return;
                
            case 2:
                customPrint("Creando Nuevo Codigo...");
                try {
                    // Pausa de 2 segundos (2000 milisegundos)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    customPrint("La pausa fue interrumpida.");
                    
                }
                
                code= Cliente.IdRandom();
                cliente = new Cliente(code,Suscripcion.Basica);
                customPrint("Codigo "+cliente.getId()+ " creado","green");

                
                salir = true;
            
            
                
            }
        }
        boolean antiguo = true;
        customPrint(cliente.consultarPerfil());
        if (cliente.getSuscripcion().name().equals("Basica")){
            antiguo = false;

        }
        
        

        
            customPrint(
        "Ingrese la opcion correspondiente\n"+
        "Desea mejorar su suscripcion? \n"+ 
        "1. Si\n"+
        "2. No\n"+
        "3. MENU PRINCIPAL","blue");
        

        
        Byte p1_ = in.nextByte();
        in.nextLine();
            while (p1_ != 1 & p1_ != 2 & p1_ !=3) {
                customPrint("La respuesta introducida no hace parte de las opciones.\n"+
        "Ingrese la opcion correspondiente\n"+
        "Desea mejorar su suscripcion? \n"+ 
        "1. SI\n"+
        "2. NO\n"+
        "3. MENU PRINCIPAL","red");
        p1_ = in.nextByte();
        in.nextLine();

                
            }
            
            
        String suscripcion="";
        if(p1_ == 1){
            
            customPrint(suscription.tipos());
                
                customPrint(
            "Que suscripcion desea adquirir?\n\n"
            );
            suscripcion = in.nextLine().toLowerCase();
                
            while (suscription.imprimirTipos(suscripcion)){

            customPrint("La respuesta introducida no hace parte de las opciones. \n"+
            "Intente de nuevo:\n"+
            "Que suscripcion desea adquirir\n\n","red"
            
            );
            suscripcion = in.nextLine().toLowerCase();
        }
        
            customPrint("Suscripcion "+suscripcion+" aplicada","green");
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

        }
        else if (p1_==3){
            return;
        }
        customPrint("Estas son las funciones disponibles\n\n"+String.format("%30s %22s %22s %15s", "Nombre Obra", "Genero", "Duracion","Precio")+"\n\n"+Funcion.generarTabla());
            customPrint("Que funcion desea comprar? \n");
            String inputF = in.nextLine().toLowerCase();
            float precioSus=0;
            while (Obra.nombres(inputF)){
                customPrint("Funcion no encontrada \n"+
                "Ingrese un nombre valido :","red");
                inputF = in.nextLine().toLowerCase();
                



            }
            


            
            customPrint("Funcion seleccionada: \n\n"+Obra.imprimirObra(Obra.buscarObra(inputF)));
            
            
            
            float descuento=0;
            if (cliente.getSuscripcion().name().equals("Basica")) {
                descuento = 0;
                precioSus = 0;
            } else if (cliente.getSuscripcion().name().equals("Vip")) {
                descuento = 0.25f;
                precioSus = 18900;
            } else if (cliente.getSuscripcion().name().equals("Premium")) {
                descuento = 0.10f;
                precioSus = 11900;
            } else if (cliente.getSuscripcion().name().equals("Elite")){
                confirmacion="Tienes un asiento Gold gratis";
                descuento = 1;
                precioSus = 39900;              
            }
            

            if (antiguo==true ){
                precioSus = 0;
            }
            System.out.println(antiguo);
            boolean antiguof;
            if (antiguo){
                antiguof=true;

            }else{
                antiguof=false;
            }
        
            


            descuento = 1-descuento;
            String mensajeDescuento="";
            String mensaje;
            if (descuento==0){
                mensaje = "Su funcion es gratiss\n" ;
            }else{
                mensaje="";
            }
            if (descuento !=1.0f){
                mensajeDescuento = "luego de descuento es :";

            }
            
            

            customPrint(asiento.tipos());
            customPrint("Que Asiento desea comprar? \n"+
            "Ingrese solo el codigo \n"+
            "La letra representa el tipo del asiento\n\n"+
            "G- Sillas Gold Exclusivas Suscripcion Gold\n"+
            "P- Sillas Premium Exclusivas Suscripcion Vip\n "+
            "C- Sillas Comfort Exclusivas Suscripcion Premium\n"+
            "B- Sillas Basicas ");
            
            Funcion funcion=Funcion.buscarFuncion(inputF);
            customPrint(funcion.tablaSillas());
            Integer codigo = in.nextInt();
            while (Funcion.buscarFuncion(inputF).verificar(codigo) | cliente.verificarSuscripcion(funcion.asignarTipoSilla(codigo))) {
                
            
                while(cliente.verificarSuscripcion(funcion.asignarTipoSilla(codigo))){
                    customPrint(
                    "Tu suscripcion "+cliente.getSuscripcion().name()+"\n"+
                    "No te permite acceder a este tipo de asientos\n"+
                "Intente con uno nuevo","red");
                codigo = in.nextInt();
    
    
                }
            while(funcion.verificar(codigo)){
                customPrint(
                "Ingrese un asiendo valido :\n"+
                "Ingrese solo el codigo \n"+
            "La letra representa el tipo del asiento","red");
            codigo = in.nextInt();

            }
        }
        tiquete.setSilla(funcion.asignarSilla(codigo));
        

            Funcion.buscarFuncion(inputF).eliminarSilla(codigo);
            customPrint(funcion.tablaSillas());
            
            

        

        
        float precioFuncion=Funcion.mostrarPrecioFuncion(inputF);
            customPrint(mensaje+"\nTotal a pagar\n\n "+mensajeDescuento+String.format("$%,.2f",((precioFuncion*descuento)+precioSus)));
            customPrint(
    "1. Realizar compra\n"+
        "2. Cancelar Compra \n"
        ,"blue");
        

        
        byte a = in.nextByte();
        in.nextLine();
            while (a != 1 & a != 2 ) {
                customPrint("La respuesta introducida no hace parte de las opciones.\n"+
        "Ingrese la opcion correspondiente\n"+
        "1. Realizar compra\n"+
        "2. Cancelar Compra \n"
        ,"red");
        a = in.nextByte();
        in.nextLine();
        
        
        
        cliente.setSuscripcion(Suscripcion.Basica);

                
            }
            if (a==1){
                
                customPrint("Realizando Compra");
                dineroTesoreria = ((Funcion.mostrarPrecioFuncion(inputF)*descuento)+precioSus);
                tesoreria.setDineroEnCaja(tesoreria.getDineroEnCaja()+dineroTesoreria);
                tesoreria.setTotal(tesoreria.getTotal()+dineroTesoreria);
            try {
                // Pausa de 2 segundos (4000 milisegundos)
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                customPrint("La pausa fue interrumpida.");
                
            }
            
        
        
        
            float precioTotalFuncion=precioSus+precioFuncion-dineroTesoreria;
            customPrint("Compra Realizada","green");
            cliente.setObra(inputF);
            cliente.setTiquete(tiquete);
            tiquete.setFuncion(funcion);
            tiquete.setValor(dineroTesoreria);
            
            Obra.buscarObra(inputF).recurrencia();
            
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
            
            customPrint(tiquete.imprimirFactura(cliente,antiguof,precioTotalFuncion,precioFuncion,precioSus));
        }else{
            customPrint("Cancelando Compra...");
            try {
                // Pausa de 2 segundos (4000 milisegundos)
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                customPrint("La pausa fue interrumpida.");
                
            }
            customPrint("Compra Cancelada");
            return;
        }
            
        
                

        
        




        

    }

    public static void gestionObras(){
        ArrayList<LocalDateTime> horarioEstObra1 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra2 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra3 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra4 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra5 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra6 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra7 = new ArrayList<>();
        ArrayList<LocalDateTime> horarioEstObra8 = new ArrayList<>();
        horarioEstObra1.add(LocalDateTime.of(2024,1,1,9,00));
        horarioEstObra1.add(LocalDateTime.of(2024,1,1,12,30));
        horarioEstObra2.add(LocalDateTime.of(2024, 1, 1, 15, 00));
        horarioEstObra2.add(LocalDateTime.of(2024,1,1,21,30));
        horarioEstObra3.add(LocalDateTime.of(2024,1,1,13,00));
        horarioEstObra3.add(LocalDateTime.of(2024,1,1,22,30));
        horarioEstObra4.add(LocalDateTime.of(2024, 1, 1, 12, 00));
        horarioEstObra4.add(LocalDateTime.of(2024,1,1,22,30));
        horarioEstObra5.add(LocalDateTime.of(2024,1,1,16,00));
        horarioEstObra5.add(LocalDateTime.of(2024,1,1,22,30));
        horarioEstObra6.add(LocalDateTime.of(2024, 1, 1, 20, 00));
        horarioEstObra6.add(LocalDateTime.of(2024,1,1,23,59));
        horarioEstObra7.add(LocalDateTime.of(2024,1,1,17,00));
        horarioEstObra7.add(LocalDateTime.of(2024,1,1,23,30));
        horarioEstObra8.add(LocalDateTime.of(2024, 1, 1, 10, 00));
        horarioEstObra8.add(LocalDateTime.of(2024,1,1,17,30));
        Sala sala1 = new Sala(1, 100, 24);
        Sala sala2 = new Sala(2, 200, 48);
        Sala sala3 = new Sala(3, 50, 16);
        Sala sala4 = new Sala(4, 150, 24);
        Obra obra1 = null;
        for (Obra obra : Obra.getObras()) {
            if (obra.getNombre().trim().equals("NOTFORITE")) { 
                obra1 = obra;
                break;
            }
        }
    
        // Si obra1 no existe, se crea y se agrega al listado
        if (obra1 == null) {
            obra1 = new Obra(new Funcion(horarioEstObra1), Genero.EXPERIMENTAL,"NOTFORITE");
            Obra obra2 = new Obra(new Funcion(horarioEstObra2), Genero.DRAMA, "NOTFORITE");
            Obra obra3 = new Obra(new Funcion(horarioEstObra3), Genero.COMEDIA,"NOTFORITE");
            Obra obra4 = new Obra(new Funcion(horarioEstObra4), Genero.MUSICAL, "NOTFORITE");            
            Obra obra5 = new Obra(new Funcion(horarioEstObra5), Genero.FANTASIA,"NOTFORITE");
            Obra obra6 = new Obra(new Funcion(horarioEstObra6), Genero.TERROR, "NOTFORITE");            
            Obra obra7 = new Obra(new Funcion(horarioEstObra7), Genero.ROMANCE,"NOTFORITE");
            Obra obra8 = new Obra(new Funcion(horarioEstObra8), Genero.CIRCO, "NOTFORITE");
        }
        
        int i;
        i = 0;
        int o = 0;
        Obra eleccion;
        eleccion = null;
        String menuObras = "";
        ArrayList<LocalDate> week = getWeek();
        if (!Obra.getObras().isEmpty()){
            for (Obra obra : Obra.getObras()){   
                if(obra.getNombre() != "NOTFORITE"){       
                    i = i + 1;
                    String item = String.valueOf(i) + "." + obra.getNombre() + "\n";
                    menuObras = menuObras + item;
                }
                else{
                    o++;
                }
            }
        }
        customPrint(menuObras + String.valueOf(i + 1) + ". Crear nueva obra");
        String obraSel = ask("Por favor indique el número de su elección sin punto");
        if (Integer.parseInt(obraSel) <= i){
            eleccion = Obra.getObras().get(Integer.parseInt(obraSel) - 1 + o);
        }
        else if (Integer.parseInt(obraSel) > i){
            String nombre = ask("Por favor ingrese el nombre de la nueva obra");
            int f;
            f = 0;
            String actores = "";
            for (Actor actor : Actor.getActors()){
                f = f + 1;
                actores = actores + String.valueOf(f) + "."+ actor.getNombre();
            }
            customPrint(actores);
            int s;
            s = 1;
            ArrayList<Actor> reparto = new ArrayList<>();
            ArrayList<Aptitud> papeles = new ArrayList<>();
            while (s != 0){
                String d = ask("Digita el número del actor que desea agregar sin punto\n Si ya terminaste de añadir el reparto por favor ingresa 0");
                s = Integer.parseInt(d);
                if (s == 0){
                    break;
                }
                else{
                    Actor elegido = Actor.getActors().get(s - 1);
                    reparto.add(elegido);
                }
                    byte[] listByte= {1, 2, 3, 4, 5};                
                    byte u = ask("Por favor indica en qué se debe enfocar el actor \n (Solo puedes seleccionar una opción, sin embargo,\n varios actores pueden enfocarse en la misma opción) \nrecuerde digitar solo el número de la opción\n1. Canto\n2. Baile\n3. Discurso\n4. Emocionalidad\n5. Improvisación", listByte, "blue");
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
            
            customPrint("1. Drama \n2. Comedia \n3. Musical \n4. Fantasía \n5. Terror \n6. Romace \n7. Circo \n8. Experimental");
            byte[] listBytesGen = {1, 2, 3, 4, 5, 6, 7, 8};
            byte l = ask("Indica el número asociado al género <3", listBytesGen, "blue");
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
            String directorSel = ask("Indique el director de la obra <3");
            if (Integer.parseInt(directorSel) <= x){
                eleccionDir = genero.getDirectores().get(Integer.parseInt(directorSel) - 1);
            }
            else if (Integer.parseInt(directorSel) > x){
                String nDirector = ask("Por favor ingrese el nombre del nuevo director");
                long idDirector = longAsk("Por favor ingrese el número de documento del nuevo director");
                eleccionDir = new Director(nDirector, idDirector, genero);
                customPrint("Director creado: \n" + eleccionDir, "green");
                }
            Director director = eleccionDir;
            x = 0;

            float costoProduccion = floatAsk("Por favor, ingresa el costo de producción");
            long dur = longAsk("Por favor ingresa la duración de la obra, \nusa el formato HHmmSS, no separes con :,- ni otro símbolo similar.");
            
            eleccion = new Obra(nombre, reparto, papeles, director, costoProduccion, genero, dur);  
            }
            customPrint("Has seleccionado" + " " + eleccion.getNombre());
            int a = eleccion.getFuncionesRecomendadas();
            boolean continuar = false;
            int drut = 0;
            do {
                int rut = intAsk("¿Cuántas funciones te gustaría crear para esta obra?");
    
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

            for (int numeroFunciones = 0; numeroFunciones < drut; numeroFunciones++){
                ArrayList<LocalDate> weekn = getWeek();
                Funcion funcion = new Funcion(eleccion, weekn);
                eleccion.addFuncion(funcion);
                customPrint("Funcion creada\nHora:  " + funcion.getHorario() + "\nSala: " + funcion.getSala());
            }
            if (in.hasNextLine()) {in.nextLine();}
    }

    public static void ContratarActor(){

    byte[] two = {0, 1, 2};
    byte menuLog = ask("Seleccione:\n1. Empresa registrada.\n2. Empresa nueva.", two, "");
    byte ACTORES_POR_PAGINA = 5;

    List<Actor> historialEmpresa = new ArrayList<>();
    Cliente empresa = new Cliente("auxiliar", 0);

    menuSwitch:
    switch (menuLog){

        case 0:
        customPrint("Saliendo...", "red");
        return;

        case 1:
        long idEntrada = longAsk("Ingrese el número de identificación.");

        if (idEntrada == 0){
            customPrint("Saliendo...", "red");
            return;
        }

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

        if (newId == 0){
            customPrint("Saliendo...", "red");
            return;
        }
        
        for (Cliente cliente : Cliente.clientes){
            while (cliente.getId() == newId){
                customPrint("Esta identificación ya existe en la base de datos, intente con una diferente.", true, "red");
                newId = longAsk("Genere un nuevo número de identificación.");

                if (newId == 0){
                    customPrint("Saliendo...", "red");
                    return;
                }
            }
        }

        while (newId <= 0){
            customPrint("La identificación debe ser un número entero positivo.", true, "red");
            newId = longAsk("Genere un nuevo número de identificación.");

            if (newId == 0){
                customPrint("Saliendo...", "red");
                return;
            }
        }

        empresa = new Cliente("Empresa", newId);
        customPrint("Cliente creado:\n" + empresa, true, "green");
        
    }

        final float CALIFICACION_ALTA = 4.0f; //por ahora
        List<Actor> actorsForRental = new ArrayList<>(Actor.getActors());

        //primera ronda de preguntas
        byte[] options = new byte[9];
        options[0] = 0; options[1] = 1;
        
        //antes de empezar, remover aquellos actores en condición de reevaluación
        actorsForRental.removeIf(actor -> actor.isReevaluacion());

        //PREGUNTA NO. 1
        byte rolActor = ask("¿Qué tipo de papel desempeñará el actor?\n1. Rol principal.\n 2. Rol secundario.", options, "");

        //reservar los de calificacion alta solo para roles principales

        switch (rolActor){

            case 0:
                customPrint("Saliendo...", "red");
                return;

            case 1:
                actorsForRental.removeIf(actor -> actor.getCalificacion() < CALIFICACION_ALTA);

            case 2:
                actorsForRental.removeIf(actor -> actor.getCalificacion() > CALIFICACION_ALTA);



            }

        options[2] = 2; options[3] = 3; options[4] = 4; options[5] = 5; options[6] = 6; options[7] = 7; options[8] = 8;

        //PREGUNTA NO. 2
        byte tipoObra = ask("¿Qué tipo de obra es?\n1. Circo.\n2. Comedia.\n3. Drama.\n4. Experimental.\n5. Fantasía.\n6. Musical.\n7. Romance.\n8. Terror.", options, "");
        
        //que se borren los actores que no tengan el género buscado en sus atributos
        switch(tipoObra){

            case 0:
                customPrint("Saliendo...", "red");
                return;

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

        byte[] five = {0, 1, 2, 3, 4, 5};
        byte aptitud = ask("¿En qué aptitud debería sobresalir el actor?\n1. Canto.\n2. Baile.\n3. Discurso.\n4. Emocionalidad\n5. Improvisación.", five, "");
        byte CALIFICACION_APTITUD_ALTA = 4;

        switch (aptitud){

            case 0:
                customPrint("Saliendo...", "red");
                return;

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
        LocalTime horaMin = LocalTime.of(8, 0);
        LocalTime horaMax = LocalTime.of(22, 0); 
        String advertencia5 = "Existe una incompatibilidad del horario con el lineamiento.\n\nRevise si:\n1. El inicio del horario ocurre antes del fin del horario.\n2. Se exceden los límites de horario (muy temprano o muy tarde).\nIntente de nuevo.";

        LocalDateTime[] horario = setSchedule(diasCadena, horaMin, horaMax, 4, 8, true, advertencia5);

        LocalDateTime fechaInicio = horario[0];
        LocalDateTime fechaFin = horario[1]; 

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

        switch (advancedSearch){

            case 0:
                customPrint("Saliendo", "red");
                return;
            
            case 1:

                List<ArrayList> contadores = new ArrayList<>();
    
                for (Actor actor : actorsForRental){
                    ArrayList<Object> contador = new ArrayList<>();
                    contador.add(actor);
                    contador.add(0);
                    contadores.add(contador);
                }
    
                byte edad = ask("¿Qué tipo de edad se busca?\n1. Infantil\n2. Juvenil.\n3. Adulto.\n4. Adulto mayor", options, "");
    
                switch (edad){

                    case 0:
                        customPrint("Saliendo...", "red");
                        return;
    
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
    
                for (ArrayList contador : contadores){
    
                    if (isIn(actorsForRental, (Actor)contador.get(0))){ 
                        
                        int newVal = ((Integer)contador.get(1)) + 1;
    
                        contador.set(1, newVal); 
                    
                    }
    
                }
    
    
                byte sexo = ask("¿Qué sexo debe tener el actor?\n1. Masculino.\n2. Femenino.", two, "");
    
                if (sexo == 1){ //si es masculino, remueve el sexo femenino y viceversa
                    actorsForRental.removeIf(actor -> actor.getSexo() == 'F');
                } else if (sexo == 2){
                    actorsForRental.removeIf(actor -> actor.getSexo() == 'M');
                } else{

                    customPrint("Saliendo...", "red");
                    return;

                }
    
                for (ArrayList contador : contadores){
    
                    if (isIn(actorsForRental, (Actor)contador.get(0))){ 
                        
                        int newVal = ((Integer)contador.get(1)) + 1;
    
                        contador.set(1, newVal); 
                    
                    }
    
                }
    
                contadores.removeIf(contador -> ((Integer)contador.get(1)) < 2);
    
                if (contadores.size() == 0){
                    customPrint("No se encontraron actores que se ajusten bien a las características.", true, "red"); return;}
    
                List<Actor> advancedList = new ArrayList<Actor>();
    
                for (ArrayList contador : contadores){
                    advancedList.add( (Actor)contador.get(0));
                }
                
                actorsForRental = advancedList;
                advancedList = null;
                
                customPrint(actorsForRental.size() + " actor/es se ajustaron a dos o más características avanzadas.", true, "green");
        }

        long duracionContrato = Duration.between(fechaInicio, fechaFin).toHours();
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

        if (presupuesto == 0){
            customPrint("Saliendo...", "red");
            return;
        }

        actorsForRental.removeIf(actor -> actor.getPrecioContrato(duracionContrato) > presupuesto);

        if (actorsForRental.size() == 0){
            customPrint("No se hallaron actores para el presupuesto", true, "red");
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
        tesoreria.transferenciaFondos();
        //Verifica si hay deudas y Pagar
        customPrint("Verificando deudas ...");
        String Deudas = "";
        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
            if(Persona.getDeuda() != 0){
                if(tesoreria.getCuenta().getSaldo() > Persona.getDeuda()){
                    boolean transaccion = tesoreria.getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda());
                    if(transaccion == true){
                        Deudas = Deudas + "Se realizo el Pago a: " + Persona.getNombre() + " por un valor de: " + String.format("$%,.2f", Persona.getDeuda() ) + "\n";
                        Persona.setDeuda(0);
                    }
                }
            }
        }
        String Saldo = String.format("$%,.2f",tesoreria.getCuenta().getSaldo());
        customPrint(Deudas + "El saldo de tesoreria es: " + Saldo, "green");
        
        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            customPrint("La pausa fue interrumpida.");    
        }
        
        //Obtener lista de empleados por ocupacion:
        boolean repetidor = false;
        do{
            String msgBase = "\n";
            for(Empleado Persona : Empleado.getTipoSeguridad()){
                if(msgBase != "\n"){
                    msgBase = msgBase + String.format("%-20s %10s", Persona.getNombre(), "ID: " +  Persona.getId()) + "\n";
                }
                else{
                    msgBase = String.format("%-20s %10s", Persona.getNombre(), "ID: " + Persona.getId()) + msgBase;
                }
            }
            customPrint("Seguridad \n" + msgBase, true, "");
            msgBase = "\n";

            try{
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                customPrint("La pausa fue interrumpida.");    
            }
            
            for(Empleado Persona : Empleado.getTipoAseador()){
                if(msgBase != "\n"){
                    msgBase = msgBase + String.format("%-20s %10s", Persona.getNombre(), "ID: " +  Persona.getId()) + "\n";
                }
                else{
                    msgBase = String.format("%-20s %10s", Persona.getNombre(), "ID: " + Persona.getId()) + msgBase;
                }
            }
            customPrint("Aseador \n" + msgBase, true, "");
            msgBase = "\n";
            
            try{
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                customPrint("La pausa fue interrumpida.");    
            }
            
            for(Empleado Persona : Empleado.getTipoProfesor()){
                if(msgBase != "\n"){
                    msgBase = msgBase + String.format("%-20s %10s", Persona.getNombre(), "ID: " +  Persona.getId()) + "\n";
                }
                else{
                    msgBase = String.format("%-20s %10s", Persona.getNombre(), "ID: " + Persona.getId()) + msgBase;
                }
            }
            customPrint("Profesor \n" + msgBase, true, "");
            
            try{
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                customPrint("La pausa fue interrumpida.");    
            }

            String question = "Deseas Contratar o Despedir a algún empleado\n1. Si\n2. No";
            byte[] options = {1,2};
            byte respuesta = ask(question, options, "blue");

            switch (respuesta) {
                case 1:
                    question = "Operacion a realizar \n1.Contratar \n2.Despedir \n0. Salir";
                    byte[] options2 = {0,1,2};
                    byte answer = ask(question, options2, "blue");
                    if(answer == 1){
                        String pregunta = "Que tipo de Empleado deseas: \n0. Salir \n1. Aseador \n2. Seguridad \n3. Profesor";
                        byte[] option = {0,1,2,3};
                        byte res = ask(pregunta, option, "blue");
                        Random random = new Random();
                        switch (res) {
                            case 1:
                                int n = 0;
                                msgBase = "\n";
                                ArrayList<String> candidatos = new ArrayList<>();
                                ArrayList<Long> idA = new ArrayList<>();
                                do{
                                    int nombre = random.nextInt(nombres.length);
                                    int apellido = random.nextInt(Apellidos.length);
                                    long id = random.nextInt(1000000 - 100 + 1) + 100;
                                    String Nombre = nombres[nombre] + " " + Apellidos[apellido];
                                    candidatos.add(Nombre);
                                    idA.add(id);
                                    n = n + 1;
                                }while (n<10);
                                int j = 0;
                                for(String Nombre : candidatos){
                                    if(msgBase != "\n"){
                                        msgBase = msgBase + String.format("%-20s %10s", j +". " + Nombre,"ID: " +  idA.get(j) + "\n");
                                        j = j + 1;
                                    }
                                    else{
                                        msgBase = String.format("%-20s %10s",j +". " + Nombre, "ID: " + idA.get(j)) + msgBase;
                                        j = j + 1;
                                    }
                                }
                                msgBase = msgBase + "10. Salir";
                                customPrint("Candidados a Aseador \n" + msgBase);
                                byte[] opciones = {0,1,2,3,4,5,6,7,8,9,10};
                                byte empleado = ask("Contrata algun empleado", opciones, "blue");
                                if(empleado < 10){
                                    ArrayList<Empleado> tipAseador = new ArrayList<>();
                                    tipAseador = Empleado.getTipoAseador();
                                    Empleado nuevo_empleado_A = new Empleado(candidatos.get(empleado), idA.get(empleado), "Aseador");
                                    tipAseador.add(nuevo_empleado_A);
                                    Empleado.setTipoAseador(tipAseador);
                                    customPrint("Se contrato a " + nuevo_empleado_A.getNombre(), "green");
                                    repetidor = false;
                                break;
                                }
                                else{
                                    customPrint("No se contrato ningun empleado", true, "green");
                                    break;
                                }

                            case 2:
                                int a = 0;
                                msgBase = "\n";
                                ArrayList<String> candidatosS = new ArrayList<>();
                                ArrayList<Long> idS = new ArrayList<>();
                                do{
                                    int nombre = random.nextInt(nombres.length);
                                    int apellido = random.nextInt(Apellidos.length);
                                    long id = random.nextInt(1000000 - 100 + 1) + 100;
                                    String Nombre = nombres[nombre] + " " + Apellidos[apellido];
                                    candidatosS.add(Nombre);
                                    idS.add(id);
                                    a = a + 1;
                                }while (a<10);
                                    int b = 0;
                                for(String Nombre : candidatosS){
                                    if(msgBase != "\n"){
                                        msgBase = msgBase + String.format("%-20s %10s", b +". " + Nombre,"ID: " +  idS.get(b) + "\n");
                                        b = b + 1;
                                    }
                                    else{
                                        msgBase = String.format("%-20s %10s",b +". " + Nombre, "ID: " + idS.get(b)) + msgBase;
                                        b = b + 1;
                                    }
                                }
                                msgBase = msgBase + "10. Salir";
                                customPrint("Candidados a Seguridad \n" + msgBase);
                                byte[] opcionesS = {0,1,2,3,4,5,6,7,8,9,10};
                                byte empleadoS = ask("Contrata algun empleado", opcionesS, "blue");
                                if(empleadoS < 10){
                                    ArrayList<Empleado> tipSeguridad = new ArrayList<>();
                                    tipSeguridad = Empleado.getTipoSeguridad();
                                    Empleado nuevo_empleado_S = new Empleado(candidatosS.get(empleadoS), idS.get(empleadoS), "Seguridad");
                                    tipSeguridad.add(nuevo_empleado_S);
                                    Empleado.setTipoSeguridad(tipSeguridad);
                                    customPrint("Se contrato a " + nuevo_empleado_S.getNombre(), "green");
                                    repetidor = false;
                                break;
                                }
                                else{
                                    customPrint("No se contrato ningun empleado", true, "green");
                                    break;
                                }
                            case 3:
                                int c = 0;
                                msgBase = "\n";
                                ArrayList<String> candidatosP = new ArrayList<>();
                                ArrayList<Long> idP = new ArrayList<>();
                                do{
                                    int nombre = random.nextInt(nombres.length);
                                    int apellido = random.nextInt(Apellidos.length);
                                    long id = random.nextInt(1000000 - 100 + 1) + 100;
                                    String Nombre = nombres[nombre] + " " + Apellidos[apellido];
                                    candidatosP.add(Nombre);
                                    idP.add(id);
                                    c = c + 1;
                                }while (c<10);
                                    int d = 0;
                                for(String Nombre : candidatosP){
                                    if(msgBase != "\n"){
                                        msgBase = msgBase + String.format("%-20s %10s", d +". " + Nombre,"ID: " +  idP.get(d) + "\n");
                                        d = d + 1;
                                    }
                                    else{
                                        msgBase = String.format("%-20s %10s",d +". " + Nombre, "ID: " + idP.get(d)) + msgBase;
                                        d = d + 1;
                                    }
                                }
                                msgBase = msgBase + "10. Salir";
                                customPrint("Candidados a Seguridad \n " + msgBase);
                                byte[] opcionesP = {0,1,2,3,4,5,6,7,8,9,10};
                                byte empleadoP = ask("Contrata algun empleado", opcionesP, "blue");
                                if(empleadoP < 10){
                                    ArrayList<Empleado> tipProfesors = new ArrayList<>();
                                    tipProfesors = Empleado.getTipoProfesor();
                                    Empleado nuevo_empleado_P = new Profesor(candidatosP.get(empleadoP), idP.get(empleadoP));
                                    tipProfesors.add(nuevo_empleado_P);
                                    Empleado.setTipoProfesor(tipProfesors);
                                    customPrint("Se contrato a " + nuevo_empleado_P.getNombre(), "green");
                                    repetidor = false;
                                break;
                                }
                                else{
                                    customPrint("No se contrato ningun empleado", true, "green");
                                    break;
                                }
                            case 0:
                                repetidor = false;
                                break;
                        }
                    }
                    else{
                        if(answer == 2){
                            question = "Introduce el id del trabajador a despedir";
                            long buscar_id = longAsk(question);
                            boolean[] encontrado = {false};
                            Empleado.getEmpleadosPorRendimiento().removeIf(Persona ->{
                                if(Persona.getId() == buscar_id){
                                    encontrado[0] = true;
                                    double liquidacion = (Persona.calcularSueldo()*1.2) + Persona.getDeuda();
                                    tesoreria.getCuenta().transferencia(Persona.getCuenta(), liquidacion);
                                    customPrint("Se despidio a " + Persona.getNombre() + " y se le pago su respectiva liquidacion", "green");
                                    if(Persona.getOcupacion() != "Aseador"){
                                        if (Persona.getOcupacion() != "Seguridad"){
                                            Empleado.getTipoProfesor().remove(Persona);
                                        }
                                        else{
                                            Empleado.getTipoSeguridad().remove(Persona);
                                        }
                                    }
                                    else{
                                        Empleado.getTipoAseador().remove(Persona);
                                    }
                                    return true;
                                }
                                return false;
                            });
                            if(!encontrado[0]){
                                customPrint("No se encontro ningun trabajador con el id " + buscar_id, "red");
                            }
                        }
                        else{
                            repetidor = false;
                        }
                    }
                    break;
        
                case 2:
                    repetidor = true;
            }
        } while(!repetidor);
        
        customPrint("Asignando trabajos, por favor espere ...", true);
        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            customPrint("La pausa fue interrumpida.");    
        }

        //Organiza el ranking - Aseador - Seguridad - Profesor - Salas
        ArrayList<Empleado> Aseador_order = Empleado.getTipoAseador();
        ArrayList<Empleado> Seguridad_order = Empleado.getTipoSeguridad();
        ArrayList<Empleado> Profesor_order = Empleado.getTipoProfesor();
        Collections.sort(Aseador_order, new Comparator<Empleado>() {
            public int compare(Empleado E1, Empleado E2){
                return Integer.compare(E2.getMetaSemanal(), E1.getMetaSemanal());
            }
        });
        Collections.sort(Seguridad_order, new Comparator<Empleado>() {
            public int compare(Empleado E1, Empleado E2){
                return Integer.compare(E2.getMetaSemanal(), E1.getMetaSemanal());
            }
        });
        Collections.sort(Profesor_order, new Comparator<Empleado>() {
            public int compare(Empleado E1, Empleado E2){
                return Integer.compare(E2.getMetaSemanal(), E1.getMetaSemanal());
            }
        });
        
        Empleado.setTipoAseador(Aseador_order);
        Empleado.setTipoProfesor(Profesor_order);
        Empleado.setTipoSeguridad(Seguridad_order);

        
        //Administrar Trabajadores
        //Asignar horas y trabajos
        //Para Seguridad
        int cant_trabajadores_principiantes = 0;
        int base = 1;
        int totalFunciones = Funcion.getFuncionesCreadas().size();
        int totalTrabajadores_S = Empleado.getTipoSeguridad().size();
        int funcion_por_trabajador = totalFunciones/totalTrabajadores_S;
        ArrayList<Funcion> funcionesDisponibles = new ArrayList<>(Funcion.getFuncionesCreadas());
        //Se organiza la lista por fecha
        try{
            funcionesDisponibles.sort((f1, f2) ->
            f1.getHorario().get(0).compareTo(f2.getHorario().get(0))
            );
        }
        catch(Exception e){}


        //Verificar si las listas no estan vacias
        if(totalFunciones != 0 && totalTrabajadores_S != 0){
            for(Empleado Persona : Empleado.getTipoSeguridad()){
                if(Persona.getMetaSemanal() == base){
                    cant_trabajadores_principiantes += 1;
                }
            }
            //Asignacion de tareas si todos los trabajadores son principiantes
            if(cant_trabajadores_principiantes == Empleado.getTipoSeguridad().size()){
                //CASO NORMAL, SE ASIGNAN EN IGUAL CANTIDAD A CADA EMPLEADO
                int funcionesSinHorarios = 0;
                for(Empleado Persona : Empleado.getTipoSeguridad()){
                    int asignadas = 0; 
                    ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                    for(int i = 0; i < funcionesDisponibles.size(); i ++){
                        if(asignadas < funcion_por_trabajador){
                            Funcion Funciones = funcionesDisponibles.get(i);
                            //Asignacion del horario y del Trabajo
                            //Verifica que la funcion tenga un horario
                            if(!Funciones.getHorario().isEmpty()){
                                if(localTime.size() != 0){
                                    if(Funciones.getHorario().get(0).isAfter(localTime.get(localTime.size()-1).get(1))){
                                        localTime.add(Funciones.getHorario());
                                        asignadas = asignadas + 1;
                                        //CALCULAR DURACION DE LA FUNCION
                                        LocalDateTime inicio = Funciones.getHorario().get(0);
                                        LocalDateTime fin = Funciones.getHorario().get(1);
        
                                        double duracionFuncion = Duration.between(inicio, fin).toMinutes()/60.0; // Para obtener las horas
                                        Persona.getTrabajos().add(duracionFuncion);
                                        Funciones.setTrabajador(true); // Se le asigna el trabajador
                                        funcionesDisponibles.remove(i);
                                        i--;
                                    }
                                }
                                else{
                                    localTime.add(Funciones.getHorario());
                                    funcionesDisponibles.remove(i);
                                    asignadas = asignadas + 1;

                                    LocalDateTime inicio = Funciones.getHorario().get(0);
                                    LocalDateTime fin = Funciones.getHorario().get(1);
        
                                    double duracionFuncion = Duration.between(inicio, fin).toMinutes()/60.0; // Para obtener las horas
                                    Persona.getTrabajos().add(duracionFuncion);
                                    Funciones.setTrabajador(true); //Se le asigna el trabajador
                                    i--;
                                }
                            }
                            else{
                                funcionesSinHorarios += 1;
                                funcionesDisponibles.remove(i);
                                i--;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //Se organiza la lista para que no haya errores en caso de asignar las funciones restantes para que no haya solapamiento
                    Collections.sort(localTime, new Comparator<ArrayList<LocalDateTime>>(){
                        public int compare(ArrayList<LocalDateTime> horario1, ArrayList<LocalDateTime> horario2){
                            return horario1.get(0).compareTo(horario2.get(0));
                        }
                    });
                    Persona.setHorario(localTime);
                    Persona.setDisponible(false);
                }
                if(funcionesSinHorarios == 1){
                    customPrint("Hay 1 Funcion sin horarios", "red");
                }
                else if(funcionesSinHorarios > 1){
                    customPrint("Hay " + funcionesSinHorarios + " Funciones sin horarios", "red");
                }
                //EVALUACION DE SALAS SIN TRABAJADOR
                if(funcionesDisponibles.size() != 0 ){
                    System.out.println("funciones que quedaron libres");
                    for(Empleado Persona : Empleado.getTipoSeguridad()){
                        //Segunda iteracion
                        ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                        for(int i = 0; i < funcionesDisponibles.size(); i++){
                            Funcion Funciones = funcionesDisponibles.get(i);
                            boolean horarioValido = true;
                            LocalDateTime inicioNuevo = Funciones.getHorario().get(0);
                            LocalDateTime finNuevo = Funciones.getHorario().get(1);
                            //Verificar que no se solapa con otro horario
                            //Se itera sobre las sublistas de localTime y verificar con la siguiente
                            for(int j = 0; j < localTime.size(); j++){
                                ArrayList<LocalDateTime> horarioActual = localTime.get(j);
                                LocalDateTime finActual = horarioActual.get(1); //Fin del horario actual

                                //Verificar si hay un sublista despues
                                if(j + 1 < localTime.size()){
                                    ArrayList<LocalDateTime> horarioSiguiente = localTime.get(j+1);
                                    LocalDateTime inicioSiguiente = horarioSiguiente.get(0);

                                    //Verificar si el horario nuevo no se solapa
                                    if(!(inicioNuevo.isAfter(finActual) && finNuevo.isBefore(inicioSiguiente))){
                                        horarioValido = false;
                                        break;
                                    }
                                }
                                else{
                                    if(!(inicioNuevo.isAfter(finActual))){
                                        horarioValido = false;
                                        break;
                                    }
                                }
                            }
                            if(horarioValido){
                                localTime.add(Funciones.getHorario());
                                //CALCULAR DURACION DE LA FUNCION
                                LocalDateTime inicio = Funciones.getHorario().get(0);
                                LocalDateTime fin = Funciones.getHorario().get(1);
        
                                double duracionFuncion = Duration.between(inicio, fin).toMinutes()/60.0; // Para obtener las horas
                                Persona.getTrabajos().add(duracionFuncion);
                                Funciones.setTrabajador(true); // Se le asigna el trabajador
                                funcionesDisponibles.remove(i);
                                i--;
                                break;
                            }
                        }
                        Collections.sort(localTime, new Comparator<ArrayList<LocalDateTime>>(){
                            public int compare(ArrayList<LocalDateTime> horario1, ArrayList<LocalDateTime> horario2){
                                return horario1.get(0).compareTo(horario2.get(0));
                            }
                        });
                        Persona.setHorario(localTime);
                    }
                }
                String msgBase = "";
                for(Empleado Persona : Empleado.getTipoSeguridad()){
                    msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " cuidará: ", Persona.getHorario().size() + " funcion/es\n");
                }
                customPrint(msgBase);
            }
            else{
                ArrayList<Funcion> FuncionPorDuracion = Funcion.getFuncionesCreadas();
                Collections.sort(FuncionPorDuracion, new Comparator<Funcion>() {
                    public int compare(Funcion f1, Funcion f2){
                        double duracionF1 = Duration.between(f1.getHorario().get(0), f1.getHorario().get(1)).toMinutes()/60.0;
                        double duracionF2 = Duration.between(f2.getHorario().get(0), f2.getHorario().get(1)).toMinutes()/60.0;
                        return Double.compare(duracionF2, duracionF1);
                    }
                });
                funcionesDisponibles = FuncionPorDuracion;
                try{
                    funcionesDisponibles.sort((f1, f2) ->
                    f1.getHorario().get(0).compareTo(f2.getHorario().get(0))
                    );
                }
                catch(IndexOutOfBoundsException e){
                    System.out.println("no hay horarios para organizar");
                }
                //Evaluacion Normal, asignacion de trabajo equitativo
                for(Empleado Persona : Empleado.getTipoSeguridad()){
                    int asignadas = 0;
                    ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                    for(int i = 0; i < funcionesDisponibles.size(); i ++){
                        if(asignadas < funcion_por_trabajador){
                            Funcion Funciones = funcionesDisponibles.get(i);
                            //Asignacion del horario y del Trabajo
                            //Verifica que la funcion tenga un horario
                            if(!Funciones.getHorario().isEmpty()){
                                if(Persona.getHorario().size() != 0){
                                    if(Funciones.getHorario().get(0).isAfter(localTime.get(localTime.size()-1).get(1))){
                                        localTime.add(Funciones.getHorario());                                        
                                        asignadas = asignadas + 1;
                                        //CALCULAR DURACION DE LA FUNCION
                                        LocalDateTime inicio = Funciones.getHorario().get(0);
                                        LocalDateTime fin = Funciones.getHorario().get(1);
        
                                        double duracionFuncion = Duration.between(inicio, fin).toMinutes()/60.0; // Para obtener las horas
                                        Persona.getTrabajos().add(duracionFuncion);

                                        funcionesDisponibles.remove(i);
                                        i--;
                                    }
                                }
                                else{
                                    localTime.add(Funciones.getHorario());
                                    funcionesDisponibles.remove(i);
                                    asignadas = asignadas + 1;
                                    i--;
                                    
                                }
                            }
                            else{
                                funcionesDisponibles.remove(i);
                                i--;                        
                                customPrint("No hay horarios para aplicar", "red");
                            }
                        }
                        else{
                            break;
                        }
                    }
                    Collections.sort(localTime, new Comparator<ArrayList<LocalDateTime>>(){
                        public int compare(ArrayList<LocalDateTime> horario1, ArrayList<LocalDateTime> horario2){
                            return horario1.get(0).compareTo(horario2.get(0));
                        }
                    });
                    Persona.setHorario(localTime);
                    Persona.setDisponible(false);
                }
                //EVALUACION DE SALAS SIN TRABAJADOR
                if(funcionesDisponibles.size() != 0 ){
                    System.out.println("funciones que quedaron libres");
                    for(Empleado Persona : Empleado.getTipoSeguridad()){
                        //Segunda iteracion
                        ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                        for(int i = 0; i < funcionesDisponibles.size(); i++){
                            Funcion Funciones = funcionesDisponibles.get(i);
                            boolean horarioValido = true;
                            LocalDateTime inicioNuevo = Funciones.getHorario().get(0);
                            LocalDateTime finNuevo = Funciones.getHorario().get(1);
                            //Verificar que no se solapa con otro horario
                            //Se itera sobre las sublistas de localTime y verificar con la siguiente
                            for(int j = 0; j < localTime.size(); j++){
                                ArrayList<LocalDateTime> horarioActual = localTime.get(j);
                                LocalDateTime finActual = horarioActual.get(1); //Fin del horario actual

                                //Verificar si hay un sublista despues
                                if(j + 1 < localTime.size()){
                                    ArrayList<LocalDateTime> horarioSiguiente = localTime.get(j+1);
                                    LocalDateTime inicioSiguiente = horarioSiguiente.get(0);

                                    //Verificar si el horario nuevo no se solapa
                                    if(!(inicioNuevo.isAfter(finActual) && finNuevo.isBefore(inicioSiguiente))){
                                        horarioValido = false;
                                        break;
                                    }
                                }
                                else{
                                    if(!(inicioNuevo.isAfter(finActual))){
                                        horarioValido = false;
                                        break;
                                    }
                                }
                            }
                            if(horarioValido){
                                localTime.add(Funciones.getHorario());
                                //CALCULAR DURACION DE LA FUNCION
                                LocalDateTime inicio = Funciones.getHorario().get(0);
                                LocalDateTime fin = Funciones.getHorario().get(1);
        
                                double duracionFuncion = Duration.between(inicio, fin).toMinutes()/60.0; // Para obtener las horas
                                Persona.getTrabajos().add(duracionFuncion);
                                Funciones.setTrabajador(true); // Se le asigna el trabajador
                                funcionesDisponibles.remove(i);
                                i--;
                                break;
                            }
                        }
                        Collections.sort(localTime, new Comparator<ArrayList<LocalDateTime>>(){
                            public int compare(ArrayList<LocalDateTime> horario1, ArrayList<LocalDateTime> horario2){
                                return horario1.get(0).compareTo(horario2.get(0));
                            }
                        });
                        Persona.setHorario(localTime);
                    }
                }
                String msgBase = "";
                for(Empleado Persona : Empleado.getTipoSeguridad()){
                    msgBase = String.format("%-10s %10s", Persona.getNombre() + " cuidará: ", Persona.getHorario().size() + " funcion/es\n");
                }
                customPrint(msgBase);
            }
        }
        else{
            if(totalFunciones == 0){
                customPrint("No hay funciones para agregar");
            }
            else{
                customPrint("No hay trabajadores de Seguridad");
            }
        }
        //Revisa si todavia quedan funciones que no se asignaron trabajador
        //En este caso estas funciones no se pueden asignar por que existiria solapamiento
        //Por tanto imprime cuantas funciones quedaron sin asiganr
        if(funcionesDisponibles.size() != 0 ){
            customPrint("Existen " + funcionesDisponibles.size() + " funcion/es sin posibilidad de seguridad");
        }

        //Para Aseador
        cant_trabajadores_principiantes = 0;
        int totalSalas = Sala.getSalas().size();
        int totalTrabajadores_A = Empleado.getTipoAseador().size();
        int cant_a_limpiar = totalFunciones/totalTrabajadores_A;
        ArrayList<Funcion> funcionesLimpiadas = new ArrayList<>(Funcion.getFuncionesCreadas()); 
        if(totalSalas != 0 && totalTrabajadores_A != 0){
            for(Empleado Persona : Empleado.getTipoAseador()){
                if(Persona.getMetaSemanal() == base){
                    cant_trabajadores_principiantes += 1;
                }
            }
            //En caso de que todos sean principiantes
            if(cant_trabajadores_principiantes == totalTrabajadores_A){
                for(Empleado Persona : Empleado.getTipoAseador()){
                    //ASIGNACION EQUITATIVA DE TRABAJO
                    int asignadas = 0;
                    ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                    for(int i = 0; i  < funcionesLimpiadas.size(); i++){
                        if(asignadas < cant_a_limpiar){
                            Funcion Funciones = funcionesLimpiadas.get(i);
                            //Verificar si la funcion tiene horario
                            if(!Funciones.getHorario().isEmpty()){
                                if(localTime.size() != 0){
                                    boolean horarioValido = true;
                                    LocalDateTime inicioNuevo = Funciones.getHorario().get(1);
                                    LocalDateTime finNuevo = inicioNuevo.plusMinutes(15);
                                    for(int j = 0; j < localTime.size(); j++){
                                        //Se obtiene el horario en el q se itera
                                        ArrayList<LocalDateTime> horarioActual = localTime.get(j);
                                        //Se accede al ultimo indice de ese horario q es el fin 
                                        LocalDateTime finActual = horarioActual.get(1);

                                        //Verificar si hay un sublista despues
                                        if(j + 1 < localTime.size()){
                                            ArrayList<LocalDateTime> horarioSiguiente = localTime.get(j+1);
                                            LocalDateTime inicioSiguiente = horarioSiguiente.get(0);

                                            //Verificar si el horario nuevo no se solapa
                                            if(!(inicioNuevo.isAfter(finActual) && finNuevo.isBefore(inicioSiguiente))){
                                                horarioValido = false;
                                                break;
                                            }
                                        }
                                        else{
                                            ArrayList<LocalDateTime> horarioSiguiente = funcionesLimpiadas.get(i+1).getHorario();
                                            LocalDateTime inicioSiguiente = horarioSiguiente.get(0);
                                            //Verificar el que el fin nuevo sea antes de la siguiente funcion y el inicio sea despues del horario ya existente
                                           if(!(finNuevo.isBefore(inicioSiguiente) && inicioNuevo.isAfter(finActual))){
                                            //Como no se cumple se tiene que pasar a revisar la siguiente funcion
                                                horarioValido = false;
                                                break;
                                           }
                                        }
                                    }
                                    if(horarioValido){

                                    }
                                }
                                else{
                                    //Se asigna la hora de fin de la funcion como el inicio del Empleado
                                    asignadas = asignadas + 1;
                                    ArrayList<LocalDateTime> sublista = new ArrayList<>();
                                    LocalDateTime finFuncion = Funciones.getHorario().get(1);
                                    //Se suma 15 minutos a la hora de fin de la funcion
                                    LocalDateTime finEmpleado = finFuncion.plusMinutes(15);
                                    //Se almacenan en la sublista
                                    sublista.add(finFuncion);
                                    sublista.add(finEmpleado);
                                    // se agregan al localTime
                                    localTime.add(sublista);
                                    funcionesLimpiadas.remove(i);
                                    Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                    Funciones.getSala().setAseado(true);
                                    funcionesLimpiadas.remove(i);
                                }
                            }
                            else{
                                funcionesLimpiadas.remove(i);
                                customPrint("No hay horario para aplicar");
                            }
                        }
                        else{
                            break;
                        }
                    }
                    Collections.sort(localTime, new Comparator<ArrayList<LocalDateTime>>(){
                        public int compare(ArrayList<LocalDateTime> horario1, ArrayList<LocalDateTime> horario2){
                            return horario1.get(0).compareTo(horario2.get(0));
                        }
                    });
                    Persona.setHorario(localTime);
                    //Asignacion De las libres
                }
            }
            else{

            }

        }
        else{
            if(totalSalas == 0){
                customPrint("No hay salas Existentes");
            }
            else{
                customPrint("No hay trabajadores de Aseador");
            }
        }


        customPrint("trabajos Asignados...", "green");
        customPrint("Desplegando Trabajadores");
        
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            customPrint("La pausa fue interrumpida.");    
        }

        customPrint("Verificando los trabajos...");

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            customPrint("La pausa fue interrumpida.");    
        }


        //Verificacion del trabajo
        //Seguridad
        cant_trabajadores_principiantes = 0;
        for(Empleado Persona : Empleado.getTipoSeguridad()){
            if(Persona.getMetaSemanal() == base){
                cant_trabajadores_principiantes += 1;
            }
        }
        if(cant_trabajadores_principiantes == Empleado.getTipoSeguridad().size()){;
            for(Empleado Persona : Empleado.getTipoSeguridad()){
                for(double Hora : Persona.getTrabajos()){
                    Random random = new Random();
                    double randomValue = random.nextDouble();
                    if(randomValue > 0.5){
                            Persona.getTrabajoCorrecto().add(true);
                            Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Hora);
                            Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                        }
                    else{Persona.getTrabajoCorrecto().add(false);}
                }
                Persona.setDisponible(true);
            }
        }
        else{
            for(Empleado Persona : Empleado.getTipoSeguridad()){
                for(double Hora : Persona.getTrabajos()){
                    Random random = new Random();
                    double randomValue = random.nextDouble();
                    if(Persona.getMetaSemanal() > 20){
                        if(Hora >= 4){
                            if(randomValue > 0.65){
                                Persona.getTrabajoCorrecto().add(true);
                                Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Hora);
                                Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                            }
                            else {Persona.getTrabajoCorrecto().add(false);}
                        }
                        else{
                            if(randomValue > 0.40){
                                Persona.getTrabajoCorrecto().add(true);
                                Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Hora);
                                Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                            }
                            else{Persona.getTrabajoCorrecto().add(false);}
                        }
                    }
                    else{
                        if(randomValue > 0.5){
                            Persona.getTrabajoCorrecto().add(true);
                            Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Hora);
                            Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                        }
                        else{Persona.getTrabajoCorrecto().add(false);}
                    }
                }
            }
        }

        //Aseador
        cant_trabajadores_principiantes = 0;
        for(Empleado Persona : Empleado.getTipoAseador()){
            if(Persona.getMetaSemanal() == base){
                cant_trabajadores_principiantes += 1;
            }
        }
        if(cant_trabajadores_principiantes == Empleado.getTipoAseador().size()){
            for(Empleado Persona : Empleado.getTipoAseador()){
                for(double Metros : Persona.getTrabajos()){
                    Random random = new Random();
                    double randomValue = random.nextDouble();
                    if(randomValue > 0.5){
                        Persona.getTrabajoCorrecto().add(true);
                        Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Metros);
                        Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                    }
                    else{Persona.getTrabajoCorrecto().add(false);}
                }
            }
        }
        else{
            for(Empleado Persona : Empleado.getTipoAseador()){
                for(double Metros : Persona.getTrabajos()){
                    Random random = new Random();
                    double randomValue = random.nextDouble();
                    if(Persona.getMetaSemanal() > 20){
                        if(Metros > 650){
                            if(randomValue > 0.65){
                                Persona.getTrabajoCorrecto().add(true);
                                Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Metros);
                                Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                            }
                            else {Persona.getTrabajoCorrecto().add(false);}
                        }
                        else{
                            if(randomValue > 0.40){
                                Persona.getTrabajoCorrecto().add(true);
                                Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Metros);
                                Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                            }
                            else{Persona.getTrabajoCorrecto().add(false);}
                        }
                    }
                    else{
                        if(randomValue > 0.5){
                            Persona.getTrabajoCorrecto().add(true);
                            Persona.setTrabajoRealizado(Persona.getTrabajoRealizado() + Metros);
                            Persona.setPuntosPositivos(Persona.getPuntosPositivos() + 1);
                        }
                        else{
                            Persona.getTrabajoCorrecto().add(false);
                        }
                    }
                }
            }
        }
        
        

        //Hora inicio - Hora fin
        //Asignar Horario Trabajador
        //Automatico: Verificar metros de la sala y se asigan de acuerdo a la dificultad, verificar por meta
        //Asigna el trabajo de una vez y se verifica
        //Asignar Horario Seguridad
        
        //Asignar Horario Profesor LO HACE OSCAR


        
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            customPrint("La pausa fue interrumpida.");
            
        }
        
        //Pagar nomina a empleados:
        Saldo = String.format("$%,.2f", tesoreria.getCuenta().getSaldo());
        customPrint("El saldo de tesoreria es: " + Saldo);
        byte[] option = {1,2};
        byte respuesta = ask("¿Desea realizar los pagos \n1. Si\n2. No", option, "green");
        switch (respuesta) {
            case 1:
                tesoreria.setTotal(tesoreria.getTotal() + tesoreria.getDineroEnCaja());
                double fondos = tesoreria.getCuenta().getSaldo();
                double totalSaldos = 0;
                //Verificacion de fondos:
                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                    totalSaldos = totalSaldos + Persona.calcularSueldo();
                }
                //Realizar pago
                if(totalSaldos > fondos){
                    ArrayList<Empleado>  Cuentas_Pagadas = new ArrayList<>();
                    double cantPagada = 0;
                    customPrint("Upps... No se puede realizar los pagos adecuadamente", "Red");
                    customPrint("Realizando pagos de manera equitativa...");
                    for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                        boolean transaccion = tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.getDeuda() + Persona.calcularSueldo()) *0.5);  //Establecer cuanto se le debe a la persona
                        if(transaccion != true){
                            System.out.println("No se le puede pagar a " + Persona.getNombre());
                            Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo());
                            System.out.println("nueva deuda: " + Persona.getDeuda() );
                        }
                        else{
                            cantPagada = cantPagada + ((Persona.calcularSueldo() + Persona.getDeuda())*0.5);
                            Persona.setDeuda((Persona.getDeuda() + (Persona.calcularSueldo() + Persona.getDeuda())* 0.5));
                            Cuentas_Pagadas.add(Persona); 
                        }
                    }
                    customPrint("Pago existoso", true, "green");
                    String msg = "Se pago un total de " + cantPagada;
                    customPrint(msg);
                    customPrint("Se realizo el pago a " + Cuentas_Pagadas.size() + " cuentas en total");
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
                        //Realizacion Pagos
                        if(totalSaldos > fondos){
                            //Verificacion
                            totalSaldos = 0;
                            customPrint("Ups... No se pueden aplicar las bonificaciones personales");
                            customPrint("Realizando Pagos");
                            for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                cantPagada = cantPagada + (Persona.calcularSueldo() + Persona.getDeuda());
                                totalSaldos = totalSaldos + Persona.calcularSueldo();
                            }
                            //Pago solo sueldo base
                            if(cantPagada > fondos){
                                System.out.println("Sueldo Base");
                                customPrint("No se pudo realizar los pagos junto a la deuda");
                                customPrint("Realizando pago del Sueldo Base");
                                tesoreria.pagarSueldoBase(null, cantPagada);
                                customPrint("Pago existoso", true, "green");
                                String msg = "Se pago un total de " + totalSaldos;
                                customPrint(msg);
                                customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                                customPrint("Saldo disponible " + String.format("$%,.2f", tesoreria.getCuenta().getSaldo()));
                                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                    if(Persona.verificacionMeta() == true){
                                        Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo()*0.15); //Se añade la bonificacion a la deuda solo a aquellas que la cumplieron
                                    }
                                }
                            }
                            else{
                            //Pago Sueldo base + Deuda
                                for(Empleado Persona: Empleado.getEmpleadosPorRendimiento()){
                                    tesoreria.getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda() + Persona.calcularSueldo());
                                }
                                customPrint("Pago existoso", true, "green");
                                String msg = "Se pago un total de " + cantPagada;
                                customPrint(msg);
                                customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                                customPrint("Saldo disponible " + String.format("$%,.2f", tesoreria.getCuenta().getSaldo()));
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
                            customPrint("Saldo disponible " + String.format("$%,.2f", tesoreria.getCuenta().getSaldo()));
                        }
                    }
                    //Pago Bonis Tesorerias + deuda
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
                            totalSaldos = 0;
                            //Verificacion Metas Personales
                            for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                if(Persona.verificacionMeta() != true){
                                    Persona.setMetaSemanal(Persona.getMetaSemanal());
                                    totalSaldos = totalSaldos + (Persona.calcularSueldo() + Persona.getDeuda());
                                }
                                else{
                                    Persona.setMetaSemanal(Persona.getMetaSemanal()); 
                                    totalSaldos = totalSaldos + ((Persona.calcularSueldo() * 1.15) + Persona.getDeuda());
                                }
                            }
                            //Realizacion Pagos
                            if(totalSaldos > fondos){
                                //Verificacion
                                totalSaldos = 0;
                                customPrint("Ups... No se pueden aplicar las bonificaciones personales");
                                customPrint("Realizando Pagos");
                                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                    cantPagada = cantPagada + (Persona.calcularSueldo() + Persona.getDeuda());
                                    totalSaldos = totalSaldos + Persona.calcularSueldo();
                                }
                                //Pago solo sueldo base
                                if(cantPagada > fondos){
                                    customPrint("No se pudo realizar los pagos junto a la deuda");
                                    customPrint("Realizando pago del Sueldo Base");
                                    tesoreria.pagarSueldoBase(null, cantPagada);
                                    customPrint("Pago existoso", true, "green");
                                    String msg = "Se pago un total de " + totalSaldos;
                                    customPrint(msg);
                                    customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                                    customPrint("Saldo disponible " + String.format("$%,.2f", tesoreria.getCuenta().getSaldo()));
                                    for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                        if(Persona.verificacionMeta() == true){
                                            Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo()*0.15); //Se añade la bonificacion a la deuda solo a aquellas que la cumplieron
                                        }
                                    }
                                }
                                else{
                                //Pago Sueldo base + Deuda
                                    for(Empleado Persona: Empleado.getEmpleadosPorRendimiento()){
                                        tesoreria.getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda() + Persona.calcularSueldo());
                                    }
                                    customPrint("Pago existoso", true, "green");
                                    String msg = "Se pago un total de " + cantPagada;
                                    customPrint(msg);
                                    customPrint("Se realizo el pago a " + Empleado.getEmpleadosPorRendimiento().size() + " cuentas en total");
                                    customPrint("Saldo disponible " + String.format("$%,.2f", tesoreria.getCuenta().getSaldo()));
                                }
                            }
                            else{
                                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                    if(Persona.verificacionMeta() == true){
                                        tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.45) + Persona.getDeuda());
                                    }
                                    else{
                                        tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.3) + Persona.getDeuda());
                                    }
                                }
                            }
                        }
                        else{
                            for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                                if(Persona.verificacionMeta() == true){
                                    tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.45) + Persona.getDeuda());
                                }
                                else{
                                    tesoreria.getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.3) + Persona.getDeuda());
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                break;
        }
    
        //Planes de mejora
        //Despedir si meta es negatica
        ArrayList<Empleado> NuevaLista = Empleado.getEmpleadosPorRendimiento();
        ArrayList<Empleado> Despedidos = new ArrayList<>();
        String msgBase = "";
        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
            if(Persona.getMetaSemanal() < 0){
                NuevaLista.remove(Persona);
                Despedidos.add(Persona);
                msgBase = msgBase + Persona.getNombre() + "\n";
            }
            continue;
        }
        Empleado.setEmpleadosPorRendimiento(NuevaLista);
        if(!Despedidos.isEmpty()){
            customPrint("Personas Despedidas: \n" + msgBase);
        }
        

        //Imprimir Ranking
        ArrayList<Empleado> Ranking = Empleado.getEmpleadosPorRendimiento();
        Collections.sort(Ranking, new Comparator<Empleado>() {
            public int compare(Empleado E1, Empleado E2){
                return Integer.compare(E2.getMetaSemanal(), E1.getMetaSemanal());
            }
        });
        Empleado.setEmpleadosPorRendimiento(Ranking);
        msgBase = "\n";
        int posicion = 1;
        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
            if(msgBase != "\n"){
                msgBase = msgBase + posicion + ". " + Persona.getNombre() + "\n"; 
                posicion = posicion + 1;
            }
            else{
                msgBase = posicion + ". " + Persona.getNombre() + msgBase;
                posicion = posicion + 1;
            }
        }
        customPrint("Ranking de Empleados \n" + msgBase, true, "green");
    }

    //FUNCIONALIDAD 4
    public static void gestionClases() throws InterruptedException {


        //PRIMERA INTERACCIÓN

        byte[] cuatro = {1,2,3,4};
        byte[] dos = {1,2};

        customPrint("Bienvenido a la gestión de clases.", "blue");
        Thread.sleep(2000);

        boolean continuar = true;

        while (continuar) {

        int queDeseaHacer = ask("¿Que desea hacer?\n" + "1. Gestionar artistas\n" + "2. Ver obras en estado crítico del teatro\n"
         + "3. Programar una clase\n" + "4. Salir", cuatro, "blue");

        
        switch (queDeseaHacer) { //GESTIONAR ARTISTAS
            case 1:

            if (Artista.getArtistas().size() != 0) {
                customPrint("Estos son los artistas que ya existen en nuestra base de datos");
                StringBuilder artistas = new StringBuilder();
                for (Artista artista : Artista.getArtistas()) {
                    if (Actor.getActors().contains(artista)) {
                        String lineaArtista = "- Actor " + artista.getNombre() + " con ID " + artista.getId(); 
                        if (lineaArtista.length() > LARGO_LINEAS) {
                            lineaArtista = lineaArtista.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                        }
                        artistas.append(lineaArtista).append("\n");
                    }
                    if (Director.getDirectors().contains(artista)) {
                        String lineaArtista = "- Director " + artista.getNombre() + " con ID " + artista.getId(); 
                        if (lineaArtista.length() > LARGO_LINEAS) {
                            lineaArtista = lineaArtista.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                        }
                        artistas.append(lineaArtista).append("\n");
                    }
                }
                customPrint(artistas.toString());
            }
            Thread.sleep(2000);
            Thread.sleep(1500);
            long idArtista = longAsk("Ingrese el ID del artista del cual desea conocer su información:\n" + "\n" + "(Puede escribir el ID de un actor o Director que no exista para inicializarlo)");
            Artista artista = Artista.buscarArtistaPorId(idArtista);
        
            if (artista == null) {
                customPrint("Artista no encontrado.", "red");
                Thread.sleep(2000);
                byte crearArtista = ask("¿Desea crear un nuevo Artista con este ID?\n" + "1. Sí\n" + "2. No", new byte[]{1, 2}, "");
            
                switch (crearArtista) {
                    case 1:
                        String nombreArtista = ask("Ingrese el nombre del nuevo artista:");
                        Thread.sleep(1000);
                        String tipoArtista = ask("Ingrese el tipo de artista (director/actor)");
                        Thread.sleep(1000);
            
                        if (tipoArtista.equals("director")) {
                            // Crear un nuevo director
                            new Director(nombreArtista, idArtista);
                            customPrint("Nuevo director agregado: " + nombreArtista + " con ID " + idArtista, "green");
                            Thread.sleep(2000);
                            customPrint("Recuerde que los directores no reciben clases.", "yellow");
                        } else if (tipoArtista.equals("actor")) {
                            // Crear un nuevo actor
                            Actor nuevoActor = new Actor(nombreArtista, idArtista);
                            customPrint("Nuevo actor agregado: " + nombreArtista + " con ID " + idArtista, "green");
                            artista = nuevoActor; // Asignar al artista actual
                            Thread.sleep(2000);
                        } else {
                            customPrint("Tipo de artista no válido. Debe ser 'director' o 'actor'.", "red");
                            return; // Salir si el tipo es inválido
                        }
                        break;
            
                    case 2:
                        break; // Salir si no desea crear un nuevo artista
                }
            } else {
                customPrint("El actor ya existe en nuestra base de datos", "green");
                Thread.sleep(2000);
            };

            if (artista != null) {
                if (((Actor)artista).sigueIgual()) {
                    customPrint("El actor no tiene calificaciones. Inicializando calificaciones...");  
                    Thread.sleep(2000);
        
                    // Llamar al método casting() para inicializar calificaciones de calificadores
                    boolean resultado = Empleado.casting(artista, Empleado.getTipoProfesor());
                    if (resultado == false){
                        customPrint("No hay profesores disponibles para inicializar las calificaciones del actor", "red");
                    }
                    else if(resultado == true) {
                        
                        // Seleccionar un profesor aleatorio
                        Profesor profesorAsignado = (Profesor) Empleado.getTipoProfesor().get((int) (Math.random() * Empleado.getTipoProfesor().size()));
                        
                        // Mostrar quién inicializó las calificaciones
                        customPrint("El/la profesor/a " + profesorAsignado.getNombre() + " es el/la responsable de inicializar las calificaciones\n" + "del actor " + artista.getNombre() + ".");
                    }
                    Thread.sleep(3000);
                }
        
                // Inicializar calificaciones del público (simuladas aleatoriamente)

                if (artista.getCalificacionesPublico().size()==0){
                    artista.inicializarCalificacionesPublico(artista);
                Thread.sleep(2000);
                }
                
                // Mostrar las calificaciones del artista, sea o no sea nuevo
                customPrint("Estas son las calificaciones del artista: " + artista.getNombre());
                Thread.sleep(2000);

                StringBuilder cal = new StringBuilder();

                if (artista.getCalificaciones() != null) {
                    String showCalificaciones = "Calificaciones de calificadores: " + ((Actor)artista).getCalificacionesAptitudes(); 
                    if (showCalificaciones.length() > LARGO_LINEAS) {
                        showCalificaciones = showCalificaciones.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                    }
                    cal.append(showCalificaciones).append("\n");
                }
                String showCalificaciones = "Calificaciones del público: " + artista.getCalificacionesPublico(); 
                    if (showCalificaciones.length() > LARGO_LINEAS) {
                        showCalificaciones = showCalificaciones.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                    }
                    cal.append(showCalificaciones).append("\n");

                customPrint(cal.toString());
                Thread.sleep(4500);

                byte areas = ask("¿Desea ver las áreas que puede mejorar el artista según sus calificiaciones?\n" + "1. Sí\n" + "2. No\n", dos, "blue");
                switch (areas) {
                    case 1:
                        
                    if (artista instanceof Actor) {
                        Actor actor = (Actor) artista;
                        Thread.sleep(1000);
                        // Mostrar áreas de mejora recomendadas
                        List<Aptitud> areasDeMejora = actor.obtenerAreasDeMejora();
                        customPrint("Áreas recomendadas para mejorar:", "yellow");
                        Thread.sleep(2000);
                        StringBuilder areasMejora = new StringBuilder();
            
                        for (int i = 0; i < Math.min(3, areasDeMejora.size()); i++) {
                            Aptitud aptitud = areasDeMejora.get(i);
                            double calificacion = actor.getCalificacionPorAptitud(aptitud);
            
                            // Formato para la columna: "- {Aptitud} (Calificación: {calificación})"
                            String linea = "- " + aptitud + " (Calificación: " + String.format("%.1f", calificacion) + ")";
            
                            // Verificar si agregar esta línea excede el límite de caracteres; si no, agregar nueva línea
                            if (linea.length() > LARGO_LINEAS) {
                                linea = linea.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                            }
            
                            areasMejora.append(linea).append("\n"); // Agregar la línea con salto
                        }
            
                        // Imprimir usando customPrint con el formato final en columnas
                        customPrint(areasMejora.toString(), "yellow");

                        Thread.sleep(1500);

                        // Preguntar si quiere seguir la recomendación
                        byte respuesta = ask("¿Desea programar una clase basada en las áreas recomendadas?\n1. Sí\n2. No", dos, "");
                        Aptitud areaSeleccionada = null;
                    
                        if (respuesta == 1) {
                            // Seleccionar el área de mejora más baja recomendada
                            areaSeleccionada = areasDeMejora.get(0);

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

                            customPrint("Se seleccionó, automáticamente, el área '" + areaSeleccionada + "' con nivel de clase: " + nivelClase);
                            Thread.sleep(1000);

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

                        }
                    }
               
                break;
            
                    case 2:

                        break;
                }
            }
        
            break;
            
            
            
        
            case 2: //OBRAS CRÍTICAS
            
            ArrayList<Obra> obrasCritics = Obra.mostrarObrasCriticas();
            // Mostrar todas las obras críticas
            if (obrasCritics.isEmpty()) {
                customPrint("No hay obras en estado crítico en el teatro.", "yellow");
            } else {
                customPrint("Obras en estado crítico del teatro:", "red");
                Thread.sleep(3000);
                for (Obra obra : obrasCritics) {
                    customPrint("- '" + obra.getNombre() + "' (Promedio de calificaciones: " + obra.promedioCalificacion() + ")");
        
                    // Revisar aspectos críticos y las calificaciones de los actores
                    for (Aptitud aspecto : obra.getPapeles()) { // Obtenemos cada aptitud crítica de la obra
                        boolean encontrado = false;
                        for (Actor actor : obra.getReparto()) { // Revisamos cada actor en el reparto
                            double calificacion = actor.getCalificacionPorAptitud(aspecto);
                            if (calificacion != -1 && calificacion < 3.0) { // Si la calificación es baja
                                customPrint("El aspecto '" + aspecto + "' tiene una calificación baja (" + calificacion + ").", "red");
                                Thread.sleep(1500);
                                customPrint("Notificando al actor: " + actor.getNombre());
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            customPrint("No hay actores con calificaciones bajas en el aspecto '" + aspecto + "'.", "yellow");
                        }
                    }
                    Thread.sleep(1500);
                }
            };
        

            case 3:
                break;

            case 4:
            // Salir del programa
            customPrint("Saliendo de la gestión de clases. ¡Hasta pronto!", "blue");
            continuar = false; 
            break;
        }
    }

        
        
    /* 
        //SEGUNDA INTERACCION


        // Si el artista no es un actor, finalizar el flujo
        

            
            } else {
                byte respuesta1 = ask("¿Desea programar otra clase?\n1. Sí\n2. No", dos, "");
                if (respuesta1 == 1){
                // Permitir al usuario elegir cualquier aptitud
                StringBuilder areas2 = new StringBuilder();
                    customPrint("Seleccione un área para programar una clase:");
                    for (int i = 0; i < actor.getAptitudes().size(); i++) {
                        Aptitud aptitud = actor.getAptitudes().get(i);

                        String linea2 = (i+1) + "." + aptitud;

                        // Verificar si agregar esta línea excede el límite de caracteres; si no, agregar nueva línea
                        if (linea2.length() > LARGO_LINEAS) {
                            linea2 = linea2.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                        }

                        areas2.append(linea2).append("\n"); // Agregar la línea con salto
                    }
                    customPrint(areas2.toString());

                    byte[] opcionesAptitudes = new byte[actor.getAptitudes().size()];
                    for (byte i = 0; i < opcionesAptitudes.length; i++) {
                        opcionesAptitudes[i] = (byte) (i + 1);
                    }
                    
                    byte opcion = ask("Ingrese el número del área deseada", opcionesAptitudes, "");
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
            Thread.sleep(1000);

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


            //TERCERA INTERACCIÓN


            // Cálculo del costo de matrícula
            double costoClase = 0;
            switch (nivelClase) {
                case "Introducción": costoClase = 100; break;
                case "Profundización": costoClase = 150; break;
                case "Perfeccionamiento": costoClase = 180; break;
            }

            customPrint("El costo de la clase es: $" + costoClase, "blue");

            // Procesar el pago desde la cuenta del artista hacia la tesorería
            boolean si_no = actor.getCuenta().transferencia(tesoreria.getCuenta(), costoClase);
            if (si_no == true) {

                // Asignación de calificador para la próxima función
                Profesor calificador = null;
                for (Empleado empleado : Empleado.getTipoProfesor()) {
                    if (empleado instanceof Profesor) {
                        Profesor profesor = (Profesor) empleado;

                        // Simular disponibilidad aleatoria
                        boolean disponible = Math.random() > 0.5;
                        if (profesor.tieneEspecializacion(areaSeleccionada) && disponible) {
                            calificador = profesor;
                            break;
                        }
                    }
                }

                if (calificador != null) {
                    customPrint("Profesor calificador asignado: " + calificador.getNombre(), "green");
                    // Evaluación y retroalimentación
                    double calificacion = Math.random() * 5; // Generar calificación aleatoria
                    customPrint("El profesor calificó el desempeño del actor con un: " + calificacion, "yellow");

                    if (calificacion == 5) {
                        // Reembolso del costo de la clase al artista
                        boolean reembolso = tesoreria.getCuenta().transferencia(actor.getCuenta(), costoClase);
                        if (reembolso) {
                            customPrint("¡Felicitaciones! La calificación perfecta de 5 ha activado un reembolso. Se han reembolsado $" 
                                + costoClase + " a la cuenta del artista.", "green");
                        } else {
                            customPrint("Error: No se pudo procesar el reembolso. Por favor, contacte al administrador.", "red");
                        }
                    }

                    // Verificar si hubo mejora
                    if (!actor.huboMejora(areaSeleccionada)) {
                        customPrint("No hubo mejora en el desempeño. Se programará una nueva clase en el área deficiente...", "red");

                        // Reutilizamos la lógica para programar otra clase

                        LocalDateTime nuevoInicio = solicitarHorario("inicio");
                        LocalDateTime nuevoFin = solicitarHorario("fin");

                        // Validar que el horario de fin sea posterior al de inicio
                        while (nuevoFin.isBefore(nuevoInicio)) {
                            customPrint("El horario de fin debe ser posterior al horario de inicio. Intente nuevamente.", "red");
                            nuevoInicio = solicitarHorario("inicio");
                            nuevoFin = solicitarHorario("fin");
                        }

                        // Buscar una sala disponible para el nuevo horario
                        Sala nuevaSala = null;
                        for (Sala sala : Sala.getSalas()) {
                            if (sala.getAseado() && sala.isDisponible(nuevoInicio, nuevoFin)) {
                                nuevaSala = sala;
                                break;
                            }
                        }

                        if (nuevaSala == null) {
                            customPrint("No hay salas disponibles en el nuevo horario deseado o no están limpias.", "red");
                            return;
                        }

                        customPrint("Sala asignada para la nueva clase: " + nuevaSala.getNumeroSala());
                        nuevaSala.anadirHorario(new ArrayList<>(List.of(nuevoInicio, nuevoFin)));

                        // Reasignar profesor capacitado
                        Profesor nuevoProfesor = null;
                        for (Empleado empleado : Empleado.getTipoProfesor()) {
                            if (empleado instanceof Profesor) {
                                Profesor profesor = (Profesor) empleado;

                                // Simular disponibilidad aleatoria
                                boolean disponible = Math.random() > 0.5;
                                if (profesor.tieneEspecializacion(areaSeleccionada) && disponible) {
                                    nuevoProfesor = profesor;
                                    break;
                                }
                            }
                        }

                        if (nuevoProfesor == null) {
                            customPrint("No hay profesores disponibles para la nueva clase en el área seleccionada.", "red");
                            return;
                        }

                        customPrint("Nuevo profesor asignado: " + nuevoProfesor.getNombre(), "green");
                        customPrint("Clase reprogramada exitosamente con el profesor " + nuevoProfesor.getNombre() 
                            + " en la sala " + nuevaSala.getNumeroSala() + ".", "green");
                    }
                } else {
                    customPrint("No hay profesores disponibles para calificar la próxima función.", "red");
                }
                // Verificar si el actor debe bajar de nivel en el área seleccionada
                if (actor.noHaMejoradoEnCuatroIntentos(areaSeleccionada)) {
                    customPrint("El actor no ha mostrado mejora en el área '" + areaSeleccionada 
                        + "' después de cuatro clases. Reduciendo el nivel en esta área...", "red");

                    double nuevaCalificacion = Math.max(0, actor.getCalificacionPorAptitud(areaSeleccionada) - 1);
                    actor.registrarCalificacion(areaSeleccionada, nuevaCalificacion); // Registrar la nueva calificación en el historial
                    customPrint("Nuevo nivel del área '" + areaSeleccionada + "': " + nuevaCalificacion, "yellow");
                }

                // Asignar puntos al profesor en función del nivel de la clase
                int puntosPositivos = 0;

                switch (nivelClase) {
                    case "Introducción":
                        puntosPositivos = 1;
                        break;
                    case "Profundización":
                        puntosPositivos = 2;
                        break;
                    case "Perfeccionamiento":
                        puntosPositivos = 3;
                        break;
                }

                profesorAsignado.agregarPuntos(puntosPositivos);
                customPrint("El profesor " + profesorAsignado.getNombre() + " ha recibido " 
                + puntosPositivos + " puntos positivos. Total acumulado: " + profesorAsignado.getPuntosPositivos() + ".", "blue");
            }
            else {
                customPrint("El actor cuenta con saldo insuficiente para pagar la clases");
            }
        } else {
            customPrint("Solo los actores pueden recibir clases.", "red");
        }*/
    }// Fin del método
}