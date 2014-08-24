package XMLModelReader;

import model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlModelWriter.XMLTagNames;

import java.util.Stack;

public class XMLFilterReader extends DefaultHandler {

    // Parsed data contains here:
    private Filter filter = new Filter();

    // These stacks are used to get current/parent element names and current book object:
    private Stack<String> elementStack = new Stack<String>();
    private Stack<Object> objectStack  = new Stack<Object>();

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
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

        // If filter or user - objectStack push
        if (XMLTagNames.filter.equals(qName)) {

            this.objectStack.push(this.filter);

        } else if (XMLTagNames.user.equals(qName)) {

            User user = new User();
            this.objectStack.push(user);

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // When closing element is found, remove the opening one from the elementStack:
        this.elementStack.pop();

        // If it is a filter closing element, remove it's object form objectStack as well;
        // If it is a user closing element, remove it's object form objectStack and add to filter:
        if (XMLTagNames.filter.equals(qName)) {

            Object object = this.objectStack.pop();

        } else if (XMLTagNames.user.equals(qName)) {

            this.filter.setUser((User) this.objectStack.pop());

        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();

        // Ignore white spaces:
        if (value.length() == 0) {
            return;
        }

        if (XMLTagNames.filter_filterID.equals(currentElement()) && XMLTagNames.filter.equals(currentElementParent())) { // Read Filter:

            Filter filter = (Filter) this.objectStack.peek();

            try {
                filter.setFilterID(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                System.out.println("exception: " + e.getMessage());
            }

        } else if (XMLTagNames.filter_filterName.equals(currentElement()) && XMLTagNames.filter.equals(currentElementParent())) {

            Filter filter = (Filter) this.objectStack.peek();
            filter.setFilterName(value);

        } else if (XMLTagNames.filter_filterData.equals(currentElement()) && XMLTagNames.filter.equals(currentElementParent())) {

            Filter filter = (Filter) this.objectStack.peek();
            filter.setFilterData(value);

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