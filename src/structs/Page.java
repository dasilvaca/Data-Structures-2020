/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserClass;
import java.util.Scanner;
/**
 *
 * @author usuario
 */
public class Page {
    
    public String title;
    public String content;
    public String state;
    public Scanner input = new Scanner(System.in);
    
    public Page(String name){
        this.title = name;
    }
    
    public User initPage(){
        System.out.print("==========================================================================");
        System.out.print("**************************************************************************");
        System.out.print("Welcome to Janu");
        System.out.print("**************************************************************************");
        System.out.print("==========================================================================");
        
        System.out.println("Is this your first time here? : ");
        String response = input.nextLine();
        if (response.equals("Y")){
            System.out.print("**************************************************************************");
            System.out.println("Please enter a username for your account: ");
            String newUsername = input.nextLine();
            System.out.println("Please enter a password for your account: ");
            String newPassword = input.nextLine();
            System.out.println("Please enter your birthdate: ");
            String newBirthDate = input.nextLine();
            System.out.println("Please enter your email: ");
            String newEmail = input.nextLine();
            User newUser = new User(newUsername, newPassword, newBirthDate, newEmail);
            
            System.out.print("**************************************************************************");
            System.out.print("Congratulations "+newUser.userName+", you have sucessfully created your own account!");
            
            return newUser;
        } else{
            System.out.print("**************************************************************************");
            System.out.println("Please enter your username: ");
            String Username = input.nextLine();
            System.out.print("Please enter your password: ");
            String password = input.nextLine();
            
            User enteredUser = new User(Username, password, "xxxxx", "xxxxx");
            return enteredUser;
        }
    }
    
    public void display(){
            
        this.state = "Open";
        System.out.print("==========================================================================");
        System.out.print("**************************************************************************");
        System.out.print(this.title);
        System.out.print("**************************************************************************");
        System.out.print("==========================================================================");
    
        System.out.print("Do you want to search anything? If not, please enter 'N'");
        String enter = input.nextLine();
        if ("N".equals(enter)){
            for (int i = 0; i < 3; i++){
                System.out.print("");
            }
            
        }
        
    }
}
