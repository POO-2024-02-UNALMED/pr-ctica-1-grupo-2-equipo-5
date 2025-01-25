package gestorAplicacion.herramientas;


public enum Asiento implements InterfaceTipos{
    BASICO,
    COMFORT, 
    PREMIUM,
    GOLD;

    public  String tipos(){
        String top = String.format("%30s %30s ","Tipo Asiento","Exclusivo para\n\n");
        String tipo1 = String.format("%30s %30s  ","BASICO","\n");
        String tipo2 = String.format("%30s %30s  ","COMFORT","\n");
        String tipo3 = String.format("%30s %30s  ","PREMIUM","\n");
        String tipo4 = String.format("%30s %30s  ","GOLD","");
        
    
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
