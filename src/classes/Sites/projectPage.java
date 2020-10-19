package classes.Sites;
import classes.*;
import structs.*;

public class projectPage extends Page{
    
    Project currentProject;
    User currentUser;

    public projectPage(Project currentProject, User currentUser){
        this.currentUser = currentUser;
        this.currentProject = currentProject;
    }

    public boolean showProject(){
        System.out.println("|===========================================================================|");
        System.out.println("|***************************************************************************|");
        System.out.println("|      "+currentProject.name+"                                              |");
        System.out.println("|                                                                           |");
        System.out.println("|                                                                           |");
        System.out.println("|  Owners:                                                                  |");
        Node<User> currentOwner = this.currentProject.owners.Firstnode;
        while(currentOwner != null){
            System.out.println("|          "+currentOwner.data.userName);
            currentOwner = currentOwner.next;
        }
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|  Followers:                                                               |");
        System.out.println("|     "+currentProject.followers.size+"                                     |");
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|  Budget:                                                                  |");
        System.out.println("|     $"+currentProject.budget+"                                            |");
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|===========================================================================|");
        System.out.println("|                                                                           |");
        System.out.println("|   Dou you want to edit this project?                                      |");
        String answer = input.nextLine();
        if(answer.equals("Y")){
            this.editProject();
        }
        System.out.println("|                                                                           |");
        System.out.println("|   Dou you want to delete this project?                                    |");
        if(input.nextLine().equals("Y")){
            this.deleteProject();
            return true;
        }
        boolean resp = false;
        while (! resp){
            System.out.println("|   Do you want to exit?");
            String ans = input.nextLine();
            if("Y".equals(ans)){
                resp = true;
            }else{
                continue;
            }
        }
        return resp;
    }

    public void editProject(){
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|  Do you want to change your project's name?                               |");
        if(input.nextLine().equals("Y")){
            String newName = input.nextLine();
            this.currentProject.name = newName;
        }
        System.out.println("|                                                                           |");
        System.out.println("|  Do you want to change your project's budget?                             |");
        if(input.nextLine().equals("Y")){
            int newBudget = input.nextInt();
            this.currentProject.budget = newBudget;
        }
        System.out.println("|                                                                           |");
        System.out.println("|  Do you want to change your project's owners?                             |");
        if(input.nextLine().equals("Y")){
            System.out.println("|                                                                           |");
            System.out.println("|  Do you want to remove?                                                   |");
            String response = input.nextLine();
            if(response.equals("Y")){
                boolean correctRemoving = false;
                while(! correctRemoving){
                    System.out.println("|                                                                           |");
                    System.out.println("|  which owner do you want to remove?                                       |");
                    String ownerToRemove = input.nextLine();
                    Node<User> currentOwner = this.currentProject.followers.Firstnode;
                    int index = 0;
                    while(currentOwner != null && currentOwner.data.userName.equals(ownerToRemove)){
                        index++;
                        currentOwner = currentOwner.next;
                    }
                    if(currentOwner != null){
                        this.currentProject.owners.remove(index);
                        correctRemoving = true;
                    }
                }
            }
        }
    }

    public void deleteProject(){
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|  Are you sure you want to delete this project?                            |");
        if(input.nextLine().equals("Y")){
            this.currentUser.ownProjectList.remove(this.currentUser.ownProjectList.indexOf(this.currentProject));
        }
    }
}
