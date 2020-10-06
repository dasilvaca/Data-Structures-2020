import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;
import structs.*;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Prueba A");
        User hola = new User("Daniel","12345678","09/11/2001","daniel@unal.edu.co");
        LinkedL lista = new LinkedL();
        lista.append(hola);
        Project prime= new Project("Estructuras",lista,500);
        hola.followProject(prime);
        Gson gson = new Gson();
        String s = gson.toJson(hola);
        JSONObject intento = new JSONObject();
        intento.put("Lista",s);
        JSONArray intentoList = new JSONArray();
        intentoList.add(intento);
        try (FileWriter file = new FileWriter("intento.json")) {
 
            file.write(intentoList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
