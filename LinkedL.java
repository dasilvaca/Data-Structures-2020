package classes;
import java.util.*;
/**
 *
 * @author JOSE LUIS
 */
public class LinkedL<T> {
    protected Node<T> Firstnode,Lastnode;
    protected int size=0;
    
    public LinkedL(){    //Inicializa la lista con la cabeza y la cola apuntando a null.
        Firstnode=new Node<T>(null,null);
        Lastnode= new Node<T>(null,null);
    }
 
    public boolean isEmpty(){
        if (size==0){  //Si el tamaño es cero la lista está vacía
            return true;
        }
        return false;
    } 
    public T get(int index){
        if(index>=size || index<0){       //Se mira si el index está dentro del rango
            System.out.println("List index out of range");
            return null;
        }
        int cont=0;
        Node<T> n= Firstnode;   //Se copia la cabeza de la lista en un nuevo nodo
        if(index==0){return n.data;} //Si el index es cero se retorna el elemento de la cabeza
        else{
            while(cont<index){ //Si index!=0 se itera hasta llegar al nodo con ese índex
            n=n.Next;  
            cont+=1;
            }
            return n.data; //
        }
    }
    public int indexOf(T el){
        Node<T> ne=Firstnode;  //Copiamos la cabeza
        int conta=0;
        while(true){  //Se itera por la lista, si el elemento actual coincide con el que se busca retorna el contador que sería el index
            if(ne.data==el){  
                return conta;
            }
            if(conta==(size-1)){ //Si el contador llega al máximo index el elemento no está en la lista
                break;
            }
            ne=ne.Next; //Avanza y aumenta el contador
            conta+=1;
        }
        System.out.println("No está en la lista");
        return -1;
    }
    public void append(T el){
        if(size==0){  //Si no hay elementos en la lista se añade y la cabeza y la cola apuntan al nuevo elemento
            Firstnode=new Node(el,null);
            Lastnode=Firstnode;
            size+=1; //Aumenta el tamaño de la lista
        }else{
            Node<T> penultimo= Lastnode; //Si ya hay elementos crea un nodo y apunta la cola y el penultimo a este
            Lastnode= new Node(el,null);
            penultimo.Next=Lastnode;
            size+=1;
        }                
    }  
    public String aString(){
        String s="";    //Se itera añadiendo cada elemento de la lista a un string el cual se retorna
        Node<T> copia= Firstnode;
        for(int i=0;i<size;i++){
            s+= (copia.data).toString()+" ";
            copia=copia.Next;
        }
        return s;
    }
    public T remove(int index){
        if(index==0){
            T v=Firstnode.data;
            Firstnode=Firstnode.Next;
            size-=1;
            return v;
        }
        Node<T> copia=Firstnode;
        for (int i=0;i<index-1;i++){
            copia=copia.Next;
        }
        T v=copia.Next.data;
        copia=copia.Next.Next;
        size-=1;
        return v;
    }  //Corregir
    public void add(int index, T el){
        if(index==0){  //Si se añade al principio la cabeza apunta a este y este al que era la cabeza
            Node<T> nuevo=new Node(el,Firstnode);
            Firstnode= nuevo;
            size+=1;
        }else{ //Si no se itera hasta llegar a uno anterior donde lo queremos añadir 
            Node<T> nuevo = Firstnode;   
            for(int i=0;i<index-1;i++){
                nuevo=nuevo.Next;
            }
            Node<T> nuevon= new Node(el,nuevo.Next);  //Luego creamos un nuevo nodo al cual apuntamos donde se estaba parado 
            nuevo.Next=nuevon;          //nuevon se inicializa apuntando al siguiente
            size+=1;
            
        }
        
    }
}
