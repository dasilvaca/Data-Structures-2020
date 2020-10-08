package structs;

public class Stack <T> {
    public Node <T> top;
    
    public Stack(){
        this.top = null;
    }
    
    public void push(T newElement){
        Node <T> newNode = new Node<T>(newElement);
        if (this.top != null){
            newNode.next = this.top;
        }
        this.top = newNode;
    }
    
    public T pop(){
        T returnElem = this.top.data;
        this.top = this.top.next;
        return returnElem;
    }
    
    public T peek(){
        return this.top.data;
    }
    
    public boolean isEmpty(){
        if(this.top == null){
            return true;
        } else{
            return false;
        }
    }
    
    public int len(){
        Node<T> curr = this.top;
        int len = 0;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        return len;
    }
    
    public void makeEmpty(){
        Node<T> curr = this.top;
        while(curr != null){
            this.top = this.top.next;
            curr = curr.next;
        }
    }
    
    public void print(){
        Node<T> curr = this.top;
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
}
