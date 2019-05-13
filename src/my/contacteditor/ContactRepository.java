package my.contacteditor;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Stan Ostrovskii
 */
public interface ContactRepository {
    Contact getContactByEmail(String email) throws SQLException;
    Contact save(Contact contact) throws SQLException;
    Contact update(Contact contact) throws SQLException; 
    Contact delete(Contact contact) throws SQLException;
    List<Contact> findAll() throws SQLException;
}
