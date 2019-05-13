/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author stan_
 */
public class ContactRepositoryImplTest {
    private ContactRepositoryImpl repo;
    private JDBCWrapper jdbc;
    public ContactRepositoryImplTest() throws SQLException {
        repo = new ContactRepositoryImpl();
        jdbc = JDBCWrapper.get();
        jdbc.update("Delete from contact", null);
        repo.save(new Contact("Silky", "Ostrovskii", "123-345-6789", "silky@abc.com", "Future wife", "Fiancee"));
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
    public void tearDown() {
    }

    /**
     * Test of getContactByEmail method, of class ContactRepositoryImpl.
     */
    @Test
    public void testGetContactByEmail() throws SQLException {
        Contact silky = repo.getContactByEmail("silky@abc.com");
        System.out.println(silky.getFirstname() + " " + silky.getLastname());
        assertEquals(silky.getFirstname(), "Silky"); 
    }

    /**
     * Test of save method, of class ContactRepositoryImpl.
     */
    @Test
    public void testSave() throws SQLException {
        System.out.println("save");
        Contact contact = new Contact("Sveni", "Svenson", "123-123-1234", "sven@abc.com", "Sven", "The Skullcrusher");
        repo.save(contact);
        Contact sven = repo.getContactByEmail("sven@abc.com");
        System.out.println(sven.getFirstname() + " " + sven.getLastname());
        assertEquals(sven.getFirstname(), "Sveni");
       
    }

    /**
     * Test of update method, of class ContactRepositoryImpl.
     */
    @Test
    public void testUpdate() throws SQLException {
        System.out.println("update");
        Contact contact = repo.getContactByEmail("silky@abc.com");
        contact.setEmail("silky2@abc.com");
        repo.update(contact);
        Contact contact2 = repo.getContactByEmail("silky2@abc.com");
        System.out.println(contact2.getFirstname() + " " + contact2.getLastname());
        assertEquals(contact2.getFirstname(), "Silky");

    }

    /**
     * Test of delete method, of class ContactRepositoryImpl.
     */
    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");
        Contact contact = repo.getContactByEmail("silky@abc.com");
        repo.delete(contact);
        Contact deletedContact = repo.getContactByEmail("silky@abc.com");
        assertEquals(deletedContact, null);
    }  
    
    @Test
    public void testFindAll() throws SQLException
    {
        List<Contact> list = repo.findAll();
        System.out.println("Found " + list.size() + " contacts");
        list.forEach((contact) -> {
            System.out.println(contact.getEmail());
        });
    }
}
