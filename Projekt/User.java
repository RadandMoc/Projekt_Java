package Projekt;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Klasa User reprezentuje użytkownika w systemie.
 * Implementuje interfejs Serializable, co umożliwia zapisywanie i odczytywanie obiektów tej klasy do i z plików.
 */
public class User implements Serializable {

    // login użytkownika
    private String login;
    // hasło użytkownika
    private String password;
    // email użytkownika
    private String email;
    // lista kontaktów użytkownika
    private HashSet<User> contactList;

    /**
     * Konstruktor klasy User, tworzy nowego użytkownika na podstawie podanego loginu, hasła i adresu email.
     *
     * @param login    login użytkownika
     * @param password hasło użytkownika
     * @param email    adres email użytkownika
     */
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        contactList = new HashSet<User>();
    }

    /**
     * Metoda toString umożliwia reprezentację użytkownika jako ciągu znaków (login).
     *
     * @return login użytkownika
     */
    @Override
    public String toString() {
        return login;
    }


    public User findUserByLogin(String login)
    {
        for (User u:contactList
             ) {
            if(u.toString().equals(login))
            {
                return u;
            }

        }
        return null;
    }

    /**
     * Metoda getUserLogin umożliwia odczytanie loginu użytkownika.
     *
     * @return login użytkownika
     */
    public String getUserLogin() {
        return login;
    }

    /**
     * Metoda getPassword umożliwia odczytanie hasła użytkownika.
     *
     * @return hasło użytkownika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda AddContact umożliwia dodanie innego użytkownika do listy kontaktów.
     *
     * @param u użytkownik, który ma zostać dodany do listy kontaktów
     */
    public void AddContact(User u) {
        contactList.add(u);
    }

    /**
     * Metoda getContactList umożliwia odczytanie listy kontaktów użytkownika.
     *
     * @return lista kontaktów użytkownika
     */
    public HashSet<User> getContactList() {
        return contactList;
    }
}
