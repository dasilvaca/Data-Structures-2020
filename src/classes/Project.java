/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import structs.*;

/**
 *
 * @author usuario
 */
public class Project {
    public String name;
    LinkedL<User> owners=new LinkedL<User>();
    LinkedL<User> followers=new LinkedL<User>();
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
