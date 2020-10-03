//package Classes;
/* clase Queue (Cola)*/
public class Queue {
    // La clase Node permite guardar un dato individual.
    public class Node {
    /* El atributo data guarda el valor del dato que se quiera guardar sea del
       tipo que sea. Mientras que el atributo next apunta a la instancia de la
       clase Node inmediatamente posterior, lo que servirá para las colas. */
        public Object data;
    /* El atributo next se inicializa en null pues por defecto ningún nodo
       tiene otro nodo que sea el siguiente a él. */
        public Node next = null;
    
    /* Constructor de la clase: */
        public Node(Object dt){
            this.data = dt;
        }
    
        public void setNext(Node next){
            this.next = next;
        }
    }
    /* Cada cola tiene dos atributos: front que guarda el primer nodo de la
       cola, es decir el primer elemento de esta, y back que guarda el último.
       Ambos atributos se inicializan en null ya que al crear una cola esta
       estará vacía. */
    public Node front = null;
    public Node back = null;
    
    /*El método enqueue añade un elemento nuevo (newNode) a la cola */
    public void enqueue(Object newData){
        Node newNode = new Node(newData);
        if(this.front == null){
            /* Si la cola está vacia (if(this.front == null)), se les da a front 
               y a back el valor de newNode */
            this.front = this.back = newNode;
        } else{
            /* si no, al atributo next del nodo que está de último se le da
               el valor de newNode, y luego se hace lo mismo con el 
               atributo back de la cola */
            back.next = newNode;
            this.back = newNode;
        }
    }
    
    
    /* El método dequeue saca de la cola el primer elemento de esta (front) */
    public void dequeue(){
        this.front = front.next;
    }
    
    
    /* El método print recorre la cola e imprime cada uno de sus elementos */
    public void print(){
        Node curr = front;
        while(curr != null){
            if(curr != back){
                System.out.print(curr.data + " ");
            } else {
                System.out.println(curr.data);
            }
            curr = curr.next;
        }
    }
    
    
    /* El método len retorna la longitud de la cola, que es 0 si está vacía. */
    public int len(){
        Node curr = front;
        int len = 0;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        return len;
    }
    
    
    /* El método get retorna el dato que está guardado en la posición que
       indique el parámetro index. Estas posiciones están dadas de la misma
       forma que en un arreglo, desde 0 hasta n-1, donde n es la longitud de
       la cola. En caso de que index sea mayor o igual a n */
    public Object get(int index){
        /* la variable curr guarda el nodo que se está revisando. i guarda un
           entero que indica la posición de curr. Como se puede observar, i se
           inicializa en 0 pues curr se inicializa con el nodo al que apunte 
           front */
        Node curr = front;
        int i = 0;
        int n = this.len();
        /* En caso de que index sea mayor o igual a la longitud n de la cola
           el método devuelve de inmediato el mensaje nulo, pues la posición no 
           se ha podido encontrar */
        if(index>=n){
            return "nulo";
        }
        /* éste while tiene como condición que i sea menor o igual a index para
           saber cuando se ha llegado a la posición que indica index */
        while(i<=index){
            curr = curr.next;
            /* cada vez que se revisa un nodo, i se incrementa en 1, para
               indicar que se cambió de posición y a curr se le asigna su next
               cambiar la siguiente posición */
            i++;
        }
        /* Finalmente, si la posición solicitada es correcta, se retorna el dato
           que está guardado en esta posición */
        return curr.data;
    }
    
    
    /* El método find permite saber si un valor (dataToFind) se encuentra en la 
       cola y, de ser así, retornar su posición; si no se encuentra retorna -1 */
    public int find(Object dataToFind){
        /* al igual que en get, aquí curr guarda el nodo que se está revisando 
           e i la posición en que sencuentra ese nodo */
        Node curr = front;
        int i = 0;
        /*éste while tiene como condición que curr no sea igual a null, para que
          la posición siempre esté en el intervalo [0, n), y que el dato 
          guardado en el nodo que se está revisando no sea igual al dato que se
          busca */
        while(curr != null){
            if(curr.data == dataToFind){
                break;
            } else{
                i++;
                curr = curr.next;
            }
        }
        /* en caso de que curr sea null, es decir que se haya recorrido toda
           la cola y no se haya encontrado el dato, se retorna -1 */
        if(curr == null){
            return -1;
        } else{
            /* en caso contrario, se retorna la posición del nodo que contiene
               el dato*/
            return i;
        }
    }
}
