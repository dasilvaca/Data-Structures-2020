package com.janus.janusapp.structs;


public class TrieNode<T> {

    public char letter;
    public LinkedL<TrieNode<T>> childs;
    public T inObject;

    public TrieNode(char letter) {
        this.childs = new LinkedL<TrieNode<T>>();
        this.inObject = null;
        this.letter = letter;
    }

    public void addLetter(char newLetter) {
        int index = findInChilds(newLetter);
        if (index < 0) {
            TrieNode<T> newNode = new TrieNode<T>(newLetter);
            this.childs.append(newNode);
        }
    }

    public int findInChilds(char letterToFind) {
        Node<TrieNode<T>> current = childs.Firstnode;
        int i = 0;
        while (current != null) {
            if (current.data.letter == letterToFind) {
                return i;
            } else {
                current = current.next;
                i++;
            }
        }
        return -1;
    }

    public DynamicArray findValidChilds(DynamicArray currentArray){
        Node<TrieNode<T>> current = this.childs.Firstnode;
        while(current!=null && current.data!=null){
            if(current.data.inObject!=null){
                currentArray.append(current.data.inObject);
            }
            currentArray = current.data.findValidChilds(currentArray);
            current = current.next;
        }
        return currentArray;
    }
}
