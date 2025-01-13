package gestorAplicacion.gestionFinanciera;

public class Tesoreria {
    private static float DineroEnCaja;
    private static float metaSemanal;
    private static CuentaBancaria Cuenta;
    private float Total;

    //Metodo para verificacion de fondos
    public double fondosSuficientes(){
        double totalNomina = 0;
        double Bonificacion = 0;
        if (Tesoreria.verificacionMeta() == true){
            for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                if (Persona.verificacionMeta() == true){
                    Bonificacion = 1.45;
                    totalNomina = totalNomina + (Persona.calcularSueldo() * Bonificacion);
                }
                else{
                    Bonificacion = 1.3;
                    totalNomina = totalNomina + (Persona.calcularSueldo() * Bonificacion);
                }
            }
            if(totalNomina < Tesoreria.getCuenta().getSaldo()){
                System.out.println("Se puede aplicar las bonificaciones");
                return totalNomina;
            }
            else{
                totalNomina = 0;
                Bonificacion = 1.3;
                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                    totalNomina = totalNomina +  (Persona.calcularSueldo() * Bonificacion);
                }
                if(totalNomina < Tesoreria.getCuenta().getSaldo()){
                    System.out.println("Se puede aplicar la bonificacion de Tesoreria");
                    return totalNomina;
                }
                else{
                    totalNomina = 0;
                    Bonificacion = 1.15;
                    for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                        if(Persona.verificacionMeta() == true){
                            totalNomina = totalNomina + (Persona.calcularSueldo() * Bonificacion);
                        }
                        else {totalNomina = totalNomina + Persona.calcularSueldo();}
                    }
                    if (totalNomina < Tesoreria.getCuenta().getSaldo()){
                        System.out.println("Se puede aplicar las bonificaciones personales");
                        return totalNomina;
                    }
                    else{
                        totalNomina = 0;
                        for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                            totalNomina = totalNomina + Persona.calcularSueldo();
                        }
                        return totalNomina;
                    }
                }
            }
        }
        else{
            totalNomina = 0;
            Bonificacion = 1.15;
            for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                if(Persona.verificacionMeta() == true){
                    totalNomina = totalNomina + (Persona.calcularSueldo() * Bonificacion);
                }
                else {totalNomina = totalNomina + Persona.calcularSueldo();}
            }
            if (totalNomina < Tesoreria.getCuenta().getSaldo()){
                System.out.println("Se puede aplicar las bonificaciones personales");
                return totalNomina;
            }
            else{
                totalNomina = 0;
                for(Empleado Persona : Empleado.getEmpleadosPorRendimiento()){
                    totalNomina = totalNomina + Persona.calcularSueldo();
                }
                return totalNomina;
            }
        }
    }

    //Metodo para verificar meta
    public static boolean verificacionMeta(){
        if(DineroEnCaja >= metaSemanal){
            System.out.println("Existe la posibilidad de aplicar Bonificacion a los salarios");
            return true;
        }
        else{
            System.out.println("Tristemente no se alcanzo la meta :(");
            return false;
        }
    }

    //Setters and Getters
    public static CuentaBancaria getCuenta() {
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
        return metaSemanal;
    }
    public void setMetaTotal(Float metaTotal) {
        metaSemanal = metaTotal;
    }
    public float getTotal() {
        return Total;
    }
    public void setTotal(Float total) {
        Total = total;
    }
}
