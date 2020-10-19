package classes.Sites;

import classes.*;
import structs.*;

public class signUpPage extends Page{

    public mainPage signUp(DynamicArray<User> users){

        System.out.println("|===========================================================================|");
        System.out.println("|***************************************************************************|");
        System.out.println("|      |- Welcome to Janus! -|                                              |");
        System.out.println("|                                                                           |");
        System.out.println("|   Please enter an username: ");
        String newUsername = input.nextLine();
        System.out.println("|   Please enter a password: ");
        String newPassword = input.nextLine();
        System.out.println("|   Please enter your birth date (DD/MM/YYYY): ");
        String newBirthDate = input.nextLine();
        System.out.println("|   Please enter your email: ");
        String newEmail = input.nextLine();

        User newUser = new User(newUsername, newPassword, newBirthDate, newEmail);
        System.out.println("|                                                                           |");
        System.out.println("|      Congratulations "+newUser.userName+",                                |");
        System.out.println("|      you have successfully created a new account!                         |");
        System.out.println("|                                                                           |");
        System.out.println("|***************************************************************************|");
        System.out.println("|===========================================================================|");
        return new mainPage(newUser);
    }
}
