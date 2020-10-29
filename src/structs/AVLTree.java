public class AVLTree<T> {
    BinaryNode<T> root;

    public AVLTree(int key, T data){
        this.root.key=key;
        this.root.data=data;
    }

    public AVLTree(BinaryNode<T> root){
        this.root=root;
    }
    public AVLTree(){
        root=null;
    }
    
}
