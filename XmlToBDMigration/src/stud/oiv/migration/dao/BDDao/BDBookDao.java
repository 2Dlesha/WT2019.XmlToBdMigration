package stud.oiv.migration.dao.BDDao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import stud.oiv.migration.beans.*;
import stud.oiv.migration.dao.BookDao;
import stud.oiv.migration.dao.DaoException;
import stud.oiv.migration.service.MigrationService;

import java.sql.*;
import java.util.List;
import java.util.TimeZone;

/**
 * Bd Books data source manager class
 */
public class BDBookDao implements BookDao {

    public static final Logger LOG = Logger.getLogger(BookDao.class.getName());

    @Override
    public List<Book> getAllBooks()throws DaoException  {
        return null;
    }

    @Override
    public void addBooks(List<Book> books)throws DaoException {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/wt2019?serverTimezone=" + TimeZone.getDefault().getID(),"root","z10012000z");
            System.out.println("Connected successfully");
            for(Book book: books) {
                if(book instanceof ArtBook) {
                    String selectSql = "Select * from art_books where (id = ?)";
                    PreparedStatement selectPs = con.prepareStatement(selectSql);
                    selectPs.setInt(1,book.getId());
                    ResultSet resSet =  selectPs.executeQuery();
                    if(!resSet.next()) {
                        String sql = "INSERT INTO art_books(id,name,page_count,author,genre) VALUES(?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, book.getId());
                        ps.setString(2, book.getName());
                        ps.setInt(3, book.getPageCount());
                        ps.setString(4, book.getAuthor());
                        ps.setString(5, ((ArtBook) book).getGenre());
                        ps.execute();
                    }else
                    {

                    }
                }else if(book instanceof Comics) {
                    String selectSql = "Select * from comics where (id = ?)";
                    PreparedStatement selectPs = con.prepareStatement(selectSql);
                    selectPs.setInt(1,book.getId());
                    ResultSet resSet =  selectPs.executeQuery();
                    if(!resSet.next()) {
                        String sql = "INSERT INTO comics(id,name,page_count,author,genre,type) VALUES(?,?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, book.getId());
                        ps.setString(2, book.getName());
                        ps.setInt(3, book.getPageCount());
                        ps.setString(4, book.getAuthor());
                        ps.setString(5, ((Comics) book).getGenre());
                        ps.setString(6, ((Comics) book).getType());
                        ps.execute();
                    }else {

                    }
                }else if(book instanceof StudyBook) {
                    String selectSql = "Select * from study_books where (id = ?)";
                    PreparedStatement selectPs = con.prepareStatement(selectSql);
                    selectPs.setInt(1,book.getId());
                    ResultSet resSet =  selectPs.executeQuery();
                    if(!resSet.next()) {
                        String sql = "INSERT INTO study_books(id,name,page_count,author,subject) VALUES(?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, book.getId());
                        ps.setString(2, book.getName());
                        ps.setInt(3, book.getPageCount());
                        ps.setString(4, book.getAuthor());
                        ps.setString(5, ((StudyBook) book).getSubject());
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
