package com.janus.janusapp.structs;


public class TrieNode<T>{

    public char letter;
    public LinkedL<TrieNode<T>> childs;
    public T inObject;

    public TrieNode(char letter){
        this.childs = new LinkedL<TrieNode<T>>();
        this.inObject = null;
        this.letter = letter;
    }

    public void addLetter(char newLetter){
        int index = findInChilds(newLetter);
        if(index<0){
            TrieNode<T> newNode = new TrieNode<T>(newLetter);
            this.childs.append(newNode);
        }
    }

    public int findInChilds(char letterToFind){
        Node<TrieNode<T>> current = childs.Firstnode;
        int i = 0;
        while(current!=null){
            if(current.data.letter==letterToFind){
                return i;
            } else{
                current = current.next;
                i++;
            }
        }
        return -1;
    }
}
