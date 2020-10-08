package structs;

public class PriorityStack<T> extends Stack<T> {
    public PriorityNode<T> top;
    
    public void insert(PriorityNode<T> nodo){
        if (this.top != null){
            nodo.next = this.top;
        }
        this.top = nodo;
    }
    
    public PriorityStack(){
        this.top = null;
    }
}

