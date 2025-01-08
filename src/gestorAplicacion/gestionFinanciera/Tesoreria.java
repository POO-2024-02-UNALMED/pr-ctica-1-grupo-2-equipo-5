package gestorAplicacion.gestionFinanciera;

public class Tesoreria {
    CuentaBancaria Cuenta;
    float DineroEnCaja;
    float MetaTotal;
    float Total;

    public CuentaBancaria getCuenta() {
        return Cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        Cuenta = cuenta;
    }
    public float getDineroEnCaja() {
        return DineroEnCaja;
    }
    public void setDineroEnCaja(Float dineroEnCaja) {
        DineroEnCaja = dineroEnCaja;
    }
    public float getMetaTotal() {
        return MetaTotal;
    }
    public void setMetaTotal(Float metaTotal) {
        MetaTotal = metaTotal;
    }
    public float getTotal() {
        return Total;
    }
    public void setTotal(Float total) {
        Total = total;
    }
}
