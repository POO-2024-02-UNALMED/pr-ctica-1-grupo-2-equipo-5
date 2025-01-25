package gestorAplicacion.herramientas;



public enum Suscripcion implements InterfaceTipos{

    Basica,
    Premium,
    Elite,
    Vip;
    @Override
    public  String tipos(){
        String top = String.format("%30s %30s %30s ","Tipo Suscipcion","Precio","Beneficios"+"\n\n");
        String tipo1 = String.format("%30s %30s %30s ","BASICA",String.format("$%,.2f",0.0),"-------"+"\n\n");
        String tipo2 = String.format("%30s %30s %30s ","PREMIUM",String.format("$%,.2f",11900.0),"10% de Descuento"+"\n");
        String cont = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES"+"\n");
        String cont2 = String.format("%30s %30s %30s ","","","Y ACCESO A ASIENTOS COMFORT"+"\n\n");
        String tipo3 = String.format("%30s %30s %30s ","VIP",String.format("$%,.2f",18900.0),"25% de Descuento"+"\n");
        String cont3 = String.format("%30s %30s %30s ","","","EN TODAS LAS FUNCIONES"+"\n");
        String cont4 = String.format("%30s %30s %30s ","","","Y ACCESO A ASIENTOS PREMIUM"+"\n\n");
        String tipo4 = String.format("%30s %30s %30s ","ELITE",String.format("$%,.2f",39900.0),"FUNCIONES GRATIS\n");
        String cont5 = String.format("%30s %30s %30s ","","","ILIMITADASSSS Y"+"\n");
        String cont6 = String.format("%30s %30s %30s ","","","ASIENTO EXCLUSIVO GOLD GRATISSS"+"\n\n");
        
    

        


        return top+tipo1+tipo2+cont+cont2+tipo3+cont3+cont4+tipo4+cont5+cont6;
    }
    @Override
    public  boolean imprimirTipos(String tipo) {
        for (Suscripcion suscripcion : Suscripcion.values()) {
            // Comparar el String con el nombre del enum
            if (suscripcion.name().equalsIgnoreCase(tipo)) {
                return false; 
            }
        }
        return true; // Ningún tipo coincide
    }
    
        
    
}
