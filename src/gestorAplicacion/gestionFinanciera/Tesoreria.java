package gestorAplicacion.gestionFinanciera;

public class Tesoreria {
    private static double dineroEnCaja;
    private static float metaSemanal;
    private static CuentaBancaria cuenta;
    private float total;

    //Constructor
    public Tesoreria(float total, float metaSemanal){
        this.total = total;
        Tesoreria.dineroEnCaja = 0;
        Tesoreria.metaSemanal = metaSemanal;
        Tesoreria.cuenta = new CuentaBancaria(1, 10000000);
    }

    //Metodo Verificacion Meta:
    public boolean verificacionMeta(){
        if (this.total == Tesoreria.metaSemanal) {
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
        Tesoreria.cuenta.transferencia(Cuenta, Cantidad);
    }

    //Setters and Getters
    public CuentaBancaria getCuenta() {
        return cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        Tesoreria.cuenta = cuenta;
    }
    public double getDineroEnCaja() {
        return dineroEnCaja;
    }
    public void setDineroEnCaja(double newDinero) {
        dineroEnCaja = newDinero;
    }
    public float getMetaTotal() {
        return metaSemanal;
    }
    public void setMetaTotal(Float metaTotal) {
        metaSemanal = metaTotal;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(Float total) {
        this.total = total;
    }
}
