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

    }
}
