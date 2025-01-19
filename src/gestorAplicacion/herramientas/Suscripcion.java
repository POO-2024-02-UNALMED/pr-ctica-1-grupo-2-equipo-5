package gestorAplicacion.herramientas;

public enum Suscripcion {

    Basica,
    Premium,
    Vip;

    public static String tiposSuscipcion(){
        String top = String.format("%30s %30s %30s ","Tipo Suscipcion","Precio","Beneficios"+"\n\n");
        String tipo1 = String.format("%30s %30s %30s ","BASICA","0","-------"+"\n\n");
        String tipo2 = String.format("%30s %30s %30s ","PREMIUM","11900","10% de Descuento "+"\n");
        String cont = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES"+"\n\n");
        String tipo3 = String.format("%30s %30s %30s ","VIP","18900","25% de Descuento"+"\n");
        String cont2 = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES");

        


        return top+tipo1+tipo2+cont+tipo3+cont2;
    }
        
    
}
