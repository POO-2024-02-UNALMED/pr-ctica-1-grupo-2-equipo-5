package gestorAplicacion.herramientas;

import java.util.ArrayList;

public enum Suscripcion {

    Basica,
    Premium,
    Vip;

    public static String tiposSuscipcion(){
        String top = String.format("%30s %30s %30s ","Tipo Suscipcion","Precio","Beneficios"+"\n\n");
        String tipo1 = String.format("%30s %30s %30s ","BASICA",String.format("$%,.2f",0.0),"-------"+"\n\n");
        String tipo2 = String.format("%30s %30s %30s ","PREMIUM",String.format("$%,.2f",11900.0),"10% de Descuento "+"\n");
        String cont = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES"+"\n\n");
        String tipo3 = String.format("%30s %30s %30s ","VIP",String.format("$%,.2f",18900.0),"25% de Descuento"+"\n");
        String cont2 = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES");

        


        return top+tipo1+tipo2+cont+tipo3+cont2;
    }
    public static boolean tipos(String tipo){
        ArrayList <String> arreglo= new ArrayList<>();
        arreglo.add("vip");
        arreglo.add("basica");
        arreglo.add("premium");
        for (String string : arreglo) {
            if (string.equals(tipo)) {
                return false;
                
            
            
            
        }
        

    }
    return true;
}
        
    
}
