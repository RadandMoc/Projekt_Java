package Projekt;

import java.io.Serializable;
import java.util.Date;

/**
 * Klasa Message reprezentuje pojedynczą wiadomość w systemie. Wiadomość może być wiadomością wychodzącą lub przychodzącą
 * w zależności od tego kto otrzyma.
 * Wiadomość zawiera treść, datę wysłania i klucz, który jest potrzebny do szyfrowania i deszyfrowania treści wiadomości.
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private short[] content;
    private Date date;
    private boolean isOutgoing;
    private String key;

    /**
     * Konstruktor Message tworzy nową wiadomość.
     * @param content Treść wiadomości jako tablica short.
     * @param date Data wysłania wiadomości.
     * @param key Klucz używany do szyfrowania i deszyfrowania wiadomości.
     */
    public Message(short[] content, Date date, String key) {
        this.content = content;
        this.date = date;
        this.isOutgoing = true;
        this.key = key;
    }

    /**
     * Zwraca datę wysłania wiadomości.
     * @return Data wysłania wiadomości.
     */
    public Object getDate() {
        return this.date;
    }

    /**
     * Zwraca reprezentację string wiadomości, w tym informacje, czy jest to wiadomość wychodząca lub przychodząca,
     * i datę wysłania.
     * @return Reprezentacja string wiadomości.
     */
    public String toString() {
        return this.isOutgoing ? "Wiadomość wychodząca z dnia " + this.date.toString() : "Wiadomość przychodząca z dnia " + this.date.toString();
    }

    /**
     * Sprawdza, czy wiadomość jest wiadomością wychodzącą.
     * @return Prawda, jeśli wiadomość jest wychodząca, fałsz w przeciwnym razie.
     */
    public boolean getIsOutcoming() {
        return this.isOutgoing;
    }

    /**
     * Ustawia, czy wiadomość jest wiadomością wychodzącą.
     * @param b Prawda, jeśli wiadomość jest wychodząca, fałsz w przeciwnym razie.
     */
    public void setIsOutgoing(boolean b) {
        this.isOutgoing = b;
    }

    /**
     * Zwraca treść wiadomości.
     * @return Treść wiadomości jako tablica short.
     */
    public short[] getContent() {
        return this.content;
    }

    /**
     * Zwraca klucz wiadomości.
     * @return Klucz wiadomości jako string.
     */
    public String getKey() {
        return this.key;
    }
}
