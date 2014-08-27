package webAppAPI.xmlModelWriter;

import java.util.Iterator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Comment;
import model.Event;
import model.Groupp;
import model.User;

public class XMLEventWriter{
	
	public static void write(XMLStreamWriter out, Event event) throws XMLStreamException {

		if (event == null) throw new XMLStreamException();

        out.writeStartDocument();
		out.writeStartElement(XMLTagNames.startTag);
		out.writeStartElement(XMLTagNames.event);
		
			out.writeStartElement(XMLTagNames.event_eventID);
			out.writeCharacters(new Integer (event.getEventID()).toString());
			out.writeEndElement();
			
			if (event.getEventName() != null) {

				out.writeStartElement(XMLTagNames.event_eventName);
				out.writeCharacters(event.getEventName());
				out.writeEndElement();

			}
			
			if (event.getCoordinates() != null) {

				out.writeStartElement(XMLTagNames.event_coordinates);
				out.writeCharacters(event.getCoordinates());
				out.writeEndElement();

			}
			
			if (event.getLatitude() != null) {
				out.writeStartElement(XMLTagNames.event_latitude);
				out.writeCharacters(event.getLatitude().toString());
				out.writeEndElement();
			}
			
			if (event.getLongitude() != null) {

				out.writeStartElement(XMLTagNames.event_longitude);
				out.writeCharacters(event.getLongitude().toString());
				out.writeEndElement();

			}
			
			if (event.getDate() != null) {

				out.writeStartElement(XMLTagNames.event_date);
				out.writeCharacters(event.getDate());
				out.writeEndElement();

			}

            if (event.getEventAdmin() != null) {

                out.writeStartElement(XMLTagNames.event_admin);
                out.writeCharacters(event.getEventAdmin());
                out.writeEndElement();

            }
			
			Iterator<User> uitr = (event.getUsers()!=null) ? event.getUsers().iterator(): null;
			while (uitr!=null && uitr.hasNext()) {

				User user = uitr.next();
				out.writeStartElement(XMLTagNames.event_user);
				XMLUserWriter.writeLikeElement(out, user);
				out.writeEndElement();

			}
			
			Iterator<Groupp> gitr = (event.getGroups()!=null) ? event.getGroups().iterator() : null;
			while (gitr!=null && gitr.hasNext()) {

				Groupp group = gitr.next();
				out.writeStartElement(XMLTagNames.event_group);
				XMLGroupWriter.writeLikeElement(out, group);
				out.writeEndElement();

			}
			
			Iterator<Comment> citr = (event.getComments()!=null) ? event.getComments().iterator(): null;
			while (citr!=null &&  citr.hasNext()) {

				Comment comment = citr.next();
				out.writeStartElement(XMLTagNames.event_comment);
				XMLCommentWriter.writeLikeElement(out, comment);
				out.writeEndElement();

			}
			
		out.writeEndElement();
		out.writeEndElement();

	}

	public static void writeLikeElement(XMLStreamWriter out, Event event) throws XMLStreamException {
		
		out.writeStartElement(XMLTagNames.event_eventID);
		out.writeCharacters(new Integer (event.getEventID()).toString());
		out.writeEndElement();
		
		if (event.getEventName() != null) {

			out.writeStartElement(XMLTagNames.event_eventName);
			out.writeCharacters(event.getEventName());
			out.writeEndElement();

		}

        if (event.getCoordinates() != null) {

            out.writeStartElement(XMLTagNames.event_coordinates);
            out.writeCharacters(event.getCoordinates());
            out.writeEndElement();

        }

        if (event.getLatitude() != null) {

            out.writeStartElement(XMLTagNames.event_latitude);
            out.writeCharacters(event.getLatitude().toString());
            out.writeEndElement();

        }

        if (event.getLongitude() != null) {

            out.writeStartElement(XMLTagNames.event_longitude);
            out.writeCharacters(event.getLongitude().toString());
            out.writeEndElement();

        }

        if (event.getDate() != null) {

            out.writeStartElement(XMLTagNames.event_date);
            out.writeCharacters(event.getDate());
            out.writeEndElement();

        }

        if (event.getEventAdmin() != null) {

            out.writeStartElement(XMLTagNames.event_admin);
            out.writeCharacters(event.getEventAdmin());
            out.writeEndElement();

        }

	}
	
}