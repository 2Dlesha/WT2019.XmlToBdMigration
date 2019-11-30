package stud.oiv.migration.dao;

import stud.oiv.migration.beans.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks() throws DaoException;
    void addBooks(List<Book> books)throws DaoException;
}
