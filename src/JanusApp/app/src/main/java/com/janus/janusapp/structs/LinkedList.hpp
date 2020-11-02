#include "Nodes.hpp"


template<typename dataType>
class LinkedList{
    private:
        Node<dataType> *head = NULL;
    public:
        void append(dataType data){
            if (head == NULL){
                head = new Node<dataType>(data);
            }
            else{
                Node<dataType> *actualNode = head, *newNode = new Node<dataType>(data);
                while (actualNode->next != NULL) actualNode = actualNode->next;
                actualNode->next = newNode;
            }
        }
        dataType TopFront(){
            return head != NULL? head-> data: NULL;
        }
        // TODO: se debe controlar la salida de este metodo en caso de no encontrarlo
        dataType get(int index){
            Node<dataType> *actualNode = head;
            for(int i = 0; i < index; i++) actualNode = actualNode->next;
            return actualNode -> data;
        }

        bool find(dataType data){
            Node<dataType> *actualNode = head;
            while (actualNode != NULL){
                if (actualNode -> data == data) return true;
                actualNode = actualNode -> next;
            }
            return false;
        }

        int indexOf(dataType data){
            int index = 0;
            Node<dataType> *actualNode = head;
            while (actualNode != NULL){
                if (actualNode -> data == data) return index;
                actualNode = actualNode -> next;
                index ++;
            }
            return -1;  // se retorna -1 porque el valor de NULL por defecto es 0, que sería un posible indice
        }
        void erase(dataType data){
            if (head == NULL) return;
            Node<dataType> *actualNode = head;
            while (actualNode -> next != NULL){
                if (actualNode -> next ->data == data){
                    Node<dataType> *newNextNode = actualNode -> next -> next;
                    delete(actualNode -> next);
                    actualNode -> next = newNextNode;
                    return;
                }
                actualNode = actualNode -> next;
            }
        }

        void pop(){
            if (head == NULL) return;
            if (head -> next == NULL){
                head = NULL;
                return;
            } 
            Node<dataType> *actualNode = head;
            while (actualNode -> next != NULL){
                actualNode = actualNode -> next;
            }
            delete(actualNode -> next);
            actualNode -> next = NULL;
            return;
        }
    /*
        void pop(int index){
            if (head == NULL)
            Node<dataType> *actualNode = head;
            for(int i = 0; i + 1 < index; i++) actualNode = actualNode->next;
            return actualNode -> data;
        }*/
};
template<typename dataType>
class TailedLinkedList{
    public:
        Node<dataType> *head = NULL, *tail = NULL;



        void append(dataType data){
            if (head == NULL){
                head = new Node<dataType>(data);
            }
            else{
                Node<dataType> *actualNode, *newNode = new Node<dataType>(data);
                actualNode = head;
                while (actualNode->next != NULL) actualNode = actualNode->next;
                actualNode->next = newNode;
            }
        }

        dataType TopFront(){
            return head != NULL? head-> data: NULL;
        }

        dataType get(int index){
            Node<dataType> *actualNode = head;
            for(int i = 0; i < index; i++) actualNode = actualNode->next;
            return actualNode -> data;
        }

};

