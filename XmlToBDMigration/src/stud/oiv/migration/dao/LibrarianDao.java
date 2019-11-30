package stud.oiv.migration.dao;

import stud.oiv.migration.beans.Librarian;

import java.util.List;

public interface LibrarianDao {
    List<Librarian> getAllLibrarians()throws DaoException;
    void addLibrarians(List<Librarian> librarians)throws DaoException;
}
