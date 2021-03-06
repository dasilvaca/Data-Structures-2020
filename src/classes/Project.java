/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import structs.*;

//import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Project {
    public String name;
    public DynamicArray<String> owners=new DynamicArray<String>();
    public DynamicArray<String> followers=new DynamicArray<>();
    public int budget;
    String category;
    String description;
    public Project(String name, DynamicArray<String> owners, int budg,String ctgr,String dscpt){
        this.name = name;
        this.owners = owners;
        this.budget = budg;
        this.category=ctgr;
        this.description=dscpt;
        
    }
    public Project(String name,User owner, int budg,String ctgr,String dscpt){
        this.name = name;
        this.budget = budg;
        this.addOwner(owner);
        this.category=ctgr;
        this.description=dscpt;
    }
    public Project(String name, int budg,String ctgr,String dscpt){
        this.name = name;
        this.budget = budg;
        this.category=ctgr;
        this.description=dscpt;
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


}
