package baseDatos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import uiMain.Main;
import baseDatos.Teatro;

public class Deserializador {
    
    public static void loadState(String filepath){

        try (ObjectInputStream memoria = new ObjectInputStream(new FileInputStream(filepath))){
            Teatro instancia = (Teatro) memoria.readObject();
            Teatro.setInstancia(instancia);
            Main.customPrint("Estado del teatro cargado correctamente", "green");
        } catch (Exception e){
            Main.customPrint("El estado del teatro no pudo ser cargado", "red");
            Teatro.setInstancia(new Teatro());
        }

    }
}
