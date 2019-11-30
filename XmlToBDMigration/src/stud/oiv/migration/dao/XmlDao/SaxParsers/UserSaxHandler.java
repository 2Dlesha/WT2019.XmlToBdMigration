package stud.oiv.migration.dao.XmlDao.SaxParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import stud.oiv.migration.beans.*;

import java.util.ArrayList;
import java.util.List;

public class UserSaxHandler extends DefaultHandler {
    private List<Book> books = new ArrayList<Book>();
    private Book book;
    private List<User> users = new ArrayList<User>();
    private User user;
    private StringBuilder text;

    public List<User> getUsersList()
    {
        return users;
    }

    public void startDocument()
    {

    }

    public void endDocument()
    {

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        text = new StringBuilder();
        if(qName.equals("User"))
        {
            user = new User("","","","",null);
        }
        if(qName.equals("bk:Comics"))
        {
            book = new Comics("",0,"","","");
        }
        if(qName.equals("bk:ArtBook"))
        {
            book = new ArtBook("",0,"","");
        }
        if(qName.equals("bk:StudyBook"))
        {
            book = new StudyBook("",0,"","");
        }
    }

    public void characters(char[] buffer, int start, int length){
        text.append(buffer,start,length);
    }

    public void  endElement(String uri,String localName,String qName)
    {
        if(book != null) {
            BooksTagName tagName = BooksTagName.valueOf(qName.toUpperCase().replace("BK:", ""));
            switch (tagName) {
                case ID:
                    book.setId(Integer.parseInt(text.toString()));
                    break;
                case NAME:
                    book.setName(text.toString());
                    break;
                case TYPE:
                    ((Comics) book).setType(text.toString());
                    break;
                case BOOKS:
                    user.setBooks(new ArrayList<>(books));
                    books = new ArrayList<Book>();
                    break;
                case GENRE:
                    if (book instanceof ArtBook)
                        ((ArtBook) book).setGenre(text.toString());
                    if (book instanceof Comics)
                        ((Comics) book).setGenre(text.toString());
                    break;
                case AUTHOR:
                    book.setAuthor(text.toString());
                    break;
                case SUBJECT:
                    ((StudyBook) book).setSubject(text.toString());
                    break;
                case PAGECOUNT:
                    book.setPageCount(Integer.parseInt(text.toString()));
                    break;
                case STUDYBOOK:
                case COMICS:
                case ARTBOOK:
                    books.add(book);
                    book = null;
                    break;

            }
        }
        else {
            UserTagName userTagName = UserTagName.valueOf(qName.toUpperCase().replace("-", "_"));
            switch (userTagName) {
                case BOOKS:
                    user.setBooks(new ArrayList<>(books));
                    books = new ArrayList<Book>();
                    break;
                case FIRSTNAME:
                    user.setFirstName(text.toString());
                    break;
                case LASTNAME:
                    user.setLastName(text.toString());
                    break;
                case ID:
                    user.setId(Integer.parseInt(text.toString()));
                    break;
                case USER:
                    users.add(user);
                    user = null;
                    break;
                case USERS:
                    break;
                case ADDRESS:
                    user.setAddress(text.toString());
                    break;
                case DATEOFMEMBERSHIP:
                    user.setDateOfMembership(text.toString());
                    break;

            }
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
