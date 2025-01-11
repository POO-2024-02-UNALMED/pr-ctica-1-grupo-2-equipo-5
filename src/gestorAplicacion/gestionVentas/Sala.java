package gestorAplicacion.gestionVentas;

import java.util.ArrayList;

public class Sala {
    private ArrayList <Silla> sillas = new ArrayList<>();
    private Boolean aseado;
    private Boolean ocupado;


    public ArrayList<Silla> getSillas() {
        return sillas;
    }
    public void setSillas(ArrayList<Silla> sillas) {
        this.sillas = sillas;
    }
    public Boolean getAseado() {
        return aseado;
    }
    public void setAseado(Boolean aseado) {
        this.aseado = aseado;
    }
    public Boolean getOcupado() {
        return ocupado;
    }
    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }
   
    


    
}
