package Projekt;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa UserMainWindow dostarcza graficzny interfejs użytkownika do zarządzania operacjami użytkownika w aplikacji czatu.
 * Umożliwia użytkownikom wysyłanie, odbieranie, szyfrowanie i deszyfrowanie wiadomości, zarządzanie kontaktami oraz wylogowywanie.
 */
public class UserMainWindow extends JFrame{

    private JButton addContactButton;
    private JList messagesList;
    private JButton encryptionButton;
    private JButton decryptionButton;
    private JList contactList;
    private JButton logOutButton;
    private JButton newMessageButton;
    private JTextArea messageText;
    private JTextArea keyText;
    private JLabel usernameLabel;
    private JPanel MainPanel;
    private JScrollPane contactPanel;
    private JButton saveButton;
    private JButton removeButton;

    private boolean isMessageExist=false;
    private User currentUser;
    private JFrame frame;


    private short[] codeMessage;

    /**
     * Domyślny konstruktor, inicjuje główne okno.
     */
    public UserMainWindow()
    {
        frame=this;
        setSize(300, 400);
        setVisible(true);
    }

    /**
     * Przeciążony konstruktor, inicjuje główne okno z danymi użytkownika.
     * @param u Użytkownik, którego dane są wykorzystywane do inicjalizacji okna.
     */

    public UserMainWindow(User u)
    {
        this();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        currentUser=u;
        setContentPane(MainPanel);
        usernameLabel.setText(u.getUserLogin());


        setMessages();
        setNewMessageButton();
        saveButton();
        setAddContactButton();
        setRemoveButton();
        setEncryptionButton();
        setDecryptionButton();
        setLogoutButton();
        setContactList(u,contactList);
    }

    /**
     * Funkcja do obsługi operacji wylogowania. Zamyka obecne okno po wylogowaniu.
     */

