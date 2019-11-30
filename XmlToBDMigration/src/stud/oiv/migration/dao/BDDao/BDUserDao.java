package stud.oiv.migration.dao.BDDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import stud.oiv.migration.beans.Book;
import stud.oiv.migration.beans.Librarian;
import stud.oiv.migration.beans.User;
import stud.oiv.migration.dao.BookDao;
import stud.oiv.migration.dao.DaoException;
import stud.oiv.migration.dao.UserDao;

import java.sql.*;
import java.util.List;
import java.util.TimeZone;

/**
 * Bd users data source manager class
 */
public class BDUserDao implements UserDao {

    public static final Logger LOG = Logger.getLogger(UserDao.class.getName());

    @Override
    public List<User> getAllUsers()throws DaoException {
        return null;
    }

    @Override
    public void addUsers(List<User> users)throws DaoException {

        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/wt2019?serverTimezone=" + TimeZone.getDefault().getID(),"root","z10012000z");
            System.out.println("Connected successfully");
            String sql = "INSERT INTO users(id,first_name,last_name,date_of_membership,address) VALUES(?,?,?,?,?)";
            for(User user: users) {
                String selectSql = "Select * from users where (id = ?)";
                PreparedStatement selectPs = con.prepareStatement(selectSql);
                selectPs.setInt(1,user.getId());
                ResultSet resSet =  selectPs.executeQuery();
                if(!resSet.next()) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ps.setString(2, user.getFirstName());
                    ps.setString(3, user.getLastName());
                    ps.setString(4, user.getDateOfMembership());
                    ps.setString(5, user.getAddress());
                    ps.execute();
                }else {

                }
            }

            sql = "INSERT INTO users_books(user_id,book_id) VALUES(?,?)";
            for(User user: users) {
                for (Book book:user.getBooks()) {
                    String selectSql = "Select * from users_books where (user_id = ?) and (book_id = ?)";
                    PreparedStatement selectPs = con.prepareStatement(selectSql);
                    selectPs.setInt(1,user.getId());
                    selectPs.setInt(2,book.getId());
                    ResultSet resSet =  selectPs.executeQuery();
                    if(!resSet.next()) {
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, user.getId());
                        ps.setInt(2, book.getId());
                        ps.execute();
                    }else
                    {

                    }
                }
            }

        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
            LOG.log(Level.ERROR,e.getMessage());
            throw new DaoException(e);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            LOG.log(Level.ERROR,e.getMessage());
            throw new DaoException(e);
        }
        finally {
            try{
                if(con != null){
                    con.close();
                }
            }
            catch (SQLException e){
                LOG.log(Level.ERROR,e.getMessage());
                throw new DaoException(e);
            }
        }
    }
}
