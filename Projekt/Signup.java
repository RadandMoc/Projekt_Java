package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Signup extends JFrame{
    private JTextField loginText;
    private JTextField passwordText;
    private JTextField emailText;
    private JButton signUpButton;
    private JPanel signupPanel;
    private JLabel Password;

    private JFrame frame;


    public Signup() {
        setTitle("Zarejestruj się");
        frame=this;
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User(loginText.getText(), passwordText.getText(), emailText.getText());
                boolean isAppropiateLength=passwordText.getText().length()>2;
                if(GUI.thisGUI().getAppManager().checkIfUserIsUnique(u)&&isAppropiateLength)
                {
                    GUI.thisGUI().createUser(u);
                    u.AddContact(GUI.thisGUI().getAppManager().returnUserWithiLogin(0));
                    u.AddContact(GUI.thisGUI().getAppManager().returnUserWithiLogin(1));
                    frame.setVisible(false);
                    try {
                        GUI.thisGUI().getAppManager().saveStateToFile("BinarySave.bin");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    if(isAppropiateLength)
                    {
                        JOptionPane.showMessageDialog(null, "podany login już istnieje", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "hasło powinno być większe niż 2", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

        });
        setContentPane(signupPanel);
        setSize(300, 400);
        setVisible(true);
    }

    public JFrame getFrame()
    {
        return frame;
    }




}
