import java.io.Serializable;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionFinanciera.Empleado;

public class Teatro implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private static Teatro instancia;
    static Tesoreria tesoreria = new Tesoreria(0, 100);

    private static Sala sala1 = new Sala(1, 100, 24);

    //empleados
    private static ArrayList<Empleado> empleadosPorRendimiento = new ArrayList<>();
    private static ArrayList<Empleado> tipoSeguridad = new ArrayList<>();
    private static ArrayList<Empleado> tipoAseador = new ArrayList<>();
    private static ArrayList<Empleado> tipoProfesor = new ArrayList<>();
    
    //actores
    private static List<Actor> actores = new ArrayList<Actor>();





}
