import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.*;
import structs.*;

public class App {

    

    public static void main(String[] args) throws Exception {
        DynamicArray<User> UserList = new DynamicArray<User>();
        DynamicArray<Project> ProjectList = new DynamicArray<Project>();
        User Jose = new User("Jose","12345","29/11/2001","jmorenoh");
        Project p1 = new Project("P1", Jose,1234); 
        UserList.append(Jose);
        UserList.append(new User("Juan","12345","29/11/2001","juaneduardo"));
        UserList.append(new User("Daniel","12345","29/11/2001","danielsantiago"));
        User iterador;
        Gson gson = new Gson();
        JSONArray JsonUserList = new JSONArray();
        String jsonString;
        for(int i=0;i<UserList.size;i++){
            JSONObject JsonUser= new JSONObject();
            iterador=UserList.get(i);
            iterador.toSave();
            jsonString=gson.toJson(iterador);
            JsonUser.put("User"+i,jsonString);
            JsonUserList.add(JsonUser);
            JsonUser.remove("User"+(i-1));
            
        }
        try (FileWriter file = new FileWriter("EdUsers.json")) {
 
            file.write(JsonUserList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        


        

    }
}
