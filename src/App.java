import structs.*;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Prueba A");
        LinkedL lista = new LinkedL();

        lista.add(0, 24);

        lista.add(1, 40);

        for(int i =0; i< lista.size; i++){
            System.out.println(lista.get(i));
        }

    }
}
