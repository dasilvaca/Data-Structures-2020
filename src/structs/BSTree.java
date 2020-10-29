package structs;

public class BSTree<T> {
    BinaryNode<T> root;

    public BSTree(int key, T data){
        this.root=new BinaryNode<>(key,data);
    }
    
    public BSTree(BinaryNode<T> root){
        this.root=root;
    }
    public BSTree(){
        root=null;
    }

    public BinaryNode<T> find(int key){
        BinaryNode<T> Iterator=root;
        while(Iterator!=null){
            if(Iterator.key==key){
                return Iterator;
            }else if(key>Iterator.key){
                if(Iterator.right==null){
                    BinaryNode<T> newNode= new BinaryNode<T>(key); 
                    newNode.father=Iterator;
                    Iterator.right=newNode;
                    return newNode;
                }
                Iterator=Iterator.right;
            }else{
                if(Iterator.left==null){
                    BinaryNode<T> newNode= new BinaryNode<T>(key); 
                    newNode.father=Iterator;
                    Iterator.left=newNode;
                    return newNode;
                }
                Iterator=Iterator.left;
            }
        }
        return null;
    }

    public BinaryNode<T> Next(BinaryNode<T> N){
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

    public void Insert(int key, T dt){
        BinaryNode<T> newNode=this.find(key);
        newNode.data = dt;
    }

    @Override    
    public String toString(){
        Queue<BinaryNode<T>> newQ = new Queue<BinaryNode<T>>();
        BinaryNode<T> Iterator = root;
        BinaryNode<T> NewNode;
        newQ.enqueue(Iterator);
        String retornable="";
        while(!newQ.isEmpty()){
            NewNode=newQ.dequeue();
            retornable+=NewNode.toString()+" ";
            if(NewNode.left!=null){
                newQ.enqueue(NewNode.left);
            }
            if(NewNode.right!=null){
                newQ.enqueue(NewNode.right);
            }
        }
        return retornable;

    }
    
    public void delete(int key){
        BinaryNode<T> toDelete=this.find(key);
        if(toDelete.right==null){
            if(toDelete==root){
                root=toDelete.left;
            }else {
                if(toDelete.father.left==toDelete){
                    toDelete.father.left=toDelete.left;
                    if(toDelete.left!=null){toDelete.left.father=toDelete.father;}
                    
                }
                if(toDelete.father.right==toDelete){
                    toDelete.father.right=toDelete.left;
                    if(toDelete.left!=null){toDelete.left.father=toDelete.father;}
                }
            }
        }else{
            BinaryNode<T> next = this.Next(toDelete);
            if(next.right!=null){
                BinaryNode<T> nextright = next.right;
                nextright.father = next.father;
                next.father.left = nextright;
            }else{
                if(next.father.left==next){next.father.left=null;}else{next.father.right=null;}
            }
            if(toDelete==root){
                next.father=null;
                if(root.left!=null){next.left=root.left;}
                next.right=root.right;
                root=next;
            }else{
                next.father=toDelete.father;
                if(toDelete.left!=null){next.left=toDelete.left;}
                next.right=toDelete.right;
                if(toDelete.father.left==toDelete){
                    toDelete.father.left=next;
                }else{
                    toDelete.father.right=next;
                }
            }
            
        }
    }

    
}


