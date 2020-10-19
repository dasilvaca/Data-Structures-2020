/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.Sites;
import java.util.Scanner;
/**
 *
 * @author usuario
 */
public class Page {

    Scanner input;

    public Page(){
        this.input = new Scanner(System.in);
    }

    public Page display(){
        return this;
    }

    public boolean showProject(){
        return true;
    }

    public boolean edit(){
        return true;
    }
}
