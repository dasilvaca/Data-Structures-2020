package classes.Sites;

import classes.*;
import structs.DynamicArray;

import java.util.Scanner;

public class logInPage extends Page{
    
    Scanner input = new Scanner(System.in);

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
            if(Users.getUser(usnm, pssw)==null){
                System.out.println("User or password are incorrect");
            }else{
                return Users.getUser(usnm, pssw);
            }
            System.out.print("|                                                                              |");

            /** Aquí va la validación de usuario; se supone que luego de que el usuario entrega sus creden-
             *  ciales, el método determina si existe o no un usuario con esos mismos username y contraseña,
             *  y de ser así  retorna dicho usuario para luego ser utilizado en una mainPage:
             * 
             * return currentUser;
             * 
             * (en la función main se verá: 
             * 
             * mainPage mpage = new mainPage(currentUser);
             * mpage.display();
             * 
             * )
             */

        }
        return null;
    }

}
