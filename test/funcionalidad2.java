package test;

// import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

import gestorAplicacion.gestionFinanciera.Empleado;
// import gestorAplicacion.gestionFinanciera.Tesoreria;
// import gestorAplicacion.gestionObras.Actor;
// import gestorAplicacion.gestionVentas.Cliente;
// import gestorAplicacion.herramientas.Aptitud;
// import gestorAplicacion.herramientas.Genero;
import uiMain.Main;

public class funcionalidad2 {
    public static Scanner in = new Scanner(System.in);
        public static void main(String[] args){
            Empleado Empleado1 = new Empleado("Roberto Perez", 1023524, "Aseador");
            Empleado Empleado2 = new Empleado("Alberto Hernandez", 1023525, "Aseador");
            Empleado Empleado3 = new Empleado("Juan Hernández", 1023526, "Seguridad");
            Empleado Empleado4 = new Empleado("Danna Montoya", 1023527, "Seguridad");
            Empleado Empleado5 = new Empleado("Carlos Velez", 1023528, "Seguridad");
            Empleado Empleado6 = new Empleado("Esteban Ramírez", 1023529, "Profesor");
            Empleado Empleado7 = new Empleado("Ana García", 1023530, "Profesor");
            Empleado Empleado8 = new Empleado("Samuel López", 1023531, "Profesor");
            Empleado Empleado9 = new Empleado("Jimena González", 1023532, "Aseador");
            Empleado1.setDeuda(20000);
            Empleado5.setDeuda(30000);
            Empleado9.setDeuda(50000);
            Empleado2.setTrabajoRealizado(250);
            Empleado3.setTrabajoRealizado(20);
            Empleado4.setTrabajoRealizado(10);
            
            Main.gestionEmpleados();
        }
}

