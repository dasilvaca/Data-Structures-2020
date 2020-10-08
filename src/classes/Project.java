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
    String name;
    LinkedL owners=new LinkedL();
    LinkedL followers=new LinkedL();
    int budget;
    
    public Project(String name, LinkedL owners, int budg){
        this.name = name;
        this.owners = owners;
        this.budget = budg;
        
    }
    
    public void addFollower(User newFollower){
        followers.append(newFollower);
    }
    
    public void addOwner(User newOwner){
        owners.append(newOwner);
    }
    public String aString(){
        String s= "Name: "+name+" Owners: "+owners.aString()+" Followers: "+followers.aString()+" Budget: "+String.valueOf(budget);
        return s;
    }
}
