package xmlModelWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Filter;

public class XMLFilterWriter{

    public static void write(XMLStreamWriter out, Filter filter) throws XMLStreamException{
        if (filter == null) throw new XMLStreamException();

        out.writeStartElement(XMLTagNames.startTag);
        out.writeStartElement(XMLTagNames.filter);

        out.writeStartElement(XMLTagNames.filter_filterID);
        out.writeCharacters(new Integer(filter.getFilterID()).toString());
        out.writeEndElement();

        if (filter.getFilterName() != null){
            out.writeStartElement(XMLTagNames.filter_filterName);
            out.writeCharacters(filter.getFilterName());
            out.writeEndElement();
        }

        if (filter.getFilterData() != null){
            out.writeStartElement(XMLTagNames.filter_filterData);
            out.writeCharacters(filter.getFilterData());
            out.writeEndElement();
        }

        if (filter.getUser() != null){
            out.writeStartElement(XMLTagNames.filter_user);
            XMLUserWriter.writeLikeElement(out, filter.getUser());
            out.writeEndElement();
        }

        out.writeEndElement();
        out.writeEndElement();

    }

    public static void writeLikeElement(XMLStreamWriter out, Filter filter) throws XMLStreamException{

        out.writeStartElement(XMLTagNames.filter_filterID);
        out.writeCharacters(new Integer(filter.getFilterID()).toString());
        out.writeEndElement();

        if (filter.getFilterName() != null){
            out.writeStartElement(XMLTagNames.comment_commentName);
            out.writeCharacters(filter.getFilterName());
            out.writeEndElement();
        }

    }

}