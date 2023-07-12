package Projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JLabel Login;
    private JLabel Password;
    private JButton loginButton;
    private JTextField textField2;
    private JButton Signup;

    private JFrame frame;
    private AppManager appManager;

    private static GUI thisGui;


    public GUI() {
        // Przykład inicjalizacji komponentów (Możesz dostosować według swoich potrzeb)
        thisGui=this;
        setSize(450,300);
        setTitle("Zaloguj");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validPassword())
                {
                    Signup sign = new Signup();
                    frame=sign.getFrame();
                }
                else
                {
                    System.out.print("KOTEK");
                    //Napisz haslo jeszcze raz
                }
            }

            private boolean validPassword() {
                String password = textField1.getText();
                return password != null && !password.isEmpty();
            }
        });
    }

    public static void main(String[] args) {
        GUI g = new GUI();
    }

    public static GUI thisGUI()
    {
        return thisGui;
    }
    public void Kot()
    {
        //pies
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
