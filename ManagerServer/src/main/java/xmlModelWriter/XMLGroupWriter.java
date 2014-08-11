package xmlModelWriter;


import java.util.Iterator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Event;
import model.Groupp;
import model.User;

public class XMLGroupWriter{
	
	
	public static void write(XMLStreamWriter out, Groupp groupp) throws XMLStreamException{
		if (groupp == null) throw new XMLStreamException();
		
		out.writeStartElement(XMLTagNames.startTag);
		out.writeStartElement(XMLTagNames.group);
			
			
			out.writeStartElement(XMLTagNames.group_grouptID);
			out.writeCharacters(new Integer (groupp.getGroupID()).toString());
			out.writeEndElement();
			
			if (groupp.getGroupName() != null){
				out.writeStartElement(XMLTagNames.group_groupName);
				out.writeCharacters(groupp.getGroupName());
				out.writeEndElement();
			}
							
			Iterator<User> uitr = (groupp.getUsers()!=null) ? groupp.getUsers().iterator() : null;
			while(uitr!= null && uitr.hasNext()){
				User user = uitr.next();
				out.writeStartElement(XMLTagNames.event_user);
				XMLUserWriter.writeLikeElement(out, user);
				out.writeEndElement();
			}
			

			Iterator<Event> eitr = (groupp.getEvents()!=null) ? groupp.getEvents().iterator() : null;
			while(eitr!= null && eitr.hasNext()){
				Event event = eitr.next();
				out.writeStartElement(XMLTagNames.user_event);
				XMLEventWriter.writeLikeElement(out, event);
				out.writeEndElement();
			}
			
		out.writeEndElement();
		out.writeEndElement();
	}
	
	public static void writeLikeElement(XMLStreamWriter out, Groupp groupp) throws XMLStreamException{
		
		out.writeStartElement(XMLTagNames.group_grouptID);
		out.writeCharacters(new Integer (groupp.getGroupID()).toString());
		out.writeEndElement();
		
		if (groupp.getGroupName() != null){
			out.writeStartElement(XMLTagNames.group_groupName);
			out.writeCharacters(groupp.getGroupName());
			out.writeEndElement();
		}
	}
	
	
}
