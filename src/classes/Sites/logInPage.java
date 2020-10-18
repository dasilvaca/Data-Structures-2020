package classes.Sites;

import classes.*;
<<<<<<< HEAD

=======
>>>>>>> d3b0dc78488e754028bee83a080597b04994a1eb
import structs.DynamicArray;

public class logInPage extends Page{

    public User logIn(DynamicArray<User> Users){
        System.out.print("|===========================================================================|");
        System.out.print("|***************************************************************************|");
        System.out.print("|                                                                           |");
        System.out.print("|       Welcome!                                                            |");
        System.out.print("|       Is this your first time here?                                       |");
        String answer = input.nextLine();
        if ("Y".equals(answer)){
            signUpPage signup = new signUpPage();
            return signup.signUp();
        } else{
            System.out.println("|       Enter your username: ");
            String usnm = input.nextLine();
            System.out.print("|                                                                              |");
            System.out.println("|       Enter your password: ");
            String pssw = input.nextLine();
            while (Users.getUser(usnm, pssw)==null){
                System.out.println("|       Enter your username: ");
                usnm = input.nextLine();
                System.out.print("|                                                                              |");
                System.out.println("|       Enter your password: ");
                pssw = input.nextLine();
                if(Users.getUser(usnm, pssw)==null){
                    System.out.println("|          User or password are incorrect.                                        |");
                }
                System.out.print("|                                                                              |");
            }
            System.out.print("|                                                                           |");
            System.out.print("|***************************************************************************|");
            System.out.print("|===========================================================================|");
            return Users.getUser(usnm, pssw);
        }
    }

}
