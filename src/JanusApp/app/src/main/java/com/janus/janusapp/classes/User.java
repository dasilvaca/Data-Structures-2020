/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.janus.janusapp.classes;

import com.janus.janusapp.structs.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author usuario
 */
public class User implements Serializable {
    public String userName;
    public String FirstName;
    public String LastName;
    public String password;
    LocalDate birthDate;
    String email;
    public LinkedL<String> ownProjectList = new LinkedL<String>();
    public LinkedL<String> followedProjects = new LinkedL<String>();

//Poner todos los atributos en el constructor String FirstName, String LastName,
    public User( String userName, String password, LocalDate birthdate, String email) {
        //this.FirstName = FirstName;
        //this.LastName = LastName;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthdate;
        this.email = email;
    }

    public User() {

    }


    public void followProject(Project project) {
        followedProjects.append(project.name);
        project.followers.append(this.userName);
    }

    public void createProject(String name, LinkedL<String> owns, int bdgt, String ctgr, String dscpt) {
        Project newProject = new Project(name, owns, bdgt, ctgr, dscpt);
        ownProjectList.append(newProject.name);
    }

    public boolean validateUsers(User userToValidate) {
        if (this.userName.equals(userToValidate.userName) && this.password.equals(userToValidate.password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "Username: " + userName + " Password: " + password + " Birthdate: " + birthDate + " Email: " + email
                + " Projects: " + ownProjectList.toString() + " Followed: " + followedProjects.toString();
        return s;
    }
}
