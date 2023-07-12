package Projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JButton signUpButton;
    private JPanel signupPanel;

    private JFrame frame;


    public Signup() {
        setTitle("Nowe Okno");
        frame=this;
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setContentPane(signupPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setVisible(true);
    }

    public JFrame getFrame()
    {
        return frame;
    }



}
