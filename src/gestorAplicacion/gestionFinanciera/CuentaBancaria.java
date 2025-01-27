package gestorAplicacion.gestionFinanciera;

import java.io.Serializable;

public class CuentaBancaria implements Serializable{
    private long idTitular;
    private double Saldo;

    public CuentaBancaria(long id, double saldo){
        this.idTitular = id;
        this.Saldo = saldo;
    }

    public void ingresar (double cant){
        this.Saldo += cant;
    }

    public boolean retirar (double cant){
        if (cant > this.Saldo){
            return false;
        }
        else{
            this.Saldo = Saldo - cant;
            return true;
        }
    }

    public boolean transferencia(CuentaBancaria Destino, double cant){
        if(cant <= this.Saldo){
            retirar(cant);
            Destino.ingresar(cant);
            return true;
        }
        else{
            return false;
        }
    }

    public long getTitular(){
        return this.idTitular;
    }
    public void setTitular(long newTitular){
        this.idTitular = newTitular;
    }
    public double getSaldo(){
        return this.Saldo;
    }
    public void setSaldo(double newSaldo){
        this.Saldo = newSaldo;
    }

}


