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
            System.out.println("|         "+currentProject.data.name+"   "+currentProject.data.followers.size);
            System.out.print("|");
            System.out.print("|   -----------------------------------------------------------------   |");
            currentProject = currentProject.next;
        }
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|     Do you want to edit your profile?                                     |");
        String response = input.nextLine();
        if(response.equals("Y")){
            return new editUserProfilePage(this.currentUser);
        }
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|     Do you want to create a new project?                                  |");
        if(input.nextLine().equals("Y")){
            System.out.print("|                                                                           |");
            System.out.print("|     How will you name your new project?                                   |");
            String newProjectName = input.nextLine();
            System.out.print("|                                                                           |");
            System.out.print("|     How much will be its budget?                                          |");
            int newProjectBudget = input.nextInt();
            LinkedL<User> newProjectOwners = new LinkedL<User>();
            newProjectOwners.append(this.currentUser);
            this.currentUser.createProject(newProjectName, newProjectOwners, newProjectBudget);
            System.out.print("|                                                                           |");
            System.out.print("|    Congratulations! You have successfully created a new project           |");
            System.out.print("|                                                                           |");
            System.out.print("|***************************************************************************|");
            System.out.print("|===========================================================================|");
            return new projectPage(this.currentUser.ownProjectList.Lastnode.data, this.currentUser);
        }
        return new searchPage(this.currentUser);

    }


}
