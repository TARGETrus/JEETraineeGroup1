package XMLModelReader;

import model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlModelWriter.XMLTagNames;

import java.util.Stack;

public class XMLEventReader extends DefaultHandler {

    // Parsed data contains here:
    private Event event = new Event();

    // These stacks are used to get current/parent element names and current book object:
    private Stack<String> elementStack = new Stack<String>();
    private Stack<Object> objectStack  = new Stack<Object>();

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    // We should override default methods to suit our needs:
    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        // We push all start elements we find into elementStack:
        this.elementStack.push(qName);

        // If event or user/group/comment - objectStack push
        if (XMLTagNames.event.equals(qName)) {

            if (XMLTagNames.startTag.equals(currentElementParent())) {

                this.objectStack.push(this.event);

            } else if (XMLTagNames.comment.equals(currentElementParent())) {

                Event event = new Event();
                this.objectStack.push(event);

            }

        } else if (XMLTagNames.user.equals(qName)) {

            User user = new User();
            this.objectStack.push(user);

        } else if (XMLTagNames.group.equals(qName)) {

            Groupp group = new Groupp();
            this.objectStack.push(group);

        } else if (XMLTagNames.comment.equals(qName)) {

            Comment comment = new Comment();
            this.objectStack.push(comment);

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // When closing element is found, remove the opening one from the elementStack:
        this.elementStack.pop();

        // If it is a event closing element, remove it's object form objectStack as well;
        // If it is a user/group/comment closing element, remove it's object form objectStack and add to event:
        if (XMLTagNames.event.equals(qName)) {

            // If it is a event inside Entity, just pop it;
            // If it is a event inside comment, add it:
            if (XMLTagNames.startTag.equals(currentElement())) {

                Object object = this.objectStack.pop();

            } else if (XMLTagNames.comment.equals(currentElement())) {

                Event   event   = (Event) this.objectStack.pop();
                Comment comment = (Comment) this.objectStack.pop();
                comment.setEvent(event);
                this.objectStack.push(comment);

            }

        } else if (XMLTagNames.user.equals(qName)) {

            this.event.getUsers().add( (User) this.objectStack.pop());

        } else if (XMLTagNames.group.equals(qName)) {

            this.event.getGroups().add( (Groupp) this.objectStack.pop());

        } else if (XMLTagNames.comment.equals(qName)) {

            this.event.getComments().add( (Comment) this.objectStack.pop());

        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();

        // Ignore white spaces:
        if (value.length() == 0) {
            return;
        }

        if (XMLTagNames.event_eventID.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) { // Read Event:

            Event event = (Event) this.objectStack.peek();

            try {
                event.setEventID(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.event_eventName.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) {

            Event event = (Event) this.objectStack.peek();
            event.setEventName(value);

        } else if (XMLTagNames.event_coordinates.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) {

            Event event = (Event) this.objectStack.peek();
            event.setCoordinates(value);

        } else if (XMLTagNames.event_latitude.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) {

            Event event = (Event) this.objectStack.peek();

            try {
                event.setLatitude(Float.parseFloat(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.event_longitude.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) {

            Event event = (Event) this.objectStack.peek();

            try {
                event.setLongitude(Float.parseFloat(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.event_date.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) {

            Event event = (Event) this.objectStack.peek();
            event.setDate(value);

        } else if (XMLTagNames.event_admin.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) {

            Event event = (Event) this.objectStack.peek();
            event.setEventAdmin(value);

        } else if (XMLTagNames.user_userID.equals(currentElement()) && XMLTagNames.user.equals(currentElementParent())) { // Read User:

            User user = (User) this.objectStack.peek();

            try {
                user.setUserID(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.user_userName.equals(currentElement()) && XMLTagNames.user.equals(currentElementParent())) {

            User user = (User) this.objectStack.peek();
            user.setUserName(value);

        } else if (XMLTagNames.user_password.equals(currentElement()) && XMLTagNames.user.equals(currentElementParent())) {

            User user = (User) this.objectStack.peek();
            user.setPassword(value);

        } else if (XMLTagNames.user_role.equals(currentElement()) && XMLTagNames.user.equals(currentElementParent())) {

            User user = (User) this.objectStack.peek();
            user.setRole(value);

        } else if (XMLTagNames.group_groupID.equals(currentElement()) && XMLTagNames.group.equals(currentElementParent())) { // Read Group:

            Groupp group = (Groupp) this.objectStack.peek();

            try {
                group.setGroupID(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.group_groupName.equals(currentElement()) && XMLTagNames.group.equals(currentElementParent())) {

            Groupp group = (Groupp) this.objectStack.peek();
            group.setGroupName(value);

        } else if (XMLTagNames.group_groupAdmin.equals(currentElement()) && XMLTagNames.group.equals(currentElementParent())) {

            Groupp group = (Groupp) this.objectStack.peek();
            group.setGroupAdmin(value);

        } else if (XMLTagNames.comment_commentID.equals(currentElement()) && XMLTagNames.comment.equals(currentElementParent())) { // Read Comment:

            Comment comment = (Comment) this.objectStack.peek();

            try {
                comment.setCommentID(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.comment_commentName.equals(currentElement()) && XMLTagNames.comment.equals(currentElementParent())) {

            Comment comment = (Comment) this.objectStack.peek();
            comment.setCommentName(value);

        } else if (XMLTagNames.comment_comment.equals(currentElement()) && XMLTagNames.comment.equals(currentElementParent())) {

            Comment comment = (Comment) this.objectStack.peek();
            comment.setComment(value);

        }

    }

    private String currentElement() {

        return this.elementStack.peek();

    }

    private String currentElementParent() {

        if(this.elementStack.size() < 2) {
            return null;
        }

        return this.elementStack.get(this.elementStack.size()-2);

    }

}