package JsonAppAPI.jsonustil;


import DAOHandler.UserDataManager;
import model.Event;
import model.Groupp;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUserModel {

    JSONArray array = new JSONArray();
    JSONObject object = new JSONObject();

    public String getJsonUser(String username){

        if(username.length() != 0){

            UserDataManager userDataManager = new UserDataManager();
            User user = userDataManager.getUserCompleteData(username);
            if(user != null){


                object.put("id", user.getUserID());
                object.put("name", user.getUserName());
                object.put("role",user.getRole());
                ArrayList<String> eventsname = new ArrayList<>();
                for(Event event: userDataManager.getUserCompleteData(username).getEvents()){
                    eventsname.add(event.getEventName());
                }
                object.put("eventlist", eventsname);
                ArrayList<String> groupsname = new ArrayList<>();
                for(Groupp group: userDataManager.getUserCompleteData(username).getGroups()){
                    groupsname.add(group.getGroupName());
                }
                object.put("grouplist", groupsname);
                object.put("status", "OK");
                array.put(object);

            }else {
                object.put("error", "user not found");
                object.put("status", "error");
                array.put(object);
            }

        }else{
            object.put("error", "incorrect date");
            object.put("status", "error");
            array.put(object);
        }



        return array.toString();
    }

    public String getJsonUserlist(){
        UserDataManager userDataManager = new UserDataManager();
        ArrayList<User> users = new ArrayList<>(userDataManager.getAllUsers());
        for(User user : users){
            JSONObject userJson = new JSONObject();
            userJson.put("id", user.getUserID());
            userJson.put("name", user.getUserName());
            userJson.put("role",user.getRole());
            array.put(userJson);
        }
        object.put("status", "OK");
        array.put(object);
        return array.toString();
    }


}
