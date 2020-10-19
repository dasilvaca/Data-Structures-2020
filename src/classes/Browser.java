/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import structs.*;
import classes.Sites.*;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.text.ParseException;
//import java.lang.Thread.UncaughtExceptionHandler;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author usuario
 */
public class Browser {
    
    public Stack<Page> navigationStack = new Stack<Page>();
    public Stack<Page> transitionNavigationStack = new Stack<Page>();
    public Page currentPage;
    public User currentUser;
    public DynamicArray<User> Users = new DynamicArray<User>();
    public DynamicArray<Project> Projects = new DynamicArray<Project>();

    public void open() {

        this.read();
        logInPage login = new logInPage();
        mainPage mnpg = login.logIn(Users);
        this.currentPage = mnpg;
        this.currentUser = mnpg.currentUser;
        // TODO: volver la condición a verdadera
        while(true){

            if(!this.currentPage.toyDentro){
                this.write();
                break;
            }
            if(this.currentPage instanceof projectPage){
                boolean answer = this.currentPage.showProject();
                if(answer){
                    this.returnToPreviousPage();
                }
            } else{
                if(this.currentPage instanceof editUserProfilePage){
                    boolean response = this.currentPage.edit();
                    if (response){
                        this.returnToPreviousPage();
                    }
                } else{
                    if (this.currentPage instanceof mainPage){
                        this.changePage(this.currentPage.display(this.Projects));
                    }
                    this.changePage(this.currentPage.display());
                }
            }
        }
    }
    
    public void changePage(Page newPage){
        this.navigationStack.push(this.currentPage);
        this.currentPage = newPage;
    }
    
    public void returnToPreviousPage(){
        if (this.navigationStack.top.next != null){
            this.transitionNavigationStack.push(this.navigationStack.pop());
            this.changePage(this.navigationStack.peek());
        } else{
            this.currentPage = new mainPage(this.currentUser);
        }
    }
    
    public void backToFuturePage(){
        this.navigationStack.push(this.transitionNavigationStack.pop());
        this.changePage(this.navigationStack.peek());
    }

    public void read()  {
        DynamicArray<User> UserList = new DynamicArray<User>();
        DynamicArray<Project> ProjectList = new DynamicArray<Project>();
        Gson gson = new Gson();
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader ProjectReader = new FileReader("EdProjects.json"))
        {
            //Read JSON file
            Object objProject = jsonParser.parse(ProjectReader);
 
            JSONArray readProjectList = (JSONArray)objProject;
            for(int l=0;l<readProjectList.size();l++){
                JSONObject JsonReadProject = (JSONObject)readProjectList.get(l);
                String readProjectString =(String)JsonReadProject.get("Project"+l);
                Project readedProject =  gson.fromJson(readProjectString, Project.class);
                ProjectList.append(readedProject);
            }
             
           
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /**catch (ParseException e) {
            e.printStackTrace();
        }**/
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        
        try (FileReader UserReader = new FileReader("EdUser.json"))
        {
            //Read JSON file
            Object objUser =jsonParser.parse(UserReader);
 
            JSONArray readUserList = (JSONArray)objUser;
            for(int k=0;k<readUserList.size();k++){
                JSONObject JsonReadUser = (JSONObject)readUserList.get(k);
                String readUserString =(String)JsonReadUser.get("User"+k);
                User readedUser =  gson.fromJson(readUserString, User.class);
                UserList.append(readedUser);
                Node <String> changer;
                if(readedUser.saveFollowedProjects.size!=0){
                changer=readedUser.saveFollowedProjects.Firstnode;
                while(changer!=null){
                    readedUser.followedProjects.append(ProjectList.getProject((String)changer.data));
                    changer=changer.next;
                }
                readedUser.saveFollowedProjects.makeEmpty();
                }
                if(readedUser.saveOwnProjectList.size!=0){
                changer=readedUser.saveOwnProjectList.Firstnode;
                while(changer!=null){
                    readedUser.ownProjectList.append(ProjectList.getProject(changer.data.toString()));
                    changer=changer.next;
                }
                readedUser.saveOwnProjectList.makeEmpty();
            }
            
        }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /**catch (ParseException e) {
            e.printStackTrace();
        }**/
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        this.Users = UserList;
        this.Projects = ProjectList;
    }
    @SuppressWarnings ("unchecked")
    public void write(){
        DynamicArray<User> UserList = this.Users;
        DynamicArray<Project> ProjectList = this.Projects;

        Gson gson = new Gson();
        JSONArray JsonUserList = new JSONArray();
        String jsonUserString;
        User iterador;
        for(int i=0;i<UserList.size;i++){
            JSONObject JsonUser= new JSONObject();
            iterador=UserList.get(i);
            iterador.toSave();
            jsonUserString=gson.toJson(iterador);
            JsonUser.put("User"+i,jsonUserString);
            JsonUserList.add(JsonUser);
            JsonUser.remove("User"+(i-1));
            
        }
        

        try (FileWriter file = new FileWriter("EdUser.json")) {
 
            file.write(JsonUserList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Project iterator;
        JSONArray JsonProjectList = new JSONArray();
        String jsonProjectString;

        for(int j=0;j<ProjectList.size;j++){
            JSONObject JsonProject= new JSONObject();
            iterator=ProjectList.get(j);
            jsonProjectString=gson.toJson(iterator);
            JsonProject.put("Project"+j,jsonProjectString);
            JsonProjectList.add(JsonProject);
            JsonProject.remove("Project"+(j-1));
            
        }

        try (FileWriter file = new FileWriter("EdProjects.json")) {
 
            file.write(JsonProjectList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
