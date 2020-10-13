/*import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;*/
import structs.*;

public class App {

    public static void main(String[] args) throws Exception {
        DoubleQueue<String> hola= new DoubleQueue<String>();
        System.out.println(hola.Size());
        hola.put("Hola");
        System.out.println(hola.Size());
        hola.print();
        System.out.println(hola.Size());
        hola.put("Me");
        System.out.println(hola.Size());
        hola.print();
        System.out.println(hola.Size());
        hola.put("Llamo");
        System.out.println(hola.Size());
        hola.print();
        System.out.println(hola.Size());
        hola.put("Jose");
        System.out.println(hola.Size());
        hola.print();

        System.out.println(hola.remove());
        System.out.println(hola.Size());
        System.out.println(hola.remove());
        System.out.println(hola.Size());
        hola.print();
        


        

    }
}
