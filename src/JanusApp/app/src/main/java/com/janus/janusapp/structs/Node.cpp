#include <cstdlib>

template <typename dataType>

class Node{
    public:
        dataType data;
        Node* next;
        
    /*
    private:
        T data;
        Node* next = NULL;
        
    public:
        T getData(){
            return this->data;
        }
        Node getNext(){
            return this->next;
        }
        Node(T data){
            this->data = data;
        }
        setNext(Node<T> next){
            this->next = next;
        }
        */
};