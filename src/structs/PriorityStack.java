package structs;

public class PriorityStack<T> extends Stack<T> {
    PriorityNode<T> top=null;
    
    public void insert(PriorityNode<T> nodo){
        
        nodo.next =this.top;
        this.top = nodo;
    }
    
    public PriorityStack(){
        this.top = null;
    }
    public void printpri(){
        PriorityNode<T> curr = this.top;
        System.out.print("[");
        while(curr != null){
            if(curr.next != null){
                System.out.print(curr.data + ", ");
            } else{
                System.out.println(curr.data + "]");
            }
            curr = curr.next;
        }
    }
    public boolean isEmptypri(){
        if(this.top == null){
            return true;
        } else{
            return false;
        }
    }
    public PriorityNode<T> poppri(){
      
        PriorityNode<T> currentPriorityNode = this.top;
        this.top = this.top.next;
        currentPriorityNode.next = null;
        return currentPriorityNode;
    }
}

