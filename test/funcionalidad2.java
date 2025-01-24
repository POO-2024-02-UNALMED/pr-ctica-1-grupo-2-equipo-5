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
            new Empleado("Roberto Perez", 1023524, "Aseador");
            new Empleado("Alberto Hernandez", 1023525, "Aseador");
            new Empleado("Juan Hernández", 1023526, "Seguridad");
            new Empleado("Danna Montoya", 1023527, "Seguridad");
            new Empleado("Carlos Velez", 1023528, "Seguridad");
            new Empleado("Esteban Ramírez", 1023529, "Profesor");
            new Empleado("Ana García", 1023530, "Profesor");
            new Empleado("Samuel López", 1023531, "Profesor");
            new Empleado("Jimena González", 1023532, "Aseador");
            Main.gestionEmpleados();
        }
}

