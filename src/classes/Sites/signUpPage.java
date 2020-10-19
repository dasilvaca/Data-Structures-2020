package classes.Sites;

import classes.*;

public class signUpPage extends Page{

    public mainPage signUp(){

        System.out.print("|===========================================================================|");
        System.out.print("|***************************************************************************|");
        System.out.print("|      |- Welcome to Janus! -|                                              |");
        System.out.print("|                                                                           |");
        System.out.println("|   Please enter an username: ");
        String newUsername = input.nextLine();
        System.out.println("|   Please enter a password: ");
        String newPassword = input.nextLine();
        System.out.println("|   Please enter your birth date (DD/MM/YYYY): ");
        String newBirthDate = input.nextLine();
        System.out.println("|   Please enter your email: ");
        String newEmail = input.nextLine();

        User newUser = new User(newUsername, newPassword, newBirthDate, newEmail);
        System.out.print("|                                                                           |");
        System.out.print("|      Congratulations "+newUser.userName+",                                |");
        System.out.print("|      you have successfully created a new account!                         |");
        System.out.print("|                                                                           |");
        System.out.print("|***************************************************************************|");
        System.out.print("|===========================================================================|");
        return new mainPage(newUser);
    }
}
