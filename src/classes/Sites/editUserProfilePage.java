package classes.Sites;
import classes.*;

public class editUserProfilePage extends Page{
    
    public User currentUser;

    public editUserProfilePage(User cUser){
        this.currentUser = cUser;
    }

    public boolean edit(){
        System.out.print("|===========================================================================|");
        System.out.print("|***************************************************************************|");
        System.out.print("|                                                                           |");
        System.out.print("|    Do you want to change your username?                                   |");
        String answer = input.nextLine();
        if(answer.equals("Y")){
            System.out.print("|        Enter your new username                                               |");
            String newUsername = input.nextLine();
            this.currentUser.userName = newUsername;
        }
        System.out.print("|    Do you want to change your password?                                   |");
        answer = input.nextLine();
        if(answer.equals("Y")){
            boolean correctEdit = false;
            while(!correctEdit){
                System.out.print("|        Enter your current password:                                          |");
                String currentPassword = input.nextLine();
                System.out.print("|        Enter your new password:                                              |");
                String newPassword = input.nextLine();
                System.out.print("|        Confirm your new password:                                            |");
                String newPasswordConfirm = input.nextLine();
                if(newPassword.equals(newPasswordConfirm) && this.currentUser.password.equals(currentPassword)){
                    this.currentUser.password = newPassword;
                    correctEdit = true;
                } else{
                    System.out.print("|        You have done some error, please do it again                           |");
                }
            }
        }
        System.out.print("|    Have you finished?                                                     |");
        answer = input.nextLine();
        if(answer.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
}
