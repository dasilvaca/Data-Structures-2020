package com.janus.janusapp.structs;

// La clase Node permite guardar un dato individual.
public class Node<T> {
    /* El atributo data guarda el valor del dato que se quiera guardar sea del
       tipo que sea. Mientras que el atributo next apunta a la instancia de la
       clase Node inmediatamente posterior, lo que servirá para las colas. */
    public T data;
    /* El atributo next se inicializa en null pues por defecto ningún nodo
       tiene otro nodo que sea el siguiente a él. */
    public Node<T> next = null;

    /* Constructor de la clase: */
    public Node(T dt) {
        this.data = dt;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
