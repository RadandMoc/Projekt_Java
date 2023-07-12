package Projekt;

import java.util.HashSet;

public class User {

    private int userId;
    private String login;
    private String password;
    private String email;
    private HashSet<User> contactList;

    public User(String login) {
        this.login = login;
    }
}
