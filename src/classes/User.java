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
    String userName;
    String password;
    String birthDate;
    String email;
    LinkedL ownProjectList;
    LinkedL followedProjects;
    
    public User(String name, String password, String birthdate, String email){
        this.userName = name;
        this.password = password;
        this.birthDate = birthdate;
        this.email = email;
    }
    
    public void followProject(Object project){
        followedProjects.append(project);
    }
    
    public void createProject(String name, LinkedL owns, int bdgt){
        Project newProject = new Project(name, owns, bdgt);
        ownProjectList.append(newProject);
    }
}
