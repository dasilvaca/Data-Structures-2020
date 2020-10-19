package classes.Sites;

import classes.*;
import structs.*;

public class mainPage extends Page{

    public User currentUser;

    public mainPage(User currentUser){
        this.currentUser = currentUser;
    }

    public Page display(DynamicArray<Project> Projects){

        System.out.println("|===========================================================================|");
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|       Welcome,"+currentUser.userName+"                                    |");
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|    Check your projects:                                                   |");
        System.out.println("|                                                                           |");
        if(this.currentUser.ownProjectList.isEmpty()){
            System.out.println("|   By now you have no active projects                                      |");
        }else{
            Node<Project> currentProject = this.currentUser.ownProjectList.Firstnode;
            while (currentProject != null){
                System.out.println("|         "+currentProject.data.name+"   "+currentProject.data.followers.size);
                System.out.println("|");
                System.out.println("|   -----------------------------------------------------------------   |");
                currentProject = currentProject.next;
            }
        }
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|     Do you want to log out?                                               |");
        System.out.print(  "|         ");
        String answer = input.nextLine();
        if (answer.equals("Y")){
            Page closePage = new Page();
            closePage.toyDentro = false;
            return closePage;
        }
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|     Do you want to edit your profile?                                     |");
        String response = input.nextLine();
        if(response.equals("Y")){
            return new editUserProfilePage(this.currentUser);
        }
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|     Do you want to create a new project?                                  |");
        if(input.nextLine().equals("Y")){
            System.out.println("|                                                                           |");
            System.out.println("|     How will you name your new project?                                   |");
            String newProjectName = input.nextLine();
            System.out.println("|                                                                           |");
            System.out.println("|     How much will be its budget?                                          |");
            int newProjectBudget = (int)input.nextInt();
            LinkedL<User> newProjectOwners = new LinkedL<User>();
            newProjectOwners.append(this.currentUser);
            this.currentUser.createProject(newProjectName, newProjectOwners, newProjectBudget);
            Project proyecto = new Project(newProjectName, this.currentUser, newProjectBudget);
            Projects.orderedAddProject(proyecto);
            System.out.println("|                                                                           |");
            System.out.println("|    Congratulations! You have successfully created a new project           |");
            System.out.println("|                                                                           |");
            System.out.println("|***************************************************************************|");
            System.out.println("|===========================================================================|");
            return new projectPage(this.currentUser.ownProjectList.Lastnode.data, this.currentUser);
        }
        return new searchPage(this.currentUser);

    }


}
