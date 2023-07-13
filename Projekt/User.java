package Projekt;

import java.util.HashSet;

public class User {

    private int userId;
    private String login;
    private String password;
    private String email;
    private HashSet<User> contactList;

    public User(int userId,String login,String password,String email) {
        this.userId=userId;
        this.login = login;
        this.password = password;
        this.email = email;
        contactList=new HashSet<User>();
    }

    @Override
    public String toString() {
        return userId+login+password+email;
    }

    public String getUserLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public void AddContact(User u)
    {
        contactList.add(u);
    }
    public HashSet<User> getContactList()
    {
        return contactList;
    }
}
