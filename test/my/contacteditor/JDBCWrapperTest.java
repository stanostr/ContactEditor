package my.contacteditor;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stan_
 */
public class JDBCWrapperTest {
    JDBCWrapper instance;
    public JDBCWrapperTest() throws SQLException {
        instance = JDBCWrapper.get();
        String url = "jdbc:sqlite:contactsdb.db";
        String[] credentials = {};
        instance.open(url, credentials);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() throws Exception {
        instance.close();
    }

    /**
     * Test of select method, of class JDBCWrapper.
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        String query = "select * from contact";
        
        try (ResultSet result = instance.select(query)) {
            while (result.next()) {
                System.out.println("Result: " + result.getString("firstname") +  "\t" +
                        result.getString("lastname") + "\t" +
                        result.getString("title"));
            }
        }
    }
    
    /**
     * Test of select method, of class JDBCWrapper.
     */
    @Test
    public void testSelect2() throws Exception {
        System.out.println("select");
        String query = "select * from contact";
        Object[] args = null;
        try (ResultSet result = instance.select(query, args)) {
            while (result.next()) {
                System.out.println("Result: " + result.getString("firstname") +  "\t" +
                        result.getString("lastname") + "\t" +
                        result.getString("title"));
            }
        }
    }
}
