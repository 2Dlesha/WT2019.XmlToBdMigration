package stud.oiv.migration.dao.BDDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import stud.oiv.migration.beans.Librarian;
import stud.oiv.migration.dao.BookDao;
import stud.oiv.migration.dao.DaoException;
import stud.oiv.migration.dao.LibrarianDao;

import java.sql.*;
import java.util.List;
import java.util.TimeZone;

/**
 * Bd librarians data source manager class
 */
public class BDLibrarianDao implements LibrarianDao {

    public static final Logger LOG = Logger.getLogger(LibrarianDao.class.getName());

    @Override
    public List<Librarian> getAllLibrarians()throws DaoException {
        return null;
    }

    @Override
    public void addLibrarians(List<Librarian> librarians)throws DaoException {

        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/wt2019?serverTimezone=" + TimeZone.getDefault().getID(),"root","z10012000z");
            System.out.println("Connected successfully");
            String sql = "INSERT INTO librarians(id,first_name,last_name,phone_number) VALUES(?,?,?,?)";
            for(Librarian lib: librarians) {
                String selectSql = "Select * from librarians where (id = ?)";
                PreparedStatement selectPs = con.prepareStatement(selectSql);
                selectPs.setInt(1,lib.getId());
                ResultSet resSet =  selectPs.executeQuery();
                if(!resSet.next()) {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, lib.getId());
                    ps.setString(2, lib.getFirstName());
                    ps.setString(3, lib.getLastName());
                    ps.setString(4, lib.getPhoneNumber());
                    ps.execute();
                }
                else
                {
                    //log
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
