/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import structs.*;

/**
 *
 * @author usuario
 */
public class User {
    public String userName;
    public String password;
    String birthDate;
    String email;
    LinkedL<Project> ownProjectList=new LinkedL<Project>();
    LinkedL<Project> followedProjects=new LinkedL<Project>();
    LinkedL<String> saveOwnProjectList=new LinkedL<String>();
    LinkedL<String> saveFollowedProjects=new LinkedL<String>();
    
    public User(String name, String password, String birthdate, String email){
        this.userName = name;
        this.password = password;
        this.birthDate = birthdate;
        this.email = email;
    }
    public void toSave(){
        Node<Project> iterador= ownProjectList.Firstnode;
        if(!ownProjectList.isEmpty()){
            while(iterador!=null){
                saveOwnProjectList.append(iterador.data.name);
                iterador=iterador.next;
            }    
        ownProjectList.makeEmpty();
        }
        iterador=followedProjects.Firstnode;
        if(!followedProjects.isEmpty()){
            while(iterador!=null){
                saveFollowedProjects.append(iterador.data.name);
                iterador=iterador.next;
            }
        followedProjects.makeEmpty();
        }
    }
    
    public void followProject(Project project){
        followedProjects.append(project);
        project.followers.append(this);
    }
    
    public void createProject(String name, LinkedL<User> owns, int bdgt){
        Project newProject = new Project(name, owns, bdgt);
        ownProjectList.append(newProject);
    }

    public boolean validateUsers(User userToValidate){
        if(this.userName.equals(userToValidate.userName) && this.password.equals(userToValidate.password)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public String toString(){
        String s= "Username: "+userName+" Password: "+password+" Birthdate: "+birthDate+" Email: "+email
        +" Projects: "+ownProjectList.toString()+" Followed: "+followedProjects.toString();
        return s;
    }
}
