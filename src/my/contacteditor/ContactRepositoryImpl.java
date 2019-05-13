package my.contacteditor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stan Ostrovskii
 */
public class ContactRepositoryImpl implements ContactRepository {

    JDBCWrapper jdbc = JDBCWrapper.get();
    
    public ContactRepositoryImpl() throws SQLException
    {
        String url = "jdbc:sqlite:contactsdb.db";
        String[] credentials = {};
        jdbc.open(url, credentials);
    }
    
    @Override
    public Contact getContactByEmail(String email) throws SQLException {
        String query = "SELECT * from Contact where email = '" + email + "'";
        ResultSet rs = jdbc.select(query);
        if(rs.next())
        {
           Contact contact = new Contact(rs.getString("email"));
           contact.setContactId(rs.getInt("contactId"));
           contact.setFirstname(rs.getString("firstname"));
           contact.setLastname(rs.getString("lastname"));
           contact.setPhone(rs.getString("phone"));
           contact.setNickname(rs.getString("nickname"));
           contact.setTitle(rs.getString("title"));
           return contact;
        } else return null;
    }

    @Override
    public Contact save(Contact contact) throws SQLException {
        String sql = "INSERT INTO Contact(firstname, lastname, phone, email, nickname, title) values ('" +
                contact.getFirstname() + "', '" + contact.getLastname() + "', '" + 
                contact.getPhone() + "', '" + contact.getEmail() + "', '" +
                contact.getNickname() + "', '" + contact.getTitle() + "')";
        jdbc.insert(sql, null);
        return contact;
    }

    @Override
    public Contact update(Contact contact) throws SQLException {
        String sql = "UPDATE Contact SET firstname=?, "
                + "lastname=?, phone=?, "
                + "email=?, nickname=?, "
                + "title=? "
                + "WHERE contactId=" + contact.getContactId();
        String[] args = {contact.getFirstname(), contact.getLastname(), contact.getPhone(), contact.getEmail(), 
        contact.getNickname(), contact.getTitle()};
        jdbc.update(sql, args);
        return contact;

    }

    @Override
    public Contact delete(Contact contact) throws SQLException {
        String sql = "DELETE FROM Contact where email= '"+contact.getEmail()+"'";
        jdbc.update(sql, null);
        return contact;
    }

    @Override
    public List<Contact> findAll() throws SQLException {
        List<Contact> contactList = new ArrayList<>();
        ResultSet rs = jdbc.select("Select * from contact", null);
        while(rs.next())
        {
           Contact contact = new Contact(rs.getString("email"));
           contact.setContactId(rs.getInt("contactId"));
           contact.setFirstname(rs.getString("firstname"));
           contact.setLastname(rs.getString("lastname"));
           contact.setPhone(rs.getString("phone"));
           contact.setNickname(rs.getString("nickname"));
           contact.setTitle(rs.getString("title"));
           contactList.add(contact);
        }
        return contactList;
    }
}
