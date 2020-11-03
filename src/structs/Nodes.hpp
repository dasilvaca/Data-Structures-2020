#include <cstdlib>

template <typename dataType>
class Node{
    public:
        dataType data;
        Node* next;
        Node(dataType data){
            this -> data = data;
            this -> next = NULL;
        }
};

/*template <typename dataType>
class DoubleNode{
    public:
        dataType data;
        DoubleNode* next;
        DoubleNode* prev;
};*/

class TreeNode{

};