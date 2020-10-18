/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import structs.*;
import classes.Sites.*;

/**
 *
 * @author usuario
 */
public class Browser {
    
    public Stack<Page> navigationStack = new Stack<Page>();
    public Stack<Page> transitionNavigationStack = new Stack<Page>();
    public LinkedL<Page> pagesList = new LinkedL<Page>();
    public LinkedL<User> users = new LinkedL<User>();
    public Page currentPage;
    
    public void open(DynamicArray<User>  Users){
        logInPage login = new logInPage();
        mainPage mnpg = new mainPage(login.logIn(Users));
        this.currentPage = mnpg;
        while(true){
            if(this.currentPage instanceof projectPage){
                boolean answer = this.currentPage.showProject();
                if(answer){
                    this.returnToPreviousPage();
                }
            } else{
                this.changePage(this.currentPage.display());
            }
        }
    }
    
    public void changePage(Page newPage){
        this.navigationStack.push(this.currentPage);
        this.currentPage = newPage;
    }
    
    public void returnToPreviousPage(){
        this.transitionNavigationStack.push(this.navigationStack.pop());
        this.changePage(this.navigationStack.peek());
    }
    
    public void backToFuturePage(){
        this.navigationStack.push(this.transitionNavigationStack.pop());
        this.changePage(this.navigationStack.peek());
    }
}
