package Projekt;

import java.util.HashSet;

public class User {

    private String login;
    private String password;
    private String email;
    private HashSet<User> contactList;

    public User(String login,String password,String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        contactList=new HashSet<User>();
    }

    @Override
    public String toString() {
        return login;
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
