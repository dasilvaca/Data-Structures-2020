package structs;

public class Stack {
    Node top;
    
    public void Stack(){
        this.top = null;
    }
    
    public void push(Object newElement){
        Node newNode = new Node(newElement);
        if (this.top != null){
            newNode.next = this.top;
        }
        this.top = newNode;
    }
    
    public Object pop(){
        Object returnElem = this.top.data;
        this.top = this.top.next;
        return returnElem;
    }
    
    public Object peek(){
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
        Node curr = this.top;
        int len = 0;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        return len;
    }
    
    public void makeEmpty(){
        Node curr = this.top;
        while(curr != null){
            this.top = this.top.next;
            curr = curr.next;
        }
    }
    
    public void print(){
        Node curr = this.top;
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
