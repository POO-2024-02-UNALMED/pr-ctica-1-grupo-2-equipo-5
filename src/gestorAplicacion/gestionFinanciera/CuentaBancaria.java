package gestorAplicacion.gestionFinanciera;

public class CuentaBancaria {
    long idTitular;
    double Saldo;

    public void ingresar (double cant){
        this.Saldo += cant;
    }

    public void retirar (double cant){
        if (cant > this.Saldo){
            System.out.println("Saldo insuficiente");
        }
        else{
            Saldo -= cant;
        }
    }

    public void transferencia(CuentaBancaria Destino, double cant){
        if(cant <= this.Saldo){
            this.retirar(cant);
            Destino.ingresar(cant);
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


