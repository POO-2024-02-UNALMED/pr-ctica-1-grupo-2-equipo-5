package uiMain;

import java.util.List;
import java.util.Scanner;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import baseDatos.Teatro;

import java.util.ArrayList;
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

import java.io.File;

import gestorAplicacion.gestionVentas.Cliente;
import gestorAplicacion.gestionClases.Profesor;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionVentas.Funcion;
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



public class Main {

    public static Scanner in = new Scanner(System.in);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("es"));


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
            LocalTime.parse(time);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static LocalTime timeAsk(String question){
        customPrint(question);
        String input = in.nextLine();

        if (input.equals("0")){
            return null;
        }

        while (!canBeTime(input)){
            customPrint("La respuesta introducida no está en el formato 24 horas (HH:MM). Intente de nuevo:", true, "red");
            customPrint(question);
            input = in.nextLine();     

            if (input == "0"){
                return null;
            }
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

    public static int LARGO_LINEAS = 120;
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
        byte[] seven = {0, 1, 2, 3, 4, 5, 6, 7};

        if(date){
            
            preguntaCompleta += "\n";

            for (int i = 0; i < getWeek().size(); i++){
                preguntaCompleta += (i+1) + ". " + getWeek().get(i).format(formatter) + "\n";

            }

            byte dia = ask(preguntaCompleta, seven, "");
            if (dia == 0){
                customPrint("Saliendo...", "red");
                return null;
            }
            diaEscogido = getWeek().get( dia-1 );

        } else{

            customPrint(preguntaCompleta);
        }

        while(true){
            inicioHorario = timeAsk("Introduzca horario de inicio (Responda en formato HH:MM).");

            if (inicioHorario == null){
                customPrint("Saliendo...", "red");
                return null;
            }

            finHorario = timeAsk("Introduzca horario de fin (Responda en formato HH:MM).");

            if (finHorario == null){
                customPrint("saliendo...", "red");
                return null;
            }


            if (inicioHorario.isBefore(horaMin) || finHorario.isAfter(horaMax) || finHorario.isBefore(inicioHorario) || inicioHorario.isAfter(finHorario)){
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
//    Sala sala1 = new Sala(1, 100, 24);

    public static void main(String args[]){  

        byte task = -1;

        // DESERIALIZACIÓN -------------------------------------------------------//
        String filename = "teatro.txt";
        String path = "src" + File.separator + "baseDatos" + File.separator + "temp" + File.separator + filename;
        Deserializador.loadState(path);
        // -----------------------------------------------------------------------//

        //si no existen salas, hay que crearlas
        if (Teatro.getInstancia().getSalas().size() == 0 ){
        Sala sala1 = new Sala(1, 100, 24);
        Sala sala2 = new Sala(2, 200, 48);
        Sala sala3 = new Sala(3, 50, 16);
        Sala sala4 = new Sala(4, 150, 24);
        }
        
        while (task != 6){

            customPrint("Teatro Escuela Carlos Mayolo", true);

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
                Serializador.saveState(path);
                break;
            }
        }
    }

    public static void gestionVentas(){
        Tiquete tiquete = new Tiquete();

        InterfaceTipos asiento = Asiento.BASICO;
        InterfaceTipos suscription = Suscripcion.Basica;
        String confirmacion = "";
        float dineroTesoreria=0;
        byte [] opciones_2 = {1,2,0};
        Cliente cliente= null;
        


        byte [] res= {1,2,0};
        int p_ =ask("Ingrese la opcion correspondiente\n"+
        "Eres cliente nuevo? \n"+ 
        "1. SI\n"+
        "2. NO\n"+
        "0. MENU PRINCIPAL", res, "");
            
        
        

        boolean salir = false;
    
        while (!salir) {
        switch (p_) {
            case 2:
                customPrint("Ingresa tu id :");
                long code = in.nextLong();
                in.nextLine();                
                

                if (Cliente.verificar(code)) {
                    customPrint("Iniciando sesion...");
                    cliente=Cliente.asignar(code);
                    
                wait(2000);
                customPrint("Sesion Iniciada","green");
        
                salir = true;
                break;
                    

                }else {
                    byte [] opcion = {1,2,3};
                    byte by=ask("Codigo no encontrado\n"+
                    "Ingrese la opcion correspondiente\n"+
                    "Tienes un codigo existente? : \n"+ 
                    "1. Si\n"+
                    "2. NO\n"+
                    "3. MENU PRINCIPAL",opcion,"");
                    
                    

                    
                    if (by == 1) {
                        break;
                        
                    }else if(by==3){
                        return;
                    }

                    

                    
                    
                }
            
                
            case 1:
                customPrint("Creando Nuevo Codigo...");
                wait(2000);
                
                code= Cliente.IdRandom();
                cliente = new Cliente(code,Suscripcion.Basica);
                customPrint("Codigo "+cliente.getId()+ " creado","green");

                
                salir = true;
                break;
            case 0:
                return;
            
            
                
            }
        }
        boolean antiguo = true;
        customPrint(cliente.consultarPerfil());
        if (cliente.getSuscripcion().name().equals("Basica")){
            antiguo = false;

        }
        
        

        
            byte p1_=ask(
        "Ingrese la opcion correspondiente\n"+
        "Desea mejorar su suscripcion? \n"+ 
        "1. Si\n"+
        "2. No\n"+
        "0. MENU PRINCIPAL",opciones_2,"");
        

        
       
            
            
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
        else if (p1_==0){
            return;
        }
        if (Obra.generarTabla()==""){
            customPrint("No hay obras disponibles");
            return;
        }
        customPrint("Estas son las Obras disponibles\n\n"+String.format("%30s %22s %22s %15s", "Nombre Obra", "Genero", "Duracion","Precio")+"\n\n"+Obra.generarTabla());
            customPrint("Que Obra desea comprar? \n");
            String inputF = in.nextLine().toLowerCase();
            float precioSus=0;
            while (Obra.nombres(inputF)){
                customPrint("Obra no encontrada \n"+
                "Ingrese un nombre valido :","red");
                inputF = in.nextLine().toLowerCase();
                



            }
            


            
            
            customPrint("Estas son las Funciones disponibles\n\n"+String.format("%20s %30s ", "Nombre Obra", "Horario")+"\n\n"+Funcion.generarTabla(inputF));
            customPrint("Que Funcion desea comprar? \n");
            byte numb = in.nextByte();
            precioSus=0;
            
            while (!Funcion.indiceFuncion(numb,inputF) | numb ==0){
                customPrint("Funcion no encontrada \n"+
                "Ingrese un numero valido :","red");
                numb = in.nextByte();
                in.nextLine();
                }
            customPrint(Funcion.escogerFuncion(numb, inputF).getObra().getNombre());
            
            
            
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
            
            Funcion funcion=Funcion.escogerFuncion(numb, inputF);
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
        

            Funcion.escogerFuncion(numb, inputF).eliminarSilla(codigo);
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
                Teatro.getInstancia().getTesoreria().setDineroEnCaja(Teatro.getInstancia().getTesoreria().getDineroEnCaja()+dineroTesoreria);
                Teatro.getInstancia().getTesoreria().setTotal(Teatro.getInstancia().getTesoreria().getTotal()+dineroTesoreria);

                
            wait(2000);
            
        
        
        
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
            wait(2000);
            customPrint("Compra Cancelada");
            return;
        }
            
        
                

        
        




        

    }

    public static void gestionObras(){
        //CREACIÓN DE OBRAS PREVIAS PARA SOLUCIÓN DE FRANJAS HORARIAS
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

        Obra obra1 = null;
        for (Obra obra : Teatro.getInstancia().getObras()) {
            if (obra.getNombre().trim().equals("NOTFORITE")) { 
                obra1 = obra;
                break;
            }
        }
    
        // Si obra1 no existe, se crea y se agrega al listado
        if (Teatro.getInstancia().getObras().size() == 0) {
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
        int o;
        o = 0;
        Obra eleccion;
        eleccion = null;
        String menuObras = "";
        ArrayList<LocalDate> week = getWeek();
        if (!Teatro.getInstancia().getObras().isEmpty()){
            for (Obra obra : Teatro.getInstancia().getObras()){  
                if(!obra.getNombre().equals("NOTFORITE")){   
                    i = i + 1;
                    String item = String.valueOf(i) + ". " + obra.getNombre() + "\n";
                    menuObras = menuObras + item;
                }
                else{
                    o++;
                }
            }
        }
        customPrint(menuObras + String.valueOf(i + 1) + ". Crear nueva obra");
        boolean continuarSelObra = false;
        while (continuarSelObra == false){        
        String obraSel = ask("Por favor indique el número de su elección sin punto");
            if(canBeInt(obraSel)){
            if (Integer.parseInt(obraSel) <= i){
                eleccion = Teatro.getInstancia().getObras().get(Integer.parseInt(obraSel) - 1 + o);
                continuarSelObra = true;
            }
            else if (Integer.parseInt(obraSel) > i){
                String nombre = ask("Por favor ingrese el nombre de la nueva obra");
                int f;
                f = 0;
                String actores = "";
                for (Actor actor : Teatro.getInstancia().getActores()){
                    f = f + 1;
                    actores = actores + String.valueOf(f) + "."+ actor.getNombre();
                }
                customPrint(actores);
                int s;
                s = 1;
                ArrayList<Actor> reparto = new ArrayList<>();
                ArrayList<Aptitud> papeles = new ArrayList<>();
                while (s != 0){
                    customPrint("Digita el número del actor que desea agregar sin punto\n Si ya terminaste de añadir el reparto por favor ingresa 0", "blue");
                    boolean continuarSelAct = false;
                    while (!continuarSelAct){
                        String d = in.nextLine();
                        if(canBeInt(d)){
                            s = Integer.parseInt(d);
                            if (s == 0){
                                continuarSelAct = true;                           
                                break;

                            }
                            else if (s > Teatro.getInstancia().getActores().size()){
                                customPrint("Opción fuera de rango, intenta de nuevo", "red");
                                break;
                            }
                            else{
                                Actor elegido = Teatro.getInstancia().getActores().get(s - 1);
                                reparto.add(elegido);
                                continuarSelAct = true;
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
                        else{
                            customPrint("oops, no pareces haber elegido una opción correcta, intenta de nuevo", "red");
                        }
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
                customPrint("Indique el director de la obra <3", "blue");
                boolean continuarDirectorSel = false;
                while (continuarDirectorSel == false){
                    String directorSel = in.nextLine();
                    if(canBeInt(directorSel)){
                        if (Integer.parseInt(directorSel) <= x){
                            eleccionDir = genero.getDirectores().get(Integer.parseInt(directorSel) - 1);
                            continuarDirectorSel = true;
                        }
                        else if (Integer.parseInt(directorSel) == x + 1){
                            String nDirector = ask("Por favor ingrese el nombre del nuevo director");
                            customPrint("Por favor ingrese el número de documento del nuevo director", "blue");
                            boolean continuarIdDir = false;
                            long idDirector = 0;
                            while(!continuarIdDir){
                                String idDirectorString = in.nextLine();
                                if(canBeLong(idDirectorString)){
                                    idDirector = Long.parseLong(idDirectorString);
                                    continuarIdDir = true;
                                }
                                else{
                                    customPrint("oops, parece que no ingresaste un número de documento, intenta de nuevo", "red");
                                }
                            }
                            eleccionDir = new Director(nDirector, idDirector, genero);
                            continuarDirectorSel = true;
                            }
                        else{
                            customPrint("oops, número fuera de rango, intenta de nuevo", "red");
                        }
                    }
                    else{
                        customPrint("oops, parece que no ingresaste un número entero, intenta de nuevo", "red");
                    }
                }
                customPrint("Director seleccionado: \n" + eleccionDir, "green");
                Director director = eleccionDir;
                x = 0;
                boolean continuarCostProdu = false;
                float costoProduccion = 0;
                while(!continuarCostProdu){
                    customPrint("Por favor, ingresa el costo de producción", "blue");
                    String costoProduccionSTR = in.nextLine();
                    if(canBeFloat(costoProduccionSTR)){
                        costoProduccion = Float.parseFloat(costoProduccionSTR);
                        continuarCostProdu = true;
                    }
                    else{
                        customPrint("oops, pareces no haber ingresado un valor numérico, intenta de nuevo", "red");
                    }
                }
                long dur = longAsk("Por favor ingresa la duración de la obra, \nusa el formato HHmmSS, no separes con :,- ni otro símbolo similar.");
                
                eleccion = new Obra(nombre, reparto, papeles, director, costoProduccion, genero, dur);  
                continuarSelObra = true;
                }
            }
            else {
                customPrint("Opción invalida, intenta de nuevo");
            }
        }
            customPrint("Has seleccionado" + " " + eleccion.getNombre());
            int a = eleccion.getFuncionesRecomendadas();
            boolean continuar = false;
            int drut = 0;
            do {
                int rut = intAsk("¿Cuántas funciones te gustaría crear para esta obra?");
    
                if (a + 2 < rut) {
                    customPrint("ALERTA, PUEDEN SER DEMASIADAS FUNCIONES PARA ESTA OBRA\n DESEA CONTINUAR?\n 1. Sí\n 2. No");
                    boolean continuarByteAlertaMax = false;
                    byte sc = 0;
                    while (!continuarByteAlertaMax){
                        String stc = in.nextLine();
                        if(canBeByte(stc)){
                            sc = Byte.parseByte(stc);
                            continuarByteAlertaMax = true;
                        }
                        else{
                            customPrint("Opción inválida, intenta de nuevo");
                        }
                    }
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
                    boolean continuarByteAlertaMin = false;
                    byte sc = 0;
                    while(!continuarByteAlertaMin){
                        String stc = in.nextLine();
                        if (canBeByte(stc)){
                            sc = Byte.parseByte(stc);
                            continuarByteAlertaMin = true;
                        }
                        else{
                            customPrint("Opción no válida, intente nuevamente.");
                        }
                    }
    
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

             //Impresión del horario 
            String horarioToString = "";
        ArrayList<LocalDateTime> dias = new ArrayList<>();
        LocalDateTime hoy = LocalDateTime.now();
        for (int pio = 1; pio <= 7; pio++) {
            dias.add(hoy.plusDays(pio).withHour(0).withMinute(0).withSecond(0).withNano(0));
        }

        // Crear un formato para imprimir fechas y horas
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");


        for (Sala sala : Teatro.getInstancia().getSalas()) {
            horarioToString = horarioToString + "Horario para sala" + sala.getNumeroSala() + ":\n";
            
            // Crear columnas para los 7 días
            ArrayList<ArrayList<String>> columnasDias = new ArrayList<>();
            for (int qpi = 0; qpi < 7; qpi++) {
                columnasDias.add(new ArrayList<>());
            }

            // Filtrar y organizar las funciones de esta sala
            ArrayList<Funcion> funcionesSala = new ArrayList<>();
            for (Funcion funcion : Teatro.getInstancia().getFuncionesCreadas()) {            
                if (funcion.getObra() != null){
                    if (funcion.getSala().equals(sala)) {
                        funcionesSala.add(funcion);
                    }
                }
            }


            //Distribuir funciones en los días correspondientes
            for (Funcion funcion : funcionesSala) {
                for (int pou = 0; pou < dias.size(); pou++) {
                    LocalDateTime inicioDia = dias.get(pou);
                    LocalDateTime finDia = inicioDia.plusDays(1);

                    if (funcion.getHorario().get(0).isAfter(inicioDia) && funcion.getHorario().get(0).isBefore(finDia)) {
                        columnasDias.get(pou).add(
                            funcion.getObra().getNombre() + " (" + 
                            funcion.getHorario().get(0).format(formatoHora) + " - " + 
                            funcion.getHorario().get(1).format(formatoHora) + ")"
                        );
                        break;
                    }
                }
            }
            for (ArrayList<String> diaFunciones : columnasDias) {
                diaFunciones.sort((f1, f2) -> {
                    LocalTime horaF1 = LocalTime.parse(f1.substring(f1.indexOf("(") + 1, f1.indexOf(" -")), formatoHora);
                    LocalTime horaF2 = LocalTime.parse(f2.substring(f2.indexOf("(") + 1, f2.indexOf(" -")), formatoHora);
                    return horaF1.compareTo(horaF2);
                });
            }
            //Imprimir el horario en columnas
            for (int coo = 0; coo < 7; coo++) {
                horarioToString = horarioToString + "Día " + dias.get(coo).format(formatoFecha) + ":\n";
                for (String detalleFuncion : columnasDias.get(coo)) {
                    horarioToString = horarioToString + "   " + detalleFuncion + "\n";
                }
            }
            customPrint(horarioToString);
            horarioToString = "";
        }

        //Impresión de lista de clientes

        if (!Teatro.getInstancia().getClientes().isEmpty()){
                ArrayList<Cliente> listaClientes = new ArrayList<>();
                String lista = "";
                for (Cliente cliente : Teatro.getInstancia().getClientes()){
                    if (cliente.getGeneroFavorito() != null){
                        if(eleccion.getGenero() == cliente.getGeneroFavorito()){
                            listaClientes.add(cliente);
                        }
                    }
                }
                for (Cliente cliente : listaClientes){
                    lista = lista + cliente.getCorreo() + "/n";
                }
            customPrint(lista);
            }
    } 


            

    public static void ContratarActor(){

    byte[] two = {0, 1, 2};
    byte menuLog = ask("Seleccione:\n1. Empresa registrada.\n2. Empresa nueva.", two, "");
    byte ACTORES_POR_PAGINA = 5;

    List<Actor> historialEmpresa = new ArrayList<>();
    Cliente empresa = null;

    menuSwitch:
    switch (menuLog){

        case 0:  //salir
            customPrint("Saliendo...", "red");
            return;

        case 1: //empresa registrada
            long idEntrada = longAsk("Ingrese el número de identificación.");

            if (idEntrada == 0){
                customPrint("Saliendo...", "red");
                return;
            }

            boolean idFlag = false;

            
            for (Cliente cliente : Teatro.getInstancia().getClientes()){

                if (cliente.getId() == idEntrada && cliente.getTipo().equals("Empresa")){
                    customPrint("Cliente confirmado en base de datos.", true, "green");
                    historialEmpresa = cliente.getHistorial();
                    empresa = cliente;
                    idFlag = true;
                    break menuSwitch;
                }

            }

            if (!idFlag){
                customPrint("El número de identificación no existe en la base de datos de empresa.\nRevise si el cliente es de tipo Empresa o si se digitó correctamente.", true, "red"); return;
            }

        case 2: //Empresa nueva

            long newId = longAsk("Genere un nuevo número de identificación.");

            if (newId == 0){
                customPrint("Saliendo...", "red");
                return;
            }
            
            for (Cliente cliente : Teatro.getInstancia().getClientes()){
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

        final int CALIFICACION_ALTA = 4; //por ahora
        List<Actor> actorsForRental = new ArrayList<>(Teatro.getInstancia().getActores());

        //primera ronda de preguntas
        byte[] options = new byte[9];
        options[0] = 0; options[1] = 1; options[2] = 2;
        
        //antes de empezar, remover aquellos actores en condición de reevaluación
        actorsForRental.removeIf(actor -> actor.isReevaluacion());

        //System.out.println(actorsForRental);

        //PREGUNTA NO. 1
        byte rolActor = ask("¿Qué tipo de papel desempeñará el actor?\n1. Rol principal.\n 2. Rol secundario.", options, "");

        //reservar los de calificacion alta solo para roles principales
        List<Actor> newAct = new ArrayList<>();

        switch (rolActor){

            case 0:
                customPrint("Saliendo...", "red");
                return;

            case 1:
                ///actorsForRental.removeIf(actor -> (actor.getCalificacion() <= CALIFICACION_ALTA) );
                for (int i = 0; i < actorsForRental.size(); i++){
                    Actor actor = actorsForRental.get(i);
                    if (actor.getCalificacion() >= CALIFICACION_ALTA){
                        newAct.add(actor);
                    }
                }
                
            case 2:
                actorsForRental.removeIf(actor -> (actor.getCalificacion() > CALIFICACION_ALTA) );

            }


        //System.out.println(actorsForRental);
        actorsForRental = newAct;
        options[3] = 3; options[4] = 4; options[5] = 5; options[6] = 6; options[7] = 7; options[8] = 8;

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

        if (horario == null){
            return;
        }

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
                customPrint("Saliendo...", "red");
                return;
            
            case 1:

                List<ArrayList<Object>> contadores = new ArrayList<>();
    
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
    
                for (ArrayList<Object> contador : contadores){
    
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
    
                for (ArrayList<Object> contador : contadores){
    
                    if (isIn(actorsForRental, (Actor)contador.get(0))){ 
                        
                        int newVal = ((Integer)contador.get(1)) + 1;
    
                        contador.set(1, newVal); 
                    
                    }
    
                }
    
                contadores.removeIf(contador -> ((Integer)contador.get(1)) < 1);
    
                if (contadores.size() == 0){
                    customPrint("No se encontraron actores que se ajusten bien a las características.", true, "red"); return;}
    
                List<Actor> advancedList = new ArrayList<Actor>();
    
                for (ArrayList<Object> contador : contadores){
                    advancedList.add( (Actor)contador.get(0));
                }
                
                actorsForRental = advancedList;
                advancedList = null;
                
                customPrint(actorsForRental.size() + " actor/es se ajustaron a una o más características avanzadas.", true, "green");
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
            Actor actorEscogido = null;
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

        customPrint("Pago recibido!", true, "green");
        customPrint("El actor escogido fue " + actorEscogido.getNombre() + " por un precio de " +  Actor.formatoPrecio(actorEscogido.getPrecioContrato(duracionContrato)) + "\nLa contratación tendrá lugar el:\n" + fechaInicio.toLocalDate().format(formatter) + "\n(" + fechaInicio.toLocalTime() + "-" + fechaFin.toLocalTime() + ")", "green");
        byte codigoCompra = empresa.pagarContratoActor(actorEscogido, duracionContrato, Teatro.getInstancia().getTesoreria());
        ArrayList<LocalDateTime> horarioFinal = new ArrayList<>(); horarioFinal.add(fechaInicio); horarioFinal.add(fechaFin);
        actorEscogido.addHorario(horarioFinal);

                
        }

    }

    //Base para funcionalidad 2
    //Interaccion 2
    public static void gestionEmpleados(){
        final String[] nombres = {"Miguel", "Juan", "Danna", "Carlos", "Oscar", "Julian", "Maria", "Paula", "Esteban", "Sara", "Frank", "Pablo", "Jimena", "Daniela", "Ana", "Emma", "Samuel"};
        final String [] Apellidos = {"Perez", "Hernandez", "Montoya", "Velez", "Aguirre", "Salazar", "Restrepo", "Rodriguez", "Garcia", "Lopez", "Sanchez", "Ramirez", "Gonzales", "Gomez", "Martinez"};
        Teatro.getInstancia().getTesoreria().transferenciaFondos();
        //Verifica si hay deudas y Pagar
        customPrint("Verificando deudas ...");
        String Deudas = "";
        for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
            if(Persona.getDeuda() != 0){
                if(Teatro.getInstancia().getTesoreria().getCuenta().getSaldo() > Persona.getDeuda()){
                    boolean transaccion = Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda());
                    if(transaccion == true){
                        Deudas = Deudas + "Se realizo el Pago a: " + Persona.getNombre() + " por un valor de: " + String.format("$%,.2f", Persona.getDeuda() ) + "\n";
                        Persona.setDeuda(0);
                    }
                }
            }
        }
        String Saldo = String.format("$%,.2f",Teatro.getInstancia().getTesoreria().getCuenta().getSaldo());
        customPrint(Deudas + "El saldo de tesoreria es: " + Saldo, "green");
        
        wait(1000);
        
        //Obtener lista de empleados por ocupacion:
        boolean repetidor = false;
        do{
            String msgBase = "\n";
            for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
                if(msgBase != "\n"){
                    msgBase = msgBase + String.format("%-20s %10s", Persona.getNombre(), "ID: " +  Persona.getId()) + "\n";
                }
                else{
                    msgBase = String.format("%-20s %10s", Persona.getNombre(), "ID: " + Persona.getId()) + msgBase;
                }
            }
            customPrint("Seguridad \n" + msgBase, true, "");
            msgBase = "\n";

            wait(1000);
            
            for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                if(msgBase != "\n"){
                    msgBase = msgBase + String.format("%-20s %10s", Persona.getNombre(), "ID: " +  Persona.getId()) + "\n";
                }
                else{
                    msgBase = String.format("%-20s %10s", Persona.getNombre(), "ID: " + Persona.getId()) + msgBase;
                }
            }
            customPrint("Aseador \n" + msgBase, true, "");
            msgBase = "\n";
            
            wait(1000);
            
            for(Empleado Persona : Teatro.getInstancia().getTipoProfesor()){
                if(msgBase != "\n"){
                    msgBase = msgBase + String.format("%-20s %10s", Persona.getNombre(), "ID: " +  Persona.getId()) + "\n";
                }
                else{
                    msgBase = String.format("%-20s %10s", Persona.getNombre(), "ID: " + Persona.getId()) + msgBase;
                }
            }
            customPrint("Profesor \n" + msgBase, true, "");
            
            wait(1000);

            String question = "Deseas Contratar o Despedir a algún empleado\n1. Si\n2. No\n0. Salir";
            byte[] options = {0,1,2};
            byte respuesta = ask(question, options, "blue");

            switch (respuesta) {
                case 0:
                    customPrint("Saliendo...", "red");
                    wait(1000);
                    return;
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
                                    tipAseador = Teatro.getInstancia().getTipoAseador();
                                    Empleado nuevo_empleado_A = new Empleado(candidatos.get(empleado), idA.get(empleado), "Aseador");
                                    tipAseador.add(nuevo_empleado_A);
                                    Teatro.getInstancia().setTipoAseador(tipAseador);
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
                                    tipSeguridad = Teatro.getInstancia().getTipoSeguridad();
                                    Empleado nuevo_empleado_S = new Empleado(candidatosS.get(empleadoS), idS.get(empleadoS), "Seguridad");
                                    tipSeguridad.add(nuevo_empleado_S);
                                    Teatro.getInstancia().setTipoSeguridad(tipSeguridad);
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
                                    tipProfesors = Teatro.getInstancia().getTipoProfesor();
                                    Empleado nuevo_empleado_P = new Profesor(candidatosP.get(empleadoP), idP.get(empleadoP));
                                    tipProfesors.add(nuevo_empleado_P);
                                    Teatro.getInstancia().setTipoProfesor(tipProfesors);
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
                            Teatro.getInstancia().getEmpleadosPorRendimiento().removeIf(Persona ->{
                                if(Persona.getId() == buscar_id){
                                    encontrado[0] = true;
                                    double liquidacion = (Persona.calcularSueldo()*1.2) + Persona.getDeuda();
                                    Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), liquidacion);
                                    customPrint("Se despidio a " + Persona.getNombre() + " y se le pago su respectiva liquidacion", "green");
                                    return true;
                                }
                                return false;
                            });
                            Teatro.getInstancia().getTipoAseador().removeIf(PersonaA ->{
                                if(PersonaA.getId() == buscar_id){
                                    return true;
                                }
                                return false;
                            });
                            Teatro.getInstancia().getTipoProfesor().removeIf(PersonaP ->{
                                if(PersonaP.getId() == buscar_id){
                                    return true;
                                }
                                return false;
                            });
                            Teatro.getInstancia().getTipoSeguridad().removeIf(PersonaS ->{
                                if(PersonaS.getId() == buscar_id){
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
        
        //Interaccion 2
        customPrint("Asignando trabajos, por favor espere ...", true);
        
        wait(2000);

        //Organiza el ranking - Aseador - Seguridad - Profesor - Salas
        ArrayList<Empleado> Aseador_order = Teatro.getInstancia().getTipoAseador();
        ArrayList<Empleado> Seguridad_order = Teatro.getInstancia().getTipoSeguridad();
        ArrayList<Empleado> Profesor_order = Teatro.getInstancia().getTipoProfesor();
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
        
        Teatro.getInstancia().setTipoAseador(Aseador_order);
        Teatro.getInstancia().setTipoProfesor(Profesor_order);
        Teatro.getInstancia().setTipoSeguridad(Seguridad_order);

        
        //Administrar Trabajadores
        //Asignar horas y trabajos
        //Para Seguridad
        boolean trabajoAsignadoS = true;
        boolean trabajoAsignadoA = true;
        int cant_trabajadores_principiantes = 0;
        int base = 6;
        int totalFunciones = Teatro.getInstancia().getFuncionesCreadas().size();
        int totalTrabajadores_S = Teatro.getInstancia().getTipoSeguridad().size();
        int funcion_por_trabajador;
        if(totalTrabajadores_S == 0){
            funcion_por_trabajador = 0;
        }else{
            funcion_por_trabajador = totalFunciones/totalTrabajadores_S;
        }
        ArrayList<Funcion> funcionesDisponibles = new ArrayList<>(Teatro.getInstancia().getFuncionesCreadas());
        //Se organiza la lista por fecha
        try{
            funcionesDisponibles.sort((f1, f2) ->
            f1.getHorario().get(0).compareTo(f2.getHorario().get(0))
            );
        }
        catch(Exception e){}

        //Verificar si las listas no estan vacias
        if(funcion_por_trabajador != 0){
            for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
                if(Persona.getMetaSemanal() == base){
                    cant_trabajadores_principiantes += 1;
                }
            }
            //Asignacion de tareas si todos los trabajadores son principiantes
            if(cant_trabajadores_principiantes == Teatro.getInstancia().getTipoSeguridad().size()){
                //CASO NORMAL, SE ASIGNAN EN IGUAL CANTIDAD A CADA EMPLEADO
                int funcionesSinHorarios = 0;
                for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
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
                    for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
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
                        Persona.setDisponible(false);
                    }
                }
                String msgBase = "";
                for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
                    if(Persona.getHorario().size() == 1){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Cuidará: ", Persona.getHorario().size() + " Funcion\n");
                    }
                    else if(Persona.getHorario().size() > 1 || Persona.getHorario().size() == 0){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Cuidará: ", Persona.getHorario().size() + " Funciones\n");
                    }            
                }
                customPrint(msgBase);
            }
            else{
                try{
                    ArrayList<Funcion> FuncionPorDuracion = new ArrayList<>(Teatro.getInstancia().getFuncionesCreadas());
                    Collections.sort(FuncionPorDuracion, new Comparator<Funcion>() {
                        public int compare(Funcion f1, Funcion f2){
                            LocalDateTime inicioF1 = f1.getHorario().get(0);
                            LocalDateTime inicioF2 = f2.getHorario().get(0);

                            int fechaCompare = inicioF2.compareTo(inicioF1);
                            if(fechaCompare != 0){
                                return fechaCompare;
                            }
                            double duracionF1 = Duration.between(f1.getHorario().get(0), f1.getHorario().get(1)).toMinutes()/60.0;
                            double duracionF2 = Duration.between(f2.getHorario().get(0), f2.getHorario().get(1)).toMinutes()/60.0;
                            return Double.compare(duracionF2, duracionF1);
                        }
                    });
                    funcionesDisponibles = FuncionPorDuracion;
                }catch(Exception e){}

                //Evaluacion Normal, asignacion de trabajo equitativo
                int funcionesSinHorarios = 0;
                for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
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
                                funcionesSinHorarios += 1;
                                funcionesDisponibles.remove(i);
                                i--;                        
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
                if(funcionesSinHorarios == 1){
                    customPrint("Hay 1 Funcion sin horarios", "red");
                }
                else if(funcionesSinHorarios > 1){
                    customPrint("Hay " + funcionesSinHorarios + " Funciones sin horarios", "red");
                }
                //EVALUACION DE SALAS SIN TRABAJADOR
                if(funcionesDisponibles.size() != 0 ){
                    for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
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
                for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
                    if(Persona.getHorario().size() == 1){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Cuidará: ", Persona.getHorario().size() + " Funcion\n");
                    }
                    else if(Persona.getHorario().size() > 1 || Persona.getHorario().size() == 0){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Cuidará: ", Persona.getHorario().size() + " Funciones\n");
                    }                
                }
                customPrint(msgBase);
            }
        }
        else{
            if(totalFunciones == 0){
                customPrint("No hay funciones para agregar", "red");
                trabajoAsignadoS = false;
            }
            else{
                customPrint("No hay trabajadores de Seguridad", "red");
                trabajoAsignadoS = false;
            }
        }
        //Revisa si todavia quedan funciones que no se asignaron trabajador
        //En este caso estas funciones no se pueden asignar por que existiria solapamiento
        //Por tanto imprime cuantas funciones quedaron sin asiganr
        if(funcionesDisponibles.size() != 0 ){
            if(funcionesDisponibles.size() == 1){
                customPrint("Existen " + funcionesDisponibles.size() + " funcion sin posibilidad de seguridad", "red");
            }
            else{
                customPrint("Existen " + funcionesDisponibles.size() + " funciones sin posibilidad de seguridad", "red");
            }
        }

        //Para Aseador
        cant_trabajadores_principiantes = 0;
        int totalSalas = Sala.getSalas().size();
        int totalTrabajadores_A = Teatro.getInstancia().getTipoAseador().size();
        int cant_a_limpiar;
        if(totalTrabajadores_A == 0){
            cant_a_limpiar = 0;
        }
        else{
            cant_a_limpiar = totalFunciones/totalTrabajadores_A;
        }
        ArrayList<Funcion> funcionesLimpiadas = new ArrayList<>(Teatro.getInstancia().getFuncionesCreadas()); 
        try{
            funcionesLimpiadas.sort((f1, f2) ->
            f1.getHorario().get(0).compareTo(f2.getHorario().get(0))
            );
        }
        catch(Exception e){}
        if(cant_a_limpiar != 0){
            for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                if(Persona.getMetaSemanal() == base){
                    cant_trabajadores_principiantes += 1;
                }
            }
            //En caso de que todos sean principiantes
            if(cant_trabajadores_principiantes == totalTrabajadores_A){
                int funcionesSinHorarios = 0;
                for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
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
                                            if(!(inicioNuevo.isAfter(finActual) || finNuevo.isBefore(inicioSiguiente))){
                                                horarioValido = false;
                                                break;
                                            }
                                        }
                                        else{
                                            //Verificar que el inicio sea despues del horario ya existente
                                            if(!(inicioNuevo.isAfter(finActual))){
                                            //Como no se cumple se tiene que pasar a revisar la siguiente funcion
                                                horarioValido = false;
                                                break;
                                            }
                                        }
                                    }
                                    if(horarioValido){
                                        asignadas += 1;
                                        ArrayList<LocalDateTime> sublista = new ArrayList<>();
                                        sublista.add(inicioNuevo);
                                        sublista.add(finNuevo);
                                        localTime.add(sublista);
                                        if(Funciones.getSala() != null){
                                            Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                            Funciones.getSala().setAseado(true);
                                        }          
                                        funcionesLimpiadas.remove(i);
                                        i--;
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
                                    if(Funciones.getSala() != null){
                                        Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                        Funciones.getSala().setAseado(true);
                                    }                                                                        
                                    i--;
                                }
                            }
                            else{
                                funcionesSinHorarios += 1;
                                funcionesLimpiadas.remove(i);
                                i--;
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
                }
                if(funcionesSinHorarios == 1){
                    customPrint("Hay 1 Funcion sin horarios validos para limpiar", "red");
                }
                else if(funcionesSinHorarios > 1){
                    customPrint("Hay " + funcionesSinHorarios + " Funciones sin horarios validos para limpiar", "red");
                }
                if(funcionesLimpiadas.size() != 0){
                    for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                        ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                        for(int i = 0; i < funcionesLimpiadas.size(); i++){
                            Funcion Funciones = funcionesLimpiadas.get(i);
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
                                    //Verificar que el inicio sea despues del horario ya existente
                                    if(!(inicioNuevo.isAfter(finActual))){
                                    //Como no se cumple se tiene que pasar a revisar la siguiente funcion
                                        horarioValido = false;
                                        break;
                                    }
                                }
                            }
                            if(horarioValido){
                                ArrayList<LocalDateTime> sublista = new ArrayList<>();
                                sublista.add(inicioNuevo);
                                sublista.add(finNuevo);
                                localTime.add(sublista);
                                if(Funciones.getSala() != null){
                                    Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                    Funciones.getSala().setAseado(true);
                                }          
                                funcionesLimpiadas.remove(i);
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
                for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                    if(Persona.getHorario().size() == 1){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Limpiará: ", Persona.getHorario().size() + " vez\n");
                    }
                    else if(Persona.getHorario().size() > 1 || Persona.getHorario().size() == 0){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Limpiará: ", Persona.getHorario().size() + " veces\n");
                    }
                }
                customPrint(msgBase);
            }
            else{
                ArrayList<Funcion> funcionesPorMetros = new ArrayList<>(Teatro.getInstancia().getFuncionesCreadas());
                Collections.sort(funcionesPorMetros, new Comparator<Funcion>() {
                    public int compare(Funcion f1, Funcion f2){
                        LocalDateTime fecha1 = f1.getHorario().get(0);
                        LocalDateTime fecha2 = f2.getHorario().get(0);

                        int fechaCompare = fecha2.toLocalDate().compareTo(fecha1.toLocalDate());

                        if(fechaCompare != 0){
                            return fechaCompare;
                        }
                        double metrosF1 = f1.getSala().getMetrosCuadrados();
                        double metrosF2 = f2.getSala().getMetrosCuadrados();

                        return Double.compare(metrosF2, metrosF1);
                    }
                });
                funcionesLimpiadas = funcionesPorMetros;
                
                int funcionesSinHorarios = 0;
                for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
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
                                            //Verificar que el inicio sea despues del horario ya existente
                                            if(!(inicioNuevo.isAfter(finActual))){
                                            //Como no se cumple se tiene que pasar a revisar la siguiente funcion
                                                horarioValido = false;
                                                break;
                                            }
                                        }
                                    }
                                    if(horarioValido){
                                        asignadas += 1;
                                        ArrayList<LocalDateTime> sublista = new ArrayList<>();
                                        sublista.add(inicioNuevo);
                                        sublista.add(finNuevo);
                                        localTime.add(sublista);
                                        if(Funciones.getSala() != null){
                                            Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                            Funciones.getSala().setAseado(true);
                                        }          
                                        funcionesLimpiadas.remove(i);
                                        i--;
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
                                    if(Funciones.getSala() != null){
                                        Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                        Funciones.getSala().setAseado(true);
                                    }   
                                    funcionesLimpiadas.remove(i);
                                    i--;
                                }
                            }
                            else{
                                funcionesSinHorarios += 1;
                                funcionesLimpiadas.remove(i);
                                i--;
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
                if(funcionesSinHorarios == 1){
                    customPrint("Hay 1 Funcion sin horarios", "red");
                }
                else if(funcionesSinHorarios > 1){
                    customPrint("Hay " + funcionesSinHorarios + " Funciones sin horarios, invalidas para limpiar", "red");
                }
                //Asignacion trabajos restantes
                if(funcionesLimpiadas.size() != 0){
                    for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                        ArrayList<ArrayList<LocalDateTime>> localTime = new ArrayList<>(Persona.getHorario());
                        for(int i = 0; i < funcionesLimpiadas.size(); i++){
                            Funcion Funciones = funcionesLimpiadas.get(i);
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
                                    //Verificar que el inicio sea despues del horario ya existente
                                    if(!(inicioNuevo.isAfter(finActual))){
                                    //Como no se cumple se tiene que pasar a revisar la siguiente funcion
                                        horarioValido = false;
                                        break;
                                    }
                                }
                            }
                            if(horarioValido){
                                ArrayList<LocalDateTime> sublista = new ArrayList<>();
                                sublista.add(inicioNuevo);
                                sublista.add(finNuevo);
                                localTime.add(sublista);
                                if(Funciones.getSala() != null){
                                    Persona.getTrabajos().add(Funciones.getSala().getMetrosCuadrados());
                                    Funciones.getSala().setAseado(true);
                                }    
                                funcionesLimpiadas.remove(i);
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
                for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                    if(Persona.getHorario().size() == 1){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Limpiará: ", Persona.getHorario().size() + " vez\n");
                    }
                    else if(Persona.getHorario().size() > 1 || Persona.getHorario().size() == 0){
                        msgBase = msgBase + String.format("%-10s %10s", Persona.getNombre() + " Limpiará: ", Persona.getHorario().size() + " veces\n");
                    }
                }
                customPrint(msgBase);
            }

        }
        else{
            if(totalSalas == 0){
                customPrint("No hay salas Existentes", "red");
                trabajoAsignadoA = false;
            }
            else{
                customPrint("No hay trabajadores de Aseador", "red");
                trabajoAsignadoA = false;
            }
        }

        if(funcionesLimpiadas.size() != 0 ){
            if(funcionesLimpiadas.size() == 1){
                customPrint("Existen " + funcionesLimpiadas.size() + " funcion donde no es posible limpiar la sala", "red");
            }
            else if(funcionesLimpiadas.size() > 1){
                customPrint("Existen " + funcionesLimpiadas.size() + " funciones donde no es posible limpiar la sala", "red");
            }
        }

        if(trabajoAsignadoA || trabajoAsignadoS){
            customPrint("trabajos Asignados...", "green");
            customPrint("Desplegando Trabajadores");
            
            wait(1000);
    
            customPrint("Verificando los trabajos...", "green");
    
            wait(1000);
            //Verificacion del trabajo
            //Seguridad
            cant_trabajadores_principiantes = 0;
            for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
                if(Persona.getMetaSemanal() == base){
                    cant_trabajadores_principiantes += 1;
                    Persona.setDisponible(true);
                }
            }
            if(cant_trabajadores_principiantes == Teatro.getInstancia().getTipoSeguridad().size()){;
                for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
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
                }
            }
            else{
                for(Empleado Persona : Teatro.getInstancia().getTipoSeguridad()){
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
            for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
                if(Persona.getMetaSemanal() == base){
                    cant_trabajadores_principiantes += 1;
                    Persona.setDisponible(true);
                }
            }
            if(cant_trabajadores_principiantes == Teatro.getInstancia().getTipoAseador().size()){
                for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
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
                for(Empleado Persona : Teatro.getInstancia().getTipoAseador()){
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
        }
        else{
            customPrint("No hay trabajos para asignar\n No se puede verificar los trabajos", "red");
        }


        wait(2000);

        //Interaccion 3
        //Pagar nomina a empleados:
        Saldo = String.format("$%,.2f", Teatro.getInstancia().getTesoreria().getCuenta().getSaldo());
        customPrint("El saldo de tesoreria es: " + Saldo);
        byte[] option = {0,1,2};
        byte respuesta = ask("¿Desea realizar los pagos \n1. Si\n2. No\n0. Salir", option, "green");
        switch (respuesta) {
            case 0:
                customPrint("Saliendo...", "red");
                wait(1000);
                return;
            case 1:
                double fondos = Teatro.getInstancia().getTesoreria().getCuenta().getSaldo();
                double totalSaldos = 0;
                //Verificacion de fondos:
                for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                    totalSaldos = totalSaldos + Persona.calcularSueldo();
                }
                //Realizar pago
                if(totalSaldos > fondos){
                    ArrayList<Empleado>  Cuentas_Pagadas = new ArrayList<>();
                    double cantPagada = 0;
                    customPrint("Upps... No se puede realizar los pagos adecuadamente", "Red");
                    customPrint("Realizando pagos de manera equitativa...");
                    for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                        boolean transaccion = Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), (Persona.getDeuda() + Persona.calcularSueldo()) *0.5);  //Establecer cuanto se le debe a la persona
                        if(transaccion != true){
                            customPrint("No se le puede pagar a " + Persona.getNombre());
                            Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo());
                            customPrint("nueva deuda: " + Persona.getDeuda() );
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
                    customPrint("Saldo disponible " + Teatro.getInstancia().getTesoreria().getCuenta().getSaldo());
                    Cuentas_Pagadas = null;
                }
                else{
                    //Verificacion fondos Bonificacion
                    totalSaldos = 0;
                    double cantPagada = 0;
                    if(Teatro.getInstancia().getTesoreria().verificacionMeta() != true){
                        //Verificacion Metas Personales
                        for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
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
                            for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                cantPagada = cantPagada + (Persona.calcularSueldo() + Persona.getDeuda());
                                totalSaldos = totalSaldos + Persona.calcularSueldo();
                            }
                            //Pago solo sueldo base
                            if(cantPagada > fondos){
                                customPrint("No se pudo realizar los pagos junto a la deuda");
                                customPrint("Realizando pago del Sueldo Base");
                                Teatro.getInstancia().getTesoreria().pagarSueldoBase(null, cantPagada);
                                customPrint("Pago existoso", true, "green");
                                String msg = "Se pago un total de " + totalSaldos;
                                customPrint(msg);
                                customPrint("Se realizo el pago a " + Teatro.getInstancia().getEmpleadosPorRendimiento().size() + " cuentas en total");
                                customPrint("Saldo disponible " + String.format("$%,.2f", Teatro.getInstancia().getTesoreria().getCuenta().getSaldo()));
                                for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                    if(Persona.verificacionMeta() == true){
                                        Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo()*0.15); //Se añade la bonificacion a la deuda solo a aquellas que la cumplieron
                                    }
                                }
                            }
                            else{
                            //Pago Sueldo base + Deuda
                                for(Empleado Persona: Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                    Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda() + Persona.calcularSueldo());
                                }
                                customPrint("Pago existoso", true, "green");
                                String msg = "Se pago un total de " + cantPagada;
                                customPrint(msg);
                                customPrint("Se realizo el pago a " + Teatro.getInstancia().getEmpleadosPorRendimiento().size() + " cuentas en total");
                                customPrint("Saldo disponible " + String.format("$%,.2f", Teatro.getInstancia().getTesoreria().getCuenta().getSaldo()));
                            }

                        }
                        //Realizacion Pago Boni + Deuda
                        else{
                            for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                if(Persona.verificacionMeta() == true){
                                    Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.15) + Persona.getDeuda());
                                }
                                else{
                                    Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), Persona.calcularSueldo() + Persona.getDeuda());
                                }
                            }
                            customPrint("Pago existoso", true, "green");
                            String msg = "Se pago un total de " + totalSaldos;
                            customPrint(msg);
                            customPrint("Se realizo el pago a " + Teatro.getInstancia().getEmpleadosPorRendimiento().size() + " cuentas en total");
                            customPrint("Saldo disponible " + String.format("$%,.2f", Teatro.getInstancia().getTesoreria().getCuenta().getSaldo()));
                        }
                    }
                    //Pago Bonis Tesorerias + deuda
                    else{
                        for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
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
                            for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                if(Persona.verificacionMeta() != true){
                                    totalSaldos = totalSaldos + (Persona.calcularSueldo() + Persona.getDeuda());
                                }
                                else{
                                    totalSaldos = totalSaldos + ((Persona.calcularSueldo() * 1.15) + Persona.getDeuda());
                                }
                            }
                            //Realizacion Pagos
                            if(totalSaldos > fondos){
                                //Verificacion
                                totalSaldos = 0;
                                customPrint("Ups... No se pueden aplicar las bonificaciones personales");
                                customPrint("Realizando Pagos");
                                for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                    cantPagada = cantPagada + (Persona.calcularSueldo() + Persona.getDeuda());
                                    totalSaldos = totalSaldos + Persona.calcularSueldo();
                                }
                                //Pago solo sueldo base
                                if(cantPagada > fondos){
                                    customPrint("No se pudo realizar los pagos junto a la deuda");
                                    customPrint("Realizando pago del Sueldo Base");
                                    Teatro.getInstancia().getTesoreria().pagarSueldoBase(null, cantPagada);
                                    customPrint("Pago existoso", true, "green");
                                    String msg = "Se pago un total de " + totalSaldos;
                                    customPrint(msg);
                                    customPrint("Se realizo el pago a " + Teatro.getInstancia().getEmpleadosPorRendimiento().size() + " cuentas en total");
                                    customPrint("Saldo disponible " + String.format("$%,.2f", Teatro.getInstancia().getTesoreria().getCuenta().getSaldo()));
                                    for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                        if(Persona.verificacionMeta() == true){
                                            Persona.setDeuda(Persona.getDeuda() + Persona.calcularSueldo()*0.15); //Se añade la bonificacion a la deuda solo a aquellas que la cumplieron
                                        }
                                    }
                                }
                                else{
                                //Pago Sueldo base + Deuda
                                    for(Empleado Persona: Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                        Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), Persona.getDeuda() + Persona.calcularSueldo());
                                    }
                                    customPrint("Pago existoso", true, "green");
                                    String msg = "Se pago un total de " + cantPagada;
                                    customPrint(msg);
                                    customPrint("Se realizo el pago a " + Teatro.getInstancia().getEmpleadosPorRendimiento().size() + " cuentas en total");
                                    customPrint("Saldo disponible " + String.format("$%,.2f", Teatro.getInstancia().getTesoreria().getCuenta().getSaldo()));
                                }
                            }
                            else{
                                for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                    if(Persona.verificacionMeta() == true){
                                        Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.45) + Persona.getDeuda());
                                    }
                                    else{
                                        Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.3) + Persona.getDeuda());
                                    }
                                }
                            }
                        }
                        else{
                            for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                                if(Persona.verificacionMeta() == true){
                                    Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.45) + Persona.getDeuda());
                                }
                                else{
                                    Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), (Persona.calcularSueldo()*1.3) + Persona.getDeuda());
                                }
                            }
                        }
                    }
                }
                //Reseteo de Trabajo
                for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
                    Persona.setTrabajos(new ArrayList<>());
                    Persona.setTrabajoCorrecto(new ArrayList<>());
                    Persona.setTrabajoRealizado(0);
                    Persona.setPuntosPositivos(0);
                    
                }
                break;
            case 2:
                break;
        }
    
        //Despedir si meta es negativa
        ArrayList<Empleado> ActEmpleados = Teatro.getInstancia().getEmpleadosPorRendimiento();
        ArrayList<Empleado> NuevaLista = new ArrayList<>(ActEmpleados);
        ArrayList<Empleado> Despedidos = new ArrayList<>();
        String msgBase = "";
        for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
            if(Persona.getMetaSemanal() < 0){
                NuevaLista.remove(Persona);
                Despedidos.add(Persona);
                double liquidacion = (Persona.calcularSueldo()*1.2) + Persona.getDeuda();
                Teatro.getInstancia().getTesoreria().getCuenta().transferencia(Persona.getCuenta(), liquidacion);
                msgBase = msgBase + Persona.getNombre() + "\n";
            }
            continue;
        }
        Teatro.getInstancia().setEmpleadosPorRendimiento(NuevaLista);
        if(!Despedidos.isEmpty()){
            customPrint("Personas Despedidas: \n" + msgBase);
        }
        
        wait(1000);

        //Imprimir Ranking
        ArrayList<Empleado> Ranking = new ArrayList<>(Teatro.getInstancia().getEmpleadosPorRendimiento());
        Collections.sort(Ranking, new Comparator<Empleado>() {
            public int compare(Empleado E1, Empleado E2){
                return Integer.compare(E2.getMetaSemanal(), E1.getMetaSemanal());
            }
        });
        Teatro.getInstancia().setEmpleadosPorRendimiento(Ranking);
        msgBase = "\n";
        int posicion = 1;
        for(Empleado Persona : Teatro.getInstancia().getEmpleadosPorRendimiento()){
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
    public static void gestionClases() {


        //PRIMERA INTERACCIÓN
        byte[] dos = {1,2};

        customPrint("Bienvenido a la gestión de clases.", "blue");
        wait(2000);

        if (Teatro.getInstancia().getArtistas().size() != 0) {
            customPrint("Estos son los artistas que ya existen en nuestra base de datos");
            StringBuilder artistas = new StringBuilder();
            for (Artista artista : Teatro.getInstancia().getArtistas()) {
                if (Teatro.getInstancia().getActores().contains(artista)) {
                    String lineaArtista = "- Actor " + artista.getNombre() + " con ID " + artista.getId(); 
                    if (lineaArtista.length() > LARGO_LINEAS) {
                        lineaArtista = lineaArtista.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                    }
                    artistas.append(lineaArtista).append("\n");
                }
                if (Teatro.getInstancia().getDirectors().contains(artista)) {
                    String lineaArtista = "- Director " + artista.getNombre() + " con ID " + artista.getId(); 
                    if (lineaArtista.length() > LARGO_LINEAS) {
                        lineaArtista = lineaArtista.substring(0, LARGO_LINEAS - 3) + "..."; // Truncar si es necesario
                    }
                    artistas.append(lineaArtista).append("\n");
                }
            }
            customPrint(artistas.toString());
            wait(3500);
        }
        
        long idArtista = longAsk("Ingrese el ID del artista del cual desea conocer su información:\n" + "\n" + "(Puede escribir el ID de un actor o Director que no exista para inicializarlo)");
        Artista artista = Artista.buscarArtistaPorId(idArtista);
    
        if (artista == null) {
            customPrint("Artista no encontrado.", "red");
            wait(2000);
            byte crearArtista = ask("¿Desea crear un nuevo Artista con este ID?\n" + "1. Sí\n" + "2. No", new byte[]{1, 2}, "");
        
            switch (crearArtista) {
                case 1:
                    String nombreArtista = ask("Ingrese el nombre del nuevo artista:");
                    wait(1000);

                    while (true) {
                        String tipoArtista = ask("Ingrese el tipo de artista (director/actor)");
                        wait(1000);
                        
                        if (tipoArtista.equals("director")) {
                            // Crear un nuevo director
                            new Director(nombreArtista, idArtista);
                            customPrint("Nuevo director agregado: " + nombreArtista + " con ID " + idArtista, "green");
                            wait(2000);
                            customPrint("Los directores no reciben clases.", "yellow");
                            break;
                        } else if (tipoArtista.equals("actor")) {
                            // Crear un nuevo actor
                            Actor nuevoActor = new Actor(nombreArtista, idArtista);
                            customPrint("Nuevo actor agregado: " + nombreArtista + " con ID " + idArtista, "green");
                            artista = nuevoActor; // Asignar al artista actual
                            wait(2000);
                            break;
                        } else {
                            customPrint("Tipo de artista no válido. Debe ser 'director' o 'actor'.", "red");
                        }
                    }
                case 2:
                    ArrayList<Obra> obrasCritics = Obra.mostrarObrasCriticas();
                    // Mostrar todas las obras críticas
                    if (obrasCritics.isEmpty()) {
                        customPrint("No hay obras en estado crítico en el teatro.", "yellow");
                    } else {
                        customPrint("Obras en estado crítico del teatro:", "red");
                        wait(3000);
                        for (Obra obra : obrasCritics) {
                            customPrint("- '" + obra.getNombre() + "' (Promedio de calificaciones: " + obra.promedioCalificacion() + ")");
                
                            // Revisar aspectos críticos y las calificaciones de los actores
                            for (Aptitud aspecto : obra.getPapeles()) { // Obtenemos cada aptitud crítica de la obra
                                boolean encontrado = false;
                                for (Actor actor : obra.getReparto()) { // Revisamos cada actor en el reparto
                                    double calificacion = actor.getCalificacionPorAptitud(aspecto);
                                    if (calificacion != -1 && calificacion < 3.0) { // Si la calificación es baja
                                        customPrint("El aspecto '" + aspecto + "' tiene una calificación baja (" + calificacion + ").", "red");
                                        wait(1500);
                                        customPrint("Notificando al actor: " + actor.getNombre());
                                        encontrado = true;
                                        break;
                                    }
                                }
                                if (!encontrado) {
                                    customPrint("No hay actores con calificaciones bajas en el aspecto '" + aspecto + "'.", "yellow");
                                }
                            }
                            wait(1500);
                        }   
                    }
                    return;
                }
        } else {
            customPrint("El artista ya existe en nuestra base de datos", "green");
            wait(2000);
            if (artista instanceof Director) {
                customPrint("Los directores no reciben clases.", "yellow");
            }
        }

        if (artista != null && artista instanceof Actor) {
            if (((Actor)artista).sigueIgual()) {
                customPrint("El actor no tiene calificaciones. Inicializando calificaciones...");  
                wait(2000);
    
                // Llamar al método casting() para inicializar calificaciones de calificadores
                boolean resultado = Empleado.casting(artista, Teatro.getInstancia().getTipoProfesor());
                if (resultado == false){
                    customPrint("No hay profesores disponibles para inicializar las calificaciones del actor", "red");
                }
                else if(resultado == true) {
                    
                    // Seleccionar un profesor aleatorio
                    Profesor profesorAsignado = (Profesor) Teatro.getInstancia().getTipoProfesor().get((int) (Math.random() * Teatro.getInstancia().getTipoProfesor().size()));
                    
                    // Mostrar quién inicializó las calificaciones
                    customPrint("El/la profesor/a " + profesorAsignado.getNombre() + " es el/la responsable de inicializar las calificaciones\n" + "del actor " + artista.getNombre() + ".");
                }
                wait(3000);
            }
    
            // Inicializar calificaciones del público (simuladas aleatoriamente)

            if (artista.getCalificacionesPublico().size()==0){
                artista.inicializarCalificacionesPublico(artista);
            }
            
            // Mostrar las calificaciones del artista, sea o no sea nuevo
            customPrint("Estas son las calificaciones del actor: " + artista.getNombre());
            wait(2000);

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
            wait(4500);

            ArrayList<Obra> obrasCritics = Obra.mostrarObrasCriticas();
            // Mostrar todas las obras críticas
            if (obrasCritics.isEmpty()) {
                customPrint("No hay obras en estado crítico en el teatro.", "yellow");
            } else {
                customPrint("Obras en estado crítico del teatro:", "red");
                wait(3000);
                for (Obra obra : obrasCritics) {
                    customPrint("- '" + obra.getNombre() + "' (Promedio de calificaciones: " + obra.promedioCalificacion() + ")");
        
                    // Revisar aspectos críticos y las calificaciones de los actores
                    for (Aptitud aspecto : obra.getPapeles()) { // Obtenemos cada aptitud crítica de la obra
                        boolean encontrado = false;
                        for (Actor actor : obra.getReparto()) { // Revisamos cada actor en el reparto
                            double calificacion = actor.getCalificacionPorAptitud(aspecto);
                            if (calificacion != -1 && calificacion < 3.0) { // Si la calificación es baja
                                customPrint("El aspecto '" + aspecto + "' tiene una calificación baja (" + calificacion + ").", "red");
                                wait(1500);
                                customPrint("Notificando al actor: " + actor.getNombre());
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            customPrint("No hay actores con calificaciones bajas en el aspecto '" + aspecto + "'.", "yellow");
                        }
                    }
                    wait(1500);
                }
            }

            if (artista instanceof Actor) {
                Actor actor = (Actor) artista;
                wait(1000);
                // Mostrar áreas de mejora recomendadas
                List<Aptitud> areasDeMejora = actor.obtenerAreasDeMejora();
                customPrint("Áreas del actor " + actor.getNombre() +  " recomendadas para mejorar:", "yellow");
                wait(2000);
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

                wait(1500);

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
                    wait(2000);

                    // Uso del método setSchedule

                    String preguntaClase = "¿Para cuál día quiere programar la clase?\n";
                    String advertencia = "Existe una incompatibilidad del horario con el lineamiento.\n\nRevise si:\n1. El inicio del horario ocurre antes del fin del horario.\n2. Se exceden los límites de horario (muy temprano o muy tarde).\nIntente de nuevo.";
                    LocalTime horaMin = LocalTime.of(10, 0);
                    LocalTime horaMax = LocalTime.of(22, 0); 

                    customPrint("Tenga en cuenta que el horario de clases inicia a las 10 am y terminan a las 10 pm.\n" + "Las clases tienen como duración mínima 2 horas y máxima 4 horas.", "blue");

                    LocalDateTime[] clases = setSchedule(preguntaClase, horaMin, horaMax, 2, 4, true, advertencia);

                    if (clases == null){
                        return;
                    }

                    LocalDateTime inicio = clases[0];
                    LocalDateTime fin = clases[1];
                
                    // Buscar una sala disponible en el horario deseado
                    Sala salaAsignada = null;
                    for (Sala sala : Teatro.getInstancia().getSalas()) {
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
                    for (Empleado empleado : Teatro.getInstancia().getTipoProfesor()) {
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
                    wait(2000);
                                
                
                    // Clase programada exitosamente
                    customPrint("Clase programada exitosamente en el área '" + areaSeleccionada + "' con el profesor '" 
                        + profesorAsignado.getNombre() + "' en la sala '" + salaAsignada.getNumeroSala() + "'.", "green");

                    // Cálculo del costo de matrícula
                    int costoClase = 0;
                    switch (nivelClase) {
                        case "Introducción": costoClase = 50000; break;
                        case "Profundización": costoClase = 75000; break;
                        case "Perfeccionamiento": costoClase = 90000; break;
                    }
        
                    customPrint("El costo de la clase es: $" + costoClase, "blue");
                    wait(2500);
        
                    // Procesar el pago desde la cuenta del artista hacia la tesorería
                    boolean si_no = actor.getCuenta().retirar(costoClase);
    
                    if (si_no == true) {
                        
                        Teatro.getInstancia().getTesoreria().setTotal(Teatro.getInstancia().getTesoreria().getTotal() + costoClase);
                        Teatro.getInstancia().getTesoreria().setDineroEnCaja(Teatro.getInstancia().getTesoreria().getDineroEnCaja() + costoClase);
                        // Asignación de calificador para la próxima función
                        Profesor calificador = null;
                        for (Empleado empleado : Teatro.getInstancia().getTipoProfesor()) {
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
                            wait(1500);
                            // Evaluación y retroalimentación
                            double calificacion = Math.round(Math.random() * 50) / 10.0; // Generar calificación aleatoria
                            customPrint("El profesor calificó el desempeño del actor con un: " + calificacion, "yellow");
        
                            if (calificacion == 5) {
                                // Reembolso del costo de la clase al artista
                                boolean reembolso = Teatro.getInstancia().getTesoreria().getCuenta().transferencia(actor.getCuenta(), costoClase);
                                if (reembolso) {
                                    customPrint("¡Felicitaciones! La calificación perfecta de 5 ha activado un reembolso. Se han reembolsado $" 
                                        + costoClase + " a la cuenta del artista.", "green");
                                } else {
                                    customPrint("Error: No se pudo procesar el reembolso. Por favor, contacte al administrador.", "red");
                                }
                            }
        
                            // Verificar si hubo mejora
                            if (!actor.huboMejora(areaSeleccionada)) {
                                wait(1000);
                                customPrint("No hubo mejora en el desempeño. Se programará una nueva clase en el área deficiente...", "red"); 
                                wait(2000);
                                customPrint("Tenga en cuenta que el horario de clases inicia a las 10 am y terminan a las 10 pm.\n" + "Las clases tienen como duración mínima 2 horas y máxima 4 horas.", "blue");
                                
                                LocalDateTime nuevoInicio = null;
                                LocalDateTime nuevoFin = null;
                                while (true) {
                                    LocalDateTime[] clasesNuevas = setSchedule(preguntaClase, horaMin, horaMax, 2, 4, true, advertencia);
                                    
                                    if (clasesNuevas == null){
                                        return;
                                    }

                                    nuevoInicio = clasesNuevas[0];
                                    nuevoFin = clasesNuevas[1];
                                    if (nuevoInicio.isAfter(fin)) {
                                        break;
                                    }
                                    customPrint("Es imposbile que una clase se programe antes de otra que ya sucedió.");
                                }
        
                                // Buscar una sala disponible para el nuevo horario
                                Sala nuevaSala = null;
                                for (Sala sala : Teatro.getInstancia().getSalas()) {
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
                                for (Empleado empleado : Teatro.getInstancia().getTipoProfesor()) {
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
                    }else {
                        customPrint("El actor cuenta con saldo insuficiente para pagar la clases");
                    }                    
                    return;
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
                wait(1000);
    
                String preguntaClase = "¿Para cuál día quiere programar la clase?\n";
                String advertencia = "Existe una incompatibilidad del horario con el lineamiento.\n\nRevise si:\n1. El inicio del horario ocurre antes del fin del horario.\n2. Se exceden los límites de horario (muy temprano o muy tarde).\nIntente de nuevo.";
                LocalTime horaMin = LocalTime.of(10, 0);
                LocalTime horaMax = LocalTime.of(22, 0); 

                customPrint("Tenga en cuenta que el horario de clases inicia a las 10 am y terminan a las 10 pm.\n" + "Las clases tienen como duración mínima 2 horas y máxima 4 horas.", "blue");

                LocalDateTime[] clases = setSchedule(preguntaClase, horaMin, horaMax, 2, 4, true, advertencia);
                LocalDateTime inicio = clases[0];
                LocalDateTime fin = clases[1];
            
                // Buscar una sala disponible en el horario deseado
                Sala salaAsignada = null;
                for (Sala sala : Teatro.getInstancia().getSalas()) {
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
                for (Empleado empleado : Teatro.getInstancia().getTipoProfesor()) {
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
                int costoClase = 0;
                switch (nivelClase) {
                    case "Introducción": costoClase = 50000; break;
                    case "Profundización": costoClase = 75000; break;
                    case "Perfeccionamiento": costoClase = 90000; break;
                }
    
                customPrint("El costo de la clase es: $" + costoClase, "blue");
    
                // Procesar el pago desde la cuenta del artista hacia la tesorería
                boolean si_no = actor.getCuenta().retirar(costoClase);

                if (si_no == true) {
                    
                    Teatro.getInstancia().getTesoreria().setTotal(Teatro.getInstancia().getTesoreria().getTotal() + costoClase);
                    Teatro.getInstancia().getTesoreria().setDineroEnCaja(Teatro.getInstancia().getTesoreria().getDineroEnCaja() + costoClase);
                    // Asignación de calificador para la próxima función
                    Profesor calificador = null;
                    for (Empleado empleado : Teatro.getInstancia().getTipoProfesor()) {
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
                        double calificacion = Math.round(Math.random() * 50) / 10.0; // Generar calificación aleatoria
                        customPrint("El profesor calificó el desempeño del actor con un: " + calificacion, "yellow");
    
                        if (calificacion == 5) {
                            // Reembolso del costo de la clase al artista
                            boolean reembolso = Teatro.getInstancia().getTesoreria().getCuenta().transferencia(actor.getCuenta(), costoClase);
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

                            customPrint("Tenga en cuenta que el horario de clases inicia a las 10 am y terminan a las 10 pm.\n" + "Las clases tienen como duración mínima 2 horas y máxima 4 horas.", "blue");

                            LocalDateTime nuevoInicio = null;
                            LocalDateTime nuevoFin = null;
                            while (true) {
                                LocalDateTime[] clasesNuevas = setSchedule(preguntaClase, horaMin, horaMax, 2, 4, true, advertencia);
                                nuevoInicio = clasesNuevas[0];
                                nuevoFin = clasesNuevas[1];
                                if (nuevoInicio.isAfter(fin)) {
                                    break;
                                }
                                customPrint("Es imposbile que una clase se programe antes de otra que ya sucedió.");
                            }
    
                            // Buscar una sala disponible para el nuevo horario
                            Sala nuevaSala = null;
                            for (Sala sala : Teatro.getInstancia().getSalas()) {
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
                            for (Empleado empleado : Teatro.getInstancia().getTipoProfesor()) {
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
            }
        }    
    }// Fin del método    
}