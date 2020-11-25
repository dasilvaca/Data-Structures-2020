package com.janus.janusapp.structs;

import android.os.Bundle;

import com.google.gson.Gson;

import java.io.Serializable;

public class HashTable<T extends Serializable,Q extends Serializable> {
    public Object table[] ;
    public int size;
    public int elements;
    public double LoadFactor;

    public HashTable(){
        table= new Object[10];
        elements=0;
        size=10;
    }

    public HashTable(int size){
        table= new Object[size];
        elements=0;
        this.size=size;
    }

    public void insert(T key, Q element){
        int position=key.hashCode()%size;
        elements++;
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",key);
        bundle.putSerializable("object",element);
        if(table[position]==null){
            DoubleLinkedL<Bundle> newList= new DoubleLinkedL<Bundle>();
            newList.append(bundle);
            table[position]=newList;
        }else{
            Gson gson = new Gson();
            String listString = gson.toJson(table[position]);
            DoubleLinkedL<Bundle> newList =gson.fromJson(listString,DoubleLinkedL.class);
            newList.append(bundle);
            table[position]=newList;
        }
        LoadFactor=elements/size;
        if(LoadFactor>0.9){
            resize();
        }
    }
    public void resize(){
        Object[] newTable = new Object[size*2];
        size*=2;
        elements=0;
        for(int i=0;i<table.length;i++){
            if(table[i]!=null){
                Gson gson = new Gson();
                String listString = gson.toJson(table[i]);
                DoubleLinkedL<Bundle> newList =gson.fromJson(listString,DoubleLinkedL.class);
                DoubleNode<Bundle> tempNode=newList.Firstnode;
                while(tempNode!=null){
                    Bundle tempbundle = tempNode.data;
                    T key = (T)tempbundle.get("key");
                    Q element = (Q)tempbundle.get("object");
                    this.insert(key,element);
                    tempNode=tempNode.next;
                }
            }
        }
    }

    public Q find(T key){
        int position = key.hashCode()%size;
        Gson gson = new Gson();
        String listString = gson.toJson(table[position]);
        DoubleLinkedL<Bundle> newList =gson.fromJson(listString,DoubleLinkedL.class);
        DoubleNode<Bundle> tempNode = newList.Firstnode;
        while(tempNode!=null){
            if(key==(T)tempNode.data.get("key")){
                return (Q)tempNode.data.get("object");
            }
        }
        return null;
    }

    public void delete(T key){
        int position = key.hashCode()%size;
        Gson gson = new Gson();
        String listString = gson.toJson(table[position]);
        DoubleLinkedL<Bundle> newList =gson.fromJson(listString,DoubleLinkedL.class);
        DoubleNode<Bundle> tempNode = newList.Firstnode;
        while(tempNode!=null) {
            if (key == (T) tempNode.data.get("key")) {
                newList.delete(tempNode);
            }
        }
    }

}
