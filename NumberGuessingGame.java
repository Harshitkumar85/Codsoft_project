import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGuessingGame extends JFrame {
    private int secretNumber;
    private int attempts;
    private int maxAttempts = 10;

    private JLabel infoLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton restartButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        secretNumber = generateRandomNumber();
        attempts = 0;

        infoLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        restartButton = new JButton("Restart");

        JPanel panel = new JPanel();
        panel.add(infoLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(restartButton);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        add(panel);
        setVisible(true);
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        try {
            int userGuess = Integer.parseInt(guessText);
            attempts++;

            if (userGuess < secretNumber) {
                infoLabel.setText("Too low. Try again.");
            } else if (userGuess > secretNumber) {
                infoLabel.setText("Too high. Try again.");
            } else {
                infoLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                guessButton.setEnabled(false);
            }

            if (attempts >= maxAttempts) {
                infoLabel.setText("Out of attempts. The number was " + secretNumber + ".");
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            infoLabel.setText("Invalid input. Please enter a number.");
        }
    }

    private void restartGame() {
        secretNumber = generateRandomNumber();
        attempts = 0;
        infoLabel.setText("Guess a number between 1 and 100:");
        guessField.setText("");
        guessButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}