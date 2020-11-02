package com.janus.janusapp.structs;

/*
 * este codigo se creo tomando como referencia la informacion de los siguientes enlaces:
 * https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * https://campus.virtual.unal.edu.co/pluginfile.php/1849395/mod_resource/content/1/_ab22a7db1ab0493ec309b0761e451b18_08_binary_search_trees_6_avl2.pdf
 *
 */
class AVLTree<T>
{
    BinaryNode<T> root; //aqui se crea el nodo raiz de nuestro arbol

    public BinaryNode<T> rotateWithLeftChild(BinaryNode<T> pivoteNode) // Funcion de rotacion a la izquierda tomando como pivote el nodo padre
    {
        /*
        lo que hacemos en esta funcion es tomar un Nodo padre el cual queremos rotar con el
        hijo izquierdo.
        */
        BinaryNode<T> childPivoteNode = pivoteNode.right; //se define su nodo hijo derecho con el que se realizara el cambio
        BinaryNode<T> treeChildPivoteNode = childPivoteNode.left; // se define el subarbol izquierdo del nodo hijo

        childPivoteNode.left = pivoteNode; // ahora el nodo pivote pasa a ser el hijo izquierdo del nodo con el que cambiamos (su antiguo hijo)
        pivoteNode.right = treeChildPivoteNode; // y la parte izquierda del antiguo hijo paso a ser la rama derecha de nuestro pivote.

        return childPivoteNode; // uniendolo con el resto del codigo el ex hijo del pivote pasa a tomar el espacio donde estaba el pivote.
    }
    public BinaryNode<T> rotateWithRightChild(BinaryNode<T> pivoteNode)
    {
        /*
        lo que hacemos en esta funcion es tomar un Nodo padre el cual queremos rotar con el
        hijo derecho.
        */
        BinaryNode<T> childPivoteNode = pivoteNode.left; //se define su nodo hijo izquierdo con el que se realizara el cambio
        BinaryNode<T> treeChildPivoteNode = childPivoteNode.right; // se define el subarbol derecho del nodo hijo

        childPivoteNode.right = pivoteNode; // ahora el nodo pivote pasa a ser el hijo derecho del nodo con el que cambiamos (su antiguo hijo)
        pivoteNode.left = treeChildPivoteNode; // y la parte derecha del antiguo hijo paso a ser la rama izquierda de nuestro pivote.

        return childPivoteNode; // uniendolo con el resto del codigo el ex hijo del pivote pasa a tomar el espacio donde estaba el pivote.
    }

    public BinaryNode<T> rotateDoubleWithLeftChild(BinaryNode<T> tempUperNode)
    {
        tempUperNode.right = rotateWithRightChild(tempUperNode.right); // tomando como pivote el el hijo derecho del nodo que esta mas arriba rotamos a la derecha
        return rotateWithLeftChild(tempUperNode); // luego tomamos como pivote el nodo que esta mas arriba en el arbol y rotamos a la izquierda
    }

    public BinaryNode<T> rotateDoubleWithRightChild(BinaryNode<T> tempUperNode)
    {
        tempUperNode.left = rotateWithLeftChild(tempUperNode.left); // tomando como pivote el el hijo derecho del nodo que esta mas arriba rotamos a la izquierda
        return rotateWithRightChild(tempUperNode); // luego tomamos como pivote el nodo que esta mas arriba en el arbol y rotamos a la izquierda
    }

    public int heightNodeTree(BinaryNode<T> tempNode)
    {
        //Esta funcion busca la altura del sub arbol desde el Nodo tempNode.
        if (tempNode == null)
            return 0;
        else
        {
            int lHeight = heightNodeTree(tempNode.left); //se usa como funcion recursiva para hallar la altura del sub arbol izquierdo del nodo
            int rHeight = heightNodeTree(tempNode.right);  //se usa como funcion recursiva para hallar la altura del sub arbol derecho del nodo
            // se suma uno ya que el primer nodo no se esta tomando en cuenta, pero sabemos que existe.
            if (lHeight > rHeight)  // se compara la altura de los sub arboles para retornar el mas alto + 1;
                return (lHeight + 1);
            else
                return (rHeight + 1);
        }
    }

    public void insert(int key)
    {
        root = insert(key, root, null); // esta funcion se usa para llamar otra funcion pero iniciando el tempNode en root
    }

    public BinaryNode<T> insert(int key, BinaryNode<T> tempNode, BinaryNode<T> father)
    {
        if (tempNode == null)
            return (new BinaryNode(key, father));
        else if (key < tempNode.key)
            tempNode.left = insert(key, tempNode.left, tempNode);
        else if (key > tempNode.key)
            tempNode.right = insert(key, tempNode.right, tempNode);
        else // Duplicate keys not allowed
            return tempNode;

        if (getBalance(tempNode) > 1 && key > tempNode.right.key)
            return rotateWithLeftChild(tempNode);
        if (getBalance(tempNode) < -1 && key < tempNode.left.key)
            return rotateWithRightChild(tempNode);
        if (getBalance(tempNode) > 1 && key < tempNode.right.key)
            return rotateDoubleWithLeftChild(tempNode);
        if (getBalance(tempNode) < -1 && key > tempNode.left.key)
            return rotateDoubleWithRightChild(tempNode);

        return tempNode;
    }

    public BinaryNode<T> find(int key)
    {
        if(findWithBoolean(key, root))
        {
            System.out.println("Se encontro la key:" +key+ " se le retornara su nodo");
            return find(key, root);
        }
        else
        {
            System.out.println("No se encontro la key:" +key+ " se le retornara el nodo root");
            return root;
        }
    }

    public BinaryNode<T> find(int key, BinaryNode<T> tempNode)
    {
        if(tempNode.key == key)
            return tempNode;
        else if(tempNode.key < key)
            return find(key, tempNode.right);
        else if(tempNode.key > key)
            return find(key, tempNode.left);
        return tempNode;
    }

    public boolean findWithBoolean(int key, BinaryNode<T> tempNode)
    {
        if(tempNode == null)
            return false;
        else
        {
            if(tempNode.key == key)
                return true;
            else if(tempNode.key < key)
                return findWithBoolean(key, tempNode.right);
            else if(tempNode.key > key)
                return findWithBoolean(key, tempNode.left);
        }
        return false;
    }

    void preOrder(BinaryNode<T> tempNode)
    {
        if (tempNode != null)
        {
            System.out.print(tempNode.key + " ");
            preOrder(tempNode.left);
            preOrder(tempNode.right);
        }
    }

    public int getBalance(BinaryNode<T> tempNode)
    {
        if (tempNode == null)
            return 0;
        return heightNodeTree(tempNode.right) - heightNodeTree(tempNode.left);
    }
}
