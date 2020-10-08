package structs;

public class PriorityStack<T> extends Stack<T> {
<<<<<<< HEAD
    public PriorityNode<T> top;
=======
    PriorityNode<T> top=null;
>>>>>>> 15ec35a91685dd694d8913f707016fa0fd6eae93
    
    public void insert(PriorityNode<T> nodo){
        if (this.top != null){
            nodo.next =this.top;
        }
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
    public T poppri(){
        T returnElem = this.top.data;
        this.top = this.top.next;
        
        return returnElem;
    }
}

