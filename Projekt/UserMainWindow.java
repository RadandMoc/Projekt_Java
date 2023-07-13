package Projekt;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UserMainWindow extends JFrame{

    private JButton addContactButton;
    private JList list2;
    private JButton button1;
    private JButton button2;
    private JList contactList;
    private JButton logOutButton;
    private JTextField textField1;
    private JButton button3;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel usernameLabel;
    private JPanel MainPanel;

    private User currentUser;


    public UserMainWindow(User u)
    {
        currentUser=u;

        usernameLabel.setText(u.getUserLogin());
        setContactList(u,contactList);
        setContentPane(MainPanel);
        setSize(300, 400);
        setVisible(true);
    }

    public void setContactList(User u, JList<String> list) {
        DefaultListModel<String> model = new DefaultListModel<>();

        for (User user : u.getContactList()) {
            model.addElement(user.getUserLogin());
        }

        list.setModel(model);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // gdy zakończono wybieranie elementu
                    String selectedUser = list.getSelectedValue(); // pobiera wybrany element
                    System.out.println("Wybrano użytkownika: " + selectedUser);
                    // Tu dodaj kod, który ma się wykonać po wybraniu użytkownika
                }
            }
        });
    }




}
