package my.contacteditor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton wrapper for common JDBC tasks
 * @author Stan Ostrovskii
 *
 */
public class JDBCWrapper {
    private Connection connection;
    private PreparedStatement ps;
    private Statement statement;
    private ResultSet rs;
    private static JDBCWrapper instance;

    private JDBCWrapper() {};

    public static JDBCWrapper get()
    {
        if(instance == null){
            instance = new JDBCWrapper();
        }
        return instance;
    }

    public void open(String url, String... credentials) throws SQLException {
        if (!isOpened ()){
                if(credentials.length==0)
                    connection = DriverManager.getConnection(url);
                else
                    connection = DriverManager.getConnection(url, credentials[0], credentials[1]);
               System.out.println("Connection to database successful");
        }
    }

    public void close () throws SQLException{
        if (isOpened()){
            connection.close();
            connection = null;
        }
    }

    public boolean isOpened (){
        return connection != null;
    }

    public ResultSet select(String query, Object[] args) throws SQLException{
        if (isOpened()){
            ps = connection.prepareStatement(query);
            if (args != null){
                for (int i=0; i<args.length; i++){
                    ps.setObject (i+1, args[i]);
                }
            }
            rs = ps.executeQuery ();
        }
        return rs;
    }
    
    /**
     * @deprecated use select(String query, Object[] args) instead
     */
    @Deprecated 
    public ResultSet select(String query) throws SQLException 
    {
        if(isOpened()) {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        }
        return rs;
    }
    
    public void insert(String sql, Object[] args) throws SQLException 
    {
      if(isOpened()) {
            ps = connection.prepareStatement(sql);
            if (args != null){
                for (int i=0; i<args.length; i++){
                    ps.setObject(i+1, args[i]);
                }
            }
            ps.executeUpdate();
        }
    }
    
    public void update(String sql,  Object[] args) throws SQLException
    {
        if(isOpened()) {
            ps = connection.prepareStatement(sql);
            if (args != null){
                for (int i=0; i<args.length; i++){
                    ps.setObject(i+1, args[i]);
                }
            }
            ps.executeUpdate();
        }
    }
    
    @Override
    public void finalize() throws Throwable
    {
        close();
        super.finalize();
    }
}

