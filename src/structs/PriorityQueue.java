package structs;

public class PriorityQueue <T> {
    public PriorityStack<T> orderedStack;
    public PriorityStack<T> transStack;

    public PriorityQueue(){
        this.orderedStack = new PriorityStack<T>();
        this.transStack = new PriorityStack<T>();
    }

    public void add(T data, int prioridad){
        PriorityNode<T> nodo = new PriorityNode<T>(prioridad, data);
        if (orderedStack.isEmptypri() || orderedStack.top.priority < prioridad){
            orderedStack.insert(nodo);
            orderedStack.printpri();
        }
        else{
            PriorityNode <T> current = orderedStack.top;
            PriorityStack<T> dup= orderedStack;
            while (current != null && current.priority > prioridad){
                
                System.out.println(current.aString()); 
                PriorityNode <T> hola = dup.poppri();
                transStack.insert(hola);
                transStack.printpri();
                current = current.next;
                
                
            }
            
            orderedStack.insert(nodo);
            current = transStack.top;
            System.out.print("Ordered: ");
            orderedStack.printpri();
            System.out.print("Transition: ");
            transStack.printpri();
            
            while (current != null){
                
                current= transStack.poppri();
                System.out.println(current.aString());
                System.out.println(current.next);
                orderedStack.insert(current);
                current=transStack.top;
                System.out.println(current);
            }
            orderedStack.printpri();
            
        }
        }
    public void print(){
        if (orderedStack.isEmpty()){
            System.out.println("Está vacía la cola :c");
        }
        else{
            PriorityNode <T> current = orderedStack.top;
            while (current.next != null){
                System.out.println(current);
                current = current.next;
            }

        }
    }
}

