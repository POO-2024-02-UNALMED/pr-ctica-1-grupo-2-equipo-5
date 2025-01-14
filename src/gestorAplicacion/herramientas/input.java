package gestorAplicacion.herramientas;

import java.util.List;
import java.util.Scanner;
import gestorAplicacion.gestionObras.Actor;

public class input{

    public static Scanner in;

    public static void setScanner(Scanner sc){ in  = sc; }
    public static Scanner getScanner(){ return in; }

    //metodo sobrecargado para uso con diferentes tipos que pregunta y escanea respuesta
    public static String ask(String question){
        System.out.println(question);
        String answer = in.nextLine();
        return answer;
    }

    //para el método que acepta enteros se revisa si el numero hace parte de las opciones disponibles
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
}