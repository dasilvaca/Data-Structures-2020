package classes.Sites;
import classes.*;

public class projectPage extends Page{
    
    Project currentProject;

    public projectPage(Project currentProject){
        this.currentProject = currentProject;
    }

    public Page showProject(){
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
        Page mnpg = this;
        while (! resp){
            System.out.println("Do you want to exit?");
            String answer = input.nextLine();
            if("Y".equals(answer)){
                mnpg = new mainPage();
                resp = true;
            }
        }
        return mnpg;
    }
}
