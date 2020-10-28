package structs;

public class BSTree<T> {
    BinaryNode<T> root;

    public BSTree(int key, T data){
        this.root.key=key;
        this.root.data=data;
    }
    
    public BSTree(BinaryNode<T> root){
        this.root=root;
    }
    public BSTree(){
        root=null;
    }

    public T find(int key){
        BinaryNode<T> newNode=root;
        while(newNode!=null){
            if(newNode.key==key){
                return newNode.data;
            }else if()
        }
    }

    public void Insert(int key, T data){
        BinaryNode<T> newNode = new BinaryNode<T>(key,data);
        if(root==null){
            this.root=newNode;
        }else{
            BinaryNode<T> Iterator = root;
            while(Iterator!=null){
                if(key>Iterator.key){
                    Iterator=Iterator.right;
                }else{
                    Iterator=Iterator.left;
                }
            }
        } 
    }

}
