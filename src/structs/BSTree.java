package structs;

public class BSTree<T> {
    BinaryNode<T> root;

    public BSTree(int key, T data){
        this.root=new BinaryNode<>(key,data);//Recomiendo tener las diapositivas a la mano
    }
    
    public BSTree(BinaryNode<T> root){    //Puros constructores ya tu sabe
        this.root=root;
    }
    public BSTree(){
        root=null;
    }

    public BinaryNode<T> find(int key){
        BinaryNode<T> Iterator=root;  //Es la copia de root con la cual se itera sobre el arbol
        while(Iterator!=null){
            if(Iterator.key==key){ //Con estos if se mira si la llave es menor, igual o mayor para llegar a
                return Iterator;  //la posición del elemento  
            }else if(key>Iterator.key){   
                if(Iterator.right==null){ //Con este if se mira si el nodo no está y si no está
                    BinaryNode<T> newNode= new BinaryNode<T>(key);  //Se añade un nodo vacio donde debería estar
                    newNode.father=Iterator; //Y se retorna ese nodo
                    Iterator.right=newNode;
                    return newNode;
                }
                Iterator=Iterator.right;
            }else{
                if(Iterator.left==null){
                    BinaryNode<T> newNode= new BinaryNode<T>(key); 
                    newNode.father=Iterator;  //Se hace lo mismo de mirar si no está pero en este caso
                    Iterator.left=newNode;  //es cuando el nodo debe ir al lado izquierdo, en el anterior
                    return newNode;       //era al lado derecho y si no está, tambien se retorna un nodo vacio 
                }                          //donde debería ir
                Iterator=Iterator.left;
            }
        }
        return null;   //Si no estoy mal este null nunca lo retorna pero lo puse para saltar
    }               //las putas  warnings

    public BinaryNode<T> Next(BinaryNode<T> node){  //Este método es el de buscar el nodo con la siguiente llave
        if(node.right!=null){     //como lo explico el profesor y está tal cual como en las diapositivas.
            return  LeftDescendant(node.right);  //retorna el nodo y no la data
        }
        return RightAncestor(node);
    }

    private BinaryNode<T> LeftDescendant(BinaryNode<T> node){
        if(node.left==null){
            return node;
        }
        return LeftDescendant(node.left);
    }

    private BinaryNode<T> RightAncestor(BinaryNode<T> node){
        if(node.key<node.father.key){
            return node.father;
        }
        return RightAncestor(node.father);
    }

    public void Insert(int key, T dt){   //Se inserta un nodo en la posición key
        BinaryNode<T> newNode=this.find(key);   //Se aprovechas de que find retorna un nodo vacio cuando
        newNode.data = dt;    //el nodo no está en el arbol y simplemente le mete la data(dt) a ese nodo
    }

    @Override    
    public String toString(){  //Como toString usé el método de imprimir por niveles
        Queue<BinaryNode<T>> newQ = new Queue<BinaryNode<T>>(); //Se crea una cola la cual nos sirve un montón
        BinaryNode<T> Iterator = root;  //El iterador sobre el arbol
        BinaryNode<T> NewNode;  //Un nodo que ayuda mas adelante
        newQ.enqueue(Iterator);  //Iterator aquí es la raiz
        String retornable="";  //La String que se retorna
        while(!newQ.isEmpty()){
            NewNode=newQ.dequeue();   //Saca el nodo de la cola
            retornable+=NewNode.toString()+" "; //Añade la data del nodo a la String
            if(NewNode.left!=null){   //Y aquí en los if añade a los hijos en caso de que tenga
                newQ.enqueue(NewNode.left);
            }                             //Y se repite hasta que se recorrió todo el arból
            if(NewNode.right!=null){
                newQ.enqueue(NewNode.right);
            }
        }
        return retornable;

    }
    
    public void delete(int key){  //Para este método revisar las diapositivas
        BinaryNode<T> toDelete=this.find(key);  //toDelete es el nodo que vamos a borrar
        if(toDelete.right==null){ //Chequea si no tiene hijo derecho
            if(toDelete==root){ 
                root=toDelete.left;   //Si toDelete es root simplemente cambiamos root al izquierdo de toDelete
            }else { //Si no es la raiz... y se promueve el hijo izquierdo de toDelete a toDelete
                if(toDelete.father.left==toDelete){
                    toDelete.father.left=toDelete.left;
                    if(toDelete.left!=null){toDelete.left.father=toDelete.father;}
                    
                }
                if(toDelete.father.right==toDelete){
                    toDelete.father.right=toDelete.left;
                    if(toDelete.left!=null){toDelete.left.father=toDelete.father;}
                }
            }
        }else{ //Si toDelete tiene hijo derecho se promueve el next a donde está toDelete
            BinaryNode<T> next = this.Next(toDelete); //next es el nodo siguiente a toDelete en valor numerico 
            if(next.right!=null){ //Si next tiene hijo derecho este se promueve a donde está next
                BinaryNode<T> nextright = next.right;  //nextright es el hijo derecho de next
                nextright.father = next.father; 
                next.father.left = nextright;
            }else{ //Si next no tiene hijo derecho se promueve null a donde estaba next
                if(next.father.left==next){next.father.left=null;}else{next.father.right=null;}
            }
            if(toDelete==root){  //Toca mirar el caso donde toDelete es root para evitar un nullpointerexception
                next.father=null; //Se promueve next a donde estaba root en este bloque
                if(root.left!=null){next.left=root.left;}
                next.right=root.right;
                root=next;
            }else{  //Se promueve next a toDelete en este bloque
                next.father=toDelete.father;
                if(toDelete.left!=null){next.left=toDelete.left;}
                next.right=toDelete.right;
                if(toDelete.father.left==toDelete){ //Se mira si toDelete es un hijo izquierdo o un hijo derecho
                    toDelete.father.left=next;
                }else{
                    toDelete.father.right=next;
                }
            }
            
        }
    }

    public BinaryNode<T> previous(BinaryNode<T> node){  
        if(node.left!=null){     
            return  rightDescendant(node.left);  
        }
        return LeftAncestor(node);
    }

    private BinaryNode<T> rightDescendant(BinaryNode<T> node){
        if(node.right==null){
            return node;
        }
        return rightDescendant(node.right);
    }

    private BinaryNode<T> LeftAncestor(BinaryNode<T> node){
        if(node.key>node.father.key){
            return node.father;
        }
        return LeftAncestor(node.father);
    }

    public LinkedL<BinaryNode<T>> search(){
        return new LinkedL<BinaryNode<T>>();
    }
}


