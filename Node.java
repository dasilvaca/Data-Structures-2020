package classes;
// La clase Node permite guardar un dato individual.
public class Node<T> {
    /* El atributo data guarda el valor del dato que se quiera guardar sea del
       tipo que sea. Mientras que el atributo next apunta a la instancia de la
       clase Node inmediatamente posterior, lo que servirá para las colas. */
    public Object data;
    /* El atributo next se inicializa en null pues por defecto ningún nodo
       tiene otro nodo que sea el siguiente a él. */
    public Node next = null;
    
    /* Constructor de la clase: */
    public Node(Object dt){
        this.data = dt;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
}
