package Projekt;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Klasa reprezentująca dane aplikacji, zawierająca informacje o wszystkich użytkownikach i ich wiadomościach.
 * Implementuje interfejs Serializable, co pozwala na zapisywanie i odczytywanie jej stanu do/z pliku.
 */
public class AppData implements Serializable{
    // Lista przechowująca wszystkich użytkowników
    private ArrayList<User> allUser;
    // Mapa przechowująca relacje między parami użytkowników a ich wiadomościami
    private HashMap<Pair,ArrayList<Message>> messagesrelation;


    /**
     * Konstruktor klasy, inicjalizuje listę użytkowników i mapę relacji wiadomości.
     */
    public AppData()
    {
        allUser=new ArrayList<User>();
        messagesrelation=new HashMap<Pair, ArrayList<Message>>();
    }


    /**
     * Metoda zwraca listę wszystkich użytkowników.
     */
    public ArrayList<User> GetallUser()
    {
        return allUser;
    }
    /**
     * Metoda dodaje użytkownika do listy użytkowników.
     */
    public void addUser(User u)
    {
        if(u!=null)
        {
            allUser.add(u);
        }
    }
    /**
     * Metoda sprawdza, czy podany użytkownik jest unikalny na liście użytkowników.
     */

    public boolean checkIfUserIsUnique(User checkUser)
    {
        for (User u:allUser)
        {
            if(checkUser.getUserLogin().equals(u.getUserLogin()))
            {
                return false;
            }

        }
        return true;
    }
    /**
     * Metoda znajduje użytkownika na liście użytkowników, korzystając z loginu i hasła.
     */
    public User findUser(String login,String password)
    {
        for (User u:allUser)
        {
            if(u.getUserLogin().equals(login)&&u.getPassword().equals(password))
            {
                return u;
            }
        }
        return null;
    }
    /**
     * Metoda znajduje i zwraca wszystkie wiadomości wysyłane i otrzymywane między dwoma użytkownikami.
     */
    public ArrayList<Message> ArrayfindAllIncomingMessage(String outcoming,String incoming) {
        ArrayList<Message> result = new ArrayList<>();
        Pair out =new Pair(outcoming,incoming);
        if(messagesrelation.containsKey(out)) {
            ArrayList<Message> incomingMessages = messagesrelation.get(out);
            for (Message message : incomingMessages) {
                message.setIsOutgoing(true);
            }
            result.addAll(incomingMessages);
        }

        Pair in = new Pair(incoming, outcoming);
        if(messagesrelation.containsKey(in)) {
            ArrayList<Message> incomingMessages = messagesrelation.get(in);
            for (Message message : incomingMessages) {
                message.setIsOutgoing(false);
            }
            result.addAll(incomingMessages);
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
    /**
     * Metoda zwraca użytkownika z listy na podstawie indeksu.
     */

    public User returnUserWithiLogin(int i)
    {
        return allUser.get(i);
    }

    /**
     * Metoda dodaje listę wiadomości do mapy relacji wiadomości między parą użytkowników.
     */
    public void AddMessage(Pair p, ArrayList<Message> messagetoadd) {
        ArrayList<Message> existingMessages = messagesrelation.get(p);
        if (existingMessages == null) {
            existingMessages = new ArrayList<>();
            messagesrelation.put(p, existingMessages);
        }
        existingMessages.addAll(messagetoadd);
    }
    /**
     * Metoda dodaje pojedynczą wiadomość do mapy relacji wiadomości między parą użytkowników.
     */
    public void addSingleMessage(Pair p,short[]  messageToAdd,String key) {
        ArrayList<Message> existingMessages = messagesrelation.get(p);
        if(existingMessages==null)
        {
            AddPair(p);
            existingMessages = messagesrelation.get(p);
        }
        existingMessages.add(new Message(messageToAdd,new Date(),key));
    }
    /**
     * Metoda zwraca listę wszystkich użytkowników.
     */
    public ArrayList<User> getAllUser()
    {
        return allUser;
    }
    /**
     * Metoda zapisuje stan obiektu do pliku w podstaci binarnej.
     */

    public void saveStateToFile(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }
    /**
     * Metoda odczytuje i zwraca stan obiektu z pliku.
     */
    public static AppData loadStateFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (AppData) ois.readObject();
        }
    }

    /**
     * Metoda dodaje parę użytkowników do mapy relacji wiadomości.
     */
    private void AddPair(Pair p)
    {
        messagesrelation.put(p,null);
    }





}
