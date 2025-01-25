package gestorAplicacion.gestionFinanciera;

public class Tesoreria {
    private static double dineroEnCaja;
    private static double metaSemanal;
    private static CuentaBancaria cuenta;
    private double total;

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
    public double getMetaTotal() {
        return metaSemanal;
    }
    public void setMetaTotal(Float metaTotal) {
        metaSemanal = metaTotal;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    // Métodos adicionales
    public static void procesarPago(CuentaBancaria cuenta, double monto) {
        if (cuenta.getSaldo() >= monto) {
            cuenta.retirar(monto);
            dineroEnCaja += monto;
            System.out.println("Pago de " + monto + " procesado correctamente. Dinero en caja: " + dineroEnCaja);
        } else {
            System.out.println("Saldo insuficiente en la cuenta del artista.");
        }
    }

    public static void reembolsarPago(CuentaBancaria cuenta, double monto) {
        if (dineroEnCaja >= monto) {
            dineroEnCaja -= monto;
            cuenta.ingresar(monto);
            System.out.println("Se ha reembolsado " + monto + " al artista. Dinero en caja: " + dineroEnCaja);
        } else {
            System.out.println("Fondos insuficientes para procesar el reembolso.");
        }
    }
}
