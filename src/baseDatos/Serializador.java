package baseDatos;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import uiMain.Main;

public class Serializador {
    
    public static void saveState(String filepath){

        try (ObjectOutputStream memoria = new ObjectOutputStream(new FileOutputStream(filepath))){
            memoria.writeObject(Teatro.getInstancia());
            Main.customPrint("Estado del teatro guardado exitosamente", "green");
        } catch (Exception e){
            Main.customPrint("Error al guardar el estado del teatro", "red");
        }
    }

}