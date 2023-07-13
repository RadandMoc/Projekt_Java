package Projekt;

import java.util.ArrayList;
import java.util.HashMap;

public class AppManager {
    ArrayList<User> allUser;
    HashMap<Pair,Message> messagesrelation;

    public AppManager()
    {
        allUser=new ArrayList<User>();
        messagesrelation=new HashMap<Pair, Message>();
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





    public User returnUserWithiLogin(int i)
    {
        return allUser.get(i);
    }

}
