package stud.oiv.migration.service;

import stud.oiv.migration.beans.Book;
import stud.oiv.migration.beans.Librarian;
import stud.oiv.migration.beans.User;
import stud.oiv.migration.dao.BDDao.BDBookDao;
import stud.oiv.migration.dao.BDDao.BDLibrarianDao;
import stud.oiv.migration.dao.BDDao.BDUserDao;
import stud.oiv.migration.dao.DaoException;
import stud.oiv.migration.dao.XmlDao.XmlBookDao;
import stud.oiv.migration.dao.XmlDao.XmlLibrarianDao;
import stud.oiv.migration.dao.XmlDao.XmlUserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.log4j.*;

public class MigrationService {

    public static final Logger LOG = Logger.getLogger( MigrationService.class.getName());

    public static void MigrateAll() throws ServiceException
    {

        try {

            LOG.log(Level.INFO,"Start librarians parsing");
            XmlLibrarianDao librarianDao = new XmlLibrarianDao();
            var librarians = librarianDao.getAllLibrarians();
            BDLibrarianDao bdLibDao = new BDLibrarianDao();
            bdLibDao.addLibrarians(librarians);
            LOG.log(Level.INFO,"End librarians parsing");

            LOG.log(Level.INFO,"Begin users parse");
            XmlUserDao userDao = new XmlUserDao();
            var users = userDao.getAllUsers();
            BDUserDao bdUserDao = new BDUserDao();
            bdUserDao.addUsers(users);
            LOG.log(Level.INFO,"End users parsing");

            LOG.log(Level.INFO,"Begin books parsing");
            XmlBookDao bookDao = new XmlBookDao();
            var books = bookDao.getAllBooks();
            BDBookDao bdBookDao = new BDBookDao();
            bdBookDao.addBooks(books);
            LOG.log(Level.INFO,"End books parsing");
        }
        catch (DaoException e)
        {
            LOG.log(Level.ERROR,e.getMessage());
            throw new ServiceException(e);
        }
    }
}
