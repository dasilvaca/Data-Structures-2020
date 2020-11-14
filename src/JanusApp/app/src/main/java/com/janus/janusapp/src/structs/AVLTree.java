package structs;

/**
 *
 * @author ANDRES
 */

/*
 * este codigo se creo tomando como referencia la informacion de los siguientes enlaces:
 * https://campus.virtual.unal.edu.co/pluginfile.php/1849395/mod_resource/content/1/_ab22a7db1ab0493ec309b0761e451b18_08_binary_search_trees_6_avl2.pdf
 * https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * https://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 */
public class AVLTree<T>
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

    public void insertNode(int key) // esta funcion se usa para llamar otra funcion pero iniciando el tempNode en root
    {
        if(!findWithBoolean(key, root)) // solo si el nodo no existe, se inserta
            root = insertNode (key, root, null); //la funcion de insert devuelve el root, pero con la modificacion de la insercion y el re balanceo.
    }

    public BinaryNode<T> insertNode(int key, BinaryNode<T> tempNode, BinaryNode<T> father)
    {
        if (tempNode == null) // si llegamos al fondo del nodo insertamos el nuevo nodo
            return (new BinaryNode(key, father)); //en esta linea se inserta el nodo y se retorna ese nuevo nodo
        else if (key < tempNode.key) //si no estamos en el fondo del arbol, entonces nos vamos por el subarbol izquierdo
            tempNode.left = insertNode(key, tempNode.left, tempNode); //de manera recursiva se recorre nodo hijo izquierdo del nodo actual
        else if (key > tempNode.key) //si no estamos en el fondo del arbol, entonces nos vamos por el subarbol derecho
            tempNode.right = insertNode(key, tempNode.right, tempNode); //de manera recursiva se recorre nodo hijo derecho del nodo actual
        else // si no pasa nada de lo anterior quiere decir que el nodo del key ya existe en el arbol, entonces retorna el nodo en el que se encuentra
            return tempNode;
        // ahora revisamos si tiene un desvalance por izquierda (-n) o por derecha(n);
        // y ademas revisa si el hijo dependiendo del caso esta con (-1 o menor) o (1 o mayor) para realizar una doble rotacion
        if (getBalance(tempNode) > 1 && key > tempNode.right.key)
            return rotateWithLeftChild(tempNode);
        if (getBalance(tempNode) < -1 && key < tempNode.left.key)
            return rotateWithRightChild(tempNode);
        if (getBalance(tempNode) > 1 && key < tempNode.right.key)
            return rotateDoubleWithLeftChild(tempNode);
        if (getBalance(tempNode) < -1 && key > tempNode.left.key)
            return rotateDoubleWithRightChild(tempNode);
        //en los 4 anteriores if si se realizo alguna rotacion, se devuelve el nodo pivote con la rotacion respectiva.
        return tempNode; //si no se realiza ninguna rotacion se devuelve el nodo sin cambios.
    }

    public void deleteNode(int key) // ua funcion para iniciar la funcion delete real en root
    {
        if(findWithBoolean(key, root)) // si el nodo existe entonces se lleva a cabo delete.
            root = deleteNode(key, root); //la funcion retorna el root pero con la modificacion del nodo eliminado y re balanceado.
        else //si no, entonces imprimimos que el nodo no existe.
            System.out.println("No se encuentra la key: " + key
                    + " por lo cual no se puede borra el nodo");
    }

    public BinaryNode<T> deleteNode(int key, BinaryNode<T> tempNode)
    {  // recuerde que ya habiamos validado que el nodo existia en el arbol
        if (key < tempNode.key)//si no estamos en el nodo que debemos eliminar y el key es menor al key del nodo actual
            tempNode.left = deleteNode(key, tempNode.left); //usamos la funcion de manera recursiva para seguir bajando en el arbol por la izquierda
        else if (key > tempNode.key) //si no estamos en el nodo que debemos eliminar y el key es mayor al key del nodo actual
            tempNode.right = deleteNode(key, tempNode.right); //usamos la funcion de manera recursiva para seguir bajando en el arbol por la derecha
        else //aqui es cuando llegamos al nodo que debemos eliminar
        {
            if ((tempNode.left == null) || (tempNode.right == null)) // si el nodo a eliminar no tiene uno de los hijos (derecho o izquierdo) o ambos.
                if (tempNode.left == null) // si el nodo que falta es el hijo izquierdo
                    tempNode = tempNode.right; // entonces sobreEscribimos el nodo a eliminar con su hijo derecho
                else if(tempNode.right == null) // si el nodo que falta es el hijo derecho
                    tempNode = tempNode.left;  // entonces sobreEscribimos el nodo a eliminar con su hijo izquierdo
                else // si no tiene ningun hijo.
                    tempNode = null; // entoces solo eliminamos el nodo subreEscribiendo con null.
            else // si tiene sus 2 hijos (derecho e izquierdo)
            {
                BinaryNode<T> temp = minValueNode(tempNode.right); //se asigna un temp en el cual se guardara el hijo mas pequeño del nodo a eliminar
                tempNode.key = temp.key; //el cual ocupa si pocision, aqui lo que hicimos fue pasar el key
                tempNode.data = temp.data; //y el data de este nodo al nodo a eliminar.
                tempNode.right = deleteNode(temp.key, tempNode.right); // y luego eliminamos el nodo que pasamos arriba
            }
        }
        // ahora revisamos si tiene un desvalance por izquierda (-n) o por derecha(n);
        // y ademas revisa si el hijo dependiendo del caso esta con (-1 o menor) o (1 o mayor) para realizar una doble rotacion
        if (getBalance(tempNode) > 1 && getBalance(tempNode.right) <= 0)
            return rotateWithLeftChild(tempNode);
        if (getBalance(tempNode) < -1 && getBalance(tempNode.left) >= 0)
            return rotateWithRightChild(tempNode);
        if (getBalance(tempNode) > 1 && getBalance(tempNode.right) < 0)
            return rotateDoubleWithLeftChild(tempNode);
        if (getBalance(tempNode) < -1 && getBalance(tempNode.left) > 0)
            return rotateDoubleWithRightChild(tempNode);
        //en los 4 anteriores if si se realizo alguna rotacion, se devuelve el nodo pivote con la rotacion respectiva.
        return tempNode; //si no se realiza ninguna rotacion se devuelve el nodo sin cambios.
    }

    BinaryNode<T> minValueNode(BinaryNode<T> tempNode)
    {
        while (tempNode.left != null) // esto se hace para llegar al hijo mas pequeño del arbol que nos ingresan
            tempNode = tempNode.left;
        return tempNode; // y se devuelve ese nodo hijo mas pequeño.
    }

    public BinaryNode<T> find(int key) // esta es la primera funcion find donde verificamos si el nodo existe o no y devolvemos un nodo
    {
        if(findWithBoolean(key, root)) // si el nodo existe, imprimimos que existe y devolvemos el nodo
        {
            System.out.println("Se encontro la key:" +key+ " se le retornara su nodo");
            return find(key, root); // ademas en esta linea se inicializa la funcion recursiva en la raiz
        }
        else //si no existe, le decimos al usuario que no existe y retornamos la raiz del arbol.
        {
            System.out.println("No se encontro la key:" +key+ " se le retornara el nodo root");
            return root;
        }
    }

    public BinaryNode<T> find(int key, BinaryNode<T> tempNode) //en esta funcion, ya que antes verificamos que el nodo existia
    {
        if(tempNode.key == key) // si el key del nodo temp es igual al que estamos buscando, entonces devolvemos ese nodo.
            return tempNode;
        else if(tempNode.key < key) //si es un key mayor el que buscamos al key del temp
            return find(key, tempNode.right); //usamos la funcion de manera recursiva para buscar en su sub arbol derecho
        else //si es un key menor el que buscamos al key del temp (se pone else por que no hay mas posibidades para el key)
            return find(key, tempNode.left); //usamos la funcion de manera recursiva para buscar en su sub arbol izquierdo
    }

    public boolean findWithBoolean(int key, BinaryNode<T> tempNode) //esta funcion es para saber si existe o no el nodo en el arbol y retorna un booleano
    {
        if(tempNode == null) // si llegamos al fondo del arbol y no encontramos la key, significa que no esta en el arbol
            return false;
        else
        {
            if(tempNode.key == key) // aqui es cuando encontramos el nodo y devolvemos el true
                return true;
            else if(tempNode.key < key) // aqui decidimos si nos vamos por el sub arbol derecho usando recursividad
                return findWithBoolean(key, tempNode.right);
            else if(tempNode.key > key) // aqui decidimos si nos vamos por el sub arbol izquierdo usando recursividad
                return findWithBoolean(key, tempNode.left);
        }
        return false; // este return es solo un default por si algo pasa y que nos devuelva falso.
    }

    void preOrder(BinaryNode<T> tempNode) // esta funcion imprime el arbol desde la raiz viajando por sus sub arboles desde los mas pequeños hacia las hojas con key mas alta.
    {
        if (tempNode != null) // si no se a salido del arbol, entonces
        {
            System.out.print(tempNode.key + " "); //imprime el key del nodo actual
            preOrder(tempNode.left);  //recorremos primero toda la parte izquierda del subarbol
            preOrder(tempNode.right);  //luego recorremos toda la parte derecha.
        }
    }

    void inOrder(BinaryNode<T> tempNode)
    {
        if (tempNode != null) // si no se a salido del arbol, entonces
        {
            inOrder(tempNode.left);  //recorremos primero toda la parte izquierda del subarbol
            System.out.print(tempNode.key + " "); //imprime el key del nodo actual
            inOrder(tempNode.right);  //luego recorremos toda la parte derecha.
        }
    }

    public int getBalance(BinaryNode<T> tempNode)// aqui sabemos si el arbol esta o no balanceado
    {
        if (tempNode == null) // si salimos del arbol, entonces retornamos 0
            return 0;
        return heightNodeTree(tempNode.right) - heightNodeTree(tempNode.left); // se busca la diferencia de la altura de la derecha - la izquierda
    }
}
