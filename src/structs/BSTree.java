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
            }else if(key>newNode.key){
                newNode=newNode.right;
            }else{
                newNode=newNode.left;
            }
        }
        return null;
    }

    public BinaryNode<T> next(BinaryNode<T> N){
        if(N.right!=null){
            return  LeftDescendant(N.right);
        }
        return RightAncestor(N);
    }

    private BinaryNode<T> LeftDescendant(BinaryNode<T> N){
        if(N.left==null){
            return N;
        }
        return LeftDescendant(N.left);
    }

    private BinaryNode<T> RightAncestor(BinaryNode<T> N){
        if(N.key<N.father.key){
            return N.father;
        }
        return RightAncestor(N.father);
    }

    public void Insert(int key, T data){
        BinaryNode<T> newNode = new BinaryNode<T>(key,data);
        
        } 
    }

}
