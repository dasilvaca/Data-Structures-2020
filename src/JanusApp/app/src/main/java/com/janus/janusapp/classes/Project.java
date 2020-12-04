/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.janus.janusapp.classes;

import com.janus.janusapp.structs.*;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Project implements Serializable {
    public String name;
    public DynamicArrayS owners=new DynamicArrayS();
    public DynamicArrayS followers=new DynamicArrayS();
    public int progress;
    public int budget;
    public String category;
    public String description;
    public String picture;

    public Project(String name, DynamicArrayS owners, int budg,String ctgr,String dscpt){
        this.name = name;
        this.owners = owners;
        this.budget = budg;
        this.category=ctgr;
        this.description=dscpt;
        this.progress = 0;
    }
    public Project(){

    }
    public Project(String name,User owner, int budg,String ctgr,String dscpt){
        this.name = name;
        this.budget = budg;
        this.addOwner(owner);
        this.addFollower(owner);
        this.category=ctgr;
        this.description=dscpt;
        this.progress = 0;
    }
    public Project(String name, int budg,String ctgr,String dscpt){
        this.name = name;
        this.budget = budg;
        this.category=ctgr;
        this.description=dscpt;
        this.progress = 0;
    }
    
    public void addFollower(User newFollower){
        followers.append(newFollower.username);
        newFollower.followedProjects.append(this.name);
    }
    
    public void addOwner(User newOwner){
        owners.append(newOwner.username);
        newOwner.ownProjectList.append(this.name);
    }
    @Override
    public String toString(){
        String s= "Name: "+name+" Owners: "+owners.toString()+" Followers: "+followers.toString()+" Budget: "+String.valueOf(budget);
        return s;
    }

    public void invest(User investor, int donation){
        investor.wallet-=donation;
        this.progress+=donation;
    }

    public void changeOwnerName(String oldName, String newName){

    }
}
