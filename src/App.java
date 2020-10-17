import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;
import structs.*;

public class App {

    

    public static void main(String[] args) throws Exception {
        
        
        
        
        
        //NO TOCAR, ya guarda usuarios en Json.
        DynamicArray<User> UserList = new DynamicArray<User>();
        DynamicArray<Project> ProjectList = new DynamicArray<Project>();
        User Jose = new User("Jose","12345","29/11/2001","jmorenoh");
        Project p1 = new Project("P1", Jose,1234); 
        ProjectList.append(p1);
        UserList.append(Jose);
        UserList.append(new User("Juan","12345","29/11/2001","juaneduardo"));
        UserList.append(new User("Daniel","12345","29/11/2001","danielsantiago"));
        User iterador;
        Gson gson = new Gson();
        JSONArray JsonUserList = new JSONArray();
        String jsonUserString;
        for(int i=0;i<UserList.size;i++){
            JSONObject JsonUser= new JSONObject();
            iterador=UserList.get(i);
            iterador.toSave();
            jsonUserString=gson.toJson(iterador);
            JsonUser.put("User"+i,jsonUserString);
            JsonUserList.add(JsonUser);
            JsonUser.remove("User"+(i-1));
            
        }
        

        try (FileWriter file = new FileWriter("EdUsers.json")) {
 
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
