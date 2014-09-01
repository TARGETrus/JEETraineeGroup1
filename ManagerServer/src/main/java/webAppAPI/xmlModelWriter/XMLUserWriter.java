package webAppAPI.xmlModelWriter;

import java.util.Iterator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Event;
import model.Filter;
import model.Groupp;
import model.User;

public class XMLUserWriter {

	public static void write(XMLStreamWriter out, User user) throws XMLStreamException {

		if (user == null) throw new XMLStreamException();

        out.writeStartDocument();
        out.writeStartElement(XMLTagNames.startTag);
		out.writeStartElement(XMLTagNames.user);
			
		out.writeStartElement(XMLTagNames.user_userID);
		out.writeCharacters(new Integer (user.getUserID()).toString());
		out.writeEndElement();

		if (user.getUserName() != null) {

			out.writeStartElement(XMLTagNames.user_userName);
			out.writeCharacters(user.getUserName());
			out.writeEndElement();

		}

		if (user.getPassword() != null) {

			out.writeStartElement(XMLTagNames.user_password);
			out.writeCharacters(user.getPassword());
			out.writeEndElement();

		}

        if (user.getRole() != null) {

            out.writeStartElement(XMLTagNames.user_role);
            out.writeCharacters(user.getRole());
            out.writeEndElement();

        }

		Iterator<Event> eitr = (user.getEvents()!=null) ? user.getEvents().iterator() : null;
		while (eitr!=null && eitr.hasNext()) {

			Event event = eitr.next();
			out.writeStartElement(XMLTagNames.user_event);
			XMLEventWriter.writeLikeElement(out, event);
			out.writeEndElement();

		}

		Iterator<Groupp> gitr = (user.getGroups()!=null) ? user.getGroups().iterator() : null;
		while (gitr!=null &&  gitr.hasNext()) {

			Groupp group = gitr.next();
			out.writeStartElement(XMLTagNames.user_group);
			XMLGroupWriter.writeLikeElement(out, group);
			out.writeEndElement();

		}

        Iterator<Filter> fitr = (user.getFilters()!=null) ? user.getFilters().iterator() : null;
        while (fitr!=null &&  fitr.hasNext()) {

            Filter filter = fitr.next();
            out.writeStartElement(XMLTagNames.user_filter);
            XMLFilterWriter.writeLikeElement(out, filter);
            out.writeEndElement();

        }

        out.writeEndElement();
        out.writeEndElement();

	}

	public static void writeLikeElement(XMLStreamWriter out, User user) throws XMLStreamException {
				
		out.writeStartElement(XMLTagNames.user_userID);
		out.writeCharacters(new Integer (user.getUserID()).toString());
		out.writeEndElement();
		
		if (user.getUserName() != null) {

			out.writeStartElement(XMLTagNames.user_userName);
			out.writeCharacters(user.getUserName());
			out.writeEndElement();

		}

        if (user.getPassword() != null) {

            out.writeStartElement(XMLTagNames.user_password);
            out.writeCharacters(user.getPassword());
            out.writeEndElement();

        }

        if (user.getRole() != null) {

            out.writeStartElement(XMLTagNames.user_role);
            out.writeCharacters(user.getRole());
            out.writeEndElement();

        }

    }
	
}