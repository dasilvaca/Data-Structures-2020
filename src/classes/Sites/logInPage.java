package classes.Sites;

import classes.*;
import structs.DynamicArray;

public class logInPage extends Page{

    public mainPage logIn(DynamicArray<User> Users){
        System.out.println("|===========================================================================|");
        System.out.println("|***************************************************************************|");
        System.out.println("|                                                                           |");
        System.out.println("|       Welcome!                                                            |");
        System.out.println("|       Is this your first time here?                                       |");
        String answer = input.nextLine();
        if ("Y".equals(answer)){
            signUpPage signup = new signUpPage();
            return signup.signUp(Users);
        } else{
            System.out.println("|       Enter your username: ");
            String usnm = input.nextLine();
            System.out.println("|                                                                              |");
            System.out.println("|       Enter your password: ");
            String pssw = input.nextLine();
            while (Users.getUser(usnm, pssw)==null){
                System.out.println("|       Enter your username: ");
                usnm = input.nextLine();
                System.out.println("|                                                                              |");
                System.out.println("|       Enter your password: ");
                pssw = input.nextLine();
                if(Users.getUser(usnm, pssw)==null){
                    System.out.println("|          User or password are incorrect.                                        |");
                }
                System.out.println("|                                                                              |");
            }
            System.out.println("|                                                                           |");
            System.out.println("|***************************************************************************|");
            System.out.println("|===========================================================================|");
            return new mainPage(Users.getUser(usnm, pssw));
        }
    }

}
