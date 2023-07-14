package Projekt;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class UserMainWindow extends JFrame{

    private JButton addContactButton;
    private JList messagesList;
    private JButton encryptionButton;
    private JButton decryptionButton;
    private JList contactList;
    private JButton logOutButton;
    private JButton newMessageButton;
    private JTextField messageText;
    private JTextField keyText;
    private JLabel usernameLabel;
    private JPanel MainPanel;
    private JScrollPane contactPanel;
    private JButton saveButton;

    private User currentUser;
    private JFrame frame;

    public UserMainWindow()
    {
        frame=this;
        setSize(300, 400);
        setVisible(true);
    }

    public UserMainWindow(User u)
    {
        this();
        currentUser=u;
        setContentPane(MainPanel);
        usernameLabel.setText(u.getUserLogin());
        setContactList(u,contactList);
        setMessages();
        setNewMessageButton();
        saveButton();
        setAddContactButton();
    }
    private void setContactList(User u, JList<String> list) {
        setList(u, list);
        Pair key = new Pair(currentUser.getUserLogin(), "Kot"); // Przykładowa para klucza
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Przykładowa wiadomość", new Date())); // Dodawanie przykładowej wiadomości do listy
        GUI.thisGUI().getAppManager().AddMessage(key, messages); // Dodawanie wiadomości do pary


        Pair key2 = new Pair("Kot",currentUser.getUserLogin()); // Przykładowa para klucza
        ArrayList<Message> messages2 = new ArrayList<>();
        messages2.add(new Message("dupa wiadomość", new Date())); // Dodawanie przykładowej wiadomości do listy
        GUI.thisGUI().getAppManager().AddMessage(key2, messages2); // Dodawanie wiadomości do pary


        contactList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    RefreshMessageList(list);
                }
            }
        });
    }

    private void setList(User u, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (User user : u.getContactList()) {
            model.addElement(user.getUserLogin());
        }
        list.setModel(model);
    }

    private void RefreshMessageList(JList<String> list) {
        String selectedUser = list.getSelectedValue();
        System.out.println("Wybrano użytkownika: " + selectedUser);

        Pair key = new Pair(currentUser.getUserLogin(), selectedUser);
        ArrayList<Message> messages = GUI.thisGUI().getAppManager().ArrayfindAllIncomingMessage(currentUser.getUserLogin(), selectedUser);

        if (messages == null) {
            messages = new ArrayList<>();
            GUI.thisGUI().getAppManager().AddMessage(key, messages);
        }

        DefaultListModel<Message> model = new DefaultListModel<>();
        for (Message message : messages) {
            model.addElement(message);
        }

        messagesList.setModel(model);
    }

    private void setMessages(){
        messagesList.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Message selectedMessage = (Message) messagesList.getSelectedValue();
                if(selectedMessage.getIsOutcoming())
                {
                    messageText.setText(selectedMessage.getContent());
                    messageText.setEditable(false);
                    keyText.setText(selectedMessage.getKey());
                    keyText.setEditable(false);
                }
                else
                {
                    messageText.setText("Zaszyfrowane");
                    keyText.setEditable(true);
                    keyText.setText(null);
                }
            }
        }
    });
    }

    private void setNewMessageButton()
    {
        newMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyText.setEditable(true);
                messageText.setEditable(true);
                keyText.setText(null);
                messageText.setText(null);
            }
        });
    }

    private void setEncryptionButton()
    {
        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void setDecryptionButton()
    {
        decryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
    }

    private void saveButton()
    {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add to database
                GUI.thisGUI().getAppManager().addSingleMessage(new Pair(currentUser.getUserLogin(),
                        (String)contactList.getSelectedValue()),messageText.getText(),keyText.getText());
                //Change message info
                RefreshMessageList(contactList);
            }
        });
    }

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
            contactToAdd.add(selectedUser);
        });
        panel.add(addButton);
        JButton confirmButton = new JButton("Zatwierdź");
        confirmButton.addActionListener(e -> {
            for (User u:contactToAdd
                 ) {
                currentUser.AddContact(u);
            }
            setList(currentUser,contactList);
            frame.dispose();
        });
        panel.add(confirmButton);
        // Dodajemy panel do ramki i pokazujemy ramkę
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }


}
