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
}
