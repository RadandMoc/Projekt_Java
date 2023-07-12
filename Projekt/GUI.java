package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel panel1;
    private JTextField textField1;
    private JLabel Login;
    private JLabel Password;
    private JButton button1;
    private JTextField textField2;
    private JButton Signup;


    public GUI() {
    button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(validPassword())
            {
                //Zrob kolejna scene
            }
            else
            {
                //Napisz haslo jeszcze raz
            }
        }

        private boolean validPassword() {
            return Boolean.parseBoolean(null);
        }
    });
}
}
