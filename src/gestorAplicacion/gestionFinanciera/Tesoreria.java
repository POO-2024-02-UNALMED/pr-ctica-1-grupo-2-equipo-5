package gestorAplicacion.gestionFinanciera;

public class Tesoreria {
    private static double DineroEnCaja;
    private static float metaSemanal;
    private static CuentaBancaria cuenta;
    private float total;

    //Constructor
    public Tesoreria(float total, float metaSemanal){
        this.total = total;
        Tesoreria.DineroEnCaja = 0;
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

    //Setters and Getters
    public CuentaBancaria getCuenta() {
        return cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        Tesoreria.cuenta = cuenta;
    }
    public double getDineroEnCaja() {
        return DineroEnCaja;
    }
    public void setDineroEnCaja(double dineroEnCaja) {
        DineroEnCaja = dineroEnCaja;
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
