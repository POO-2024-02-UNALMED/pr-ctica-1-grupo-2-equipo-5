package gestorAplicacion.gestionFinanciera;
import gestorAplicacion.gestionObras.Actor;
import gestorAplicacion.gestionVentas.Funcion;
import gestorAplicacion.gestionVentas.Tiquete;
import gestorAplicacion.herramientas.Genero;
import java.util.ArrayList;
import uiMain.Main.isIn;
import gestorAplicacion.gestionFinanciera.CuentaBancaria;

public class Cliente {

    private List<T> historial = new ArrayList<>()
    private Funcion funcion;
    private Genero generoFavorito;
    private Actor actorFavorito;
    private List<Tiquete> ultimasCompras = new ArrayList<Tiquete>()
    private String tipo;

    public Cliente(String tipo){
        this.tipo = tipo;
    }

    public void pagarAlquilerActor(Actor actor){
        
        if (!Main.isIn(this.historial, actor)){
            historial.add(actor);
        }

        int precio = (int) actor.getPrecioContrato();
        CuentaBancaria.transferencia(Tesoreria.getCuenta(), precio);
    }

    public List<T> getHistorial(){ return historial; }
    public void setHistorial(List <T> historial){ this.historial = historial; }

}
