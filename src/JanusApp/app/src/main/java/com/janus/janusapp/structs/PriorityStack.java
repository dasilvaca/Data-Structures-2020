package com.janus.janusapp.structs;

public class PriorityStack<T> extends Stack<T> {
    PriorityNode<T> top = null;
    int size = 0;

    public void insert(PriorityNode<T> nodo) {

        nodo.next = this.top;
        this.top = nodo;
        size += 1;
    }

    public PriorityStack() {
        this.top = null;
    }

    public void printpri() {
        PriorityNode<T> curr = this.top;
        System.out.print("[");
        while (curr != null) {
            if (curr.next != null) {
                System.out.print(curr.data + ", ");
            } else {
                System.out.println(curr.data + "]");
            }
            curr = curr.next;
        }
    }

    public boolean isEmptypri() {
        if (this.top == null) {
            return true;
        } else {
            //[[7]]
            return false;
        }
    }

    public PriorityNode<T> poppri() {
        if (size == 0) {
            return null;
        }
        PriorityNode<T> currentPriorityNode = this.top;
        this.top = this.top.next;
        currentPriorityNode.next = null;
        size -= 1;
        return currentPriorityNode;
    }
}

