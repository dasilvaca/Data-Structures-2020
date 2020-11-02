package com.janus.janusapp.structs;

public class DoubleLinkedL<T>{
    public DoubleNode<T> FirstIn;
    public DoubleNode<T> LastIn;
    int size;

    /**public DoubleQueue(){
        size=0;
        FirstIn=null;
        LastIn=null;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public void put(T data){
        DoubleNode<T> newNode = new DoubleNode<T>(data);
        if(size==0){
            FirstIn=LastIn=newNode;
            size=1;
        }else{
            LastIn.back=newNode;
            newNode.next=LastIn;
            LastIn=newNode;
            size+=1;
        }
    }
    public int Size(){
        return size;
    }
    public T remove(){
        T data=FirstIn.data;
        FirstIn=FirstIn.back;
        FirstIn.next=null;
        size-=1;
        return data;
    }
    public void print(){
        DoubleNode<T> iterador=LastIn;
        String imprimible="[ ";
        while(iterador!=null){
            imprimible+=iterador.data.toString()+" ";
            iterador=iterador.next;
        }
        System.out.println(imprimible+"]");
    }**/

}
