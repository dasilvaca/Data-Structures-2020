package classes.Sites;

import classes.*;
import structs.*;

public class mainPage extends Page{

    User currentUser;

    public mainPage(User currentUser){
        this.currentUser = currentUser;
    }

    public Page display(){

        Node<Project> currentProject = this.currentUser.ownProjectList.Firstnode;
        System.out.print("|===========================================================================|");
        System.out.print("|***************************************************************************|");
        System.out.print("|                                                                           |");
        System.out.print("|       Welcome,"+currentUser.userName+"                                    |");
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|    Check your projects:                                                   |");
        System.out.print("|                                                                           |");
        while (currentProject != null){
            System.out.print("|         "+currentProject.data.name+"   "+currentProject.data.followers.size+"     |");
            System.out.print("|   -----------------------------------------------------------------   |");
            currentProject = currentProject.next;
        }
        
        return new searchPage(this.currentUser);

    }


}
