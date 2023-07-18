package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klasa Signup reprezentuje formularz rejestracji użytkownika.
 * Jest to klasa dziedzicząca po JFrame, zawierająca różne komponenty interfejsu użytkownika oraz funkcje obsługujące interakcje z użytkownikiem.
 */
public class Signup extends JFrame {
    // Komponenty interfejsu użytkownika
    private JTextField loginText;
    private JTextField passwordText;
    private JTextField emailText;
    private JButton signUpButton;
    private JPanel signupPanel;
    private JLabel Password;

    // Ramka dla formularza rejestracji
    private JFrame frame;

    /**
     * Konstruktor klasy Signup inicjalizuje interfejs użytkownika formularza rejestracji i ustawia jego funkcjonalność.
     */
    public Signup() {
        // Ustawienie tytułu okna
        setTitle("Zarejestruj się");

        // Przypisanie ramki
        frame = this;

        // Dodanie akcji do przycisku rejestracji
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Utworzenie nowego użytkownika na podstawie danych z formularza
                User u = new User(loginText.getText(), passwordText.getText(), emailText.getText());

                // Sprawdzenie poprawności hasła
                boolean isAppropriateLength = passwordText.getText().length() > 2;

                // Warunek sprawdzający unikalność użytkownika i długość hasła
                if(GUI.thisGUI().getAppManager().checkIfUserIsUnique(u) && isAppropriateLength) {
                    GUI.thisGUI().createUser(u);

                    frame.setVisible(false);
                    try {
                        GUI.thisGUI().getAppManager().saveStateToFile("BinarySave.bin");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    if(isAppropriateLength) {
                        JOptionPane.showMessageDialog(null, "Podany login już istnieje", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hasło powinno być większe niż 2", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        setContentPane(signupPanel);
        setSize(300, 400);
        setVisible(true);
    }

    /**
     * Metoda getFrame zwraca ramkę dla formularza rejestracji.
     *
     * @return ramka dla formularza rejestracji
     */
    public JFrame getFrame() {
        return frame;
    }
}
