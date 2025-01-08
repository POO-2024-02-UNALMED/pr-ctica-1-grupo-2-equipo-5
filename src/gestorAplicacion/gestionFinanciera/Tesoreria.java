package gestorAplicacion.gestionFinanciera;

public class Tesoreria {
    CuentaBancaria Cuenta;
    Float DineroEnCaja;
    Float MetaTotal;
    Float Total;

    public CuentaBancaria getCuenta() {
        return Cuenta;
    }
    public void setCuenta(CuentaBancaria cuenta) {
        Cuenta = cuenta;
    }
    public Float getDineroEnCaja() {
        return DineroEnCaja;
    }
    public void setDineroEnCaja(Float dineroEnCaja) {
        DineroEnCaja = dineroEnCaja;
    }
    public Float getMetaTotal() {
        return MetaTotal;
    }
    public void setMetaTotal(Float metaTotal) {
        MetaTotal = metaTotal;
    }
    public Float getTotal() {
        return Total;
    }
    public void setTotal(Float total) {
        Total = total;
    }
}
