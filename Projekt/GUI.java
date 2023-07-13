package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private JPanel panel1;
    private JTextField loginText;
    private JLabel Login;
    private JLabel Password;
    private JButton logIn;
    private JTextField passwordText;
    private JButton signupButton;
    private JFrame frame;
    private AppManager appManager;

    private static GUI thisGui;


    public GUI() {
        // Przykład inicjalizacji komponentów (Możesz dostosować według swoich potrzeb)
        thisGui=this;
        setSize(350,300);
        setTitle("Zaloguj");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);

        appManager=new AppManager();

        appManager.addUser(new User(4,"Kot","Pies","rtot"));
        appManager.addUser(new User(5,"Kotek","Pies","rtot"));
        appManager.addUser(new User(6,"Koteczek","Pies","rtot"));


        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = appManager.findUser(loginText.getText(), passwordText.getText());
                if(u!=null)
                {
                    UserMainWindow userMainWindow = new UserMainWindow(u);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "wprowadzono login lub hasło jest błędne", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            private boolean validPassword() {
                String password = loginText.getText();
                return password != null && !password.isEmpty();
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signup sign = new Signup();
                frame=sign.getFrame();
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
    public void createUser(User u)
    {
        appManager.addUser(u);
    }

    public AppManager getAppManager()
    {
        return appManager;
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
