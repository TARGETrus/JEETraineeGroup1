package xmlModelWriter;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import model.Comment;

public class XMLCommentWriter{
	public static void write(XMLStreamWriter out,Comment comment) throws XMLStreamException{
		if (comment == null) throw new XMLStreamException();
		
		out.writeStartElement(XMLTagNames.startTag);
		out.writeStartElement(XMLTagNames.comment);
			
			out.writeStartElement(XMLTagNames.comment_commentID);
			out.writeCharacters(new Integer (comment.getCommentID()).toString());
			out.writeEndElement();
			
			if (comment.getCommentName() != null){
				out.writeStartElement(XMLTagNames.comment_commentName);
				out.writeCharacters(comment.getCommentName());
				out.writeEndElement();
			}
			
			if (comment.getComment() != null){
				out.writeStartElement(XMLTagNames.comment_comment);
				out.writeCharacters(comment.getComment());
				out.writeEndElement();
			}
			
			if (comment.getEvent() != null){
				out.writeStartElement(XMLTagNames.comment_event);
				XMLEventWriter.writeLikeElement(out, comment.getEvent());
				out.writeEndElement();
			}
			
		
		out.writeEndElement();
		out.writeEndElement();
	
	}
	
	
	public static void writeLikeElement(XMLStreamWriter out,Comment comment) throws XMLStreamException{
				
		out.writeStartElement(XMLTagNames.comment_commentID);
		out.writeCharacters(new Integer (comment.getCommentID()).toString());
		out.writeEndElement();
		
		if (comment.getCommentName() != null){
			out.writeStartElement(XMLTagNames.comment_commentName);
			out.writeCharacters(comment.getCommentName());
			out.writeEndElement();
		}
	
		
	}
	
	}