    private void setLogoutButton()
    {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    /**
     * Funkcja do inicjowania listy kontaktów. Dodaje listenera do obsługi wyboru kontaktu.
     * @param u Użytkownik, którego lista kontaktów ma być ustawiona.
     * @param list Lista JList zawierająca kontakty.
     */
    private void setContactList(User u, JList<String> list) {
        setList(u, list);

        contactList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    RefreshMessageList(list);
                }
            }
        });
    }

    /**
     * Funkcja do wypełniania listy JList kontaktami.
     * @param u Użytkownik, którego lista kontaktów ma być ustawiona.
     * @param list JList do wypełnienia kontaktami.
     */
    private void setList(User u, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();

        try {

            for (User user : u.getContactList()) {
                model.addElement(user.getUserLogin());
            }
            list.setModel(model);
        } catch (Exception e) {
            //Donothing
        }
    }
    /**
     * Funkcja do odświeżania listy wiadomości.
     * @param list JList zawierająca wiadomości.
     */

    private void RefreshMessageList(JList<String> list) {
        String selectedUser = list.getSelectedValue();
        Pair key = new Pair(currentUser.getUserLogin(), selectedUser);
        Pair key2 = new Pair(selectedUser,currentUser.getUserLogin());
        ArrayList<Message> messages = GUI.thisGUI().getAppManager().
                ArrayfindAllIncomingMessage(currentUser.getUserLogin(), selectedUser);

        if (messages == null) {
            messages = new ArrayList<>();
            GUI.thisGUI().getAppManager().AddMessage(key, messages);
            GUI.thisGUI().getAppManager().AddMessage(key2, messages);
        }

        DefaultListModel<Message> model = new DefaultListModel<>();
        for (Message message : messages) {
            model.addElement(message);
        }

        messagesList.setModel(model);
        try {
            GUI.thisGUI().getAppManager().saveStateToFile("BinarySave.bin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Funkcja do szyfrowania danej wiadomości.
     * @return Zaszyfrowana wiadomość jako łańcuch znaków.
     */

    private void setMessages(){
        messagesList.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Message selectedMessage = (Message) messagesList.getSelectedValue();
                if(selectedMessage.getIsOutcoming())
                {
                    messageText.setText(Projekt.IntsToString(selectedMessage.getContent()));
                    messageText.setEditable(false);
                    keyText.setText(selectedMessage.getKey());
                    keyText.setEditable(false);
                }
                else
                {
                    messageText.setText(encryption(selectedMessage));
                    messageText.setEditable(false);
                    keyText.setEditable(true);
                    keyText.setText(null);
                }
                isMessageExist=true;
            }
        }
    });
    }

    /**
     * Funkcja do szyfrowania danej wiadomości.
     * @param message Wiadomość do zaszyfrowania.
     * @return Zaszyfrowana wiadomość jako łańcuch znaków.
     */

    private String encryption(Message message) {
        short[] intKey = Projekt.TextToInts(message.getKey());
        intKey=Projekt.PasswordPepper(intKey);
        short[] intMessage = message.getContent();
        intMessage= Projekt.Vernam(intMessage,intKey,false);
        intMessage=Projekt.Salting(intMessage,intKey);
        return Projekt.IntsToString(intMessage);
    }
    /**
     * Funkcja do szyfrowania danej wiadomości i zwrócenia wyniku jako tablicy liczb typu short.
     * @param message Wiadomość do zaszyfrowania.
     * @return Zaszyfrowana wiadomość jako tablica liczb typu short.
     */
    private short[] intEncryption(Message message) {
        short[] intKey = Projekt.TextToInts(message.getKey());
        intKey=Projekt.PasswordPepper(intKey);
        short[] intMessage = message.getContent();
        intMessage= Projekt.Vernam(intMessage,intKey,false);
        intMessage=Projekt.Salting(intMessage,intKey);
        return intMessage;
    }

    /**
     * Funkcja do obsługi tworzenia nowych wiadomości.
     */
    private void setNewMessageButton()
    {
        newMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyText.setEditable(true);
                messageText.setEditable(true);
                keyText.setText(null);
                messageText.setText(null);
                isMessageExist=false;
            }
        });
    }

    /**
            * Funkcja do obsługi szyfrowania wiadomości.
     */

    private void setEncryptionButton()
    {
        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short[] intMessage= Projekt.TextToInts(messageText.getText());
                short[] intKey = Projekt.TextToInts(keyText.getText());
                intKey=Projekt.PasswordPepper(intKey);
                intMessage= Projekt.Vernam(intMessage,intKey,false);
                intMessage=Projekt.Salting(intMessage,intKey);
                codeMessage=intMessage;
                messageText.setText(Projekt.IntsToString(intMessage));
            }
        });
    }
    /**
     * Funkcja do obsługi deszyfrowania wiadomości.
     */
    private void setDecryptionButton()
    {
        decryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short[] intMessage = new short[1];
                if(isMessageExist)
                {
                    intMessage=intEncryption((Message) messagesList.getSelectedValue());
                }
                else {
                    intMessage = codeMessage;
                }
                short[] intKey = Projekt.TextToInts(keyText.getText());
                intKey=Projekt.PasswordPepper(intKey);
                intMessage=Projekt.Desalting(intMessage,intKey);
                intMessage= Projekt.Vernam(intMessage,intKey,true);
                messageText.setText(Projekt.IntsToString(intMessage));
            }

        });
    }

    /**
     * Funkcja do obsługi operacji zapisu. Dodaje utworzoną wiadomość do bazy danych.
     */
    private void saveButton()
    {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add to database
                if(!isMessageExist){
                GUI.thisGUI().getAppManager().addSingleMessage(new Pair(currentUser.getUserLogin(),
                        (String)contactList.getSelectedValue()),Projekt.TextToInts(messageText.getText()),keyText.getText());
                //Change message info
                RefreshMessageList(contactList);
                }

            }
        });
    }
    /**
     * Funkcja do obsługi dodawania nowych kontaktów.
     */
    private void setAddContactButton()
    {
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> userContactList = new ArrayList<User>(currentUser.getContactList());
                userContactList.add(currentUser);
                ArrayList<User> allUsers = GUI.thisGUI().getAppManager().getAllUser();
                ArrayList<User> finalList = new ArrayList<>(allUsers);
                finalList.removeAll(userContactList);
                setAddContactWindow(finalList);

            }

        });
    }

    private void setRemoveButton()
    {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String removeLogin = (String) contactList.getSelectedValue();
                    User removeUser = currentUser.findUserByLogin(removeLogin);

                    ((DefaultListModel) contactList.getModel()).remove(selectedIndex);
                    if (removeUser != null) {
                        currentUser.getContactList().remove(removeUser);
                    }

                }
                try {
                    GUI.thisGUI().getAppManager().saveStateToFile("BinarySave.Bin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    /**
     * Funkcja do tworzenia nowego okna do dodawania kontaktów.
     * @param finalList Lista kontaktów do dodania.
     */
    private void setAddContactWindow(ArrayList<User> finalList) {
        JFrame frame = new JFrame("Add New Contact");
        frame.setSize(400, 200);
        JPanel panel = new JPanel();
        DefaultComboBoxModel<User> model = new DefaultComboBoxModel<>();
        for (User user : finalList) {
            model.addElement(user);
        }
        JComboBox<User> comboBox = new JComboBox<>(model);
        panel.add(comboBox);
        ArrayList<User> contactToAdd = new ArrayList<User>();

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(e -> {
            User selectedUser=(User)comboBox.getSelectedItem();
            if(selectedUser!=null){
                contactToAdd.add(selectedUser);
            }
        });
        panel.add(addButton);
        JButton confirmButton = new JButton("Zatwierdź");
        confirmButton.addActionListener(e -> {
            for (User u:contactToAdd
                 ) {
                currentUser.AddContact(u);
            }
            if(currentUser!=null){
            setList(currentUser,contactList);
            }
            frame.dispose();
            try {
                GUI.thisGUI().getAppManager().saveStateToFile("BinarySave.bin");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(confirmButton);
        // Dodajemy panel do ramki i pokazujemy ramkę
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }


}
