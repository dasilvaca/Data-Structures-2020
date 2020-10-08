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
        if (orderedStack.isEmptypri() || orderedStack.top.priority > prioridad){
            orderedStack.insert(nodo);
            orderedStack.printpri();
        }
        else{
            PriorityNode <T> current = orderedStack.top;
            while (current.next != null && current.priority >= prioridad){
                transStack.insert(current);
                current = current.next;
                orderedStack.poppri();
                
                
            }
            
            orderedStack.insert(nodo);
            current = transStack.top;
            orderedStack.printpri();
            //transStack.printpri();
            
            while (current != null){
                orderedStack.insert(current);
                current = current.next;
                transStack.poppri();
            }
            orderedStack.print();
            
        }
        }
       
        
    }

