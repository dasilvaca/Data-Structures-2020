package structs;


public class PriorityNode<T> extends Node<T>{
    int priority;
    PriorityNode<T> next = null;

    public PriorityNode(int prioridad, T dato){
        super(dato);
        this.priority = prioridad;
    }
    public String aString(){
        return (String)this.data;
    }
}

