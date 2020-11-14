package com.janus.janusapp.structs;

public class PriorityQueue<T> {
    public PriorityStack<T> orderedStack;
    public PriorityStack<T> transStack;

    public PriorityQueue() {
        this.orderedStack = new PriorityStack<T>();
        this.transStack = new PriorityStack<T>();
    }

    public void insert(T data, int prioridad) {
        PriorityNode<T> nodo = new PriorityNode<T>(prioridad, data);
        if (orderedStack.isEmptypri() || orderedStack.top.priority < prioridad) {
            orderedStack.insert(nodo);
        } else {
            PriorityNode<T> current = orderedStack.top;
            PriorityStack<T> duplicado = orderedStack;
            while (current != null && current.priority >= prioridad) {
                current = current.next;
                PriorityNode<T> hola = duplicado.poppri();
                transStack.insert(hola);
            }
            orderedStack.insert(nodo);
            current = transStack.top;
            while (current != null) {
                current = transStack.poppri();
                orderedStack.insert(current);
                current = transStack.top;
            }

        }
    }

    public T ExtractMax() {
        PriorityNode<T> Max = orderedStack.poppri();
        return Max.data;
    }

    public void print() {
        if (orderedStack.isEmpty()) {
            System.out.println("Está vacía la cola :c");
        } else {
            PriorityNode<T> current = orderedStack.top;
            while (current.next != null) {
                System.out.println(current);
                current = current.next;
            }

        }
    }
}

