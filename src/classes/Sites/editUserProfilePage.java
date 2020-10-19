package classes.Sites;
import classes.*;

public class editUserProfilePage extends Page{
    
    public User currentUser;

    public editUserProfilePage(User cUser){
        this.currentUser = cUser;
    }

    public boolean edit(){
        System.out.println("|===========================================================================|");
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|    Do you want to change your username?                                   |");
        String answer = input.nextLine();
        if(answer.equals("Y")){
            System.out.println("|        Enter your new username                                               |");
            String newUsername = input.nextLine();
            this.currentUser.userName = newUsername;
        }
        System.out.println("|    Do you want to change your password?                                   |");
        answer = input.nextLine();
        if(answer.equals("Y")){
            boolean correctEdit = false;
            while(!correctEdit){
                System.out.println("|        Enter your current password:                                          |");
                String currentPassword = input.nextLine();
                System.out.println("|        Enter your new password:                                              |");
                String newPassword = input.nextLine();
                System.out.println("|        Confirm your new password:                                            |");
                String newPasswordConfirm = input.nextLine();
                if(newPassword.equals(newPasswordConfirm) && this.currentUser.password.equals(currentPassword)){
                    this.currentUser.password = newPassword;
                    correctEdit = true;
                } else{
                    System.out.println("|        You have done some error, please do it again                           |");
                }
            }
        }
        System.out.println("|    Have you finished?                                                     |");
        answer = input.nextLine();
        if(answer.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
}
