package com.janus.janusapp.structs;

public class DoubleNode<T> {
    public T data;
    public DoubleNode<T> next=null;
    public DoubleNode<T> back=null;

    public DoubleNode(T dt){
        this.data = dt;
    }
    public void setNext(DoubleNode <T> next){
        this.next = next;
    }

    public void setBack(DoubleNode<T> back){
        this.back=back;
    }
    @Override
    public String toString(){
        return data.toString();
    }
}
