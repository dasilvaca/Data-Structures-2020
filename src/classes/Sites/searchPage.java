package classes.Sites;

import classes.*;
import structs.*;

public class searchPage extends Page{
    
    User currentUser;

    public searchPage(User cuser){
        this.currentUser = cuser;
    }

    public Page display(){
        System.out.print("|     Which project dou you want to see in detail?                           |");
        String projectoToFind = input.nextLine();

        Node<Project> currentProject = this.currentUser.ownProjectList.Firstnode;
        while (currentProject != null && currentProject.data.name.equals(projectoToFind)){
            currentProject = currentProject.next;
        }
        if (currentProject == null){
            return new mainPage(currentUser);
        } else{
            projectPage foundProjectPage = new projectPage(currentProject.data, currentUser);
            return foundProjectPage;
        }
    }
}
