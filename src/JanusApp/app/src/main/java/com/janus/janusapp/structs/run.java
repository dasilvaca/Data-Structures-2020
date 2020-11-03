package com.janus.janusapp.structs;

import com.janus.janusapp.structs.*;
class run {
    public static void main(String[] args){
        BSTree<String> tree= new BSTree<String>(5,"5");
        tree.Insert(6, "6");
        tree.Insert(1,"1");
        tree.Insert(9, "9");
        tree.Insert(2, "2");
        tree.Insert(7,"7");
        tree.Insert(8, "8");
        tree.Insert(3,"3");
        System.out.println(tree.toString());

    }
}
