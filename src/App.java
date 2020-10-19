//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
//import java.text.ParseException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import classes.*;

import structs.*;

public class App {

    
    
    public static void main(String[] args) throws Exception {
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
        }

        
        

        /**User Jose = new User("Jose","12345","139130","jmorenoh");
        Project p1 = new Project("P1",Jose,123);
        Project p2 = new Project("P2",Jose,123);
        Jose.followProject(p2);
        UserList.append(Jose);
        ProjectList.append(p1);
        ProjectList.append(p2);**/
 
       
        
        /**File f = new File("EdUser.json");
        f.delete();
        try {
            f.createNewFile();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        }**/


        
        
        //////////////////////////////////////////////////////////////////////////////////////////////
        /**JSONArray JsonUserList = new JSONArray();
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
        }**/
    }
}
