package stud.oiv.migration.dao;

import stud.oiv.migration.beans.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers()throws DaoException;
    void addUsers(List<User> users)throws DaoException;
}
