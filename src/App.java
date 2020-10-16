import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;
import structs.*;

public class App {

    /**
     *
     */

    public static void main(String[] args) throws Exception {
        
        
        User Daniel = new User("Daniel","12","09/11/2001","daniel@unal.edu.co");
        User Juan = new User("Juan","13","03/09/1999","juan@unal.edu.co");
        User Jose= new User("Jose","14","29/11/20001","jose@unal.edu.co");
        Project janos = new Project("Janos",Daniel,300);
        janos.addOwner(Juan);
        
        Juan.toSave();
        Daniel.toSave();
        Jose.toSave();
        DynamicArray hola= new DynamicArray();
        hola.append(Juan);
        hola.append(Daniel);
        hola.append(Jose);
        JSONObject prueba = new JSONObject();
        Gson gson = new Gson();
        String JSON = gson.toJson(hola);
        System.out.println(JSON);
        DynamicArray Jjose = gson.fromJson(JSON, DynamicArray.class);
        for(int i=0;i< Jjose.size;i++){
            System.out.println(Jjose.get(i));
        }


        

    }
}
