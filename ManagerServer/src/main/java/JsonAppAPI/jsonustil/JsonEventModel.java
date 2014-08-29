package JsonAppAPI.jsonustil;


import DAOHandler.EventDataManager;
import model.Event;
import model.Groupp;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonEventModel {

    JSONObject object = new JSONObject();
    JSONArray array = new JSONArray();

    public String getJsonEvent(String eventname){

        if(eventname.length() != 0){
            EventDataManager eventDataManager = new EventDataManager();
            Event event = eventDataManager.getEventData(eventname);

            if(event != null){

                object.put("eventame", event.getEventName());
                object.put("event_id", event.getEventID());
                object.put("event_admin", event.getEventAdmin());
                ArrayList<String> userlist = new ArrayList<>();
                for(User user : eventDataManager.getEventCompleteData(eventname).getUsers()){
                    userlist.add(user.getUserName());
                }
                object.put("userlist", userlist);
                ArrayList<String> grouplist = new ArrayList<>();
                for(Groupp group : eventDataManager.getEventCompleteData(eventname).getGroups()){
                    grouplist.add(group.getGroupName());
                }
                object.put("grouplist", grouplist);
                object.put("status", "OK");
                array.put(object);

            }else{
                object.put("error", "event not found");
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

    public String getJsonEventlist(){
        EventDataManager eventDataManager = new EventDataManager();
        ArrayList<Event> events = new ArrayList<>(eventDataManager.getAllEventsJoin());
        for(Event event : events){
            JSONObject eventJson = new JSONObject();
            object.put("eventame", event.getEventName());
            object.put("event_id", event.getEventID());
            object.put("event_admin", event.getEventAdmin());
            array.put(eventJson);
        }
        object.put("status", "OK");
        array.put(object);
        return array.toString();
    }

}
