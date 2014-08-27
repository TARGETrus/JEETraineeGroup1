package webAppAPI.xmlModelReader;

import model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import webAppAPI.xmlModelWriter.XMLTagNames;

import java.util.Stack;

public class XMLCommentReader extends DefaultHandler {

    // Parsed data contains here:
    private Comment comment = new Comment();

    // These stacks are used to get current/parent element names and current book object:
    private Stack<String> elementStack = new Stack<String>();
    private Stack<Object> objectStack  = new Stack<Object>();

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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

        // If comment or event - objectStack push
        if (XMLTagNames.comment.equals(qName)) {

            this.objectStack.push(this.comment);

        } else if (XMLTagNames.event.equals(qName)) {

            Event event = new Event();
            this.objectStack.push(event);

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // When closing element is found, remove the opening one from the elementStack:
        this.elementStack.pop();

        // If it is a comment closing element, remove it's object form objectStack as well;
        // If it is a event closing element, remove it's object form objectStack and add to filter:
        if (XMLTagNames.comment.equals(qName)) {

            Object object = this.objectStack.pop();

        } else if (XMLTagNames.event.equals(qName)) {

            this.comment.setEvent((Event) this.objectStack.pop());

        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();

        // Ignore white spaces:
        if (value.length() == 0) {
            return;
        }

        if (XMLTagNames.comment_commentID.equals(currentElement()) && XMLTagNames.comment.equals(currentElementParent())) { // Read Comment:

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

        } else if (XMLTagNames.event_eventID.equals(currentElement()) && XMLTagNames.event.equals(currentElementParent())) { // Read Event:

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