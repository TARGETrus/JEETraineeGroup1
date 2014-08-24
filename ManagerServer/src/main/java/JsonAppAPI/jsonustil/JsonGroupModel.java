package JsonAppAPI.jsonustil;


import DAOHandler.GroupDataManager;
import model.Event;
import model.Groupp;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;

public class JsonGroupModel {
    JSONObject object = new JSONObject();
    JSONArray array = new JSONArray();

    public String getJsonGroup(String groupname){

        if(groupname.length() != 0){
            GroupDataManager groupDataManager = new GroupDataManager();
            Groupp group = groupDataManager.getGroupData(groupname);

            if(group != null){

                object.put("geoupname", group.getGroupName());
                object.put("group_id", group.getGroupID());
                object.put("geoup_admin", group.getGroupAdmin());
                ArrayList<String> userlist = new ArrayList<>();
                for(User user : groupDataManager.getGroupCompleteData(groupname).getUsers()){
                    userlist.add(user.getUserName());
                }
                object.put("userlist", userlist);
                ArrayList<String> eventlist = new ArrayList<>();
                for(Event event : groupDataManager.getGroupCompleteData(groupname).getEvents()){
                    eventlist.add(event.getEventName());
                }
                object.put("eventlist", eventlist);
                object.put("status", "OK");
                array.put(object);

            }else{
                object.put("error", "group not found");
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

    public String getJsonGrouplist(){
        GroupDataManager groupDataManager = new GroupDataManager();
        ArrayList<Groupp> groups = new ArrayList<>(groupDataManager.getAllGroups());
        for(Groupp group : groups){
            JSONObject groupJson = new JSONObject();
            groupJson.put("geoupname", group.getGroupName());
            groupJson.put("group_id", group.getGroupID());
            groupJson.put("geoup_admin", group.getGroupAdmin());
            array.put(groupJson);
        }
        object.put("status", "OK");
        array.put(object);
        return array.toString();
    }

}
