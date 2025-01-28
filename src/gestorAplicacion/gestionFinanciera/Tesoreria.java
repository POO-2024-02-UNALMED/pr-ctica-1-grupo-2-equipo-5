package gestorAplicacion.gestionFinanciera;

import java.io.Serializable;


public class Tesoreria implements Serializable{
    private double dineroEnCaja;
    private double metaSemanal;
    private CuentaBancaria cuenta;
    private double total;

    //Constructor
    public Tesoreria(float total, float metaSemanal){
        this.total = total;
        this.dineroEnCaja = 0;
        this.metaSemanal = metaSemanal;
        this.cuenta = new CuentaBancaria(1, 10000000);
    }

    //Metodo Verificacion Meta:
/**
 * The function `verificacionMeta` checks if the total value is equal to the weekly goal and returns
 * true if they are equal, otherwise returns false.
 * 
 * @return The method `verificacionMeta` is returning a boolean value. It returns `true` if the total
 * is equal to the weekly goal (`metaSemanal`), and `false` otherwise.
 */
    public boolean verificacionMeta(){
        if (this.total == this.metaSemanal) {
            return true;
        }
        else{
            return false;
        }
    }
    //Transferir dineroencaja a la cuenta
    public void transferenciaFondos(){
        this.getCuenta().ingresar(dineroEnCaja);
        dineroEnCaja = 0;
    }
    //Pago sueldo base
    public void pagarSueldoBase(CuentaBancaria Cuenta, double Cantidad){
        this.cuenta.transferencia(Cuenta, Cantidad);
    }

    //Setters and Getters
    public CuentaBancaria getCuenta() {
        return cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }
    public double getDineroEnCaja() {
        return dineroEnCaja;
    }
    public void setDineroEnCaja(double newDinero) {
        this.dineroEnCaja = newDinero;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getMetaSemanal() {
        return metaSemanal;
    }
    public void setMetaSemanal(double metaSemanal) {
        this.metaSemanal = metaSemanal;
    }
}
