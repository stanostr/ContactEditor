package my.contacteditor;

/**
 * @author Stan Ostrovskii
 */
public class Contact {
    private int contactId;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String nickname;
    private String title;

    public Contact(String email)
    {
        this.email = email;
    }
    
    public Contact(String firstname, String lastname, String phone, String email, String nickname, String title)
    {   
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
        this.title = title;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
