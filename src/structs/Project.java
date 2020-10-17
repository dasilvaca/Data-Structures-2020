/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserClass;

/**
 *
 * @author usuario
 */
public class Project {
    String name;
    LinkedL owners;
    LinkedL followers;
    int budget;
    LinkedL objectives;
    
    public Project(String name, LinkedL owners, int budg, LinkedL objevs){
        this.name = name;
        this.owners = owners;
        this.budget = budg;
        this.objectives = objevs;
        
    }
    
    public void addFollower(User newFollower){
        followers.append(newFollower);
    }
    
    public void addOwner(User newOwner){
        owners.append(newOwner);
    }
    
}
