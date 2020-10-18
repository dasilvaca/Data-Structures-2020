package classes.Sites;
import classes.*;

public class projectPage extends Page{
    
    Project currentProject;
    User currentUser;

    public projectPage(Project currentProject, User currentUser){
        this.currentUser = currentUser;
        this.currentProject = currentProject;
    }

    public boolean showProject(){
        System.out.print("|===========================================================================|");
        System.out.print("|***************************************************************************|");
        System.out.print("|      "+currentProject.name+"                                              |");
        System.out.print("|                                                                           |");
        System.out.print("|                                                                           |");
        System.out.print("|  Owners:                                                                  |");
        System.out.print("|     "+currentProject.owners.toString()+"");
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|                                                                           |");
        System.out.print("|  Followers:                                                               |");
        System.out.print("|     "+currentProject.followers.size+"                                     |");
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|                                                                           |");
        System.out.print("|  Budget:                                                                  |");
        System.out.print("|     "+currentProject.budget+"                                             |");
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|===========================================================================|");

        boolean resp = false;
        while (! resp){
            System.out.println("Do you want to exit?");
            String answer = input.nextLine();
            if("Y".equals(answer)){
                resp = true;
            }else{
                continue;
            }
        }
        return resp;
    }
}
