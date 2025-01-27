import java.io.Serializable;
import gestorAplicacion.gestionFinanciera.Tesoreria;
import gestorAplicacion.gestionVentas.Sala;
import gestorAplicacion.gestionFinanciera.Empleado;
import gestorAplicacion.gestionObras.Director;
import gestorAplicacion.gestionObras.Actor;


public class Teatro implements Serializable{
    
    private final long serialVersionUID = 1L;
    private static Teatro instancia;
    private Tesoreria tesoreria = new Tesoreria(0, 100);

    private Sala sala1 = new Sala(1, 100, 24);

    //empleados
    private ArrayList<Empleado> empleadosPorRendimiento = new ArrayList<>();
    private ArrayList<Empleado> tipoSeguridad = new ArrayList<>();
    private ArrayList<Empleado> tipoAseador = new ArrayList<>();
    private ArrayList<Empleado> tipoProfesor = new ArrayList<>();
    
    //artistas
    private List<Actor> actores = new ArrayList<Actor>();
    private List<Director> directors = new ArrayList<Director>(); //lista que almacenará todos los actores creados
    
    //obras
    private ArrayList<Obra> obras = new ArrayList<>();
    private ArrayList<Obra> estadoCriticoS;

    //clientes
    private ArrayList<Cliente> clientes = new ArrayList<>();
    
    //funciones
    private ArrayList <Funcion> funcionesCreadas= new ArrayList<>();
    //salas
    private ArrayList <Sala> salas = new ArrayList<>();

    //tiquetes
    private ArrayList <Tiquete> tiquetes=new ArrayList<>();

}
