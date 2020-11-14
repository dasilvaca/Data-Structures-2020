package com.janus.janusapp.structs;

import com.janus.janusapp.classes.User;
import com.janus.janusapp.structs.*;

import java.util.Scanner;

class run {
    public static void main(String[] args){
        Trie<Object> newTrie = new Trie<Object>();
        User user1 = new User("Juan", "Bedoya", "jubedoyat@unal.edu.co", "jubedoyat",
                "7263516653", "bedoya123", /*LocalDate*/ "20/04/2002", "Male");
        newTrie.addWord(user1.username, user1);
        User user2 = new User("Daniel", "Silva", "dasilvaca@unal.edu.co", "dasilvaca",
                "7376257863", "silva456", /*LocalDate*/ "17/08/2000", "Male");
        newTrie.addWord(user2.username, user2);
        User user3 = new User("Jose", "Moreno", "jmorenoh@unal.edu.co", "jmoreno",
                "5243562434", "moreno789", /*LocalDate*/ "23/10/2001", "Male");
        newTrie.addWord(user3.username, user3);
        User user4 = new User("Diana", "Lizarazo", "dlizarazos@unal.edu.co", "dlizarazos",
                "6354526435", "dghhgdg", /*LocalDate*/ "02/06/2003", "Female");
        newTrie.addWord(user4.username, user4);
        User user5 = new User("Dalila", "Salazar", "dsalazarcor@unal.edu.co", "dsalazarcor",
                "4356341783", "yryryet", /*LocalDate*/ "15/03/2001", "Female");
        newTrie.addWord(user5.username, user5);

        Scanner input = new Scanner(System.in);

        for(int i=0; i<5; i++){
            String in = input.nextLine();
            if(newTrie.findWord(in)!=null){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }

        input.close();
    }
}
