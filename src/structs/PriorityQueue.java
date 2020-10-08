package structs;

public class PriorityQueue <T> {
    PriorityStack<T> orderedStack;
    PriorityStack<T> transStack;

    public PriorityQueue(){
        this.orderedStack = new PriorityStack<T>();
        this.transStack = new PriorityStack<T>();
    }

    public void add(T data, int prioridad){
        PriorityNode<T> nodo = new PriorityNode<T>(prioridad, data);
        if (orderedStack.isEmpty() || orderedStack.top.priority < prioridad){
            orderedStack.insert(nodo);
        }
        else{
            PriorityNode <T> current = orderedStack.top;
            while (current.next != null || current.priority > prioridad){
                transStack.insert(current);
                current = current.next;
                orderedStack.pop();
            }
            orderedStack.insert(nodo);
            current = transStack.top;
            while (current != null){
                orderedStack.insert(current);
                current = current.next;
                transStack.pop();
            }
        }
        }
        public String aString(){
            String s="";
            PriorityNode<T> current=orderedStack.top;
            while (current.next != null){
                s+=current.aString()+" ";
                current=current.next;
            }
            return s;

        }
    }

