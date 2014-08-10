package xmlModelWriter;



public interface XMLTagNames {

	public static String startTag = "Entity";
	
	//List of entities used in XML (In  out XML should be on of these)
	//have to type all fields of Entity manualy :=(

	//for model.User
	public static String user = "user";
	public static String user_userID = "userID";
	public static String user_userName = "userName";
	public static String user_password = "password";
	//List reduced to name of an element
	public static String user_event = "event";
	public static String user_group = "group";
		
	//for model.Event
	public static String event = "event";
	public static String event_eventID = "eventID";
	public static String event_eventName = "eventName";
	public static String event_coordinates = "coordinates";
	public static String event_latitude = "latitude";
	public static String event_longitude = "longitude";
	public static String event_date ="date";
	//List reduced to name of an element
	public static String event_user = "user";
	public static String event_group = "group";
	public static String event_comment = "comment";
	
	//for model.Groupp
	public static String group = "group";
	public static String group_grouptID = "groupID";
	public static String group_groupName = "groupName";
	//List reduced to name of an element
	public static String group_user = "user";
	public static String group_event = "event";
		
	//for model.Comment
	public static String comment = "comment";
	public static String comment_commentID = "commentID";
	public static String comment_commentName = "commentName";
	public static String comment_comment = "comment";
	public static String comment_event = "event";
		
}
