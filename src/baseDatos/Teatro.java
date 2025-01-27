import java.io.Serializable;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionObras.Director;
import gestorAplicacion.gestionObras.Actor;


public class Teatro implements Serializable{
    
    public static final long serialVersionUID = 1L;
    public static Teatro instancia;
    static Tesoreria tesoreria = new Tesoreria(0, 100);

    public static Sala sala1 = new Sala(1, 100, 24);

    //empleados
    public static ArrayList<Empleado> empleadosPorRendimiento = new ArrayList<>();
    public static ArrayList<Empleado> tipoSeguridad = new ArrayList<>();
    public static ArrayList<Empleado> tipoAseador = new ArrayList<>();
    public static ArrayList<Empleado> tipoProfesor = new ArrayList<>();
    
    //artistas
    public static List<Actor> actores = new ArrayList<Actor>();
    public static List<Director> directors = new ArrayList<Director>(); //lista que almacenará todos los actores creados
    
    //obras
    public static ArrayList<Obra> obras = new ArrayList<>();
    public static ArrayList<Obra> estadoCriticoS;

    //clientes
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    
    //funciones
    public static ArrayList <Funcion> funcionesCreadas= new ArrayList<>();
    //salas
    public static ArrayList <Sala> salas = new ArrayList<>();

    //tiquetes
    private static ArrayList <Tiquete> tiquetes=new ArrayList<>();

}
