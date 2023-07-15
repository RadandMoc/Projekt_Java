package Projekt;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AppManager implements Serializable{
    ArrayList<User> allUser;
    HashMap<Pair,ArrayList<Message>> messagesrelation;

    public AppManager()
    {
        allUser=new ArrayList<User>();
        messagesrelation=new HashMap<Pair, ArrayList<Message>>();
    }

    public ArrayList<User> GetallUser()
    {
        return allUser;
    }

    public void addUser(User u)
    {
        if(u!=null)
        {
            allUser.add(u);
        }
    }


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

    public ArrayList<Message> ArrayfindAllIncomingMessage(String outcoming,String incoming) {
        ArrayList<Message> result = new ArrayList<>();
        Pair out =new Pair(outcoming,incoming);
        if(messagesrelation.containsKey(out)) {
            result.addAll(messagesrelation.get(out));
        }
        Pair in = new Pair(incoming, outcoming);
        if(messagesrelation.containsKey(in)) {
            ArrayList<Message> incomingMessages = messagesrelation.get(in);
            for (Message message : incomingMessages) {
                message.setIsOutgoing();
            }
            result.addAll(incomingMessages);
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }


    public User returnUserWithiLogin(int i)
    {
        return allUser.get(i);
    }


    public void AddMessage(Pair p, ArrayList<Message> messagetoadd) {
        ArrayList<Message> existingMessages = messagesrelation.get(p);
        if (existingMessages == null) {
            existingMessages = new ArrayList<>();
            messagesrelation.put(p, existingMessages);
        }
        existingMessages.addAll(messagetoadd);
    }

    public void addSingleMessage(Pair p,short[]  messageToAdd,String key) {
        ArrayList<Message> existingMessages = messagesrelation.get(p);
        existingMessages.add(new Message(messageToAdd,new Date()));
    }

    public ArrayList<User> getAllUser()
    {
        return allUser;
    }


    public void saveStateToFile(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    public static AppManager loadStateFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (AppManager) ois.readObject();
        }
    }






}
