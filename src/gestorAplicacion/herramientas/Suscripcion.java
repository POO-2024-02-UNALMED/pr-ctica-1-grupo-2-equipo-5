package gestorAplicacion.herramientas;



public enum Suscripcion {

    Basica,
    Premium,
    Elite,
    Vip;

    public static String tiposSuscipcion(){
        String top = String.format("%30s %30s %30s ","Tipo Suscipcion","Precio","Beneficios"+"\n\n");
        String tipo1 = String.format("%30s %30s %30s ","BASICA",String.format("$%,.2f",0.0),"-------"+"\n\n");
        String tipo2 = String.format("%30s %30s %30s ","PREMIUM",String.format("$%,.2f",11900.0),"10% de Descuento "+"\n");
        String cont = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES"+"\n\n");
        String tipo3 = String.format("%30s %30s %30s ","VIP",String.format("$%,.2f",18900.0),"25% de Descuento"+"\n");
        String cont2 = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES"+"\n\n");
        String tipo4 = String.format("%30s %30s %30s ","ELITE",String.format("$%,.2f",39900.0),"FUNCIONES GARTIS\n");
        String cont3 = String.format("%30s %30s %30s ","","","ILIMITADASSSS"+"\n\n");
        
    

        


        return top+tipo1+tipo2+cont+tipo3+cont2+tipo4+cont3;
    }
    public static boolean tipos(String tipo) {
        for (Suscripcion suscripcion : Suscripcion.values()) {
            // Comparar el String con el nombre del enum
            if (suscripcion.name().equalsIgnoreCase(tipo)) {
                return false; 
            }
        }
        return true; // Ningún tipo coincide
    }
    
    
        
    
}
