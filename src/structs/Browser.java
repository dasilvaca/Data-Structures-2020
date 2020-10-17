/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserClass;

/**
 *
 * @author usuario
 */
public class Browser {
    
    public Stack<Page> navigationStack = new Stack();
    public Stack<Page> transitionNavigationStack = new Stack();
    public LinkedL<Page> pagesList = new LinkedL();
    public LinkedL<User> users = new LinkedL();
    public Page currentPage;
    
    public Browser(LinkedL pgsList){
        currentPage = new Page("logInPage");
        this.pagesList = pgsList;
        this.navigationStack.push(this.currentPage);
    }
    
    public void open(){
        User enteredUser = this.currentPage.initPage();
        boolean found = false;
        for (int i = 0; i < this.users.size; i++){
            if(this.users.get(i).validateUsers(enteredUser)){
                found = true;
            }
        }
        if (found){
            this.changePage(this.pagesList.get(0));
        }
    }
    
    public void search(){
        String projectToFind = this.currentPage.input.nextLine();
        boolean found = false;
        int index = 0;
        for (int i = 0; i < this.pagesList.size; i++){
            if(pagesList.get(i).title.equals(projectToFind)){
                found = true;
            }
            index++;
        }
        if (found){
            this.changePage(pagesList.get(index));
        } else{
            System.out.print("Proyecto no encontrado :'v");
        }
    }
    
    public void changePage(Page newPage){
        this.navigationStack.push(this.currentPage);
        this.currentPage = newPage;
        this.currentPage.display();
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
