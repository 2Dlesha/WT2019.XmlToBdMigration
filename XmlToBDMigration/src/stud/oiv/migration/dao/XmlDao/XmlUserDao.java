package stud.oiv.migration.dao.XmlDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import stud.oiv.migration.beans.Librarian;
import stud.oiv.migration.beans.User;
import stud.oiv.migration.dao.BookDao;
import stud.oiv.migration.dao.DaoException;
import stud.oiv.migration.dao.UserDao;
import stud.oiv.migration.dao.XmlDao.SaxParsers.LibrarianSaxHandler;
import stud.oiv.migration.dao.XmlDao.SaxParsers.UserSaxHandler;

import java.util.List;

public class XmlUserDao implements UserDao {

    public static final Logger LOG = Logger.getLogger(UserDao.class.getName());

    @Override
    public List<User> getAllUsers()throws DaoException {
        List<User> users = null;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            UserSaxHandler handler = new UserSaxHandler();
            reader.setContentHandler(handler);
            reader.parse("D:\\Study\\University\\5 term\\WT\\XmlToBDMigration\\src\\XmlStorage\\users.xml");
            reader.setFeature("http://xml.org/sax/features/validation",true);
            reader.setFeature("http://xml.org/sax/features/namespaces",true);
            reader.setFeature("http://xml.org/sax/features/string-interning",true);
            reader.setFeature("http://apache.org/xml/features/validation/schema",false);
            users = handler.getUsersList();
        }catch (Exception e) {
            LOG.log(Level.ERROR,e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public void addUsers(List<User> users)throws DaoException {

    }
}
