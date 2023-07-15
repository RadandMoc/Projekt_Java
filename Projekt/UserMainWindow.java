package Projekt;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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

    private User currentUser;
    private JFrame frame;

    private short[] codeMessage;
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
        setEncryptionButton();
        setDecryptionButton();
        setLogoutButton();
    }

    private void setLogoutButton()
    {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

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

    private void setList(User u, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (User user : u.getContactList()) {
            model.addElement(user.getUserLogin());
        }
        list.setModel(model);
    }

    private void RefreshMessageList(JList<String> list) {
        String selectedUser = list.getSelectedValue();
        Pair key = new Pair(currentUser.getUserLogin(), selectedUser);
        ArrayList<Message> messages = GUI.thisGUI().getAppManager().
                ArrayfindAllIncomingMessage(currentUser.getUserLogin(), selectedUser);

        if (messages == null) {
            messages = new ArrayList<>();
            GUI.thisGUI().getAppManager().AddMessage(key, messages);
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

    private void setDecryptionButton()
    {
        decryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                short[] intMessage= codeMessage;
                short[] intKey = Projekt.TextToInts(keyText.getText());
                intKey=Projekt.PasswordPepper(intKey);
                intMessage=Projekt.Desalting(intMessage,intKey);
                intMessage= Projekt.Vernam(intMessage,intKey,true);
                messageText.setText(Projekt.IntsToString(intMessage));
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
                        (String)contactList.getSelectedValue()),Projekt.TextToInts(messageText.getText()),keyText.getText());
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
