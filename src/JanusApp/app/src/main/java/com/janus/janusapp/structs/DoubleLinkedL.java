package com.janus.janusapp.structs;

public class DoubleLinkedL<T> {
    DoubleNode Firstnode=new DoubleNode<T>();
    DoubleNode Lastnode=new DoubleNode<T>();
    int size=0;

    public DoubleLinkedL(){

    }

    public boolean isEmpty() {
        if (size == 0) {  //Si el tamaño es cero la lista está vacía
            return true;
        }
        return false;
    }
    public T get(int index) {
        if (index >= size || index < 0) {       //Se mira si el index está dentro del rango
            System.out.println("List index out of range");
            return null;
        }
        int cont = 0;
        DoubleNode<T> tempNode = Firstnode;   //Se copia la cabeza de la lista en un nuevo nodo
        if (index == 0) {
            return tempNode.data;
        } //Si el index es cero se retorna el elemento de la cabeza
        else {
            while (cont < index) { //Si index!=0 se itera hasta llegar al nodo con ese índex
                tempNode = tempNode.next;
                cont += 1;
            }
            return tempNode.data; //
        }
    }

    public void append(T element) {
        if (size == 0) {  //Si no hay elementos en la lista se añade y la cabeza y la cola apuntan al nuevo elemento
            Firstnode = new DoubleNode<T>(element);
            Lastnode = Firstnode;
            size += 1; //Aumenta el tamaño de la lista
        } else {
            DoubleNode<T> penultimo = Lastnode; //Si ya hay elementos crea un nodo y apunta la cola y el penultimo a este
            Lastnode = new DoubleNode<T>(element);
            penultimo.next = Lastnode;
            Lastnode.back=penultimo;
            size += 1;
        }
    }
    public T remove(int index) {
        if (index == 0) {
            T value =(T)Firstnode.data;
            Firstnode = Firstnode.next;
            size -= 1;
            return value;
        }
        DoubleNode<T> tempNode = Firstnode;
        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.next;
        }
        T value = tempNode.next.data;
        tempNode.next.data = null;
        tempNode.next = tempNode.next.next;
        size -= 1;
        return value;
    }
    public void delete(DoubleNode deletable){
        if(size==1){

        }else if(deletable==Firstnode){
            deletable.next.back=null;
            deletable.next=null;
        }else if(deletable==Lastnode){
            deletable.back.next=null;
            deletable.back=null;
        }else{
            deletable.back.next=deletable.next;
            deletable.next.back= deletable.back;
            deletable.next=deletable.back=null;
        }

        size--;
    }

    public void add(int index, T element) {
        if (index == 0) {  //Si se añade al principio la cabeza apunta a este y este al que era la cabeza
            DoubleNode<T> newNode = new DoubleNode<T>(element);
            newNode.next = Firstnode;
            newNode.next.back=newNode;
            Firstnode = newNode;
            size += 1;
        } else { //Si no se itera hasta llegar a uno anterior donde lo queremos añadir
            DoubleNode<T> iterable = Firstnode;
            for (int i = 0; i < index - 1; i++) {
                iterable = iterable.next;
            }
            DoubleNode<T> newNode = new DoubleNode<T>(element);//Luego creamos un nuevo nodo al cual apuntamos donde se estaba parado
            newNode.next = iterable.next;
            newNode.back=iterable;
            iterable.next = newNode;          //nuevon se inicializa apuntando al siguiente
            size += 1;

        }

    }
}
