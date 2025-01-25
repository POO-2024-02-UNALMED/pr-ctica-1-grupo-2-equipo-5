package gestorAplicacion.herramientas;


public enum Asiento implements InterfaceTipos{
    Basico,
    Comfort, 
    Premium,
    Gold;

    public  String tipos(){
        String top = String.format("%30s %30s ","Tipo Asiento","Precio Adicional\n\n");
        String tipo1 = String.format("%30s %30s  ","BASICO",String.format("$%,.2f",0.0)+"\n");
        String tipo2 = String.format("%30s %30s  ","COMFORT",String.format("$%,.2f",2900.0)+"\n");
        String tipo3 = String.format("%30s %30s  ","PREMIUM",String.format("$%,.2f",5900.0)+"\n");
        String tipo4 = String.format("%30s %30s  ","GOLD",String.format("$%,.2f",9900.0));
        
    
        return top+tipo1+tipo2+tipo3+tipo4;
    }
    
    public boolean imprimirTipos(String tipo) {
        for (Asiento asiento : Asiento.values()) {
            // Comparar el String con el nombre del enum
            if (asiento.name().equalsIgnoreCase(tipo)) {
                return false; 
            }
        }
        return true; // Ningún tipo coincide
    }
}
