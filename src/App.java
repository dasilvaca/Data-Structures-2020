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
        PriorityQueue<String> x = new PriorityQueue<String>();

        x.add("Juanito", 1);
        x.add("Federico", 100);
        x.add("Charlie", 9999);
        x.add("Pepito", 1000);
        x.add("Sultano", 2);
        x.add("Fulano", 99999);
        x.add("Petunia", 500);
        x.add("Men", 50);
        x.add("mani", 45);
        x.add("Meibus", 2);
        x.add("asdfasdf", 70);
        System.out.println(x);
    }
}
