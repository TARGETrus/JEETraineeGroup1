package xmlModelWriter;


import java.util.Iterator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Event;
import model.Group;
import model.User;

public class XMLGroupWriter{
	
	
	public static void write(XMLStreamWriter out, Group group) throws XMLStreamException{
		if (group == null) throw new XMLStreamException();
		
		out.writeStartElement(XMLTagNames.startTag);
		out.writeStartElement(XMLTagNames.group);
			
			
			out.writeStartElement(XMLTagNames.group_grouptID);
			out.writeCharacters(new Integer (group.getGroupID()).toString());
			out.writeEndElement();
			
			if (group.getGroupName() != null){
				out.writeStartElement(XMLTagNames.group_groupName);
				out.writeCharacters(group.getGroupName());
				out.writeEndElement();
			}
							
			Iterator<User> uitr = (group.getUsers()!=null) ? group.getUsers().iterator() : null;
			while(uitr!= null && uitr.hasNext()){
				User user = uitr.next();
				out.writeStartElement(XMLTagNames.event_user);
				XMLUserWriter.writeLikeElement(out, user);
				out.writeEndElement();
			}
			

			Iterator<Event> eitr = (group.getEvents()!=null) ? group.getEvents().iterator() : null;
			while(eitr!= null && eitr.hasNext()){
				Event event = eitr.next();
				out.writeStartElement(XMLTagNames.user_event);
				XMLEventWriter.writeLikeElement(out, event);
				out.writeEndElement();
			}
			
		out.writeEndElement();
		out.writeEndElement();
	}
	
	public static void writeLikeElement(XMLStreamWriter out, Group group) throws XMLStreamException{
		
		out.writeStartElement(XMLTagNames.group_grouptID);
		out.writeCharacters(new Integer (group.getGroupID()).toString());
		out.writeEndElement();
		
		if (group.getGroupName() != null){
			out.writeStartElement(XMLTagNames.group_groupName);
			out.writeCharacters(group.getGroupName());
			out.writeEndElement();
		}
	}
	
	
}
