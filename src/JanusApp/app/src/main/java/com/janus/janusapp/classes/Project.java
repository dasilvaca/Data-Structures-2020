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
    public LinkedL<User> owners=new LinkedL<User>();
    public LinkedL<User> followers=new LinkedL<User>();
    public int budget;
    
    public Project(String name, LinkedL<User> owners, int budg){
        this.name = name;
        this.owners = owners;
        this.budget = budg;
        
    }
    public Project(String name,User owner, int budg){
        this.name = name;
        this.budget = budg;
        this.addOwner(owner);
    }
    
    public void addFollower(User newFollower){
        followers.append(newFollower);
        newFollower.followedProjects.append(this);
    }
    
    public void addOwner(User newOwner){
        owners.append(newOwner);
        newOwner.ownProjectList.append(this);
    }
    @Override
    public String toString(){
        String s= "Name: "+name+" Owners: "+owners.toString()+" Followers: "+followers.toString()+" Budget: "+String.valueOf(budget);
        return s;
    }


}
