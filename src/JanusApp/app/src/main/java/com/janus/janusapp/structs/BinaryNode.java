package com.janus.janusapp.structs;

public class BinaryNode<T> {
    public BinaryNode<T> left = null;
    public BinaryNode<T> right = null;
    public BinaryNode<T> father = null;
    public T data;
    public int key;

    public BinaryNode(int ky,T dt){
        this.data = dt;
        this.key = ky;
    }

    public BinaryNode(int ky){
        this.key = ky;
    }

    public BinaryNode(int ky, T dt, BinaryNode<T> father){
        this.key = ky;
        this.data = dt;
        this.father = father;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
