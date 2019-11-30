package stud.oiv.migration.dao.XmlDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import stud.oiv.migration.beans.Book;
import stud.oiv.migration.beans.Librarian;
import stud.oiv.migration.dao.BookDao;
import stud.oiv.migration.dao.DaoException;
import stud.oiv.migration.dao.XmlDao.SaxParsers.BookSaxHandler;
import stud.oiv.migration.dao.XmlDao.SaxParsers.LibrarianSaxHandler;

import java.util.List;

public class XmlBookDao implements BookDao {

    public static final Logger LOG = Logger.getLogger(BookDao.class.getName());

    @Override
    public List<Book> getAllBooks()throws DaoException {
        List<Book> books = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            BookSaxHandler handler = new BookSaxHandler();
            reader.setContentHandler(handler);
            reader.parse("D:\\Study\\University\\5 term\\WT\\XmlToBDMigration\\src\\XmlStorage\\books.xml");
            reader.setFeature("http://xml.org/sax/features/validation",true);
            reader.setFeature("http://xml.org/sax/features/namespaces",true);
            reader.setFeature("http://xml.org/sax/features/string-interning",true);
            reader.setFeature("http://apache.org/xml/features/validation/schema",false);
            books = handler.getBooksList();
        }catch (Exception e) {
            LOG.log(Level.ERROR,e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            throw new DaoException();

        }
        return books;
    }

    @Override
    public void addBooks(List<Book> books)throws DaoException {

    }
}

