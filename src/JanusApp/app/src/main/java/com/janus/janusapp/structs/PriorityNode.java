package com.janus.janusapp.structs;


public class PriorityNode<T> extends Node<T>{
    int priority;
    PriorityNode<T> next = null;

    public PriorityNode(int prioridad, T dato){
        super(dato);
        this.priority = prioridad;
    }
    public String aString(){
        if(this.equals(null)){
            return "null";
        }
        return (String)this.data;
    }
}

