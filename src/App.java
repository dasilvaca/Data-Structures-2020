/*import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;*/
import structs.*;

public class App {

    public static void main(String[] args) throws Exception {
        
        /***User hola = new User("Daniel","12345678","09/11/2001","daniel@unal.edu.co");
        LinkedL lista = new LinkedL();
        lista.append(hola);
        Project prime= new Project("Estructuras",lista,500);
        System.out.println(hola.aString());
        System.out.println(prime.aString());***/



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
        x.orderedStack.printpri();
        System.out.println("Hola");
        
        /**PriorityStack<String> s= new PriorityStack<String>();
        PriorityNode<String> n= new PriorityNode<String>(3, "HOLA");
        s.insert(n);
        s.printpri();
       
        
        Stack<String> st= new Stack<String>();
        st.push("Joselo");
        st.print();**/

    }
}
