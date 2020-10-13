/*import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;*/
import structs.*;

public class App {

    public static void main(String[] args) throws Exception {
        
        Queue<String> hola= new Queue<String>();
        hola.enqueue("Hola");
        hola.print();
        hola.enqueue("Me");
        hola.print();
        hola.enqueue("Llamo");
        hola.print();
        hola.enqueue("Jose");
        hola.print();
        System.out.println(hola.dequeue());
        hola.print();
        

        

    }
}
