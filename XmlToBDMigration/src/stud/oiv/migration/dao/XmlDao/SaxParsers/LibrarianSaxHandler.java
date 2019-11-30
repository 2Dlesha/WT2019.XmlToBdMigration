package stud.oiv.migration.dao.XmlDao.SaxParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import stud.oiv.migration.beans.Librarian;

import java.util.ArrayList;
import java.util.List;

public class LibrarianSaxHandler extends DefaultHandler {
    private List<Librarian> librarians = new ArrayList<Librarian>();
    private Librarian librarian;
    private StringBuilder text;

    public List<Librarian> getLibrarianList()
    {
        return librarians;
    }

    public void startDocument()
    {

    }

    public void endDocument()
    {

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        text = new StringBuilder();
        if(qName.equals("Librarian"))
        {
            librarian = new Librarian("","","");
        }
    }

    public void characters(char[] buffer, int start, int length){
        text.append(buffer,start,length);
    }

    public void  endElement(String uri,String localName,String qName)
    {
        LibrarianTagName tagName = LibrarianTagName.valueOf(qName.toUpperCase().replace("-","_"));
        switch(tagName){
            case ID:
                librarian.setId(Integer.parseInt(text.toString()));
                break;
            case PHONENUMBER:
                librarian.setPhoneNumber(text.toString());
                break;
            case LASTNAME:
                librarian.setLastName(text.toString());
                break;
            case FIRSTNAME:
                librarian.setFirstName(text.toString());
                break;
            case LIBRARIAN:
                librarians.add(librarian);
                librarian = null;
                break;
        }
    }

    public void warning(SAXParseException exception)
    {

    }

    public void error(SAXParseException exception){

    }

    public void fatalError(SAXParseException exception){

    }
}
