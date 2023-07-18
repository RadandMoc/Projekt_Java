package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame{
    private JPanel panel1;
    private JTextField loginText;
    private JLabel Login;
    private JLabel Password;
    private JButton logIn;
    private JTextField passwordText;
    private JButton signupButton;
    private JFrame frame;
    private AppData appData;

    private static GUI thisGui;


    /**
     * Konstruktor GUI. Inicjuje interfejs graficzny i ładuje stan aplikacji z pliku.
     * W przypadku błędów podczas ładowania stanu, rzuca wyjątkiem RuntimeException.
     */

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

/*
        appData=new AppData();



        try {
          appData.saveStateToFile("BinarySave.bin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/


        try {
            appData = AppData.loadStateFromFile("BinarySave.bin");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = appData.findUser(loginText.getText(), passwordText.getText());
                if(u!=null)
                {
                    UserMainWindow userMainWindow = new UserMainWindow(u);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "wprowadzono login lub hasło jest błędne", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
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

    /**
     * Główna metoda aplikacji. Tworzy nowy interfejs GUI.
     * @param args Parametry wiersza poleceń (nieużywane).
     */
    public static void main(String[] args) {
        GUI g = new GUI();
    }

    /**
     * Zwraca aktualną instancję GUI.
     * @return Aktualna instancja GUI.
     */
    public static GUI thisGUI()
    {
        return thisGui;
    }
    /**
     * Dodaje nowego użytkownika do AppManager.
     * @param u Obiekt użytkownika do dodania.
     */
    public void createUser(User u)
    {
        appData.addUser(u);
    }
    /**
     * Zwraca instancję AppManager.
     * @return Instancja AppManager.
     */
    public AppData getAppManager()
    {
        return appData;
    }
    /**
     * Tworzy komponenty interfejsu użytkownika. Wszystkie niestandardowe komponenty powinny być tworzone tutaj.
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
