#include "LinkedList.hpp"
#include <iostream>
typedef long long int ll;
using namespace std;

void print(Node<int>*node){
    while (node != NULL){
        cout<<node ->data <<endl;
        node = node -> next;
    }
}

int main(void){
    LinkedList<int> lista;
    for (int i = 0; i < 20; i++) lista.append(i);
    for (int i = 0; i < 20; i++) cout<<lista.get(i)<<endl;
    lista.erase(15);
    cout<<lista.indexOf(15)<<endl;
    for (int i = 0; i < 19; i++) cout<<lista.get(i)<<endl;
    return 0;
}