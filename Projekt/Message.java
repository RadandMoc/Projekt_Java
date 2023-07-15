package Projekt;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private short[] content;
    private Date date;
    private boolean isOutgoing;
    private String key;


    public Message(short[] content,Date date)
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

    public short[] getContent()
    {
        return content;
    }


    public String getKey() {
        return key;
    }
}
