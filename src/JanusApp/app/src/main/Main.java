package com.janus.janusapp;

public class Main{

    public static void main(String[] args){
//seis letras en orden alfabético
//convertir las letras en números elevando a su longitud
// AABBCC 65+65+66+... **6
//probar primero con valores pequeños 
/**AVL y/o BS
 * Linked List
 * Dynamic Array (tal vez)
 * 
 */
        
        Node sample = new Node(1);
        Node second = new Node(1);
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(2);
        sample.next = a;
        second.next = b;
        if(sample==second){
            System.out.println("s");
        }else{
            System.out.println("n");
        }
    }
}