package Projekt;

import java.util.Date;

public class Message {
    private String content;
    private Date date;
    private boolean isOutgoing;
    private String key;


    public Message(String content,Date date)
    {
        this.content=content;
        this.date=date;
        isOutgoing=true;
        key="KLUCZ";
    }

    public Object getDate() {
        return  date;
    }


    @Override
    public String toString() {
        if(isOutgoing){
            return "Wiadomość wychodząca z dnia " + date.toString();
        }
        else
        {
            return "Wiadomość przychodzące z dnia " + date.toString();
        }
    }

    public boolean getIsOutcoming()
    {
        return isOutgoing;
    }
    public void setIsOutgoing() {
        isOutgoing=false;
    }

    public String getContent()
    {
        return content;
    }


    public String getKey() {
        return key;
    }
}
